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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ChatClient;
import main.ClientController;
import main.ClientUI;

	public class ClientMenuUiController extends Controller {

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Button ConnectBtn;

	    @FXML
	    private TextField ipTxt;
	    
	    @FXML
	    private ImageView LogoImage;
	    
	    @FXML
	    void ConnectToServer(ActionEvent event) throws IOException {
	    	String ip;
	    	ip=ipTxt.getText();
	    	/*if(ipTxt.getText().trim().isEmpty())
			{
				System.out.println("In order to update you must enter all fields");	
			}
	    	else
	    	{*/
	    		//ClientUI.chat= new ClientController(ip, 5555);
	    		ClientUI.chat= new ClientController("localhost", 5555);
	    		start(event,"BeforLogin","Description","");
	    		//Message msg = new Message(MessageType.login, null);
	    		//ClientUI.chat.accept(msg);
	    		//start(event,"BeforLogin","Description","");
	    	}
	    
	    public void start(Stage primaryStage) throws Exception {
			primaryStage.setTitle("BiteMe Main Client Panel");
			Parent root=FXMLLoader.load(getClass().getResource("/fxml/ClientMainUi.fxml"));
			Scene home=new Scene(root);
			primaryStage.setScene(home);
			//primaryStage.getIcons().add(new Image("/gui/ClientIcon.png"));
			primaryStage.show();
		}

		@Override
		public void display(String string) {
			
		}
}