package Entities;

import java.io.Serializable;

public class BusinessAccountTracking implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ID;
	private String companyName;
	private String budget;
	private String status;

	public BusinessAccountTracking(String iD, String companyName, String budget) {
		this.ID = iD;
		this.companyName = companyName;
		this.budget = budget;
	}

	public String getBudget() {
		return budget;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getID() {
		return ID;
	}

	public String getCompanyName() {
		return companyName;
	}
}