package com.interdevinc.brokerage;

public class PayStructure
{
    private String repNumber;
    private double minimumAmount;
    private double maximumAmount;
    private double payPercentage;
    private String percentageAsString;
    
    /**
     * CONSTRUCTOR: PAY STRUCTURE
     */
    public PayStructure()
    {
    	
    }
    
    /**
     * CONSTURCTOR: PAY STRUCTURE
     * @param rn, min, max, pp
     */
    public PayStructure(String rn, double min, double max, double pp)
    {
		setRepNumber(rn);
		setMinimumAmount(min);
		setMaximumAmount(max);
		setPayPercentage(pp);
		setPercentageAsString(pp);
    }

    /**
     * @return the repNumber
     */
    public String getRepNumber()
    {
        return repNumber;
    }

    /**
     * @return the minimumAmount
     */
    public double getMinimumAmount()
    {
        return minimumAmount;
    }

    /**
     * @return the maximumAmount
     */
    public double getMaximumAmount()
    {
        return maximumAmount;
    }

    /**
     * @return the payPercentage
     */
    public double getPayPercentage()
    {
        return payPercentage;
    }

    /**
     * @return the percentageAsString
     */
    public String getPercentageAsString()
    {
        return percentageAsString;
    }

    /**
     * @param rn the repNumber to set
     */
    public void setRepNumber(String rn)
    {
        repNumber = rn;
    }

    /**
     * @param min the minimumAmount to set
     */
    public void setMinimumAmount(double min)
    {
        minimumAmount = min;
    }

    /**
     * @param max the maximumAmount to set
     */
    public void setMaximumAmount(double max)
    {
        maximumAmount = max;
    }

    /**
     * @param pp the payPercentage to set
     */
    public void setPayPercentage(double pp)
    {
        payPercentage = pp;
    }

    /**
     * @param pp the percentageAsString to set
     */
    public void setPercentageAsString(double pp)
    { 
        percentageAsString = new StringBuilder(Double.toString(pp * 100)).append("%").toString();
    }
    
    /**
     * toString
     */
	public String toString()
	{
		StringBuilder payStructureToString = new StringBuilder();
		payStructureToString = payStructureToString.append("  [Pay Structure: "+repNumber+"\n");
		payStructureToString = payStructureToString.append("  ->Min Amount: "+Double.toString(minimumAmount)+"\n");
		payStructureToString = payStructureToString.append("  ->Max Amount: "+Double.toString(maximumAmount)+"\n");
		payStructureToString = payStructureToString.append("  ->Pay Percentat: "+Double.toString(payPercentage)+"\n");
		payStructureToString = payStructureToString.append("  ->Percentage: "+percentageAsString+"]\n");
		return payStructureToString.toString();
	}
	
}
