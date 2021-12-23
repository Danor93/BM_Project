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

/** This class describes the start of the choosing restaurant process,starting from choosing city
 * @author Adi & Talia
 *
 */
public class ChooseRestController extends Controller implements Initializable{
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button next;
    

    @FXML
    private Button back;
    
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
    
    
    /**This method selects the wanted city from the combo box
     * @param event			pressing the combo box
     */
    @FXML
    void selectCity(ActionEvent event) {
    	cityName=combo1.getSelectionModel().getSelectedItem().toString();
    }



    /** Checks if the costumer chose a city from the combo box 
     * @param event					pressing the "next" button
     * @throws IOException
     */
    @FXML
    void proceedToRest(ActionEvent event) throws IOException {
    	if(cityName!=null && !cityName.equals("select"))
    	{
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/restListForm.fxml"));
			Parent root=load.load();
			RestListFormController aFrame = load.getController();
			aFrame.display(cityName);
			aFrame.start(stage,root);
    	}
    	
    	else
    	{
    		noSelect.setText("Please choose city");
    	}
    	
    }

	/**
	 *
	 */
	public void initialize(URL location, ResourceBundle resources) {
		Message msg=new Message(MessageType.Show_Cities,null);
		ClientUI.chat.accept(msg);
		observableList=FXCollections.observableArrayList(cities);
		combo1.setItems(observableList);	
	}
	
	
	/** This method meant to get back to costumer page
	 * @param event				pressing the "back" button 
	 * @throws IOException
	 */

    @FXML
    void back(ActionEvent event) throws IOException {
    	startScreen(event,"CustomerScreen","Costumer Screen");
    }


    @FXML
    void initialize() {
    	//setImage(BackImage, "background.jpeg");
        assert BackBtn != null : "fx:id=\"BackBtn\" was not injected: check your FXML file 'ChooseRestaurant.fxml'.";
        assert BackImage != null : "fx:id=\"BackImage\" was not injected: check your FXML file 'ChooseRestaurant.fxml'.";
        assert combo1 != null : "fx:id=\"combo1\" was not injected: check your FXML file 'ChooseRestaurant.fxml'.";
        assert next != null : "fx:id=\"next\" was not injected: check your FXML file 'ChooseRestaurant.fxml'.";
        assert noSelect != null : "fx:id=\"noSelect\" was not injected: check your FXML file 'ChooseRestaurant.fxml'.";

    }



}
