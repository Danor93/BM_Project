package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.ClientUI;
import javafx.scene.text.Text;

public class BranchManagerOpenNewAccountController extends Controller implements ControllerInterface, Initializable {

	/*
	 * author:Danor this class for open new account
	 */
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnBusinessAccount;

	@FXML
	private Button btnPrivateAccount;

	@FXML
	private ImageView BackImage;

	@FXML
	private ImageView homePage;

	@FXML
	private Button logout;

	@FXML
	private Text userName;
	
	@FXML
	void backToHome(MouseEvent event) throws IOException {
		start(event, "BranchManagerScreen", "Branch Manager",LoginScreenController.user.getUserName());
	}

	@FXML
	void logout(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event,"LoginScreen", "Login Screen","");
	}

	/* for business account */
	@FXML
	void BusinessAccount(ActionEvent event) throws IOException {
		start(event, "BranchManagerOpenNewBussinessAccount", "Open New Bussiness Account","");
	}

	/* for private account */
	@FXML
	void PrivateAccount(ActionEvent event) throws IOException {
		start(event, "BranchManagerOpenNewPrivateAccount", "Open New Private Account","");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnBusinessAccount.getStylesheets().add("/css/buttons.css");
		btnPrivateAccount.getStylesheets().add("/css/buttons.css");
		logout.getStylesheets().add("/css/buttons.css");
	}

	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN() + " " + LoginScreenController.user.getLastN());
	}

	@Override
	public void Back(ActionEvent event) throws IOException {
		// TODO Auto-generated method stub
		
	}
}