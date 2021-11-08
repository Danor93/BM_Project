package Entities;

public class Order {

	public Dish[] OrderedDishes;

	public String Restaurants;
	public String SpecificDetails;
	public double Discount;
	public double finalPrice;

	public enum paymantmethod {
		CREDIT_CARD, BUSINESS_CARD
	}

	public String getRestaurants() {
		return Restaurants;
	}

	public void setRestaurants(String restaurants) {
		Restaurants = restaurants;
	}

	public String getSpecificDetails() {
		return SpecificDetails;
	}

	public void setSpecificDetails(String specificDetails) {
		SpecificDetails = specificDetails;
	}

	public double getDiscount() {
		return Discount;
	}

	public void setDiscount(double discount) {
		Discount = discount;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Dish[] getOrderedDishes() {
		return OrderedDishes;
	}

	public void setOrderedDishes(Dish[] orderedDishes) {
		OrderedDishes = orderedDishes;
	}

}
