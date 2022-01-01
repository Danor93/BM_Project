package querys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBFirstName {
	
	public static String DBFirstName(String userName, String password) {
		String rs1=null;
		PreparedStatement stmt;
		try {
			if(DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("SELECT FirstName FROM bitemedb.users WHERE userName=? AND password=?");
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