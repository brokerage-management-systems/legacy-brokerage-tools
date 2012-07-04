/**
 *  
 */
package com.interdevinc.commissionvalidation;

import info.mattweppler.guiprompt.GuiPrompt;
import info.mattweppler.loggingutils.Logger;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.interdevinc.brokerage.Account;
import com.interdevinc.brokerage.Broker;
import com.interdevinc.brokerage.CommissionDate;
import com.interdevinc.brokerage.Trade;
import com.interdevinc.brokerage.TradeTotals;
import com.interdevinc.fbsireportparser.Fbnr074pReport;

public class CommissionValidation
{
    public static void buildCommissionValidationData(CommissionDate commissionDate)
    {
        // FBNR074P Report
        HashMap<?,?> fbnr074pReport = Fbnr074pReport.retrieveSummedReportByCommissionMonthAsHashMap(commissionDate.getCommissionMonthEnd());
        //Fbnr074pReport.printFbnr074Report(fbnr074pReport, commissionDate.getMonthLabel());
        
        // TRDREV_TD Data
        String dateSearchType = "settlement"; // settlement || trade
        String groupBy = "rep"; // rep|acct|firm
        Broker broker = null;
        Account account = null;

        ArrayList<Broker> brokerList = TradeData.createListOfBrokers(dateSearchType, commissionDate.getCommissionMonthStart(), commissionDate.getCommissionMonthEnd());
        ArrayList<Account> accountList = TradeData.createListOfAccounts(dateSearchType, commissionDate.getCommissionMonthStart(), commissionDate.getCommissionMonthEnd());

        ArrayList<Trade> listOfTrades = TradeData.createListOfTradesByCriteria(dateSearchType, commissionDate.getCommissionMonthStart(), commissionDate.getCommissionMonthEnd(), broker, account);
        ArrayList<Trade> listOfBunchedTrades = Trade.bunchTradesByTradeDefinitionTradeID(listOfTrades);
        ArrayList<TradeTotals> listOfTradeTotals = TradeData.calculateTradesByGrouping(groupBy, listOfBunchedTrades, brokerList, accountList);
        //TradeData.calculateFirmTotals(listOfTradeTotals);
        HashMap<?,?> tradeTotalsMap = TradeTotals.retrieveHashMapFromArrayList(listOfTradeTotals);
        
        runCommissionValidationReport(commissionDate, brokerList, fbnr074pReport, tradeTotalsMap);
    }

    public static void cancelAfterMonthEndException(Broker broker, CommissionDate commissionDate, Double difference, StringBuilder outputString)
    {
        CommissionDate dateForQuery = new CommissionDate();
        if (difference < 0) {
            // Cancel took place after last month...
            dateForQuery = CommissionDate.retrieveCommissionDateByCommissionMonthEnd(commissionDate.getPreviousMonth());
        } else {
            // Cancel took place after this month...
            dateForQuery = commissionDate;
        }
        
        Double commissionAsDouble;
        String summedCommission = TradeData.retrieveSummedCommissionForBrokerWithCancelsAfterRunDate(broker.getRepNumber(), dateForQuery);
        if (summedCommission == null) {
            outputString = outputString.append("\tDoes the Broker have any Rep Changes?").append("\n");
            return;
        } else if (summedCommission.length() >= 2) {
            StringBuilder builder = new StringBuilder(summedCommission);
            commissionAsDouble = Double.parseDouble(builder.insert(summedCommission.length() - 2, ".").toString());
        } else {
            commissionAsDouble = Double.parseDouble(summedCommission);
        }
        
        double newDifference = 0.0;
        if (difference < 0) {
            // Cancel took place after last month...
            newDifference = commissionAsDouble + difference;
        } else {
            // Cancel took place after this month...
            newDifference = commissionAsDouble - difference;
        }
        
        if (newDifference == 0) {
            outputString = outputString.append("\tThere was a cancel(s) after " + dateForQuery.getCommissionMonthEnd() + " in the amount of: " + roundTwoDecimals(commissionAsDouble) + ". Commission is now Validated.").append("\n");
        } else {
            outputString = outputString.append("\tThere was a cancel(s) after " + dateForQuery.getCommissionMonthEnd() + " in the amount of: " + commissionAsDouble + ". Commission is still Not Validated.").append("\n");
            outputString = outputString.append("\tThe difference between the cancel(s) (" + commissionAsDouble + ") and the remaining commission (" + roundTwoDecimals(difference) + ") is: " + roundTwoDecimals(newDifference)).append("\n");
            outputString = outputString.append("\tDoes the Broker have any Rep Changes?").append("\n");
        }
    }
    
