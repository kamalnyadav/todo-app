package com.deloitte.todoapp.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.todoapp.models.TodoTask;
import com.deloitte.todoapp.repositories.TodoTaskRepository;
import com.deloitte.todoapp.util.TodoUtil;

@Service
public class TodoTaskServiceImpl implements TodoTaskService {

	@Autowired
	private TodoTaskRepository todoTaskRepository;
	
	public List<TodoTask> getAll(String userId){
		return todoTaskRepository.findByUserId(userId);
	}
	
	public Optional<TodoTask> getById(Long id) {
		return todoTaskRepository.findById(id);
	}
	
	public TodoTask save(TodoTask todoTask) {
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		  LocalDateTime now = LocalDateTime.now(); 
		if(todoTask.getId() == null) {
			todoTask.setCreatedTime(dtf.format(now));
		}

		todoTask.setUpdatedTime(dtf.format(now));
		return todoTaskRepository.save(todoTask);
	}
	
    public void deleteById(Long id) {
    	todoTaskRepository.deleteById(id);
    }
}
