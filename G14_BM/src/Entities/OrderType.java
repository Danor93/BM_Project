package Entities;

public enum OrderType {

	Order_in,Single_Delivery,take_out,Shared_Delivery;
	
	
	public static OrderType toOrderType(String type) {
		if(type == null || type.equals("")) {
			return null;
		}
		if(type.equals("order in")) {
			return OrderType.Order_in;
		}
		if(type.equals("single delivery")) {
			return OrderType.Single_Delivery;
		}
		if(type.equals("take out")) {
			return OrderType.take_out;
		}
		if(type.equals("shared delivery")) {
			return OrderType.Shared_Delivery;
		}
		return null;
	}
}
