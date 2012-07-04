package com.interdevinc.brokerage;

public class RepOverride
{
    private String associatedRepNumber;
    private String overrideOnRepNumber;
    private double splitPercent;
    private String percentageAsString;
    
    /**
     * CONSTURCTOR: REP OVERRIDE
     */
    public RepOverride()
    {
    	
    }
    
    /**
     * CONSTURCTOR: REP OVERRIDE
     * @param jrn, sp     
     */
    public RepOverride(String arn, String orn, double sp)
    {
		setAssociatedRepNumber(arn);
		setOverrideOnRepNumber(orn);
		setSplitPercent(sp);
		setPercentageAsString(sp);
    }
    
    /**
     * @return the associatedRepNumber
     */
    public String getAssociatedRepNumber()
    {
    	return associatedRepNumber;
    }

    /**
     * @return the overrideOnRepNumber
     */
    public String getOverrideOnRepNumber()
    {
        return overrideOnRepNumber;
    }

    /**
     * @return the splitPercent
     */
    public double getSplitPercent()
    {
        return splitPercent;
    }
    
    /**
     * @return the percentageAsString
     */
    public String getPercentageAsString()
    {
        return percentageAsString;
    }
    
    /**
     * @param arn the associatedRepNumber to set
     */
    public void setAssociatedRepNumber(String arn)
    {
    	associatedRepNumber = arn;
    }

    /**
     * @param orn the overrideOnRepNumber to set
     */
    public void setOverrideOnRepNumber(String orn)
    {
    	overrideOnRepNumber = orn;
    }

    /**
     * @param sp the splitPercent to set
     */
    public void setSplitPercent(double sp)
    {
        splitPercent = sp;
    }
    
    /**
     * @param sp the percentageAsString to set
     */
    public void setPercentageAsString(double sp)
    { 
        percentageAsString = new StringBuilder(Double.toString(sp * 100)).append("%").toString();
    }

    /**
     * toString
     */
	public String toString()
	{
		StringBuilder overrideRepToString = new StringBuilder();
		overrideRepToString = overrideRepToString.append("  [Broker Override: "+associatedRepNumber+"\n");
		overrideRepToString = overrideRepToString.append("  ->Override On: "+overrideOnRepNumber+"\n");
		overrideRepToString = overrideRepToString.append("  ->Split: "+Double.toString(splitPercent)+"\n");
		overrideRepToString = overrideRepToString.append("  ->Percentage: "+percentageAsString+"\n");
		return overrideRepToString.toString();
	}
    
}
