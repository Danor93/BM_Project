package Entities;

import java.util.ArrayList;

public class RevenueReport extends Report {

	private static final long serialVersionUID = -140954762019086715L;
	private ArrayList<Order> Data = new ArrayList<>();
	private int sum;

	public RevenueReport(ArrayList<Restaurant> restaurant, String month, String year) {
		super(restaurant, month, year);
		sum = 0;
	}

	public int CaculateSum() {
		for (int i = 0; i < Data.size(); i++) {
			sum += Data.get(i).getTotalPrice();
		}
		return sum;
	}

	public int getOrderAmount() {
		return Data.size();
	}
	
	public void addToData (Order order) {
		Data.add(order);
	}
	
	public ArrayList<Order> getData(){
		return Data;
	}
}
