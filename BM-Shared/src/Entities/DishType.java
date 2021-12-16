package Entities;

public enum DishType {

	Starter, Salad, MainDish, Dessert, Drink;

	public static DishType toDishType(String type) {

		switch (type) {
		case "Starter": {
			return Starter;
		}

		case "Salad": {
			return Salad;
		}

		case "Main dish": {
			return MainDish;
		}

		case "Dessert": {
			return Dessert;
		}

		case "Drink": {
			return Drink;
		}

		default: {
			return null;
		}

		}

	}

	public static String typeToString(DishType dishType) {
		switch (dishType) {
		case MainDish: {
			return "Main dish";
		}
		case Drink: {
			return "Drink";
		}
		case Starter: {
			return "Starter";
		}
		case Dessert: {
			return "Dessert";
		}
		case Salad: {
			return "Salad";
		}
		default: {
			return null;
		}

		}
	}
}
