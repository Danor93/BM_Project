package Parsing;
//client
import java.util.ArrayList;

import Entities.Dish;
import Entities.Message;
import Entities.Restaurant;
import Entities.User;
import Entities.homeBranches;
import client.controllers.ChangeInfoDBController;
import client.controllers.ChooseRestController;
import client.controllers.DeleteOrUpdateDishController;
import client.controllers.LoginScreenController;
import client.controllers.OpenNewBussinessAccountController;
import client.controllers.OpenNewPrivateAccountController;
import client.controllers.RestListFormController;
import client.controllers.SupplierScreenController;

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

		case ConfirmOpenNewBusinessAccount: {
			OpenNewBussinessAccountController.ConfirmOpenNewBusinessAccountControllerFlag = true;
			break;
		}

		case ConfirmOpenNewPrivateAccount: {
			OpenNewPrivateAccountController.ConfirmOpenNewPrivateAccountFlag = true;
			break;
		}

		case ID_Exists_False: {
			ChangeInfoDBController.idFalseFlag = true;
			break;
		}

		case Show_Dishes_succ: {
			DeleteOrUpdateDishController.dishes = (ArrayList<Dish>) receivedMessage.getMessageData();
			break;
		}
		case MenuExistTrue: 
		{
			SupplierScreenController.ExisingMenuFlag=true; 
			break; 
		}
		case MenuExistFalse: 
		{
			SupplierScreenController.ExisingMenuFlag=false; 
			break; 
		}

		case Dish_add_succ: {

		}
		
		case Dish_update_succ: {

		}

		default: {
			break;
		}

		}
	}
}