package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Account;
import Entities.Client;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientUI;
import main.PopUpMessage;

public class BranchManagerOpenNewPrivateAccountController extends Controller
		implements ControllerInterface, Initializable {
	
	/*
	 * Author:Danor
	 * this class is for open new account
	 */
	public static Client PAccount = new Client(null, null, null, null, null, null, null, null, null, null, null);
	public static boolean ConfirmOpenNewPrivateAccountFlag = false;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnBackToOpenNewAccount;

	@FXML
	private TextField txtFirstName;

	@FXML
	private TextField txtLastName;

	@FXML
	private TextField txtID;

	@FXML
	private TextField txtTelephone;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtCreditCardNumber;

	@FXML
	private Button btnConfirm;

	@FXML
	private ImageView BackImage;

	/*for confirm button*/
	@FXML
	void Confirm(ActionEvent event) throws IOException {
		if (txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtID.getText().isEmpty()
				|| txtTelephone.getText().isEmpty() || txtEmail.getText().isEmpty()
				|| txtCreditCardNumber.getText().isEmpty()) {
			PopUpMessage.errorMessage("you must fill all of the fileds!");
		} else {
			ClientUI.chat.accept(new Message(MessageType.check_Private_accout_exits, txtID.getText().toString()));
			if (ConfirmOpenNewPrivateAccountFlag) {
				PAccount.setFirstN(txtFirstName.getText());
				PAccount.setLastN(txtLastName.getText());
				PAccount.setId(txtID.getText());
				PAccount.setPhone(txtTelephone.getText());
				PAccount.setEmail(txtEmail.getText());
				PAccount.setCreditCardNumber(txtCreditCardNumber.getText());
				ClientUI.chat.accept(new Message(MessageType.add_new_private_account, PAccount));
				PopUpMessage.successMessage("Account " + PAccount.getFirstN() + " has been added succefuly!");
				ConfirmOpenNewPrivateAccountFlag = false;
			}
		}
	}

	@FXML
	void initialize() {
		setImage(BackImage, "background.png");
		assert btnBackToOpenNewAccount != null
				: "fx:id=\"btnBackToOpenNewAccount\" was not injected: check your FXML file 'OpenNewPrivateAccount.fxml'.";
		assert txtFirstName != null
				: "fx:id=\"txtFirstName\" was not injected: check your FXML file 'OpenNewPrivateAccount.fxml'.";
		assert txtLastName != null
				: "fx:id=\"txtLastName\" was not injected: check your FXML file 'OpenNewPrivateAccount.fxml'.";
		assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'OpenNewPrivateAccount.fxml'.";
		assert txtTelephone != null
				: "fx:id=\"txtTelephone\" was not injected: check your FXML file 'OpenNewPrivateAccount.fxml'.";
		assert txtEmail != null
				: "fx:id=\"txtEmail\" was not injected: check your FXML file 'OpenNewPrivateAccount.fxml'.";
		assert txtCreditCardNumber != null
				: "fx:id=\"txtCreditCardNumber\" was not injected: check your FXML file 'OpenNewPrivateAccount.fxml'.";
		assert btnConfirm != null
				: "fx:id=\"btnConfirm\" was not injected: check your FXML file 'OpenNewPrivateAccount.fxml'.";

	}

	@Override
	public void Back(ActionEvent event) throws IOException {
		startScreen(event, "OpenNewAccount", "Open New Account");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ConfirmOpenNewPrivateAccountFlag = false;
	}
}