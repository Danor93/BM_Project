package querys;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Entities.Order;
import Entities.OrderType;


public class DBCheck {

	public static String DBCheck(String userName, String password) {
		
		String ReturnedValue = null;
		Statement stmt;
		String query="";
		try {
			if(DBConnect.conn != null) {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT Role FROM order.users WHERE userName="+ userName +" AND password="+password+"");
				if(rs!=null )
				  ReturnedValue = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ReturnedValue; 
	}
}