package Entities;

import java.io.Serializable;

public class Supplier implements Serializable{

	private static final long serialVersionUID = -2735114008397983290L;
	private int restId;
	private String supplierName; 
	private String openingTime;
	private String city;
	private String address;
	private String supplierStatus;
	private homeBranches homeBranch;
	private String Confirm_Employee;


	public Supplier(int restId, String supplierName, String openingTime, String city, String address,
			String supplierStatus, homeBranches homeBranch, String confirm_Employee) {
		super();
		this.restId = restId;
		this.supplierName = supplierName;
		this.openingTime = openingTime;
		this.city = city;
		this.address = address;
		this.supplierStatus = supplierStatus;
		this.homeBranch = homeBranch;
		Confirm_Employee = confirm_Employee;
	}


	
	public int getRestId() {
		return restId;
	}


	public void setRestId(int restId) {
		this.restId = restId;
	}


	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}


	public String getOpeningTime() {
		return openingTime;
	}


	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getSupplierStatus() {
		return supplierStatus;
	}


	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}


	public homeBranches getHomeBranch() {
		return homeBranch;
	}


	public void setHomeBranch(homeBranches homeBranch) {
		this.homeBranch = homeBranch;
	}
	
	public String getConfirm_Employee() {
		return Confirm_Employee;
	}


	public void setConfirm_Employee(String confirm_Employee) {
		Confirm_Employee = confirm_Employee;
	}
}
