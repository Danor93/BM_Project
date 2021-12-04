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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientUI;

public class OpenNewPrivateAccountController {
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
    void BackToNewAccountScreen(ActionEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/client/controllers/OpenNewAccount.fxml"));
		Scene scene = new Scene(root);		
		primaryStage.setTitle("BiteMe");
		primaryStage.setScene(scene);		
		primaryStage.show();
    }

    @FXML
    void Confirm(ActionEvent event) throws IOException {
    	Account account = new Account(null, null, null, null, null, null, null, null);
		Message msg = new Message(MessageType.ConfirmOpenNewPrivateAccount, null);
		ClientUI.chat.accept(msg);
		if (ConfirmOpenNewPrivateAccountFlag == true) {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			ConfirmOpenNewPrivateAccountController aFrame = new ConfirmOpenNewPrivateAccountController();
			aFrame.start(primaryStage);
			ConfirmOpenNewPrivateAccountFlag = false;
		}
    }

    @FXML
    void initialize() {
        assert btnBackToOpenNewAccount != null : "fx:id=\"btnBackToOpenNewAccount\" was not injected: check your FXML file 'OpenNewPrivateAccount.fxml'.";
        assert txtFirstName != null : "fx:id=\"txtFirstName\" was not injected: check your FXML file 'OpenNewPrivateAccount.fxml'.";
        assert txtLastName != null : "fx:id=\"txtLastName\" was not injected: check your FXML file 'OpenNewPrivateAccount.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'OpenNewPrivateAccount.fxml'.";
        assert txtTelephone != null : "fx:id=\"txtTelephone\" was not injected: check your FXML file 'OpenNewPrivateAccount.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'OpenNewPrivateAccount.fxml'.";
        assert txtCreditCardNumber != null : "fx:id=\"txtCreditCardNumber\" was not injected: check your FXML file 'OpenNewPrivateAccount.fxml'.";
        assert btnConfirm != null : "fx:id=\"btnConfirm\" was not injected: check your FXML file 'OpenNewPrivateAccount.fxml'.";

    }

	public void start(Stage primaryStage) throws IOException {
		FXMLLoader load = new FXMLLoader();
		primaryStage.setTitle("BiteMe");
		Pane root = load.load(getClass().getResource("/client/controllers/OpenNewPrivateAccount.fxml").openStream());
		Scene home = new Scene(root);
		primaryStage.setScene(home);
		primaryStage.show();		
	}
}