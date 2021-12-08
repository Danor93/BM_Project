package Entities;

public class Restaurant {
	private String supplierName,openning,city,address;
	private homeBranches homeBranch;
	
	public Restaurant(String supplierName, String openning, String city, String address,homeBranches homeBranch) {
		
		this.supplierName = supplierName;
		this.openning = openning;
		this.city = city;
		this.address = address;
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
	
	
	

}
