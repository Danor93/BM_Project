// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package main;

import client.controllers.UpdateFormController;
import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ocsf.client.AbstractClient;

import java.io.*;
import java.util.ArrayList;


public class ChatClient extends AbstractClient {
	public static ChatClient chatClient;
	public static ArrayList<Order> orders = new ArrayList<Order>();
	public static boolean waitingForResponse = false;

	public ChatClient(String host, int port) throws IOException {
		super(host, port);
		chatClient = this;
		openConnection();
	}

	public void handleMessageFromServer(Object msg) {
		System.out.println("--> get message from server");
		waitingForResponse = false;
		Message mssg = (Message) msg;
		if (mssg.getMessageType().equals(MessageType.Show_Orders_succ)) {
			ArrayList<Order> arr = (ArrayList<Order>) mssg.getMessageData();
			orders=arr;
		}
		if (mssg.getMessageType().equals(MessageType.Update_succesfuly)) {
			UpdateFormController.flagUpdate = true;
		}
		
		if(mssg.getMessageType().equals(MessageType.login))
		{
			
		}
		
		if(mssg.getMessageType().equals(MessageType.Disconected))
		{
			
		}
	}
	public void handleMessageFromClientUI(Object message) {
		try {
			//openConnection();// in order to send more than one message
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