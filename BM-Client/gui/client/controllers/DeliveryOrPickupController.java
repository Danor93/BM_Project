package client.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import Entities.BussinessAccount;
import Entities.Message;
import Entities.MessageType;
import Entities.OrderType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.ClientUI;

public class DeliveryOrPickupController extends Controller {

    @FXML
    private Button back;

    @FXML
    private DatePicker date;

    @FXML
    private ImageView delivery;

    @FXML
    private ImageView takeAway;

    @FXML
    private TextField time;
    
    @FXML
    private Label notify;
    
    @FXML
    private CheckBox no;
    
    @FXML
    private CheckBox yes;
    
    @FXML
    private Label bussLabel;

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void chooseDate(ActionEvent event) 
    {

    }

    @FXML
    void chooseDelivery(MouseEvent event) throws IOException 
    {
    	if(getTimeAndDate()==true)
    	{
    		if(IdentifyW4cController.client instanceof BussinessAccount)
	    	{
	    		if(yes.isSelected())
	    		{
	    			ShowOrderController.finalOrder.setUseBudget(1);	
	    		}
	    	}
    		//earlyOrder=checkEarlyOrder();
    		ShowOrderController.finalOrder.setEarlyOrder(checkEarlyOrder());
    		//ShowOrderController.finalOrder.setEarlyOrder(earlyOrder);
    		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/Delivery.fxml"));
    		Parent root=load.load();
    		DeliveryController aFrame = load.getController();
    		aFrame.start(primaryStage, root);
   
    	}

    }

    @FXML
    void chooseTakeAway(MouseEvent event) throws IOException 
    {
    	if(getTimeAndDate()==true)
    	{
    		ShowOrderController.finalOrder.setOrderType("Take Away");
    		ShowOrderController.finalOrder.setEarlyOrder(checkEarlyOrder());

	    	if(IdentifyW4cController.client instanceof BussinessAccount)
	    	{
	    		if(yes.isSelected())
	    		{
	    			ShowOrderController.finalOrder.setUseBudget(1);
	    		}
	    	}
	    	
	    	Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/OrderConfirm.fxml"));
			Parent root=load.load();
			OrderConfimController aFrame = load.getController();
			aFrame.Display();
			aFrame.start(primaryStage, root); 
	    		    	

    	}

    }
    

    @FXML
    void chooseNo(ActionEvent event) {
    	if(no.isSelected())
    		yes.setSelected(false);

    }

    
    @FXML
    void chooseYes(ActionEvent event) {
    	if(yes.isSelected())
    		no.setSelected(false);

    }
    
    public void display()
    {
    	if(IdentifyW4cController.client instanceof BussinessAccount)
    	{
    		bussLabel.setVisible(true);
    		no.setVisible(true);
    		yes.setVisible(true);
    		no.setDisable(false);
    		yes.setDisable(false);
    	}
    }

    
	public void start(Stage primaryStage, Parent root) {
		Scene scene=new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	
	private boolean getTimeAndDate()
	{
		//check time of 00:00
		LocalDate orderDate=date.getValue();
    	String orderTime=time.getText();
    	if(orderDate!=null && !orderTime.equals("") && !orderDate.isBefore(LocalDate.now()))
    	{
    		try {
    			LocalTime timeOfOrder=LocalTime.parse(orderTime);
    			String[] restHours=RestListFormController.chosenRst.getOpenning().split("-");
    			//check how to change
    			if(!timeOfOrder.isAfter(LocalTime.parse(restHours[1])) && !timeOfOrder.isBefore(LocalTime.parse(restHours[0])))
    			{
    				if(orderDate.isEqual(LocalDate.now()))
    				{
    					if(!timeOfOrder.isBefore(LocalTime.now()))
    					{
    						ShowOrderController.finalOrder.setTimeOfOrder(orderTime);
            				ShowOrderController.finalOrder.setDateOfOrder(orderDate.toString());
            				return true;
    					}
    							
    					else
    					{
    						notify.setText("Sorry,That hour has passed");
        					return false;
    					}
    				}
    				
    				else
    				{
    					ShowOrderController.finalOrder.setTimeOfOrder(orderTime);
        				ShowOrderController.finalOrder.setDateOfOrder(orderDate.toString());
        				return true;
    				}
    				
    			}
    			else
    			{
    				notify.setText("Sorry, At this time the restaurant is close");
    				return false;
    			}
	
    		}
    		catch(DateTimeParseException c)
    		{
    			notify.setText("Invalid Time, please choose new one");
    			return false;
    		}
    	}
    	
    	else
    	{
    		notify.setText("Please choose valid Date and Time");
    		return false;
    	}
    	
	}
	
	private String checkEarlyOrder()
	{
		LocalDate orderDate=date.getValue();
		
		if(orderDate.isAfter(LocalDate.now()))
			return "yes";
		
		else
		{
			if(java.time.Duration.between(LocalTime.now(),LocalTime.parse(time.getText())).toHours()>=2)
			{

				return "yes";
			}
			
			else
				return "no";
		}
		
	}

}
