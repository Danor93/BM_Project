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
				query = "SELECT * FROM Order";
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					String resturant = rs.getString("Resturant");
					int OrderNumber = Integer.parseInt(rs.getString("orderNumber"));
					String OrderTime = rs.getString("OrderTime");
					String PhoneNumber = rs.getString("PhoneNumber");
					OrderType orderType = OrderType.toOrderType(rs.getString("OrderType"));
					String OrderAddress = rs.getString("OrderAddress");
					orders.add(new Order(resturant, OrderNumber, OrderTime, PhoneNumber, orderType, OrderAddress));
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
