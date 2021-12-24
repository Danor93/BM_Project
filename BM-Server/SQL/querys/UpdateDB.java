package querys;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import Entities.BusinessAccountTracking;
import Entities.Dish;
import Entities.DishType;
import Entities.Employer;
import Entities.Order;

public class UpdateDB {

	public static void UpdateOrderAddress(String address) {
		PreparedStatement stmt;
		String query = "";
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("UPDATE order.orders SET OrderAddress = ?");
				stmt.setString(1, address);
				stmt.executeUpdate();

			} else {
				System.out.println("Conn is null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void UpdateTypeOrder(String type) {
		PreparedStatement stmt;
		String query = "";
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("UPDATE order.orders SET TypeOfOrder = ?");
				stmt.setString(1, type);
				stmt.executeUpdate();

			} else {
				System.out.println("Conn is null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void UpdateisLoggedIn(String userName) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("UPDATE bitemedb.users SET isLoggedIn = '0' WHERE userName=?");
				stmt.setString(1, userName);
				stmt.executeUpdate();

			} else {
				System.out.println("Conn is null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean NewDish(Dish dish) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement(
						"INSERT INTO bitemedb.dishes(dishName, dishType, restId1, supplierName, price, inventory, choiceFactor, choiceDetails, ingredients, extra) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				stmt.setString(1, dish.getDishName());
				stmt.setString(2, DishType.fromTypeToStr(dish.getDishType()));
				stmt.setString(3, dish.getRestCode());
				stmt.setString(4, dish.getSupplierName());
				stmt.setString(5, String.valueOf(dish.getPrice()));
				stmt.setString(6, dish.getChoiceFactor());
				stmt.setString(7, dish.getChoiceDetails());
				stmt.setString(8, dish.getIngredients());
				stmt.setString(9, dish.getExtra());
				stmt.executeUpdate();
				return true;

			} else {
				System.out.println("Conn is null");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean UpdateDish(Dish dish) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("UPDATE bitemedb.dishes SET dishName='"
						+ dish.getDishName() + "', dishType='" + dish.getDishType() + "', restId1='"
						+ dish.getRestCode() + "', supplierName='" + dish.getSupplierName() + "', price='"
						+ dish.getPrice() + "''"  + "', choiceFactor='"
						+ dish.getChoiceFactor() + "', choiceDetails='" + dish.getChoiceDetails() + "', ingredients='"
						+ dish.getIngredients() + "', extra='" + dish.getExtra()
						+ "' WHERE dishName=? AND dishType=? AND restId1=?");
				stmt.setString(1, dish.getDishName());
				stmt.setString(2, DishType.fromTypeToStr(dish.getDishType()));
				stmt.setString(3, dish.getRestCode());
				stmt.executeUpdate();
				return true;
			} else {
				System.out.println("Conn is null");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean deleteDish(Dish dish) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement(
						"DELETE FROM bitemedb.dishes WHERE dishName=? AND dishType=? AND restId1=?");
				stmt.setString(1, dish.getDishName());
				stmt.setString(2, DishType.fromTypeToStr(dish.getDishType()));
				stmt.setString(3, dish.getRestCode());
				stmt.executeUpdate();
				return true;
			} else {
				System.out.println("Conn is null");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean updateOrderStatusToNotApproved(ArrayList<Order> arrayList) {
		PreparedStatement stmt;
		int i = 0;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement(
						"UPDATE bitemedb.order SET orderStatus = 'not approved' WHERE orderNumber=?");
				stmt.setString(1, String.valueOf(arrayList.get(i).getOrderNum()));
				i++;
				stmt.executeUpdate();
				return true;

			} else {
				System.out.println("Conn is null");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	public static boolean updateOrderStatusToApproved(ArrayList<Order> arrayList) {
		PreparedStatement stmt;
		int i = 0;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement(
						"UPDATE bitemedb.order SET orderStatus = 'approved' WHERE orderNumber=?");
				stmt.setString(1, String.valueOf(arrayList.get(i).getOrderNum()));
				i++;
				stmt.executeUpdate();
				return true;

			} else {
				System.out.println("Conn is null");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	public static boolean RegistrationOfEmployer(Employer employer) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement(
						"INSERT INTO bitemedb.company(w4cBusiness, companyName, companyStatus) VALUES (?, ?, ?)");
				stmt.setString(1, employer.getW4cBussines());
				stmt.setString(2, employer.getCompanyName());
				stmt.setString(3, employer.getCompanyStatus());
				stmt.executeUpdate();
				return true;

			} else {
				System.out.println("Conn is null");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean BusinessAccountStatusToApproved(ArrayList<BusinessAccountTracking> arrayList) {
		PreparedStatement stmt;
		int i=0;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("UPDATE bitemedb.buss_client SET status = 'approved' WHERE ID=?");
				stmt.setString(1, String.valueOf(arrayList.get(i).getID()));
				i++;
				stmt.executeUpdate();
				return true;

			} else {
				System.out.println("Conn is null");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	public static boolean BusinessAccountStatusToNotApproved(ArrayList<BusinessAccountTracking> arrayList) {
		PreparedStatement stmt;
		int i = 0;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("UPDATE bitemedb.buss_client SET status = 'Not approved' WHERE ID=?");
				stmt.setString(1, String.valueOf(arrayList.get(i).getID()));
				i++;
				stmt.executeUpdate();
				return true;

			} else {
				System.out.println("Conn is null");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

}