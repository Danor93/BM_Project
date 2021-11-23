// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.AbstractClient;
import client.*;
import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import Server.EchoServer;
import gui.ShowFormController;
import gui.UpdateFormController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

/**
 * This class overrides some of the methods defined in the abstract superclass
 * in order to give more functionality to the client.
 */
public class ChatClient extends AbstractClient {
	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	public static ChatClient chatClient;
	public static ArrayList<Order> orders = new ArrayList<Order>();
	public static boolean waitingForResponse = false;

	// Constructors ****************************************************

	/**
	 * 
	 * Constructs an instance of the chat client.
	 *
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientUI The interface type variable.
	 */

	public ChatClient(String host, int port) throws IOException {
		super(host, port);
		chatClient = this;
		openConnection();
	}

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	public void handleMessageFromServer(Object msg) {
		System.out.println("--> get message from server");
		waitingForResponse = false;
		Message mssg = (Message) msg;
		int i=0;
		if (mssg.getMessageType().equals(MessageType.Show_Orders_succ)) {
			ArrayList<Order> arr = (ArrayList<Order>) mssg.getMessageData();
			while(i<arr.size())
			{
				orders.add(arr.get(i));
				i++;
			}
		}
		if (mssg.getMessageType().equals(MessageType.Update_succesfuly)) {
			UpdateFormController.flagUpdate = true;
		}
	}
	/**
	 * 
	 *
	 * @param message The message from the UI.
	 */

	public void handleMessageFromClientUI(Object message) {
		try {
			openConnection();// in order to send more than one message
			waitingForResponse = true;
			sendToServer(message);
			// wait for response
			while (waitingForResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Error Connection");
			errorAlert.setContentText("cant connect to server");
			errorAlert.showAndWait();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}
}
//End of ChatClient class
