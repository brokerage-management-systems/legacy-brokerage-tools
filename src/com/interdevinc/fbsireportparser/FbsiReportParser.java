/**
 * FbsiReportParser
 * Created on May 20, 2011 21:07 PM
 * @author Matthew Weppler
 * copyright 2011 InterDev Inc.
 */
 
// insert into FbsiMonthEndReport

package com.interdevinc.fbsireportparser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

import info.mattweppler.databaseutils.DatabaseUtils;
import info.mattweppler.fileutils.FileUtils;
import info.mattweppler.guiprompt.GuiPrompt;

public class FbsiReportParser
{
    private static void insertIntoFbnr074pReportTable(String partialQuery)
    {
    	String insertQuery = "INSERT INTO report_fbnr074p (branch, brokerNumber, amountOfTrades, commission, concession, concessionAway, clearing, execution, payout, commissionMonth, createdBy, createdDate) VALUES " + partialQuery;
    	//System.out.println(insertQuery);
    	DatabaseUtils dbc = new DatabaseUtils();
    	try {
    		Statement stmt = dbc.getConnection().createStatement();
    		//System.out.println("Result: " + stmt.executeUpdate(insertQuery));
    		stmt.executeUpdate(insertQuery);
    		
    		stmt.close();
    		dbc.closeConnection();
    	} catch (SQLException sqle) {
    		sqle.printStackTrace();
    	}
	}
	
