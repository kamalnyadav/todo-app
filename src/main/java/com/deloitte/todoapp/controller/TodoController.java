package com.deloitte.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deloitte.todoapp.service.TodoTaskService;
import com.deloitte.todoapp.util.TodoUtil;

@Controller
public class TodoController {

	@Autowired
	private TodoTaskService todoTaskService;
	
	@Autowired
	private TodoUtil todoUtil;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("todoTasks", todoTaskService.getAll(todoUtil.getUserId()));
		return modelAndView;
	}

	  @RequestMapping("/login-error")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);
	    return "login.html";
	  }
	  
}
