package querys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import controllers.ServerUIFController;
import main.EchoServer;

public class DBConnect {

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
			conn = DriverManager.getConnection("jdbc:mysql://localhost/order?serverTimezone=IST", username,password);
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
	
}
