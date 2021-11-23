package gui;

import java.util.ArrayList;

import Entities.Message;
import Entities.MessageType;
import extra.ClientConnection;
import ocsf.server.ConnectionToClient;

public class UpdateClientTable {

	private static ArrayList<ClientConnection> client = null; // arraylist of all the connection clients

	/***
	 * this method handle the table of connected/disconnected clients
	 * 
	 * @param msg
	 * @param connection
	 */
	public static void UpdateClientTable(Object msg, ConnectionToClient connection) {
		ClientConnection newClient = new ClientConnection(connection);
		int flag = 0;
		Message resivedMessage = (Message) msg;
		if (resivedMessage.getMessageType() == MessageType.Show_Orders
				|| resivedMessage.getMessageType() == MessageType.Update_succesfuly) {

			if (client == null) {// first client
				client = new ArrayList<ClientConnection>();
				client.add(newClient);
				ServerUIFController.serveruifconroller.Update(client); // update the table
			} else {

				for (int i = 0; i < client.size(); i++) {// check if the client already connected to server
					if (client.get(i).getHostName().equals(newClient.getHostName())) {
						client.get(i).setStatus("Connected");// change to connect
						flag = 1;
						ServerUIFController.serveruifconroller.Update(client); // update the table

						break;
					}

				}
				if (flag == 0) {
					client.add(newClient);// new client
					ServerUIFController.serveruifconroller.Update(client); // update the table
				}
			}
		}
	}
}