    public static double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
    
    public static void runCommissionValidationReport(CommissionDate commissionDate, 
            ArrayList<Broker> brokerList, HashMap<?,?> fbnr074pReport, HashMap<?,?> tradeTotalsMap)
    {
        StringBuilder outputString = new StringBuilder("");
        outputString = outputString.append("*************************************************************************").append("\n");
        outputString = outputString.append("\tCommission Validation Report for: " + commissionDate.getMonthLabel()).append("\n");
        outputString = outputString.append("*************************************************************************").append("\n");
        for (Broker broker : brokerList) {
            if (!fbnr074pReport.containsKey(broker.getRepNumber()))
                outputString = outputString.append("Broker: " + broker.getRepNumber() + " not found in FBRN074P Report").append("\n");
            if (!tradeTotalsMap.containsKey(broker.getRepNumber()))
                outputString = outputString.append("Broker: " + broker.getRepNumber() + " not found in TradeTotals Report").append("\n");
            if (fbnr074pReport.containsKey(broker.getRepNumber()) && tradeTotalsMap.containsKey(broker.getRepNumber())) {
//                System.out.println(fbnr074pReport.get(b.getRepNumber()));
//                System.out.println(tradeTotalsMap.get(b.getRepNumber()));
                Fbnr074pReport fr = (Fbnr074pReport) fbnr074pReport.get(broker.getRepNumber());
                TradeTotals tt = (TradeTotals) tradeTotalsMap.get(broker.getRepNumber());
                if (roundTwoDecimals(fr.getCommission()) == roundTwoDecimals(tt.getCommissionAsDouble())) {
                    outputString = outputString.append("Broker: " + broker.getRepNumber() + "\tValidated \tCommission: " + roundTwoDecimals(tt.getCommissionAsDouble())).append("\n");
                } else {
                    outputString = outputString.append("Broker: " + broker.getRepNumber() + "\tNot Validated \tDifference from FBRN074P Report: " + roundTwoDecimals(fr.getCommission() - tt.getCommissionAsDouble())).append("\n");
                    cancelAfterMonthEndException(broker, commissionDate, roundTwoDecimals(fr.getCommission() - tt.getCommissionAsDouble()), outputString);
                }
            }
        }
        outputString = outputString.append("*** END OF DATA ***").append("\n");
        //System.out.println(outputString.toString());
        Logger.createLoggerWithMessage(outputString.toString());
    }

    public static void main(String[] args)
    {
        // Specific Month
    	String monthYear = GuiPrompt.genericStringPrompt("Please enter a month & year:","Example: July 2011",null);
    	if (monthYear != null) {
    		CommissionDate commissionDate = CommissionDate.retrieveCommissionDateByMonthYear(monthYear);
    		buildCommissionValidationData(commissionDate);
    	} else {
    		System.exit(0);
    	}
//      CommissionDate commissionDate = CommissionDate.retrieveCommissionDateByMonthYear("April 2011");
//      CommissionDate commissionDate = CommissionDate.retrieveCommissionDateByMonthYear(args[0]);
    	
    	
//        // All Months
//        ArrayList<CommissionDate> commissionDateList = CommissionDate.retrieveAllCommissionDates();
//        for (CommissionDate cd : commissionDateList) {
//            buildCommissionValidationData(cd);
//        }
    }
}
