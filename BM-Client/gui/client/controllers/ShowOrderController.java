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

//Adi&Talia
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
    	start(event, "CustomerScreen", "CustomerScreen",LoginScreenController.user.getFirstN());
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

    @FXML
    void back(ActionEvent event) throws IOException {
		start(event, "MenuScreen", "Restaurant's menu","");
    }
    

    @FXML
    void proceed(ActionEvent event) throws IOException {
    	if(SingletonOrder.getInstance().myOrder.isEmpty())
    		notify.setText("There in no dish in the order. Please return to menu to choose one");
    	else
    	{
    		finalOrder=new Order(null,RestListFormController.chosenRst.getSupplierName(),null,null,"Waiting",LoginScreenController.user.getId(),RestListFormController.chosenRst.getRestCode(),total);
    		ClientUI.chat.accept(new Message(MessageType.getRefundDetails,finalOrder));
    		start(event, "DeliveryOrPickUp", "Your supply details",LoginScreenController.user.getFirstN());
    	}
    	
    }

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
	
	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN());	
	}
}