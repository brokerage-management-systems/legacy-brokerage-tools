package com.interdevinc.brokerage;

public class PayrollReport
{
    private String repNumber;
    private String repFullName;
    private String payrollMonth;
    private String grossCommission;
    private String netCommission;
    private String totalCheck;
    
    /**
     * CONSTRUCTOR: PAYROLL REPORT
     */
    public PayrollReport()
    {
    	
    }
    
    public PayrollReport(String rn, String fn, String pm, String gc, String nc, String tc)
    {
		setRepNumber(rn);
		setRepFullName(fn);
		setPayrollMonth(pm);
		setGrossCommission(gc);
		setNetCommission(nc);
		setTotalCheck(tc);
    }

    /**
     * @return the repNumber
     */
    public String getRepNumber() {
        return repNumber;
    }

    /**
     * @return the repName
     */
    public String getRepFullName()
    {
        return repFullName;
    }

    /**
     * @return the payrollMonth
     */
    public String getPayrollMonth()
    {
        return payrollMonth;
    }

    /**
     * @return the grossCommission
     */
    public String getGrossCommission()
    {
        return grossCommission;
    }

    /**
     * @return the netCommission
     */
    public String getNetCommission()
    {
        return netCommission;
    }

    /**
     * @return the totalCheck
     */
    public String getTotalCheck()
    {
        return totalCheck;
    }

    /**
     * @param repNumber the repNumber to set
     */
    public void setRepNumber(String rn)
    {
        repNumber = rn;
    }

    /**
     * @param repName the repName to set
     */
    public void setRepFullName(String fn)
    {
    	repFullName = fn;
    }

    /**
     * @param payrollMonth the payrollMonth to set
     */
    public void setPayrollMonth(String pm)
    {
        payrollMonth = pm;
    }

    /**
     * @param grossCommission the grossCommission to set
     */
    public void setGrossCommission(String gc)
    {
        grossCommission = gc;
    }

    /**
     * @param netCommission the netCommission to set
     */
    public void setNetCommission(String nc)
    {
        netCommission = nc;
    }

    /**
     * @param totalCheck the totalCheck to set
     */
    public void setTotalCheck(String tc)
    {
        totalCheck = tc;
    }
    
}
