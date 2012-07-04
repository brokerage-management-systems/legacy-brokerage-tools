package com.interdevinc.brokerage;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class TradeTotals
{
    private String repNumber;
    private String repFullName;
    private String totalTrades;
    private String cancels;
    private String optionTrades;
    private String optionContracts;
    private String principal;
    private String commission;
    private String concession;
    private String totalGross;
    private NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

    public TradeTotals()
    {
    	
    }

    public String getRepNumber()
    {
        return repNumber;
    }

    public String getRepFullName()
    {
        return repFullName;
    }

    public String getTotalTrades()
    {
        return totalTrades;
    }

    public String getCancels()
    {
        return cancels;
    }

    public String getOptionTrades()
    {
        return optionTrades;
    }

    public String getOptionContracts()
    {
    	return optionContracts;
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

    public String getTotalGross()
    {
        return totalGross;
    }
    
    public String getTotalGrossAsCurrency()
	{
       	return nf.format(getTotalGrossAsDouble());
	}
    
    public Double getTotalGrossAsDouble()
    {
        if (totalGross.length() >= 2) {
            StringBuilder builder = new StringBuilder(totalGross);
            return Double.parseDouble(builder.insert(totalGross.length() - 2, ".").toString());
        } else {
            return Double.parseDouble(totalGross);
        }
    }

    public void setRepNumber(String rn)
    {
        repNumber = rn;
    }

    public void setRepFullName(String fn)
    {
    	repFullName = fn;
    }

    public void setTotalTrades(String tt)
    {
        totalTrades = tt;
    }

    public void setCancels(String x)
    {
        cancels = x;
    }

    public void setOptionTrades(String ot)
    {
        optionTrades = ot;
    }

    public void setOptionContracts(String oc)
    {
    	optionContracts = oc;
    }

    public void setPrincipal(String pr)
    {
        principal = pr;
    }

    public void setCommission(String cm)
    {
        commission = cm;
    }

    public void setConcession(String cn)
    {
        concession = cn;
    }
    
    public void setTotalGross(String tg)
    {
    	totalGross = tg;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TradeTotals [");
        if (repNumber != null) {
            builder.append("repNumber=");
            builder.append(repNumber);
            builder.append(", ");
        }
        if (repFullName != null) {
            builder.append("repFullName=");
            builder.append(repFullName);
            builder.append(", ");
        }
        if (totalTrades != null) {
            builder.append("totalTrades=");
            builder.append(totalTrades);
            builder.append(", ");
        }
        if (cancels != null) {
            builder.append("cancels=");
            builder.append(cancels);
            builder.append(", ");
        }
        if (optionTrades != null) {
            builder.append("optionTrades=");
            builder.append(optionTrades);
            builder.append(", ");
        }
        if (optionContracts != null) {
            builder.append("optionContracts=");
            builder.append(optionContracts);
            builder.append(", ");
        }
        if (principal != null) {
            builder.append("principal=");
            builder.append(principal);
            builder.append(", ");
        }
        if (commission != null) {
            builder.append("commission=");
            builder.append(commission);
            builder.append(", ");
        }
        if (concession != null) {
            builder.append("concession=");
            builder.append(concession);
            builder.append(", ");
        }
        if (totalGross != null) {
            builder.append("totalGross=");
            builder.append(totalGross);
        }
        builder.append("]");
        return builder.toString();
    }
    
    /*
     * toStringCurrency
     */
    public String toStringCurrency()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("TradeTotals [");
        if (repNumber != null) {
            builder.append("repNumber=");
            builder.append(repNumber);
            builder.append(", ");
        }
        if (repFullName != null) {
            builder.append("repFullName=");
            builder.append(repFullName);
            builder.append(", ");
        }
        if (totalTrades != null) {
            builder.append("totalTrades=");
            builder.append(totalTrades);
            builder.append(", ");
        }
        if (cancels != null) {
            builder.append("cancels=");
            builder.append(cancels);
            builder.append(", ");
        }
        if (optionTrades != null) {
            builder.append("optionTrades=");
            builder.append(optionTrades);
            builder.append(", ");
        }
        if (optionContracts != null) {
            builder.append("optionContracts=");
            builder.append(optionContracts);
            builder.append(", ");
        }
        if (principal != null) {
            builder.append("principal=");
            builder.append(getPrincipalAsCurrency());
            builder.append(", ");
        }
        if (commission != null) {
            builder.append("commission=");
            builder.append(getCommissionAsCurrency());
            builder.append(", ");
        }
        if (concession != null) {
            builder.append("concession=");
            builder.append(getConcessionAsCurrency());
            builder.append(", ");
        }
        if (totalGross != null) {
            builder.append("totalGross=");
            builder.append(getTotalGrossAsCurrency());
        }
        builder.append("]");
        return builder.toString();
    }
    
    /*
     * toStringDouble
     */
    public String toStringDouble()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("TradeTotals [");
        if (repNumber != null) {
            builder.append("repNumber=");
            builder.append(repNumber);
            builder.append(", ");
        }
        if (repFullName != null) {
            builder.append("repFullName=");
            builder.append(repFullName);
            builder.append(", ");
        }
        if (totalTrades != null) {
            builder.append("totalTrades=");
            builder.append(totalTrades);
            builder.append(", ");
        }
        if (cancels != null) {
            builder.append("cancels=");
            builder.append(cancels);
            builder.append(", ");
        }
        if (optionTrades != null) {
            builder.append("optionTrades=");
            builder.append(optionTrades);
            builder.append(", ");
        }
        if (optionContracts != null) {
            builder.append("optionContracts=");
            builder.append(optionContracts);
            builder.append(", ");
        }
        if (principal != null) {
            builder.append("principal=");
            builder.append(getPrincipalAsDouble());
            builder.append(", ");
        }
        if (commission != null) {
            builder.append("commission=");
            builder.append(getCommissionAsDouble());
            builder.append(", ");
        }
        if (concession != null) {
            builder.append("concession=");
            builder.append(getConcessionAsDouble());
            builder.append(", ");
        }
        if (totalGross != null) {
            builder.append("totalGross=");
            builder.append(getTotalGrossAsDouble());
        }
        builder.append("]");
        return builder.toString();
    }

    public static void printTradeTotals(ArrayList<TradeTotals> totalsList)
    {
        for (TradeTotals tradeTotal : totalsList) {
            System.out.println(tradeTotal.toStringDouble());
        }
    }
    
    public static HashMap<String, TradeTotals> retrieveHashMapFromArrayList(ArrayList<TradeTotals> totalsList)
    {
        HashMap<String, TradeTotals> totalsMap = new HashMap<String, TradeTotals>();
        for (TradeTotals totals : totalsList) {
            totalsMap.put(totals.getRepNumber(), totals);
        }
        return totalsMap;
    }
    
}
