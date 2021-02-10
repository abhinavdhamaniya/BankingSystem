package com.abhinav.bankingsystem.error;

public class ErrorResponse {

	int status;
	String message;
	String timestamp;
	
	
	public ErrorResponse(int status, String message, String timestamp) {
		
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	
	
	public int getError() {
		return status;
	}
	public void setError(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
