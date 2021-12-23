package querys;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Entities.BusinessAccountTracking;
import Entities.BussinessAccount;
import Entities.Client;
import Entities.Employer;
import Entities.MyFile;
import Entities.Order;
import Entities.OrderType;
import Entities.Supplier;
import Entities.User;
import Entities.homeBranches;
import controllers.ServerUIFController;
import javafx.stage.FileChooser;

public class Query {

	/*
	 * importData this method import the database script
	 */
	public static void importData() {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SQL", "*.sql"));
		File file = fileChooser.showOpenDialog(ServerUIFController.serveruifconroller.stage);
		String path;
		if (file != null) {
			path = file.getPath();
			if (DBConnect.conn == null) {
				ServerUIFController.serveruifconroller
						.message("First insert user and password,press Start and then import", "Error");
			} else {
				Connection connection = DBConnect.conn;
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

	/**
	 * readtScript this method get script and executed it into database
	 * 
	 * @param conn - the connection to db
	 * @param in   - the script file
	 */
	public static void readtScript(Connection conn, InputStream in) throws SQLException {
		Scanner s = new Scanner(in);
		s.useDelimiter("(;(\r)?\n)|(--\n)");
		Statement st = null;
		try {
			st = conn.createStatement();
			while (s.hasNext()) {
				String line = s.next();
				if (line.startsWith("/*!") && line.endsWith("*/")) {
					int i = line.indexOf(' ');
					line = line.substring(i + 1, line.length() - " */".length());
				}

				if (line.trim().length() > 0) {
					st.execute(line);
				}
			}
		} finally {
			if (st != null)
				st.close();
		}
	}

	/*
	 * Author:Danor this method is for a Execute a Query which return the company
	 * that not approved or waiting for approved by the Branch Manager.
	 */
	public static ArrayList<Employer> LoadEmployers() {
		ArrayList<Employer> employers = new ArrayList<>();
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT w4cBusiness,companyName,companyStatus FROM company WHERE companyStatus='not approved' or companyStatus ='waiting'"
							+ "");
			while (rs.next()) {
				Employer employer = new Employer(rs.getString(1), rs.getString(2), rs.getString(3));
				employers.add(employer);
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return employers;
	}

	/*
	 * Author:Danor this method Execute an Update for the companyStatus on the
	 * company table
	 */
	public static void UpdateEmployers(String CompanyName, String CompanyStatus) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("UPDATE company SET companyStatus= '" + CompanyStatus + "'"
						+ " WHERE companyName= '" + CompanyName + "'  ;");
				stmt.executeUpdate();
			}

			else {
				System.out.println("Conn is null");
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public static ArrayList<Supplier> LoadSuppliers() {
		ArrayList<Supplier> suppliers = new ArrayList<>();
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM supplier WHERE supplierStatus='not approved' or supplierStatus ='waiting'" + "");
			while (rs.next()) {
				Supplier supplier = new Supplier(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), homeBranches.toHomeBranchType(rs.getString(7)));
				suppliers.add(supplier);
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return suppliers;
	}

	public static void UpdateSupplier(String SupplierName, String SupplierStatus) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("UPDATE supplier SET supplierStatus= '" + SupplierStatus + "'"
						+ " WHERE supplierName= '" + SupplierName + "'  ;");
				stmt.executeUpdate();
			} else {
				System.out.println("Conn is null");
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public static User IDcheck(String ID) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("SELECT * FROM users WHERE ID= '" + ID + "'" + "'  ;");
				ResultSet rs = stmt.executeQuery();
				if (rs != null) {
					User user = new User(rs.getString(3), rs.getString(6), rs.getString(4), rs.getString(5),
							homeBranches.toHomeBranchType(rs.getString(10)), rs.getString(1), rs.getString(2),
							rs.getString(9));
					user.setEmail(rs.getString(7));
					user.setPhone(rs.getString(8));
					rs.close();
					return user;
				} else {
					return null;
				}
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return null;
	}

	public static ArrayList<User> getAccount() {
		ArrayList<User> users = new ArrayList<>();
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users");
			while (rs.next()) {
				User user = new User(rs.getString(3), rs.getString(6), rs.getString(4), rs.getString(5),
						homeBranches.toHomeBranchType(rs.getString(10)), rs.getString(1), rs.getString(2),
						rs.getString(9));
				user.setEmail(rs.getString(7));
				user.setPhone(rs.getString(8));
				users.add(user);
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return users;
	}

	public static void DeleteAccount(User user) {
		if (DBConnect.conn != null) {
			try {
				PreparedStatement stmt = DBConnect.conn
						.prepareStatement("DELETE FROM users WHERE ID = '" + user.getId() + "' ;");
				stmt.executeUpdate();
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}

	public static Boolean checkEmployerStatus(String CompanyName) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT companyStatus FROM company WHERE companyName= '" + CompanyName + "' ;");
				while (rs.next()) {
					String status = rs.getString(1);
					if (status.equals("approved")) {
						return true;
					} else {
						return false;
					}
				}
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return false;
	}

	public static Boolean checkAccountDetails(BussinessAccount Account) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT firstName,lastName,Email,phone FROM import_users WHERE id = '"
						+ Account.getId() + "' ;");
				while (rs.next()) {
					String FirstName = rs.getString(1);
					String LastName = rs.getString(2);
					String Email = rs.getString(3);
					String Phone = rs.getString(4);
					if ((!FirstName.equals(Account.getFirstN())) || (!LastName.equals(Account.getLastN()))
							|| (!Email.equals(Account.getEmail())) || (!Phone.equals(Account.getPhone()))) {
						rs.close();
						return false;
					} else {
						rs.close();
						return true;
					}
				}
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return false;
	}

	public static void addNewBAccount(BussinessAccount BAccount) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT userName,password,role FROM import_users WHERE id= '" + BAccount.getId() + "' ;");
				while (rs.next()) {
					String UserName = rs.getString(1);
					String Password = rs.getString(2);
					String Role = rs.getString(3);
				PreparedStatement stmt2 = DBConnect.conn.prepareStatement(
						"INSERT INTO users (userName,password,Role,FirstName,LastName,ID,Email,phone,isLoggedIn,homeBranch)"
								+ "VALUES(?,?,?,?,?,?,?,?,?,?)");
				stmt2.setString(1, UserName);
				stmt2.setString(2, Password);
				stmt2.setString(3, Role);
				stmt2.setString(4, BAccount.getFirstN());
				stmt2.setString(5, BAccount.getLastN());
				stmt2.setString(6, BAccount.getId());
				stmt2.setString(7, BAccount.getEmail());
				stmt2.setString(8, BAccount.getPhone());
				stmt2.setInt(9, 0);
				stmt2.setString(10,BAccount.getBranch().toString());
				stmt2.executeUpdate();

				PreparedStatement stmt3 = DBConnect.conn
						.prepareStatement("INSERT INTO client (client_id,w4c_private,status) VALUES(?,?,?)");
				stmt3.setString(1, BAccount.getId());
				stmt3.setString(2, "456");
				stmt3.setString(3, "Active");
				stmt3.executeUpdate();

				PreparedStatement stmt4 = DBConnect.conn
						.prepareStatement("INSERT INTO buss_client (ID,companyName,budget,status) VALUES(?,?,?,?)");
				stmt4.setString(1, BAccount.getId());
				stmt4.setString(2, BAccount.getCompanyName());
				stmt4.setString(3, BAccount.getBudget());
				stmt4.setString(4,"Waiting");
				stmt4.executeUpdate();
				}
				rs.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}

	public static Boolean checkPrivateAccount(Client client) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT id FROM import_users WHERE id= '" + client.getId() + "' ;");
				while (rs.next()) {
					String FirstName = rs.getString(1);
					String LastName = rs.getString(2);
					String Email = rs.getString(3);
					String Phone = rs.getString(4);
					if ((!FirstName.equals(client.getFirstN())) || (!LastName.equals(client.getLastN()))
							|| (!Email.equals(client.getEmail())) || (!Phone.equals(client.getPhone()))) {
						rs.close();
						return false;
					} else {
						rs.close();
						return true;
					}
				}
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return false;
	}

