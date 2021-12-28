package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import main.ClientUI;

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
	    private ImageView BackImage;

	    @FXML
	    private Button back;

	    @FXML
	    private ComboBox<String> combo1;

	    @FXML
	    private ImageView homePage;

	    @FXML
	    private Button next;

	    @FXML
	    private Label noSelect;

	    @FXML
	    private Text userName;
	    
	    public static ArrayList<String> cities;
		
		ObservableList<String> observableList;
		
		public static String cityName;

		/** This method meant to get back to login page and logout the customer
		 * @param event				pressing the "logout" button 
		 * @throws IOException
		 */
	    @FXML
	    void back(ActionEvent event) throws IOException {
	    	ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
			start(event, "LoginScreen", "Login","");

	    }

		/** This method meant to get back to costumer page
		 * @param event				pressing the "home" image 
		 * @throws IOException
		 */
	    @FXML
	    void backToHome(MouseEvent event) throws IOException {
	    	start(event, "CustomerScreen", "CustomerScreen","");
	    }

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
	    		start(event, "restListForm", "Restaurants list",cityName);
	        	/*Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        	FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/restListForm.fxml"));
				Parent root=load.load();
				RestListFormController aFrame = load.getController();
				aFrame.display(cityName);
				aFrame.start(stage,root);*/
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
		

	    @FXML
	    void initialize() {
	        assert BackImage != null : "fx:id=\"BackImage\" was not injected: check your FXML file 'ChooseRestaurant.fxml'.";
	        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'ChooseRestaurant.fxml'.";
	        assert combo1 != null : "fx:id=\"combo1\" was not injected: check your FXML file 'ChooseRestaurant.fxml'.";
	        assert homePage != null : "fx:id=\"homePage\" was not injected: check your FXML file 'ChooseRestaurant.fxml'.";
	        assert next != null : "fx:id=\"next\" was not injected: check your FXML file 'ChooseRestaurant.fxml'.";
	        assert noSelect != null : "fx:id=\"noSelect\" was not injected: check your FXML file 'ChooseRestaurant.fxml'.";
	        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'ChooseRestaurant.fxml'.";

	    }


		@Override
		public void display(String string) {
			userName.setText(LoginScreenController.user.getFirstN());
		}
	}

