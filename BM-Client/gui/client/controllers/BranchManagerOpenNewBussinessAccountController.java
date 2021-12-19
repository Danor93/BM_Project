package client.controllers;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import Entities.BussinessAccount;
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
import main.PopUpMessage;
import Entities.BussinessAccount;

public class BranchManagerOpenNewBussinessAccountController extends Controller implements ControllerInterface, Initializable {

	/*
	 * Author:danor
	 * this class is for open business account
	  */
	public static BussinessAccount  BAccount = new BussinessAccount(null, null, null, null, null, null, null, null, null, null, null, null);
	public static Boolean AprrovedFlag=false;
	public static Boolean ConfirmFlag=false;

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

	@Override
	public void Back(ActionEvent event) throws IOException {
		startScreen(event, "BranchManagerOpenNewAccount", "Open New Account");
	}

	/*for confirm button*/
	@FXML
	void ConfirmBussinessAccount(ActionEvent event) {
		if (txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtID.getText().isEmpty()
				|| txtTelephone.getText().isEmpty() || txtEmail.getText().isEmpty()
				|| txtEmployersName.getText().isEmpty() || txtMonthlyBillingCeiling.getText().isEmpty()) {
			PopUpMessage.errorMessage("you must fill all of the fileds!");
		}
		else {
			ClientUI.chat.accept(new Message(MessageType.check_account_employer_approved,txtEmployersName.getText().toString()));
			if(AprrovedFlag==true) {
				BAccount.setFirstN(txtFirstName.getText());
				BAccount.setLastN(txtLastName.getText());
				BAccount.setId(txtID.getText());
				BAccount.setPhone(txtTelephone.getText());
				BAccount.setEmail(txtEmail.getText());
				BAccount.setCompanyName( txtEmployersName.getText());
				BAccount.setBudget(txtMonthlyBillingCeiling.getText());
				AprrovedFlag=false;
				ClientUI.chat.accept(new Message(MessageType.New_BAccount,BAccount));
				if(ConfirmFlag==true) {
					PopUpMessage.successMessage("The bussiness account has beed confirmed!");
					ConfirmFlag=false;
				}
			}
			else {
				PopUpMessage.errorMessage("your Employer doesn't Approved yet!");
			}
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//setImage(BackImage, "background.png");
	}
	
    @FXML
    void initialize() {
        assert BackImage != null : "fx:id=\"BackImage\" was not injected: check your FXML file 'BranchManagerOpenNewBussinessAccount.fxml'.";
        assert btnBackToOpenNewAccount != null : "fx:id=\"btnBackToOpenNewAccount\" was not injected: check your FXML file 'BranchManagerOpenNewBussinessAccount.fxml'.";
        assert btnConfirm != null : "fx:id=\"btnConfirm\" was not injected: check your FXML file 'BranchManagerOpenNewBussinessAccount.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'BranchManagerOpenNewBussinessAccount.fxml'.";
        assert txtEmployersName != null : "fx:id=\"txtEmployersName\" was not injected: check your FXML file 'BranchManagerOpenNewBussinessAccount.fxml'.";
        assert txtFirstName != null : "fx:id=\"txtFirstName\" was not injected: check your FXML file 'BranchManagerOpenNewBussinessAccount.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'BranchManagerOpenNewBussinessAccount.fxml'.";
        assert txtLastName != null : "fx:id=\"txtLastName\" was not injected: check your FXML file 'BranchManagerOpenNewBussinessAccount.fxml'.";
        assert txtMonthlyBillingCeiling != null : "fx:id=\"txtMonthlyBillingCeiling\" was not injected: check your FXML file 'BranchManagerOpenNewBussinessAccount.fxml'.";
        assert txtTelephone != null : "fx:id=\"txtTelephone\" was not injected: check your FXML file 'BranchManagerOpenNewBussinessAccount.fxml'.";
    }
}