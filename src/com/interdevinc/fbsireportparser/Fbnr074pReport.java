package com.interdevinc.fbsireportparser;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import info.mattweppler.databaseutils.DatabaseUtils;
import info.mattweppler.loggingutils.Logger;

public class Fbnr074pReport {

	private int id;
    private int amountOfTrades;
    private String branch;
	private String brokerNumber;
	private double clearing;
	private double commission;
	private Date commissionMonth;
	private double concession;
	private double concessionAway;
	private double execution;
	private double payout;
	private int createdBy;
    private Date createdDate;
	private NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
	
	public Fbnr074pReport()
	{
		
	}
		
	public int getId() {
        return id;
    }

    public int getAmountOfTrades() {
        return amountOfTrades;
    }

    public String getBranch() {
        return branch;
    }

    public String getBrokerNumber() {
        return brokerNumber;
    }

    public double getClearing() {
        return clearing;
    }

    public double getCommission() {
        return commission;
    }

    public Date getCommissionMonth() {
        return commissionMonth;
    }

    public double getConcession() {
        return concession;
    }

    public double getConcessionAway() {
        return concessionAway;
    }

    public double getExecution() {
        return execution;
    }

    public double getPayout() {
        return payout;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmountOfTrades(int amountOfTrades) {
        this.amountOfTrades = amountOfTrades;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setBrokerNumber(String brokerNumber) {
        this.brokerNumber = brokerNumber;
    }

    public void setClearing(double clearing) {
        this.clearing = clearing;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public void setCommissionMonth(Date commissionMonth) {
        this.commissionMonth = commissionMonth;
    }

    public void setConcession(double concession) {
        this.concession = concession;
    }

    public void setConcessionAway(double concessionAway) {
        this.concessionAway = concessionAway;
    }

    public void setExecution(double execution) {
        this.execution = execution;
    }

    public void setPayout(double payout) {
        this.payout = payout;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Fbnr074pReport [id=");
        builder.append(id);
        builder.append(", amountOfTrades=");
        builder.append(amountOfTrades);
        builder.append(", ");
        if (branch != null) {
            builder.append("branch=");
            builder.append(branch);
            builder.append(", ");
        }
        if (brokerNumber != null) {
            builder.append("brokerNumber=");
            builder.append(brokerNumber);
            builder.append(", ");
        }
        builder.append("clearing=");
        builder.append(clearing);
        builder.append(", commission=");
        builder.append(commission);
        builder.append(", ");
        if (commissionMonth != null) {
            builder.append("commissionMonth=");
            builder.append(commissionMonth);
            builder.append(", ");
        }
        builder.append("concession=");
        builder.append(concession);
        builder.append(", concessionAway=");
        builder.append(concessionAway);
        builder.append(", execution=");
        builder.append(execution);
        builder.append(", payout=");
        builder.append(payout);
        builder.append(", createdBy=");
        builder.append(createdBy);
        builder.append(", ");
        if (createdDate != null) {
            builder.append("createdDate=");
            builder.append(createdDate);
        }
        builder.append("]");
        return builder.toString();
    }

    public static void printFbnr074Report(HashMap<?,?> fbnr074pReport, String monthString)
    {
    	StringBuilder outputString = new StringBuilder();
    	//System.out.println("Report for: "+monthString);
    	outputString = outputString.append("*** Report for: "+monthString+" ***\n");
        // Keys & Values
        Iterator it = fbnr074pReport.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            //System.out.println(pairs.getKey()+" : "+pairs.getValue());
            outputString = outputString.append(pairs.getKey()+" : "+pairs.getValue()+"\n");
        }
        outputString = outputString.append("*** End of Data ***\n");
        Logger.createLoggerWithMessage(outputString.toString());
        
//        // Just Keys
//        for (Object key : fbnr074pReport.keySet()) {
//            System.out.println("Key:"+key);
//        }
//        // Just Values
//        for (Object value : fbnr074pReport.values()) {
//            System.out.println("Value:"+value);
//        }
//        // Keys & Values
//        for (Entry<?, ?> entry : fbnr074pReport.entrySet()) {
//            Object key = entry.getKey();
//            Object value = entry.getValue();
//            System.out.println(key+" : "+value);
//        }
    }
    
    /**
	 * METHOD: RETRIEVE REPORT BY COMMISSION MONTH AS HASHMAP
	 * 
	 * @param commissionMonth
	 * @return reportMap
	 */
	public static HashMap<String, Fbnr074pReport> retrieveReportByCommissionMonthAsHashMap(String commissionMonth)
	{
		HashMap<String, Fbnr074pReport> reportMap = new HashMap<String, Fbnr074pReport>();
		DatabaseUtils dbc = new DatabaseUtils();
    	try {
        	StringBuilder selectQuery = new StringBuilder();
        	selectQuery = selectQuery.append("SELECT id, amountOfTrades, branch, brokerNumber, clearing, commission, commissionMonth, concession, concessionAway, execution, payout, createdBy, createdDate FROM report_fbnr074p");
			selectQuery = selectQuery.append(" WHERE commissionMonth='"+commissionMonth+"'");
			selectQuery = selectQuery.append(" ORDER BY brokerNumber ASC");
			//System.out.println(selectQuery.toString());
			//init connection and statement
		    Statement statement = dbc.getConnection().createStatement();
		    //execute statement and retrieve resultSet
		    statement.execute(selectQuery.toString());
		    ResultSet results = statement.getResultSet();
		    if(results != null){
				while (results.next()) {
					Fbnr074pReport tempReport = new Fbnr074pReport();
					tempReport.setId(results.getInt(1));
					tempReport.setAmountOfTrades(results.getInt(2));
					tempReport.setBranch(results.getString(3));
					tempReport.setBrokerNumber(results.getString(4));
					tempReport.setClearing(results.getDouble(5));
					tempReport.setCommission(results.getDouble(6));
					tempReport.setCommissionMonth(results.getDate(7));
					tempReport.setConcession(results.getDouble(8));
					tempReport.setConcessionAway(results.getDouble(9));
					tempReport.setExecution(results.getDouble(10));
					tempReport.setPayout(results.getDouble(11));
					tempReport.setCreatedBy(results.getInt(12));
					tempReport.setCreatedDate(results.getDate(13));
					//tempReport.toString();
					reportMap.put(tempReport.getBrokerNumber(), tempReport);
				}
		    }
			//close all processing objects
		    results.close();
		    statement.close();
		    dbc.closeConnection();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return reportMap;
	}
	
    /**
     * METHOD: RETRIEVE SUMMED REPORT BY COMMISSION MONTH AS HASHMAP
     * 
     * @param commissionMonth
     * @return reportMap
     */
    public static HashMap<String, Fbnr074pReport> retrieveSummedReportByCommissionMonthAsHashMap(String commissionMonth)
    {
        HashMap<String, Fbnr074pReport> reportMap = new HashMap<String, Fbnr074pReport>();
        DatabaseUtils dbc = new DatabaseUtils();
        try {
            StringBuilder selectQuery = new StringBuilder();
            selectQuery = selectQuery.append("SELECT SUM(amountOfTrades), brokerNumber, SUM(clearing), SUM(commission), commissionMonth, SUM(concession), SUM(concessionAway), SUM(execution), SUM(payout) FROM report_fbnr074p");
            selectQuery = selectQuery.append(" WHERE commissionMonth='"+commissionMonth+"'");
            selectQuery = selectQuery.append(" GROUP BY brokerNumber");
            selectQuery = selectQuery.append(" ORDER BY brokerNumber ASC");
            //System.out.println(selectQuery.toString());
            //init connection and statement
            Statement statement = dbc.getConnection().createStatement();
            //execute statement and retrieve resultSet
            statement.execute(selectQuery.toString());
            ResultSet results = statement.getResultSet();
            if(results != null){
                while (results.next()) {
                    Fbnr074pReport tempReport = new Fbnr074pReport();
                    tempReport.setAmountOfTrades(results.getInt(1));
                    tempReport.setBrokerNumber(results.getString(2));
                    tempReport.setClearing(results.getDouble(3));
                    tempReport.setCommission(results.getDouble(4));
                    tempReport.setCommissionMonth(results.getDate(5));
                    tempReport.setConcession(results.getDouble(6));
                    tempReport.setConcessionAway(results.getDouble(7));
                    tempReport.setExecution(results.getDouble(8));
                    tempReport.setPayout(results.getDouble(9));
                    //tempReport.toString();
                    reportMap.put(tempReport.getBrokerNumber(), tempReport);
                }
            }
            //close all processing objects
            results.close();
            statement.close();
            dbc.closeConnection();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return reportMap;
    }
    
}
