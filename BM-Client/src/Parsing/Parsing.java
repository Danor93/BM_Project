package Parsing;

//client
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import Entities.Client;
import Entities.Dish;
import Entities.Employer;
import Entities.Message;
import Entities.MyFile;
import Entities.BusinessAccountTracking;
import Entities.Order;
import Entities.Receipt;
import Entities.OrdersReport;
import Entities.PerformanceReport;
import Entities.Restaurant;
import Entities.RevenueReport;
import Entities.User;
import Entities.homeBranches;
import client.controllers.AddDishToMenuController;
import client.controllers.BranchManagerChooseReportToViewController;
import client.controllers.BranchManagerCloseAccountController;
import client.controllers.BranchManagerChangePermissionsController;
import client.controllers.ChooseRestController;
import client.controllers.DeleteOrUpdateDishController;
import client.controllers.DeliveryOrPickupController;
import client.controllers.IdentifyW4cController;
import client.controllers.HRManagerConfirmationOfOpeningABusinessAccountController;
import client.controllers.HRManagerScreenController;
import client.controllers.ConfirmEmployerRegController;
import client.controllers.ConfirmOrderApprovalController;
import client.controllers.BranchManagerOpenNewBussinessAccountController;
import client.controllers.ConfirmSupplierRegController;
import client.controllers.CustomerScreenController;
import client.controllers.LoginScreenController;
import client.controllers.OrderConfimController;
import client.controllers.BranchManagerOpenNewPrivateAccountController;
import client.controllers.BranchManagerUploadPDFController;
import client.controllers.CEODownloadQuarterlyReportController;
import client.controllers.RestListFormController;
import client.controllers.ShowOrderController;
import client.controllers.SupplierScreenController;
import client.controllers.ViewReceiptController;
import client.controllers.QuarterReportController;

