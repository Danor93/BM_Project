package SQL;

import java.sql.SQLException;
import java.sql.Statement;
import Entities.Order;

public class UpdateDB {
	
	public static void UpdateOrderAddress(Order order) {
		Statement stmt;
		String query = "";
		try {
			if(DBConnect.conn!= null) {
				stmt = DBConnect.conn.createStatement();
				query = "UPDATE Order "
					  + "SET OrderAddress = '" + order.getOrderAddress() + "' "
					  + "WHERE OrderNumber = '" + order.getOrderNumber() + "';";
				stmt.executeUpdate(query);
			}else {
				System.out.println("Conn is null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void UpdateTypeOrder(Order order) {
		Statement stmt;
		String query = "";
		try {
			if(DBConnect.conn!= null) {
				stmt = DBConnect.conn.createStatement();
				query = "UPDATE Order "
					  + "SET TypeOfOrder = '" + order.getOrderAddress() + "' "
					  + "WHERE OrderNumber = '" + order.getOrderNumber() + "';";
				stmt.executeUpdate(query);
			}else {
				System.out.println("Conn is null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
