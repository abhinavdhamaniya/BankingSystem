package com.abhinav.bankingsystem.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.abhinav.bankingsystem.DAO.BankDAO;
import com.abhinav.bankingsystem.javaClasses.Account;
import com.abhinav.bankingsystem.javaClasses.Operation;
import com.abhinav.bankingsystem.javaClasses.StandingOrder;

@Component("bankservice")
public class AccountService implements BankService{
	
	@Autowired
	@Qualifier("accountDAO")
	BankDAO dao;
	
	@PostConstruct
	public void initialiseConnection() throws Exception
	{}
	
	
	public Account createAccount(Account account) throws Exception
	{	
		return dao.createAccount(account);
		
	}

	public Account retrieveAccount(int accountId) throws Exception {
		
		return dao.retrieveAccount(accountId);
	}
	
	public Account depositIntoAccount(int accountId, int amount) throws Exception
	{
		return dao.depositIntoAccount(accountId, amount);
	}
	
	public Account withdrawalFromAccount(int accountId, int amount) throws Exception
	{
		return dao.withdrawalFromAccount(accountId, amount);
	}
	
	public List<Operation> printHistory(int accountId) throws Exception
	{
		return dao.printHistory(accountId);
	}
	
	public StandingOrder createStandingOrder(StandingOrder standingOrder) throws Exception
	{
		return dao.createStandingOrder(standingOrder);
	}
	
}
