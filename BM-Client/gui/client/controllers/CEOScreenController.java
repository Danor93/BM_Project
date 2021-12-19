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

public class CEOScreenController extends BranchManagerScreenController implements ControllerInterface,Initializable {
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
	private Button btnCreateOrder;

	@FXML
	private ImageView BackImage;


/**	@Override
	public void Back(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, null));
		startScreen(event, "LoginScreen", "Login");
	}**/

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

}