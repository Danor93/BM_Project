package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ChatClient;
import main.ClientController;
import main.ClientUI;

public class LoginScreenController {
	public static boolean BMflag = false;
	public static boolean CEOflag = false;
	public static boolean Customerflag = false;
	public static boolean Supplierflag = false;
	public static boolean nullflag = false;
	public static String Name = null;
	public static ActionEvent mainevent;
	// public static String Name;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	@FXML
	private TextField txtUserName;

	@FXML
	private TextField txtPassword;

	@FXML
	private Button btnLogin;

	@FXML
	private Label WrongInputInLoggin;

	@FXML
	void ConnectSystem(ActionEvent event) throws IOException {
		mainevent = event;
		StringBuilder str = new StringBuilder();
		str.append(txtUserName.getText());
		str.append("@");
		str.append(txtPassword.getText());
		Message msg = new Message(MessageType.loginSystem, str.toString());
		ClientUI.chat.accept(msg);
		System.out.println(msg.getMessageData().toString());
		if (BMflag == true) {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			BranchManagerScreenController aFrame = new BranchManagerScreenController();
			aFrame.start(primaryStage);
			BMflag = false;

		} else if (Customerflag == true) {
			// CustomerScreenController.TempName=Name;
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			CustomerScreenController aFrame = new CustomerScreenController();
			aFrame.start(primaryStage);
			Customerflag = false;
			
		} else if (CEOflag == true) {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			CEOScreenController aFrame = new CEOScreenController();
			aFrame.start(primaryStage);
			CEOflag = false;
		}
		
		else if (Supplierflag == true) {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			SupplierScreenController aFrame = new SupplierScreenController();
			aFrame.start(primaryStage);
			Supplierflag = false;
		}
		
		else if (nullflag == true) {
			WrongInputInLoggin.setText("Wrong Input");
			nullflag = false;
		}

	}
	
	@FXML
	void getUserName(InputMethodEvent event) {

	}

	@FXML
	void initialize() {
		assert txtUserName != null : "fx:id=\"txtUserName\" was not injected: check your FXML file 'LoginScreen.fxml'.";
		assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'LoginScreen.fxml'.";
		assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'LoginScreen.fxml'.";
	}
}