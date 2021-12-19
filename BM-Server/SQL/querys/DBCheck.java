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
						"SELECT Role,ID,FirstName,LastName,homeBranch,userName,password,isLoggedIn FROM bytemedatabase.users WHERE userName=? AND password=?");
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

	public static Boolean MenuExistingCheck(String ID) {

		String rs1 = null;
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("SELECT dishName FROM bytemedatabase.dishes WHERE restId1=?");
				stmt.setString(1, ID);
				// stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();
				rs.next();
				rs1 = rs.getString(1).toString();
				rs.close();
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("return false");
			return false; 
		}
		System.out.println("in sql before false rs1="+ rs1);
			return true; 
			
	}
}