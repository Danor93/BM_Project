package client.controllers;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.ClientUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.BussinessAccount;
import Entities.Delivery;
import Entities.Message;
import Entities.MessageType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DeliveryController {
	
	   @FXML
	    private TextField address;
	   

	    @FXML
	    private TextField city;


	    @FXML
	    private Button back;

	    @FXML
	    private Button minus;

	    @FXML
	    private Button next;

	    @FXML
	    private CheckBox no;

	    @FXML
	    private Label notify;

	    @FXML
	    private Label numOfPeople;

	    @FXML
	    private Text numberPersons;
	    

	    @FXML
	    private ChoiceBox<String> options;

	    @FXML
	    private TextField phoneNum;

	    @FXML
	    private Button plus;

	    @FXML
	    private TextField recipient;

	    @FXML
	    private CheckBox yes;
	    
	    @FXML
	    private CheckBox regular;

	    @FXML
	    private CheckBox robot;

	    @FXML
	    private CheckBox shared;
	    

	    @FXML
	    private Label notifyAdd;

	    @FXML
	    private Label notifyCity;

	    @FXML
	    private Label notifyName;

	    @FXML
	    private Label notifyPhone;
	    
	  //  public static BussinessAccount bussinessClient; 
	    
	    public ObservableList <String> observableList;
	    
	    public static Delivery myDelivery;
	    
	    private int countParticipants=2;


	    
	public void start(Stage primaryStage, Parent root) {
		Scene scene=new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	  @FXML
	    void back(ActionEvent event) throws IOException 
	    {
		  	Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DeliveryOrPickup.fxml"));
			Parent root=load.load();
			DeliveryOrPickupController aFrame = load.getController();
			aFrame.start(primaryStage, root);
	    }

	    @FXML
	    void personDec(ActionEvent event) {
	    	
	    	if(countParticipants>2)
	    	{
	    		countParticipants--;
	    		numOfPeople.setText(""+countParticipants);
	    	}
	    }
	    

	    @FXML
	    void personInc(ActionEvent event) {
	    	countParticipants++;
    		numOfPeople.setText(""+countParticipants);
	    }

	    @FXML
	    void proceed(ActionEvent event) throws IOException {
	    	notify.setText("");
	    	notifyAdd.setText("");
	    	notifyCity.setText("");
	    	notifyName.setText("");
	    	notifyPhone.setText("");
	    	
	    	if(address.getText().equals("") ||city.getText().equals("") || recipient.getText().equals("") || phoneNum.getText().equals("") || (!regular.isSelected() && !shared.isSelected() && !robot.isSelected()))
	    	{
	    		notify.setText("Please fill all fields");
	    	}
	    	
	    	
	    	
	    	else
	    	{
		    	
	    		if(!(city.getText().toLowerCase()).equals(ChooseRestController.cityName.toLowerCase()))
	    		{
	    			 notifyCity.setText("Sorry, We can only deliver to "+ChooseRestController.cityName);
	    			
	    		}
	    		
	    		char[] chars = recipient.getText().toCharArray();

    		    for (char c : chars) 
    		    {
    		    	
    		    	if(!Character.isLetter(c) && !Character.isSpaceChar(c)) 
    		        {
    		        	notifyName.setText("Invalid name");
    		        }
    		    }

    		    
    		    String regexStr = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";
    		    if (!(phoneNum.getText()).matches(regexStr))
    		    {
    		    	notifyPhone.setText("Invalid phone number");
    		    }
    		    
    		    if(notifyAdd.getText().equals("")&& notifyCity.getText().equals("") && notifyName.getText().equals("") && notifyPhone.getText().equals(""))
    		    {
    		    	int numOfParticipants;
    		    	String deliveryType;
    		    	int deliPrice;
    		    	
    		    	if(shared.isSelected())
    		    	{
    		    		deliveryType="Shared";
    		    		numOfParticipants=countParticipants;
    		    		
    		    		if(numOfParticipants>2)
    		    			deliPrice=15*numOfParticipants;
    		    		else
    		    			deliPrice=20*numOfParticipants;
    		    	}
    		    	
    		    	else
    		    	{
    		    		deliveryType="Regular";
    		    		numOfParticipants=1;
    		    		deliPrice=25;
    		    	}
    		    	myDelivery=new Delivery(deliveryType,address.getText(),phoneNum.getText(),city.getText(),recipient.getText(),numOfParticipants,deliPrice);
    		    	ShowOrderController.finalOrder.setOrderType(deliveryType);
    		    	
    		    	Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    				FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/OrderConfirm.fxml"));
    				Parent root=load.load();
    				OrderConfimController aFrame = load.getController();
    				aFrame.Display();
    				aFrame.start(primaryStage, root);

 
    		    }

	    	}

	    }
	    

	    @FXML
	    void chooseRegular(ActionEvent event) {
	    	
	    	if(regular.isSelected())
	    	{
	    		notify.setText("");
    			chooseVisibleAndDisable(false,true);
	    		shared.setSelected(false);
	    		robot.setSelected(false);
	    	}

	    }

	    @FXML
	    void chooseRobot(ActionEvent event) {
	    	
	    	if(robot.isSelected())
	    	{
    			chooseVisibleAndDisable(false,true);
	    		shared.setSelected(false);
	    		regular.setSelected(false);
	    		notify.setText("This option is not avaliable yet, please choose new one");
	    		robot.setSelected(false);
	    	}

	    }

	    @FXML
	    void chooseShared(ActionEvent event) {
	    	
	    	if(shared.isSelected())
	    	{
	    		notify.setText("");
	    		regular.setSelected(false);
	    		robot.setSelected(false);
	    		
	    		if(IdentifyW4cController.client instanceof BussinessAccount)
	    		{
	    			chooseVisibleAndDisable(true,false);
		    	}
	    		
		    	else
		    	{
		    		notify.setText("This option is for bussiness clients only");
		    		shared.setSelected(false);	
		    	}
	    	}
	    	
	    	else
	    	{
	    		shared.setSelected(false);	
    			chooseVisibleAndDisable(false,true);
	    	}

	    }
	    
	    private void chooseVisibleAndDisable(boolean visible,boolean disable)
	    {
			minus.setVisible(visible);
			minus.setDisable(disable);
			plus.setVisible(visible);
			plus.setDisable(disable);
			numberPersons.setVisible(visible);
			numOfPeople.setVisible(visible);
	    }




	    //check distance calculations with tiran
}
