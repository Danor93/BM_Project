package querys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.text.html.parser.Entity;

import Entities.Dish;
import Entities.DishType;
import Entities.Order;
import Entities.OrderType;
import Entities.User;

public class ShowDishes {
	public static ArrayList<Dish> getDishes(String ID) {
		ArrayList<Dish> dishes = new ArrayList<>();
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("SELECT * FROM bitemedb.dishes WHERE restId1=? ORDER BY dishType");
				stmt.setString(1, ID);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Dish dish = new Dish(rs.getString("dishName"), rs.getString("supplierName"),
							rs.getString("choiceFactor"), rs.getString("choiceDetails"), rs.getString("ingredients"),
							rs.getString("extra"), Float.parseFloat(rs.getString("price")),
							DishType.toDishType(rs.getString("dishType")));
					dish.setRestCode(rs.getString("restId1"));
					dishes.add(dish);
				}
				rs.close();
			} else {
				System.out.println("Conn is null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dishes;
	}
}