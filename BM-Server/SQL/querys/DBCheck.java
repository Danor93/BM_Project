package querys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Entities.Order;
import Entities.OrderType;

public class DBCheck {

	public static String DBCheck(String userName, String password) {

		StringBuilder result = new StringBuilder();
		String rs2 = null;
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement(
						"SELECT Role,ID,FirstName,LastName,homeBranch,userName,password,isLoggedIn FROM bitemedb.users WHERE userName=? AND password=?");
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
					result.append("@");
					result.append(rs.getString(7));
					result.append("@");
					result.append(rs.getString(8));
				}
				rs.close();

				if (result.length() == 0) {
					result.append("WrongInput");
				}
				stmt = DBConnect.conn
						.prepareStatement("SELECT isLoggedIn FROM bitemedb.users WHERE userName=? AND password=?");
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
					return "Already";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result.toString();
	}

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

	public static Boolean MenuExistingCheck(String ID) {

		String rs1 = null;
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("SELECT dishName FROM bitemedb.dishes WHERE restId1=?");
				stmt.setString(1, ID);
				ResultSet rs = stmt.executeQuery();
				while (rs.next())
					rs1 = rs.getString(1).toString();
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("return false");
		}
		if(rs1==null)
			return false;
		else
			return true;
	}

	public static ArrayList<String> w4cBusinessGet() {
		ArrayList<String> rs1 = new ArrayList<String>();
		Statement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT w4cBusiness FROM bitemedb.company");
				while (rs.next()) {
					rs1.add(rs.getString(1).toString());
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs1;
	}
}
