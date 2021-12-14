package querys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import Entities.Employer;
import controllers.ServerUIFController;
import javafx.stage.FileChooser;

public class Query {

	/*importData 
	 * this method import the database script
	 * */
	public static void importData() {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SQL", "*.sql"));
		File file = fileChooser.showOpenDialog(ServerUIFController.serveruifconroller.stage);
		String path;
		if (file != null) {
			path = file.getPath();
			if(DBConnect.conn==null) {
			ServerUIFController.serveruifconroller.message("First insert user and password,press Start and then import", "Error");
			}
			else {
			Connection connection= DBConnect.conn;
			InputStream targetStream = null;
			try {
				targetStream = new FileInputStream(path);
			} catch (FileNotFoundException e1) {
				ServerUIFController.serveruifconroller.message("The database file is incorrect", "Error");
				e1.printStackTrace();
			}
			try {
				readtScript(connection, targetStream);
			} catch (SQLException e) {
				ServerUIFController.serveruifconroller.message("The database file is incorrect", "Error");
				e.printStackTrace();
			}
			ServerUIFController.serveruifconroller.message("The database was successfully imported", "Success");
			}
		} else {
			System.out.println("error"); // or something else
		}
	}
	
	/**readtScript this method get script and executed it into database
	 * @param conn - the connection to db
	 * @param in - the script file
	 */
	public static void readtScript(Connection conn, InputStream in) throws SQLException
	{
	    Scanner s = new Scanner(in);
	    s.useDelimiter("(;(\r)?\n)|(--\n)");
	    Statement st = null;
	    try
	    {
	        st = conn.createStatement();
	        while (s.hasNext())
	        {
	            String line = s.next();
	            if (line.startsWith("/*!") && line.endsWith("*/"))
	            {
	                int i = line.indexOf(' ');
	                line = line.substring(i + 1, line.length() - " */".length());
	            }

	            if (line.trim().length() > 0)
	            {
	                st.execute(line);
	            }
	        }
	    }
	    finally
	    {
	        if (st != null) st.close();
	    }
	}
	
	
	public static ArrayList<Employer> LoadEmployers() {
		
		ArrayList<Employer> employers = new ArrayList<>();
		Statement stmt;
		try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM bytemedatabase.company");
				Employer employer = null;
				while (rs.next()) {
					employer.setW4cBussines(rs.getString("w4cBusiness"));
					employer.setCompanyName(rs.getString("companyName"));
					if(rs.getString("companyStatus").equals("approved")) {
						employer.setCompanyStatus(true);
					}
					else {
						employer.setCompanyStatus(false);
					}
					System.out.println(employer);
					employers.add(employer);
				}
				rs.close();
				
				for(Employer s:employers)
				{
					System.out.println(s);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employers;
	}

}
