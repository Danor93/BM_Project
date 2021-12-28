package Entities;

import java.io.Serializable;

public class Delivery implements Serializable{

	private static final long serialVersionUID = 8991119981743155411L;
	
	private String deliveryType,address,phone,city,recipient;
	private int participantsNum,orderNum;
	private int deliPrice;
	
	
	public Delivery(String deliveryType, String address, String phone, String city, String recipient,
			int participantsNum, int deliPrice) {
		this.deliveryType = deliveryType;
		this.address = address;
		this.phone = phone;
		this.city = city;
		this.recipient = recipient;
		this.participantsNum = participantsNum;
		this.deliPrice = deliPrice;
	}

	

	public int getOrderNum() {
		return orderNum;
	}



	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}



	public String getDeliveryType() {
		return deliveryType;
	}


	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getRecipient() {
		return recipient;
	}


	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}


	public int getParticipantsNum() {
		return participantsNum;
	}


	public void setParticipantsNum(int participantsNum) {
		this.participantsNum = participantsNum;
	}


	public int getDeliPrice() {
		return deliPrice;
	}


	public void setDeliPrice(int deliPrice) {
		this.deliPrice = deliPrice;
	}
	

}
