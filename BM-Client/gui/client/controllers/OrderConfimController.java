package client.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import Entities.BussinessAccount;
import Entities.Dish;
import Entities.Message;
import Entities.MessageType;
import Entities.SingletonOrder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.ClientUI;

public class OrderConfimController extends Controller{

    @FXML
    private Button approve;

    @FXML
    private Button back;

    @FXML
    private CheckBox no;

    @FXML
    private TextArea orderDetails;

    @FXML
    private Label refundNotify;

    @FXML
    private Label totalPrice;

    @FXML
    private Text userName;

    @FXML
    private CheckBox yes;
    

    @FXML
    private Label refundDec;
    
    @FXML
    private ImageView BackImage;

    @FXML
    private ImageView homePage;

    @FXML
    private Button logout;
    
    public float calPrice;
    
    float priceAfterRef;
    
    public static String isSuccess;
    
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
		SingletonOrder.getInstance().myOrder.clear();
    	ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login","");
    }
    
    @FXML
    void chooseNo(ActionEvent event) {
    	if(no.isSelected())
    	{
    		yes.setSelected(false);
    		refundDec.setText("");
			totalPrice.setText("Total price of: "+calPrice+"$");
    	}

    }

    @FXML
    void chooseYes(ActionEvent event) {
    	if(yes.isSelected())
    	{
    		priceAfterRef=calPrice;
    		
    		no.setSelected(false);
    		if(Float.parseFloat(ShowOrderController.refund)>=calPrice)
    		{
    			refundDec.setText("-"+priceAfterRef+"$ credit");
    			priceAfterRef=0;
    		}
    		
    		else
    		{
    			refundDec.setText("-"+ShowOrderController.refund+"$ credit");
    			priceAfterRef-=Float.parseFloat(ShowOrderController.refund);
    		}
    		
			totalPrice.setText("Total price of: "+priceAfterRef+"$");
    	}
    	
    	else
    	{
    		no.setSelected(true);
    		refundDec.setVisible(false);
    		totalPrice.setText("Total price of: "+calPrice+"$");
    	}
    }


    @FXML
    void approve(ActionEvent event) throws IOException 
    {
    	if(ShowOrderController.refund!=null)
    	{
    		if(yes.isSelected())
    		{
    			ShowOrderController.finalOrder.setTotalPrice(priceAfterRef);
    			if(Float.parseFloat(ShowOrderController.refund)>calPrice)
    			{
    				ShowOrderController.finalOrder.setUseRefund(Float.toString(calPrice));
    			}
    			else
    				ShowOrderController.finalOrder.setUseRefund(ShowOrderController.refund);
    		}
    			
    	}
    	
		else
		{
			ShowOrderController.finalOrder.setTotalPrice(calPrice);
			
		}

    	
    	Message msg=new Message(MessageType.InsertOrder,ShowOrderController.finalOrder);
		ClientUI.chat.accept(msg);
		
		SingletonOrder.getInstance().myOrder.get(0).setOrderNumber(ShowOrderController.finalOrder.getOrderNum());
		
		Message msg2=new Message(MessageType.InsertDishesOrder,SingletonOrder.getInstance().myOrder);
		ClientUI.chat.accept(msg2);
		
		if(!ShowOrderController.finalOrder.getOrderType().equals("Take Away"))
		{
			DeliveryController.myDelivery.setOrderNum(ShowOrderController.finalOrder.getOrderNum());
			Message msg3=new Message(MessageType.InsertDelivery,DeliveryController.myDelivery);
			ClientUI.chat.accept(msg3);
		}

		
		if(isSuccess!=null)
		{
			Alert alert=new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Order excepted");
			alert.getDialogPane().setPrefSize(500, 200);
			String alertText;
			
			alertText="The system received your order and waiting for the supplier's approval.";
			if(ShowOrderController.finalOrder.getUseBudget()==1)
	    	{
	    		BussinessAccount buss=(BussinessAccount)IdentifyW4cController.client;
	    		if(Float.parseFloat(buss.getBudget())<ShowOrderController.finalOrder.getTotalPrice())
	    		{
	    			alertText="The system received your order and waiting for the supplier's approval.\nPlease note that the remaining charge will be taken from your credit card";
	    		}
	    	}
			
			alert.setContentText(alertText);
			Optional<ButtonType>result=alert.showAndWait();
			
			if(result.get()==ButtonType.OK || result.get()==ButtonType.CLOSE)
			{
				SingletonOrder.getInstance().myOrder.clear();
		    	start(event,"CustomerScreen","Costumer Screen",LoginScreenController.user.getFirstN());
			}
		}
    }

    
    @FXML
    void back(ActionEvent event) throws IOException {
		start(event, "DeliveryOrPickUp", "Your supply details","");
    }
    


	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN());
		calPrice=ShowOrderController.finalOrder.getTotalPrice();
		if(ShowOrderController.refund!=null)
		{
			refundNotify.setText("You have a "+ShowOrderController.refund+"$ credit. Would you like to use it?");
			no.setVisible(true);
			yes.setVisible(true);
			no.setDisable(false);
			yes.setDisable(false);

		}
		
		orderDetails.appendText("Your order is: \n\n");
		
		for(Dish dish:SingletonOrder.getInstance().myOrder)
		{
			orderDetails.appendText(dish.getDishName()+": \n");
			
			if(!dish.getChoiceFactor().equals("")&&dish.getChoiceFactor()!=null)
			{
				orderDetails.appendText(dish.getChoiceFactor()+": "+dish.getChoiceDetails()+"\n");
			}
			//!dish.getExtra().equals("")
			
			if(dish.getExtra()!=null)
			{
				orderDetails.appendText(dish.getExtra()+"\n");
			}
			
			orderDetails.appendText("Dish price: "+dish.getPrice()+"*"+dish.getQuentity()+"$\n\n");
		}
		
		if(!ShowOrderController.finalOrder.getOrderType().equals("Take Away"))
		{
			orderDetails.appendText("Delivery details: \n");
			orderDetails.appendText("Recipient name: "+ DeliveryController.myDelivery.getRecipient()+"\n");
			orderDetails.appendText("Recipient Address: "+DeliveryController.myDelivery.getAddress()+","+DeliveryController.myDelivery.getCity()+"\n");
			orderDetails.appendText("Recipient phone: "+DeliveryController.myDelivery.getPhone()+"\n");
			orderDetails.appendText("Delivery type: "+DeliveryController.myDelivery.getDeliveryType()+"\n");
			orderDetails.appendText("Number of participants:" +DeliveryController.myDelivery.getParticipantsNum()+"\n");
			orderDetails.appendText("Price of delivery:" +DeliveryController.myDelivery.getDeliPrice()+"$\n");
			calPrice+=DeliveryController.myDelivery.getDeliPrice();
		}
		else
		{
			orderDetails.appendText("Take Away- Free of charge\n");	
		}

		
		if(ShowOrderController.finalOrder.getEarlyOrder().equals("yes"))
		{
			orderDetails.appendText("-10% Early order");
			calPrice-=calPrice*0.1;
		}
		
		totalPrice.setText("Total price of: "+calPrice+"$");
		
	}

}




	