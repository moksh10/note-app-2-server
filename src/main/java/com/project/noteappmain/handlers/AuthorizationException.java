package com.project.noteappmain.handlers;

@SuppressWarnings("serial")
public class AuthorizationException extends RuntimeException {

	public AuthorizationException() {
		super("Authorization failed");
	}
}
