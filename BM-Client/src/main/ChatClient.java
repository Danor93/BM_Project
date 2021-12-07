// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package main;

import client.*;
import client.controllers.BranchManagerScreenController;
import client.controllers.ChooseRestController;
import client.controllers.CustomerScreenController;
import client.controllers.LoginScreenController;
import client.controllers.UpdateFormController;
import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import Entities.User;
import Entities.homeBranches;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


import java.io.*;
import java.util.ArrayList;

import javax.security.auth.login.LoginContext;

public class ChatClient extends AbstractClient {
	public static ChatClient chatClient;
	public static ArrayList<Order> orders = new ArrayList<Order>();
	public static boolean waitingForResponse = false;

	public ChatClient(String host, int port) throws IOException {
		super(host, port);
		chatClient = this;
		openConnection();
		///adi1234
	}

	@SuppressWarnings("unchecked")
	public void handleMessageFromServer(Object msg) {
		System.out.println("--> get message from server");
		waitingForResponse = false;
		Message mssg = (Message) msg;
		
		/*if (mssg.getMessageType().equals(MessageType.Show_Orders_succ)) {
			ArrayList<Order> arr = (ArrayList<Order>) mssg.getMessageData();
			orders=arr;
		}
		if (mssg.getMessageType().equals(MessageType.Update_succesfuly)) {
			UpdateFormController.flagUpdate = true;
		}*/
		
		switch(mssg.getMessageType())
		{
		case login:
		{
			String[] DivedMsg = ((String) mssg.getMessageData()).split("@");
			
			if(!DivedMsg[0].equals("WrongInput"))
			{
				LoginScreenController.user=new User(DivedMsg[0],DivedMsg[1],DivedMsg[2],DivedMsg[3],DivedMsg[4],homeBranches.toHomeBranchType(DivedMsg[5]));
				LoginScreenController.LoginFlag=true;

			}
			
			break;
		}
		
		case Show_Cities:{
			ChooseRestController.cities=(ArrayList<String>)mssg.getMessageData();
			break;
		}
		
		
		case Disconected:
		{
			break;
		}
		
		default:
		{
			break;
		}
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