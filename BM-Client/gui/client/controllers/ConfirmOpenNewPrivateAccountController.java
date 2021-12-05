package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ConfirmOpenNewPrivateAccountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBackToBranchManager;

    @FXML
    void BackToBranchManagerScreen(ActionEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/BranchManagerScreen.fxml"));
		Scene scene = new Scene(root);		
		primaryStage.setTitle("BiteMe");
		primaryStage.setScene(scene);		
		primaryStage.show();
    }

    @FXML
    void initialize() {
        assert btnBackToBranchManager != null : "fx:id=\"btnBackToBranchManager\" was not injected: check your FXML file 'ConfirmOpenNewPrivateAccount.fxml'.";

    }

	public void start(Stage primaryStage) throws IOException {
		FXMLLoader load = new FXMLLoader();
		primaryStage.setTitle("BiteMe");
		Pane root = load.load(getClass().getResource("/fxml/ConfirmOpenNewPrivateAccount.fxml").openStream());
		Scene home = new Scene(root);
		primaryStage.setScene(home);
		primaryStage.show();		
	}
}