package client.controllers;

import java.io.IOException;

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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.ClientUI;

public class OrderConfimController {

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
    
    public float calPrice;
    
    float priceAfterRef;
    
    public static String isSuccess;
    
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
    			refundDec.setText("-"+priceAfterRef+"credit");
    			priceAfterRef=0;
    		}
    		
    		else
    		{
    			refundDec.setText("-"+ShowOrderController.refund+"$ credit");
    			priceAfterRef-=Float.parseFloat(ShowOrderController.refund);
    		}
    		
			totalPrice.setText("Total price of: "+priceAfterRef+"$");
    	}
    }


    @FXML
    void approve(ActionEvent event) 
    {
    	if(ShowOrderController.refund!=null)
    	{
    		if(yes.isSelected())
    		{
    			ShowOrderController.finalOrder.setTotalPrice(priceAfterRef);
    			ShowOrderController.finalOrder.setUseRefund(ShowOrderController.refund);
    		}
    		
    		else
    		{
    			ShowOrderController.finalOrder.setTotalPrice(calPrice);
    			
    		}
    		
    		
    		
    	}
    	if(ShowOrderController.finalOrder.getUseBudget()==1)
    	{
    		BussinessAccount buss=(BussinessAccount)IdentifyW4cController.client;
    		if(Float.parseFloat(buss.getBudget())<ShowOrderController.finalOrder.getTotalPrice())
    		{
    			//label
    		}
    		
    	}
    	Message msg=new Message(MessageType.InsertOrder,ShowOrderController.finalOrder);
		ClientUI.chat.accept(msg);
		
		SingletonOrder.getInstance().myOrder.get(0).setOrderNumber(ShowOrderController.finalOrder.getOrderNum());
		
		Message msg2=new Message(MessageType.InsertDishesOrder,SingletonOrder.getInstance().myOrder);
		ClientUI.chat.accept(msg2);
		
		if(isSuccess!=null)
		{
			System.out.println("hi adi -my name is -no");
		}

	
    	
    	
    	
    	
    	

    }

    
    @FXML
    void back(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DeliveryOrPickup.fxml"));
		Parent root=load.load();
		DeliveryOrPickupController aFrame = load.getController();
		aFrame.start(primaryStage, root);

    }
    
	public void start(Stage primaryStage, Parent root) {
		Scene scene=new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void Display()
	{
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
			
			if(!dish.getChoiceFactor().equals(""))
			{
				orderDetails.appendText(dish.getChoiceFactor()+": "+dish.getChoiceDetails()+"\n");
			}
			
			if(!dish.getExtra().equals(""))
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
		
		if(DeliveryOrPickupController.earlyOrder)
		{
			orderDetails.appendText("-10% Early order");
			calPrice-=calPrice*0.1;
		}
		
		totalPrice.setText("Total price of: "+calPrice+"$");		
	}

}




	