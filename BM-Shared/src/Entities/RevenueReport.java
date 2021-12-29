package Entities;

import java.util.ArrayList;
import java.util.Hashtable;

public class RevenueReport extends Report {

	private static final long serialVersionUID = -140954762019086715L;
	private ArrayList<Order> Data = new ArrayList<>();
	private int sum;
	private Hashtable<String, ArrayList<Order>> OrdersOfRests = new Hashtable<>();

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

	public void addToData(Order order) {
		Data.add(order);
	}

	public ArrayList<Order> getData() {
		return Data;
	}

	public void OrgenizeData() {
		ArrayList<Order> tempArray = new ArrayList<>();
		for (Restaurant r : super.Restaurant) {
			for (Order o : Data) {
				if (o.getRestId().equals(r.getRestCode()))
					tempArray.add(o);
			}
			OrdersOfRests.put(r.getSupplierName(), tempArray);
		}

	}

	public Hashtable<String, ArrayList<Order>> getOrgizedData() {

		return this.OrdersOfRests;
	}
}
