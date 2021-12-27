package client.controllers;

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

public class MenuScreenController extends Controller implements Initializable {

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

<<<<<<< HEAD
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
    
    /** This method meant to get back to costumer page
	 * @param event				pressing the "home" image 
	 * @throws IOException
	 */
    @FXML
    void backToHome(MouseEvent event) throws IOException {
    	start(event, "CustomerScreen", "CustomerScreen","");
    }
    
    
	/** This method meant to get back to login page and logout the customer
	 * @param event				pressing the "logout" button 
	 * @throws IOException
	 */

    @FXML
    void logout(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, null));
		start(event, "LoginScreen", "Login","");
    }
=======
	@FXML
	private ImageView starters;
>>>>>>> master

	public static String chosenFoodType;

<<<<<<< HEAD
    @FXML
    void backToRestChoose(ActionEvent event) throws IOException {
    	/*Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
=======
	@FXML
	void backToRestChoose(ActionEvent event) throws IOException {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
>>>>>>> master
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/restListForm.fxml"));
		Parent root = load.load();
		RestListFormController aFrame = load.getController();
<<<<<<< HEAD
		aFrame.start(primaryStage, root);*/
    }
    

	/** This method meant to show the dishes in the order
	 * @param event				pressing the "order now" image 
	 * @throws IOException
	 */
    
    @FXML
    void orderNow(MouseEvent event) throws IOException {
    	/*Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
=======
		aFrame.start(primaryStage, root);
	}

	@FXML
	void orderNow(MouseEvent event) throws IOException {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
>>>>>>> master
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/ShowOrder.fxml"));
		Parent root = load.load();
		ShowOrderController aFrame = load.getController();
		aFrame.start(primaryStage, root);
		*/
		start(event, "ShowOrder", "Your order","");

	}

<<<<<<< HEAD
    /**This method meant to show the desserts of the restaurant
     * @param event              pressing the dessert image
     * @throws IOException
     */
    @FXML
    void showDesserts(MouseEvent event) throws IOException {
    	chosenFoodType="Dessert";
    	/*Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
=======
	@FXML
	void showDesserts(MouseEvent event) throws IOException {
		chosenFoodType = "Dessert";
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
>>>>>>> master
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DishesOfKindScreen.fxml"));
		Parent root = load.load();
		ChoosingDishesController aFrame = load.getController();
		aFrame.display(chosenFoodType);
<<<<<<< HEAD
		aFrame.start(primaryStage,root);*/
    	
    	start(event, "DishesOfKindScreen", "Restaurant's desserts",chosenFoodType);
=======
		aFrame.start(primaryStage, root);
>>>>>>> master

	}

<<<<<<< HEAD
    /**This method meant to show the drinks of the restaurant
     * @param event              pressing the drink image
     * @throws IOException
     */
    @FXML
    void showDrinks(MouseEvent event) throws IOException {
    	chosenFoodType="Drink";
    	/*Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
=======
	@FXML
	void showDrinks(MouseEvent event) throws IOException {
		chosenFoodType = "Drink";
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
>>>>>>> master
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DishesOfKindScreen.fxml"));
		Parent root = load.load();
		ChoosingDishesController aFrame = load.getController();

		aFrame.display(chosenFoodType);
<<<<<<< HEAD
		aFrame.start(primaryStage,root);*/
		
		start(event, "DishesOfKindScreen", "Restaurant's drinks",chosenFoodType);
		
=======
		aFrame.start(primaryStage, root);
>>>>>>> master

	}

<<<<<<< HEAD
    /**This method meant to show the main dishes of the restaurant
     * @param event              pressing the main dish image
     * @throws IOException
     */
    @FXML
    void showMainDishes(MouseEvent event) throws IOException {
    	chosenFoodType= "Main dish";
    	/*Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
=======
	@FXML
	void showMainDishes(MouseEvent event) throws IOException {
		chosenFoodType = "Main dish";
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
>>>>>>> master
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DishesOfKindScreen.fxml"));
		Parent root = load.load();
		ChoosingDishesController aFrame = load.getController();

		aFrame.display("Main dish");
<<<<<<< HEAD
		aFrame.start(primaryStage,root);*/
    	
    	start(event, "DishesOfKindScreen", "Restaurant's Main dishes",chosenFoodType);
    }

    /**This method meant to show the salads of the restaurant
     * @param event              pressing the salad image
     * @throws IOException
     */
    @FXML
    void showSalads(MouseEvent event) throws IOException {
		chosenFoodType="Salad";
    	/*Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
=======
		aFrame.start(primaryStage, root);
	}

	@FXML
	void showSalads(MouseEvent event) throws IOException {
		chosenFoodType = "Salad";
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
>>>>>>> master
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DishesOfKindScreen.fxml"));
		Parent root = load.load();
		ChoosingDishesController aFrame = load.getController();
		System.out.println(chosenFoodType);
		aFrame.display(chosenFoodType);
<<<<<<< HEAD
		aFrame.start(primaryStage,root);*/
		
		start(event, "DishesOfKindScreen", "Restaurant's salads",chosenFoodType);
    }

    @FXML
    void showStarters(MouseEvent event) throws IOException {
    	chosenFoodType="Starter";
    	/*Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
=======
		aFrame.start(primaryStage, root);
	}

	@FXML
	void showStarters(MouseEvent event) throws IOException {
		chosenFoodType = "Starter";
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
>>>>>>> master
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DishesOfKindScreen.fxml"));
		Parent root = load.load();
		ChoosingDishesController aFrame = load.getController();
<<<<<<< HEAD
			
		aFrame.display(chosenFoodType);
		aFrame.start(primaryStage,root);*/
    	
    	start(event, "DishesOfKindScreen", "Restaurant's starters",chosenFoodType);
    }
    
=======

		aFrame.display(chosenFoodType);
		aFrame.start(primaryStage, root);

	}

>>>>>>> master
	/**
	 * @param stage
	 * @param root
	 * @throws IOException
	 */
	public void start(Stage stage, Parent root) throws IOException {
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param supplier
	 */
<<<<<<< HEAD
	public void display(String string) {
		restName.setText(RestListFormController.chosenRst.getSupplierName());
		userName.setText(LoginScreenController.user.getFirstN());
		//restName.setText(supplier);
		dishAdded.setText(string);
		
=======
	public void display(String supplier) {
		restName.setText(supplier);

>>>>>>> master
	}

	@FXML
	void initialize() {
		assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'MenuScreen.fxml'.";
		assert desserts != null : "fx:id=\"desserts\" was not injected: check your FXML file 'MenuScreen.fxml'.";
		assert drinks != null : "fx:id=\"drinks\" was not injected: check your FXML file 'MenuScreen.fxml'.";
		assert main != null : "fx:id=\"main\" was not injected: check your FXML file 'MenuScreen.fxml'.";
		assert order != null : "fx:id=\"order\" was not injected: check your FXML file 'MenuScreen.fxml'.";
		assert restName != null : "fx:id=\"restName\" was not injected: check your FXML file 'MenuScreen.fxml'.";
		assert salads != null : "fx:id=\"salads\" was not injected: check your FXML file 'MenuScreen.fxml'.";
		assert starters != null : "fx:id=\"starters\" was not injected: check your FXML file 'MenuScreen.fxml'.";

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setImage(desserts,"/Images/dessert.jpg");
		setImage(drinks,"/Images/drinks.jpg");
		setImage(salads,"/Images/salad.jpg");
		setImage(order,"/Images/showOrder.jpg");
		//setImage(starters,"/Images/starters.gif‬");
		setImage(main,"/Images/mainDish.png");
	}


}
