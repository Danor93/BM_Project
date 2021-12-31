package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Entities.BussinessAccount;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientUI;
import main.PopUpMessage;
import Entities.BussinessAccount;
import javafx.scene.text.Text;

public class BranchManagerOpenNewBussinessAccountController extends Controller implements Initializable {

	/*
	 * Author:danor this class is for open business account
	 */
	public static BussinessAccount BAccount = new BussinessAccount(null, null, null, null, null, null, null, null, null,
			null, null, null);
	public static Boolean AprrovedFlag = false;
	public static Boolean Checkdeatils = false;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView BackImage;

	@FXML
	private Button btnBackToOpenNewAccount;

	@FXML
	private Button btnConfirm;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtEmployersName;

	@FXML
	private TextField txtFirstName;

	@FXML
	private TextField txtID;

	@FXML
	private TextField txtLastName;

	@FXML
	private TextField txtMonthlyBillingCeiling;

	@FXML
	private TextField txtTelephone;

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
	void ConfirmBussinessAccount(ActionEvent event) {
		if (txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtID.getText().isEmpty()
				|| txtTelephone.getText().isEmpty() || txtEmail.getText().isEmpty()
				|| txtEmployersName.getText().isEmpty() || txtMonthlyBillingCeiling.getText().isEmpty()) {
			PopUpMessage.errorMessage("you must fill all of the fileds!");
		} else {
			ClientUI.chat.accept(
					new Message(MessageType.check_account_employer_approved, txtEmployersName.getText().toString()));
			if (AprrovedFlag == true) {
				BAccount.setFirstN(txtFirstName.getText());
				BAccount.setLastN(txtLastName.getText());
				BAccount.setId(txtID.getText());
				BAccount.setPhone(txtTelephone.getText());
				BAccount.setEmail(txtEmail.getText());
				BAccount.setCompanyName(txtEmployersName.getText());
				BAccount.setBudget(txtMonthlyBillingCeiling.getText());
				BAccount.setBranch(
						homeBranches.toHomeBranchType(LoginScreenController.user.getHomeBranch().toString()));
				AprrovedFlag = false;
				ClientUI.chat.accept(new Message(MessageType.check_Baccount_details, BAccount));
				if (Checkdeatils) {
					PopUpMessage.successMessage("The bussiness account " + BAccount.getFirstN() + " "
							+ BAccount.getLastN() + " has been confirmed!");
					Checkdeatils = false;
				} else {
					PopUpMessage.errorMessage("one or more of the deatils is wrong!");
					Checkdeatils = false;
				}
			} else {
				PopUpMessage.errorMessage("your Employer -'" + BAccount.getCompanyName() + "' doesn't Approved yet!");
				AprrovedFlag = false;
			}
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Checkdeatils = false;
		AprrovedFlag = false;
		btnConfirm.getStylesheets().add("/css/buttons.css");
		logout.getStylesheets().add("/css/buttons.css");
		back.getStylesheets().add("/css/buttons.css");
	}

	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN() + " " + LoginScreenController.user.getLastN());

	}
}