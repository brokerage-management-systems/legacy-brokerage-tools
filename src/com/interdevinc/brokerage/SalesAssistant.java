package com.interdevinc.brokerage;

import java.util.ArrayList;

public class SalesAssistant {

	private boolean active;
	private String firstName;
	private String lastName;
	private String pay;
	private String percent;
	private String salesAssistantId;
	
	private ArrayList<Broker> listOfBrokers;

	public boolean getActive()
	{
		return active;
	}

	public ArrayList<Broker> getListOfBrokers()
	{
		return listOfBrokers;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getPay()
	{
		return pay;
	}

	public String getPercent()
	{
		return percent;
	}

	public String getSalesAssistantId()
	{
		return salesAssistantId;
	}

	public void setActive(boolean act) {
		active = act;
	}

	public void setBrokers(ArrayList<Broker> br) {
		listOfBrokers = br;
	}

	public void setFirstName(String fn) {
		firstName = fn;
	}

	public void setLastName(String ln) {
		lastName = ln;
	}

	public void setPay(String py) {
		pay = py;
	}

	public void setPercent(String pt) {
		percent = pt;
	}

	public void setSalesAssistantId(String said) {
		salesAssistantId = said;
	}

	/**
	 * toString
	 */
	public String toString() {
		StringBuilder salesAssistantToString = new StringBuilder();
		salesAssistantToString = salesAssistantToString.append("  [SalesAssistantID: "+salesAssistantId+"\n");
		salesAssistantToString = salesAssistantToString.append("  FirstName: "+firstName+"\n");
		salesAssistantToString = salesAssistantToString.append("  LastName: "+lastName+"\n");
		salesAssistantToString = salesAssistantToString.append("  Active: "+Boolean.toString(active)+"\n");
		for (Broker broker : listOfBrokers) {
			salesAssistantToString.append("  Broker: "+broker.toString()+"\n");
		}
		salesAssistantToString = salesAssistantToString.append("  Pay: "+pay+"]\n");
		return salesAssistantToString.toString();
	}
	
}