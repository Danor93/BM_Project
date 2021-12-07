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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ChatClient;
import main.ClientController;
import main.ClientUI;

public class LoginScreenController extends Controller {
	public static boolean BMflag = false;
	public static boolean CEOflag = false;
	public static boolean Customerflag = false;
	public static boolean Supplierflag = false;
	public static boolean AlreadyLoggedInFlag = false;
	public static boolean WrongInputFlag = false;
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
    private ImageView loginImage;

	@FXML
	void ConnectSystem(ActionEvent event) throws IOException {
		mainevent = event;
		StringBuilder str = new StringBuilder();
		str.append(txtUserName.getText());
		str.append("@");
		str.append(txtPassword.getText());
		Message msg = new Message(MessageType.loginSystem, str.toString());
		ClientUI.chat.accept(msg);
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
		
		else if (AlreadyLoggedInFlag == true) {
			WrongInputInLoggin.setText("The user is already logged in");
			AlreadyLoggedInFlag = false;
		}
		
		else if (WrongInputFlag == true) {
			WrongInputInLoggin.setText("Wrong input");
			WrongInputFlag = false;
		}
	}
	
	@FXML
	void getUserName(InputMethodEvent event) {

	}

	@FXML
	void initialize() {
		super.setImage(loginImage, "LoginScreen.jpeg");
		assert txtUserName != null : "fx:id=\"txtUserName\" was not injected: check your FXML file 'LoginScreen.fxml'.";
		assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'LoginScreen.fxml'.";
		assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'LoginScreen.fxml'.";
	}
}