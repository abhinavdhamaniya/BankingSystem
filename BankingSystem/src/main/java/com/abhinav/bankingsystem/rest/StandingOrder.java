package com.abhinav.bankingsystem.rest;

public class StandingOrder {
	
	private String fromAccount, toAccount;
	private int amount;
	
	public StandingOrder() {}
	
	public StandingOrder(String fromAccount, String toAccount, int amount) {

		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
	}
	
	
	public String getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	public String getToAccount() {
		return toAccount;
	}
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
