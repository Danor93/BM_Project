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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientUI;

public class OpenNewAccountController extends Controller {
	public static boolean OpenNewPrivateAccountFlag = false;
	public static boolean OpenNewBussinesAccountFlag = false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBusinessAccount;

    @FXML
    private Button btnPrivateAccount;

    @FXML
    private Button btnBackToBranchManager;
    
    @FXML
    private ImageView BackImage;

    @FXML
    void initialize() {
    	setImage(BackImage,"background.jpeg");
        assert btnBusinessAccount != null : "fx:id=\"btnBusinessAccount\" was not injected: check your FXML file 'OpenNewAccount.fxml'.";
        assert btnPrivateAccount != null : "fx:id=\"btnPrivateAccount\" was not injected: check your FXML file 'OpenNewAccount.fxml'.";
        assert btnBackToBranchManager != null : "fx:id=\"btnBackToBranchManager\" was not injected: check your FXML file 'OpenNewAccount.fxml'.";
    }
    
    @FXML
    void BackToBranchManagerScreen(ActionEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/BranchManagerScreen.fxml"));
		Scene scene = new Scene(root);		
		primaryStage.setTitle("BiteMe Branch Manager Panel");
		primaryStage.setScene(scene);		
		primaryStage.show();
    }
    
    @FXML
    void BusinessAccount(ActionEvent event) throws IOException {
		Message msg = new Message(MessageType.OpenNewBussinesAccount, null);
		ClientUI.chat.accept(msg);
		if (OpenNewBussinesAccountFlag == true) {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			OpenNewBussinessAccountController aFrame = new OpenNewBussinessAccountController();
			aFrame.start(primaryStage);
			OpenNewBussinesAccountFlag = false;
		}
    }

    @FXML
    void PrivateAccount(ActionEvent event) throws IOException {
		Message msg = new Message(MessageType.OpenNewPrivateAccount, null);
		ClientUI.chat.accept(msg);
		if (OpenNewPrivateAccountFlag == true) {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			OpenNewPrivateAccountController aFrame = new OpenNewPrivateAccountController();
			aFrame.start(primaryStage);
			OpenNewPrivateAccountFlag = false;
		}
    }

    public void start(Stage primaryStage) throws IOException {
		FXMLLoader load = new FXMLLoader();
		primaryStage.setTitle("BiteMe Open New Account Panel");
		Pane root = load.load(getClass().getResource("/fxml/OpenNewAccount.fxml").openStream());
		Scene home = new Scene(root);
		primaryStage.setScene(home);
		primaryStage.show();
	}
    

}