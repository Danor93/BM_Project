package SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Entities.Order;
import Entities.OrderType;

public class ShowOrders {
	public static ArrayList<Order> getOrders() {
		ArrayList<Order> orders = new ArrayList<>();
		Statement stmt;
		String query = "";
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM order.orders");
				while (rs.next()) {
					String restaurant = rs.getString("Restaurant");
					int OrderNumber = Integer.parseInt(rs.getString("OrderNumber"));
					String OrderTime = rs.getString("OrderTime");
					String PhoneNumber = rs.getString("PhoneNumber");
					OrderType orderType = OrderType.toOrderType(rs.getString("TypeOfOrder"));
					String OrderAddress = rs.getString("OrderAddress");
					orders.add(new Order(restaurant, OrderNumber, OrderTime, PhoneNumber, orderType, OrderAddress));
				}
				rs.close();
			} else {
				System.out.println("Conn is null");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
}
