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
		
		String rs1=null;
		PreparedStatement stmt;
		try {
			if(DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("SELECT Role FROM bytemedatabase.users WHERE userName=? AND password=?");
				stmt.setString(1, userName);
				stmt.setString(2, password);
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
}