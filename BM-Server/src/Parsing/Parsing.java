package Parsing;

import java.io.IOException;
import java.util.ArrayList;
import Entities.Dish;
import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import Entities.Restaurant;
import ocsf.server.ConnectionToClient;
import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import querys.DBCheck;
import querys.ShowDishes;
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

		case ID_exists: {
			String id;
			id = DBCheck.IDcheck((String) receivedMessage.getMessageData());
			if (id == null) {
				messageFromServer = new Message(MessageType.ID_Exists_False, null);
			} else {
				messageFromServer = new Message(MessageType.ID_Exists_True, null);
			}

			return messageFromServer;
		}

		case add_new_dish: {
			System.out.println(receivedMessage.getMessageData());
			if (UpdateDB.NewDish((Dish) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.Dish_add_succ, null);
			}
		}

		case Disconected: {
			UpdateDB.UpdateisLoggedIn(result2);
			messageFromServer = new Message(MessageType.Disconected, null);
			return messageFromServer;
		}

		case Show_Dishes: {// get all orders from DB
			System.out.println(receivedMessage.getMessageData());
			ArrayList<Dish> dishes = ShowDishes.getDishes(receivedMessage.getMessageData());
			for (int i = 0; i < dishes.size(); i++)
				System.out.println(dishes.get(i).getDishName());
			messageFromServer = new Message(MessageType.Show_Dishes_succ, dishes);
			return messageFromServer;
		}

		case updateDish: {
			System.out.println("receivedMessage= " + receivedMessage.getMessageData());
			if (UpdateDB.UpdateDish((Dish) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.Dish_update_succ, null);
			}
		}

		default: {
			messageFromServer = new Message(MessageType.Error, null);
			return messageFromServer;
		}

		}
	}
}
