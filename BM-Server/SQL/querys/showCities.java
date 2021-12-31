package querys;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entities.Order;
import Entities.OrderType;

public class showCities {

	public static ArrayList<String> getCities() {
		ArrayList<String> cities = new ArrayList<>();
		Statement stmt;
		try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT DISTINCT city FROM bitemedb.supplier");
				while (rs.next()) {
					cities.add(rs.getString(1));
				}
				rs.close();
				for(String s:cities)
				{
					System.out.println(s);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cities;
	}
}
