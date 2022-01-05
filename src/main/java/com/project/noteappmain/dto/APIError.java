package com.project.noteappmain.dto;

import java.sql.Timestamp;


public class APIError {
	private int status;
    private String message;
    private Timestamp timestamp;
    private String error;

    public APIError() {
    	
    }
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

 
}
