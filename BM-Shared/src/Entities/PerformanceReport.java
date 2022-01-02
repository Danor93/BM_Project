package Entities;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * @author Sahar this object is to save performance rerport data to be used in
 *         the application.
 */

public class PerformanceReport implements Serializable {

	private static final long serialVersionUID = 640146676291178737L;

	private String month, year;
	private String branch;
	private String ResName;
	private int total_orders;
	private int onTime;
	private int areLate;
	private double lateRate;
	private double AvarageCookingTime;

	public PerformanceReport(String month, String year, String branch, String resName, int total_orders, int onTime,
			int areLate) {
		super();
		this.month = month;
		this.year = year;
		this.branch = branch;
		ResName = resName;
		this.total_orders = total_orders;
		this.onTime = onTime;
		this.areLate = areLate;
	}

	public double getLateRate() {
		return lateRate;
	}

	public void setLateRate(double lateRate) {
		this.lateRate = lateRate;
	}

	public double getAvarageCookingTime() {
		return AvarageCookingTime;
	}

	public void setAvarageCookingTime(double avarageCookingTime) {
		AvarageCookingTime = avarageCookingTime;
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

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getResName() {
		return ResName;
	}

	public void setResName(String resName) {
		ResName = resName;
	}

	public int getTotal_orders() {
		return total_orders;
	}

	public void setTotal_orders(int total_orders) {
		this.total_orders = total_orders;
	}

	public int getOnTime() {
		return onTime;
	}

	public void setOnTime(int onTime) {
		this.onTime = onTime;
	}

	public int getAreLate() {
		return areLate;
	}

	public void setAreLate(int areLate) {
		this.areLate = areLate;
	}
}