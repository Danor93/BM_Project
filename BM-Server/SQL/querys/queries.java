package querys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entities.BussinessAccount;
import Entities.Client;
import Entities.Dish;
import Entities.DishType;
import Entities.Order;
import Entities.OrderType;
import Entities.Restaurant;
import Entities.SingletonOrder;
import Entities.homeBranches;
import controllers.ServerUIFController;
import main.EchoServer;

public class queries {

	public static Connection conn;

	@SuppressWarnings("deprecation")
	public static Connection connect(String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			EchoServer.serverUIFController.addToTextArea("Driver definition succeed.");
		} catch (Exception ex) {
			/* handle the error */
			EchoServer.serverUIFController.addToTextArea("Driver definition failed.");
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/bitemedb?serverTimezone=IST", username,
					password);
			EchoServer.serverUIFController.addToTextArea("SQL connection succeed.");
			ServerUIFController.flagon = true;
		} catch (SQLException ex) {/* handle any errors */
			EchoServer.serverUIFController.addToTextArea("SQLException:" + ex.getMessage() + ".");
			EchoServer.serverUIFController.addToTextArea("SQLState: " + ex.getSQLState() + ".");
			EchoServer.serverUIFController.addToTextArea("VendorError: " + ex.getErrorCode() + ".");
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
		String rs2 = null;
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement(
						"SELECT Role,ID,FirstName,LastName,w4cPrivate,homeBranch FROM bitemedb.users WHERE userName=? AND password=?");
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
						"SELECT isLoggedIn FROM bitemedb.users WHERE userName=? AND password=?");
				stmt.setString(1, userName);
				stmt.setString(2, password);
				rs = stmt.executeQuery();
				rs.next();
				rs2 = rs.getString(1).toString(); // isLoggedIn of userName
				if (rs2.equals("0")) {
					stmt = DBConnect.conn.prepareStatement(
							"UPDATE bitemedb.users SET isLoggedIn='1' WHERE userName=? AND password=?");
					stmt.setString(1, userName);
					stmt.setString(2, password);
					stmt.executeUpdate();
				} else {
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
				stmt = DBConnect.conn.prepareStatement("SELECT FirstName FROM bitemedb.users WHERE ID=?");
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
	public static ArrayList<String> getCities() {
		ArrayList<String> cities = new ArrayList<>();
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT DISTINCT city FROM bitemedb.supplier");
			while (rs.next()) {
				cities.add(rs.getString(1));
			}
			rs.close();

			for (String s : cities) {
				System.out.println(s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cities;

	}

	/**
	 * @param city
	 * @return
	 */
	public static ArrayList<Restaurant> getRestaurants(String city) {
		ArrayList<Restaurant> rest = new ArrayList<>();
		PreparedStatement stmt;
		String query = "";
		try {
			stmt = DBConnect.conn.prepareStatement(
					"SELECT restId,supplierName,openingTime,city,address,homeBranch FROM bitemedb.supplier WHERE city=? and supplierStatus=?");
			stmt.setString(1, city);
			stmt.setString(2, "approved");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Restaurant rst = new Restaurant(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), homeBranches.toHomeBranchType(rs.getString(6)));
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
				stmt.setString(1, address);
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
				stmt.setString(1, type);
				stmt.executeUpdate();

			} else {
				System.out.println("Conn is null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Client checkAccountKind(String id) {
		Client client = null;
		PreparedStatement stmt1, stmt2, stmt3;
		String w4c = null;

		try {
			stmt1 = DBConnect.conn.prepareStatement("SELECT w4c_private FROM bitemedb.client WHERE client_id=?");
			stmt1.setString(1, id);
			ResultSet rs = stmt1.executeQuery();
			while (rs.next()) {
				w4c = rs.getString(1);
			}
			rs.close();

			stmt2 = DBConnect.conn
					.prepareStatement("SELECT companyName,budget FROM bitemedb.buss_client WHERE ID=?");
			stmt2.setString(1, id);
			ResultSet rs2 = stmt2.executeQuery();
			while (rs2.next()) {
				client = new BussinessAccount(rs2.getString(1), w4c, rs2.getString(2), null, null, null, null, null, null, null, null, null);
			}
			rs2.close();

			if (client instanceof BussinessAccount) {
				BussinessAccount bussinessAccount = (BussinessAccount) client;
				stmt3 = DBConnect.conn
						.prepareStatement("SELECT w4cBusiness FROM bitemedb.company WHERE companyName=?");
				stmt3.setString(1, bussinessAccount.getCompanyName());
				ResultSet rs3 = stmt3.executeQuery();
				while (rs3.next()) {
					bussinessAccount.setEmployerCode(rs3.getString(1));
				}
				rs3.close();

			}

			else {
				client = new Client(w4c, null, null,null, null, null, null, null, null, null, null);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

	public static String getRefundSum(Order order) {
		PreparedStatement stmt;
		String ammount = null;

		try {
			stmt = DBConnect.conn.prepareStatement("SELECT ammount FROM bitemedb.refund WHERE ID=? and restId=?");
			stmt.setString(1, order.getCostumerId());
			stmt.setString(2, order.getRestId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ammount = rs.getString(1);
			}
			rs.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return ammount;
	}

	public static Integer insertOrder(Order msg) {
		Integer orderNum = null;
		PreparedStatement stmt, stmt1;

		try {
			stmt = DBConnect.conn.prepareStatement("INSERT INTO bitemedb.order VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, null);
			stmt.setString(2, msg.getOrderType());
			stmt.setString(3, msg.getRestName());
			stmt.setString(4, msg.getRestId());
			stmt.setString(5, Float.toString(msg.getTotalPrice()));
			stmt.setString(6, msg.getTimeOfOrder());
			stmt.setString(7, msg.getDateOfOrder());
			stmt.setString(8, msg.getOrderStatus());
			stmt.setString(9, msg.getCostumerId());
			stmt.setString(10, msg.getUseRefund());
			stmt.setInt(11, msg.getUseBudget());
			stmt.setString(12, msg.getEarlyOrder());

			stmt.executeUpdate();

			stmt1 = DBConnect.conn.prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet rs = stmt1.executeQuery();
			while (rs.next()) {
				orderNum = rs.getInt(1);
			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orderNum;

	}

	public static String insertDishesOrder(ArrayList<Dish> message) {
		PreparedStatement stmt;
		int orderNumber = message.get(0).getOrderNumber();

		try {

			for (Dish dish : message) {
				stmt = DBConnect.conn
						.prepareStatement("INSERT INTO bitemedb.dishesinorder VALUES(?,?,?,?,?,?,?,?,?)");
				stmt.setString(1, null);
				stmt.setInt(2, orderNumber);
				stmt.setString(3, dish.getDishName());
				stmt.setString(4, dish.getRestCode());
				stmt.setString(5, DishType.fromTypeToStr(dish.getDishType()));
				stmt.setString(6, dish.getChoiceFactor());
				stmt.setString(7, dish.getChoiceDetails());
				stmt.setString(8, dish.getExtra());
				stmt.setInt(9, dish.getQuentity());
				stmt.executeUpdate();
			}
			return "success!";

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
}
