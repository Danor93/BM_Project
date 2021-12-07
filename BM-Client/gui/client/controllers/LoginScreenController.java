package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.User;
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
	public static boolean LoginFlag = false;
	public static User user;


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
		StringBuilder str = new StringBuilder();
		//need to check if the fields are empty
		str.append(txtUserName.getText());
		str.append("@");
		str.append(txtPassword.getText());

		Message msg = new Message(MessageType.loginSystem, str.toString());
		
		ClientUI.chat.accept(msg);
		
		if(LoginFlag)
		{
			LoginFlag=false;
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			if (user.getRole().equals("BranchManager")) {
				BranchManagerScreenController aFrame = new BranchManagerScreenController();
				//aFrame.start(primaryStage);

			} else if (user.getRole().equals("Customer")) {	
				FXMLLoader load = new FXMLLoader(getClass().getResource("/client/controllers/CustomerScreen.fxml"));
				Parent root=load.load();
				CustomerScreenController aFrame = load.getController();
				aFrame.display(user.getFirstN());
				aFrame.start(primaryStage,root);
				
				
			} else if (user.getRole().equals("CEO")) {
				CEOScreenController aFrame = new CEOScreenController();
				//aFrame.start(primaryStage);
			}
		}
		
		else
		{
			WrongInputInLoggin.setText("User name or password are incorrect, please try again!");
		}
		
	}
	
    public void start(Stage primaryStage) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/client/controllers/LoginScreen.fxml").openStream());
		Scene scene = new Scene(root);			
		primaryStage.setTitle("BiteMe");
		primaryStage.setScene(scene);
		//primaryStage.getIcons().add(new Image("/gui/ClientIcon.png"));
		primaryStage.show();

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