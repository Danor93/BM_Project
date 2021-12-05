package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ChatClient;
import main.ClientController;
import main.ClientUI;


public class BranchManagerScreenController {
	public static boolean OpenNewAccountFlag = false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnConfirmEmployerRegistration;

    @FXML
    private Button btnOpenNewAccount;

    @FXML
    private Button btnChangePersonalInformation;

    @FXML
    private Button btnConfirmSupplierRegistration;

    @FXML
    private Button btnUploadPDF;

    @FXML
    private Button btnViewBranchsReports;

    @FXML
    private Button btnBack;

    @FXML
    void Back(ActionEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginScreen.fxml"));
		Scene scene = new Scene(root);		
		primaryStage.setTitle("BiteMe Login Panel");
		primaryStage.setScene(scene);		
		primaryStage.show();
		ClientUI.chat.accept(new Message(MessageType.Disconected,null));
}

    @FXML
    void initialize() {
        assert btnConfirmEmployerRegistration != null : "fx:id=\"btnConfirmEmployerRegistration\" was not injected: check your FXML file 'BranchManagerScreen.fxml'.";
        assert btnOpenNewAccount != null : "fx:id=\"btnOpenNewAccount\" was not injected: check your FXML file 'BranchManagerScreen.fxml'.";
        assert btnChangePersonalInformation != null : "fx:id=\"btnChangePersonalInformation\" was not injected: check your FXML file 'BranchManagerScreen.fxml'.";
        assert btnConfirmSupplierRegistration != null : "fx:id=\"btnConfirmSupplierRegistration\" was not injected: check your FXML file 'BranchManagerScreen.fxml'.";
        assert btnUploadPDF != null : "fx:id=\"btnUploadPDF\" was not injected: check your FXML file 'BranchManagerScreen.fxml'.";
        assert btnViewBranchsReports != null : "fx:id=\"btnViewBranchsReports\" was not injected: check your FXML file 'BranchManagerScreen.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'BranchManagerScreen.fxml'.";
    }
    
    public void start(Stage primaryStage) throws IOException {
		FXMLLoader load = new FXMLLoader();
		primaryStage.setTitle("BiteMe Branch Manager Panel");
		Pane root = load.load(getClass().getResource("/fxml/BranchManagerScreen.fxml").openStream());
		Scene home = new Scene(root);
		primaryStage.setScene(home);
		// primaryStage.getIcons().add(new Image("/gui/ServerIcon.png"));
		//lblName.setText("test"); 
		primaryStage.show();
	}
    
    @FXML
    void OpenNewAccount(ActionEvent event) throws IOException {
		Message msg = new Message(MessageType.OpenNewAccount, null);
		ClientUI.chat.accept(msg);
		if (OpenNewAccountFlag == true) {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			OpenNewAccountController aFrame = new OpenNewAccountController();
			aFrame.start(primaryStage);
			OpenNewAccountFlag = false;
		}
    }
}