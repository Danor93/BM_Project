package querys;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import Entities.BusinessAccountTracking;
import Entities.BussinessAccount;
import Entities.Client;
import Entities.Delivery;
import Entities.Dish;
import Entities.DishType;
import Entities.Employer;
import Entities.MyFile;
import Entities.Order;
import Entities.OrderType;
import Entities.OrdersReport;
import Entities.PerformanceReport;
import Entities.Receipt;
import Entities.Restaurant;
import Entities.RevenueReport;
import Entities.Supplier;
import Entities.User;
import Entities.homeBranches;
import controllers.ServerUIFController;
import javafx.stage.FileChooser;

/**
 * @author Danor
 * @author Sahar
 * @author Aviel
 * @author Lior
 * @author Adi
 * @author Talia 
 * This class handles all queries the server needs to perform.
 */
public class Query {

	/*
	 * importData this method import the database script.
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
	 * readtScript this method get script and executed it into database.
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
	
	
	/**
	 * this query is for login to the system,getting the user information,change to '1' in islogin and check if the account is freeze or active.
	 * @param userName - for the logging
	 * @param password - for the logging
	 * @return - return the user information from the DB.
	 */
	public static String Login(String userName, String password) {
		StringBuilder result = new StringBuilder();
		String role = null,ID=null,status = null;
		PreparedStatement stmt,stmt1;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("SELECT Role,ID,FirstName,LastName,homeBranch,userName,password,isLoggedIn FROM bitemedb.users WHERE userName=? AND password=?");
				stmt.setString(1, userName);
				stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if((rs.getString(8)).equals("1")){
						return "Already";
					}
					role=rs.getNString(1);
					result.append(rs.getString(1));
					result.append("@");
					ID=rs.getString(2);
					result.append(rs.getString(2));
					result.append("@");
					result.append(rs.getString(3));
					result.append("@");
					result.append(rs.getString(4));
					result.append("@");
					result.append(rs.getString(5));
					result.append("@");
					result.append(rs.getString(6));
					result.append("@");
					result.append(rs.getString(7));
					result.append("@");
					result.append(rs.getString(8));
				}
				rs.close();
				if (result.length() == 0) {
					return "WrongInput";
				}
				if(role.equals("Customer"))
				{
					stmt1 = DBConnect.conn.prepareStatement("SELECT status FROM bitemedb.client WHERE client_id=?");
					stmt1.setString(1, ID);
					ResultSet rs1 = stmt1.executeQuery();
					while(rs1.next())
					{
						status=rs1.getString(1);
					}
					rs1.close();
					if(status.equals("Freeze"))
					{
						return "Freeze";
					}
				}
				stmt = DBConnect.conn.prepareStatement("UPDATE bitemedb.users SET isLoggedIn='1' WHERE userName=? AND password=?");
				stmt.setString(1, userName);
				stmt.setString(2, password);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	/**
	 * Method to check if there a menu exit or not
	 * @param ID
	 * @return true or false
	 */
	public static Boolean MenuExistingCheck(String ID) {
		String rs1 = null;
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("SELECT dishName FROM bitemedb.dishes WHERE restId1=?");
				stmt.setString(1, ID);
				ResultSet rs = stmt.executeQuery();
				while (rs.next())
					rs1 = rs.getString(1).toString();
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(rs1==null)
			return false;
		else
			return true;
	}
	
