package Parsing;

import java.util.ArrayList;

import Entities.Dish;
import Entities.Employer;
import Entities.Message;
import Entities.Restaurant;
import Entities.Supplier;
import Entities.User;
import Entities.homeBranches;
import client.controllers.ChangeInfoDBController;
import client.controllers.ChooseRestController;
import client.controllers.ConfirmEmployerRegController;
import client.controllers.ConfirmSupplierRegController;
import client.controllers.LoginScreenController;
import client.controllers.OpenNewBussinessAccountController;
import client.controllers.OpenNewPrivateAccountController;
import client.controllers.RestListFormController;

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
							DivedMsg[4], homeBranches.toHomeBranchType(DivedMsg[5]), DivedMsg[6], DivedMsg[7],
							DivedMsg[8]);
				}

			}
			break;
		}

		case Show_Cities: {
			ChooseRestController.cities = (ArrayList<String>) receivedMessage.getMessageData();
			break;
		}
		
		case show_Restaurants: {
			RestListFormController.restaurants= (ArrayList<Restaurant>) receivedMessage.getMessageData();
			break;
		}
		
		case get_Dishes: {
			RestListFormController.dishes= (ArrayList<Dish>) receivedMessage.getMessageData();
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

		case ConfirmOpenNewBusinessAccount: {
			OpenNewBussinessAccountController.ConfirmOpenNewBusinessAccountControllerFlag = true;
			break;
		}

		case ConfirmOpenNewPrivateAccount: {
			OpenNewPrivateAccountController.ConfirmOpenNewPrivateAccountFlag = true;
			break;
		}
		
		case Dish_add_succ:{
			
		}
		
		case Employer_list:{
			ConfirmEmployerRegController.Employers=(ArrayList<Employer>) receivedMessage.getMessageData();
			break;
		}
		
		case Supplier_list:{
			ConfirmSupplierRegController.Suppliers=(ArrayList<Supplier>) receivedMessage.getMessageData();
			break;
		}
		
		case Delete_Account_Succ:{
			
		}

		default: {
			break;
		}

		}
	}
}