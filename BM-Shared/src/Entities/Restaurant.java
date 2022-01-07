package Entities;

import java.io.Serializable;

public class Restaurant implements Serializable {
	
	private static final long serialVersionUID = 6163898968170345743L;
	private String supplierName, openning, city, address,restCode;
	private homeBranches homeBranch;


	public Restaurant(String restCode, String supplierName, String openning, String city, String address,
			homeBranches homeBranch) {
		this.restCode = restCode;
		this.supplierName = supplierName;
		this.openning = openning;
		this.city = city;
		this.address=address;
		this.homeBranch = homeBranch;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public String getOpenning() {
		return openning;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

	public homeBranches getHomeBranch() {
		return homeBranch;
	}

	public String getRestCode() {
		return restCode;
	}
}
