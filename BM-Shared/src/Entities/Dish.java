package Entities;

import java.io.Serializable;

public class Dish implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String dishName,supplierName,choiceFactor,choiceDetails,ingredients,extra;
	private float price;
	private int inventory;
	private DishType dishType;
	

	public Dish(String dishName, String supplierName, String choiceFactor, String choiceDetails, String ingredients,
			String extra, float price, int inventory, DishType dishType) {
		this.dishName = dishName;
		this.supplierName = supplierName;
		this.choiceFactor = choiceFactor;
		this.choiceDetails = choiceDetails;
		this.ingredients = ingredients;
		this.extra = extra;
		this.price = price;
		this.inventory = inventory;
		this.dishType = dishType;
	}
	
	


	public void setExtra(String extra) {
		this.extra = extra;
	}



	public String getChoiceFactor() {
		return choiceFactor;
	}



	public void setChoiceFactor(String choiceFactor) {
		this.choiceFactor = choiceFactor;
	}



	public String getChoiceDetails() {
		return choiceDetails;
	}



	public void setChoiceDetails(String choiceDetails) {
		this.choiceDetails = choiceDetails;
	}



	public String getIngredients() {
		return ingredients;
	}



	public String getExtra() {
		return extra;
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
