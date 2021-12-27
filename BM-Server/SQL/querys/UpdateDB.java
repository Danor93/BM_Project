package querys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
				stmt = DBConnect.conn.prepareStatement("UPDATE bitemedb.users SET isLoggedIn = '0' WHERE userName=?");
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
						"INSERT INTO bitemedb.dishes(dishName, dishType, restId1, supplierName, price, choiceFactor, choiceDetails, ingredients, extra) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
				stmt = DBConnect.conn.prepareStatement("UPDATE bitemedb.dishes SET price='" + dish.getPrice()
						+ "', choiceFactor='" + dish.getChoiceFactor() + "', choiceDetails='" + dish.getChoiceDetails()
						+ "', ingredients='" + dish.getIngredients() + "', extra='" + dish.getExtra()
						+ "' WHERE dishName=? AND dishType=? AND restId1=?");
				stmt.setString(1, dish.getDishName());
				stmt.setString(2, DishType.fromTypeToStr(dish.getDishType()));
				System.out.println(DishType.fromTypeToStr(dish.getDishType()));
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
				stmt = DBConnect.conn
						.prepareStatement("DELETE FROM bitemedb.dishes WHERE dishName=? AND dishType=? AND restId1=?");
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

	public static boolean updateOrderStatusToNotApproved(Order order) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("UPDATE bitemedb.order SET orderStatus = 'Not approved' WHERE orderNumber=?");
				stmt.setString(1, String.valueOf(order.getOrderNum()));
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

	public static boolean updateOrderStatusToApproved(Order order) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("UPDATE bitemedb.order SET orderStatus = 'Approved' WHERE orderNumber=?");
				stmt.setString(1, String.valueOf(order.getOrderNum()));
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
		Statement stmt;
		ArrayList<String> compantStatus = new ArrayList<>();
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT companyStatus FROM bitemedb.company WHERE companyName='"
					+ employer.getCompanyName() + "'" + "");
			while (rs.next()) {
				return false;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PreparedStatement stmt2;
		try {
			if (DBConnect.conn != null) {
				stmt2 = DBConnect.conn.prepareStatement(
						"INSERT INTO bitemedb.company(w4cBusiness, companyName, companyStatus) VALUES (?, ?, ?)");
				stmt2.setString(1, employer.getW4cBussines());
				stmt2.setString(2, employer.getCompanyName());
				stmt2.setString(3, employer.getCompanyStatus());
				stmt2.executeUpdate();
				return true;

			} else {
				System.out.println("Conn is null");
				return false;
			}
		} catch (SQLException s) {
			s.printStackTrace();
			return false;
		}
	}

	public static boolean BusinessAccountStatusToApproved(ArrayList<BusinessAccountTracking> arrayList) {
		PreparedStatement stmt;
		int i = 0;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("UPDATE bitemedb.buss_client SET status = 'Approved' WHERE ID=?");
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

	public static boolean updateRefundAmmount(Order order) {
		int newAmmount;
		String ammount=null;
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ammount FROM bitemedb.refund WHERE restId='"
					+ order.getRestId() + "' AND ID='" + order.getCostumerId() + "'" + "");
			while (rs.next()) {
				ammount = rs.getString(1);
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}

		newAmmount = Integer.parseInt(ammount) - Integer.parseInt(order.getUseRefund());
		if (newAmmount < 0)
			newAmmount = 0;

		PreparedStatement stmt2;
		try {
			if (DBConnect.conn != null) {
				stmt2 = DBConnect.conn.prepareStatement("UPDATE bitemedb.refund SET ammount='" + newAmmount
						+ "' WHERE restId='" + order.getRestId() + "' AND ID='"
						+ order.getCostumerId() + "'" + "");
				stmt2.executeUpdate();
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

	public static boolean updateBudgetValue(Order order) {
		Float newBudget;
		String budget = null;
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT budget FROM bitemedb.buss_client WHERE ID='"
					+ order.getCostumerId() + "' AND status='Approved'" + "");
			while (rs.next()) {
				budget = rs.getString(1);
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}

		newBudget = Float.parseFloat(budget) - order.getTotalPrice();

		if (newBudget.compare(newBudget, 0) < 0)
			newBudget = Float.parseFloat("0");

		PreparedStatement stmt2;
		try {
			if (DBConnect.conn != null) {
				stmt2 = DBConnect.conn.prepareStatement("UPDATE bitemedb.buss_client SET budget='" + newBudget
						+ "' WHERE ID='" + order.getCostumerId() + "' AND status='Approved'" + "");
				stmt2.executeUpdate();
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

	public static boolean updateOrderStatusSended(Order order) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("UPDATE bitemedb.order SET orderStatus = 'Sended' WHERE orderNumber=?");
				stmt.setString(1, String.valueOf(order.getOrderNum()));
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