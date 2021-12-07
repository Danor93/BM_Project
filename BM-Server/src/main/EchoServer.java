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
import querys.showCities;
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
	//public static ClientController ClientController;

	
	public EchoServer(int port) {
		super(port);
	}

	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		//System.out.println(("Message received: " + msg + " from " + client));
		Message message = (Message) msg;
		Message messageFromServer = null;
		switch (message.getMessageType()) {
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
			String[] DivededUandP = ((String) message.getMessageData()).split("@");
			result = DBCheck.DBCheck(DivededUandP[0],DivededUandP[1]);
			System.out.println(result);
			messageFromServer = new Message(MessageType.login,result);
			break;

		}
		
		case Show_Cities: {
			ArrayList<String> city = showCities.getCities();
			messageFromServer = new Message(MessageType.Show_Cities, city);
			break;
		}
		
	/*	case ReturnFirstName:
		{
			String result; 
			String[] DivededAdd = ((String) message.getMessageData()).split("@");
			result = DBFirstName.DBFirstName(DivededAdd[0],DivededAdd[1]);	
			System.out.println(result);
			messageFromServer = new Message(MessageType.ReturnFirstName_success, result);
			break;
		}*/
		case Disconected:{
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
