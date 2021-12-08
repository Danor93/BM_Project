package Parsing;

import java.io.IOException;
import java.util.ArrayList;
import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import querys.DBCheck;
import querys.ShowOrders;
import querys.UpdateDB;
import querys.showCities;
import ocsf.server.ConnectionToClient;

public class Parsing {
	
	public static String result2;

	public static Message parsing(Object msg, ConnectionToClient client) {
		Message receivedMessage;
		Message messageFromServer;
		receivedMessage = (Message) msg;

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
			System.out.println(result);
			messageFromServer = new Message(MessageType.login, result);
			return messageFromServer;
		}

		case Show_Cities: {
			ArrayList<String> city = showCities.getCities();
			messageFromServer = new Message(MessageType.Show_Cities, city);
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
