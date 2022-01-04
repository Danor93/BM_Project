package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Entities.Dish;
import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import Entities.SingletonOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.ClientUI;

/**This method meant to show the costumer the dishes he chose until now, allowing him to confirm and remove dishes he chose in the order
 * @author Adi 
 * @author Talia
 */
public class ShowOrderController extends Controller implements Initializable{


    @FXML
    private Label notify;
    
    @FXML
    private Button back;

    @FXML
    private ListView<String> listOrder;

    @FXML
    private Button next;

    @FXML
    private Button remove;

    @FXML
    private Text  totalPrice;
    

    @FXML
    private ImageView BackImage;
    
    @FXML
    private ImageView homePage;
    
    @FXML
    private Button logout;

    @FXML
    private Text userName;
    
    ObservableList<String> orders;
    
    ArrayList<String> myDishes=new ArrayList<>();
    
    private float total=0;   
    public static Order finalOrder;
    public static String refund=null;

	/**
	 * This method meant to get back to costumer page
	 * 
	 * @param event pressing the "home" image
	 * @throws IOException
	 */
	@FXML
	void backToHome(MouseEvent event) throws IOException {
    	if(SingletonOrder.getInstance()!=null)
    	{
    		SingletonOrder.getInstance().myOrder.clear();
    	}
    	start(event, "CustomerScreen", "CustomerScreen","");
	}

	/**
	 * This method meant to get back to login page and logout the customer
	 * 
	 * @param event pressing the "logout" button
	 * @throws IOException
	 */

    @FXML
    void logout(ActionEvent event) throws IOException {
		SingletonOrder.getInstance().myOrder.clear();
    	ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login","");
    }

    
	/** This method meant to get back to the previous page
	 * @param event				pressing the "back" button 
	 * @throws IOException
	 */
    @FXML
    void back(ActionEvent event) throws IOException {
		start(event, "MenuScreen", "Restaurant's menu","");
    }
    
    /**This method create the entity of the order and asks the DB if there's a refund to the customer in this restaurant. the method also moves us to the next page to proceed the order process
     * @param event                pressing the "proceed" button 
     * @throws IOException
     */
    @FXML
    void proceed(ActionEvent event) throws IOException {
    	if(SingletonOrder.getInstance().myOrder.isEmpty())
    		notify.setText("There in no dish in the order. Please return to menu to choose one");
    	else
    	{
    		finalOrder=new Order(null,RestListFormController.chosenRst.getSupplierName(),null,null,"Waiting",LoginScreenController.user.getId(),RestListFormController.chosenRst.getRestCode(),total);
    		ClientUI.chat.accept(new Message(MessageType.getRefundDetails,finalOrder));
    		start(event, "DeliveryOrPickup", "Your supply details","");
    	}
    	
    }

    
    /**The method meant to remove a dish from the order 
     * @param event       pressing the "remove" button
     */
    @FXML
    void removeOrder(ActionEvent event) 
    {
    	String s=listOrder.getSelectionModel().getSelectedItem();
    	if(s==null)
    	{
    		notify.setText("In order to remove a dish, Please select one");
    	}
    	else
    	{
    		int index=myDishes.indexOf(s);
        	myDishes.remove(s);
        	Dish removeDish=SingletonOrder.getInstance().myOrder.get(index);
        	SingletonOrder.getInstance().myOrder.remove(index);
        	total-=removeDish.getPrice()*removeDish.getQuentity();
        	totalPrice.setText("Total price: "+total+" $");
        	orders=FXCollections.observableArrayList(myDishes);
    		listOrder.setItems(orders);
    	}
    	
    }

	/**This method meant to initialize the list with the dishes the costumer chose until now, 
	 * including the choice factors and extras chosen by the customer
	 *
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		myDishes.clear();
		for(Dish dish: SingletonOrder.getInstance().myOrder)
		{
			total+=dish.getPrice()*dish.getQuentity();
			StringBuilder dishString=new StringBuilder();
			dishString.append(dish.getDishName()+":       ");	
			if(dish.getChoiceFactor()!=null&&!dish.getChoiceFactor().equals(""))
			{
				dishString.append(dish.getChoiceFactor()+": ");
				dishString.append(dish.getChoiceDetails()+"       ");
			}
			if(dish.getExtra()!=null)
			{
				dishString.append(dish.getExtra()+"      ");
			}
			dishString.append("quentity:         "+dish.getQuentity());
			dishString.append("         "+dish.getPrice()+"$");
			myDishes.add(dishString.toString());
		}
		totalPrice.setText("Total price: "+total+" $");
		orders=FXCollections.observableArrayList(myDishes);
		listOrder.setItems(orders);
	}
	
	/**Abstract method for displaying labels to the screen
	 * @param string        empty string
	 */
	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN());	
	}
}