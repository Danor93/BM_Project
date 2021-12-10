package querys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entities.Order;
import Entities.OrderType;
import Entities.Restaurant;
import Entities.homeBranches;

public class showRestaurants {
	public static ArrayList<Restaurant> getRestaurants(String city){
		ArrayList<Restaurant> rest=new ArrayList<>();
		PreparedStatement stmt;
		String query = "";
		try {
				stmt = DBConnect.conn.prepareStatement("SELECT restId,supplierName,openingTime,city,address,homeBranch FROM bytemedatabase.supplier WHERE city=? and supplierStatus=?");
				stmt.setString(1,city);
				stmt.setString(2,"approved");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Restaurant rst=new Restaurant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),homeBranches.toHomeBranchType(rs.getString(6)));
					rest.add(rst);
				}
				rs.close();
		}

		 catch (SQLException e) {
			e.printStackTrace();
		}
		return rest;
	}

}
