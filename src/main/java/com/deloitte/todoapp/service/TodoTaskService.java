package com.deloitte.todoapp.service;

import java.util.List;
import java.util.Optional;

import com.deloitte.todoapp.models.TodoTask;

public interface TodoTaskService {

	public List<TodoTask> getAll(String userId);
	
	public Optional<TodoTask> getById(Long id);
	
	public TodoTask save(TodoTask todoTask);
	
	public void deleteById(Long id);
}
