// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package main;

import client.*;
import client.controllers.BranchManagerScreenController;
import client.controllers.CustomerScreenController;
import client.controllers.LoginScreenController;
import client.controllers.OpenNewAccountController;
import client.controllers.OpenNewBussinessAccountController;
import client.controllers.OpenNewPrivateAccountController;
import client.controllers.SupplierScreenController;
import client.controllers.UpdateFormController;
import Entities.Message;
import Entities.MessageType;
import Entities.Order;
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
	} //

	public void handleMessageFromServer(Object msg) {
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
		if(mssg.getMessageType().equals(MessageType.BranchManager))
		{
			LoginScreenController.BMflag=true; 
			//System.out.println(LoginScreenController.BMflag);
		}
		if(mssg.getMessageType().equals(MessageType.Customer))
		{
			LoginScreenController.Customerflag=true; 
			LoginScreenController.Name=mssg.getMessageData().toString();
			//System.out.println(LoginScreenController.BMflag);
		}
		if(mssg.getMessageType().equals(MessageType.CEO))
		{
			LoginScreenController.CEOflag=true; 		
		}
		if(mssg.getMessageType().equals(MessageType.Supplier))
		{
			LoginScreenController.Supplierflag=true; 		
		}
		if(mssg.getMessageType().equals(MessageType.ReturnFirstName_success))
		{
			LoginScreenController.Name= mssg.getMessageData().toString();
		}
		if(mssg.getMessageType().equals(MessageType.OpenNewAccount))
		{
			BranchManagerScreenController.OpenNewAccountFlag=true; 		
		}
		if(mssg.getMessageType().equals(MessageType.OpenNewBussinesAccount))
		{
			OpenNewAccountController.OpenNewBussinesAccountFlag=true; 		
		}
		if(mssg.getMessageType().equals(MessageType.OpenNewPrivateAccount))
		{
			OpenNewAccountController.OpenNewPrivateAccountFlag=true; 		
		}
		if(mssg.getMessageType().equals(MessageType.WrongInput))
		{
			LoginScreenController.WrongInputFlag=true; 		
		}
		if(mssg.getMessageType().equals(MessageType.AlreadyLoggedIn)) {
			LoginScreenController.AlreadyLoggedInFlag=true; 		
		}
		if(mssg.getMessageType().equals(MessageType.ConfirmOpenNewBusinessAccount)) {
			OpenNewBussinessAccountController.ConfirmOpenNewBusinessAccountControllerFlag=true; 		
		}
		if(mssg.getMessageType().equals(MessageType.ConfirmOpenNewPrivateAccount)) {
			OpenNewPrivateAccountController.ConfirmOpenNewPrivateAccountFlag=true; 		
		}
		if(mssg.getMessageType().equals(MessageType.ConfirmEmployerRegistration))
		{
			BranchManagerScreenController.ConfirmEmployerRegistrationFlag=true; 		
		}
		if(mssg.getMessageType().equals(MessageType.CreateMenu))
		{
			SupplierScreenController.CreateMenuFlag=true; 		
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