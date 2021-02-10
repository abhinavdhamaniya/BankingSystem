package com.abhinav.bankingsystem.javaClasses;

public class Account{
	
	public String accountNumber, typeOfAccount, firstName, lastName, phoneNumber, aadharCardNumber, emailId, address, dateOfBirth, bankBranch, ifscCode;
	public String pinCode;
	public long accountBalance;


	public Account(String accountNumber, String typeOfAccount, String firstName, String lastName, String phoneNumber,
			String aadharCardNumber, String emailId, String address, String dateOfBirth, String bankBranch,
			String pinCode, String ifscCode) {

		this.accountNumber = accountNumber;
		this.typeOfAccount = typeOfAccount;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.aadharCardNumber = aadharCardNumber;
		this.emailId = emailId;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.bankBranch = bankBranch;
		this.pinCode = pinCode;
		this.ifscCode = ifscCode;
		this.accountBalance=0;
	}
	
	public Account(String accountNumber, String typeOfAccount, String firstName, String lastName, String phoneNumber,
			String aadharCardNumber, String emailId, String address, String dateOfBirth, String bankBranch,
			String pinCode, String ifscCode, long accountBalance) {

		this.accountNumber = accountNumber;
		this.typeOfAccount = typeOfAccount;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.aadharCardNumber = aadharCardNumber;
		this.emailId = emailId;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.bankBranch = bankBranch;
		this.pinCode = pinCode;
		this.ifscCode = ifscCode;
		this.accountBalance=accountBalance;
	}


	public Account() {
		
	}

	
	public long getAccountBalance() {
		return accountBalance;
	}


	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}

	
	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getIfscCode() {
		return ifscCode;
	}


	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	
	public String getTypeOfAccount() {
		return typeOfAccount;
	}
	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAadharCardNumber() {
		return aadharCardNumber;
	}
	public void setAadharCardNumber(String aadharCardNumber) {
		this.aadharCardNumber = aadharCardNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
}
