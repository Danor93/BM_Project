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
		
		StringBuilder result=new StringBuilder();
		PreparedStatement stmt;
		try {
			if(DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("SELECT Role,ID,FirstName,LastName,w4cPrivate,homeBranch FROM bytemedatabase.users WHERE userName=? AND password=?");
				stmt.setString(1, userName);
				stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next())
				{
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
				
				
				if(result.length()==0)
				{
					result.append("WrongInput");
				}
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}
}