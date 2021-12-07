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

public class SupplierScreenController extends Controller {
	public static boolean CreateMenuFlag = false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreateMenu;

    @FXML
    private Button btnUpdateMenu;

    @FXML
    private Button btnUpdateOrderStatus;

    @FXML
    private Button btnBack;
    
    @FXML
    private ImageView BackImage;

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
    	setImage(BackImage,"background.jpeg");
        assert btnCreateMenu != null : "fx:id=\"btnCreateMenu\" was not injected: check your FXML file 'SupplierScreen.fxml'.";
        assert btnUpdateMenu != null : "fx:id=\"btnUpdateMenu\" was not injected: check your FXML file 'SupplierScreen.fxml'.";
        assert btnUpdateOrderStatus != null : "fx:id=\"btnUpdateOrderStatus\" was not injected: check your FXML file 'SupplierScreen.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'SupplierScreen.fxml'.";
    }
    
    public void start(Stage primaryStage) throws IOException {
		FXMLLoader load = new FXMLLoader();
		primaryStage.setTitle("BiteMe Supplier Panel");
		Pane root = load.load(getClass().getResource("/fxml/SupplierScreen.fxml").openStream());
		Scene home = new Scene(root);
		primaryStage.setScene(home);
		primaryStage.show();
	}
    
    @FXML
    void CreateMenu(ActionEvent event) throws IOException {
		Message msg = new Message(MessageType.CreateMenu, null);
		ClientUI.chat.accept(msg);
		if (CreateMenuFlag == true) {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			CreateMenuScreenController aFrame = new CreateMenuScreenController();
			aFrame.start(primaryStage);
			CreateMenuFlag = false;
		}
    }

}