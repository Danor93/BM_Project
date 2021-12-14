package Entities;

import java.io.Serializable;

public class Dish implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String dishName, supplierName, extra, ingredients, restCode, choiceFactor, detailsOfChoice;
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

	public String getRestCode() {
		return restCode;
	}

	public void setRestCode(String restCode) {
		this.restCode = restCode;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getChoiceFactor() {
		return choiceFactor;
	}

	public void setChoiceFactor(String choiceFactor) {
		this.choiceFactor = choiceFactor;
	}

	public String getDetailsOfChoice() {
		return detailsOfChoice;
	}

	public void setDetailsOfChoice(String detailsOfChoice) {
		this.detailsOfChoice = detailsOfChoice;
	}
}