	public static void addNewPAccount(Client PAccount) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT userName,password,role FROM import_users WHERE id= '" + PAccount.getId() + "' ;");
				while (rs.next()) {
					String UserName = rs.getString(1);
					String Password = rs.getString(2);
					String Role = rs.getString(3);

					PreparedStatement stmt2 = DBConnect.conn.prepareStatement(
							"INSERT INTO users (userName,password,Role,FirstName,LastName,ID,Email,phone,isLoggedIn,homeBranch)"
									+ "VALUES(?,?,?,?,?,?,?,?,?,?)");
					stmt2.setString(1, UserName);
					stmt2.setString(2, Password);
					stmt2.setString(3, Role);
					stmt2.setString(4, PAccount.getFirstN());
					stmt2.setString(5, PAccount.getLastN());
					stmt2.setString(6, PAccount.getId());
					stmt2.setString(7, PAccount.getEmail());
					stmt2.setString(8, PAccount.getPhone());
					stmt2.setInt(9, 0);
					stmt2.setString(10,PAccount.getHomeBranch().toString());
					stmt2.executeUpdate();

					PreparedStatement stmt3 = DBConnect.conn.prepareStatement(
							"INSERT INTO client (client_id,w4c_private,status,CreditCardNumber) VALUES(?,?,?,?)");
					stmt3.setString(1, PAccount.getId());
					stmt3.setString(2, "789");
					stmt3.setString(3, "Active");
					stmt3.setString(4, PAccount.getCreditCardNumber());
					stmt3.executeUpdate();
				}
				rs.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}

	public static ArrayList<User> GetAccountForFreeze() {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE Role='Customer' ");
				ArrayList<User> users = new ArrayList<>();
				while (rs.next()) {
					User user = new User(rs.getString(3), rs.getString(6), rs.getString(4), rs.getString(5),
							homeBranches.toHomeBranchType(rs.getString(10)), rs.getString(1), rs.getString(2),
							rs.getString(9));
					user.setEmail(rs.getString(7));
					user.setPhone(rs.getString(8));
					users.add(user);
				}
				rs.close();
				return users;
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return null;
	}

	public static Boolean CheckAccountStatus(String AccountID) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT status FROM client WHERE client_id= '" + AccountID + "' ;");
				while (rs.next()) {
					String status = rs.getString(1);
					if (status.equals("Active")) {
						rs.close();
						return true;
					} else {
						rs.close();
						return false;
					}
				}
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return false;
	}

	public static void UpdateAccountStatusToFreeze(String AccountID) {
		if (DBConnect.conn != null) {
			try {
				PreparedStatement stmt = DBConnect.conn
						.prepareStatement("UPDATE client SET status='Freeze' WHERE client_id= '" + AccountID + "'  ;");
				stmt.executeUpdate();
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}

	public static Boolean checkYearAndQuarter(String quarter, String year) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT quarter,year FROM reports");
				while (rs.next()) {
					String quarter2 = rs.getString(1);
					String year2 = rs.getString(2);
					if ((year.equals(year2)) && (quarter.equals(quarter2))) {
						rs.close();
						return false;
					}
				}
				rs.close();
				return true;
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return false;
	}

	public static void updateFile(MyFile file) {
		String sql = "INSERT INTO reports(quarter,year,date_added,file_name,upload_file) values(?,?,?,?,?)";
		try {
			Timestamp date = new java.sql.Timestamp(new Date().getTime());
			InputStream is = new ByteArrayInputStream(file.getMybytearray());
			PreparedStatement stmt = DBConnect.conn.prepareStatement(sql);
			stmt.setString(1, file.getQuertar());
			stmt.setString(2, file.getYear());
			stmt.setTimestamp(3, date);
			stmt.setString(4, file.getFileName());
			stmt.setBlob(5, is);
			// stmt.setString(6,file.getHomebranch());//fix.
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Order> LoadOrders() {
		ArrayList<Order> orders = new ArrayList<>();
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT orderType,restName,timeOfOrder,dateOfOrder,orderStatus,costumerID,rstID,totalPrice,orderNumber FROM bytemedatabase.order WHERE orderStatus ='waiting'"
							+ "");
			while (rs.next()) {
				Order order = new Order(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
						Float.parseFloat(rs.getString(8)));
				order.setOrderNum(Integer.parseInt(rs.getString(9)));
				orders.add(order);
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return orders;
	}

	public static ArrayList<BusinessAccountTracking> LoadBusinessAccountDetails() {
		ArrayList<BusinessAccountTracking> businessAccountTracking = new ArrayList<>();
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT ID,companyName,budget FROM buss_client WHERE status ='waiting'" + "");
			while (rs.next()) {
				BusinessAccountTracking BAT = new BusinessAccountTracking(rs.getString(1), rs.getString(2),
						rs.getString(3));
				BAT.setStatus("waiting");

				businessAccountTracking.add(BAT);
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return businessAccountTracking;
	}
}