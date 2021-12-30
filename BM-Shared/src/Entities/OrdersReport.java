package Entities;

import java.io.Serializable;

public class OrdersReport implements Serializable {

	private static final long serialVersionUID = 1983595595251314658L;
	private String month, year;
	private String branch;
	private String ResName;
	private String DishType;
	private int Quantity;
	public OrdersReport(String month, String year, String branch, String resName, String dishType, int quantity) {
		super();
		this.month = month;
		this.year = year;
		this.branch = branch;
		ResName = resName;
		DishType = dishType;
		Quantity = quantity;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getResName() {
		return ResName;
	}
	public void setResName(String resName) {
		ResName = resName;
	}
	public String getDishType() {
		return DishType;
	}
	public void setDishType(String dishType) {
		DishType = dishType;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public String getBranch() {
		return branch;
	} 
	
	public String toString()
	{
		return "Restaurant name = " + ResName + " branch is = " + branch + " month is= " + month + " year is: " + year
				+ " Quantity is : " + Quantity + " DishType is " + DishType;
	}
	
	
	
	
	
	

}
