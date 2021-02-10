package com.abhinav.bankingsystem.scheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("standingOrderScheduler")
public class StandingOrderScheduler implements Scheduler{
	
	
	@Scheduled(fixedDelay = 1000)
	public void scheduleFixedDelayTask() throws Exception {
		
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/bankingsystem", "root", "");
		
		//fetching all the standing orders
		String fetchQuery="SELECT FromAccount, ToAccount, Amount FROM standingorders";
		Statement st= con.createStatement();
		ResultSet rs=st.executeQuery(fetchQuery);
		while(rs.next())
		{
			String fromAccount= rs.getString(1);
			String toAccount= rs.getString(2);
			int amount= rs.getInt(3);
			
			//fetching old balance of payer
			String fetchBalanceQueryPayer= String.format("SELECT AccountBalance FROM accounts WHERE AccountNumber='%s'", fromAccount);
			Statement st1= con.createStatement();
			ResultSet rs1=st1.executeQuery(fetchBalanceQueryPayer);
			rs1.next();
			int payerOldBalance= rs1.getInt(1);
			
			if(payerOldBalance<=0)
			{
				System.out.println(String.format("Standing Order failed for Payer Account: %s. INSUFFICIENT FUNDS.", fromAccount));
				continue;
			}
			
			//fetching old balance of payee
			String fetchBalanceQueryPayee= String.format("SELECT AccountBalance FROM accounts WHERE AccountNumber='%s'", toAccount);
			Statement st2= con.createStatement();
			ResultSet rs2= st2.executeQuery(fetchBalanceQueryPayee);
			rs2.next();
			int payeeOldBalance= rs2.getInt(1);
			
			//updating account balance of payer
			String balanceUpdationQueryPayer= String.format("UPDATE accounts SET AccountBalance = %s WHERE AccountNumber = %s", payerOldBalance-amount, fromAccount);
			PreparedStatement ps0= con.prepareStatement(balanceUpdationQueryPayer);
			int count0= ps0.executeUpdate(balanceUpdationQueryPayer);
			
			//updating account balance of payee
			String balanceUpdationQueryPayee= String.format("UPDATE accounts SET AccountBalance = %s WHERE AccountNumber = %s", payeeOldBalance+amount, toAccount);
			PreparedStatement ps1= con.prepareStatement(balanceUpdationQueryPayee);
			int count1= ps1.executeUpdate(balanceUpdationQueryPayee);
			
			//Logging deposit transaction in operations table
			String query2= String.format("INSERT INTO operations VALUES('deposit', %s, %s, %s, %s)", toAccount, amount, System.currentTimeMillis(), payeeOldBalance+amount);
			PreparedStatement ps2= con.prepareStatement(query2);
			int count2= ps2.executeUpdate(query2);
			
			//Logging withdrawal transaction in operations table
			String query3= String.format("INSERT INTO operations VALUES('withdrawal', %s, %s, %s, %s)", fromAccount, amount, System.currentTimeMillis(), payerOldBalance-amount);
			PreparedStatement ps3= con.prepareStatement(query3);
			int count3= ps3.executeUpdate(query3);
			
			//Logging onto console
			System.out.println(String.format("Standing Order Excecuted. Payer Account Number: %s, Payee Account Number:%s, Amount:%s, Timestamp:%s", fromAccount, toAccount, amount, System.currentTimeMillis() / 1000));
		}
		
	}
	
}
