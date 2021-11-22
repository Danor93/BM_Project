package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Entities.Message;
import Entities.MessageType;

public class UpdateFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private Button fUpdateBtn;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblType;
    
    @FXML
    private Label updateLbl;

    @FXML
    private TextField txtAddress; 

    @FXML
    private TextField txtType;
    
    public static boolean flagUpdate=false;

    @FXML
    void back(ActionEvent event) throws IOException 
    {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("Showupdate.fxml"));
		Scene scene = new Scene(root);		
		primaryStage.setTitle("BiteMe");
		primaryStage.setScene(scene);		
		primaryStage.show();
    }

    @FXML
    void sendDataUpdate(ActionEvent event)
    {
		FXMLLoader loader = new FXMLLoader();
		
		if(txtAddress.getText().trim().isEmpty()||txtType.getText().trim().isEmpty())
		{
			System.out.println("In order to update you must enter all fields");	
		}
		StringBuilder str=new StringBuilder();
		str.append(txtAddress.getText()+"@");
		str.append(txtType.getText());
		Message msg = new Message(MessageType.Show_Orders,str);
		ChatClient.chatClient.handleMessageFromClientUI(msg);
		
		if(flagUpdate==true)
		{
			updateLbl.setText("Update Successed!");	
			flagUpdate=false;
		}
		else
		{
			updateLbl.setText("Update Failed!");
		}

    }  

}