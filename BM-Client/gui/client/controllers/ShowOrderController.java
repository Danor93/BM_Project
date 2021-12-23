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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.ClientUI;

public class ShowOrderController extends Controller implements Initializable{

    @FXML
    private Button back;

    @FXML
    private ListView<String> listOrder;

    @FXML
    private Button next;

    @FXML
    private Button remove;

    @FXML
    private Button totalPrice;
    
    ObservableList<String> orders;
    
    ArrayList<String> myDishes=new ArrayList<>();
    
    private float total=0;
    
    public static Order finalOrder;
    public static String refund=null;
    
    
   

    @FXML
    void back(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/MenuScreen.fxml"));
		Parent root=load.load();
		MenuScreenController aFrame = load.getController();
		aFrame.start(primaryStage, root);

    }
    

    @FXML
    void proceed(ActionEvent event) throws IOException {
    	finalOrder=new Order(null,RestListFormController.chosenRst.getSupplierName(),null,null,"Waiting for approval",LoginScreenController.user.getId(),RestListFormController.chosenRst.getRestCode(),total);
    	Message msg=new Message(MessageType.getRefundDetails,finalOrder);
		ClientUI.chat.accept(msg);
    	Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DeliveryOrPickUp.fxml"));
		Parent root=load.load();
		DeliveryOrPickupController aFrame = load.getController();
		aFrame.display();
		aFrame.start(primaryStage, root);
    }

    @FXML
    void removeOrder(ActionEvent event) 
    {
    	String s=listOrder.getSelectionModel().getSelectedItem();
    	int index=myDishes.indexOf(s);
    	myDishes.remove(s);
    	Dish removeDish=SingletonOrder.getInstance().myOrder.get(index);
    	SingletonOrder.getInstance().myOrder.remove(index);
    	total-=removeDish.getPrice()*removeDish.getQuentity();
    	totalPrice.setText("Total price: "+total+" $");
    	orders=FXCollections.observableArrayList(myDishes);
		listOrder.setItems(orders);

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		
		for(Dish dish: SingletonOrder.getInstance().myOrder)
		{
			total+=dish.getPrice()*dish.getQuentity();
			StringBuilder dishString=new StringBuilder();
			dishString.append(dish.getDishName()+":       ");
			
			if(dish.getChoiceFactor()!=null)
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

	public void start(Stage primaryStage, Parent root) {
		Scene scene=new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}

