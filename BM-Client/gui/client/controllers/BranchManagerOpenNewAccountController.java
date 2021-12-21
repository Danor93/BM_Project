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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientUI;

public class BranchManagerOpenNewAccountController extends Controller implements ControllerInterface,Initializable {

	/*
	 * author:Danor
	 * this class for open new account
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
	 private Button BackBtn;

	@FXML
	private ImageView BackImage;

	@FXML
	void initialize() {
		//setImage(BackImage, "background.png");
		assert btnBusinessAccount != null
				: "fx:id=\"btnBusinessAccount\" was not injected: check your FXML file 'OpenNewAccount.fxml'.";
		assert btnPrivateAccount != null
				: "fx:id=\"btnPrivateAccount\" was not injected: check your FXML file 'OpenNewAccount.fxml'.";
	    assert BackBtn != null : "fx:id=\"BackBtn\" was not injected: check your FXML file 'BranchManagerOpenNewAccount.fxml'.";
	}

	/*for business account*/
	@FXML
	void BusinessAccount(ActionEvent event) throws IOException {
		startScreen(event,"BranchManagerOpenNewBussinessAccount", "Open New Bussiness Account");
	}

	/*for private account*/
	@FXML
	void PrivateAccount(ActionEvent event) throws IOException {
		startScreen(event,"BranchManagerOpenNewPrivateAccount", "Open New Private Account");
	}

	/*for back to the branch manager screen*/
	@Override
	public void Back(ActionEvent event) throws IOException {
		if(LoginScreenController.user.getRole().equals("CEO")) {
			startScreen(event, "CEOScreen", "CEO");
		}
		if(LoginScreenController.user.getRole().equals("BranchManager")) {
			startScreen(event, "BranchManagerScreen", "Branch Manager");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(LoginScreenController.user.getRole().equals("CEO")) {
			BackBtn.setText("Back to CEO Panel");
		}
		if(LoginScreenController.user.getRole().equals("BranchManager")) {
			BackBtn.setText("Back to Branch Manager Panel");
		}
	}
}