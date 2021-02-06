package com.abhinav.bankingsystem.rest;

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

import org.springframework.stereotype.Component;


@Component("bankservice")
public class AccountService implements BankService{
	
	
	@PostConstruct
	public void initialiseConnection() throws Exception
	{}
	
	//used to convert currentTimeMillis to readable Date
	public String currentTimeMillisToDate(String millis)
	{
		long lmillis=Long.parseLong(millis); 
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(lmillis);
        return sdf.format(resultdate);
	}
	
	public Account createAccount(Account account) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/bankingsystem", "root", "");
		
		//updating accounts table
		String query= String.format("INSERT INTO accounts(TypeOfAccount, FirstName, LastName, PhoneNumber, AadharCardNumber, EmailId, Address, DateOfBirth, BankBranch, PinCode, IfscCode, AccountBalance) VALUES('%s', '%s','%s','%s','%s','%s','%s','%s','%s','%s','%s', 0)", account.typeOfAccount, account.firstName, account.lastName, account.phoneNumber, account.aadharCardNumber, account.emailId, account.aadharCardNumber, account.dateOfBirth, account.bankBranch, account.pinCode, account.ifscCode);
		PreparedStatement ps= con.prepareStatement(query);
		int count= ps.executeUpdate(query);
		
		//fetching the last created account
		String fetchQuery="SELECT * FROM accounts WHERE AccountNumber=(SELECT max(AccountNumber) FROM accounts)";
		Statement st= con.createStatement();
		ResultSet rs=st.executeQuery(fetchQuery);
		rs.next();
		
		return new Account(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
		
	}

	public Account retrieveAccount(int accountId) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/bankingsystem", "root", "");
		
		String fetchQuery= String.format("SELECT * FROM accounts WHERE AccountNumber='%s'", accountId);
		Statement st= con.createStatement();
		
		ResultSet rs=st.executeQuery(fetchQuery);
		rs.next();
		
		return new Account(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12), rs.getInt(13));
		
	}
	
	public Account depositIntoAccount(int accountId, int amount) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/bankingsystem", "root", "");
		
		//fetching old balance
		String fetchQuery= String.format("SELECT AccountBalance FROM accounts WHERE AccountNumber='%s'", accountId);
		Statement st= con.createStatement();
		ResultSet rs=st.executeQuery(fetchQuery);
		rs.next();
		int oldBalance= rs.getInt(1);
		
		//updating account balance
		String balanceUpdationQuery= String.format("UPDATE accounts SET AccountBalance = %s WHERE AccountNumber = %s", amount+oldBalance, accountId);
		PreparedStatement ps0= con.prepareStatement(balanceUpdationQuery);
		int count0= ps0.executeUpdate(balanceUpdationQuery);
		
		//updating operations table
		String query= String.format("INSERT INTO operations VALUES('deposit', '%s','%s','%s','%s')", accountId, amount, System.currentTimeMillis(), amount+oldBalance);
		PreparedStatement ps= con.prepareStatement(query);
		int count= ps.executeUpdate(query);
		
		
		//Fetching updated account
		String fetchQuery2= String.format("SELECT * FROM accounts WHERE AccountNumber='%s'", accountId);
		Statement st2= con.createStatement();
		ResultSet rs2=st2.executeQuery(fetchQuery2);
		rs2.next();
		
		return new Account(rs2.getString(1), rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getString(5),rs2.getString(6),rs2.getString(7),rs2.getString(8),rs2.getString(9),rs2.getString(10),rs2.getString(11),rs2.getString(12), rs2.getInt(13));
	
	}
	
	public Account withdrawalFromAccount(int accountId, int amount) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/bankingsystem", "root", "");
		
		//fetching old balance
		String fetchQuery= String.format("SELECT AccountBalance FROM accounts WHERE AccountNumber='%s'", accountId);
		Statement st= con.createStatement();
		ResultSet rs=st.executeQuery(fetchQuery);
		rs.next();
		int oldBalance= rs.getInt(1);
		
		//updating account balance
		String balanceUpdationQuery= String.format("UPDATE accounts SET AccountBalance = %s WHERE AccountNumber = %s", oldBalance-amount, accountId);
		PreparedStatement ps0= con.prepareStatement(balanceUpdationQuery);
		int count0= ps0.executeUpdate(balanceUpdationQuery);
		
		//updating operations table
		String query= String.format("INSERT INTO operations VALUES('withdrawal', '%s','%s','%s','%s')", accountId, amount, System.currentTimeMillis(), oldBalance-amount);
		PreparedStatement ps= con.prepareStatement(query);
		int count= ps.executeUpdate(query);
		
		
		//Fetching updated account
		String fetchQuery2= String.format("SELECT * FROM accounts WHERE AccountNumber='%s'", accountId);
		Statement st2= con.createStatement();
		ResultSet rs2=st2.executeQuery(fetchQuery2);
		rs2.next();
		
		return new Account(rs2.getString(1), rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getString(5),rs2.getString(6),rs2.getString(7),rs2.getString(8),rs2.getString(9),rs2.getString(10),rs2.getString(11),rs2.getString(12), rs2.getInt(13));
	
	}
	
	public List<Operation> printHistory(int accountId) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/bankingsystem", "root", "");
		
		//fetching from operations table
		String fetchQuery= String.format("SELECT * FROM operations WHERE AccountNumber='%s'", accountId);
		Statement st= con.createStatement();
		ResultSet rs=st.executeQuery(fetchQuery);
		
		List<Operation> list= new ArrayList<Operation>();
		while(rs.next())
		{
			list.add(new Operation(rs.getString(1), rs.getString(2), currentTimeMillisToDate(rs.getString(4)), rs.getInt(3), rs.getString(5)));
		}
		
		return list;
	}
	
	public StandingOrder createStandingOrder(StandingOrder standingOrder) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/bankingsystem", "root", "");
		
		//checking payee account exists or not
		String fetchQuery0= String.format("SELECT * FROM accounts WHERE AccountNumber='%s'", standingOrder.getToAccount());
		Statement st0= con.createStatement();
		ResultSet rs0= st0.executeQuery(fetchQuery0);
		if(rs0.next()==false)
		{
			throw new AccountDoesNotExistException("Payee's account doesn't exists in the system.");
		}
		
		//Inserting a new standing order into StandingOrders table
		String query= String.format("INSERT INTO standingorders(FromAccount, ToAccount, Amount) VALUES('%s', '%s','%s')", standingOrder.getFromAccount(), standingOrder.getToAccount(), standingOrder.getAmount());
		PreparedStatement ps= con.prepareStatement(query);
		int count= ps.executeUpdate(query);
		
		//fetching the last created standing order
		String fetchQuery="SELECT FromAccount, ToAccount, Amount FROM standingorders WHERE ID=(SELECT max(ID) FROM standingorders)";
		Statement st= con.createStatement();
		ResultSet rs=st.executeQuery(fetchQuery);
		rs.next();
		
		return new StandingOrder(rs.getString(1), rs.getString(2),rs.getInt(3));
	}
	
}
