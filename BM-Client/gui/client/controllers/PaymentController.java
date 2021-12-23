package client.controllers;

import java.io.IOException;

import Entities.BussinessAccount;
import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.ClientUI;

public class PaymentController {

    @FXML
    private Button back;

    @FXML
    private TextField employerCode;

    @FXML
    private TextField employerName;

    @FXML
    private Button next;

    @FXML
    private CheckBox no;

    @FXML
    private CheckBox yes;
    
    @FXML
    private Label notify;

    @FXML
    private Label notifyCode;

    @FXML
    private Label notifyName;


    @FXML
    void back(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DeliveryOrPickup.fxml"));
		Parent root=load.load();
		DeliveryOrPickupController aFrame = load.getController();
		aFrame.start(primaryStage, root);

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

    @FXML
    void next(ActionEvent event) throws IOException {
		notifyName.setText("");
		notifyCode.setText("");
		notify.setText("");

    	if(yes.isSelected())
    	{
    		BussinessAccount bussinessAcount = (BussinessAccount)IdentifyW4cController.client;
    		if(employerCode.getText().equals("") || employerName.getText().equals(""))
        		notify.setText("Please fill all fields");
    		
    		else
    		{
    			if(!employerName.getText().equals(bussinessAcount.getCompanyName()) || !employerCode.getText().equals(bussinessAcount.getEmployerCode()))
        		{
        			if(!employerCode.getText().equals(bussinessAcount.getEmployerCode()))
            			notifyCode.setText("Employer's code is incorrect");
            	
        			if(!employerName.getText().equals(bussinessAcount.getCompanyName()))
        				notifyName.setText("Employer's name is incorrect");
        		}
        		
        		else
        		{
        			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        			FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/OrderConfirm.fxml"));
        			Parent root=load.load();
        			OrderConfimController aFrame = load.getController();
        			aFrame.Display();
        			aFrame.start(primaryStage, root);
        		}
    		}
    		
    		
    	}
    	
    	else
    	{
    		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/OrderConfirm.fxml"));
			Parent root=load.load();
			OrderConfimController aFrame = load.getController();
			aFrame.Display();
			aFrame.start(primaryStage, root);
    	}
    }
    
    
	public void start(Stage primaryStage, Parent root) {
		Scene scene=new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}

    
