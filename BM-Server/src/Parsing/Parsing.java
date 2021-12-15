package Parsing;

import java.io.IOException;
import java.util.ArrayList;
import Entities.Dish;
import Entities.Employer;
import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import Entities.Restaurant;
import Entities.Supplier;
import Entities.User;
import ocsf.server.ConnectionToClient;
import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import querys.DBCheck;
import querys.Query;
import querys.ShowOrders;
import querys.UpdateDB;
import querys.getDishes;
import querys.showCities;
import querys.showRestaurants;
import ocsf.server.ConnectionToClient;

public class Parsing {
	public static String result2;

	public static Message parsing(Object msg, ConnectionToClient client) {
		Message messageFromServer;
		Message receivedMessage = (Message) msg;

		switch (receivedMessage.getMessageType()) {
		case Show_Orders: {// get all orders from DB
			ArrayList<Order> order = ShowOrders.getOrders();
			messageFromServer = new Message(MessageType.Show_Orders_succ, order);
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
			result2 = DivededUandP[0];
			System.out.println(result);
			messageFromServer = new Message(MessageType.login, result);
			return messageFromServer;
		}

		case Show_Cities: {
			ArrayList<String> city = showCities.getCities();

			for (String s : city) {
				System.out.println(s);
			}
			messageFromServer = new Message(MessageType.Show_Cities, city);
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

		case ConfirmOpenNewBusinessAccount: {
			messageFromServer = new Message(MessageType.ConfirmOpenNewBusinessAccount, null);
			return messageFromServer;
		}

		case ConfirmOpenNewPrivateAccount: {
			messageFromServer = new Message(MessageType.ConfirmOpenNewPrivateAccount, null);
			return messageFromServer;
		}

		case add_new_dish: {
			System.out.println(receivedMessage.getMessageData());
			if (UpdateDB.NewDish((Dish) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.Dish_add_succ, null);
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
			ArrayList<Supplier> Suppliers = Query.LoadSuppliers();
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
		
		case get_Accounts:{
			User user = (User) receivedMessage.getMessageData();
			Query.DeleteAccount(user);
			messageFromServer = new Message(MessageType.Delete_Account_Succ, null);
			return messageFromServer;
		}

		case Disconected: {
			UpdateDB.UpdateisLoggedIn(result2);
			messageFromServer = new Message(MessageType.Disconected, null);
			return messageFromServer;
		}

		default: {
			messageFromServer = new Message(MessageType.Error, null);
			return messageFromServer;
		}

		}
	}
}
