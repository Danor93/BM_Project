package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientUI;
import Entities.Message;
import Entities.MessageType;

public class ShowUpdateController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button showBtn;

    @FXML
    private Button updateBtn;
    
    @FXML
    private Button CloseBtn;

    @FXML
    void showOrders(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	
    	Message msg = new Message(MessageType.Show_Orders,null);
    	ClientUI.chat.accept(msg);
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		Pane root = loader.load(getClass().getResource("/fxml/ShowForm.fxml").openStream());
		ShowFormController showFormController  = loader.getController();		
		showFormController.initialize(showFormController.getLocation(), showFormController.getResources());
		Scene scene = new Scene(root);			
		primaryStage.setTitle("Show Form");
		primaryStage.setScene(scene);	
		//primaryStage.getIcons().add(new Image("/gui/ClientIcon.png"));
		primaryStage.show();
    }

    @FXML
    void updateOrders(ActionEvent event) throws IOException 
    {
		FXMLLoader loader = new FXMLLoader();
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		Pane root = loader.load(getClass().getResource("/fxml/UpdateForm.fxml").openStream());
		Scene scene = new Scene(root);			
		primaryStage.setTitle("Update window");
		primaryStage.setScene(scene);
		//primaryStage.getIcons().add(new Image("/gui/ClientIcon.png"));
		primaryStage.show();
    }
    
	public void Close(ActionEvent event) {
		Stage stage = (Stage) CloseBtn.getScene().getWindow();
	    stage.close();
		Message msg = new Message(MessageType.Disconected,null);
    	ClientUI.chat.accept(msg);
	}

    @FXML
    void initialize() {
        assert showBtn != null : "fx:id=\"showBtn\" was not injected: check your FXML file 'Showupdate.fxml'.";
        assert updateBtn != null : "fx:id=\"updateBtn\" was not injected: check your FXML file 'Showupdate.fxml'.";
    }
  
	public void start(Stage primaryStage) throws IOException 
	{
		primaryStage.setTitle("BiteMe");
		Parent root=FXMLLoader.load(getClass().getResource("/fxml/Showupdate.fxml"));
		Scene home=new Scene(root);
		primaryStage.setScene(home);
		//primaryStage.getIcons().add(new Image("/gui/ClientIcon.png"));
		primaryStage.show();
	}

}


