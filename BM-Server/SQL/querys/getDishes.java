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
				stmt = DBConnect.conn.prepareStatement("SELECT dishName,supplierName,choiceFactor,choiceDetails,ingredients,extra,price,inventory,dishType FROM bitemedb.dishes WHERE restId1=?");
				stmt.setString(1,restCode);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Dish dish= new Dish(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6), rs.getFloat(7),rs.getInt(8),DishType.toDishType(rs.getString(9)));
					System.out.println(dish.getDishName());
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
