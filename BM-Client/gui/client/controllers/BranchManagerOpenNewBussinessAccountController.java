package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import Entities.BussinessAccount;
import Entities.Message;
import Entities.MessageType;
import Entities.homeBranches;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.ClientUI;
import main.PopUpMessage;

/**
 * @author Danor this class implements the functionality of the Branch Manager
 *         to open new Business Account.
 */
public class BranchManagerOpenNewBussinessAccountController extends AbstractController implements Initializable {

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
	private TextField CreditCardText;

	@FXML
	private Label InvaildCreditCard;

	@FXML
	private Label InvaildCell;

	@FXML
	private Label InvaildEmail;

	@FXML
	private Label InvaildEmp;

	@FXML
	private Label InvaildFirstN;

	@FXML
	private Label InvaildID;

	@FXML
	private Label InvaildLastN;

	@FXML
	private Label InvaildTel;

	@FXML
	private ImageView homePage;

	@FXML
	private Button logout;

	@FXML
	private Text userName;

	@FXML
	private Button back;

	/**
	 * @param event - back to the home screen of the Branch Manager
	 */
	@FXML
	void backToHome(MouseEvent event) throws IOException {
		start(event, "BranchManagerScreen", "Branch Manager", LoginScreenController.user.getFirstN());
	}

	/**
	 * @param event - logout the user.
	 */
	@FXML
	void logout(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login Screen", "");
	}

	/**
	 * @param event - for back to the open new account choose screen.
	 */
	@FXML
	void backToMenu(ActionEvent event) throws IOException {
		start(event, "BranchManagerOpenNewAccount", "Open New Account", LoginScreenController.user.getFirstN());
	}

	/**
	 * for to confirm and check the new Business Account.
	 * 
	 * @param event - for confirm button.
	 */
	@FXML
	void ConfirmBussinessAccount(ActionEvent event) {
		if (txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtID.getText().isEmpty()
				|| txtTelephone.getText().isEmpty() || txtEmail.getText().isEmpty()
				|| txtEmployersName.getText().isEmpty()
				|| txtMonthlyBillingCeiling.getText().isEmpty()) {/* if the filed are empty. */
			PopUpMessage.errorMessage("you must fill all of the fileds!");
		} else {
			char[] charsFirstName = txtFirstName.getText().toCharArray();
			for (char c : charsFirstName) {

				if (!(Character.isLetter(c))) {
					InvaildFirstN.setText("Invaild Name!");
				}
			}

			char[] charsLastName = txtLastName.getText().toCharArray();
			for (char c : charsLastName) {

				if (!(Character.isLetter(c))) {
					InvaildLastN.setText("Invaild Name!");
				}
			}

			char[] charsIDName = txtID.getText().toCharArray();
			for (char c : charsIDName) {

				if (!(Character.isDigit(c))) {
					InvaildID.setText("Invaild ID!");
				}
			}

			char[] charsTelephone = txtTelephone.getText().toCharArray();
			for (char c : charsTelephone) {

				if (!(Character.isDigit(c))) {
					InvaildTel.setText("Invaild Telephone");
				}
			}

			boolean result = true;
			try {
				InternetAddress emailAddr = new InternetAddress(txtEmail.getText().toString());
				emailAddr.validate();
			} catch (AddressException ex) {
				result = false;
			}

			if (!result) {
				InvaildEmail.setText("Invaild Email!");
			}

			char[] charsEmp = txtEmployersName.getText().toCharArray();
			for (char c : charsEmp) {

				if (!(Character.isLetter(c))) {
					InvaildEmp.setText("Invaild Employer Name!");
				}
			}

			char[] charsCelling = txtMonthlyBillingCeiling.getText().toCharArray();
			for (char c : charsCelling) {

				if (!(Character.isDigit(c))) {
					InvaildCell.setText("Invaild Input!");
				}
			}

			char[] charsCreditCard = CreditCardText.getText().toCharArray();
			for (char c : charsCreditCard) {

				if (!(Character.isDigit(c))) {
					InvaildCreditCard.setText("Invaild Credit Card!");
				}
			}

			if (InvaildFirstN.getText().equals("") && InvaildLastN.getText().equals("")
					&& InvaildID.getText().equals("") && InvaildEmail.getText().equals("")
					&& InvaildEmp.getText().equals("") && InvaildCell.getText().equals("")
					&& InvaildTel.getText().equals("") && InvaildCreditCard.getText().equals("")) {
				ClientUI.chat.accept(new Message(MessageType.check_account_employer_approved,
						txtEmployersName.getText().toString()));
				if (AprrovedFlag) {
					BAccount.setFirstN(txtFirstName.getText());
					BAccount.setLastN(txtLastName.getText());
					BAccount.setId(txtID.getText());
					BAccount.setPhone(txtTelephone.getText());
					BAccount.setEmail(txtEmail.getText());
					BAccount.setCompanyName(txtEmployersName.getText());
					BAccount.setBudget(txtMonthlyBillingCeiling.getText());
					BAccount.setBranch(
							homeBranches.toHomeBranchType(LoginScreenController.user.getHomeBranch().toString()));
					BAccount.setRole("Customer");
					BAccount.setCreditCardNumber(CreditCardText.getText());
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
					PopUpMessage
							.errorMessage("your Employer -'" + BAccount.getCompanyName() + "' doesn't Approved yet!");
					AprrovedFlag = false;
				}
			}
		}

	}

	/**
	 * initialize the button functionality and style.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Checkdeatils = false;
		AprrovedFlag = false;
		btnConfirm.getStylesheets().add("/css/buttons.css");
		logout.getStylesheets().add("/css/buttons.css");
		back.getStylesheets().add("/css/buttons.css");
	}

	/**
	 * display the name of the user.
	 */
	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN() + " " + LoginScreenController.user.getLastN());
	}
}