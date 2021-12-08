package querys;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDB {

	public static void UpdateOrderAddress(String address) {
		PreparedStatement stmt;
		String query = "";
		try {
			if (DBConnect.conn != null) {
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
	
	public static void UpdateisLoggedIn(String userName) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("UPDATE bytemedatabase.users SET isLoggedIn = '0' WHERE userName=?");
				stmt.setString(1,userName);
		 		stmt.executeUpdate();

			} else {
				System.out.println("Conn is null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
