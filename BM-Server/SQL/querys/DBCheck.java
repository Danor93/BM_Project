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
		
		String rs1=null, rs2=null;
		PreparedStatement stmt;
		try {
			if(DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("SELECT Role FROM bytemedatabase.users WHERE userName=? AND password=?");
				stmt.setString(1, userName);
				stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();
				rs.next();
				rs1 = rs.getString(1).toString(); //Role of userName
				rs.close();
				if(rs1!=null) {
					stmt = DBConnect.conn.prepareStatement("SELECT isLoggedIn FROM bytemedatabase.users WHERE userName=? AND password=?");
					stmt.setString(1, userName);
					stmt.setString(2, password);
					rs = stmt.executeQuery();
					rs.next();
					rs2 = rs.getString(1).toString(); //isLoggedIn of userName
					if(rs2.equals("0")) {
						stmt = DBConnect.conn.prepareStatement("UPDATE bytemedatabase.users SET isLoggedIn='1' WHERE userName=? AND password=?");
						stmt.setString(1, userName);
						stmt.setString(2, password);
						stmt.executeUpdate();
					}
					else {
						System.out.println("Already loggedIn");
						rs1="AlreadyLoggedIn";
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs1; 
	}
}