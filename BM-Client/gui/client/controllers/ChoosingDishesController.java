package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Dish;
import Entities.DishType;
import Entities.Message;
import Entities.MessageType;
import Entities.SingletonOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.ClientUI;

/**
 * @author Adi
 *
 */
public class ChoosingDishesController extends Controller implements Initializable {

    @FXML
    private Button addToOrder;

    @FXML
    private Button back;

    @FXML
    private Text foodKind;

    @FXML
    private ListView<String> list;

    
    @FXML
    private TextArea ingredients;

    
    @FXML
    private Text notify;
    
    @FXML
    private Text dishLbl;
    
    @FXML
    private Button minus;

    @FXML
    private Button plus;
    
    @FXML
    private Label quantity;

    @FXML
    private AnchorPane ingPane;
    
    @FXML
    private Text price;
    

    @FXML
    private ImageView homePage;

    @FXML
    private Button logout;
    

    @FXML
    private Text userName;

    public ArrayList<String> dishNames;
	public ArrayList<Dish> dishListOfType;
	public static Dish chosenDish;
	public static int quentity=1;
	String selectedDish;
	float priceDish;

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
	    ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login","");
	 }

	 
    /**This method adds a dish to the order
     * @param event              pressing the "add to my order" button
     * @throws IOException
     */
    @FXML
    void addDishToOrder(ActionEvent event) throws IOException 
    {
    	String selectedDish =list.getSelectionModel().getSelectedItem();
    	
    	if(selectedDish==null)
    	{
    		notify.setText("Please choose dish");
    	}
    	
    	else
    	{
    		
    		int indexOfDish=dishNames.indexOf(selectedDish);
        	chosenDish=dishListOfType.get(indexOfDish);
        	if(!chosenDish.getChoiceFactor().equals("")||!chosenDish.getExtra().equals(""))
        	{
        		/*
        		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DishOptions.fxml"));
        		Parent root=load.load();
        		DishOptionsController aFrame = load.getController();
        		aFrame.setValue();
        		aFrame.start(primaryStage, root);*/
        		
        		start(event, "DishOptions", "choose dish details","");

        	}
        	
        	else
        	{
        		Dish dish=new Dish(chosenDish.getDishName(),chosenDish.getSupplierName(),chosenDish.getChoiceFactor(),null,chosenDish.getIngredients(),null,chosenDish.getPrice(),chosenDish.getDishType());
        		dish.setRestCode(RestListFormController.chosenRst.getRestCode());
        		dish.setQuentity(quentity);
        		SingletonOrder.getInstance().myOrder.add(dish);
        		
        		/*Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/MenuScreen.fxml"));
        		Parent root=load.load();
        		MenuScreenController aFrame = load.getController();
        		//aFrame.display(RestListFormController.chosenRst.getSupplierName(),"The dish was successfully added to your order");
        		aFrame.display("The dish was successfully added to your order");
        		aFrame.start(primaryStage,root);*/
        		
        		start(event, "MenuScreen","Restaurant's menu","The dish was successfully added to your order");
    
        		
        	}
    	}
    }
    
    
	/** This method meant to get back to the previous page
	 * @param event				pressing the "back" button 
	 * @throws IOException
	 */
    @FXML
    void backToMenu(ActionEvent event) throws IOException {
    	start(event,"MenuScreen","Restaurant's menu","");
    	/*Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/MenuScreen.fxml"));
		Parent root=load.load();
		MenuScreenController aFrame = load.getController();
		//aFrame.display(RestListFormController.chosenRst.getSupplierName(),"");
		aFrame.display("");
		aFrame.start(primaryStage,root);*/

    }
    
    @FXML
    void getDishPrice(MouseEvent event)
    {
    	quantity.setText("1");
    	quentity=1;
    	String s="price : ";
    	selectedDish =list.getSelectionModel().getSelectedItem();
    	if(selectedDish!=null)
    	{
    		int indexOfDish=dishNames.indexOf(selectedDish);
        	ingPane.setVisible(true);
        	dishLbl.setText(selectedDish);
        	priceDish=dishListOfType.get(indexOfDish).getPrice();
        	price.setText(s+priceDish+"$");
        	ingredients.setText(dishListOfType.get(indexOfDish).getIngredients());
        	minus.setDisable(false);
        	plus.setDisable(false);
    	}
    	

    }
    
    @FXML
    void decQuentity(ActionEvent event) {
    	if(quentity>1)
    		quentity--;
    	
    	quantity.setText(""+quentity);
    	price.setText("price : "+priceDish*quentity+"$");

    }
    
    
    @FXML
    void incQuentity(ActionEvent event) {
    	quentity++;
    	quantity.setText(""+quentity);
    	price.setText("price : "+priceDish*quentity+"$");
    }


	public void start(Stage stage, Parent root) {
		Scene scene = new Scene(root);		
		stage.setScene(scene);
		stage.show();
		
	}

	public void display(String string) 
	{
		foodKind.setText(string);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		dishNames=new ArrayList<>();
		dishListOfType= new ArrayList<>();
		for(Dish dish:RestListFormController.dishes)
		{
			if((MenuScreenController.chosenFoodType).equals(DishType.fromTypeToStr(dish.getDishType())))
			{
				System.out.println(dish.getDishName());
				dishNames.add(dish.getDishName());
				dishListOfType.add(dish);
			}
					
		}

		ObservableList<String> observableList = FXCollections.observableArrayList(dishNames);
		list.getItems().addAll(observableList);	
	}
}



