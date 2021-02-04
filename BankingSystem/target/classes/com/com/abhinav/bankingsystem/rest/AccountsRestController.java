package com.abhinav.bankingsystem.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1.0/accounts")
public class AccountsRestController {
	
	@Autowired
	@Qualifier("bankservice")
	BankService service;
	
	@PostConstruct
	public void initialise() throws Exception
	{

	}
	
	@PostMapping("/")
	public Account createAccount(@RequestBody Account account) throws Exception
	{
		return service.createAccount(account);	
	}
	
	@GetMapping("/{accountId}")
	public Account retrieveAccount(@PathVariable String accountId) throws Exception
	{
		return service.retrieveAccount(accountId);	
	}
}
