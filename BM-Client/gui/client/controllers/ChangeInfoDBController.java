package client.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import main.ClientUI;

public class ChangeInfoDBController extends Controller {

	public static boolean idFalseFlag=false;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView BackImage;

    @FXML
    private TextField TxtID;

    @FXML
    private Button btncon;

    @FXML
    private Label lblError;

    @FXML
    void Continue(ActionEvent event) {
    	
    if(TxtID.getText()==null)
    {
    	lblError.setText("ID filed is empty!");
    }
    else {
    	String id = TxtID.getText();
    	Message m = new Message(MessageType.ID_exists,id);
    	ClientUI.chat.accept(m);
    	if(idFalseFlag==false)
    	{
    		lblError.setText("ID not Exsits on the DB!");
    	}
    	else {
    		//here we need to add FXML document.
    	}
    }
   
    
    

    }

    @FXML
    void initialize() {
    	setImage(BackImage,"background.jpeg");
        assert BackImage != null : "fx:id=\"BackImage\" was not injected: check your FXML file 'ChangeInfoDB.fxml'.";
        assert TxtID != null : "fx:id=\"TxtID\" was not injected: check your FXML file 'ChangeInfoDB.fxml'.";
        assert btncon != null : "fx:id=\"btncon\" was not injected: check your FXML file 'ChangeInfoDB.fxml'.";
        assert lblError != null : "fx:id=\"lblError\" was not injected: check your FXML file 'ChangeInfoDB.fxml'.";

    }

}
