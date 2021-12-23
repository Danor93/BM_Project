package Entities;

import java.io.Serializable;

public class Report implements Serializable {

	private static final long serialVersionUID = 583542510006825068L;
	private Restaurant restaurant;
	private String month,year;
	
	public Report(Restaurant restaurant, String month, String year) {
		super();
		this.restaurant = restaurant;
		this.month = month;
		this.year = year;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
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

}
