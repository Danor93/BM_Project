package Parsing;

//client
import java.util.ArrayList;
import Entities.BussinessAccount;
import Entities.Client;
import Entities.Delivery;
import Entities.Dish;
import Entities.Employer;
import Entities.Message;
import Entities.MessageType;
import Entities.BusinessAccountTracking;
import Entities.Order;
import Entities.Restaurant;
import Entities.RevenueReport;
import Entities.SingletonOrder;
import Entities.Supplier;
import Entities.User;
import Entities.homeBranches;
import client.controllers.BranchManagerChooseReportToViewController;
import client.controllers.BranchManagerCloseAccountController;
import client.controllers.BranchManagerChangePermissionsController;
import client.controllers.ChooseRestController;
import client.controllers.DeleteOrUpdateDishController;
import client.controllers.DeliveryController;
import client.controllers.DeliveryOrPickupController;
import client.controllers.IdentifyW4cController;
import client.controllers.HRManagerConfirmationOfOpeningABusinessAccountController;
import client.controllers.HRManagerRegistrationOfEmployersController;
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
import client.controllers.RestListFormController;
import client.controllers.ShowOrderController;
import client.controllers.SupplierScreenController;
import main.PopUpMessage;



public class Parsing {
	public static void Message(Object msg) throws Exception {
		Message receivedMessage = null;
		receivedMessage = (Message) msg;

		switch (receivedMessage.getMessageType()) {

		case loginSystem: {
			String[] DivedMsg = ((String) receivedMessage.getMessageData()).split("@");
			
			if (!receivedMessage.getMessageData().equals("WrongInput")) {
				if (receivedMessage.getMessageData().equals("Already")) {
					LoginScreenController.AlreadyLoggedInFlag = true;
					//LoginScreenController.LoginFlag = true;
					LoginScreenController.statusUser="The user is already logged in";
				} else {
					if (receivedMessage.getMessageData().equals("Freeze"))
					{
						LoginScreenController.statusUser="Frozen Account";
					}
					else
					{
						//LoginScreenController.LoginFlag = true;
						LoginScreenController.user = new User(DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
								homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);	
						LoginScreenController.statusUser="Active";
					}
				
				
				}
			}
			else
			{
				LoginScreenController.statusUser="User name or password are inccorect";
			}
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
		
		case ClientConfirm:
		{
			CustomerScreenController.orderConfirm=(ArrayList<Order>) receivedMessage.getMessageData();
			break;
		}

		

		case IdentifyW4c:{
			IdentifyW4cController.client=(Client) receivedMessage.getMessageData();
			break;
		}
		
		case getRefundDetails:{
			ShowOrderController.refund=(String) receivedMessage.getMessageData();		
			break;
		}
		
		case InsertOrder:
		{
			ShowOrderController.finalOrder.setOrderNum((Integer)receivedMessage.getMessageData());
			break;
		}
		
		case InsertDishesOrder:
		{
			OrderConfimController.isSuccess=(String)receivedMessage.getMessageData();
			break;
		}
		
		case InsertDelivery:
		{
			break;
		}
		
		case orderDone:
		{
			break;
		}

		case Disconected: {
			break;
		}

		case ReturnFirstName_success: {
			LoginScreenController.user.setFirstN(receivedMessage.getMessageData().toString()); 
			break;
		}

	//	case WrongInput: {
		//	LoginScreenController.WrongInputFlag = true;
		//	break;
	//	}

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

		case Dish_add_succ: {

		}

		case Dish_update_succ: {

		}

		case Employer_list: {
			ConfirmEmployerRegController.Employers = (ArrayList<Employer>) receivedMessage.getMessageData();
			break;
		}

		case Supplier_list: {
			ConfirmSupplierRegController.Suppliers = (ArrayList<Supplier>) receivedMessage.getMessageData();
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
		
		case Baccount_details_not_ok:{
			BranchManagerOpenNewBussinessAccountController.Checkdeatils=false;
			break;
		}

		case ConfirmOpenNewBusinessAccount: {
			BranchManagerOpenNewBussinessAccountController.Checkdeatils=true;
			break;
		}
		
		case PAccount_details_not_ok:{
			BranchManagerOpenNewPrivateAccountController.ConfirmOpenNewPrivateAccountFlag=false;
			break;
		}
		
		case ConfirmOpenNewPrivateAccount:{
			BranchManagerOpenNewPrivateAccountController.ConfirmOpenNewPrivateAccountFlag=true;
			break;
		}

		case return_accounts_for_freeze: {
			BranchManagerChangePermissionsController.Users = (ArrayList<User>) receivedMessage.getMessageData();
			break;
		}

		case Account_Status_Active:{
			Boolean status = (Boolean) receivedMessage.getMessageData();
			if(status) {
				BranchManagerChangePermissionsController.ActiveAccount=true;
			}
			else {
				BranchManagerChangePermissionsController.ActiveAccount=false;
			}
			break;
		}
		
		case Account_Status_Freeze:{
			Boolean status = (Boolean) receivedMessage.getMessageData();
			if(status) {
				BranchManagerChangePermissionsController.FreezeAccount=true;
			}
			else {
				BranchManagerChangePermissionsController.FreezeAccount=false;
			}
			break;
		}

		case Orders_List: {
			ConfirmOrderApprovalController.allOrders = (ArrayList<Order>) receivedMessage.getMessageData();
			break;
		}

		case changed_status_to_notApproved_succ: {
			break;
		}

		case changed_status_to_Approved_succ: {
			break;
		}

		case RegistrationOfEmployer_succ: {
			HRManagerRegistrationOfEmployersController.RegistrationFlag = true;
			break;

		}

		case RegistrationOfEmployer_failed: {
			HRManagerRegistrationOfEmployersController.RegistrationFlag = false;
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
			HRManagerConfirmationOfOpeningABusinessAccountController.trackingDetails = (ArrayList<BusinessAccountTracking>) receivedMessage.getMessageData();
			break;
		}
		
		case send_Revenue_Report:{
			BranchManagerChooseReportToViewController.revenueReport = (RevenueReport) receivedMessage.getMessageData();
			break;
		}
		
		case changed_BusinessAccount_status_to_Approved_succ: {
			break;
		}

		case changed_BusinessAccount_status_to_NotApproved_succ: {
			break;
		}
		
		


		default: {
			break;
		}

		}
	}
}