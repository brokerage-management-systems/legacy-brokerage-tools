package com.interdevinc.brokerage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import info.mattweppler.databaseutils.DatabaseUtils;

public class CommissionDate
{
    private String commissionMonthStart;
    private String commissionMonthEnd;
    private String previousMonth;
    private String nextMonth;
    private String monthLabel;

    public CommissionDate()
    {
    	
    }

    public String getCommissionMonthStart()
    {
        return commissionMonthStart;
    }

    public String getCommissionMonthEnd()
    {
        return commissionMonthEnd;
    }

    public String getPreviousMonth()
    {
        return previousMonth;
    }

    public String getNextMonth()
    {
        return nextMonth;
    }

    public String getMonthLabel()
    {
        return monthLabel;
    }

    public void setCommissionMonthStart(String cms)
    {
        commissionMonthStart = cms;
    }

    public void setCommissionMonthEnd(String cme)
    {
        commissionMonthEnd = cme;
    }

    public void setPreviousMonth(String pm)
    {
        previousMonth = pm;
    }

    public void setNextMonth(String nm)
    {
        nextMonth = nm;
    }

    public void setMonthLabel(String ml)
    {
        monthLabel = ml;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CommissionDate [");
        if (monthLabel != null) {
            builder.append("MonthLabel=");
            builder.append(monthLabel);
            builder.append(", ");
        }
        if (commissionMonthStart != null) {
            builder.append("MonthStart=");
            builder.append(commissionMonthStart);
            builder.append(", ");
        }
        if (commissionMonthEnd != null) {
            builder.append("MonthEnd=");
            builder.append(commissionMonthEnd);
            builder.append(", ");
        }
        if (previousMonth != null) {
            builder.append("Previous=");
            builder.append(previousMonth);
            builder.append(", ");
        }
        if (nextMonth != null) {
            builder.append("NextMonth=");
            builder.append(nextMonth);
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * 
     * 
     * @return
     */
    public static ArrayList<CommissionDate> retrieveAllCommissionDates()
    {
        ArrayList<CommissionDate> commissionDateList = new ArrayList<CommissionDate>();
        DatabaseUtils dbc = new DatabaseUtils();
        try {
            StringBuilder selectQuery = new StringBuilder();
            selectQuery = selectQuery.append("SELECT commissionMonthStart , commissionMonthEnd, previousMonth, nextMonth, monthLabel FROM commissionMonths");
            selectQuery = selectQuery.append(" ORDER BY commissionMonthEnd ASC");
            //System.out.println(selectQuery.toString());
            //init connection and statement
            Statement statement = dbc.getConnection().createStatement();
            //execute statement and retrieve resultSet
            statement.execute(selectQuery.toString());
            ResultSet results = statement.getResultSet();
            if(results != null){
                while (results.next()) {
                    CommissionDate commissionDate = new CommissionDate();
                    commissionDate.setCommissionMonthStart(results.getString(1));
                    commissionDate.setCommissionMonthEnd(results.getString(2));
                    commissionDate.setPreviousMonth(results.getString(3));
                    commissionDate.setNextMonth(results.getString(4));
                    commissionDate.setMonthLabel(results.getString(5));
                    commissionDateList.add(commissionDate);
                }
            }
            //close all processing objects
            results.close();
            statement.close();
            dbc.closeConnection();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return commissionDateList;
    }
    
    public static CommissionDate retrieveCommissionDateByCommissionMonthEnd(String commissionMonthEnd)
    {
        CommissionDate commissionDate = new CommissionDate();
        DatabaseUtils dbc = new DatabaseUtils();
        try {
            StringBuilder selectQuery = new StringBuilder();
            selectQuery = selectQuery.append("SELECT commissionMonthStart , commissionMonthEnd, previousMonth, nextMonth, monthLabel FROM commissionMonths");
            selectQuery = selectQuery.append(" WHERE commissionMonthEnd='"+commissionMonthEnd+"'");
            //System.out.println(selectQuery.toString());
            //init connection and statement
            Statement statement = dbc.getConnection().createStatement();
            //execute statement and retrieve resultSet
            statement.execute(selectQuery.toString());
            ResultSet results = statement.getResultSet();
            if(results != null){
                results.next();
                commissionDate.setCommissionMonthStart(results.getString(1));
                commissionDate.setCommissionMonthEnd(results.getString(2));
                commissionDate.setPreviousMonth(results.getString(3));
                commissionDate.setNextMonth(results.getString(4));
                commissionDate.setMonthLabel(results.getString(5));
            }
            //close all processing objects
            results.close();
            statement.close();
            dbc.closeConnection();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return commissionDate;
    }

    /**
     * Pass in String in form of: "January 2010"
     * 
     * @param monthYear
     * @return commissionDate
     */
    public static CommissionDate retrieveCommissionDateByMonthYear(String monthYear)
    {
        CommissionDate commissionDate = new CommissionDate();
        DatabaseUtils dbc = new DatabaseUtils();
        try {
            StringBuilder selectQuery = new StringBuilder();
            selectQuery = selectQuery.append("SELECT commissionMonthStart , commissionMonthEnd, previousMonth, nextMonth, monthLabel FROM commissionMonths");
            selectQuery = selectQuery.append(" WHERE monthLabel='"+monthYear+"'");
            //System.out.println(selectQuery.toString());
            //init connection and statement
            Statement statement = dbc.getConnection().createStatement();
            //execute statement and retrieve resultSet
            statement.execute(selectQuery.toString());
            ResultSet results = statement.getResultSet();
            if(results != null){
                results.next();
                commissionDate.setCommissionMonthStart(results.getString(1));
                commissionDate.setCommissionMonthEnd(results.getString(2));
                commissionDate.setPreviousMonth(results.getString(3));
                commissionDate.setNextMonth(results.getString(4));
                commissionDate.setMonthLabel(results.getString(5));
            }
            //close all processing objects
            results.close();
            statement.close();
            dbc.closeConnection();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return commissionDate;
    }

}