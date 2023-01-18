package com.deloitte.todoapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.todoapp.models.TodoTask;

@Repository
public interface TodoTaskRepository extends JpaRepository<TodoTask, Long>{
		public List<TodoTask> findByUserId(String userId);
}
