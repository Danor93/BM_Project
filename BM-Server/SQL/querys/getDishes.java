package querys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entities.Dish;
import Entities.DishType;
import Entities.Restaurant;
import Entities.homeBranches;

public class getDishes {

	public static ArrayList<Dish> getDishes(String restCode) {
		ArrayList<Dish> dishes=new ArrayList<>();
		PreparedStatement stmt;
		String query = "";
		try {
				stmt = DBConnect.conn.prepareStatement("SELECT dishName,supplierName,price,inventory,dishType FROM bytemedatabase.dishes WHERE restId1=?");
				stmt.setString(1,restCode);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Dish dish=new Dish(rs.getString(1),rs.getString(2),rs.getFloat(3),rs.getInt(4),DishType.toDishType(rs.getString(5)));
					dishes.add(dish);
				}
				rs.close();
		}

		 catch (SQLException e) {
			e.printStackTrace();
		}
		return dishes;
	}
}
