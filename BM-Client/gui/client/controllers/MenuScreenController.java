package client.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.ClientUI;

public class MenuScreenController extends Controller {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button back;

	@FXML
	private ImageView desserts;

	@FXML
	private ImageView drinks;

	@FXML
	private ImageView main;

	@FXML
	private ImageView order;

	@FXML
	private Text restName;

	@FXML
	private ImageView salads;

	@FXML
	private ImageView starters;

	@FXML
	private ImageView homePage;

	@FXML
	private Button logout;

	@FXML
	private Text userName;

	@FXML
	private Text dishAdded;

	public static String chosenFoodType;

	/**
	 * This method meant to get back to costumer page
	 * 
	 * @param event pressing the "home" image
	 * @throws IOException
	 */
	@FXML
	void backToHome(MouseEvent event) throws IOException {
		start(event, "CustomerScreen", "CustomerScreen", "");
	}

	/**
	 * This method meant to get back to login page and logout the customer
	 * 
	 * @param event pressing the "logout" button
	 * @throws IOException
	 */

    @FXML
    void logout(ActionEvent event) throws IOException {
    	logoutForCustomer();
    	ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login","");
    }
    

	/** This method meant to get back to the previous page
	 * @param event				pressing the "back" button 
	 * @throws IOException
	 */
    @FXML
    void backToRestChoose(ActionEvent event) throws IOException {
		start(event, "restListForm", "Restaurants list",ChooseRestController.cityName);
    }

	/**
	 * This method meant to show the dishes in the order
	 * 
	 * @param event pressing the "order now" image
	 * @throws IOException
	 */

	 @FXML
	    void orderNow(MouseEvent event) throws IOException {
			start(event, "ShowOrder", "Your order","");

	    }

	/**This method meant to show the desserts of the restaurant
     * @param event              pressing the dessert image
     * @throws IOException
     */
    @FXML
    void showDesserts(MouseEvent event) throws IOException {
    	chosenFoodType="Dessert";
    	start(event, "DishesOfKindScreen", "Restaurant's desserts",chosenFoodType);

    }

	/**This method meant to show the drinks of the restaurant
     * @param event              pressing the drink image
     * @throws IOException
     */
    @FXML
    void showDrinks(MouseEvent event) throws IOException {
    	chosenFoodType="Drink";
		start(event, "DishesOfKindScreen", "Restaurant's drinks",chosenFoodType);
    }



	/**
	 * This method meant to show the main dishes of the restaurant
	 * 
	 * @param event pressing the main dish image
	 * @throws IOException
	 */
    @FXML
    void showMainDishes(MouseEvent event) throws IOException {
    	chosenFoodType= "Main dish";
    	start(event, "DishesOfKindScreen", "Restaurant's Main dishes",chosenFoodType);
    }

	/**
	 * This method meant to show the salads of the restaurant
	 * 
	 * @param event pressing the salad image
	 * @throws IOException
	 */
	@FXML
	void showSalads(MouseEvent event) throws IOException {
		chosenFoodType = "Salad";
		start(event, "DishesOfKindScreen", "Restaurant's salads", chosenFoodType);
	}

	@FXML
	void showStarters(MouseEvent event) throws IOException {
		chosenFoodType = "Starter";
		start(event, "DishesOfKindScreen", "Restaurant's starters", chosenFoodType);
	}


	/**
	 * @param supplier
	 */
	@Override
	public void display(String string) {
		restName.setText(RestListFormController.chosenRst.getSupplierName());
		userName.setText(LoginScreenController.user.getFirstN());
		// restName.setText(supplier);
		dishAdded.setText(string);
	}


}
