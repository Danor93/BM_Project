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
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CustomerScreenController {

	public String TempName; 
	public static CustomerScreenController cs; 
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreateOrder;

    @FXML
    private Button btnBack;
    
    @FXML
    private Label lblName;
    
    public void start(Stage primaryStage) throws IOException {
		//lblName.setText("test"); 
		FXMLLoader load = new FXMLLoader();
		primaryStage.setTitle("BiteMe Costomer Panel");
		Pane root = load.load(getClass().getResource("/fxml/CustomerScreen.fxml").openStream());
		Scene home = new Scene(root);
		primaryStage.setScene(home);
	//	lblName.setText(TempName); 
		// primaryStage.getIcons().add(new Image("/gui/ServerIcon.png"));
		//lblName.setText("test"); 
		primaryStage.show();
		//lblName.setText("test"); 
	}

    @FXML
    void Back(ActionEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginScreen.fxml"));
		Scene scene = new Scene(root);		
		primaryStage.setTitle("BiteMe");
		primaryStage.setScene(scene);		
		primaryStage.show();
    }
   
    @FXML
    void initialize() {
    }
}
