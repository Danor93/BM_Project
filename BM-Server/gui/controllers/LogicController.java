package controllers;

import java.util.ArrayList;

import Entities.Message;
import Entities.MessageType;
import extra.ClientConnection;
import ocsf.server.ConnectionToClient;


public class LogicController {

	private static ArrayList<ClientConnection> clients = null;

	public static void UpdateClientTable(Object msg, ConnectionToClient client) {
		ClientConnection newClient = new ClientConnection(client);
		Message recivedMessage = (Message) msg;
		int flag = 0;
		if (recivedMessage.getMessageType().equals(MessageType.login)) {
			if (clients == null) {// first client

				clients = new ArrayList<ClientConnection>();
				clients.add(newClient);
				ServerUIFController.serveruifconroller.Update(clients); // update the table
			} else {

				for (int i = 0; i < clients.size(); i++) {// check if the client already connected to server
					if (clients.get(i).getHostName().equals(newClient.getHostName())) {
						clients.get(i).setStatus("Connected");// change to connect
						flag = 1;
						ServerUIFController.serveruifconroller.Update(clients); // update the table
						break;
					}
				}

				if (flag == 0) {
					clients.add(newClient);// new client
					ServerUIFController.serveruifconroller.Update(clients); // update the table
				}

				if (recivedMessage.getMessageType().equals(MessageType.Disconected)) {/*if client disconnected.*/
					for (int i = 0; i < clients.size(); i++)
						if (clients.get(i).getHostName().equals(newClient.getHostName()))
							clients.get(i).setStatus("Disconnected");
					ServerUIFController.serveruifconroller.Update(clients); // update the table
				}

			}
		}
	}
}
