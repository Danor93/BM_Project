package Entities;

import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = 2360410765980564003L;
	private int orderNum, useBudget;
	private String restName, timeOfOrder, dateOfOrder, orderStatus, costumerId, restId, useRefund;
	private float totalPrice;
	private String orderType;

	public Order(String orderType, String restName, String timeOfOrder, String dateOfOrder, String orderStatus,
			String costumerId, String restId, float totalPrice) {
		this.useBudget = 0;
		this.useRefund = "0";
		this.orderType = orderType;
		this.restName = restName;
		this.timeOfOrder = timeOfOrder;
		this.dateOfOrder = dateOfOrder;
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

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getUseBudget() {
		return useBudget;
	}

	public void setUseBudget(int useBudget) {
		this.useBudget = useBudget;
	}

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public String getTimeOfOrder() {
		return timeOfOrder;
	}

	public void setTimeOfOrder(String timeOfOrder) {
		this.timeOfOrder = timeOfOrder;
	}

	public String getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(String dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCostumerId() {
		return costumerId;
	}

	public void setCostumerId(String costumerId) {
		this.costumerId = costumerId;
	}

	public String getRestId() {
		return restId;
	}

	public void setRestId(String restId) {
		this.restId = restId;
	}

	public String getUseRefund() {
		return useRefund;
	}

	public void setUseRefund(String useRefund) {
		this.useRefund = useRefund;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}