package client.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

public class BranchManagerScreenController extends Controller implements ControllerInterface,Initializable {

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

	@Override
	public void Back(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, null));
		startScreen(event, "LoginScreen", "Login");
	}

	@FXML
	void initialize() {
		//setImage(BackImage, "background.png");
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

	@FXML
	void OpenNewAccount(ActionEvent event) throws IOException {
		startScreen(event, "BranchManagerOpenNewAccount", "Open New Account");
	}

	/*open a screen for confirm an employer*/
	@FXML
	void ConfirmEmployerReg(ActionEvent event) throws IOException {
		startScreen(event, "ConfirmEmployerRegistartion", "Confirm Employer");
	}

	/*open a screen for confirm an Supplier*/
	@FXML
	void ConfirmSupplierReg(ActionEvent event) throws IOException {
		startScreen(event, "ConfirmSupplierRegistartion", "Confirm Supplier");
	}

	/*open a screen for close an account*/
	@FXML
	void deleteAccount(ActionEvent event) throws IOException {
		startScreen(event, "BranchManagerCloseAccount", "Close Account");
	}
	
	
	/*open a screen for Freeze an account*/
	@FXML
	void FreezeAccount(ActionEvent event) throws IOException {
		startScreen(event, "BranchManagerFreezeAccount", "Freeze Account");
	}

	/*for upload the quarterly PDF.*/
	@FXML
	void UploadPDF(ActionEvent event) {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
			File file = fileChooser.showOpenDialog(BranchManagerScreenController.stage);
			if (file != null) {
				String path = file.getPath();
				File f = new File(path);
				MyFile msg = new MyFile(f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1));
				try {
					File newFile = new File(path);
					byte[] mybytearray = new byte[(int) newFile.length()];
					msg.initArray(mybytearray.length);
					msg.setSize(mybytearray.length);

					FileInputStream fis = new FileInputStream(newFile);
					BufferedInputStream bis = new BufferedInputStream(fis);

					bis.read(msg.getMybytearray(), 0, mybytearray.length);
					ClientUI.chat.accept(new Message(MessageType.send_PDF, msg));

				} catch (Exception e) {
					System.out.println("Error send (Files)msg) to Server");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}