    private static String[] parseAllFilesInDirectory(String directory)
    {
        try {
            return FileUtils.retrieveStringArrayOfFileNamesInDirectoryWithExtension(directory, ".txt", true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String returnSqlFormattedDate(String dateString)
    {
    	//System.out.println(dateString.substring(6, dateString.length()) + dateString.substring(0, 2) + dateString.substring(3, 5));
    	return dateString.substring(6, dateString.length()) + dateString.substring(0, 2) + dateString.substring(3, 5);
    }
    
    private static String readInFbnr074pFileReturnInsertQuery(String filePath)
    {
        boolean exitLoop = false;
        Pattern pattern = Pattern.compile(".*BROKER SUMMARY REPORT.*[0-1][0-9]/[0-3][0-9]/[0-9]{4}.*");
        String line;
        String date = null;
        StringBuilder strBuilder = new StringBuilder();
        String tempString = new String("");
        //SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String timestamp = DATE_TIME_FORMAT.format(cal.getTime());
        try {
            BufferedReader fileIns = new BufferedReader(new FileReader(filePath));
            line = fileIns.readLine();
            while ((line = fileIns.readLine()) != null) {
            	if (Pattern.matches(pattern.pattern(), line)) {
            		date = returnSqlFormattedDate(line.substring(line.indexOf("BROKER SUMMARY REPORT") + 21, line.length()).trim());
            	}
                if (line.contains("BRANCH  REP  REP NAME               TRADES   COMMISSIONS   CONCESSIONS  CONCESSIONS AWAY      CLEARING     EXECUTION       PAYOUT")) {
                    while ((line = fileIns.readLine()) != null) {
                        if (line.contains("SUPER EAS TOTALS")) {
                            exitLoop = true;
                            break;
                        } else {
                        	strBuilder = strBuilder.append("(");
                        	// Branch
                        	tempString = line.substring(0,5).trim();
                        	tempString = (tempString.equals("")) ? "" : tempString;
                            strBuilder = strBuilder.append("'"+tempString+"',");
                            // BrokerNumber
                            tempString = line.substring(5,12).trim();
                            tempString = (tempString.equals("")) ? "000" : tempString;
                            strBuilder = strBuilder.append("'"+tempString+"',");
                            // BrokerName not used...
                            //tempString = line.substring(12,34).trim();
                            //tempString = (tempString.equals("")) ? "" : tempString;
                            //strBuilder = strBuilder.append("'"+tempString+"',");
                            // AmountOfTrades
                            tempString = line.substring(34,43).trim();
                            tempString = (tempString.equals("")) ? "0" : tempString;
                            strBuilder = strBuilder.append("'"+tempString+"',");
                            // Commission
                            tempString = line.substring(43,57).replaceAll(",", "").trim();
                            tempString = (tempString.equals("")) ? "0" : tempString;
                            strBuilder = strBuilder.append("'"+tempString+"',");
                            // Concession
                            tempString = line.substring(57,71).replaceAll(",", "").trim();
                            tempString = (tempString.equals("")) ? "0" : tempString;
                            strBuilder = strBuilder.append("'"+tempString+"',");
                            // ConcessionAway
                            tempString = line.substring(71,89).replaceAll(",", "").trim();
                            tempString = (tempString.equals("")) ? "0" : tempString;
                            strBuilder = strBuilder.append("'"+tempString+"',");
                            // Clearing
                            tempString = line.substring(89,103).replaceAll(",", "").trim();
                            tempString = (tempString.equals("")) ? "0" : tempString;
                            strBuilder = strBuilder.append("'"+tempString+"',");
                            // Execution
                            tempString = line.substring(103,117).replaceAll(",", "").trim();
                            tempString = (tempString.equals("")) ? "0" : tempString;
                            strBuilder = strBuilder.append("'"+tempString+"',");
                            // Payout
                            tempString = line.substring(117,130).replaceAll(",", "").trim();
                            tempString = (tempString.equals("")) ? "0" : tempString;
                            strBuilder = strBuilder.append("'"+tempString+"',");
                            // CommissionMonth
                            strBuilder = strBuilder.append("'"+date+"'").append(",");
                            // CreatedBy
                            strBuilder = strBuilder.append("'0'").append(",");
                            // CreatedDate
                            strBuilder = strBuilder.append("'"+timestamp+"'").append("),");
                        }
                    }
                }
                if (exitLoop) break;
            }
            fileIns.close();
        } catch (FileNotFoundException fnfe) {
        } catch (IOException ioe) {
        } catch (NumberFormatException nfe) { }
        //System.out.println(strBuilder.toString().substring(0, strBuilder.length() - 1));
        return strBuilder.toString().substring(0, strBuilder.length() - 1);
    }
    
    public static void main(String[] args)
    {
        // To import all files in a directory uncomment below:
//        String[] strings = parseAllFilesInDirectory(path);
//        for (String string : strings) {
//            String newPath = path.concat(string);
//            //System.out.println("Parsing file: "+newPath);
//            insertIntoFbnr074pReportTable(readInFbnr074pFileReturnInsertQuery(newPath));
//    		  verifySqlEntry();
//        }

        // To import a single file uncomment below:
    	//String filename = args[0];
    	String filename = GuiPrompt.openFileDialog().getAbsolutePath();
    	//System.out.println("Parsing file: "+filename);
    	insertIntoFbnr074pReportTable(readInFbnr074pFileReturnInsertQuery(filename));
    	verifySqlEntry();
    }
    
    private static void verifySqlEntry()
    {
    	DatabaseUtils dbc = new DatabaseUtils();
    	try {
    		String selectQuery = "SELECT `report_fbnr074p`.`commissionMonth` FROM report_fbnr074p ORDER BY `report_fbnr074p`.`commissionMonth` DESC LIMIT 1";
    		Statement stmt = dbc.getConnection().createStatement();
    		//System.out.println("Result: " + stmt.executeUpdate(selectQuery));
    		stmt.execute(selectQuery);
    		ResultSet results = stmt.getResultSet();
		    String reportMonth = null;
    		if(results != null){
				while (results.next()) {
					reportMonth = results.getString(1);
				}
		    }
			//close all processing objects
		    results.close();
		    stmt.close();
		    dbc.closeConnection();
    		
    		HashMap<?,?> fbnr074pReport = Fbnr074pReport.retrieveSummedReportByCommissionMonthAsHashMap(reportMonth);
            Fbnr074pReport.printFbnr074Report(fbnr074pReport, reportMonth);
    	} catch (NullPointerException npe) {
    		npe.printStackTrace();
    	} catch (SQLException sqle) {
    		sqle.printStackTrace();
    	}
    }
    
}
