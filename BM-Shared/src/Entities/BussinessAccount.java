package Entities;

import java.io.Serializable;

public class BussinessAccount extends Client implements Serializable {

	private BusinessAccountTracking businessAccountTracking;
	private static final long serialVersionUID = 3754841791740654616L;
	private String CompanyName, budget, employerCode;
	private homeBranches branch;

	public BussinessAccount(String role, String id, String firstN, String lastN, homeBranches homeBranch,
			String userName, String password, String isLoggedIn, String w4c_private, String status, String companyName,
			String budget) {
		super(role, id, firstN, lastN, homeBranch, userName, password, isLoggedIn, w4c_private, status, budget);
		CompanyName = companyName;
		this.budget = budget;
		businessAccountTracking = new BusinessAccountTracking(id, companyName, budget);
	}

	public BussinessAccount(String CompanyName,String w4c_private,String budget)
	{
		super(w4c_private);
		this.CompanyName=CompanyName;
		this.budget=budget;
	}

	public BusinessAccountTracking getBusinessAccountTracking() {
		return this.businessAccountTracking;
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

	public String getEmployerCode() {
		return employerCode;
	}

	public void setEmployerCode(String employerCode) {
		this.employerCode = employerCode;
	}

	public homeBranches getBranch() {
		return branch;
	}

	public void setBranch(homeBranches branch) {
		this.branch = branch;
	}

}
