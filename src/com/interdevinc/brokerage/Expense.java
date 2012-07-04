package com.interdevinc.brokerage;

public class Expense
{
    private String expenseID;
    private String repNumber;
    private String commissionMonth;
    private String expenseName;
    private String expenseAmount;
    private String expenseNote;
    
    /**
     * CONSTRUCTOR: EXPENSE
     */
    public Expense()
    {
    	
    }
    
    public Expense(String eid, String rn, String cm, String en, String ea, String n)
    {
		setExpenseID(eid);
		setRepNumber(rn);
		setCommissionMonth(cm);
		setExpenseName(en);
		setExpenseAmount(ea);
		setExpenseNote(n);
    }

    /**
     * @return the expenseID
     */
    public String getExpenseID()
    {
    	return expenseID;
    }

    /**
     * @return the repNumber
     */
    public String getRepNumber()
    {
        return repNumber;
    }

    /**
     * @return the commissionMonth
     */
    public String getCommissionMonth()
    {
        return commissionMonth;
    }

    /**
     * @return the expenseName
     */
    public String getExpenseName()
    {
        return expenseName;
    }

    /**
     * @return the expenseAmount
     */
    public String getExpenseAmount()
    {
        return expenseAmount;
    }

    /**
     * @return the expenseNote
     */
    public String getExpenseNote()
    {
        return expenseNote;
    }

    /**
     * @param eid the expenseID to set
     */
    public void setExpenseID(String eid)
    {
    	expenseID = eid;
    }

    /**
     * @param rn the repNumber to set
     */
    public void setRepNumber(String rn)
    {
        repNumber = rn;
    }

    /**
     * @param cm the commissionMonth to set
     */
    public void setCommissionMonth(String cm)
    {
        commissionMonth = cm;
    }

    /**
     * @param en the expenseName to set
     */
    public void setExpenseName(String en)
    {
        expenseName = en;
    }

    /**
     * @param ea the expenseAmount to set
     */
    public void setExpenseAmount(String ea)
    {
        expenseAmount = ea;
    }

    /**
     * @param n the expenseNote to set
     */
    public void setExpenseNote(String n)
    {
        expenseNote = n;
    }

    /**
     * toString
     */
	public String toString()
	{
		StringBuilder expenseToString = new StringBuilder();
		expenseToString = expenseToString.append("  [Expense: "+expenseName+"\n");
		expenseToString = expenseToString.append("  ->Amount: "+expenseAmount+"\n");
		expenseToString = expenseToString.append("  ->Note: "+expenseNote+"\n");
		expenseToString = expenseToString.append("  ->Month: "+commissionMonth+"\n");
		expenseToString = expenseToString.append("  ->Broker: "+repNumber+"]\n");
		return expenseToString.toString();
	}
    
}
