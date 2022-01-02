package Entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

	private static final long serialVersionUID = 2360410765980564003L;
	private int orderNum, useBudget;
	private String restName, timeOfOrder, dateOfOrder, orderStatus, costumerId, restId, useRefund, EarlyOrder,suppApproved;
	private String timeSended;

	private float totalPrice;
	private String orderType, dishes;

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

	public Order(int orderNum, String restName, String timeOfOrder, String dateOfOrder, String EarlyOrder) {
		this.orderNum = orderNum;
		this.restName = restName;
		this.timeOfOrder = timeOfOrder;
		this.dateOfOrder = dateOfOrder;
		this.EarlyOrder = EarlyOrder;

	}
	public String getSuppApproved() {
		return suppApproved;
	}

	public void setSuppApproved(String suppApproved) {
		this.suppApproved = suppApproved;
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

	public String getEarlyOrder() {
		return EarlyOrder;
	}

	public void setEarlyOrder(String earlyOrder) {
		EarlyOrder = earlyOrder;
	}

	public String getDishes() {
		return dishes;
	}

	public void setDishes(String dishes) {
		this.dishes = dishes;
	}

	public String getTimeSended() {
		return timeSended;
	}

	public void setTimeSended(String timeSended) {
		this.timeSended = timeSended;
	}
}