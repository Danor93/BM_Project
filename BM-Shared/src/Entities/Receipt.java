package Entities;

import java.io.Serializable;

public class Receipt implements Serializable{
	
	private static final long serialVersionUID = 1865084539851638526L;
	private int orderNumber;
	private String restaurant;
	private float totalPrice;
	private float priceAfterCommission ;
	
	public Receipt(int orderNumber, String restaurant, float totalPrice, float priceAfterCommission) {
		this.orderNumber = orderNumber;
		this.restaurant = restaurant;
		this.totalPrice = totalPrice;
		this.priceAfterCommission = priceAfterCommission;
	}
	
	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public float getPriceAfterCommission() {
		return priceAfterCommission;
	}

	public void setPriceAfterCommission(float priceAfterCommission) {
		this.priceAfterCommission = priceAfterCommission;
	}
}
