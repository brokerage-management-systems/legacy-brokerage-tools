package com.interdevinc.brokerage;

import java.util.ArrayList;

public class Broker {

    // Main Info
    private String userID;
    private String repNumber;
    private String firstName;
    private String lastName;
    private boolean active;
    
    // Formatted Display Names
    private String fullInfo;
    private String fullNameWithComma;
    private String fullNameWithSpace;
    
    // Other Info
    private ArrayList<JointRep> jointRepList; 
    private ArrayList<RepOverride> repOverrideList; 
    private ArrayList<PayStructure> payStructureList; 

    /**
     * CONSTRUCTOR: BROKER
     */
    public Broker()
    {
    	
    }
    
    /**
     * CONSTRUCTOR: BROKER
     * @param uid
     * @param rn
     * @param fn
     * @param ln
     * @param a
     */
    public Broker(String uid, String rn, String fn, String ln, boolean a)
    {
		setUserID(uid);
		setRepNumber(rn);
		setFirstName(fn);
		setLastName(ln);
		setActive(a);
		setFullInfo();
		setFullNameWithComma();
		setFullNameWithSpace();
    }

    /**
     * @return the userID
     */
    public String getUserID()
    {
        return userID;
    }

    /**
     * @return the repNumber
     */
    public String getRepNumber()
    {
        return repNumber;
    }

    /**
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @return the active
     */
    public boolean isActive()
    {
        return active;
    }
    
    /**
     * @return the jointRep
     */
    public ArrayList<JointRep> getJointRepList()
    {
    	return jointRepList;
    }
    
    /**
     * @return the repOverride
     */
    public ArrayList<RepOverride> getRepOverrideList()
    {
    	return repOverrideList;
    }

    /**
     * @return the payStructure
     */
    public ArrayList<PayStructure> getPayStructureList()
    {
        return payStructureList;
    }
    
    /**
     * @return full info
     */
    public String getFullInfo()
    {
    	return fullInfo;
    }
    
    /**
     * @return full name lastName, firstName
     */
    public String getFullNameWithComma()
    {
    	return fullNameWithComma;
    }
    
    /**
     * @return full name firstName lastName
     */
    public String getFullNameWithSpace()
    {
    	return fullNameWithSpace;
    }
    
    /**
     * @param uid the userID to set
     */
    public void setUserID(String uid)
    {
        userID = uid;
    }

    /**
     * @param rn the repNumber to set
     */
    public void setRepNumber(String rn)
    {
        repNumber = rn;
    }

    /**
     * @param fn the firstName to set
     */
    public void setFirstName(String fn)
    {
        firstName = fn;
    }

    /**
     * @param ln the lastName to set
     */
    public void setLastName(String ln)
    {
        lastName = ln;
    }

    /**
     * @param a the active to set
     */
    public void setActive(boolean a)
    {
        active = a;
    }
    
    /**
     * @param jr the jointRep to set
     */
    public void setJointRepList(ArrayList<JointRep> jrl)
    {
    	jointRepList = jrl;
    }
    
    /**
     * @param ro the repOverride to set
     */
    public void setRepOverrideList(ArrayList<RepOverride> rol)
    {
    	repOverrideList = rol;
    }
    
    /**
     * @param ps the payStructure to set
     */
    public void setPayStructureList(ArrayList<PayStructure> psl)
    {
        payStructureList = psl;
    }
    
    /**
     * fullInfo set to "repNumber - firstName lastName"
     */
    public void setFullInfo()
    {
    	fullInfo = repNumber + " - " + firstName + " " + lastName;
    }
    
    /**
     * fullNameWithComma set to "lastName, firstName"
     */
    public void setFullNameWithComma()
    {
    	fullNameWithComma = lastName + ", " + firstName;
    }
    
    /**
     * fullNameWithSpace set to "firstName lastName"
     */
    public void setFullNameWithSpace()
    {
    	fullNameWithSpace = firstName + " " + lastName;
    }

    /**
     * toString
     */
	public String toString()
	{
		StringBuilder brokerToString = new StringBuilder();
		brokerToString = brokerToString.append("ID: "+userID+"<br />");
		brokerToString = brokerToString.append("Rep#: "+repNumber+"<br />");
		brokerToString = brokerToString.append("FirstName: "+firstName+"<br />");
		brokerToString = brokerToString.append("LastName: "+lastName+"<br />");
		brokerToString = brokerToString.append("Active: "+Boolean.toString(active)+"<br />");
		
		for (JointRep jointRep : jointRepList) {
			brokerToString.append(jointRep.toString());
		}
		
		for (RepOverride repOverride : repOverrideList) {
			brokerToString.append(repOverride.toString());
		}
		
		for (PayStructure payStructure : payStructureList) {
			brokerToString.append(payStructure.toString());
		}
		
//		if (salesAssistant!=NULL) {
//			brokerToString .= salesAssistant[0]->toString();
//		} else {
//			brokerToString .= "No Sales Assistant.<br />";
//		}
//		
//		if (brokerAccounts!=NULL) {
//			for (i = 0; i < count(brokerAccounts); i++) {
//				brokerToString .= brokerAccounts[i]->toString();
//			}
//		} else {
//			brokerToString .= "No Accounts.<br />";
//		}
//		
//		if (brokerTrades!=NULL) {
//			for (i = 0; i < count(brokerTrades); i++) {
//				brokerToString .= brokerTrades[i]->toString();
//			}
//		} else {
//			brokerToString .= "No Trades.<br />";
//		}
		
		return brokerToString.toString();
	}
	
}
