package Entities;

public class Employer {

	private String w4cBussines;
	private String CompanyName;
	private Boolean CompanyStatus;

	public Employer(String w4cBussines, String companyName, Boolean companyStatus) {
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

	public Boolean getCompanyStatus() {
		return CompanyStatus;
	}

	public void setCompanyStatus(Boolean companyStatus) {
		CompanyStatus = companyStatus;
	}
}
