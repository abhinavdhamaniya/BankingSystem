package com.abhinav.bankingsystem.service;

import java.util.List;
import com.abhinav.bankingsystem.javaClasses.Account;
import com.abhinav.bankingsystem.javaClasses.Operation;
import com.abhinav.bankingsystem.javaClasses.StandingOrder;

public interface BankService {
	
	public void initialiseConnection() throws Exception;
	public Account createAccount(Account account) throws Exception;
	public Account retrieveAccount(int accountId) throws Exception;
	public Account depositIntoAccount(int accountId, int amount) throws Exception;
	public Account withdrawalFromAccount(int accountId, int amount) throws Exception;
	public List<Operation> printHistory(int accountId) throws Exception;
	public StandingOrder createStandingOrder(StandingOrder standingOrder) throws Exception;
}
