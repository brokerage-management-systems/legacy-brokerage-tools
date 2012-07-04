/**
 *  
 */
package com.interdevinc.brokerage;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class Trade {

	private String repNumber;
	private String branch;
	private String accountNumber;
	private String tradeReferenceNumber;
	private String tradeDate;
	private String settleDate;
	private String accountType;
	private String marketCode;
	private String blotterCode;
	private String buySellCode;
	private String cancelCode;
	private String quantity;
	private String cusip;
	private String symbol;
	private String securityDescription1;
	private String securityDescription2;
	private String price;
	private String principal;
	private String commission;
	private String concession;
	private String solicitationCode;
	private String securityType;
	private String tradeReferenceTradeID;
	private NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
		
	public Trade()
	{
		
	}

	public String getAccountNumber()
	{
		return accountNumber;
	}
	
	public String getAccountType()
	{
		return accountType;
	}
	
	public String getBlotterCode()
	{
		return blotterCode;
	}
	
	public String getBranch()
	{
		return branch;
	}
	
	public String getBuySellCode()
	{
		return buySellCode;
	}
	
	public String getCancelCode()
	{
		return cancelCode;
	}
	
	public String getCommission()
	{
		return commission;
	}

	public String getCommissionAsCurrency()
	{
    	return nf.format(getCommissionAsDouble());
	}
	
	public Double getCommissionAsDouble()
	{
	    if (commission.length() >= 2) {
	        StringBuilder builder = new StringBuilder(commission);
	        return Double.parseDouble(builder.insert(commission.length() - 2, ".").toString());
	    } else {
	        return Double.parseDouble(commission);
	    }
	}
	
	public String getConcession()
	{
		return concession;
	}
	
	public String getConcessionAsCurrency()
	{
       	return nf.format(getConcessionAsDouble());
	}
	
	public Double getConcessionAsDouble()
	{
	    if (concession.length() >= 2) {
	        StringBuilder builder = new StringBuilder(concession);
	        return Double.parseDouble(builder.insert(concession.length() - 2, ".").toString());
	    } else {
	        return Double.parseDouble(concession);
	    }
	}
		
	public String getCusip()
	{
		return cusip;
	}
	
	public String getMarketCode()
	{
		return marketCode;
	}
	
	public String getPrice()
	{
		return price;
	}
	
	public String getPriceAsCurrency()
	{
       	return nf.format(getPriceAsDouble());
	}
	
	public Double getPriceAsDouble()
	{
	    if (price.length() >= 2) {
	        StringBuilder builder = new StringBuilder(price);
	        return Double.parseDouble(builder.insert(price.length() - 2, ".").toString());
	    } else {
	        return Double.parseDouble(price);
	    }
	}
		
	public String getPrincipal()
	{
		return principal;
	}
	
    public String getPrincipalAsCurrency()
    {
        return nf.format(getPrincipalAsDouble());
    }
    
    public Double getPrincipalAsDouble()
    {
        if (principal.length() >= 2) {
            StringBuilder builder = new StringBuilder(principal);
            return Double.parseDouble(builder.insert(principal.length() - 2, ".").toString());
        } else {
            return Double.parseDouble(principal);
        }
    }
    	
	public Long getPrincipalAsLong()
	{
		return Long.parseLong(principal);
	}
	
	public String getQuantity()
	{
		return quantity;
	}
	
	public String getRepNumber()
	{
		return repNumber;
	}
	
	public String getSecurityDescription1()
	{
		return securityDescription1;
	}
	
	public String getSecurityDescription2()
	{
		return securityDescription2;
	}
	
	public String getSecurityType()
	{
		return securityType;
	}
	
	public String getSettleDate()
	{
		return settleDate;
	}
	
	public String getSolicitationCode()
	{
		return solicitationCode;
	}
	
	public String getSymbol()
	{
		return symbol;
	}
	
	public String getTradeDate()
	{
		return tradeDate;
	}
	
	public String getTradeReferenceNumber()
	{
		return tradeReferenceNumber;
	}
	
	public String getTradeReferenceTradeID()
	{
		return tradeReferenceTradeID;
	}

	public void setAccountNumber(String an)
	{
		accountNumber = an;
	}
	
	public void setAccountType(String at)
	{
		accountType = at;
	}
	
	public void setBlotterCode(String bc)
	{
		blotterCode = bc;
	}
	
	public void setBranch(String br)
	{
		branch = br;
	}
	
	public void setBuySellCode(String bsc)
	{
		buySellCode = bsc;
	}
	
	public void setCancelCode(String cc)
	{
		cancelCode = cc;
	}

	public void setCommission(String cm)
	{
		if (this.getCancelCode().equals("1")) {
			commission = "-".concat(parseCommissionField(cm));
		} else {
			commission = parseCommissionField(cm);
		}
	}
	
	/**
	 * Sets commission when bunching a trade.
	 */
	public void setCommissionBunched(String cm)
	{
		commission = cm;
	}
	
	public void setConcession(String cn)
	{
		if (this.getCancelCode().equals("1")) {
			concession = "-".concat(parseConcessionField(cn));
		} else {
			concession = parseConcessionField(cn);
		}
	}
	
	/**
	 * Sets concession when bunching a trade.
	 */
	public void setConcessionBunched(String cn)
	{
		concession = cn;
	}
	
	public void setCusip(String cip)
	{
		cusip = cip;
	}
	
	public void setMarketCode(String mc)
	{
		marketCode = mc;
	}
	
	public void setPrice(String pr)
	{
		price = parsePriceField(pr);
	}
	
	/**
	 * Sets price when bunching a trade.
	 */
	public void setPriceBunched(String pr)
	{
		price = pr;
	}
	
	public void setPrincipal(String pn)
	{
		if (getCancelCode().equals("1")) {
			principal = "-".concat(parsePrincipalField(pn));
		} else {
			principal = parsePrincipalField(pn); 
		}
	}
	
	/**
	 * Sets principal when bunching a trade.
	 */
	public void setPrincipalBunched(String pn)
	{
		principal = pn;
	}
	
	public void setQuantity(String qty)
	{
		quantity = parseQuantityField(qty);
	}
	
	/**
	 * Sets quantity when bunching a trade.
	 */
	public void setQuantityBunched(String qty)
	{
		quantity = qty;
	}
	
	public void setRepNumber(String rn)
	{
		repNumber = rn;
	}
	
	public void setSecurityDescription1(String sd1)
	{
		securityDescription1 = sd1;
	}
	
	public void setSecurityDescription2(String sd2)
	{
		securityDescription2 = sd2;
	}
	
	public void setSecurityType(String st)
	{
		securityType = st;
	}
	
	public void setSettleDate(String sd)
	{
		settleDate = sd;
	}
	
	public void setSolicitationCode(String sc)
	{
		solicitationCode = sc;
	}
	
	public void setSymbol(String sym)
	{
		symbol = sym;
	}
	
	public void setTradeDate(String td)
	{
		tradeDate = td;
	}
	
	public void setTradeReferenceNumber(String trn)
	{
		tradeReferenceNumber = trn;
	}
	
	public void setTradeReferenceTradeID(String trti)
	{
		tradeReferenceTradeID = trti;
	}

	/**
	 * METHOD: BUNCH TRADES BY TRADE DEFINITION TRADE ID
	 * Takes an array of trades
	 * Tests if 2 trades have equal TradeDefTradeIDs and if true bunches them and adds to new array.
	 * Otherwise just adds the trade to the new array
	 * After all trades have been tested, returns new array of Trades.
	 */
	public static ArrayList<Trade> bunchTradesByTradeDefinitionTradeID(ArrayList<Trade> tradeList)
	{
		ArrayList<Trade> bunchedTradeList = new ArrayList<Trade>();
		for (int tradeIndex = 0; tradeIndex < tradeList.size(); tradeIndex++) {
			Trade tempTrade = new Trade();
			if (tradeIndex == tradeList.size() - 1) {
				//Last Trade so just add Trade to new array of Trades
				tempTrade = tradeList.get(tradeIndex);
			} else {
				tempTrade = tradeList.get(tradeIndex);
				Trade firstTrade = tempTrade;
				Trade secondTrade = tradeList.get(tradeIndex + 1);
				while (firstTrade.getTradeReferenceTradeID().equals(secondTrade.getTradeReferenceTradeID())) {
					tradeIndex++;
					tempTrade = bunchTwoTrades(firstTrade, secondTrade);
					if (tradeIndex == tradeList.size() - 1) {
						//Last Trade, break out of loop
						break;
					} else {
						firstTrade = tempTrade;
						secondTrade = tradeList.get(tradeIndex + 1);
					}
				}
			}
			bunchedTradeList.add(tempTrade); // new trade
		}
		return bunchedTradeList;
	}
	
	/**
	 * METHOD: BUNCH TWO TRADES
	 * Takes 2 Trades
	 * Sums the Commission, Concession, & Quantity, Averages the Price
	 * Returns single Trade
	 */
	public static Trade bunchTwoTrades(Trade firstTrade, Trade secondTrade)
	{
		Trade bunchedTrade = new Trade();
		bunchedTrade.setRepNumber(secondTrade.getRepNumber());
		bunchedTrade.setBranch(secondTrade.getBranch());
		bunchedTrade.setAccountNumber(secondTrade.getAccountNumber());
		bunchedTrade.setTradeReferenceNumber(secondTrade.getTradeReferenceNumber());
		bunchedTrade.setTradeDate(secondTrade.getTradeDate());
		bunchedTrade.setSettleDate(secondTrade.getSettleDate());
		bunchedTrade.setAccountType(secondTrade.getAccountType());
		bunchedTrade.setMarketCode(secondTrade.getMarketCode());
		bunchedTrade.setBlotterCode(secondTrade.getBlotterCode());
		bunchedTrade.setBuySellCode(secondTrade.getBuySellCode());
		bunchedTrade.setCancelCode(secondTrade.getCancelCode());
		bunchedTrade.setQuantityBunched(Integer.toString(Integer.parseInt(firstTrade.getQuantity()) + Integer.parseInt(secondTrade.getQuantity()))); // Sum
		bunchedTrade.setCusip(secondTrade.getCusip());
		bunchedTrade.setSymbol(secondTrade.getSymbol());
		bunchedTrade.setSecurityDescription1(secondTrade.getSecurityDescription1());
		bunchedTrade.setSecurityDescription2(secondTrade.getSecurityDescription2());
		bunchedTrade.setPriceBunched(Integer.toString((Integer.parseInt(firstTrade.getPrice()) + Integer.parseInt(secondTrade.getPrice())) / 2)); // Average
		bunchedTrade.setPrincipalBunched(Integer.toString(Integer.parseInt(firstTrade.getPrincipal()) + Integer.parseInt(secondTrade.getPrincipal()))); // Sum
		bunchedTrade.setCommissionBunched(Integer.toString(Integer.parseInt(firstTrade.getCommission()) + Integer.parseInt(secondTrade.getCommission()))); // Sum
		bunchedTrade.setConcessionBunched(Integer.toString(Integer.parseInt(firstTrade.getConcession()) + Integer.parseInt(secondTrade.getConcession()))); // Sum
		bunchedTrade.setSolicitationCode(secondTrade.getSolicitationCode());
		bunchedTrade.setSecurityType(secondTrade.getSecurityType());
		bunchedTrade.setTradeReferenceTradeID(secondTrade.getTradeReferenceTradeID());
		return bunchedTrade;
	}

	/**
	 * METHOD: COMPARE TRADE DEFINITION TRADE ID
	 * Returns true if TradeReferenceTradeID are equal, otherwise false.
	 */
	public static boolean compareTradeDefinitionTradeID(ArrayList<Trade> tradeList, int firstTrade, int secondTrade) {
		if (tradeList.get(firstTrade).getTradeReferenceTradeID() == tradeList.get(secondTrade).getTradeReferenceTradeID()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * METHOD: TO STRING
	 */
	public String toString()
	{
		StringBuilder tradeToString = new StringBuilder();
		tradeToString = tradeToString.append("Rep#: "+repNumber+"\n");
		tradeToString = tradeToString.append("Branch & Account#: "+branch+"-"+accountNumber+"\n");
		tradeToString = tradeToString.append("Trade Reference#: "+tradeReferenceNumber+"\n");
		tradeToString = tradeToString.append("Trade Date: "+tradeDate+"\n");
		tradeToString = tradeToString.append("Settle Date: "+settleDate+"\n");
		tradeToString = tradeToString.append("Account Type: "+accountType+"\n");
		tradeToString = tradeToString.append("Market Code: "+marketCode+"\n");
		tradeToString = tradeToString.append("Blotter Code: "+blotterCode+"\n");
		tradeToString = tradeToString.append("Buy/Sell Code: "+buySellCode+"\n");
		tradeToString = tradeToString.append("Cancel Code: "+cancelCode+"\n");
		tradeToString = tradeToString.append("Quantity: "+quantity+"\n");
		tradeToString = tradeToString.append("Cusip: "+cusip+"\n");
		tradeToString = tradeToString.append("Symbol: "+symbol+"\n");
		tradeToString = tradeToString.append("Security Description: "+securityDescription1+" "+securityDescription2+"\n");
		tradeToString = tradeToString.append("Price: "+price+"\n");
		tradeToString = tradeToString.append("Principal: "+principal+"\n");
		tradeToString = tradeToString.append("Commission: "+commission+"\n");
		tradeToString = tradeToString.append("Concession: "+concession+"\n");
		tradeToString = tradeToString.append("Solicition Code: "+solicitationCode+"\n");
		tradeToString = tradeToString.append("Security Type: "+securityType+"\n");
		tradeToString = tradeToString.append("Trade Reference Trade ID: "+tradeReferenceTradeID+"\n");
		return tradeToString.toString();
	}

	/**
	 * Commission is returned from the database as a 10 character String.
	 * It has leading 0's and does not have a decimal point.
	 * 
	 * Turn the string into an Integer to remove leading 0's &
	 * the decimal point needs to be inserted 2 places from the end of the String.
	 */
	private String parseCommissionField(String cm) {
		StringBuilder sbcm = new StringBuilder(Integer.toString(Integer.parseInt(cm)));
		//System.out.println("String: " + sbcm + " | Length: " + sbcm.length());
//		if (sbcm.length() > 2) {
//			return sbcm.insert(sbcm.length() - 2, ".").toString();
//		} else {
//			return sbcm.toString();
//		}
		return sbcm.toString();
	}
	
	/**
	 * Concession is returned from the database as a 10 character String.
	 * It has leading 0's and does not have a decimal point.
	 * 
	 * Turn the string into an Integer to remove leading 0's &
	 * the decimal point needs to be inserted 2 places from the end of the String.
	 */
	private String parseConcessionField(String cn) {
		StringBuilder sbcn = new StringBuilder(Integer.toString(Integer.parseInt(cn)));
		//System.out.println("String: " + sbcn + " | Length: " + sbcn.length());
//		if (sbcn.length() > 2) {
//			return sbcn.insert(sbcn.length() - 2, ".").toString();
//		} else {
//			return sbcn.toString();
//		}
		return sbcn.toString();
	}
	
	/**
	 * Price is returned from the database as a 18 character String.
	 * It has leading 0's and does not have a decimal point.
	 * 
	 * Turn the string into an Integer to remove leading 0's &
	 * the decimal point needs to be inserted 9 places from the end
	 * lastly strip off 5 more trailing 0's.
	 */
	private String parsePriceField(String pr) {
		StringBuilder sbpr = new StringBuilder(Integer.toString(Integer.parseInt(pr.substring(0, pr.length() - 5))));
//		if (sbpr.length() > 4) {
//			return sbpr.insert(sbpr.length() - 4, ".").toString();
//		} else {
//			return sbpr.toString();
//		}
		return sbpr.toString();
	}
	
	/**
	 * Principal is returned from the database as a 10 character String.
	 * It has leading 0's and does not have a decimal point.
	 * 
	 * Turn the string into an Integer to remove leading 0's &
	 * the decimal point needs to be inserted 2 places from the end of the String.
	 */
	private String parsePrincipalField(String pn) {
		StringBuilder sbpn = new StringBuilder(Long.toString(Long.parseLong(pn)));
		//System.out.println("String: " + sbpn + " | Length: " + sbpn.length());
//		if (sbpn.length() > 2) {
//			return sbpn.insert(sbpn.length() - 2, ".").toString();
//		} else {
//			return sbpn.toString();
//		}
		return sbpn.toString();
	}
	
	/**
	 * Quantity is returned from the database as a 16 character String.
	 * It has leading 0's and does not have a decimal point.
	 * 
	 * Turn the string into an Integer to remove leading 0's &
	 * strip 5 trailing 0's from the end of the String.
	 */
	private String parseQuantityField(String qty) {
		return Integer.toString(Integer.parseInt(qty.substring(0, qty.length() - 5)));
	}

}