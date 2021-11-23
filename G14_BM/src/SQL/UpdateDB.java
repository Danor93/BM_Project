package SQL;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import Entities.Order;

public class UpdateDB {

	public static void UpdateOrderAddress(String address) {
		PreparedStatement stmt;
		String query = "";
		try {
			if (DBConnect.conn != null) {
				//stmt = DBConnect.conn.createStatement();
				// query = "UPDATE order.orders " + "SET OrderAddress = '" + address + "';";
				//stmt.executeUpdate("UPDATE order.orders " + "SET OrderAddress = '" + address + "';");
				stmt = DBConnect.conn.prepareStatement("UPDATE order.orders SET OrderAddress = ?");
				stmt.setString(1,address);
		 		stmt.executeUpdate();

			} else {
				System.out.println("Conn is null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void UpdateTypeOrder(String type) {
		PreparedStatement stmt;
		String query = "";
		try {
			if (DBConnect.conn != null) {
				//stmt = DBConnect.conn.createStatement();
				// query = "UPDATE order.orders " + "SET TypeOfOrder = '" + type + "';";
				//stmt.executeUpdate("UPDATE order.orders " + "SET TypeOfOrder = '" + type + "';");
				stmt = DBConnect.conn.prepareStatement("UPDATE order.orders SET TypeOfOrder = ?");
				stmt.setString(1,type);
		 		stmt.executeUpdate();

			} else {
				System.out.println("Conn is null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
