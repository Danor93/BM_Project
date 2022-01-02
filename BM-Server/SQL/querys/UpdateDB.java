package querys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;

import Entities.BusinessAccountTracking;
import Entities.Dish;
import Entities.DishType;
import Entities.Employer;
import Entities.Order;
import Entities.OrdersReport;
import Entities.RevenueReport;
import Entities.RevenueReport;

public class UpdateDB {

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


	public static boolean addToRevenueReportsTable(RevenueReport rr) {
		boolean ExistingReport = false;
		PreparedStatement stmt;
		Statement stmt2;
		int orderAmount = 0;
		float income = 0;
		try {
			stmt2 = DBConnect.conn.createStatement();
			ResultSet rs = stmt2.executeQuery("SELECT * FROM bitemedb.revenue_reports WHERE month='" + rr.getMonth()
					+ "' AND year ='" + rr.getYear() + "' AND resturant = '" + rr.getResName() + "' ");
			while (rs.next()) {
				ExistingReport = true;
				orderAmount = rs.getInt(5);
				income = rs.getFloat(6);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (ExistingReport == false) {
			try {
				if (DBConnect.conn != null) {

					stmt = DBConnect.conn.prepareStatement(
							"INSERT INTO bitemedb.revenue_reports(branch, month, year, resturant, orders_amount, Income,Quarterly) VALUES (?,?,?, ?, ?, ?,?);");
					stmt.setString(1, rr.getBranch());
					stmt.setString(2, rr.getMonth());
					stmt.setString(3, rr.getYear());
					stmt.setString(4, rr.getResName());
					stmt.setInt(5, rr.getOrdersamount());
					stmt.setFloat(6, rr.getIncome());
					stmt.setString(7, rr.getQuarterly());
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
		} else {
			PreparedStatement stmt3;
			try {
				if (DBConnect.conn != null) {
					orderAmount += rr.getOrdersamount();
					income += rr.getIncome();
					stmt3 = DBConnect.conn.prepareStatement(
							"UPDATE bitemedb.revenue_reports SET orders_amount = ?,Income = ? WHERE month= '"
									+ rr.getMonth() + "' AND year ='" + rr.getYear() + "' AND resturant = '"
									+ rr.getResName() + "' ");
					stmt3.setInt(1, orderAmount);
					stmt3.setFloat(2, income);
					stmt3.executeUpdate();
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
	}

	public static boolean addToOrdersReportsTable(ArrayList<OrdersReport> ordersreports) {
		boolean RetVal = false; 
		PreparedStatement stmt;
		Statement stmt2;
		for (OrdersReport or : ordersreports) {
			int Quantity = 0;
			boolean ExistingReport = false;
			try {
				stmt2 = DBConnect.conn.createStatement();
				ResultSet rs = stmt2.executeQuery("SELECT Quantity FROM bitemedb.orders_report WHERE month='"
						+ or.getMonth() + "' AND year ='" + or.getYear() + "' AND ResName = '" + or.getResName()
						+ "' AND DishType = '" + or.getDishType() + "'");
				while (rs.next()) {
					ExistingReport = true;
					Quantity = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (ExistingReport == false) {
				try {
					if (DBConnect.conn != null) {
						stmt = DBConnect.conn.prepareStatement(
								"INSERT INTO bitemedb.orders_report(month, year, ResName, DishType, branch, Quantity) VALUES (?,?,?, ?, ?, ?);");
						stmt.setString(1, or.getMonth());
						stmt.setString(2, or.getYear());
						stmt.setString(3, or.getResName());
						stmt.setString(4, or.getDishType());
						stmt.setString(5, or.getBranch());
						stmt.setInt(6, or.getQuantity());
						stmt.executeUpdate();
						RetVal = true;
					} else {
						System.out.println("Conn is null");
						RetVal = false;
					}
				} catch (SQLException e) {
					e.printStackTrace();
					RetVal = false;
				}
			} else {
				PreparedStatement stmt3;
				try {
					if (DBConnect.conn != null) {
						Quantity += or.getQuantity();
						stmt3 = DBConnect.conn
								.prepareStatement("UPDATE bitemedb.orders_report SET Quantity = '" + Quantity + "' WHERE month= '"
										+ or.getMonth() + "' AND year ='" + or.getYear() + "' AND ResName = '"
										+ or.getResName() + "' AND DishType= '" + or.getDishType() + "' ;");
						stmt3.executeUpdate();
						Quantity=0;
						RetVal = true;
					} else {
						System.out.println("Conn is null");
						RetVal = false;
					}
				} catch (SQLException e) {
					e.printStackTrace();
					RetVal = false;
				}
			}
		}
		return RetVal; 
	}

	public static ArrayList<RevenueReport> getRevenueReport(String data) {
		String Data[] = data.split("@");
		String branch = Data[0];
		String month = Data[1];
		String year = Data[2];
		Statement stmt;
		ArrayList<RevenueReport> reportarray = new ArrayList<>();
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM bitemedb.revenue_reports WHERE month='" + month
						+ "' AND year ='" + year + "' AND branch = '" + branch + "' ");
				while (rs.next()) {
					RevenueReport report = new RevenueReport(rs.getString(4), branch, month, year, rs.getString(7),
							rs.getInt(5), rs.getFloat(6));
					reportarray.add(report);
				}
				return reportarray;
			} else {
				System.out.println("Conn is null");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<OrdersReport> getOrdersReport(String data) {
		String Data[] = data.split("@");
		String branch = Data[0];
		String month = Data[1];
		String year = Data[2];
		Statement stmt;
		ArrayList<OrdersReport> reportarray = new ArrayList<>();
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM bitemedb.orders_report WHERE month='" + month
						+ "' AND year ='" + year + "' AND branch = '" + branch + "' ");
				while (rs.next()) {
					OrdersReport report = new OrdersReport(month, year, branch, rs.getString(3), rs.getString(4),
							rs.getInt(6));
					reportarray.add(report);
				}
				return reportarray;
			} else {
				System.out.println("Conn is null");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
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
			return false;
		}
	}

	/**
	 * Method for updating dish information in the database
	 * @param dish
	 * @return true / false
	 */
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

	/**
	 * Method for deleting a dish from the database
	 * @param dish
	 * @return true / false
	 */
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

	/**
	 * Method for updating order status to - Not approved
	 * @param order
	 * @return true / false
	 */
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

	/**
	 * Method for updating order status to - Approved
	 * @param order
	 * @return true / false
	 */
	public static boolean updateOrderStatusToApproved(Order order) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("UPDATE bitemedb.order SET orderStatus = 'Approved', timeApproved='"+LocalTime.now().toString()+"' WHERE orderNumber=?");
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

	/**
	 * A method for registering a new employer in the database.
	 * @param employer
	 * @return true / false
	 */
	public static boolean RegistrationOfEmployer(Employer employer) {
		Statement stmt;
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

	/**
	 * Method for updating businessAccount status to Approved
	 * @param businessAccount
	 * @return true / false
	 */
	public static boolean BusinessAccountStatusToApproved(BusinessAccountTracking businessAccount) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("UPDATE bitemedb.buss_client SET status = 'Approved' WHERE ID=?");
				stmt.setString(1, String.valueOf(businessAccount.getID()));
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

	/**
	 * Method for updating businessAccount status to Not approved
	 * @param businessAccount
	 * @return true / false
	 */
	public static boolean BusinessAccountStatusToNotApproved(BusinessAccountTracking businessAccount) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("UPDATE bitemedb.buss_client SET status = 'Not approved' WHERE ID=?");
				stmt.setString(1, String.valueOf(businessAccount.getID()));
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

	/**
	 * Method for updating the Refund value in the database.
	 * @param order
	 * @return true/false
	 */
	public static boolean updateRefundAmmount(Order order) {
		int newAmmount;
		String ammount = null;
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ammount FROM bitemedb.refund WHERE restId='" + order.getRestId()
					+ "' AND ID='" + order.getCostumerId() + "'" + "");
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
						+ "' WHERE restId='" + order.getRestId() + "' AND ID='" + order.getCostumerId() + "'" + "");
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

	/**
	 * Method for updating the Budget value in the database.
	 * @param order
	 * @return true / false
	 */
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

	/**
	 * Method to update order status to - Sended.
	 * @param order
	 * @return true / false
	 */
	public static boolean updateOrderStatusSended(Order order) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("UPDATE bitemedb.order SET orderStatus = 'Sended', timeSended='"+LocalTime.now().toString()+"' WHERE orderNumber=?");
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