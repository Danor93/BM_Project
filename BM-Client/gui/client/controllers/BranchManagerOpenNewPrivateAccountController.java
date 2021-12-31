package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Account;
import Entities.Client;
import Entities.Message;
import Entities.MessageType;
import Entities.homeBranches;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientUI;
import main.PopUpMessage;
import javafx.scene.text.Text;

public class BranchManagerOpenNewPrivateAccountController extends Controller implements Initializable {

	/*
	 * Author:Danor this class is for open new account
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

	@FXML
	private ImageView homePage;

	@FXML
	private Button logout;

	@FXML
	private Text userName;

	@FXML
	private Button back;

	@FXML
	void backToHome(MouseEvent event) throws IOException {
		start(event, "BranchManagerScreen", "Branch Manager", LoginScreenController.user.getUserName());
	}

	@FXML
	void logout(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login Screen", "");
	}

	@FXML
	void backToMenu(ActionEvent event) throws IOException {
		start(event, "BranchManagerOpenNewAccount", "Open New Account", LoginScreenController.user.getUserName());
	}

	/* for confirm button */
	@FXML
	void Confirm(ActionEvent event) throws IOException {
		if (txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtID.getText().isEmpty()
				|| txtTelephone.getText().isEmpty() || txtEmail.getText().isEmpty()
				|| txtCreditCardNumber.getText().isEmpty()) {
			PopUpMessage.errorMessage("you must fill all of the fileds!");
		} else {
			PAccount.setFirstN(txtFirstName.getText());
			PAccount.setLastN(txtLastName.getText());
			PAccount.setId(txtID.getText());
			PAccount.setPhone(txtTelephone.getText());
			PAccount.setEmail(txtEmail.getText());
			PAccount.setCreditCardNumber(txtCreditCardNumber.getText());
			PAccount.setBranch(homeBranches.toHomeBranchType(LoginScreenController.user.getHomeBranch().toString()));
			ClientUI.chat.accept(new Message(MessageType.check_PAccount_details, PAccount));
			if (ConfirmOpenNewPrivateAccountFlag) {
				PopUpMessage.successMessage(
						"Account " + PAccount.getFirstN() + " " + PAccount.getLastN() + " has been added succefuly!");
				ConfirmOpenNewPrivateAccountFlag = false;
			} else {
				PopUpMessage.errorMessage("one or more of the deatils is wrong!");
				ConfirmOpenNewPrivateAccountFlag = false;
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ConfirmOpenNewPrivateAccountFlag = false;
		btnConfirm.getStylesheets().add("/css/buttons.css");
		logout.getStylesheets().add("/css/buttons.css");
		back.getStylesheets().add("/css/buttons.css");
	}

	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN() + " " + LoginScreenController.user.getLastN());
	}
}