public class Parsing {
	@SuppressWarnings("unchecked")
	public static void Message(Object msg) throws Exception {
		Message receivedMessage = (Message) msg;

		switch (receivedMessage.getMessageType()) {

		case loginSystem: {
			String[] DivedMsg = ((String) receivedMessage.getMessageData()).split("@");
			if (!receivedMessage.getMessageData().equals("WrongInput")) {
				if (receivedMessage.getMessageData().equals("Already")) {
					LoginScreenController.statusUser = "The user is already logged in";
					LoginScreenController.user = null;
				} else {
					if (receivedMessage.getMessageData().equals("Freeze")) {
						LoginScreenController.statusUser = "Frozen Account";
						LoginScreenController.user = null;
					} else {
						LoginScreenController.user = new User(DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
								homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
						LoginScreenController.statusUser = "Active";
					}
				}
			} else {
				LoginScreenController.statusUser = "User name or password are inccorect";
				LoginScreenController.user = null;
			}
			IdentifyW4cController.client = null;
			break;
		}

		case ShowHistogram: {
			if (QuarterReportController.report1 == null) {
				if (!((Map<String, ArrayList<Float>>) receivedMessage.getMessageData()).isEmpty()) {
					QuarterReportController.report1 = (Map<String, ArrayList<Float>>) receivedMessage.getMessageData();
				}
			} else {
				if (!((Map<String, ArrayList<Float>>) receivedMessage.getMessageData()).isEmpty()) {
					QuarterReportController.report2 = (Map<String, ArrayList<Float>>) receivedMessage.getMessageData();
				} else {
					QuarterReportController.report2 = null;
				}
			}
			break;
		}

		case Join: {
			if (receivedMessage.getMessageData() != null) {
				DeliveryOrPickupController.isJoin = true;
				String[] div = ((String) receivedMessage.getMessageData()).split("@");
				ShowOrderController.finalOrder.setTimeOfOrder(div[0]);
				ShowOrderController.finalOrder.setDateOfOrder(div[1]);
				ShowOrderController.finalOrder.setEarlyOrder(div[2]);
			}
			break;
		}

		case priceShare: {
			OrderConfimController.part = (Integer) receivedMessage.getMessageData();
			break;
		}

		case getYears: {
			QuarterReportController.years = (ArrayList<String>) receivedMessage.getMessageData();
			break;
		}

		case Show_Cities: {
			ChooseRestController.cities = (ArrayList<String>) receivedMessage.getMessageData();
			break;
		}

		case show_Restaurants: {
			RestListFormController.restaurants = (ArrayList<Restaurant>) receivedMessage.getMessageData();
			break;
		}

		case get_Dishes: {
			RestListFormController.dishes = (ArrayList<Dish>) receivedMessage.getMessageData();
			break;
		}

		case IdentifyW4c: {
			IdentifyW4cController.client = (Client) receivedMessage.getMessageData();
			break;
		}

		case getRefundDetails: {
			ShowOrderController.refund = (String) receivedMessage.getMessageData();
			break;
		}

		case InsertOrder: {
			ShowOrderController.finalOrder.setOrderNum((Integer) receivedMessage.getMessageData());
			break;
		}

		case InsertDishesOrder: {
			OrderConfimController.isSuccess = (String) receivedMessage.getMessageData();
			break;
		}

		case ClientConfirm: {
			CustomerScreenController.orderConfirm = (ArrayList<Order>) receivedMessage.getMessageData();
			break;
		}

		case InsertDelivery: {
			break;
		}
		case InsertShared: {
			break;
		}

		case orderDone: {
			break;
		}

		case ReturnFirstName_success: {
			LoginScreenController.user.setFirstN(receivedMessage.getMessageData().toString());
			break;
		}

		/**
		 * Inserting all dishes into a static variable
		 */
		case Show_Dishes_succ: {
			DeleteOrUpdateDishController.dishes = (ArrayList<Dish>) receivedMessage.getMessageData();
			break;
		}

		/**
		 * Change the flag to true when the menu exists.
		 */
		case MenuExistTrue: {
			SupplierScreenController.ExisingMenuFlag = true;
			break;
		}

		/**
		 * Change the flag to false when the menu exists.
		 */
		case MenuExistFalse: {
			SupplierScreenController.ExisingMenuFlag = false;
			break;
		}

		/**
		 * Enter the value of the restaurant name into the static variable
		 */
		case rest_Name: {
			AddDishToMenuController.restName = (String) receivedMessage.getMessageData();
			break;
		}

		/**
		 * Change the flag to true if adding the dish to the menu was successful
		 */
		case Dish_add_succ: {
			AddDishToMenuController.dishAdd = true;
			break;
		}

		/**
		 * Change flag to false if adding dish to menu failed
		 */
		case dish_add_fail: {
			AddDishToMenuController.dishAdd = false;
			break;
		}

		case Dish_update_succ: {
			break;
		}

		case Dish_delete_succ: {
			break;
		}

		case Employer_list: {
			ConfirmEmployerRegController.Employers = (ArrayList<Employer>) receivedMessage.getMessageData();
			break;
		}

		case Supplier_List_Update_succ: {
			ConfirmSupplierRegController.confirmRegFlag = true;
			break;
		}

		case supplier_not_match: {
			ConfirmSupplierRegController.confirmRegFlag = false;
			break;
		}

		case Account_list: {
			BranchManagerCloseAccountController.Users = (ArrayList<User>) receivedMessage.getMessageData();
			break;
		}

		case employer_approved: {
			BranchManagerOpenNewBussinessAccountController.AprrovedFlag = true;
			break;
		}

		case employer_not_approved: {
			BranchManagerOpenNewBussinessAccountController.AprrovedFlag = false;
			break;
		}

		case Baccount_details_not_ok: {
			BranchManagerOpenNewBussinessAccountController.Checkdeatils = false;
			break;
		}

		case ConfirmOpenNewBusinessAccount: {
			BranchManagerOpenNewBussinessAccountController.Checkdeatils = true;
			break;
		}

		case PAccount_details_not_ok: {
			BranchManagerOpenNewPrivateAccountController.ConfirmOpenNewPrivateAccountFlag = false;
			break;
		}

		case ConfirmOpenNewPrivateAccount: {
			BranchManagerOpenNewPrivateAccountController.ConfirmOpenNewPrivateAccountFlag = true;
			break;
		}

		case return_accounts_for_freeze: {
			BranchManagerChangePermissionsController.Users = (ArrayList<User>) receivedMessage.getMessageData();
			break;
		}

		case Account_Status_Active: {
			Boolean status = (Boolean) receivedMessage.getMessageData();
			if (status) {
				BranchManagerChangePermissionsController.ActiveAccount = true;
			} else {
				BranchManagerChangePermissionsController.ActiveAccount = false;
			}
			break;
		}

		case Account_Status_Freeze: {
			Boolean status = (Boolean) receivedMessage.getMessageData();
			if (status) {
				BranchManagerChangePermissionsController.FreezeAccount = true;
			} else {
				BranchManagerChangePermissionsController.FreezeAccount = false;
			}
			break;
		}

		/**
		 * Enter all orders waiting for approval into a static variable
		 */
		case Orders_List: {
			ConfirmOrderApprovalController.allOrders = (ArrayList<Order>) receivedMessage.getMessageData();
			break;
		}

		case update_RefundTable: {
			break;
		}

		case update_Budget_bussClient: {
			break;
		}

		case changed_status_to_notApproved_succ: {
			break;
		}

		case changed_status_to_Approved_succ: {
			break;
		}

		case changed_status_to_sended_succ: {
			break;
		}

		/**
		 * Entering the telephone number value into a static variable
		 */
		case set_Phone_number: {
			ConfirmOrderApprovalController.phoneNumber = (String) receivedMessage.getMessageData();
			break;
		}

		/**
		 * Inserting all w4cBusiness values ​​into a static variable
		 */
		case W4C_Business_List: {
			HRManagerScreenController.w4cBusiness = (ArrayList<String>) receivedMessage.getMessageData();
			break;
		}

		/**
		 * Change flag to true if adding new employer to database success
		 */
		case RegistrationOfEmployer_succ: {
			HRManagerScreenController.RegistrationFlag = true;
			break;

		}

		/**
		 * Change flag to false if adding new employer to database failed.
		 */
		case RegistrationOfEmployer_failed: {
			HRManagerScreenController.RegistrationFlag = false;
			break;
		}

		case year_and_querter_ok: {
			BranchManagerUploadPDFController.yearandqflag = true;
			break;
		}

		case year_and_querter_not_ok: {
			BranchManagerUploadPDFController.yearandqflag = false;
			break;
		}

		case upload_pdf_succ: {
			BranchManagerUploadPDFController.succesUpload = true;
			break;
		}

		case Delete_Account_Succ: {
			break;
		}

		/**
		 * Inserting the BusinessAccountTracking values ​​into a static variable
		 */
		case businessAccountsTracking: {
			HRManagerConfirmationOfOpeningABusinessAccountController.trackingDetails = (ArrayList<BusinessAccountTracking>) receivedMessage
					.getMessageData();
			break;
		}
		case UpdateSuccsesfuly: {
			break;
		}

		case UpdateFailed: {
			break;
		}

		case RReportUpdated: {
			BranchManagerChooseReportToViewController.revenueArray = (ArrayList<RevenueReport>) receivedMessage
					.getMessageData();
			break;
		}

		case OReportUpdated: {
			BranchManagerChooseReportToViewController.ordersArray = (ArrayList<OrdersReport>) receivedMessage
					.getMessageData();
			break;
		}

		case DType_Quantities: {
			HashMap<String, Integer> map = (HashMap<String, Integer>) receivedMessage.getMessageData();
			ConfirmOrderApprovalController.dishTypesQuentities = map;
			break;
		}

		case changed_BusinessAccount_status_to_Approved_succ: {
			break;
		}

		case changed_BusinessAccount_status_to_NotApproved_succ: {
			break;
		}

		/**
		 * Insert the selected file data in MyFile format into a static variable
		 */
		case download_pdf_succ: {
			CEODownloadQuarterlyReportController.downloadFileData = (MyFile) receivedMessage.getMessageData();
			break;
		}

		/**
		 * Insert the relevant year and quarterly with the selected branch into a static
		 * variable
		 */
		case relevantYearAndQuarterly: {
			CEODownloadQuarterlyReportController.yearsAndQuarter = (ArrayList<String>) receivedMessage.getMessageData();
			break;
		}

		/**
		 * Insert all the receipts into a static variable
		 */
		case get_receipt: {
			ViewReceiptController.receipts = (ArrayList<Receipt>) receivedMessage.getMessageData();
			break;
		}

		case return_years_for_report: {
			BranchManagerChooseReportToViewController.years = (ArrayList<String>) receivedMessage.getMessageData();
			break;
		}

		/**
		 * 
		 */
		case get_Performance_report: {
			BranchManagerChooseReportToViewController.performanceArray = (ArrayList<PerformanceReport>) receivedMessage
					.getMessageData();
			break;
		}
		default: {
			break;
		}

		}
	}
}