package Entities;

import java.io.Serializable;

public class Employer implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	private String w4cBussines;
	private String CompanyName;
	private String CompanyStatus;

	public Employer(String w4cBussines, String companyName, String companyStatus) {
		super();
		this.w4cBussines = w4cBussines;
		CompanyName = companyName;
		CompanyStatus = companyStatus;
	}

	public String getW4cBussines() {
		return w4cBussines;
	}

	public void setW4cBussines(String w4cBussines) {
		this.w4cBussines = w4cBussines;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public String getCompanyStatus() {
		return CompanyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
		CompanyStatus = companyStatus;
	}
}
