package com.abhinav.bankingsystem.rest;

public class ErrorResponse {

	int status;
	String message;
	long timestamp;
	
	
	public ErrorResponse(int status, String message, long timestamp) {
		
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
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
