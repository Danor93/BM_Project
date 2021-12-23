package Entities;

import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int orderNum,useBudget;
	private String restName,timeOfOrder,dateOfOrder,orderStatus,costumerId,restId,useRefund;
	private float totalPrice;
	private String orderType;
	
	public Order(String orderType, String restName, String timeOfOrder,String dateOfOrder, String orderStatus, String costumerId,
			String restId, float totalPrice) {
		this.useBudget=0;
		this.useRefund="0";
		this.orderType = orderType;
		this.restName = restName;
		this.timeOfOrder = timeOfOrder;
		this.dateOfOrder=dateOfOrder;
		this.orderStatus = orderStatus;
		this.costumerId = costumerId;
		this.restId = restId;
		this.totalPrice = totalPrice;
	}


	public int getUseBudget() {
		return useBudget;
	}


	public void setUseBudget(int useBudget) {
		this.useBudget = useBudget;
	}


	public String getUseRefund() {
		return useRefund;
	}


	public void setUseRefund(String useRefund) {
		this.useRefund = useRefund;
	}


	public int getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}


	public String getOrderType() {
		return orderType;
	}


	public void setOrderType(String orderType) {
		this.orderType = orderType;
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


	public float getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getRestName() {
		return restName;
	}


	public String getCostumerId() {
		return costumerId;
	}


	public String getRestId() {
		return restId;
	}
	
	
	
	

}