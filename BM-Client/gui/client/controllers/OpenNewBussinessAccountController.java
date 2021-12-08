package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Account;
import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientUI;

public class OpenNewBussinessAccountController extends Controller {
	public static boolean ConfirmOpenNewBusinessAccountControllerFlag = false;

	@FXML
	private Button btnBackToOpenNewAccount;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private TextField txtEmployersName;

    @FXML
    private TextField txtMonthlyBillingCeiling;

    @FXML
    private Button btnConfirm;
    
    @FXML
    private ImageView BackImage;

    @FXML
    void Confirm(ActionEvent event) throws IOException {
    	Account account = new Account(null, null, null, null, null, null, null, null);
		Message msg = new Message(MessageType.ConfirmOpenNewBusinessAccount, null);
		ClientUI.chat.accept(msg);
		if (ConfirmOpenNewBusinessAccountControllerFlag == true) {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			ConfirmOpenNewBusinessAccountController aFrame = new ConfirmOpenNewBusinessAccountController();
			aFrame.start(primaryStage);
			ConfirmOpenNewBusinessAccountControllerFlag = false;
		}
    }

    @FXML
    void BackToNewAccountScreen(ActionEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/OpenNewAccount.fxml"));
		Scene scene = new Scene(root);		
		primaryStage.setTitle("BiteMe Open New Account Panel");
		primaryStage.setScene(scene);		
		primaryStage.show();
    }
    
    @FXML
    void initialize() {
    	setImage(BackImage,"background.jpeg");
        assert txtFirstName != null : "fx:id=\"txtFirstName\" was not injected: check your FXML file 'OpenNewBussinessAccount.fxml'.";
        assert txtLastName != null : "fx:id=\"txtLastName\" was not injected: check your FXML file 'OpenNewBussinessAccount.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'OpenNewBussinessAccount.fxml'.";
        assert txtTelephone != null : "fx:id=\"txtTelephone\" was not injected: check your FXML file 'OpenNewBussinessAccount.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'OpenNewBussinessAccount.fxml'.";
        assert txtEmployersName != null : "fx:id=\"txtEmployersName\" was not injected: check your FXML file 'OpenNewBussinessAccount.fxml'.";
        assert txtMonthlyBillingCeiling != null : "fx:id=\"txtMonthlyBillingCeiling\" was not injected: check your FXML file 'OpenNewBussinessAccount.fxml'.";
        assert btnConfirm != null : "fx:id=\"btnConfirm\" was not injected: check your FXML file 'OpenNewBussinessAccount.fxml'.";

    }

	public void start(Stage primaryStage) throws IOException {
		FXMLLoader load = new FXMLLoader();
		primaryStage.setTitle("BiteMe Open New Bussiness Account Panel");
		Pane root = load.load(getClass().getResource("/fxml/OpenNewBussinessAccount.fxml").openStream());
		Scene home = new Scene(root);
		primaryStage.setScene(home);
		primaryStage.show();				
	}
}