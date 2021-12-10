package querys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entities.Order;
import Entities.OrderType;
import Entities.Restaurant;
import Entities.homeBranches;
import controllers.ServerUIFController;
import main.EchoServer;

public class queries {
	
	public static Connection conn;
	@SuppressWarnings("deprecation")
	public static Connection connect(String username,String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			EchoServer.serverUIFController.addToTextArea("Driver definition succeed.");
		} catch (Exception ex) {
			/* handle the error */
			EchoServer.serverUIFController.addToTextArea("Driver definition failed.");
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/bytemedatabase?serverTimezone=IST", username,password);
			EchoServer.serverUIFController.addToTextArea("SQL connection succeed.");
			ServerUIFController.flagon=true;
		} catch (SQLException ex) {/* handle any errors */
			EchoServer.serverUIFController.addToTextArea("SQLException:" + ex.getMessage()+".");
			EchoServer.serverUIFController.addToTextArea("SQLState: " + ex.getSQLState()+".");
			EchoServer.serverUIFController.addToTextArea("VendorError: " + ex.getErrorCode()+".");
			EchoServer.serverUIFController.addToTextArea("Wrong input!\n");
		}
		return conn;
	}
	
	
	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	public static String DBCheck(String userName, String password) {
		StringBuilder result = new StringBuilder();
		String rs2=null;
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement(
						"SELECT Role,ID,FirstName,LastName,w4cPrivate,homeBranch FROM bytemedatabase.users WHERE userName=? AND password=?");
				stmt.setString(1, userName);
				stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					result.append(rs.getString(1));
					result.append("@");
					result.append(rs.getString(2));
					result.append("@");
					result.append(rs.getString(3));
					result.append("@");
					result.append(rs.getString(4));
					result.append("@");
					result.append(rs.getString(5));
					result.append("@");
					result.append(rs.getString(6));
				}
				rs.close();

				if (result.length() == 0) {
					result.append("WrongInput");
				}
				stmt = DBConnect.conn.prepareStatement(
						"SELECT isLoggedIn FROM bytemedatabase.users WHERE userName=? AND password=?");
				stmt.setString(1, userName);
				stmt.setString(2, password);
				rs = stmt.executeQuery();
				rs.next();
				rs2 = rs.getString(1).toString(); // isLoggedIn of userName
				if (rs2.equals("0")) {
					stmt = DBConnect.conn.prepareStatement(
							"UPDATE bytemedatabase.users SET isLoggedIn='1' WHERE userName=? AND password=?");
					stmt.setString(1, userName);
					stmt.setString(2, password);
					stmt.executeUpdate();
				}
				else
				{
					System.out.println("already Log In");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result.toString();
	}

	
	
	/**
	 * @param ID
	 * @return
	 */
	public static String IDcheck(String ID) {

		String rs1 = null;
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("SELECT FirstName FROM bytemedatabase.users WHERE ID=?");
				stmt.setString(1, ID);
				// stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();
				rs.next();
				rs1 = rs.getString(1).toString();
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs1;
	}
	
	
	/**
	 * @return
	 */
	/**
	 * @return
	 */
	public static ArrayList<String> getCities() {
		ArrayList<String> cities = new ArrayList<>();
		Statement stmt;
		try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT DISTINCT city FROM bytemedatabase.supplier");
				while (rs.next()) {
					cities.add(rs.getString(1));
				}
				rs.close();
				
				for(String s:cities)
				{
					System.out.println(s);
				}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cities;

	}
	
	
	/**
	 * @return
	 */
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
	
	/**
	 * @param city
	 * @return
	 */
	public static ArrayList<Restaurant> getRestaurants(String city){
		ArrayList<Restaurant> rest=new ArrayList<>();
		PreparedStatement stmt;
		String query = "";
		try {
				stmt = DBConnect.conn.prepareStatement("SELECT restId,supplierName,openingTime,city,address,homeBranch FROM bytemedatabase.supplier WHERE city=? and supplierStatus=?");
				stmt.setString(1,city);
				stmt.setString(2,"approved");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Restaurant rst=new Restaurant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),homeBranches.toHomeBranchType(rs.getString(6)));
					rest.add(rst);
				}
				rs.close();
		}

		 catch (SQLException e) {
			e.printStackTrace();
		}
		return rest;
	}
	
	
	/**
	 * @param address
	 */
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

	/**
	 * @param type
	 */
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


}
