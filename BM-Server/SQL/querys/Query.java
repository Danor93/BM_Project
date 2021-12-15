package querys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import Entities.Employer;
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
					"SELECT w4cBusiness,companyName,companyStatus FROM bytemedatabase.company WHERE companyStatus='not approved' or companyStatus ='waiting'"
							+ "");
			while (rs.next()) {
				Employer employer = new Employer(rs.getString(1), rs.getString(2), rs.getString(3));
				employers.add(employer);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
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
		} catch (SQLException e) {
			e.printStackTrace();
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
		} catch (SQLException e) {
			e.printStackTrace();
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
		} catch (SQLException e) {
			e.printStackTrace();
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
							rs.getString(10), homeBranches.toHomeBranchType(rs.getString(11)), rs.getString(1),
							rs.getString(2), rs.getString(9));
					user.setEmail(rs.getString(7));
					user.setPhone(rs.getString(8));
					rs.close();
					return user;
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
						rs.getString(10), homeBranches.toHomeBranchType(rs.getString(11)), rs.getString(1),
						rs.getString(2), rs.getString(9));
				user.setEmail(rs.getString(7));
				user.setPhone(rs.getString(8));
				users.add(user);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public static void DeleteAccount(User user) {
		Statement stmt;
		try {
			stmt = DBConnect.conn.prepareStatement("DELETE FROM users WHERE ID= '" + user.getId() + "' ;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
