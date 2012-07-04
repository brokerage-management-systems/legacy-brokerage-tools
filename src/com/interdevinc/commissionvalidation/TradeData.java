package com.interdevinc.commissionvalidation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.interdevinc.brokerage.Account;
import com.interdevinc.brokerage.Broker;
import com.interdevinc.brokerage.CommissionDate;
import com.interdevinc.brokerage.Trade;
import com.interdevinc.brokerage.TradeTotals;
import com.interdevinc.fbsireportparser.Fbnr074pReport;

import info.mattweppler.databaseutils.DatabaseUtils;

public class TradeData
{
	/**
	 * METHOD: CREATE LIST OF ACCOUNTS
	 * Creates, sets & returns accountList.
	 * @param dateType
	 * @param searchDateStart
	 * @param searchDateEnd
	 * @return accountList
	 */
	public static ArrayList<Account> createListOfAccounts(String dateType, String searchDateStart, String searchDateEnd)
	{
		ArrayList<Account> accountList = new ArrayList<Account>();
		DatabaseUtils dbc = new DatabaseUtils();
    	try {
        	StringBuilder selectQuery = new StringBuilder();
        	selectQuery = selectQuery.append("SELECT DISTINCT BRANCH_01_a, ACCOUNT_NUMBER_01, REGISTERED_REP_OWNING_REP_RR_09 FROM TRDREV_TD");
			if (dateType.equals("settlement")) {
				selectQuery = selectQuery.append(" WHERE SETTLEMENT_DATE_01 BETWEEN '"+searchDateStart+"' AND '"+searchDateEnd+"'");
			} else if (dateType.equals("trade")) {
				selectQuery = selectQuery.append(" WHERE TRADE_DATE_01 BETWEEN '"+searchDateStart+"' AND '"+searchDateEnd+"'");
			}
			selectQuery = selectQuery.append(" ORDER BY REGISTERED_REP_OWNING_REP_RR_09, BRANCH_01_a, ACCOUNT_NUMBER_01 ASC");
			//System.out.println(selectQuery.toString());
			//init connection and statement
		    Statement statement = dbc.getConnection().createStatement();
		    //execute statement and retrieve resultSet
		    statement.execute(selectQuery.toString());
		    ResultSet results = statement.getResultSet();
		    if(results != null){
				while (results.next()) {
					Account tempAccount = new Account();
					tempAccount.setBranch(results.getString(1));
					tempAccount.setAccountNumber(results.getString(2));
					tempAccount.setRepNumber(results.getString(3));
					accountList.add(tempAccount);
				}
		    }
			//close all processing objects
		    results.close();
		    statement.close();
		    dbc.closeConnection();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return accountList;
	}
	
	/**
	 * METHOD: CREATE LIST OF BROKERS
	 * Creates, sets & returns brokerList.
	 * @param dateType
	 * @param searchDateStart
	 * @param searchDateEnd
	 * @return brokerList
	 */
	public static ArrayList<Broker> createListOfBrokers(String dateType, String searchDateStart, String searchDateEnd)
	{
		ArrayList<Broker> brokerList = new ArrayList<Broker>();
		DatabaseUtils dbc = new DatabaseUtils();
    	try {
        	StringBuilder selectQuery = new StringBuilder();
        	selectQuery = selectQuery.append("SELECT DISTINCT REGISTERED_REP_OWNING_REP_RR_09 FROM TRDREV_TD");
			if (dateType.equals("settlement")) {
				selectQuery = selectQuery.append(" WHERE SETTLEMENT_DATE_01 BETWEEN '"+searchDateStart+"' AND '"+searchDateEnd+"'");
			} else if (dateType.equals("trade")) {
				selectQuery = selectQuery.append(" WHERE TRADE_DATE_01 BETWEEN '"+searchDateStart+"' AND '"+searchDateEnd+"'");
			} else {
				// Default is Trade Date
				selectQuery = selectQuery.append(" WHERE TRADE_DATE_01 BETWEEN '"+searchDateStart+"' AND '"+searchDateEnd+"'");
			}
			selectQuery = selectQuery.append(" ORDER BY REGISTERED_REP_OWNING_REP_RR_09 ASC");
			//System.out.println(selectQuery.toString());
			//init connection and statement
		    Statement statement = dbc.getConnection().createStatement();
		    //execute statement and retrieve resultSet
		    statement.execute(selectQuery.toString());
		    ResultSet results = statement.getResultSet();
		    if(results != null){
				while (results.next()) {
					Broker tempBroker = new Broker();
					tempBroker.setRepNumber(results.getString(1));
					brokerList.add(tempBroker);
				}
		    }
			//close all processing objects
		    results.close();
		    statement.close();
		    dbc.closeConnection();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return brokerList;
	}
	
	/**
	 * METHOD: CREATE LIST OF TRADES BY CRITERIA
	 * @param dateSearchType
	 * @param searchDateStart
	 * @param searchDateEnd
	 * @param broker
	 * @param account
	 */
	public static ArrayList<Trade> createListOfTradesByCriteria(
			String dateSearchType,
			String searchDateStart,
			String searchDateEnd,
			Broker broker,
			Account account)
	{
		ArrayList<Trade> tradeList = new ArrayList<Trade>();
		DatabaseUtils dbc = new DatabaseUtils();
    	try {
        	StringBuilder selectQuery = new StringBuilder();
        	selectQuery = selectQuery.append("SELECT " +
				"REGISTERED_REP_OWNING_REP_RR_09, " +
				"BRANCH_01_a, ACCOUNT_NUMBER_01, " +
				"TRADE_REFERENCE_NUMBER_01, " +
				"DATE_FORMAT(TRADE_DATE_01, '%m/%d/%Y'), " +
				"DATE_FORMAT(SETTLEMENT_DATE_01, '%m/%d/%Y'), " +
				"ACCOUNT_TYPE_01, " +
				"MARKET_CODE_01, " +
				"BLOTTER_CODE_01, " +
				"BUY_SELL_CODE_01, " +
				"CANCEL_CODE_01, " +
				"QUANTITY_03, " +
				"CUSIP_01, " +
				"SYMBOL_02, " +
				"SECURITY_DESCRIPTION_LINE_05_a, " +
				"SECURITY_DESCRIPTION_LINE_05_b, " +
				"PRICE_03, " +
				"PRINCIPAL_04, " +
				"TRADE_COMMISSION_04, " +
				"TRADE_CONCESSION_05, " +
				"SOLICITED_CODE_10, " +
				"SECURITY_TYPE_02, " +
				"TRADE_DEFINITION_TRADE_ID_12 " +
				"FROM TRDREV_TD");
        	String dateModifier = null;
			if (dateSearchType.equals("settlement")) {
				dateModifier = "SETTLEMENT_DATE_01";
			} else if (dateSearchType.equals("trade")) {
				dateModifier = "TRADE_DATE_01";
			}
			selectQuery = selectQuery.append(" WHERE "+dateModifier+" BETWEEN '"+searchDateStart+"' AND '"+searchDateEnd+"'");
			if (broker != null) {
				selectQuery = selectQuery.append(" AND REGISTERED_REP_OWNING_REP_RR_09='"+broker.getRepNumber()+"'");
			}
			if (account != null) {
				selectQuery = selectQuery.append(" AND BRANCH_01_a='"+account.getBranch()+"' AND ACCOUNT_NUMBER_01='"+account.getAccountNumber()+"'");
			}
			selectQuery = selectQuery.append(" ORDER BY REGISTERED_REP_OWNING_REP_RR_09, "+dateModifier+", TRADE_DEFINITION_TRADE_ID_12 ASC"); // use RUN_DATE_01 instead of $dateModifier
	    	//System.out.println(selectQuery.toString());
			//init connection and statement
		    Statement statement = dbc.getConnection().createStatement();
		    //execute statement and retrieve resultSet
		    statement.execute(selectQuery.toString());
		    ResultSet results = statement.getResultSet();
		    if(results != null){
				while (results.next()) {
					Trade tempTrade = new Trade();
					tempTrade.setRepNumber(results.getString(1)); 
					tempTrade.setBranch(results.getString(2));
					tempTrade.setAccountNumber(results.getString(3));
					tempTrade.setTradeReferenceNumber(results.getString(4));
					tempTrade.setTradeDate(results.getString(5));
					tempTrade.setSettleDate(results.getString(6));
					tempTrade.setAccountType(results.getString(7));
					tempTrade.setMarketCode(results.getString(8));
					tempTrade.setBlotterCode(results.getString(9));
					tempTrade.setBuySellCode(results.getString(10));
					tempTrade.setCancelCode(results.getString(11));
					tempTrade.setQuantity(results.getString(12));
					tempTrade.setCusip(results.getString(13));
					tempTrade.setSymbol(results.getString(14));
					tempTrade.setSecurityDescription1(results.getString(15));
					tempTrade.setSecurityDescription2(results.getString(16));
					tempTrade.setPrice(results.getString(17));
					tempTrade.setPrincipal(results.getString(18));
					tempTrade.setCommission(results.getString(19));
					tempTrade.setConcession(results.getString(20));
					tempTrade.setSolicitationCode(results.getString(21));
					tempTrade.setSecurityType(results.getString(22));
					tempTrade.setTradeReferenceTradeID(results.getString(23));
					tradeList.add(tempTrade);
				}
		    }
    	    //close all processing objects
    	    results.close();
    	    statement.close();
    	    dbc.closeConnection();
    	} catch (SQLException sqle) {
    		sqle.printStackTrace();
    	}
    	return tradeList;
	}
	
	/**
	 * METHOD: CALCULATE TRADES BY GROUPING
	 * @param groupBy
	 * @param listOfTrades
	 * @param listOfBrokers
	 * @param listOfAccounts
	 * @return tradeTotals
	 */
	public static ArrayList<TradeTotals> calculateTradesByGrouping(
			String groupBy,
			ArrayList<Trade> listOfTrades,
			ArrayList<Broker> listOfBrokers,
			ArrayList<Account> listOfAccounts)
	{
		int groupingCount = 0;
		if (groupBy.equals("rep")) {
			groupingCount = listOfBrokers.size();
		} else if (groupBy.equals("acct")) {
			groupingCount = listOfAccounts.size();
		} else {
			groupingCount = 1;
		}
		boolean okToCalculate	= false;
		ArrayList<TradeTotals> tradeTotals = new ArrayList<TradeTotals>();
		for (int groupIndex = 0; groupIndex < groupingCount; groupIndex++) {
			// Clear variables
			int		cancelCode			= 0;
			int		quantity			= 0;
			int		securityType		= 0;
			int		cancelCount			= 0;
			int		optionTradeCount	= 0;
			int		optionQuantity		= 0;
			int		optionQuantityTotal	= 0;
			int		tradeCount			= 0;
			long	principalTotal		= 0;
			long	commissionTotal		= 0;
			long	concessionTotal		= 0;
			TradeTotals tempTradeTotal = new TradeTotals();
			for (int tradeIndex = 0; tradeIndex < listOfTrades.size(); tradeIndex++) {
				okToCalculate = false;
				if (groupBy.equals("rep") && listOfTrades.get(tradeIndex).getRepNumber().equals(listOfBrokers.get(groupIndex).getRepNumber())) {
					okToCalculate = true;
					tempTradeTotal.setRepNumber(listOfTrades.get(tradeIndex).getRepNumber());
				} else if (groupBy.equals("acct") && listOfTrades.get(tradeIndex).getBranch().equals(listOfAccounts.get(groupIndex).getBranch()) && listOfTrades.get(tradeIndex).getAccountNumber().equals(listOfAccounts.get(groupIndex).getAccountNumber())) {
					okToCalculate = true;
					tempTradeTotal.setRepNumber(listOfAccounts.get(groupIndex).getFullAccountNumber());
				} else if (groupBy.equals("all")) {
					okToCalculate = true;
				}
				if (okToCalculate) {
					
					cancelCode   = (listOfTrades.get(tradeIndex).getCancelCode().equals("1")) ? 
								Integer.parseInt(listOfTrades.get(tradeIndex).getCancelCode()) : 0;		// cancelCode
					quantity     = Integer.parseInt(listOfTrades.get(tradeIndex).getQuantity());		// quantity
					securityType = (listOfTrades.get(tradeIndex).getSecurityType().equals("F")) ? -1 : Integer.parseInt(listOfTrades.get(tradeIndex).getSecurityType());	// secType
					if (cancelCode == 1) { // Count Cancels
						cancelCount++;
					}
					if (securityType == 8) {			// If Result is an Option Trade...
						if (cancelCode != 1) {			// If Result is not a Canceled Trade...
							optionTradeCount++;
							optionQuantity		 = quantity;
							optionQuantityTotal	+= optionQuantity;
						} else if (cancelCode == 1) {	// If Result is a Canceled Trade...
							optionTradeCount--;
							optionQuantity		 = quantity;
							optionQuantityTotal	-= optionQuantity;
						}
					}
					if(securityType != -1 && cancelCode != 1) { // Count Trade
						tradeCount++;
					}
					// Total Principal, Commission, & Concession
					principalTotal	+= Long.parseLong(listOfTrades.get(tradeIndex).getPrincipal());
					commissionTotal += Long.parseLong(listOfTrades.get(tradeIndex).getCommission());
					concessionTotal += Long.parseLong(listOfTrades.get(tradeIndex).getConcession());
				}
			}
			tempTradeTotal.setTotalTrades(Integer.toString(tradeCount));
			tempTradeTotal.setCancels(Integer.toString(cancelCount));
			tempTradeTotal.setOptionTrades(Integer.toString(optionTradeCount));
			tempTradeTotal.setOptionContracts(Integer.toString(optionQuantityTotal));
			tempTradeTotal.setPrincipal(Long.toString(principalTotal));
			tempTradeTotal.setCommission(Long.toString(commissionTotal));
			tempTradeTotal.setConcession(Long.toString(concessionTotal));
			tempTradeTotal.setTotalGross(Long.toString(commissionTotal + concessionTotal));
			tradeTotals.add(tempTradeTotal);
		}
		return tradeTotals;
	}
	
	/**
	 * METHOD: CALCULATE FIRM TOTALS
	 * 1. Trade Count
	 * 2. Cancel Count
	 * 3. Option Trade Count
	 * 4. Option Quantity Total
	 * 5. Principal Total
	 * 6. Commission Total
	 * 7. Concession Total
	 * 8. Commission & Concession
	 * @param tradeTotalsList
	 * @return tradeTotalsList
	 */
	public static ArrayList<TradeTotals> calculateFirmTotals(ArrayList<TradeTotals> tradeTotalsList)
	{
		TradeTotals tempTradeTotal = new TradeTotals();
		int		tradeCount			= 0;
		int		cancelCount			= 0;
		int		optionTradeCount	= 0;
		int		optionQuantityTotal	= 0;
		long	principalTotal		= 0;
		long	commissionTotal		= 0;
		long	concessionTotal		= 0;
		long	totalGross			= 0;
		for (TradeTotals tradeTotal : tradeTotalsList) {
			tradeCount			+= Integer.parseInt(tradeTotal.getTotalTrades());
			cancelCount			+= Integer.parseInt(tradeTotal.getCancels());
			optionTradeCount	+= Integer.parseInt(tradeTotal.getOptionTrades());
			optionQuantityTotal	+= Integer.parseInt(tradeTotal.getOptionContracts());
			principalTotal		+= Long.parseLong(tradeTotal.getPrincipal());
			commissionTotal		+= Long.parseLong(tradeTotal.getCommission());
			concessionTotal		+= Long.parseLong(tradeTotal.getConcession());
			totalGross			+= Long.parseLong(tradeTotal.getTotalGross());
		}
		tempTradeTotal.setRepNumber("");
		tempTradeTotal.setRepFullName("Firm Totals");
		tempTradeTotal.setTotalGross(Integer.toString(tradeCount));
		tempTradeTotal.setCancels(Integer.toString(cancelCount));
		tempTradeTotal.setOptionTrades(Integer.toString(optionTradeCount));
		tempTradeTotal.setOptionContracts(Integer.toString(optionQuantityTotal));
		tempTradeTotal.setPrincipal(Long.toString(principalTotal));
		tempTradeTotal.setCommission(Long.toString(commissionTotal));
		tempTradeTotal.setConcession(Long.toString(concessionTotal));
		tempTradeTotal.setTotalGross(Long.toString(totalGross));
		tradeTotalsList.add(tempTradeTotal);
		return tradeTotalsList;
	}
	
	/**
	 * 
	 * @param brokerNumber
	 * @param commissionDate
	 * @return tradeList
	 * 
	 * ->repNumber
	 * ->cancelCode
	 * ->commission
	 * ->tradeDate
	 * ->settleDate
	 * ->branch
	 * ->accountNumber
	 * ->tradeReferenceNumber
	 * ->tradeReferenceTradeId
	 * **does not set runDate
	 */
	public static ArrayList<Trade> retrieveCancelsForBrokerAfterRunDate(String brokerNumber, CommissionDate commissionDate)
	{
	    ArrayList<Trade> tradeList = new ArrayList<Trade>();
	    DatabaseUtils dbc = new DatabaseUtils();
        try {
            StringBuilder selectQuery = new StringBuilder("");
            selectQuery = selectQuery.append("SELECT REGISTERED_REP_OWNING_REP_RR_09 AS brokerNumber, CANCEL_CODE_01 AS cancel, TRADE_COMMISSION_04 AS commission, TRADE_DATE_01 AS tradeDate, SETTLEMENT_DATE_01 AS settleDate, BRANCH_01_a AS branch, ACCOUNT_NUMBER_01 AS accountNumber, TRADE_REFERENCE_NUMBER_01 AS tradeReferenceNumber, TRADE_DEFINITION_TRADE_ID_12 AS tradeDefinitionTradeId, RUN_DATE_01 AS runDate FROM TRDREV_TD");
            selectQuery = selectQuery.append(" WHERE RUN_DATE_01 > '"+commissionDate.getCommissionMonthEnd()+"' AND SETTLEMENT_DATE_01 BETWEEN '"+commissionDate.getCommissionMonthStart()+"' AND '"+commissionDate.getCommissionMonthEnd()+"' AND CANCEL_CODE_01='1' AND REGISTERED_REP_OWNING_REP_RR_09='"+brokerNumber+"'");
            selectQuery = selectQuery.append(" ORDER BY RUN_DATE_01 ASC");
            //System.out.println(selectQuery.toString());
            //init connection and statement
            Statement statement = dbc.getConnection().createStatement();
            //execute statement and retrieve resultSet
            statement.execute(selectQuery.toString());
            ResultSet results = statement.getResultSet();
            if(results != null){
                while (results.next()) {
                    Trade tempTrade = new Trade();
                    tempTrade.setRepNumber(results.getString(1));
                    tempTrade.setCancelCode(results.getString(2));
                    tempTrade.setCommission(results.getString(3));
                    tempTrade.setTradeDate(results.getString(4));
                    tempTrade.setSettleDate(results.getString(5));
                    tempTrade.setBranch(results.getString(6));
                    tempTrade.setAccountNumber(results.getString(7));
                    tempTrade.setTradeReferenceNumber(results.getString(8));
                    tempTrade.setTradeReferenceTradeID(results.getString(9));
                    tradeList.add(tempTrade);
                }
            }
            //close all processing objects
            results.close();
            statement.close();
            dbc.closeConnection();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return tradeList;
	}
	
    public static String retrieveSummedCommissionForBrokerWithCancelsAfterRunDate(String brokerNumber, CommissionDate commissionDate)
    {
        String commission = null;
        DatabaseUtils dbc = new DatabaseUtils();
        try {
            StringBuilder selectQuery = new StringBuilder("");
            selectQuery = selectQuery.append("SELECT SUM(TRADE_COMMISSION_04) AS commission FROM TRDREV_TD");
            selectQuery = selectQuery.append(" WHERE RUN_DATE_01 > '"+commissionDate.getCommissionMonthEnd()+"' AND SETTLEMENT_DATE_01 BETWEEN '"+commissionDate.getCommissionMonthStart()+"' AND '"+commissionDate.getCommissionMonthEnd()+"' AND CANCEL_CODE_01='1' AND REGISTERED_REP_OWNING_REP_RR_09='"+brokerNumber+"'");
            //System.out.println(selectQuery.toString());
            //init connection and statement
            Statement statement = dbc.getConnection().createStatement();
            //execute statement and retrieve resultSet
            statement.execute(selectQuery.toString());
            ResultSet results = statement.getResultSet();
            if(results != null){
                results.next();
                //commission = (results.getString(1) != null) ? results.getString(1) : "0";
                commission = results.getString(1);
            }
            //close all processing objects
            results.close();
            statement.close();
            dbc.closeConnection();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return commission;
    }
    
}