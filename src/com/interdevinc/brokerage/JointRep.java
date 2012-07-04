package com.interdevinc.brokerage;

public class JointRep
{
    private String associatedRepNumber;
    private String jointRepNumber;
    private double splitPercent;
    private String percentageAsString;
    
    /**
     * CONSTURCTOR: JOINT REP
     */
    public JointRep()
    {
    	
    }
    
    /**
     * CONSTURCTOR: JOINT REP
     * @param jrn, sp
     */
    public JointRep(String arn, String jrn, double sp)
    {
		setAssociatedRepNumber(arn);
		setJointRepNumber(jrn);
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
     * @return the jointRepNumber
     */
    public String getJointRepNumber()
    {
        return jointRepNumber;
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
     * @param jrn the jointRepNumber to set
     */
    public void setJointRepNumber(String jrn)
    {
        jointRepNumber = jrn;
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
    
    public String toString() {
    	StringBuilder jointRepToString = new StringBuilder();
		jointRepToString = jointRepToString.append("  "+"[JointRep: "+jointRepNumber+"\n");
		jointRepToString = jointRepToString.append("  "+"->Label: "+percentageAsString+"\n");
		jointRepToString = jointRepToString.append("  "+"->Split: "+splitPercent+"]\n");
		return jointRepToString.toString();
	}
    
}
