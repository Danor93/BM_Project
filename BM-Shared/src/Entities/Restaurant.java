package Entities;

import java.io.Serializable;

public class Restaurant implements Serializable {
	private static final long serialVersionUID = 1L;
	private String supplierName, openning, city, address;
	private homeBranches homeBranch;
	private int restCode;

	public Restaurant(int restCode, String supplierName, String openning, String city, String address,
			homeBranches homeBranch) {
		this.restCode = restCode;
		this.supplierName = supplierName;
		this.openning = openning;
		this.city = city;
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

	public int getRestCode() {
		return restCode;
	}

}
