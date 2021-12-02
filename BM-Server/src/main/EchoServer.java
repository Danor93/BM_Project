package main;
// This file contains material supporting section 3.7 of the textbook:

// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.util.ArrayList;

import javax.swing.text.AbstractDocument.BranchElement;

import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import controllers.ServerUIFController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import querys.DBCheck;
import querys.DBConnect;
import querys.DBFirstName;
import querys.ShowOrders;
import querys.UpdateDB;
import server.AbstractServer;
import server.ConnectionToClient;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 */
public class EchoServer extends AbstractServer {
	// Class variables *************************************************
	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;
	public static ServerUIFController serverUIFController;

	public EchoServer(int port) {
		super(port);
	}

	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		// System.out.println(("Message received: " + msg + " from " + client));
		if (!(ServerUIFController.clients.contains(client))) {
			ClientConnection newClient = new ClientConnection(client);
			ServerUIFController.clients.add(newClient);
			ServerUIFController.serveruifconroller.Update(ServerUIFController.clients);
		}
		Message message = (Message) msg;
		Message messageFromServer = null;

		switch (message.getMessageType()) {

		case login:
			break;

		case Show_Orders: {// get all orders from DB
			ArrayList<Order> order = ShowOrders.getOrders();
			messageFromServer = new Message(MessageType.Show_Orders_succ, order);
			break;
		}
		case Update_Orders: {
			String[] DivededAdd = ((String) message.getMessageData()).split("@");
			UpdateDB.UpdateOrderAddress(DivededAdd[0]);
			UpdateDB.UpdateTypeOrder(DivededAdd[1]);
			messageFromServer = new Message(MessageType.Update_succesfuly, null);
			break;
		}

		case loginSystem: {
			String result;
			String[] DivededAdd = ((String) message.getMessageData()).split("@");
			result = DBCheck.DBCheck(DivededAdd[0], DivededAdd[1]);
			System.out.println(result);
			if (result.equals("Customer")) {
				// messageFromServer = new Message(MessageType.Customer, null);
				String result2;
				String[] DivededAdd2 = ((String) message.getMessageData()).split("@");
				result2 = DBFirstName.DBFirstName(DivededAdd2[0], DivededAdd2[1]);
				System.out.println(result2);
				messageFromServer = new Message(MessageType.Customer, null);

			} else if (result.equals("BranchManager")) {
				messageFromServer = new Message(MessageType.BranchManager, null);
			} else if (result.equals("CEO")) {
				messageFromServer = new Message(MessageType.CEO, null);
			} else {
				messageFromServer = new Message(MessageType.loginWrongInput, null);
			}
			break;
		}

		/*
		 * case ReturnFirstName: { String result; String[] DivededAdd = ((String)
		 * message.getMessageData()).split("@"); result =
		 * DBFirstName.DBFirstName(DivededAdd[0],DivededAdd[1]);
		 * System.out.println(result); messageFromServer = new
		 * Message(MessageType.ReturnFirstName_success, result); break; }
		 */
		case Disconected: {
			ClientConnection newClient = new ClientConnection(client);
			for (int i = 0; i < ServerUIFController.clients.size(); i++) {
				if (ServerUIFController.clients.get(i).getHostName().equals(newClient.getHostName())) {
					ServerUIFController.clients.get(i).setStatus("Disconnected");
				}
			}
			serverUIFController.Update(ServerUIFController.clients);
			
			messageFromServer = new Message(MessageType.Disconected, null);
			break;
		}

		default: {
			messageFromServer = new Message(MessageType.Error, null);
			break;
		}
		}
		this.sendToAllClients(messageFromServer);
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the server instance (there is
	 * no UI in this phase).
	 *
	 * @param args[0] The port number to listen on. Defaults to 5555 if no argument
	 *                is entered.
	 */
	public static void main(String[] args) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}

		EchoServer sv = new EchoServer(port);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
}
//End of EchoServer class
