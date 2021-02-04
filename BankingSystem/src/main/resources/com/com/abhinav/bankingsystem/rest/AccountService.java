package com.abhinav.bankingsystem.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;


@Component("bankservice")
public class AccountService implements BankService{
	
	
	@PostConstruct
	public void initialiseConnection() throws Exception
	{
		
	}
	
	public Account createAccount(Account account) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/bankingsystem", "root", "");
		
		String query= String.format("INSERT INTO accounts(TypeOfAccount, FirstName, LastName, PhoneNumber, AadharCardNumber, EmailId, Address, DateOfBirth, BankBranch, PinCode, IfscCode) VALUES('%s', '%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')", account.typeOfAccount, account.firstName, account.lastName, account.phoneNumber, account.aadharCardNumber, account.emailId, account.aadharCardNumber, account.dateOfBirth, account.bankBranch, account.pinCode, account.ifscCode);
		System.err.println(query);
		PreparedStatement ps= con.prepareStatement(query);
		int count= ps.executeUpdate(query);
		
		String fetchQuery="SELECT * FROM accounts WHERE AccountNumber=(SELECT max(AccountNumber) FROM accounts)";
		Statement st= con.createStatement();
		ResultSet rs=st.executeQuery(fetchQuery);
		rs.next();
		
		return new Account(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
		
	}

	public Account retrieveAccount(String accountId) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/bankingsystem", "root", "");
		
		String fetchQuery= String.format("SELECT * FROM accounts WHERE AccountNumber='%s'", accountId);
		Statement st= con.createStatement();
		
		ResultSet rs=st.executeQuery(fetchQuery);
		rs.next();
		
		return new Account(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
		
	}
	
}
