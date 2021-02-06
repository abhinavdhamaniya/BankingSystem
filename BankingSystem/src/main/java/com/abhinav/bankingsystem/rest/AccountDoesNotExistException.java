package com.abhinav.bankingsystem.rest;

public class AccountDoesNotExistException extends Exception {
	
	AccountDoesNotExistException(String s)
	{
		super(s);
	}
}
