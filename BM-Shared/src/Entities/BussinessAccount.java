package Entities;

import java.io.Serializable;

public class BussinessAccount extends Client implements Serializable {
	
	private static final long serialVersionUID = 3754841791740654616L;
	private String CompanyName,budget;
	
	public BussinessAccount(String role, String id, String firstN, String lastN, homeBranches homeBranch,
			String userName, String password, String isLoggedIn, String w4c_private, String status, String companyName,
			String budget) {
		super(role, id, firstN, lastN, homeBranch, userName, password, isLoggedIn, w4c_private, status, budget);
		CompanyName = companyName;
		this.budget = budget;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}
}
