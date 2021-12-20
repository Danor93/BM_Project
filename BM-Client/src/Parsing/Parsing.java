package Parsing;

//client
import java.util.ArrayList;

import Entities.Dish;
import Entities.Employer;
import Entities.Message;
import Entities.Order;
import Entities.Restaurant;
import Entities.Supplier;
import Entities.User;
import Entities.homeBranches;
import client.controllers.BranchManagerCloseAccountController;
import client.controllers.BranchManagerFreezeAccountController;
import client.controllers.ChooseRestController;
import client.controllers.DeleteOrUpdateDishController;
import client.controllers.ConfirmEmployerRegController;
import client.controllers.ConfirmOrderApprovalController;
import client.controllers.BranchManagerOpenNewBussinessAccountController;
import client.controllers.ConfirmSupplierRegController;
import client.controllers.LoginScreenController;
import client.controllers.BranchManagerOpenNewBussinessAccountController;
import client.controllers.BranchManagerOpenNewPrivateAccountController;
import client.controllers.BranchManagerScreenController;
import client.controllers.RestListFormController;
import client.controllers.SupplierScreenController;
import main.PopUpMessage;

public class Parsing {
	public static void Message(Object msg) throws Exception {
		Message receivedMessage = null;
		receivedMessage = (Message) msg;

		switch (receivedMessage.getMessageType()) {

		case login: {
			String[] DivedMsg = ((String) receivedMessage.getMessageData()).split("@");
			if (!receivedMessage.getMessageData().equals("WrongInput")) {
				if (receivedMessage.getMessageData().equals("Already")) {
					LoginScreenController.AlreadyLoggedInFlag = true;
					LoginScreenController.LoginFlag = true;
				} else {
					LoginScreenController.LoginFlag = true;
					LoginScreenController.user = new User(DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
							homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
				}

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

		case Disconected: {
			break;
		}

		case ReturnFirstName_success: {
			LoginScreenController.Name = receivedMessage.getMessageData().toString();
			break;
		}

		case WrongInput: {
			LoginScreenController.WrongInputFlag = true;
			break;
		}

		case ConfirmOpenNewPrivateAccount: {
			BranchManagerOpenNewPrivateAccountController.ConfirmOpenNewPrivateAccountFlag = true;
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

		case BAccount_succ: {
			BranchManagerOpenNewBussinessAccountController.ConfirmFlag = true;
			break;
		}

		case PAccount_exits: {
			BranchManagerOpenNewPrivateAccountController.ConfirmOpenNewPrivateAccountFlag = true;
			break;
		}

		case PAccount_NOT_exits: {
			BranchManagerOpenNewPrivateAccountController.ConfirmOpenNewPrivateAccountFlag = false;
			break;
		}

		case return_accounts_for_freeze: {
			BranchManagerFreezeAccountController.Users = (ArrayList<User>) receivedMessage.getMessageData();
			break;
		}

		case Account_Active: {
			BranchManagerFreezeAccountController.FreezeAccount = false;
			break;
		}

		case Account_Freeze: {
			BranchManagerFreezeAccountController.FreezeAccount = true;
			break;
		}

		case pdf_succ: {
			PopUpMessage.successMessage("Success import the pdf file!");
			break;
		}

		case Orders_List: {
			ConfirmOrderApprovalController.allOrders = (ArrayList<Order>) receivedMessage.getMessageData();
			break;
		}

		case changed_status_to_notApproved_succ: {
		}

		case changed_status_to_Approved_succ: {

		}

		case RegistrationOfEmployer_succ: {

		}

		case Delete_Account_Succ: {

		}

		default: {
			break;
		}

		}
	}
}