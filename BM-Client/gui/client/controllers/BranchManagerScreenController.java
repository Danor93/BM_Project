package client.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.stream.events.StartDocument;

import Entities.Message;
import Entities.MessageType;
import Entities.MyFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.ClientUI;

/**
 * this is the controller of the main branch screen.
 */
public class BranchManagerScreenController extends Controller implements ControllerInterface, Initializable {

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
	private Label nameLabel;

	@FXML
	private ImageView BackImage;
	
	  @FXML
	    private Button ChangePBtn;

	/**
	 * for back to login screen.
	 */
	@Override
	public void Back(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, null));
		startScreen(event, "LoginScreen", "Login");
	}

	@FXML
	void initialize() {
		// setImage(BackImage, "background.png");
		assert btnConfirmEmployerRegistration != null
				: "fx:id=\"btnConfirmEmployerRegistration\" was not injected: check your FXML file 'BranchManagerScreen.fxml'.";
		assert btnOpenNewAccount != null
				: "fx:id=\"btnOpenNewAccount\" was not injected: check your FXML file 'BranchManagerScreen.fxml'.";
		assert btnConfirmSupplierRegistration != null
				: "fx:id=\"btnConfirmSupplierRegistration\" was not injected: check your FXML file 'BranchManagerScreen.fxml'.";
		assert btnUploadPDF != null
				: "fx:id=\"btnUploadPDF\" was not injected: check your FXML file 'BranchManagerScreen.fxml'.";
		assert btnViewBranchsReports != null
				: "fx:id=\"btnViewBranchsReports\" was not injected: check your FXML file 'BranchManagerScreen.fxml'.";
	}

	
	/**
	 * this will open the new account screen.
	 * @param event - for the open new account button.
	 * 
	 */
	@FXML
	void OpenNewAccount(ActionEvent event) throws IOException {
		startScreen(event, "BranchManagerOpenNewAccount", "Open New Account");
	}


	/**
	 * open a screen for confirm an employer
	 * @param event - for the confirm employer button.
	 */
	@FXML
	void ConfirmEmployerReg(ActionEvent event) throws IOException {
		startScreen(event, "ConfirmEmployerRegistartion", "Confirm Employer");
	}


	/**
	 * open a screen for confirm an Supplier
	 * @param event - for the confirm supplier button.
	 */
	@FXML
	void ConfirmSupplierReg(ActionEvent event) throws IOException {
		startScreen(event, "ConfirmSupplierRegistartion", "Confirm Supplier");
	}

	/**
	 * open a screen for close an account
	 * @param event - for the close account button.
	 */
	@FXML
	void deleteAccount(ActionEvent event) throws IOException {
		startScreen(event, "BranchManagerCloseAccount", "Close Account");
	}

	
	/**
	 * open a screen for Change Permissions of account.
	 * @param event - for the change permissions button.
	 */
	@FXML
	void ChangePermissions(ActionEvent event) throws IOException {
		startScreen(event, "BranchManagerChangePermissions", "Freeze Account");
	}

	
	/**
	 * for upload the quarterly PDF.
	 * @param event - for the upload pdf button.
	 */
	@FXML
	void UploadPDF(ActionEvent event) throws IOException {
		startScreen(event, "BranchManagerUploadPDF", "Upload PDF");
	}

	/**
	 * for view the branch monthly report
	 * @param event - for the branch manager report button.
	 */
	@FXML
	void ViewBranchManagerReport(ActionEvent event) throws IOException {
		startScreen(event, "BranchManagerChooseReportToView", "View Report");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*setImage(BackImage,"/Images/BackImageBiteMe.jpeg");*/
	}

}