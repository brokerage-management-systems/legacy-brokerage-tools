package com.interdevinc.brokerage;

import java.util.ArrayList;

public class Account
{

	private int id;
	private String accountNumber;
	private String branch;
	private String firstName;
	private String fullAccountNumber;
	private String lastName;
	private String repNumber;
	private boolean active;
	
	private ArrayList<Trade> accountTradeList;
	
	public int getID()
	{
		return id;
	}
	
	public String getAccountNumber()
	{
		return accountNumber;
	}
	
	public String getBranch()
	{
		return branch;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getFullAccountNumber()
	{
		return fullAccountNumber;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getRepNumber()
	{
		return repNumber;
	}
	
	public boolean getActive()
	{
		return active;
	}
	
	public ArrayList<Trade> getAccountTradeList()
	{
		return accountTradeList;
	}

	public void setID(int id)
	{
		this.id = id;
	}
	
	public void setAccountNumber(String an)
	{
		accountNumber = an;
	}
	
	public void setBranch(String br)
	{
		branch = br;
	}
	
	public void setFirstName(String fn)
	{
		firstName = fn;
	}
	
	public void setFullAccountNumber(String fan)
	{
		fullAccountNumber = fan;
	}
	
	public void setLastName(String ln)
	{
		lastName = ln;
	}
	
	public void setRepNumber(String bn)
	{
		repNumber = bn;
	}
	
	public void setActive(boolean act)
	{
		active = act;
	}
	
	public void setAccountTradeList(ArrayList<Trade> atl)
	{
		accountTradeList = atl;
	}
	
	public String toString() {
		StringBuilder accountToString = new StringBuilder(); 
		accountToString = accountToString.append("ID: "+id+"\n");
		accountToString = accountToString.append("Account#: "+accountNumber+"\n");
		accountToString = accountToString.append("FirstName: "+firstName+"\n");
		accountToString = accountToString.append("LastName: "+lastName+"\n");
		accountToString = accountToString.append("Broker#: "+repNumber+"\n");
		accountToString = accountToString.append("Active: "+active+"\n");
		for (Trade trade : accountTradeList) {
			accountToString.append("Trade: "+trade.toString()+"\n");
		}
		return accountToString.toString();
	}
	
}