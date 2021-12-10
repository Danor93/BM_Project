package Entities;

import java.io.Serializable;

public class Dish implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String dishName,supplierName,size,cookLevel,extra;
	private float price;
	private int inventory;
	private DishType dishType;
	
	
	public Dish(String dishName, String supplierName, float price,int inventory, DishType dishType) {
		this.dishName = dishName;
		this.supplierName = supplierName;
		this.price = price;
		this.inventory = inventory;
		this.dishType = dishType;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public String getCookLevel() {
		return cookLevel;
	}


	public void setCookLevel(String cookLevel) {
		this.cookLevel = cookLevel;
	}


	public String getExtra() {
		return extra;
	}


	public void setExtra(String extra) {
		this.extra = extra;
	}


	public String getDishName() {
		return dishName;
	}


	public String getSupplierName() {
		return supplierName;
	}


	public float getPrice() {
		return price;
	}


	public int getInventory() {
		return inventory;
	}


	public DishType getDishType() {
		return dishType;
	}
	

}
