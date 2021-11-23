package Entities;

import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	private String Restaurant;
	private int OrderNumber;
	private String OrderTime;
	private String PhoneNumber;
	private OrderType OrderType;
	private String OrderAddress;

	public Order(String Restaurant, int orderNumber, String orderTime, String phoneNumber, Entities.OrderType orderType,
			String orderAddress) {
		//super();
		this.Restaurant = Restaurant;
		this.OrderNumber = orderNumber;
		this.OrderTime = orderTime;
		this.PhoneNumber = phoneNumber;
		this.OrderType = orderType;
		this.OrderAddress = orderAddress;
	}

	public String getResturant() {
		return Restaurant;
	}

	public void setResturant(String resturant) {
		Restaurant = resturant;
	}

	public int getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		OrderNumber = orderNumber;
	}

	public String getOrderTime() {
		return OrderTime;
	}

	public void setOrderTime(String orderTime) {
		OrderTime = orderTime;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public OrderType getOrderType() {
		return OrderType;
	}

	public void setOrderType(OrderType orderType) {
		OrderType = orderType;
	}

	public String getOrderAddress() {
		return OrderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		OrderAddress = orderAddress;
	}
}
