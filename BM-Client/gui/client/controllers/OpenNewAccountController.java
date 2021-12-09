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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientUI;

public class OpenNewAccountController extends Controller implements ControllerInterface {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnBusinessAccount;

	@FXML
	private Button btnPrivateAccount;

	@FXML
	private Button btnBackToBranchManager;

	@FXML
	private ImageView BackImage;

	@FXML
	void initialize() {
		setImage(BackImage, "background.jpeg");
		assert btnBusinessAccount != null
				: "fx:id=\"btnBusinessAccount\" was not injected: check your FXML file 'OpenNewAccount.fxml'.";
		assert btnPrivateAccount != null
				: "fx:id=\"btnPrivateAccount\" was not injected: check your FXML file 'OpenNewAccount.fxml'.";
		assert btnBackToBranchManager != null
				: "fx:id=\"btnBackToBranchManager\" was not injected: check your FXML file 'OpenNewAccount.fxml'.";
	}

	@FXML
	void BusinessAccount(ActionEvent event) throws IOException {
		startScreen(event, "OpenNewBussinessAccount", "Open New Bussiness Account");
	}

	@FXML
	void PrivateAccount(ActionEvent event) throws IOException {
		startScreen(event, "OpenNewPrivateAccount", "Open New Private Account");
	}

	@Override
	public void Back(ActionEvent event) throws IOException {
		startScreen(event, "BranchManagerScreen", "Branch Manager");
	}

}