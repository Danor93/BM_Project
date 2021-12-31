package querys;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

import com.mysql.cj.xdevapi.DbDoc;

import Entities.BusinessAccountTracking;
import Entities.BussinessAccount;
import Entities.Client;
import Entities.DishType;
import Entities.Employer;
import Entities.MyFile;
import Entities.Order;
import Entities.OrderType;
import Entities.Report;
import Entities.Restaurant;
import Entities.RevenueReport;
import Entities.Supplier;
import Entities.User;
import Entities.homeBranches;
import controllers.ServerUIFController;
import javafx.stage.FileChooser;
import Entities.MessageType;

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
					"SELECT w4cBusiness,companyName,companyStatus FROM company WHERE companyStatus='Not approved' or companyStatus ='Waiting'"
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
				stmt = DBConnect.conn.prepareStatement("UPDATE bitemedb.company SET companyStatus= '" + CompanyStatus
						+ "'" + " WHERE companyName= '" + CompanyName + "'  ;");
				stmt.executeUpdate();
			}
			else {
				System.out.println("Conn is null");
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public static Boolean checkSupplier(Supplier supplier) {
		if (DBConnect.conn != null) {
			try {
				String[] role1 = null;
				String[] role2 = null;
				Statement stmt1 = DBConnect.conn.createStatement();
				ResultSet rs1 = stmt1.executeQuery(
						"SELECT role FROM bitemedb.import_users WHERE id = '" + supplier.getRestId() + "' ;");
				while (rs1.next()) {
					role1 = rs1.getString(1).split("-");
				}
				rs1.close();

				Statement stmt2 = DBConnect.conn.createStatement();
				ResultSet rs2 = stmt2.executeQuery(
						"SELECT role FROM bitemedb.import_users WHERE id = '" + supplier.getConfirm_Employee() + "' ;");
				while (rs2.next()) {
					role2 = rs2.getString(1).split("-");
				}
				rs2.close();

				if (role1[0].equals("Certified") && role1[1].equals(supplier.getSupplierName())
						&& role2[0].equals("Approved") && role2[1].equals(supplier.getSupplierName())) {
					return true;
				} else {
					return false;
				}

			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return null;
	}

	public static void UpdateSupplier(Supplier supplier) {
		try {
			if (DBConnect.conn != null) {
				User Certified_Employee = new User(null, null, null, null, null, null, null, null);
				User Approved_Employee = new User(null, null, null, null, null, null, null, null);
				Statement stmt1 = DBConnect.conn.createStatement();
				ResultSet rs = stmt1.executeQuery(
						"SELECT userName,password,firstName,lastName,Email,phone,role FROM bitemedb.import_users WHERE id = '"
								+ supplier.getRestId() + "' OR id= '" + supplier.getConfirm_Employee()
								+ "' ORDER BY role;");
				while (rs.next()) {
					String[] role = rs.getString(7).split("-");
					if (role[0].equals("Approved")) {
						Approved_Employee.setId(supplier.getConfirm_Employee());
						Approved_Employee.setFirstN(rs.getString(3));
						Approved_Employee.setLastN(rs.getString(4));
						Approved_Employee.setUserName(rs.getString(1));
						Approved_Employee.setPassword(rs.getString(2));
						Approved_Employee.setEmail(rs.getString(5));
						Approved_Employee.setPhone(rs.getString(6));
						Approved_Employee.setRole(rs.getString(7));
					}
					if (role[0].equals("Certified")) {
						Certified_Employee.setId(String.valueOf(supplier.getRestId()));
						Certified_Employee.setFirstN(rs.getString(3));
						Certified_Employee.setLastN(rs.getString(4));
						Certified_Employee.setUserName(rs.getString(1));
						Certified_Employee.setPassword(rs.getString(2));
						Certified_Employee.setEmail(rs.getString(5));
						Certified_Employee.setPhone(rs.getString(6));
						Certified_Employee.setRole(rs.getString(7));
					}
				}
				rs.close();
				PreparedStatement stmt2 = DBConnect.conn.prepareStatement(
						"INSERT INTO bitemedb.users(userName,password,Role,FirstName,LastName,ID,Email,phone,isLoggedIn,homeBranch) VALUES(?,?,?,?,?,?,?,?,?,?)");
				stmt2.setString(1, Approved_Employee.getUserName());
				stmt2.setString(2, Approved_Employee.getPassword());
				stmt2.setString(3, "Supplier-" + Approved_Employee.getRole());
				stmt2.setString(4, Approved_Employee.getFirstN());
				stmt2.setString(5, Approved_Employee.getLastN());
				stmt2.setString(6, Approved_Employee.getId());
				stmt2.setString(7, Approved_Employee.getEmail());
				stmt2.setString(8, Approved_Employee.getPhone());
				stmt2.setString(9, "0");
				stmt2.setString(10, supplier.getHomeBranch().toString());
				stmt2.executeUpdate();

				PreparedStatement stmt3 = DBConnect.conn.prepareStatement(
						"INSERT INTO bitemedb.users(userName,password,Role,FirstName,LastName,ID,Email,phone,isLoggedIn,homeBranch) VALUES(?,?,?,?,?,?,?,?,?,?)");
				stmt3.setString(1, Certified_Employee.getUserName());
				stmt3.setString(2, Certified_Employee.getPassword());
				stmt3.setString(3, "Supplier-" + Certified_Employee.getRole());
				stmt3.setString(4, Certified_Employee.getFirstN());
				stmt3.setString(5, Certified_Employee.getLastN());
				stmt3.setString(6, Certified_Employee.getId());
				stmt3.setString(7, Certified_Employee.getEmail());
				stmt3.setString(8, Certified_Employee.getPhone());
				stmt3.setString(9, "0");
				stmt3.setString(10, supplier.getHomeBranch().toString());
				stmt3.executeUpdate();

				PreparedStatement stmt4 = DBConnect.conn.prepareStatement(
						"INSERT INTO bitemedb.supplier(restId,supplierName,openingTime,city,address,supplierStatus,homeBranch,Confirm_Employee) VALUES(?,?,?,?,?,?,?,?)");
				stmt4.setString(1, String.valueOf(supplier.getRestId()));
				stmt4.setString(2, supplier.getSupplierName());
				stmt4.setString(3, supplier.getOpeningTime());
				stmt4.setString(4, supplier.getCity());
				stmt4.setString(5, supplier.getAddress());
				stmt4.setString(6, "Approved");
				stmt4.setString(7, supplier.getHomeBranch().toString());
				stmt4.setString(8, supplier.getConfirm_Employee());
				stmt4.executeUpdate();
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public static User IDcheck(String ID) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("SELECT * FROM bitemedb.users WHERE ID= '" + ID + "'" + "'  ;");
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

	public static ArrayList<User> getAccount(String Branch) {
		if (DBConnect.conn != null) {
			ArrayList<User> users = new ArrayList<>();
			Statement stmt;
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT * FROM users WHERE Role='Customer' AND homeBranch= '" + Branch + "' ;");
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
		return null;
	}

	public static void DeleteAccount(User user) {
		if (DBConnect.conn != null) {
			try {
				PreparedStatement stmt = DBConnect.conn
						.prepareStatement("DELETE FROM bitemedb.users WHERE ID = '" + user.getId() + "' ;");
				stmt.executeUpdate();

				PreparedStatement stmt2 = DBConnect.conn
						.prepareStatement("DELETE FROM bitemedb.client WHERE client_id = '" + user.getId() + "' ;");
				stmt2.executeUpdate();

				Statement stmt3 = DBConnect.conn.createStatement();
				ResultSet rs = stmt3
						.executeQuery("SELECT ID FROM bitemedb.buss_client WHERE ID= '" + user.getId() + "' ;");
				while (rs.next()) {
					PreparedStatement stmt4 = DBConnect.conn
							.prepareStatement("DELETE FROM bitemedb.buss_client WHERE ID = '" + user.getId() + "' ;");
					stmt4.executeUpdate();
				}
				rs.close();
			} catch (SQLException s) {
				System.out.println(s.getMessage());
				s.printStackTrace();
			}
		}
	}

	public static Boolean checkEmployerStatus(String CompanyName) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT companyStatus FROM bitemedb.company WHERE companyName= '" + CompanyName + "' ;");
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
		return null;
	}

	public static Boolean checkAccountDetails(BussinessAccount Account) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT firstName,lastName,Email,phone FROM bitemedb.import_users WHERE id = '"
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
		return null;
	}

	public static void addNewBAccount(BussinessAccount BAccount) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt1 = DBConnect.conn.createStatement();
				ResultSet rs = stmt1.executeQuery(
						"SELECT userName,password,role FROM import_users WHERE id= '" + BAccount.getId() + "' ;");
				while (rs.next()) {
					String UserName = rs.getString(1);
					String Password = rs.getString(2);
					String Role = rs.getString(3);

					PreparedStatement stmt2 = DBConnect.conn.prepareStatement(
							"INSERT INTO users (userName,password,Role,FirstName,LastName,ID,Email,phone,isLoggedIn,homeBranch)"
									+ "VALUES(?,?,?,?,?,?,?,?,?,?)");
					stmt2.setString(1, BAccount.getId());
					stmt2.setString(2, Password);
					stmt2.setString(3, "Active");
					stmt2.setString(1, UserName);
					stmt2.setString(2, Password);
					stmt2.setString(3, Role);
					stmt2.setString(4, BAccount.getFirstN());
					stmt2.setString(5, BAccount.getLastN());
					stmt2.setString(6, BAccount.getId());
					stmt2.setString(7, BAccount.getEmail());
					stmt2.setString(8, BAccount.getPhone());
					stmt2.setInt(9, 0);
					stmt2.setString(10, BAccount.getBranch().toString());
					stmt2.executeUpdate();

					PreparedStatement stmt3 = DBConnect.conn
							.prepareStatement("INSERT INTO client (client_id,w4c_private,status) VALUES(?,?,?)");
					stmt3.setString(1, BAccount.getId());
					Random rand = new Random(); // instance of random class
					int int_random = rand.nextInt(1000);
					String w4cNew = "P" + String.valueOf(int_random);
					stmt3.setString(2, w4cNew);
					stmt3.setString(3, "Active");
					stmt3.executeUpdate();

					PreparedStatement stmt4 = DBConnect.conn
							.prepareStatement("INSERT INTO buss_client (ID,companyName,budget,status) VALUES(?,?,?,?)");
					stmt4.setString(1, BAccount.getId());
					stmt4.setString(2, BAccount.getCompanyName());
					stmt4.setString(3, BAccount.getBudget());
					stmt4.setString(4, "Waiting");
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
				ResultSet rs = stmt
						.executeQuery("SELECT id FROM bitemedb.import_users WHERE id= '" + client.getId() + "' ;");
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
		return null;
	}

	public static void addNewPAccount(Client PAccount) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT userName,password,role FROM bitemedb.import_users WHERE id= '"
						+ PAccount.getId() + "' ;");
				while (rs.next()) {
					String UserName = rs.getString(1);
					String Password = rs.getString(2);
					String Role = rs.getString(3);

					PreparedStatement stmt2 = DBConnect.conn.prepareStatement(
							"INSERT INTO bitemedb.users (userName,password,Role,FirstName,LastName,ID,Email,phone,isLoggedIn,homeBranch)"
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
					stmt2.setString(10, PAccount.getBranch().toString());
					stmt2.executeUpdate();

					PreparedStatement stmt3 = DBConnect.conn.prepareStatement(
							"INSERT INTO bitemedb.client (client_id,w4c_private,status,CreditCardNumber) VALUES(?,?,?,?)");
					stmt3.setString(1, PAccount.getId());
					Random rand = new Random(); // instance of random class
					int int_random = rand.nextInt(1000);
					String w4cNew = "P" + String.valueOf(int_random);
					stmt3.setString(2, w4cNew);
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

	public static ArrayList<User> GetAccountForFreeze(String Branch) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT * FROM users WHERE homeBranch= '" + Branch + "' AND Role='Customer'");
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

	public static Boolean CheckAccountStatusActive(String AccountID) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT status FROM bitemedb.client WHERE client_id= '" + AccountID + "' ;");
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
		return null;
	}

	public static Boolean CheckAccountStatusFreeze(String AccountID) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT status FROM client WHERE client_id= '" + AccountID + "' ;");
				while (rs.next()) {
					String status = rs.getString(1);
					if (status.equals("Freeze")) {
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
		return null;
	}

	public static void UpdateAccountStatusToActive(String AccountID) {
		if (DBConnect.conn != null) {
			try {
				PreparedStatement stmt = DBConnect.conn
						.prepareStatement("UPDATE client SET status='Active' WHERE client_id= '" + AccountID + "'  ;");
				stmt.executeUpdate();
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}

	public static void UpdateAccountStatusToFreeze(String AccountID) {
		if (DBConnect.conn != null) {
			try {
				PreparedStatement stmt = DBConnect.conn.prepareStatement(
						"UPDATE bitemedb.client SET status='Freeze' WHERE client_id= '" + AccountID + "'  ;");
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
				ResultSet rs = stmt.executeQuery("SELECT quarter,year FROM bitemedb.reports");
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
		return null;
	}

	public static void updateFile(MyFile file) {
		if (DBConnect.conn != null) {
			String sql = "INSERT INTO reports(quarter,year,date_added,file_name,upload_file,homebranch) values(?,?,?,?,?,?)";
			try {
				Timestamp date = new java.sql.Timestamp(new Date().getTime());
				InputStream is = new ByteArrayInputStream(file.getMybytearray());
				PreparedStatement stmt = DBConnect.conn.prepareStatement(sql);
				stmt.setString(1, file.getQuarter());
				stmt.setString(2, file.getYear());
				stmt.setTimestamp(3, date);
				stmt.setString(4, file.getFileName());
				stmt.setBlob(5, is);
				stmt.setString(6, file.getHomebranch().toString());
				stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param branch
	 * @param year
	 * @param quarter Method connects to DB and get the relevant file with match
	 *                parameters and creates a MyFile object with the information
	 * @return relevant MyFile from db
	 */
	public static MyFile downloadFile(String branch, String year, String quarter) {

		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement(
						"SELECT upload_file FROM reports WHERE homebranch=? AND year=? AND quarter=?;");
				stmt.setString(1, branch);
				stmt.setString(2, year);
				stmt.setString(3, quarter);
				ResultSet rs = stmt.executeQuery();
				MyFile downloadFile = new MyFile("");
				while (rs.next()) {
					downloadFile.setHomebranch(homeBranches.toHomeBranchType(branch));
					downloadFile.setQuarter(quarter);
					downloadFile.setYear(year);
					downloadFile.setFileName("Branch-" + branch + "_Year-" + year + "_Quarterly-" + quarter + ".pdf");
					downloadFile.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
					downloadFile
							.setDescription("PDF file, Branch:" + branch + ", Year:" + year + ", Quarterly:" + quarter);
					Blob fileData = rs.getBlob(1);
					if (fileData != null) {
						int len = (int) fileData.length();
						InputStream inputFileData = fileData.getBinaryStream();
						byte[] bytes = new byte[len];
						int bytesRead = 0;
						while (bytesRead != -1)
							try {
								bytesRead = inputFileData.read(bytes);
							} catch (IOException e) {

								e.printStackTrace();
							}
						downloadFile.initArray(len);
						downloadFile.setSize(len);
						downloadFile.setMybytearray(bytes);
					} else {
						System.out.println("no pdf");
					}
				}
				rs.close();
				return downloadFile;
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param branch Method connect to DB and get the relevant year and quarterly
	 *               who match parameter "branch" and creates a ArrayList object
	 *               with the information
	 * @return ArrayList with the match year and quarterly
	 */
	public static ArrayList<String> getRelevantYearsAndQuarterly(String branch) {
		if (DBConnect.conn != null) {
			ArrayList<String> yearsAndQuarter = new ArrayList<>();
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT year,quarter FROM reports WHERE homebranch=?;");
				stmt.setString(1, branch);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					String resYear = rs.getString(1);
					String resQuarter = rs.getString(2);
					yearsAndQuarter.add(resYear + "@" + resQuarter);

				}
				rs.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
			return yearsAndQuarter;

		}
		return null;
	}

	public static ArrayList<Order> LoadOrders(String restName) {
		if (DBConnect.conn != null) {
			ArrayList<Order> orders = new ArrayList<>();
			System.out.println("in server - " + restName);
			Statement stmt;
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT orderType,timeOfOrder,dateOfOrder,orderStatus,costumerID,rstID,totalPrice,orderNumber,usedRefund,usedBudget,EarlyOrder FROM bitemedb.order WHERE restName='"
								+ restName + "' AND orderStatus ='Waiting' OR orderStatus='Approved'" + "");
				while (rs.next()) {
					Order order = new Order(rs.getString(1), restName, rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6), Float.parseFloat(rs.getString(7)));
					order.setOrderNum(Integer.parseInt(rs.getString(8)));
					order.setUseRefund(rs.getString(9));
					order.setUseBudget(Integer.parseInt(rs.getString(10)));
					order.setEarlyOrder(rs.getString(11));
					orders.add(order);
				}
				rs.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
			return orders;
		}
		return null;
	}

	public static ArrayList<BusinessAccountTracking> LoadBusinessAccountDetails(String companyName) {
		if (DBConnect.conn != null) {
			ArrayList<BusinessAccountTracking> businessAccountTracking = new ArrayList<>();
			Statement stmt;
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT ID,companyName,budget FROM bitemedb.buss_client WHERE companyName='"
								+ companyName + "' AND status ='Waiting' ORDER BY companyName" + "");
				while (rs.next()) {
					BusinessAccountTracking BAT = new BusinessAccountTracking(rs.getString(1), rs.getString(2),
							rs.getString(3));
					BAT.setStatus("Waiting");

					businessAccountTracking.add(BAT);
				}
				rs.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
			return businessAccountTracking;
		}
		return null;
	}

	public static ArrayList<String> LoadW4CBusiness() {
		if (DBConnect.conn != null) {
			ArrayList<String> w4cBusiness = new ArrayList<>();
			Statement stmt;
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT w4cBusiness FROM bitemedb.company" + "");
				while (rs.next()) {
					w4cBusiness.add(rs.getString(1));
				}
				rs.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
			return w4cBusiness;
		}
		return null;
	}

	public static Boolean LoadCompanyStatus(StringBuilder companyName) {
		if (DBConnect.conn != null) {
			Statement stmt;
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT companyStatus FROM bitemedb.company WHERE companyName='"
						+ companyName + "' AND companyStatus='Approved' ORDER BY companyName" + "");
				while (rs.next()) {
					return true;
				}
				rs.close();
			} catch (SQLException s) {
				s.printStackTrace();
				return false;
			}
			return false;
		}
		return null;
	}

	public static String LoadPhoneNumber(Order order) {
		if (DBConnect.conn != null) {
			Statement stmt;
			String phoneNumber = null;
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT phone FROM bitemedb.users WHERE ID='" + order.getCostumerId() + "'" + "");
				while (rs.next()) {
					phoneNumber = rs.getString(1);
				}
				rs.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
			return phoneNumber;
		}
		return null;
	}

	public static HashMap<String, Integer> getQuntitiesOfDishTypes(int id) {
		if (DBConnect.conn != null) {
			Statement stmt;
			HashMap<String, Integer> retMap = new HashMap<>();
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT dishType,quentity FROM bitemedb.dishesinorder WHERE orderNum='" + id + "'" + "");
				while (rs.next()) {
					int quentity = rs.getInt(2);
					String dishType = rs.getString(1);
					if (retMap.containsKey(dishType)) {
						quentity += retMap.get(dishType);
					}
					retMap.put(dishType, quentity);

				}
				rs.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
			return retMap;
		}
		return null;
	}
}