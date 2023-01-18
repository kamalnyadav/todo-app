package com.deloitte.todoapp.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.deloitte.todoapp.models.TodoTask;
import com.deloitte.todoapp.service.TodoTaskService;
import com.deloitte.todoapp.util.TodoUtil;

@Controller
public class TodoFormController {

	@Autowired
	private TodoTaskService todoTaskService;
	
	@Autowired
	private TodoUtil todoUtil;

	@GetMapping("/create-task")
	public String createForm(TodoTask todoTask) {
		return "new-todo-task";
	}

	@PostMapping("/create-task")
	public String createTodoTask(@Valid TodoTask todoTask, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new-todo-task";
        }
		todoTask.setUserId(todoUtil.getUserId());
		todoTaskService.save(todoTask);

		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteTodoTask(@PathVariable("id") Long id, Model model) {
		Optional<TodoTask> task = todoTaskService.getById(id);
		if (!task.isPresent()) {
			throw new IllegalArgumentException("Todo Task id: " + id + "not found");
		}
		todoTaskService.deleteById(id);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String showTodoUpdateForm(@PathVariable("id") Long id, Model model) {
		Optional<TodoTask> task = todoTaskService.getById(id);
		TodoTask todoTask = new TodoTask();
		if (task.isPresent()) {
			todoTask = task.get();
		}
		else {
			throw new IllegalArgumentException("Todo Task id: " + id + "not found");
		}
		model.addAttribute("todoTask", todoTask);
		return "edit-todo-task";
	}
	
	@PostMapping("/edit-task/{id}")
	public String editTodoTask(@PathVariable("id") Long id, @Valid TodoTask todoTask, BindingResult result, Model model) {
		Optional<TodoTask> task = todoTaskService.getById(id);
		TodoTask tempTodoTask = new TodoTask();
		String description = todoTask.getDescription();
		Boolean isComplete = todoTask.getIsComplete();
		if (task.isPresent()) {
			tempTodoTask = task.get();
		}
		else {
			throw new IllegalArgumentException("Todo Task id: " + id + "not found");
		}
        if (result.hasErrors()) {
        	model.addAttribute("todoTask", tempTodoTask);
        	model.addAttribute("error", "Task Description Cannot Be Empty ");
            return "edit-todo-task";
        }
        tempTodoTask.setDescription(description);
        tempTodoTask.setIsComplete(isComplete);
		todoTaskService.save(tempTodoTask);

		return "redirect:/";
	}
}
