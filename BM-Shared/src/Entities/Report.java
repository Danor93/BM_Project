package Entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Report implements Serializable {

	private static final long serialVersionUID = 583542510006825068L;
	protected ArrayList<Restaurant> Restaurant;
	private String month,year;
	
	public Report(ArrayList<Restaurant> restaurant, String month, String year) {
		super();
		Restaurant = restaurant;
		this.month = month;
		this.year = year;
	}

	public ArrayList<Restaurant> getRestaurant() {
		return Restaurant;
	}

	public void setRestaurant(ArrayList<Restaurant> restaurant) {
		Restaurant = restaurant;
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
