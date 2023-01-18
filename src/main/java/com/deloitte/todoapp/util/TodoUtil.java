package com.deloitte.todoapp.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class TodoUtil {

	private String userId;
	
	public String getUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
		  userId = ((UserDetails)principal).getUsername();
		} else {
		  userId = principal.toString();
		}
		
		return userId;
	}
	
}
