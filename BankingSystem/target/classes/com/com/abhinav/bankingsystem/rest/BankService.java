package com.abhinav.bankingsystem.rest;

import java.sql.SQLException;

import org.springframework.stereotype.Component;

public interface BankService {
	
	public void initialiseConnection() throws Exception;
	public Account createAccount(Account account) throws Exception;
	public Account retrieveAccount(String accountId) throws Exception;
}
