package com.interdevinc.brokerage;

public class Client {

	private String accountNumber;
	private boolean active;
	private String branchCode;
	private String clientID;
	private String firstName;
	private String fullAccountNumber;
	private String lastName;
	private String repNumber;
	
	/**
	 * @return accountNumber
	 */
	public String getAccountNumber()
	{
		return accountNumber;
	}
	
	/**
	 * @return active
	 */
	public boolean getActive()
	{
		return active;
	}
	
	/**
	 * @return branchCode
	 */
	public String getBranchCode()
	{
		return branchCode;
	}
	
	/**
	 * @return clientFullInfo
	 */
	public String getClientFullInfo()
	{
		return lastName + ", " + firstName + " - " + accountNumber;
	}

	/**
	 * @return clientFullInfoWRepNumber
	 */
	public String getClientFullInfoWRepNumber()
	{
		return lastName + ", " + firstName + " - " + accountNumber + " | " + repNumber;
	}

	/**
	 * @return clientID
	 */
	public String getClientID()
	{
		return clientID;
	}

	/**
	 * @return firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return fullAccountNumber
	 */
	public String getFullAccountNumber()
	{
		return fullAccountNumber;
	}

	/**
	 * @return lastName
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return repNumber
	 */
	public String getRepNumber()
	{
		return repNumber;
	}

	/**
	 * @param an the accountNumber to set
	 */
	public void setAccountNumber(String an)
	{
		accountNumber = an;
	}

	/**
	 * @param act the active to set
	 */
	public void setActive(boolean act)
	{
		active = act;
	}

	/**
	 * @param bc the accountNumber to set
	 */
	public void setBranchCode(String bc)
	{
		branchCode = bc;
	}
	
	/**
	 * @param cid the clientID to set
	 */
	public void setClientID(String cid)
	{
		clientID = cid;
	}

	/**
	 * @param fn the firstName to set
	 */
	public void setFirstName(String fn)
	{
		firstName = fn;
	}

	/**
	 * PLEASE TEST ME BEFORE USING IN PRODUCTION!!!
	 * @param fullAcct the fullAccountNumber to set
	 */
	public void setFullAccountNumber(String fullAcct)
	{
		fullAccountNumber = fullAcct;
		setAccountNumber(fullAcct.substring(fullAcct.indexOf("-"), fullAcct.length()));
		setBranchCode(fullAcct.substring(0, fullAcct.indexOf("-")));
	}

	/**
	 * @param ln the lastName to set
	 */
	public void setLastName(String ln)
	{
		lastName = ln;
	}

	/**
	 * @param rn the repNumber to set
	 */
	public void setRepNumber(String rn)
	{
		repNumber = rn;
	}
	
	/**
	 * toString
	 */
	public String toString()
	{
		StringBuilder clientToString = new StringBuilder();
		clientToString = clientToString.append("[Client: " + clientID + "\n");
		clientToString = clientToString.append("->Account#: " + fullAccountNumber + " -> " + branchCode + "-" + accountNumber + "\n");
		clientToString = clientToString.append("->FirstName: " + firstName + "\n");
		clientToString = clientToString.append("->LastName: " + lastName + "\n");
		clientToString = clientToString.append("->Active: " + active + "]\n");
		return clientToString.toString();
	}
	
}