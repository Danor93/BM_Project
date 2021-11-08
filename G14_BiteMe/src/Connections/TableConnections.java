package Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TableConnections {
	public static void main(String[] args) 
	{
		try 
		{
            Class.forName("com.mysql.cj.G14_Tables.Driver").newInstance();
            System.out.println("Driver definition succeed");
        } catch (Exception ex) {
        	/* handle the error*/
        	 System.out.println("Driver definition failed");
        	 }   
        try 
        {
            Connection conn = DriverManager.getConnection("G14_Tables:mysql://localhost/world?serverTimezone=IST","root","LiorGanon07875306");
            System.out.println("SQL connection succeed");
            //createTableCourses(conn);
            //printCourses(conn);
     	} catch (SQLException ex) 
     	    {/* handle any errors*/
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            }
   	}

	public static void printClient(Connection con)
	{
		Statement stmt;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM order;");
	 		while(rs.next())
	 		{
				 // Print out the values
				 System.out.println(rs.getString(1)+"  " +rs.getString(2)+"  "+rs.getString(3)+"  +"+rs.getString(4)+"  "+rs.getNString(5)+"  "+rs.getString(6));
			} 
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	public static void Updates(Connection con1){
		PreparedStatement stmt;
		Scanner s = new Scanner(System.in);
		String typeOfOrder, orderAddress;
		try {
			typeOfOrder = s.next();
			orderAddress = s.next();
			if(orderAddress.length() <= 45 && typeOfOrder.length() <= 20) {
				stmt = con1.prepareStatement("UPDATE order SET typeOfOrder = ? WHERE orderAddress = ?");
		 		stmt.setString(1,typeOfOrder);
		 		stmt.setString(2,orderAddress);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {	e.printStackTrace();}
		 		
	}
}