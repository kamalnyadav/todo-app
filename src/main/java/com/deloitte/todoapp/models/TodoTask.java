package com.deloitte.todoapp.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "todo_tasks")
public class TodoTask implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "Task Description Cannot Be Empty")
	private String description;

	private Boolean isComplete;

	private String createdTime;

	private String updatedTime;

	private String userId;
	
	

	public TodoTask() {
		
	}

	public TodoTask(Long id, @NotEmpty(message = "Task Description Cannot Be Empty") String description,
			Boolean isComplete, String createdTime, String updatedTime, String userId) {
		super();
		this.id = id;
		this.description = description;
		this.isComplete = isComplete;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Boolean isComplete) {
		this.isComplete = isComplete;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "TodoTask [id=" + id + ", description=" + description + ", isComplete=" + isComplete + ", createdTime="
				+ createdTime + ", updatedTime=" + updatedTime + ", userId=" + userId + "]";
	}



}
