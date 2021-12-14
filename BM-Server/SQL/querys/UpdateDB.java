package querys;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Entities.Dish;

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
	
	public static boolean NewDish(Dish dish) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("INSERT INTO bytemedatabase.dishes(dishName, dishType, restId1, supplierName,inventory, price,size) VALUES (?, ?, ?, ?,?, ?,?)");
				stmt.setString(1,dish.getDishName());
				stmt.setString(2,dish.getDishType().toString());
				stmt.setString(3,dish.getRestCode());
				stmt.setString(4,dish.getSupplierName());
				stmt.setString(5,String.valueOf(dish.getInventory()));
				stmt.setString(6,String.valueOf(dish.getPrice()));
				stmt.setString(7,dish.getSize());
		 		stmt.executeUpdate();
		 		return true;

			} else {
				System.out.println("Conn is null");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}