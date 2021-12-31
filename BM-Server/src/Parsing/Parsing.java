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
import Entities.OrdersReport;
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

		case ShowHistogram: {
			String[] DivededAdd = ((String) receivedMessage.getMessageData()).split(",");
			Map<String, ArrayList<Float>> histogram = (Map<String, ArrayList<Float>>) queries
					.getHistogramData(DivededAdd);
			return messageFromServer = new Message(MessageType.ShowHistogram, histogram);
		}

		case getYears: {
			ArrayList<String> years = (ArrayList<String>) queries.getYear();
			return messageFromServer = new Message(MessageType.getYears, years);
		}

		case loginSystem: {
			String result;
			String[] DivededUandP = ((String) receivedMessage.getMessageData()).split("@");
			result = DBCheck.DBCheck(DivededUandP[0], DivededUandP[1]);
			if (!result.equals("Already") && !result.equals("WrongInput")) {
				LogicController.UpdateClientTable(msg, client);
			}
			return messageFromServer = new Message(MessageType.loginSystem, result);
		}

		case IdentifyW4c: {
			Client costumer = queries.checkAccountKind((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.IdentifyW4c, costumer);
		}

		case Show_Cities: {
			ArrayList<String> city = showCities.getCities();
			return messageFromServer = new Message(MessageType.Show_Cities, city);
		}

		case ClientConfirm: {
			ArrayList<Order> orders = queries.ConfirmClient((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.ClientConfirm, orders);
		}

		case show_Restaurants: {
			ArrayList<Restaurant> restaurants = showRestaurants
					.getRestaurants((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.show_Restaurants, restaurants);
		}

		case get_Dishes: {
			ArrayList<Dish> dishesOfRest = getDishes.getDishes((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.get_Dishes, dishesOfRest);
		}

		case getRefundDetails: {
			String refundSum = queries.getRefundSum((Order) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.getRefundDetails, refundSum);
		}

		case InsertOrder: {
			Integer insert = queries.insertOrder((Order) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.InsertOrder, insert);
		}

		case InsertDishesOrder: {
			String insert = queries.insertDishesOrder((ArrayList<Dish>) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.InsertDishesOrder, insert);
		}

		case InsertDelivery: {
			String insert = queries.insertDelivery((Delivery) receivedMessage.getMessageData());
			return  messageFromServer = new Message(MessageType.InsertDelivery, insert);
		}

		case orderDone: {
			String checkRef = queries.checkRefund((Order) receivedMessage.getMessageData());
			return  messageFromServer = new Message(MessageType.InsertDelivery, checkRef);
		}

		case add_new_dish: {
			if (UpdateDB.NewDish((Dish) receivedMessage.getMessageData())) {
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

		case downloadPDF: {
			String[] DivededBandQandY = ((String) receivedMessage.getMessageData()).split("@");
			MyFile file = Query.downloadFile(DivededBandQandY[0], DivededBandQandY[1], DivededBandQandY[2]);
			return messageFromServer = new Message(MessageType.download_pdf_succ, file);
		}

		case showRelevantYearsAndQuarterly: {
			String branch = (String) receivedMessage.getMessageData();
			ArrayList<String> yearsAndQuarter = Query.getRelevantYearsAndQuarterly(branch);
			return new Message(MessageType.relevantYearAndQuarterly, yearsAndQuarter);
		}

		case Show_Dishes: {
			ArrayList<Dish> dishes = ShowDishes.getDishes((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.Show_Dishes_succ, dishes);
		}

		case updateDish: {
			if (UpdateDB.UpdateDish((Dish) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.Dish_update_succ, null);
			}
		}

		case MenuExist: {
			if (DBCheck.MenuExistingCheck((String) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.MenuExistTrue, null);
			} else {
				return messageFromServer = new Message(MessageType.MenuExistFalse, null);
			}
		}

		case deleteDish: {
			if (UpdateDB.deleteDish((Dish) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.Dish_delete_succ, null);
			}
		}

		case get_orders_to_approve: {
			ArrayList<Order> orders = Query.LoadOrders((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.Orders_List, orders);
		}

		case Use_Refund: {
			if (UpdateDB.updateRefundAmmount((Order) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.update_RefundTable, null);
			}
		}

		case Use_Budget: {
			if (UpdateDB.updateBudgetValue((Order) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.update_Budget_bussClient, null);
			}
		}

		case Order_not_approved: {
			if (UpdateDB.updateOrderStatusToNotApproved((Order) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.changed_status_to_notApproved_succ, null);
			}
		}

		case Order_approved: {
			if (UpdateDB.updateOrderStatusToApproved((Order) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.changed_status_to_Approved_succ, null);
			}
		}

		case Order_sended: {
			if (UpdateDB.updateOrderStatusSended((Order) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.changed_status_to_sended_succ, null);
			}
		}

		case get_Phone_Number: {
			String phoneNumber = Query.LoadPhoneNumber((Order) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.set_Phone_number, phoneNumber);
		}

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
			if (UpdateDB.BusinessAccountStatusToApproved((BusinessAccountTracking) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.changed_BusinessAccount_status_to_Approved_succ,
						null);
			}
		}

		case update_status_NotApproved_businessAccount: {
			System.out.println("receivedMessage= " + receivedMessage.getMessageData());
			if (UpdateDB
					.BusinessAccountStatusToNotApproved((BusinessAccountTracking) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.changed_BusinessAccount_status_to_NotApproved_succ,
						null);
			}

		}
		case addto_Revenue_report: {
			if (UpdateDB.addToRevenueReportsTable((RevenueReport) receivedMessage.getMessageData())) {
				System.out.println("DB Updated Succsessfuly!");
				return messageFromServer = new Message(MessageType.UpdateSuccsesfuly, null);
			} else {
				System.out.println("DB Updated Failed");
				return messageFromServer = new Message(MessageType.UpdateFailed, null);
			}
		}
		
		case get_Revenue_report: {
			try {
				ArrayList<RevenueReport> array = (ArrayList<RevenueReport>) UpdateDB
						.getRevenueReport((String) receivedMessage.getMessageData());
				return messageFromServer = new Message(MessageType.RReportUpdated, array);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		case get_Orders_report: {
			ArrayList<OrdersReport> array = UpdateDB.getOrdersReport((String) receivedMessage.getMessageData());
			return messageFromServer = new Message(MessageType.OReportUpdated, array);
		}

		case get_Dish_type: {
			HashMap<String, Integer> retMap;
			int id = (Integer) receivedMessage.getMessageData();
			retMap = Query.getQuntitiesOfDishTypes(id);
			return messageFromServer = new Message(MessageType.DType_Quantities, retMap);
		}

		case addto_Order_report: {
			if (UpdateDB.addToOrdersReportsTable((ArrayList<OrdersReport>) receivedMessage.getMessageData())) {
				return messageFromServer = new Message(MessageType.UpdateSuccsesfuly, null);
			} else {
				return messageFromServer = new Message(MessageType.UpdateFailed, null);
			}
			
		}
		
		case Disconected: {
			UpdateDB.UpdateisLoggedIn((String) receivedMessage.getMessageData());
			LogicController.UpdateClientTable(msg, client);
			return messageFromServer = new Message(MessageType.Disconected, null);
		}

		default: {
			return messageFromServer = new Message(MessageType.Error, null);
		}

		}
	}
}
