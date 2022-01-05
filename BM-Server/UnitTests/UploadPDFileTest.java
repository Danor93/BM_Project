import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UploadPDFileTest {

public static Connection testConn;	
	
	public class testDBConnect {
		public Connection testConn;
		
		/**
		 * this is an inner class that implements the connection of the DB without the gui.
		 * @return - a Connection parameter.
		 */
		public Connection testConnect() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			} catch (Exception ex) {
				/* handle the error */
				ex.printStackTrace();
			}

			try {
				testConn = DriverManager.getConnection("jdbc:mysql://localhost/bitemedb?serverTimezone=IST", "root","Aa123456");
			} catch (SQLException ex) {/* handle any errors */
			}
			return testConn;
		}
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
