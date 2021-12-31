package Parsing;

//client
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import Entities.BussinessAccount;
import Entities.Client;
import Entities.Delivery;
import Entities.Dish;
import Entities.Employer;
import Entities.Message;
import Entities.MessageType;
import Entities.MyFile;
import Entities.BusinessAccountTracking;
import Entities.Order;
import Entities.OrdersReport;
import Entities.Restaurant;
import Entities.RevenueReport;
import Entities.SingletonOrder;
import Entities.Supplier;
import Entities.User;
import Entities.homeBranches;
import client.controllers.AddDishToMenuController;
import client.controllers.BranchManagerChooseReportToViewController;
import client.controllers.BranchManagerCloseAccountController;
import client.controllers.BranchManagerChangePermissionsController;
import client.controllers.ChooseRestController;
import client.controllers.DeleteOrUpdateDishController;
import client.controllers.DeliveryController;
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
import client.controllers.BranchManagerScreenController;
import client.controllers.BranchManagerUploadPDFController;
import client.controllers.CEODownloadQuarterlyReportController;
import client.controllers.RestListFormController;
import client.controllers.ShowOrderController;
import client.controllers.SupplierScreenController;
import client.controllers.QuarterReportController;
import main.PopUpMessage;

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
			IdentifyW4cController.client=null;
			break;
		}

		
		case ShowHistogram: {
			if(QuarterReportController.report1==null)
			{
				if(!((Map<String, ArrayList<Float>>)receivedMessage.getMessageData()).isEmpty())
				{
					QuarterReportController.report1=(Map<String, ArrayList<Float>>) receivedMessage.getMessageData();
				}
			}
			else
			{
				if(!((Map<String, ArrayList<Float>>)receivedMessage.getMessageData()).isEmpty())
				{
					QuarterReportController.report2=(Map<String, ArrayList<Float>>) receivedMessage.getMessageData();
				}
				else
				{
					QuarterReportController.report2=null;
				}
			}
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

		case orderDone: {
			break;
		}

		case ReturnFirstName_success: {
			LoginScreenController.user.setFirstN(receivedMessage.getMessageData().toString());
			break;
		}

		case Show_Dishes_succ: {
			DeleteOrUpdateDishController.dishes = (ArrayList<Dish>) receivedMessage.getMessageData();
			break;
		}
		case MenuExistTrue: {
			SupplierScreenController.ExisingMenuFlag = true;
			break;
		}
		case MenuExistFalse: {
			SupplierScreenController.ExisingMenuFlag = false;
			break;
		}

		case rest_Name: {
			AddDishToMenuController.restName = (String) receivedMessage.getMessageData();
			break;
		}

		case Dish_add_succ: {
			AddDishToMenuController.dishAdd = true;
			break;
		}

		case dish_add_fail: {
			AddDishToMenuController.dishAdd = false;
			break;
		}

		case Dish_update_succ: {
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

		case set_Phone_number: {
			ConfirmOrderApprovalController.phoneNumber = (String) receivedMessage.getMessageData();
			break;
		}

		case W4C_Business_List: {
			HRManagerScreenController.w4cBusiness = (ArrayList<String>) receivedMessage.getMessageData();
			break;
		}

		case RegistrationOfEmployer_succ: {
			HRManagerScreenController.RegistrationFlag = true;
			break;

		}

		case Company_Status_Equale_To_Approved: {
			/*
			 * HRManagerConfirmationOfOpeningABusinessAccountController.
			 * CompanyStatusApproved = true; break;
			 */
		}

		case Company_Status_Not_Equale_To_Approved: {
			/*
			 * HRManagerConfirmationOfOpeningABusinessAccountController.
			 * CompanyStatusApproved = false; break;
			 */
		}

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

		case businessAccountsTracking: {
			HRManagerConfirmationOfOpeningABusinessAccountController.trackingDetails = (ArrayList<BusinessAccountTracking>) receivedMessage
					.getMessageData();
			break;
		}
		case UpdateSuccsesfuly:{
			break;
		}
		
		case UpdateFailed:{
			break;
		}
		
		case RReportUpdated: {
			BranchManagerChooseReportToViewController.revenueArray = (ArrayList<RevenueReport>) receivedMessage.getMessageData();
			System.out.println("hi hi");
			break;
		}
		
		case OReportUpdated:{
			BranchManagerChooseReportToViewController.ordersArray =(ArrayList<OrdersReport>) receivedMessage.getMessageData();
			break; 
		}
		
		case DType_Quantities:{
			HashMap<String,Integer> map = (HashMap<String,Integer>)receivedMessage.getMessageData();
			ConfirmOrderApprovalController.dishTypesQuentities=map;
			break;
		}

		case changed_BusinessAccount_status_to_Approved_succ: {
			break;
		}

		case changed_BusinessAccount_status_to_NotApproved_succ: {
			break;
		}

		case download_pdf_succ: {
			CEODownloadQuarterlyReportController.downloadFileData = (MyFile) receivedMessage.getMessageData();
			break;
		}
		
		case relevantYearAndQuarterly: {
			CEODownloadQuarterlyReportController.yearsAndQuarter = (ArrayList<String>) receivedMessage.getMessageData();
		}

		default: {
			break;
		}

		}
	}
}