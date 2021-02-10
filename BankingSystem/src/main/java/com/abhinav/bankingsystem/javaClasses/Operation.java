package com.abhinav.bankingsystem.javaClasses;


public class Operation {
	
	String type, date, balanceAfterTransaction;
	String accountNumber;
	int amount;
	
	public Operation(String type,  String accountNumber, String date, int amount, String balanceAfterTransaction) {
	
		this.type = type;
		this.date = date;
		this.amount = amount;
		this.balanceAfterTransaction = balanceAfterTransaction;
		this.accountNumber = accountNumber;
	}
	
	Operation(){}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getBalanceAfterTransaction() {
		return balanceAfterTransaction;
	}

	public void setBalanceAfterTransaction(String balanceAfterTransaction) {
		this.balanceAfterTransaction = balanceAfterTransaction;
	}


	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