	public static ArrayList<String> w4cBusinessGet() {
		ArrayList<String> rs1 = new ArrayList<String>();
		Statement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT w4cBusiness FROM bitemedb.company");
				while (rs.next()) {
					rs1.add(rs.getString(1).toString());
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs1;
	}
	

	
	
	/**This method meant to get all of the cities from the DB 
	 * @return       ArrayList "cities" or null if there's no city in the DB
	 */
	public static ArrayList<String> getCities() {
		ArrayList<String> cities = new ArrayList<>();
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT DISTINCT city FROM bitemedb.supplier");
			while (rs.next()) {
				cities.add(rs.getString(1));
			}
			rs.close();
			for (String s : cities) {
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cities;
	}



	/**This method meant to check if the customer that entered the system is a business client and if he does- 
	 * gets the budget and the company name and code from the DB
	 * @param id         The client's ID
	 * @return
	 */
	public static Client checkAccountKind(String id)
	{
		Client client=null;
		PreparedStatement stmt1,stmt2,stmt3;
		String w4c=null;
		try {
			stmt1 = DBConnect.conn.prepareStatement("SELECT w4c_private FROM bitemedb.client WHERE client_id=?");
			stmt1.setString(1,id);
			ResultSet rs = stmt1.executeQuery();
			while (rs.next()) {
				w4c=rs.getString(1);
			}
			rs.close();
			stmt2 = DBConnect.conn.prepareStatement("SELECT companyName,budget FROM bitemedb.buss_client WHERE ID=? and status=?");
			stmt2.setString(1,id);
			stmt2.setString(2,"Approved");
			ResultSet rs2 = stmt2.executeQuery();
			while (rs2.next()) {
				client = new BussinessAccount(rs2.getString(1),w4c,rs2.getString(2));
			}
			rs2.close();
			if(client instanceof BussinessAccount)
			{
				BussinessAccount bussinessAccount=(BussinessAccount)client;
				stmt3 = DBConnect.conn.prepareStatement("SELECT w4cBusiness FROM bitemedb.company WHERE companyName=?");
				stmt3.setString(1,bussinessAccount.getCompanyName());
				ResultSet rs3 = stmt3.executeQuery();
				while (rs3.next()) {
					bussinessAccount.setEmployerCode(rs3.getString(1));
				}
				rs3.close();
			}
			else
			{
				client=new Client(w4c);
			}
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}


	/**This method gets the amount of the refund for the specific customer and restaurant-
	 * if there's no refund-returns null
	 * @param order         the order entity of the customer
	 * @return              the amount of the refund/null
	 */
	public static String getRefundSum(Order order) {
		PreparedStatement stmt;
		String ammount = null;
		try {
			stmt = DBConnect.conn.prepareStatement("SELECT ammount FROM bitemedb.refund WHERE ID=? and restId=?");
			stmt.setString(1, order.getCostumerId());
			stmt.setString(2, order.getRestId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ammount = rs.getString(1);
			}
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return ammount;
	}

	/**The method inserts the order of the customer to the order's table in the DB 
	 * @param msg       the order's entity
	 * @return          the order AI number from the DB
	 */
	public static Integer insertOrder(Order msg) {
		Integer orderNum = null;
		PreparedStatement stmt, stmt1;
		try {
			stmt = DBConnect.conn.prepareStatement("INSERT INTO bitemedb.order VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, null);
			stmt.setString(2, msg.getOrderType());
			stmt.setString(3, msg.getRestName());
			stmt.setString(4, msg.getRestId());
			stmt.setString(5, Float.toString(msg.getTotalPrice()));
			stmt.setString(6, msg.getTimeOfOrder());
			stmt.setString(7, msg.getDateOfOrder());
			stmt.setString(8, msg.getOrderStatus());
			stmt.setString(9, msg.getCostumerId());
			stmt.setString(10, msg.getUseRefund());
			stmt.setInt(11, msg.getUseBudget());
			stmt.setString(12, msg.getEarlyOrder());
			stmt.setString(13, null);
			stmt.setInt(14, 0);
			stmt.setString(15, null);
			
			stmt.executeUpdate();
			stmt1 = DBConnect.conn.prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet rs = stmt1.executeQuery();
			while (rs.next()) {
				orderNum = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderNum;
	}

	/**This method inserts the dishes that the customer chose in his order into dishesinorder table in the DB
	 * @param message           ArrayList of dishes
	 * @return                  success/null
	 */
	public static String insertDishesOrder(ArrayList<Dish> message) {
		PreparedStatement stmt;
		int orderNumber = message.get(0).getOrderNumber();
		try {
			for (Dish dish : message) {
				stmt = DBConnect.conn
						.prepareStatement("INSERT INTO bitemedb.dishesinorder VALUES(?,?,?,?,?,?,?,?,?)");
				stmt.setString(1, null);
				stmt.setInt(2, orderNumber);
				stmt.setString(3, dish.getDishName());
				stmt.setString(4, dish.getRestCode());
				stmt.setString(5, DishType.fromTypeToStr(dish.getDishType()));
				stmt.setString(6, dish.getChoiceFactor());
				stmt.setString(7, dish.getChoiceDetails());
				stmt.setString(8, dish.getExtra());
				stmt.setInt(9, dish.getQuentity());
				stmt.executeUpdate();
			}
			return "success!";
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/**This method inserts the delivery details that the customer entered for the order he made
	 * @param message         delivery entity
	 * @return                success/null
	 */
	public static String insertDelivery(Delivery message) {
		PreparedStatement stmt;
		try
		{
			stmt = DBConnect.conn.prepareStatement("INSERT INTO bitemedb.delivery VALUES(?,?,?,?,?,?,?)");
			stmt.setInt(1, message.getOrderNum());
			stmt.setString(2, message.getDeliveryType());
			stmt.setInt(3, message.getParticipantsNum());
			stmt.setString(4, message.getAddress());
			stmt.setString(5, message.getPhone());
			stmt.setString(6,message.getRecipient());
			stmt.setString(7, Float.toString(message.getDeliPrice()));
			stmt.executeUpdate();
			return "success!";
		}
	 catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
	}

	/**This method selects all of the orders for the specific customer that have the status sent in the DB
	 * @param msg        the costumer's ID
	 * @return           ArrayList of orders
	 */
	public static ArrayList<Order> ConfirmClient(String msg) {
		PreparedStatement stmt,stm2;
		ArrayList<Order> orders=new ArrayList<>();
		try {
			stmt = DBConnect.conn.prepareStatement("SELECT orderNumber,restName,timeOfOrder,dateOfOrder,EarlyOrder,rstID,totalPrice,timeApproved FROM bitemedb.order WHERE costumerID=? and orderStatus=?");
			stmt.setString(1,msg);
			stmt.setString(2,"Sended");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Order order =new Order (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				order.setRestId(Integer.toString(rs.getInt(6)));
				order.setTotalPrice(Float.parseFloat(rs.getString(7)));
				order.setSuppApproved(rs.getString(8));
				orders.add(order);
			}
			rs.close();
			for(Order ord:orders)
			{
				StringBuilder b=new StringBuilder();
				stm2 = DBConnect.conn.prepareStatement("SELECT dishName FROM bitemedb.dishesinorder WHERE orderNum=?");
				stm2.setInt(1, ord.getOrderNum());
				ResultSet rs1=stm2.executeQuery();
				while(rs1.next())
				{
					b.append(rs1.getString(1));
					b.append(",");
				}
				ord.setDishes(b.toString());
				rs1.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	/**The method checks if the costumer deserves to get a refund due to delays of order supply
	 * and also updates the refund that the customer deserves in the refund table- if he has no refund for
	 *  the specific restaurant it will create one, otherwise-calculate the new amount of the refund
	 * @param msg          entity of order
	 * @return             "ok"
	 */
	public static String checkRefund(Order msg) {
		int flag=0;
		if(msg.getEarlyOrder().equals("yes"))
		{
			if(java.time.Duration.between(LocalTime.parse(msg.getTimeOfOrder()), LocalTime.now()).toMinutes()>20)
			{	
				putRefund(msg);
				putPerfReport(msg,0);	
			}
			else
			{
				flag=1;
				putPerfReport(msg,1);		
			}
		}
		else
		{
			if(java.time.Duration.between(LocalTime.parse(msg.getSuppApproved()), LocalTime.now()).toMinutes()>60)
			{	
				putRefund(msg);
				putPerfReport(msg,0);		
			}
			else
			{
				flag=1;
				putPerfReport(msg,1);
			}
		}
		
		PreparedStatement stmt;
		try {
			stmt = DBConnect.conn.prepareStatement("UPDATE bitemedb.order SET punctuality=?,orderStatus=? WHERE orderNumber=?");
			stmt.setInt(1,flag);
			stmt.setString(2,"Done");
			stmt.setInt(3, msg.getOrderNum());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ok";
	}
	
	/**this method meant to create or update a performance report
	 * @param msg         the order's entity
	 * @param flag        meant to warn if the order was on time or late    
	 */
	private static void putPerfReport(Order msg, int flag) 
	{
		PreparedStatement stmt,stmt1,stmt2,stmt3;
		String branchRest=null;
		int total=0,late=0,onTime=0;
		try {
			stmt = DBConnect.conn.prepareStatement("SELECT homeBranch FROM bitemedb.supplier WHERE supplierName=?");
			stmt.setString(1, msg.getRestName());
			ResultSet rs1=stmt.executeQuery();
			while(rs1.next())
			{
				branchRest=rs1.getString(1);
			}
			
			rs1.close();
			String [] date=(LocalDate.now().toString()).split("-");
			stmt1 = DBConnect.conn.prepareStatement("SELECT total_orders,onTime,areLate FROM bitemedb.performance_reports WHERE restaurant=? and year=? and month=?");
			stmt1.setString(1, msg.getRestName());
			stmt1.setString(2, date[0]);
			stmt1.setString(3, date[1]);
			ResultSet rs2=stmt1.executeQuery();
			while(rs2.next())
			{
				total=rs2.getInt(1);
				onTime=rs2.getInt(2);
				late=rs2.getInt(3);	
			}
			rs2.close();
			
			if(total==0)
			{
				stmt2 = DBConnect.conn.prepareStatement("INSERT INTO bitemedb.performance_reports VALUES(?,?,?,?,?,?,?)");
				stmt2.setString(1, msg.getRestName());
				stmt2.setString(2, date[0]);
				stmt2.setString(3, date[1]);
				stmt2.setString(4,branchRest);
				stmt2.setInt(5, 1);
				if(flag==1)
				{
					stmt2.setInt(6,1);
					stmt2.setInt(7,0);
				}
				else
				{
					stmt2.setInt(6,0);
					stmt2.setInt(7,1);
				}
				stmt2.executeUpdate();	
			}
			else
			{
				stmt3 = DBConnect.conn.prepareStatement("UPDATE bitemedb.performance_reports SET total_orders=?,onTime=?,areLate=? WHERE restaurant=? and year=? and month=?");
				stmt3.setInt(1,total+1);
				if(flag==1)
				{
					stmt3.setInt(2,onTime+1);
					stmt3.setInt(3,late);
				}
				else
				{
					stmt3.setInt(2,onTime);
					stmt3.setInt(3,late+1);
				}
				stmt3.setString(4,msg.getRestName());
				stmt3.setString(5,date[0]);
				stmt3.setString(6,date[1]);
				stmt3.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	/** private method to insert/update a refund to the DB
	 * @param order          the order's entity
	 */
	private static void putRefund(Order order)
	{
		PreparedStatement stmt,stmt2,stmt3;
		String amount=null;
		try {
			stmt = DBConnect.conn.prepareStatement("SELECT ammount FROM bitemedb.refund WHERE ID=? and restId=?");
			stmt.setString(1, order.getCostumerId());
			stmt.setString(2,order.getRestId());
			ResultSet rs1=stmt.executeQuery();
			while(rs1.next())
			{
				amount=rs1.getString(1);
			}
			rs1.close();
			if(amount!=null)
			{
				Float sum= (float) (order.getTotalPrice()*0.5+ Float.parseFloat(amount));
				stmt2 = DBConnect.conn.prepareStatement("UPDATE bitemedb.refund SET ammount=? WHERE ID=? and restId=?");
				stmt2.setString(1, Float.toString(sum));
				stmt2.setString(2, order.getCostumerId());	
				stmt2.setString(3,order.getRestId());
				stmt2.executeUpdate();
			}
			else
			{
				Float sum= (float) (order.getTotalPrice()*0.5);
				stmt3 = DBConnect.conn.prepareStatement("INSERT INTO bitemedb.refund VALUES(?,?,?)");
				System.out.println("bbb");
				stmt3.setString(1, order.getCostumerId());
				stmt3.setString(2,Float.toString(sum));
				stmt3.setString(3, order.getRestId());
				stmt3.executeUpdate();
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**The method meant to get the histogram's data from the DB according to the selected details by the CEO
	 * @param divededAdd          array of strings with the CEO entered data-branch,year,quarterly
	 * @return                    hashMap with the histogram details- amount of orders and income for each restaurant
	 */
	public static Map<String, ArrayList<Float>> getHistogramData(String[] divededAdd) 
	{
		PreparedStatement stmt;
		Map<String, ArrayList<Float>> mapHist=new HashMap<>();
		String rest=null;
		try {
			stmt = DBConnect.conn.prepareStatement("SELECT resturant,orders_amount,Income FROM bitemedb.revenue_reports WHERE Quarterly=? and branch=? and year=?");
			stmt.setString(1, divededAdd[0]);
			stmt.setString(2,divededAdd[1]);
			stmt.setString(3,divededAdd[2]);
			ResultSet rs1=stmt.executeQuery();
			while(rs1.next())
			{
				rest=rs1.getString(1);
				if(mapHist.containsKey(rest))
				{
					Float amount=mapHist.get(rest).get(0);
					amount+=rs1.getInt(2);
					mapHist.get(rest).set(0, amount);
					Float income=mapHist.get(rest).get(1);
					income+=rs1.getFloat(3);
					mapHist.get(rest).set(1, income);		
				}
				else
				{
					ArrayList<Float> temp=new ArrayList<>();
					temp.add((float) rs1.getInt(2));
					temp.add(rs1.getFloat(3));	
					mapHist.put(rest, temp);
				}
			}
			rs1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapHist;
	}

	/**This method meant to get all the years that exists in the revenue report
	 
	 * @return ArrayList<String>  that list includes all the years that exists in the revenue report
	 */
	public static ArrayList<String> getYear() {
		ArrayList<String>years=null;
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT DISTINCT year FROM bitemedb.revenue_reports");
			years=new ArrayList<String>();
			while (rs.next()) {
				years.add(rs.getString(1));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return years;
	}
	
	/**
	 * 
	 * @param ID
	 * @return ArrayList of dishes
	 */
	public static ArrayList<Dish> ShowDishes(String ID) {
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

	/**This method meant to get all dishes of specific restaurant
	 * @param restCode   is the code of the specific restaurant 
	 * @return ArrayList<Dish>  that list includes all dishes of specific restaurant
	 */
		
	public static ArrayList<Dish> getDishes(String restCode) {
		ArrayList<Dish> dishes=new ArrayList<>();
		try {
			PreparedStatement stmt = DBConnect.conn.prepareStatement("SELECT dishName,supplierName,choiceFactor,choiceDetails,ingredients,extra,price,dishType FROM bitemedb.dishes WHERE restId1=?");
				stmt.setString(1,restCode);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Dish dish= new Dish(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6), rs.getFloat(7),DishType.toDishType(rs.getString(8)));
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
	
	
	
	
	/**This method meant to get all the restaurant of the specific city
	 * @param city  city is the specific city that client wants to order from
	 * @return ArrayList<Reestaurant>  that list includes all the restaurant from specific city
	 */
	public static ArrayList<Restaurant> getRestaurants(String city) {
		ArrayList<Restaurant> rest = new ArrayList<>();
		PreparedStatement stmt;
		String query = "";
		try {
			stmt = DBConnect.conn.prepareStatement(
					"SELECT restId,supplierName,openingTime,city,address,homeBranch FROM bitemedb.supplier WHERE city=? and supplierStatus=?");
			stmt.setString(1, city);
			stmt.setString(2, "Approved");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Restaurant rst = new Restaurant(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), homeBranches.toHomeBranchType(rs.getString(6)));
				rest.add(rst);
			}
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return rest;
	}
	

	/**
	 * this method load employers from the DB for the Branch Manager for
	 * confirmation.
	 * 
	 * @return - the array list of employers to confirm.
	 */
	public static ArrayList<Employer> LoadEmployers() {
		if (DBConnect.conn != null) {
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
		return null;
	}

	/**
	 * this method update the employer status from the Branch Manager.
	 * 
	 * @param CompanyName   - the company name of the employer.
	 * @param CompanyStatus - the updated company name of the employer.
	 */
	public static void UpdateEmployers(String CompanyName, String CompanyStatus) {
		try {
			if (DBConnect.conn != null) {
				PreparedStatement stmt = DBConnect.conn.prepareStatement("UPDATE bitemedb.company SET companyStatus= '"
						+ CompanyStatus + "'" + " WHERE companyName= '" + CompanyName + "'  ;");
				stmt.executeUpdate();
			} else {
				System.out.println("Conn is null");
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	/**
	 * this method check if the supplier company name equals to the supplier
	 * certified and approved in the DB.
	 * 
	 * @param supplier - the supplier to check his details.
	 * @return - true or false if the details are correct.
	 */
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

	/**
	 * this method adds to the users and supplier tables in the DB the details of
	 * the supplier.
	 * 
	 * @param supplier - supplier for the id of the certified and id approved
	 *                 supplier.
	 */
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

	/**
	 * this method return the user by his ID from the DB.
	 * 
	 * @param ID - the id of the user.
	 * @return - return the user from the DB
	 */
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

	/**
	 * this method returns array list of users from the DB to the Branch Manager.
	 * 
	 * @param Branch - the Branch of the Branch Manager to search users from his
	 *               branch.
	 * @return - Array list of users.
	 */
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

	/**
	 * this method delete the user from the users,client tables and if its a
	 * business client,delete form the buss_client table in DB.
	 * 
	 * @param user - user from the Branch Manager to Delete.
	 */
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

	/**
	 * this method returns true of false to the Branch Manager if the company is
	 * approved or not.
	 * 
	 * @param CompanyName - company name for find the company status in the DB
	 * @return - true of false.
	 */
	public static Boolean checkEmployerStatus(String CompanyName) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT companyStatus FROM bitemedb.company WHERE companyName= '" + CompanyName + "' ;");
				while (rs.next()) {
					String status = rs.getString(1);
					if (status.equals("Approved")) {
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

	/**
	 * this method check if the details of the business account from the Branch
	 * Manager equals to the details from the import_users.
	 * 
	 * @param Account - business account from the Branch Manager to check his
	 *                details.
	 * @return - true or false.
	 */
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

	/**
	 * this method adds the business account from the Branch manager to the
	 * users,client,buss_client tables in the DB.
	 * 
	 * @param BAccount - business account from the Branch Manager for add to the DB.
	 */
	public static void addNewBAccount(BussinessAccount BAccount) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt1 = DBConnect.conn.createStatement();
				ResultSet rs = stmt1.executeQuery(
						"SELECT userName,password FROM import_users WHERE id= '" + BAccount.getId() + "' ;");
				while (rs.next()) {
					String UserName = rs.getString(1);
					String Password = rs.getString(2);

					PreparedStatement stmt2 = DBConnect.conn.prepareStatement(
							"INSERT INTO users (userName,password,Role,FirstName,LastName,ID,Email,phone,isLoggedIn,homeBranch)"
									+ "VALUES(?,?,?,?,?,?,?,?,?,?)");
					stmt2.setString(1, BAccount.getId());
					stmt2.setString(2, Password);
					stmt2.setString(3, "Active");
					stmt2.setString(1, UserName);
					stmt2.setString(2, Password);
					stmt2.setString(3, BAccount.getRole());
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

	/**
	 * this method check if the details of the private client from the Branch
	 * Manager is equals to the details in the import_users table.
	 * 
	 * @param client - private account details from the Branch Manager.
	 * @return - true or false.
	 */
	public static Boolean checkPrivateAccount(Client client) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT firstName,lastName,Email,phone FROM bitemedb.import_users WHERE id= '" + client.getId() + "' ;");
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

	/**
	 * this method adds the private account from the Branch manager to the
	 * users,client tables in the DB.
	 * 
	 * @param PAccount - private account details for add.
	 */
	public static void addNewPAccount(Client PAccount) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT userName,password FROM bitemedb.import_users WHERE id= '"
						+ PAccount.getId() + "' ;");
				while (rs.next()) {
					String UserName = rs.getString(1);
					String Password = rs.getString(2);

					PreparedStatement stmt2 = DBConnect.conn.prepareStatement(
							"INSERT INTO bitemedb.users (userName,password,Role,FirstName,LastName,ID,Email,phone,isLoggedIn,homeBranch)"
									+ "VALUES(?,?,?,?,?,?,?,?,?,?)");
					stmt2.setString(1, UserName);
					stmt2.setString(2, Password);
					stmt2.setString(3, PAccount.getRole());
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

	/**
	 * this method returns users from the branch location to change permissions.
	 * 
	 * @param Branch - branch location for getting account from the DB.
	 * @return - array list of users for change permissions to the Branch Manager.
	 */
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

	/**
	 * this method return if the status of the client is active.
	 * 
	 * @param AccountID - to find the client with his ID.
	 * @return - true or false.
	 */
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

	/**
	 * this method return if the status of the client is freeze.
	 * 
	 * @param AccountID - to find the client with his ID.
	 * @return - true or false.
	 */
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

	/**
	 * this method update the account to active.
	 * @param AccountID - to find the client with his ID.
	 */
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

	/**
	 * this method update the account to Freeze.
	 * 
	 * @param AccountID - to find the client with his ID.
	 */
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

	/**
	 * this method return true or false if there is already report for the year and
	 * quarter given.
	 * 
	 * @param quarter - for check the quarter.
	 * @param year    - for check the year.
	 * @return - true or false.
	 */
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

	/**
	 * this method update the MyFile file form the Branch Manager to the DB.
	 * 
	 * @param file - for update the DB.
	 */
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
	 * @param branch  - for the Branch of the report.
	 * @param year    - for the Year of the report.
	 * @param quarter - Method connects to DB and get the relevant file with match
	 *                parameters and creates a MyFile object with the information
	 * @return - relevant MyFile from DB.
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
	 * Method connect to DB and get the relevant year and quarterly who match
	 * parameter "branch" and creates a ArrayList object with the information
	 * 
	 * @param branch - for the branch of the Branch Manager.
	 * @return - ArrayList with the match year and quarterly
	 */
	public static ArrayList<String> getRelevantYearsAndQuarterly(String branch) {
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
			System.out.println(yearsAndQuarter.toString());
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return yearsAndQuarter;

	}

	/**
	 * Method for getting all the businessAccount waiting for approval. The business
	 * accounts who are waiting for approval and their status is - Waiting, and work
	 * under the same company as the HR Manager.
	 * 
	 * @param companyName
	 * @return arrayList of BusinessAccountTracking
	 */
	public static ArrayList<BusinessAccountTracking> LoadBusinessAccountDetails(String companyName) {
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

	/**
	 * Method for getting all the w4cBusiness that are in the database
	 * 
	 * @return arrayList of w4cBusiness
	 */
	public static ArrayList<String> LoadW4CBusiness() {
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

	public static boolean LoadCompanyStatus(StringBuilder companyName) {
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

	/**
	 * Method for obtaining the telephone number of the customer who placed the
	 * order.
	 * 
	 * @param order
	 * @return phone number
	 */
	public static String LoadPhoneNumber(Order order) {
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

	/**
	 * Method for obtaining the name of the restaurant whose ID is the same as the
	 * ID of the certified employee.
	 * 
	 * @param ID
	 * @return restaurant name
	 */
	public static String getRestName(String ID) {
		String restName = null;
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT supplierName FROM bitemedb.supplier WHERE restId='" + ID + "' ;");
			while (rs.next()) {
				restName = rs.getString(1);
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return restName;
	}

	/*
	 * Method for receiving all orders awaiting approval / approval at a specific
	 * restaurant.
	 * 
	 * @param restName - for the restaurant name to check.
	 * 
	 * @return - array list of orders.
	 */
	public static ArrayList<Order> LoadOrders(String restName) {
		if (DBConnect.conn != null) {
			ArrayList<Order> orders = new ArrayList<>();
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

	/**
	 * Method for receiving all orders approved / sended at a specific restaurant.
	 * 
	 * @param ID
	 * @return arrayList of receipt
	 */
	public static ArrayList<Receipt> LoadOrdersApproved(String ID) {
		ArrayList<Receipt> receipts = new ArrayList<>();
		Float priceAfterCommission;
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT orderNumber,restName,totalPrice,dateOfOrder FROM bitemedb.order WHERE rstID='"
							+ ID + "' AND orderStatus='Approved' OR orderStatus='Sended';");
			while (rs.next()) {
				if (LocalDate.now().isAfter(LocalDate.parse(rs.getString(4)))
						|| LocalDate.now().isEqual(LocalDate.parse(rs.getString(4)))) {
					String[] DivededAdd = ((String) rs.getString(4)).split("-");
					ArrayList<Integer> checkMonth = new ArrayList<Integer>();
					for (String s : DivededAdd)
						checkMonth.add(Integer.parseInt(s));

					if (DivededAdd[0].equals(String.valueOf(LocalDate.now().getYear()))
							&& checkMonth.get(1) == Integer.parseInt(String.valueOf(LocalDate.now().getMonthValue()))) {
						priceAfterCommission = (float) (Float.parseFloat(rs.getString(3))
								- (Float.parseFloat(rs.getString(3)) * 0.1));
						Receipt receipt = new Receipt(rs.getInt(1), rs.getString(2), Float.parseFloat(rs.getString(3)),
								priceAfterCommission);
						receipts.add(receipt);
					}
				}
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return receipts;
	}

	/**
	 * this method returns HashMap of the Dish Type from the DB.
	 * 
	 * @param id - for the id to check.
	 * @return - HashMap of dish types.
	 */
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

	public static ArrayList<PerformanceReport> LoadPerformanceReport(String messageData) {
		ArrayList<PerformanceReport> reports = new ArrayList<>();
		System.out.println("test");
		ArrayList<String> restNames = new ArrayList<>();
		boolean existRest = false;
		int totalOrders = 0;
		String approvedTime;
		String sendTime;
		String[] data = messageData.split("@");
		String branch = data[0];
		String month = data[1];
		String year = data[2];
		float priceAfterCommission;
		long avgDiff = 0;
		Statement stmt, stmt2, stmt3;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT supplierName FROM bitemedb.supplier WHERE homeBranch='" + branch + "' ;");
			while (rs.next()) {
				restNames.add(rs.getString(1));
				existRest = true;
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}

		if (existRest) {
			for (String name : restNames) {
				System.out.println(name);
				try {
					stmt2 = DBConnect.conn.createStatement();
					ResultSet rs2 = stmt2.executeQuery(
							"SELECT timeApproved,timeSended FROM bitemedb.order WHERE restName='" + name + "' AND orderStatus='Sended' ;");

					while (rs2.next()) {
						approvedTime = rs2.getString(1);
						sendTime = rs2.getString(2);
						long avg = LocalTime.parse(approvedTime).until(LocalTime.parse(sendTime), ChronoUnit.SECONDS);
						avgDiff += avg;
					}
					rs2.close();
				} catch (SQLException s) {
					s.printStackTrace();
				}
				try {
					stmt3 = DBConnect.conn.createStatement();
					ResultSet rs3 = stmt3.executeQuery(
							"SELECT * FROM bitemedb.performance_reports WHERE restaurant='" + name + "' AND year='"+year+"' AND month='"+month+"' ;");
					while (rs3.next()) {
					PerformanceReport report = new PerformanceReport(month, year, branch, name, rs3.getInt(5),
							rs3.getInt(6), rs3.getInt(7));
					int total_orders, onTime;
					total_orders = rs3.getInt(5);
					onTime = rs3.getInt(6);
					Double avgCookingTime = (double)avgDiff / (double)total_orders;
					Double onTimeRate = (double)onTime / (double)total_orders;
					report.setAvarageCookingTime(avgCookingTime.shortValue());
					report.setOnTimeRate(onTimeRate);
					reports.add(report);
					}
					rs3.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return reports;
		}
		return null;
	}
	
	public static void UpdateisLoggedIn(String userName) {
		try {
			if (DBConnect.conn != null) {
				PreparedStatement stmt = DBConnect.conn.prepareStatement("UPDATE bitemedb.users SET isLoggedIn = '0' WHERE userName=?");
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

	/**This method  meant to check if the client can join to the shared delivery. 
	 * @param msg    msg is the message that we get from the client.It's includes the order's number of the shared delivery. 
	 *
	 *@return String    that string includes the time, date and if it's early order- of the order that the client wants to join  
	 */
	

	public static String checkJoin(String msg) 
	{
		String type=null,time=null;
		String []div=msg.split("@");
		PreparedStatement stmt;
		StringBuilder b=null;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("SELECT orderType,timeApproved,timeOfOrder,dateOfOrder,EarlyOrder FROM bitemedb.order WHERE orderNumber=? and restName=?");
				stmt.setInt(1, Integer.parseInt(div[0]));
				stmt.setString(2, div[1]);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) 
				{
					type=rs.getString(1);
					time=rs.getString(2);
					b=new StringBuilder();
					b.append(rs.getString(3));
					b.append("@");
					b.append(rs.getString(4));
					b.append("@");
					b.append(rs.getString(5));
				}
				rs.close();
				
				if(type==null||type.equals("Take Away"))
				{
					return null;
				}
				
				else
				{
					System.out.println("blala "+java.time.Duration.between(LocalTime.parse(time), LocalTime.now()).toMinutes());
					if(java.time.Duration.between(LocalTime.parse(time), LocalTime.now()).toMinutes()>15)
					{
						return null;
					}
					else
					{
						return b.toString();
					}
				}
			}	
		}	
		 catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**This method  meant to get the number of participants of the shared delivery 
	 * @param msg  msg is the message that we get from the client.It's includes the order's number of the shared delivery. 
	 *
	 **@return Integer- the number number of participants of the shared delivery 
	 */

	public static Integer getParticipants(Integer msg) 
	{
		PreparedStatement stmt;
		Integer part=0;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("SELECT participantsNum FROM bitemedb.delivery WHERE orderNum=?");
				stmt.setInt(1, msg);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) 
				{
					part=rs.getInt(1);
					
				}
				rs.close();
			}
				
	
	}
		catch (SQLException e) {
			e.printStackTrace();
		}
			 
	return part;		
}

	
	/**This method  meant to update the shared delivery and the total price of all the participants/ 
	 * @param msg  msg is the message that we get from the client.It's includes the type of delivery, number of participants and the order number of the delivery that the client wants to join.
	 */
	public static void InsertShared(String msg) 
	{
		Map<Integer,Float> map=new HashMap<>();
		String []div1=msg.split("@");
		String []div2=div1[0].split("-");
		float totalMain=0;
		

		PreparedStatement stmt,stmt1,stmt2,stmt3,stmt4;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("UPDATE bitemedb.delivery SET participantsNum = ?,deliPrice=? WHERE orderNum=?");
				stmt.setInt(3,Integer.parseInt(div2[1]));
				stmt.setInt(1,Integer.parseInt(div1[1]));
				
				if(Integer.parseInt(div1[1])>2)
				{
					stmt.setFloat(2,Integer.parseInt(div1[1])*15);
				}
				else
				{
					stmt.setFloat(2,Integer.parseInt(div1[1])*20);
				}
				stmt.executeUpdate();
				
				if(Integer.parseInt(div1[1])==3)
				{
					System.out.println("2456");
					stmt1 = DBConnect.conn
							.prepareStatement("SELECT orderNumber,totalPrice FROM bitemedb.order WHERE orderType=?");
					stmt1.setString(1, div1[0]);
					ResultSet rs1 = stmt1.executeQuery();
					while (rs1.next()) 
					{
						if(rs1.getInt(1)!=Integer.parseInt(div1[2]))
						{
							map.put(rs1.getInt(1),rs1.getFloat(2)-5);
						}
						
					}
					rs1.close();
					for(Integer num :map.keySet())
					{
						System.out.println("the number bla is "+num);
						stmt2 = DBConnect.conn
								.prepareStatement("UPDATE bitemedb.order SET totalPrice = ? WHERE orderNumber=?");
						stmt2.setFloat(1,map.get(num));
						stmt2.setInt(2,num);
						stmt2.executeUpdate();
					}
				}
				
				stmt4 = DBConnect.conn
						.prepareStatement("SELECT totalPrice FROM bitemedb.order WHERE orderNumber=?");
				stmt4.setInt(1, Integer.parseInt(div2[1]));
				ResultSet rs2 = stmt4.executeQuery();
				while (rs2.next()) 
				{
					totalMain=rs2.getFloat(1);
					
				}
				rs2.close();
				stmt3 = DBConnect.conn
						.prepareStatement("UPDATE bitemedb.order SET totalPrice = ? WHERE orderNumber=?");
				stmt3.setFloat(1,totalMain-5);
				stmt3.setInt(2,Integer.parseInt(div2[1]));
				stmt3.executeUpdate();	
				
				
				
				
			}

		} catch (SQLException e) {
			e.printStackTrace();	
		}
		
		
	}
	
	
	}
