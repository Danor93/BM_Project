package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Interfaces.ControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.ClientUI;

/**
 * @author Danor
 * this class is for the main screen of the Branch Manager to choose which action he want.

 */
public class BranchManagerScreenController extends AbstractController implements ControllerInterface, Initializable {

	public static Stage stage;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnConfirmEmployerRegistration;

	@FXML
	private Button btnOpenNewAccount;

	@FXML
	private Button btnConfirmSupplierRegistration;

	@FXML
	private Button btnUploadPDF;

	@FXML
	private Button btnViewBranchsReports;

	@FXML
	private Button btnLogout;
	
    @FXML
    private Button ClostAccountBtn;

	@FXML
	private Label nameLabel;

	@FXML
	private ImageView BackImage;
	
	@FXML
	private Button ChangePBtn;

	/**
	 * for back to the login screen.
	 */
	@Override
	public void Back(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login","");
	}
	
	/**
	 * this will open the new account screen.
	 * @param event - for the open new account button.
	 * 
	 */
	@FXML
	void OpenNewAccount(ActionEvent event) throws IOException {
		start(event, "BranchManagerOpenNewAccount", "Open New Account",LoginScreenController.user.getFirstN());
	}


	/**
	 * open a screen for confirm an employer
	 * @param event - for the confirm employer button.
	 */
	@FXML
	void ConfirmEmployerReg(ActionEvent event) throws IOException {
		start(event, "ConfirmEmployerRegistartion", "Confirm Employer",LoginScreenController.user.getFirstN());
	}

	/**
	 * open a screen for confirm an Supplier
	 * @param event - for the confirm supplier button.
	 */
	@FXML
	void ConfirmSupplierReg(ActionEvent event) throws IOException {
		start(event, "BranchManagerSupplierRegistration", "Supplier Registration",LoginScreenController.user.getFirstN());
	}

	/**
	 * open a screen for close an account
	 * @param event - for the close account button.
	 */
	@FXML
	void deleteAccount(ActionEvent event) throws IOException {
		start(event, "BranchManagerCloseAccount", "Close Account",LoginScreenController.user.getFirstN());
	}

	
	/**
	 * open a screen for Change Permissions of account.
	 * @param event - for the change permissions button.
	 */
	@FXML
	void ChangePermissions(ActionEvent event) throws IOException {
		start(event, "BranchManagerChangePermissions", "Change Permissions",LoginScreenController.user.getFirstN());
	}

	
	/**
	 * for upload the quarterly PDF.
	 * @param event - for the upload pdf button.
	 */
	@FXML
	void UploadPDF(ActionEvent event) throws IOException {
		start(event, "BranchManagerUploadPDF", "Upload PDF",LoginScreenController.user.getFirstN());
	}

	/**
	 * for view the branch monthly report
	 * @param event - for the branch manager report button.
	 */
	@FXML
	void ViewBranchManagerReport(ActionEvent event) throws IOException {
		start(event, "BranchManagerChooseReportToView", "View Report",LoginScreenController.user.getFirstN());
	}

	/**
	 * initialize the buttons style.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnLogout.getStylesheets().add("/css/buttons.css");
		btnOpenNewAccount.getStylesheets().add("/css/buttons.css");
		ChangePBtn.getStylesheets().add("/css/buttons.css");
		btnConfirmEmployerRegistration.getStylesheets().add("/css/buttons.css");
		btnOpenNewAccount.getStylesheets().add("/css/buttons.css");
		btnViewBranchsReports.getStylesheets().add("/css/buttons.css");
		btnUploadPDF.getStylesheets().add("/css/buttons.css");
		btnConfirmSupplierRegistration.getStylesheets().add("/css/buttons.css");
		ClostAccountBtn.getStylesheets().add("/css/buttons.css");
	}

	/**
	 * display the name of the user.
	 */
	@Override
	public void display(String string) {
		nameLabel.setText(LoginScreenController.user.getFirstN() + " " + LoginScreenController.user.getLastN());
	}
}