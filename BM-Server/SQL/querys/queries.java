package querys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Entities.BussinessAccount;
import Entities.Client;
import Entities.Delivery;
import Entities.Dish;
import Entities.DishType;
import Entities.Order;
import Entities.OrderType;
import Entities.Restaurant;
import Entities.SingletonOrder;
import Entities.homeBranches;
import controllers.ServerUIFController;
import main.EchoServer;

public class queries {

	public static Connection conn;

	@SuppressWarnings("deprecation")
	public static Connection connect(String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			EchoServer.serverUIFController.addToTextArea("Driver definition succeed.");
		} catch (Exception ex) {
			/* handle the error */
			EchoServer.serverUIFController.addToTextArea("Driver definition failed.");
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/bitemedb?serverTimezone=IST", username,
					password);
			EchoServer.serverUIFController.addToTextArea("SQL connection succeed.");
			ServerUIFController.flagon = true;
		} catch (SQLException ex) {/* handle any errors */
			EchoServer.serverUIFController.addToTextArea("SQLException:" + ex.getMessage() + ".");
			EchoServer.serverUIFController.addToTextArea("SQLState: " + ex.getSQLState() + ".");
			EchoServer.serverUIFController.addToTextArea("VendorError: " + ex.getErrorCode() + ".");
			EchoServer.serverUIFController.addToTextArea("Wrong input!\n");
		}
		return conn;
	}

	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	public static String DBCheck(String userName, String password) {
		StringBuilder result = new StringBuilder();
		String rs2 = null;
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement(
						"SELECT Role,ID,FirstName,LastName,w4cPrivate,homeBranch FROM bitemedb.users WHERE userName=? AND password=?");
				stmt.setString(1, userName);
				stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					result.append(rs.getString(1));
					result.append("@");
					result.append(rs.getString(2));
					result.append("@");
					result.append(rs.getString(3));
					result.append("@");
					result.append(rs.getString(4));
					result.append("@");
					result.append(rs.getString(5));
					result.append("@");
					result.append(rs.getString(6));
				}
				rs.close();

				if (result.length() == 0) {
					result.append("WrongInput");
				}
				stmt = DBConnect.conn.prepareStatement(
						"SELECT isLoggedIn FROM bitemedb.users WHERE userName=? AND password=?");
				stmt.setString(1, userName);
				stmt.setString(2, password);
				rs = stmt.executeQuery();
				rs.next();
				rs2 = rs.getString(1).toString(); // isLoggedIn of userName
				if (rs2.equals("0")) {
					stmt = DBConnect.conn.prepareStatement(
							"UPDATE bitemedb.users SET isLoggedIn='1' WHERE userName=? AND password=?");
					stmt.setString(1, userName);
					stmt.setString(2, password);
					stmt.executeUpdate();
				} else {
					System.out.println("already Log In");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result.toString();
	}

	/**
	 * @param ID
	 * @return
	 */
	public static String IDcheck(String ID) {

		String rs1 = null;
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("SELECT FirstName FROM bitemedb.users WHERE ID=?");
				stmt.setString(1, ID);
				ResultSet rs = stmt.executeQuery();
				rs.next();
				rs1 = rs.getString(1).toString();
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs1;
	}


	/**
	 * @return
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

	/**
	 * @param city
	 * @return
	 */
	public static ArrayList<Restaurant> getRestaurants(String city) {
		ArrayList<Restaurant> rest = new ArrayList<>();
		PreparedStatement stmt;
		String query = "";
		try {
			stmt = DBConnect.conn.prepareStatement(
					"SELECT restId,supplierName,openingTime,city,address,homeBranch FROM bitemedb.supplier WHERE city=? and supplierStatus=?");
			stmt.setString(1, city);
			stmt.setString(2, "approved");
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
	 * @param address
	 */
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

	/**
	 * @param type
	 */
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

	public static Integer insertOrder(Order msg) {
		Integer orderNum = null;
		PreparedStatement stmt, stmt1;
		try {
			stmt = DBConnect.conn.prepareStatement("INSERT INTO bitemedb.order VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
				System.out.println("aaa");
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

	public static Map<String, ArrayList<Float>> getHistogramData(String[] divededAdd) 
	{
		PreparedStatement stmt;
		Map<String, ArrayList<Float>> mapHist=new HashMap<>();
		String rest=null;
		try {
			stmt = DBConnect.conn.prepareStatement("SELECT resturant,orders_amount,Income FROM bitemedb.revenue_reports WHERE Quaterly=? and branch=? and year=?");
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
}