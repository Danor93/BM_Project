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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientController;
import main.ClientUI;

	public class ClientMenuUiController {
	
		//public static Stage mainStage;
		//public static ClientController chat;
		public static ShowUpdateController ShowUpdateController;

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Button ConnectBtn;

	    @FXML
	    private TextField ipTxt;
	    
	    @FXML
	    void ConnectToServer(ActionEvent event) throws IOException {
	    	
	    	String ip;
	    	ip=ipTxt.getText();
	    	System.out.println(ip);
	    	if(ipTxt.getText().trim().isEmpty())
			{
				System.out.println("In order to update you must enter all fields");	
			}
	    	else
	    	{
	    		ClientUI.chat= new ClientController(ip, 5555);
	    		//Message msg = new Message(MessageType.login,null);
	        	//ClientUI.chat.accept(msg);
	    		FXMLLoader loader = new FXMLLoader();
	    		((Node) event.getSource()).getScene().getWindow().hide();
	    		Stage primaryStage = new Stage();
	    		Pane root = loader.load(getClass().getResource("/client/controllers/LoginScreen.fxml").openStream());
	    		Scene scene = new Scene(root);			
	    		primaryStage.setTitle("BiteMe");
	    		primaryStage.setScene(scene);
	    		//primaryStage.getIcons().add(new Image("/gui/ClientIcon.png"));
	    		primaryStage.show();
	    	}
	    }
	    
	    public void start(Stage primaryStage) throws Exception {
			primaryStage.setTitle("BiteMe");
			Parent root=FXMLLoader.load(getClass().getResource("/client/controllers/ClientMainUi.fxml"));
			Scene home=new Scene(root);
			primaryStage.setScene(home);
			//primaryStage.getIcons().add(new Image("/gui/ClientIcon.png"));
			primaryStage.show();
		}

}