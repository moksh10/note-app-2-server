package com.project.noteappmain.handlers;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException() {
		// TODO Auto-generated constructor stub
		super("User not found");
	}
}
