package Parsing;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.IllegalFormatPrecisionException;
import java.util.Map;
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
import Entities.Receipt;
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

		
		case ShowHistogram:
		{
			String[] DivededAdd = ((String) receivedMessage.getMessageData()).split(",");
			Map<String,ArrayList<Float>> histogram=(Map<String,ArrayList<Float>>)queries.getHistogramData(DivededAdd);
			messageFromServer = new Message(MessageType. ShowHistogram, histogram);
			return messageFromServer;
		}
		
		case getYears:
		{
			ArrayList<String> years=(ArrayList<String>)queries.getYear();
			messageFromServer = new Message(MessageType. getYears,  years);
			return messageFromServer;
		}

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
			// result2 = DivededUandP[0];
			messageFromServer = new Message(MessageType.loginSystem, result);
			return messageFromServer;
		}

		case IdentifyW4c: {
			Client costumer = queries.checkAccountKind((String) receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.IdentifyW4c, costumer);
			return messageFromServer;
		}

		case Show_Cities: {
			ArrayList<String> city = showCities.getCities();
			messageFromServer = new Message(MessageType.Show_Cities, city);
			return messageFromServer;
		}

		case ClientConfirm: {
			ArrayList<Order> orders = queries.ConfirmClient((String) receivedMessage.getMessageData());
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
			ArrayList<Dish> dishesOfRest = getDishes.getDishes((String) receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.get_Dishes, dishesOfRest);
			return messageFromServer;
		}

		case getRefundDetails: {
			String refundSum = queries.getRefundSum((Order) receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.getRefundDetails, refundSum);
			return messageFromServer;
		}

		case InsertOrder: {
			Integer insert = queries.insertOrder((Order) receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.InsertOrder, insert);
			return messageFromServer;
		}

		case InsertDishesOrder: {
			String insert = queries.insertDishesOrder((ArrayList<Dish>) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.InsertDishesOrder, insert);
		}

		case InsertDelivery: {
			String insert = queries.insertDelivery((Delivery) receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.InsertDelivery, insert);
			return messageFromServer;
		}

		case orderDone: {
			String checkRef = queries.checkRefund((Order) receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.InsertDelivery, checkRef);
			return messageFromServer;
		}

		/**
		 * Case for getting the restaurant name from the database
		 * @param = ID of the certified employee in the restaurant
		 */
		case get_Rest_Name:{
			String restName = Query.getRestName((String)receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.rest_Name, restName);
		}

		/**
		 * Case for adding a new dish to the menu
		 * @param = A desirable dish to add to the menu
		 */
		case add_new_dish: {
			System.out.println(receivedMessage.getMessageData());
			if (UpdateDB.NewDish((Dish) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.Dish_add_succ, null);
			} else {
				return messageFromServer = new Message(MessageType.dish_add_fail, null);
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
		
		case check_suppliers_details:{
			Supplier supplier = (Supplier) receivedMessage.getMessageData();
			if(Query.checkSupplier(supplier)) {
				Query.UpdateSupplier(supplier);
				return messageFromServer = new Message(MessageType.Supplier_List_Update_succ,null);
			}
			else {
				return messageFromServer = new Message(MessageType.supplier_not_match,null);
			}
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

		case check_account_status_Freeze: {
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

		case Update_Status_to_Freeze: {
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

		case downloadPDF: {
			String[] DivededBandQandY = ((String) receivedMessage.getMessageData()).split("@");
			MyFile file = Query.downloadFile(DivededBandQandY[0], DivededBandQandY[1], DivededBandQandY[2]);
			messageFromServer = new Message(MessageType.download_pdf_succ, file);
			return messageFromServer;
		}

		case showRelevantYearsAndQuarterly: {
			String branch = (String) receivedMessage.getMessageData();
			ArrayList<String> yearsAndQuarter = Query.getRelevantYearsAndQuarterly(branch);
			return new Message(MessageType.relevantYearAndQuarterly, yearsAndQuarter);

		}

		case Disconected: {
			UpdateDB.UpdateisLoggedIn((String) receivedMessage.getMessageData());
			LogicController.UpdateClientTable(msg, client);
			return messageFromServer = new Message(MessageType.Disconected, null);
		}

		/**
		 * Case of obtaining all the dishes belonging to a specific restaurant
		 * @param = ID of the certified employee in the restaurant
		 */
		case Show_Dishes: {
			ArrayList<Dish> dishes = ShowDishes.getDishes((String)receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.Show_Dishes_succ, dishes);
			return messageFromServer;
		}

		/**
		 * Case for updating a dish according to the required changes in the database
		 * @param = A desirable dish to update in the menu 
		 */
		case updateDish: {
			if (UpdateDB.UpdateDish((Dish) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.Dish_update_succ, null);
				return messageFromServer;
			}
		}

		/**
		 * Case for checking if there is a menu for a restaurant or not
		 * @param = ID of the certified employee in the restaurant
		 */
		case MenuExist: {
			if (DBCheck.MenuExistingCheck((String) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.MenuExistTrue, null);
			} else {
				messageFromServer = new Message(MessageType.MenuExistFalse, null);
			}
			return messageFromServer;
		}

		/**
		 * Case for deleting a dish from the menu and updating it in the database
		 * @param = A desirable dish to delete in the menu 
		 */
		case deleteDish: {
			if (UpdateDB.deleteDish((Dish) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.Dish_delete_succ, null);
				return messageFromServer;
			}			
		}

		/**
		 * Case for receiving orders awaiting approval
		 * @param = The name of the restaurant we want to confirm its reservations
		 */
		case get_orders_to_approve: {
			ArrayList<Order> orders = Query.LoadOrders((String) receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.Orders_List, orders);
			return messageFromServer;
		}

		/**
		 * Case of using refund and downloading the refund value from the database
		 * @param = Order received
		 */
		case Use_Refund: {
			if (UpdateDB.updateRefundAmmount((Order) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.update_RefundTable, null);
				return messageFromServer;
			}
		}

		/**
		 * Case of using budget and downloading the budget value from the database
		 * @param = Order received
		 */
		case Use_Budget: {
			if (UpdateDB.updateBudgetValue((Order) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.update_Budget_bussClient, null);
				return messageFromServer;
			}
		}

		/**
		 * Change the order status to - Not approved in the database
		 * @param = Order received
		 */
		case Order_not_approved: {
			if (UpdateDB.updateOrderStatusToNotApproved((Order) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.changed_status_to_notApproved_succ, null);
				return messageFromServer;
			}
		}

		/**
		 * Change the order status to - Approved in the database
		 * @param = Order received
		 */
		case Order_approved: {
			if (UpdateDB.updateOrderStatusToApproved((Order) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.changed_status_to_Approved_succ, null);
				return messageFromServer;
			}
		}

		/**
		 * Change the order status to - Sended in the database
		 * @param = Order received
		 */
		case Order_sended: {
			if (UpdateDB.updateOrderStatusSended((Order) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.changed_status_to_sended_succ, null);
				return messageFromServer;
			}
		}

		/**
		 * Case for obtaining the telephone number of the ordering customer
		 * @param = Order received
		 */
		case get_Phone_Number: {
			String phoneNumber = Query.LoadPhoneNumber((Order) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.set_Phone_number, phoneNumber);
		}

		/**
		 * Case for getting all the w4cBusiness that are in the database
		 */
		case getAllW4CBusiness: {
			ArrayList<String> w4cBusiness = Query.LoadW4CBusiness();
			return messageFromServer = new Message(MessageType.W4C_Business_List, w4cBusiness);
		}

		case RegistrationOfEmployer: {
			if (UpdateDB.RegistrationOfEmployer((Employer) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.RegistrationOfEmployer_succ, null);
			} else {
				return messageFromServer = new Message(MessageType.RegistrationOfEmployer_failed, null);
			}
		}

		case get_Company_Status: {
			if (Query.LoadCompanyStatus((StringBuilder) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.Company_Status_Equale_To_Approved, null);
			} else {
				return messageFromServer = new Message(MessageType.Company_Status_Not_Equale_To_Approved, null);
			}
		}

		case get_business_account_details: {
			ArrayList<BusinessAccountTracking> businessAccount = Query
					.LoadBusinessAccountDetails((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.businessAccountsTracking, businessAccount);
		}

		case update_status_approved_businessAccount: {
			System.out.println("receivedMessage= " + receivedMessage.getMessageData());
			if (UpdateDB.BusinessAccountStatusToApproved(
					(BusinessAccountTracking) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.changed_BusinessAccount_status_to_Approved_succ,
						null);
			}
		}

		case update_status_NotApproved_businessAccount: {
			System.out.println("receivedMessage= " + receivedMessage.getMessageData());
			if (UpdateDB.BusinessAccountStatusToNotApproved(
					(BusinessAccountTracking) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.changed_BusinessAccount_status_to_NotApproved_succ,
						null);
			}

		}

		case get_Revenue_report: {
			String[] details = ((String) receivedMessage.getMessageData()).split("@");
			RevenueReport Revenuereport = Query.getRevenueReport(details[0], details[1], details[2]);
			return messageFromServer = new Message(MessageType.send_Revenue_Report, Revenuereport);
		}
		
		/**
		 * Accept all confirmed reservations at this restaurant
		 * @param = ID of the certified employee in the restaurant
		 */
		case get_orders_Approved:{
			ArrayList<Receipt> receipt = Query.LoadOrdersApproved((String) receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.get_receipt, receipt);
			return messageFromServer;
		}

		default: {
			return messageFromServer = new Message(MessageType.Error, null);
		}

		}
	}
}
