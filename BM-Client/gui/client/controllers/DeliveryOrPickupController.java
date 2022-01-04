package client.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import Entities.BussinessAccount;
import Entities.Message;
import Entities.MessageType;
import Entities.OrderType;
import Entities.SingletonOrder;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.ClientUI;

public class DeliveryOrPickupController extends Controller {
	

    @FXML
    private ImageView BackImage;

    @FXML
    private Button back;

    @FXML
    private Label bussLabel;

    @FXML
    private DatePicker date;

    @FXML
    private ImageView delivery;

    @FXML
    private ImageView shared;

    @FXML
    private ImageView homePage;

    @FXML
    private Button logout;

    @FXML
    private CheckBox no;

    @FXML
    private Label notify;

    @FXML
    private Label notifySupply;

    @FXML
    private Text orderNumlbl;

    @FXML
    private TextField sharedOrderNum;

    @FXML
    private ImageView sharedlbl;

    @FXML
    private TextField time;

    @FXML
    private Text userName;

    @FXML
    private CheckBox yes;
    
    @FXML
    private Button join;
    
    public static boolean isJoin=false;


    
	/** This method meant to get back to costumer page
	 * @param event				pressing the "home" image 
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
    
    
	/** This method meant to get back to login page and logout the customer
	 * @param event				pressing the "logout" button 
	 * @throws IOException
	 */

    @FXML
    void logout(ActionEvent event) throws IOException {
		SingletonOrder.getInstance().myOrder.clear();
    	ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login","");
    }
    
	/** This method meant to get back to show order
	 * @param event				pressing the "back" button 
	 * @throws IOException
	 */
    @FXML
    void back(ActionEvent event) throws IOException {
		start(event, "ShowOrder", "Your order","");
    }
    
    /**This method checks if the client can join to another delivery of other business client
     * @param event          pressing the delivery image
     * @throws IOException
     */
    
    @FXML
    void chooseShared(MouseEvent event) 
    {
    	if (!(IdentifyW4cController.client instanceof BussinessAccount))
    	{
    		notifySupply.setText("This option is for bussiness clients only! ");
    	}
    	else
    	{
    		orderNumlbl.setVisible(true);
    		sharedOrderNum.setVisible(true);
    		sharedOrderNum.setDisable(false); 
    		join.setVisible(true);
    		join.setDisable(false);
    	}

    }
    

    /**This method checks if the client can join to another delivery of other business client with specific number order
     * it also checks if the business client chose to use his budget. if the client can join he moved to Order Confirmation. 
     * @param event          pressing the delivery image
     * @throws IOException
     */
    
    @FXML
    void join(ActionEvent event) throws IOException 
    {
    	if(sharedOrderNum.getText().equals(""))
    	{
    		notifySupply.setText("In order to join you need to insert order number");
    	}
    	
    	else
    	{
    		StringBuilder b=new StringBuilder();
    		b.append(sharedOrderNum.getText());
    		b.append("@");
    		b.append(RestListFormController.chosenRst.getSupplierName());
    		
			Message msg=new Message(MessageType.Join,b.toString());
    		ClientUI.chat.accept(msg);
    		if(isJoin==true)
    		{
    			if(yes.isSelected())
	    		{
	    			ShowOrderController.finalOrder.setUseBudget(1);	
	    		}
    			ShowOrderController.finalOrder.setOrderType("Shared-"+sharedOrderNum.getText());
    			
    			start(event, "OrderConfirm", "Order Confirmation","");
    			
    		}
    		else
    		{
    			notifySupply.setText("You can't join to this order");
    		}
    		
    	}

    }

    /**This method checks if the entered time and date are valid using the private method and the order is an early order.
     * it also checks if the business client chose to use his budget and moves him to the delivery details screen
     * @param event          pressing the delivery image
     * @throws IOException
     */

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
    		ShowOrderController.finalOrder.setEarlyOrder(checkEarlyOrder());
    		
    		
    		
    		start(event, "Delivery", "Your delivery","");

   
    	}

    }

    /**This method checks if the entered time and date are valid using the private method and the order is an early order.
     * it also checks if the business client chose to use his budget and moves him to confirming order screen
     * @param event          pressing the take away image
     * @throws IOException
     */

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
	    	
	    	
			
    		start(event, "OrderConfirm", "Order Confirmation","");

    	}

    }
    
    /**This method allows a business customer to choose that he doesn't want to pay with his budget
     * @param event    choose no in the checkbox
     */

    @FXML
    void chooseNo(ActionEvent event) {
    	if(no.isSelected())
    		yes.setSelected(false);

    }

    /**This method allows a business customer to choose that he wants to pay with his budget
     * @param event    choose yes in the checkbox
     */
    
    @FXML
    void chooseYes(ActionEvent event) {
    	if(yes.isSelected())
    		no.setSelected(false);

    }
    
    
  
   
	

	/** This method meant to check if the chosen time and date by the customer are valid
	 * @return      true/false
	 */
	
	private boolean getTimeAndDate()
	{
		
		LocalDate orderDate=date.getValue();
    	String orderTime=time.getText();
    	if(orderDate!=null && !orderTime.equals("") && !orderDate.isBefore(LocalDate.now()))
    	{
    		try {
    			LocalTime timeOfOrder=LocalTime.parse(orderTime);
    			String[] restHours=RestListFormController.chosenRst.getOpenning().split("-");

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
	
	/**This method meant to check if the customer entered an early order
	 * @return         yes/no according to the check
	 */
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


	/**Abstract method for displaying labels to the screen
	 * @param string        empty string
	 */
	
	@Override
	public void display(String string) {
		isJoin=false;
    	if(IdentifyW4cController.client instanceof BussinessAccount)
    	{
    		bussLabel.setVisible(true);
    		no.setVisible(true);
    		yes.setVisible(true);
    		no.setDisable(false);
    		yes.setDisable(false);
    	}
    	
    	userName.setText(LoginScreenController.user.getFirstN());
		
	}

}
