package Parsing;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
import Entities.OrdersReport;
import Entities.PerformanceReport;
import Entities.Restaurant;
import Entities.RevenueReport;
import Entities.SingletonOrder;
import Entities.Supplier;
import Entities.User;
import controllers.LogicController;
import ocsf.server.ConnectionToClient;
import querys.Query;

public class Parsing {

	public static Message parsing(Object msg, ConnectionToClient client) {
		Message messageFromServer;
		Message receivedMessage = (Message) msg;

		switch (receivedMessage.getMessageType()) {

		case ShowHistogram: {
			String[] DivededAdd = ((String) receivedMessage.getMessageData()).split(",");
			Map<String, ArrayList<Float>> histogram = (Map<String, ArrayList<Float>>) Query
					.getHistogramData(DivededAdd);
			return messageFromServer = new Message(MessageType.ShowHistogram, histogram);
		}
		
		case Join:
		{
			System.out.println("iam " +Query.checkJoin((String)receivedMessage.getMessageData()));
			return messageFromServer = new Message(MessageType.Join,Query.checkJoin((String)receivedMessage.getMessageData()) );
		}

		case getYears: {
			ArrayList<String> years = (ArrayList<String>) Query.getYear();
			return messageFromServer = new Message(MessageType.getYears, years);
		}

		case loginSystem: {
			String result;
			String[] DivededUandP = ((String) receivedMessage.getMessageData()).split("@");
			result = Query.Login(DivededUandP[0], DivededUandP[1]);
			if (!result.equals("Already") && !result.equals("WrongInput") && !result.equals("Freeze")) {
				LogicController.UpdateClientTable(msg, client);
			}
			return messageFromServer = new Message(MessageType.loginSystem, result);
		}

		case IdentifyW4c: {
			Client costumer = Query.checkAccountKind((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.IdentifyW4c, costumer);
		}

		case Show_Cities: {
			ArrayList<String> city = Query.getCities();
			return messageFromServer = new Message(MessageType.Show_Cities, city);
		}

		case ClientConfirm: {
			ArrayList<Order> orders = Query.ConfirmClient((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.ClientConfirm, orders);
		}
		
		case priceShare:
		{
			return messageFromServer = new Message(MessageType.priceShare,Query.getParticipants((Integer) receivedMessage.getMessageData()) );
			
		}
		
		case InsertShared:
		{
			Query.InsertShared((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.InsertShared,null);
		}

		case show_Restaurants: {
			ArrayList<Restaurant> restaurants = Query
					.getRestaurants((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.show_Restaurants, restaurants);
		}

		case get_Dishes: {
			ArrayList<Dish> dishesOfRest = Query.getDishes((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.get_Dishes, dishesOfRest);
		}

		case getRefundDetails: {
			String refundSum = Query.getRefundSum((Order) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.getRefundDetails, refundSum);
		}

		case InsertOrder: {
			Integer insert = Query.insertOrder((Order) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.InsertOrder, insert);
		}

		case InsertDishesOrder: {
			String insert = Query.insertDishesOrder((ArrayList<Dish>) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.InsertDishesOrder, insert);
		}

		case InsertDelivery: {
			String insert = Query.insertDelivery((Delivery) receivedMessage.getMessageData());
			return  messageFromServer = new Message(MessageType.InsertDelivery, insert);
		}

		case orderDone: {
			String checkRef = Query.checkRefund((Order) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.InsertDelivery, checkRef);
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
			if (Query.NewDish((Dish) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.Dish_add_succ, null);
			} else {
				return messageFromServer = new Message(MessageType.dish_add_fail, null);
			}
		}

		case get_Employer: {
			ArrayList<Employer> employers = Query.LoadEmployers();
			return messageFromServer = new Message(MessageType.Employer_list, employers);
		}

		case Employer_Update: {
			ArrayList<Employer> employers = (ArrayList<Employer>) receivedMessage.getMessageData();
			for (int i = 0; i < employers.size(); i++) {
				Query.UpdateEmployers(employers.get(i).getCompanyName(), employers.get(i).getCompanyStatus());
			}
			return messageFromServer = new Message(MessageType.Employer_List_Update_succ, null);
		}

		case check_suppliers_details: {
			Supplier supplier = (Supplier) receivedMessage.getMessageData();
			if (Query.checkSupplier(supplier)) {
				Query.UpdateSupplier(supplier);
				return messageFromServer = new Message(MessageType.Supplier_List_Update_succ, null);
			} else {
				return messageFromServer = new Message(MessageType.supplier_not_match, null);
			}
		}

		case get_Accounts: {
			String Branch = (String)receivedMessage.getMessageData();
			ArrayList<User> Users = Query.getAccount(Branch);
			return messageFromServer = new Message(MessageType.Account_list, Users);
		}

		case Delete_Account: {
			User user = (User) receivedMessage.getMessageData();
			Query.DeleteAccount(user);
			return messageFromServer = new Message(MessageType.Delete_Account_Succ, null);
		}

		case check_account_employer_approved: {
			String Employer_name = (String) receivedMessage.getMessageData();
			if ((Query.checkEmployerStatus(Employer_name)) == true) {
				return messageFromServer = new Message(MessageType.employer_approved, null);
			} else {
				return messageFromServer = new Message(MessageType.employer_not_approved, null);
			}
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

		/**
		 * Case for download a PDF
		 *  @param  a string with selected branch, selected year and selected quarterly
		 *  @return a message to client with the selected file data in MyFile format
		 */
		case downloadPDF: {
			String[] DivededBandQandY = ((String) receivedMessage.getMessageData()).split("@");
			MyFile file = Query.downloadFile(DivededBandQandY[0], DivededBandQandY[1], DivededBandQandY[2]);
			return messageFromServer = new Message(MessageType.download_pdf_succ, file);
		}

		/**
		 * Case for getting from database the relevant year and quarterly appropriate for a selected branch
		 * @param a string with the selected branch name
		 * @return a message to client with the year and quarterly in ArrayList<String> format 
		 */
		case showRelevantYearsAndQuarterly: {
			String branch = (String) receivedMessage.getMessageData();
			ArrayList<String> yearsAndQuarter = Query.getRelevantYearsAndQuarterly(branch);
			return messageFromServer =  new Message(MessageType.relevantYearAndQuarterly, yearsAndQuarter);
		}

		/**
		 * Case of obtaining all the dishes belonging to a specific restaurant
		 * @param = ID of the certified employee in the restaurant
		 */
		case Show_Dishes: {
			ArrayList<Dish> dishes = Query.ShowDishes((String)receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.Show_Dishes_succ, dishes);
		}

		/**
		 * Case for updating a dish according to the required changes in the database
		 * @param = A desirable dish to update in the menu 
		 */
		case updateDish: {
			if (Query.UpdateDish((Dish) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.Dish_update_succ, null);
			}
		}

		/**
		 * Case for checking if there is a menu for a restaurant or not
		 * @param = ID of the certified employee in the restaurant
		 */
		case MenuExist: {
			if (Query.MenuExistingCheck((String) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.MenuExistTrue, null);
			} else {
				return messageFromServer = new Message(MessageType.MenuExistFalse, null);
			}
		}

		/**
		 * Case for deleting a dish from the menu and updating it in the database
		 * @param = A desirable dish to delete in the menu 
		 */
		case deleteDish: {
			if (Query.deleteDish((Dish) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.Dish_delete_succ, null);
			}			
		}

		/**
		 * Case for receiving orders awaiting approval
		 * @param = The name of the restaurant we want to confirm its reservations
		 */
		case get_orders_to_approve: {
			ArrayList<Order> orders = Query.LoadOrders((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.Orders_List, orders);
		}

		/**
		 * Case of using refund and downloading the refund value from the database
		 * @param = Order received
		 */
		case Use_Refund: {
			if (Query.updateRefundAmmount((Order) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.update_RefundTable, null);
			}
		}

		/**
		 * Case of using budget and downloading the budget value from the database
		 * @param = Order received
		 */
		case Use_Budget: {
			if (Query.updateBudgetValue((Order) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.update_Budget_bussClient, null);
			}
		}

		/**
		 * Change the order status to - Not approved in the database
		 * @param = Order received
		 */
		case Order_not_approved: {
			if (Query.updateOrderStatusToNotApproved((Order) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.changed_status_to_notApproved_succ, null);
			}
		}

		/**
		 * Change the order status to - Approved in the database
		 * @param = Order received
		 */
		case Order_approved: {
			if (Query.updateOrderStatusToApproved((Order) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.changed_status_to_Approved_succ, null);
			}
		}

		/**
		 * Change the order status to - Sended in the database
		 * @param = Order received
		 */
		case Order_sended: {
			if (Query.updateOrderStatusSended((Order) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.changed_status_to_sended_succ, null);
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

		/**
		 * Case for adding a new employer to the database
		 * @param = New employer to add
		 */
		case RegistrationOfEmployer: {
			if (Query.RegistrationOfEmployer((Employer) receivedMessage.getMessageData())) {
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

		/**
		 * Case for obtaining a business account awaiting approval
		 * @param = company name
		 */
		case get_business_account_details: {
			ArrayList<BusinessAccountTracking> businessAccount = Query
					.LoadBusinessAccountDetails((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.businessAccountsTracking, businessAccount);
		}

		/**
		 * Case to change businessAccount status to - Approved
		 */
		case update_status_approved_businessAccount: {
			if (Query.BusinessAccountStatusToApproved((BusinessAccountTracking) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.changed_BusinessAccount_status_to_Approved_succ,
						null);
			}
		}

		/**
		 * Case to change businessAccount status to - Not approved
		 */
		case update_status_NotApproved_businessAccount: {
			if (Query.BusinessAccountStatusToNotApproved((BusinessAccountTracking) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.changed_BusinessAccount_status_to_NotApproved_succ,
						null);
			}

		}
		case addto_Revenue_report: {
			if (Query.addToRevenueReportsTable((RevenueReport) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.UpdateSuccsesfuly, null);
			} else {
				System.out.println("DB Updated Failed");
				return messageFromServer = new Message(MessageType.UpdateFailed, null);
			}
		}
		
		case get_Revenue_report: {
			try {
				ArrayList<RevenueReport> array = (ArrayList<RevenueReport>) Query
						.getRevenueReport((String) receivedMessage.getMessageData());
				return messageFromServer = new Message(MessageType.RReportUpdated, array);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		case get_Orders_report: {
			ArrayList<OrdersReport> array = Query.getOrdersReport((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.OReportUpdated, array);
		}

		case get_Dish_type: {
			HashMap<String, Integer> retMap;
			int id = (Integer) receivedMessage.getMessageData();
			retMap = Query.getQuntitiesOfDishTypes(id);
			return messageFromServer = new Message(MessageType.DType_Quantities, retMap);
		}

		case addto_Order_report: {
			if (Query.addToOrdersReportsTable((ArrayList<OrdersReport>) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.UpdateSuccsesfuly, null);
			} else {
				return messageFromServer = new Message(MessageType.UpdateFailed, null);
			}
			
		}
		
		case Disconected: {
			Query.UpdateisLoggedIn((String) receivedMessage.getMessageData());
			LogicController.UpdateClientTable(msg, client);
			return messageFromServer = new Message(MessageType.Disconected, null);
		}
		
		/**
		 * Accept all confirmed reservations at this restaurant
		 * @param = ID of the certified employee in the restaurant
		 */
		case get_orders_Approved:{
			ArrayList<Receipt> receipt = Query.LoadOrdersApproved((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.get_receipt, receipt);
		}
		
		/**
		 * 
		 */
		case get_Performance_report:{
			ArrayList<PerformanceReport> reports = Query.LoadPerformanceReport((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.get_Performance_report, reports);
		}
		
		case get_year_for_report:{
			ArrayList<String> years = Query.getYear();
			return messageFromServer = new Message(MessageType.return_years_for_report,years);
		}
		
		default: {
			return messageFromServer = new Message(MessageType.Error, null);
		}

		}
	}
}
