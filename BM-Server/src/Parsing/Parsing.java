package Parsing;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
//server

import java.util.ArrayList;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.IllegalFormatPrecisionException;
import Entities.BusinessAccountTracking;
import Entities.BussinessAccount;
import Entities.Client;
import Entities.Delivery;
import Entities.Dish;
import Entities.DishType;
import Entities.Employer;
import Entities.Message;
import Entities.MessageType;
import Entities.MyFile;
import Entities.Order;
import Entities.Restaurant;
import Entities.RevenueReport;
import Entities.SingletonOrder;
import Entities.Supplier;
import Entities.User;
import controllers.LogicController;
import ocsf.server.ConnectionToClient;
import querys.DBCheck;
import querys.ShowDishes;
import querys.Query;
import querys.UpdateDB;
import querys.getDishes;
import querys.queries;
import querys.showCities;
import querys.showRestaurants;


public class Parsing {
	public static String result2;

	public static Message parsing(Object msg, ConnectionToClient client) {
		Message messageFromServer;
		Message receivedMessage = (Message) msg;

		switch (receivedMessage.getMessageType()) {
		
	

		case Update_Orders: {
			String[] DivededAdd = ((String) receivedMessage.getMessageData()).split("@");
			UpdateDB.UpdateOrderAddress(DivededAdd[0]);
			UpdateDB.UpdateTypeOrder(DivededAdd[1]);
			messageFromServer = new Message(MessageType.Update_succesfuly, null);
			return messageFromServer;
		}

		case loginSystem: {
			String result;
			String[] DivededUandP = ((String) receivedMessage.getMessageData()).split("@");
			result = DBCheck.DBCheck(DivededUandP[0], DivededUandP[1]);
		//	result2 = DivededUandP[0];
			messageFromServer = new Message(MessageType.loginSystem, result);
			return messageFromServer;
		}
		
		case IdentifyW4c: {
			Client costumer=queries.checkAccountKind((String) receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.IdentifyW4c, costumer);
			return messageFromServer;
		}

		case Show_Cities: {
			ArrayList<String> city = showCities.getCities();
			messageFromServer = new Message(MessageType.Show_Cities, city);
			return messageFromServer;
		}
		
		case ClientConfirm:
		{
			ArrayList<Order> orders=queries.ConfirmClient((String)receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.ClientConfirm, orders);
			return messageFromServer;
		}

		case show_Restaurants: {
			ArrayList<Restaurant> restaurants = showRestaurants
					.getRestaurants((String) receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.show_Restaurants, restaurants);
			return messageFromServer;
		}

		case get_Dishes: {
			ArrayList<Dish> dishesOfRest =getDishes.getDishes((String)receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.get_Dishes,dishesOfRest);
			return messageFromServer;
		}
		
		case getRefundDetails:{
			String refundSum=queries.getRefundSum((Order)receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.getRefundDetails,refundSum);
			return messageFromServer;
		}
		
		case InsertOrder:
		{
			Integer insert=queries.insertOrder((Order)receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.InsertOrder,insert);
			return messageFromServer;
		}
		
		case InsertDishesOrder:
		{
			String insert=queries.insertDishesOrder((ArrayList<Dish>)receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.InsertDishesOrder,insert);
			return messageFromServer;
		}
		
		case InsertDelivery:
		{
			String insert=queries.insertDelivery((Delivery)receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.InsertDelivery,insert);
			return messageFromServer;
		}
		
		case orderDone:
		{
			String checkRef=queries.checkRefund((Order)receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.InsertDelivery,checkRef);
			return messageFromServer;
		}

		case add_new_dish: {
			System.out.println(receivedMessage.getMessageData());
			if (UpdateDB.NewDish((Dish) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.Dish_add_succ, null);
			}
		}

		case get_Employer: {
			ArrayList<Employer> employers = Query.LoadEmployers();
			messageFromServer = new Message(MessageType.Employer_list, employers);
			return messageFromServer;
		}

		case Employer_Update: {
			ArrayList<Employer> employers = (ArrayList<Employer>) receivedMessage.getMessageData();
			for (int i = 0; i < employers.size(); i++) {
				Query.UpdateEmployers(employers.get(i).getCompanyName(), employers.get(i).getCompanyStatus());
			}
			messageFromServer = new Message(MessageType.Employer_List_Update_succ, null);
			return messageFromServer;
		}

		case get_Supplier: {
			String Branch = (String) receivedMessage.getMessageData();
			ArrayList<Supplier> Suppliers = Query.LoadSuppliers(Branch);
			messageFromServer = new Message(MessageType.Supplier_list, Suppliers);
			return messageFromServer;
		}

		case Supplier_Update: {
			ArrayList<Supplier> Suppliers = (ArrayList<Supplier>) receivedMessage.getMessageData();
			for (int i = 0; i < Suppliers.size(); i++) {
				Query.UpdateSupplier(Suppliers.get(i).getSupplierName(), Suppliers.get(i).getSupplierStatus());
			}
			messageFromServer = new Message(MessageType.Supplier_List_Update_succ, null);
			return messageFromServer;
		}

		case get_Accounts: {
			ArrayList<User> Users = Query.getAccount();
			messageFromServer = new Message(MessageType.Account_list, Users);
			return messageFromServer;
		}

		case Delete_Account: {
			User user = (User) receivedMessage.getMessageData();
			Query.DeleteAccount(user);
			messageFromServer = new Message(MessageType.Delete_Account_Succ, null);
			return messageFromServer;
		}

		case check_account_employer_approved: {
			String Employer_name = (String) receivedMessage.getMessageData();
			if ((Query.checkEmployerStatus(Employer_name)) == true) {
				messageFromServer = new Message(MessageType.employer_approved, null);
			} else {
				messageFromServer = new Message(MessageType.employer_approved, null);
			}
			return messageFromServer;
		}

		case check_Baccount_details: {
			BussinessAccount BAccount = (BussinessAccount) receivedMessage.getMessageData();
			if ((Query.checkAccountDetails(BAccount)) == true) {
				Query.addNewBAccount(BAccount);
				return messageFromServer = new Message(MessageType.ConfirmOpenNewBusinessAccount, null);
			} else {
				return messageFromServer = new Message(MessageType.Baccount_details_not_ok, null);
			}
		}

		case check_PAccount_details: {
			Client Pclient = (Client) receivedMessage.getMessageData();
			if ((Query.checkPrivateAccount(Pclient)) == true) {
				Query.addNewPAccount(Pclient);
				return messageFromServer = new Message(MessageType.ConfirmOpenNewPrivateAccount, null);
			} else {
				return messageFromServer = new Message(MessageType.PAccount_details_not_ok, null);
			}
		}

		case get_accounts_for_freeze: {
			ArrayList<User> Users = Query.GetAccountForFreeze((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.return_accounts_for_freeze, Users);
		}

		case check_account_status_Active: {
			String AccountID = (String) receivedMessage.getMessageData();
			if (Query.CheckAccountStatusActive(AccountID)) {
				return messageFromServer = new Message(MessageType.Account_Status_Active, true);
			} else {
				return messageFromServer = new Message(MessageType.Account_Status_Active, false);
			}
		}
		
		case check_account_status_Freeze:{
			String AccountID = (String) receivedMessage.getMessageData();
			if (Query.CheckAccountStatusFreeze(AccountID)) {
				return messageFromServer = new Message(MessageType.Account_Status_Freeze, true);
			} else {
				return messageFromServer = new Message(MessageType.Account_Status_Freeze, false);
			}
		}

		case Update_Status_to_Active: {
			String AccountID = (String) receivedMessage.getMessageData();
			Query.UpdateAccountStatusToActive(AccountID);
			return messageFromServer = new Message(MessageType.Account_Freeze_succ, null);
		}
		
		case Update_Status_to_Freeze:{
			String AccountID = (String) receivedMessage.getMessageData();
			Query.UpdateAccountStatusToFreeze(AccountID);
			return messageFromServer = new Message(MessageType.Account_Freeze_succ, null);
		}

		case check_year_and_quertar: {
			String[] Divededyandq = ((String) receivedMessage.getMessageData()).split("@");
			if ((Query.checkYearAndQuarter(Divededyandq[0], Divededyandq[1])) == true) {
				return messageFromServer = new Message(MessageType.year_and_querter_ok, null);
			} else {
				return messageFromServer = new Message(MessageType.year_and_querter_not_ok, null);
			}
		}

		case send_PDF: {
			MyFile file = (MyFile) receivedMessage.getMessageData();
			DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalDateTime nowTime = LocalDateTime.now();
			file.setDate(time.format(nowTime).toString());
			Query.updateFile(file);
			return messageFromServer = new Message(MessageType.upload_pdf_succ, null);
		}

		case Disconected: {
			UpdateDB.UpdateisLoggedIn((String)receivedMessage.getMessageData());
			LogicController.UpdateClientTable(msg, client);
			messageFromServer = new Message(MessageType.Disconected, null);
			return messageFromServer;
		}

		case Show_Dishes: {// get all orders from DB
			System.out.println(receivedMessage.getMessageData());
			ArrayList<Dish> dishes = ShowDishes.getDishes(receivedMessage.getMessageData());
			System.out.println("in server : ");
			for (int i = 0; i < dishes.size(); i++)
				System.out.println(dishes.get(i).getDishName());
			messageFromServer = new Message(MessageType.Show_Dishes_succ, dishes);
			return messageFromServer;
		}

		case updateDish: {
			System.out.println("receivedMessage= " + receivedMessage.getMessageData());
			if (UpdateDB.UpdateDish((Dish) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.Dish_update_succ, null);
				return messageFromServer;
			}
		}

		case MenuExist: {
			if (DBCheck.MenuExistingCheck((String) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.MenuExistTrue, null);
			} else {
				messageFromServer = new Message(MessageType.MenuExistFalse, null);
			}
			return messageFromServer;
		}

		case deleteDish: {
			System.out.println("receivedMessage= " + receivedMessage.getMessageData());
			if (UpdateDB.deleteDish((Dish) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.Dish_delete_succ, null);
				return messageFromServer;
			}
		}

		case get_orders_to_approve: {
			ArrayList<Order> orders = Query.LoadOrders();
			messageFromServer = new Message(MessageType.Orders_List, orders);
			return messageFromServer;
		}

		case Order_not_approved: {
			System.out.println("receivedMessage= " + receivedMessage.getMessageData());
			if (UpdateDB.updateOrderStatusToNotApproved((ArrayList<Order>) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.changed_status_to_notApproved_succ, null);
				return messageFromServer;
			}
		}

		case Order_approved: {
			System.out.println("receivedMessage= " + receivedMessage.getMessageData());
			if (UpdateDB.updateOrderStatusToApproved((ArrayList<Order>) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.changed_status_to_Approved_succ, null);
				return messageFromServer;
			}
		}

		case RegistrationOfEmployer: {
			System.out.println("receivedMessage= " + receivedMessage.getMessageData());
			if (UpdateDB.RegistrationOfEmployer((Employer) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.RegistrationOfEmployer_succ, null);
				return messageFromServer;
			} else {
				messageFromServer = new Message(MessageType.RegistrationOfEmployer_failed, null);
				return messageFromServer;
			}
		}

		case get_business_account_details: {
			ArrayList<BusinessAccountTracking> businessAccount = Query.LoadBusinessAccountDetails();
			messageFromServer = new Message(MessageType.businessAccountsTracking, businessAccount);
			return messageFromServer;
		}

		case update_status_approved_businessAccount: {
			System.out.println("receivedMessage= " + receivedMessage.getMessageData());
			if (UpdateDB.BusinessAccountStatusToApproved(
					(ArrayList<BusinessAccountTracking>) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.changed_BusinessAccount_status_to_Approved_succ,
						null);
			}
		}

		case update_status_NotApproved_businessAccount: {
			System.out.println("receivedMessage= " + receivedMessage.getMessageData());
			if (UpdateDB.BusinessAccountStatusToNotApproved(
					(ArrayList<BusinessAccountTracking>) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.changed_BusinessAccount_status_to_NotApproved_succ,
						null);
			}
		}

		case get_Revenue_report: {
			String[] details = ((String) receivedMessage.getMessageData()).split("@");
			RevenueReport Revenuereport = Query.getRevenueReport(details[0], details[1], details[2]);
			return messageFromServer = new Message(MessageType.send_Revenue_Report,Revenuereport);
		}

		default: {
			messageFromServer = new Message(MessageType.Error, null);
			return messageFromServer;
		}

		}
	}
}
