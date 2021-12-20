package Entities;

import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int orderNum;
	private String restName,timeOfOrder,dateOfOrder,orderStatus,costumerId,restId;
	private float totalPrice;
	private OrderType orderType;
	
	public Order(OrderType orderType, String restName, String timeOfOrder, String dateOfOrder, String orderStatus, String costumerId,
			String restId, float totalPrice) {
		this.orderType = orderType;
		this.restName = restName;
		this.timeOfOrder = timeOfOrder;
		this.dateOfOrder = dateOfOrder;
		this.orderStatus = orderStatus;
		this.costumerId = costumerId;
		this.restId = restId;
		this.totalPrice = totalPrice;
	}

	public int getOrderNum() {
		return orderNum;
	}
	
	public String getRestName() {
		return restName;
	}


	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}


	public OrderType getOrderType() {
		return orderType;
	}


	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}


	public String getTimeOfOrder() {
		return timeOfOrder;
	}


	public void setTimeOfOrder(String timeOfOrder) {
		this.timeOfOrder = timeOfOrder;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public float getTotalPrice() {
		return totalPrice;
	}

	public String getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(String dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	
	@Override
	public String toString() {
		return orderNum+String.valueOf(orderType)+restId+timeOfOrder+dateOfOrder;
		
	}

	public String getCostumerId() {
		return costumerId;
	}
}