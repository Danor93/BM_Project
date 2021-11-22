package SQL;

import java.sql.SQLException;
import java.sql.Statement;
import Entities.Order;

public class UpdateDB {

	public static void UpdateOrderAddress(String address) {
		Statement stmt;
		String query = "";
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.createStatement();
				// query = "UPDATE order.orders " + "SET OrderAddress = '" + address + "';";
				stmt.executeUpdate("UPDATE order.orders " + "SET OrderAddress = '" + address + "';");
			} else {
				System.out.println("Conn is null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void UpdateTypeOrder(String type) {
		Statement stmt;
		String query = "";
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.createStatement();
				// query = "UPDATE order.orders " + "SET TypeOfOrder = '" + type + "';";
				stmt.executeUpdate("UPDATE order.orders " + "SET TypeOfOrder = '" + type + "';");
			} else {
				System.out.println("Conn is null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
