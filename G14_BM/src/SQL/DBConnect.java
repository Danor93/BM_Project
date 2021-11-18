package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Controllers.*;

import Server.EchoServer;

public class DBConnect {

	@SuppressWarnings("deprecation")
	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			//EchoServer.serverController.addToTextArea("Driver definition succeed.");
		} catch (Exception ex) {
			/* handle the error */
			EchoServer.serverUIFController.addToTextArea("Driver definition failed.");
		}

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/ordertable?serverTimezone=IST", "root",
					"Ds0502660865");
			EchoServer.serverUIFController.addToTextArea("SQL connection succeed.");
		} catch (SQLException ex) {/* handle any errors */
			EchoServer.serverUIFController.addToTextArea("SQLException:" + ex.getMessage()+".");
			EchoServer.serverUIFController.addToTextArea("SQLState: " + ex.getSQLState()+".");
			EchoServer.serverUIFController.addToTextArea("VendorError: " + ex.getErrorCode()+".");
		}
		return con;
	}
	
}
