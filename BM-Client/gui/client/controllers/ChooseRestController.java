package client.controllers;


import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ChooseRestController extends Controller implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button next;
    
	@FXML
    private ComboBox<String> combo1;

    @FXML
    private Label noSelect;
	
	public static ArrayList<String> cities;
	
	ObservableList<String> observableList;
	
	public static String cityName;
	
    @FXML
    private ImageView BackImage;
    
    @FXML
    private Button BackBtn;

    @FXML
    void selectCity(ActionEvent event) {
    	cityName=combo1.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void proceedToRest(ActionEvent event) throws IOException {
    	if(cityName!=null && !cityName.equals("select"))
    	{
    		startScreen(event, "restListForm", "Restaurant list");
    	}
    	
    	else
    	{
    		noSelect.setText("Please choose city");
    	}
    	
    }

	public void initialize(URL location, ResourceBundle resources) {
		Message msg=new Message(MessageType.Show_Cities,null);
		ClientUI.chat.accept(msg);
		observableList=FXCollections.observableArrayList(cities);
		combo1.setItems(observableList);	
	}
	
	
	@FXML
	public void BackToW4CScreen(ActionEvent event) throws IOException {
		startScreen(event, "InsertCodeOfW4C","Insert W4C");
	}

}
