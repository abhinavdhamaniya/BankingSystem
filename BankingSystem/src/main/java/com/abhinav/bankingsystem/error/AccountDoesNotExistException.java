package com.abhinav.bankingsystem.error;

public class AccountDoesNotExistException extends Exception {
	
	public AccountDoesNotExistException(String s)
	{
		super(s);
	}
}
