package Entities;

public class Dish {

public String Details; 
public String Meal; 
public String type;
public double price;


public Dish(String details, String meal, String type, double price) {
	super();
	this.setDetails(details);
	this.setMeal(meal);
	this.setType(type);
	this.setPrice(price);
}
public String getDetails() {
	return Details;
}
public void setDetails(String details) {
	Details = details;
}
public String getMeal() {
	return Meal;
}
public void setMeal(String meal) {
	Meal = meal;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}



	
	
	
}
