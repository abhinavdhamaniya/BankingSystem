package com.abhinav.bankingsystem.controller;


import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.abhinav.bankingsystem.javaClasses.Account;
import com.abhinav.bankingsystem.javaClasses.Operation;
import com.abhinav.bankingsystem.javaClasses.StandingOrder;
import com.abhinav.bankingsystem.scheduler.Scheduler;
import com.abhinav.bankingsystem.service.BankService;

@RestController
@RequestMapping("/v1.0/accounts")
public class AccountsRestController {
	
	@Autowired
	@Qualifier("bankservice")
	BankService service;
	
	@Autowired
	@Qualifier("standingOrderScheduler")
	Scheduler scheduler;
	
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
	public Account retrieveAccount(@PathVariable int accountId) throws Exception
	{
		return service.retrieveAccount(accountId);	
	}
	
	@PostMapping("/{accountId}/deposits")
	public Account depositIntoAccount(@PathVariable int accountId, @RequestBody int amount) throws Exception
	{
		return service.depositIntoAccount(accountId, amount);	
	}
	
	@PostMapping("/{accountId}/withdrawals")
	public Account withdrawalFromAccount(@PathVariable int accountId, @RequestBody int amount) throws Exception
	{
		return service.withdrawalFromAccount(accountId, amount);	
	}
	
	@GetMapping("/{accountId}/operations")
	public List<Operation> printHistory(@PathVariable int accountId) throws Exception
	{
		return service.printHistory(accountId);	
	}
	
	@PostMapping("/{accountId}/standing-orders")
	public StandingOrder createStandingOrder(@PathVariable String accountId, @RequestBody StandingOrder standingOrder) throws Exception
	{
		standingOrder.setFromAccount(accountId);
		return service.createStandingOrder(standingOrder);
	}
	
	
}
