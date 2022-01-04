package client.controllers;

import java.io.IOException;

import Entities.Delivery;
import Entities.Message;
import Entities.MessageType;
import Entities.SingletonOrder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.ClientUI;

public class DeliveryController extends Controller {

	 
	  @FXML
	    private ImageView BackImage;

	    @FXML
	    private TextField address;

	    @FXML
	    private Button back;

	    @FXML
	    private TextField city;

	    @FXML
	    private ImageView homePage;

	    @FXML
	    private Button logout;

	    @FXML
	    private Button next;

	    @FXML
	    private Label notify;

	    @FXML
	    private Label notifyAdd;

	    @FXML
	    private Label notifyCity;

	    @FXML
	    private Label notifyName;

	    @FXML
	    private Label notifyPhone;

	    @FXML
	    private TextField phoneNum;

	    @FXML
	    private TextField recipient;

	    @FXML
	    private CheckBox regular;

	    @FXML
	    private CheckBox robot;

	    @FXML
	    private Text userName;

	public ObservableList<String> observableList;

	public static Delivery myDelivery;

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
		start(event, "CustomerScreen", "CustomerScreen", LoginScreenController.user.getFirstN());
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
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login", "");
	}

	/**
	 * This method meant to get back to choosing supply details
	 * 
	 * @param event pressing the "back" button
	 * @throws IOException
	 */

	@FXML
	void back(ActionEvent event) throws IOException {
		start(event, "DeliveryOrPickup", "Your supply details", LoginScreenController.user.getFirstN());
	}

	
	/**
	 * This method meant to check if all the details that the client entered is valid ,add them to the final order and moved him to the confirmation order
	 * 
	 * @param event pressing the "proceed" button
	 * @throws IOException
	 */
	@FXML
	void proceed(ActionEvent event) throws IOException {
		notify.setText("");
		notifyAdd.setText("");
		notifyCity.setText("");
		notifyName.setText("");
		notifyPhone.setText("");

		if (address.getText().equals("") || city.getText().equals("") || recipient.getText().equals("")
				|| phoneNum.getText().equals("")
				|| (!regular.isSelected() && !robot.isSelected())) {
			notify.setText("Please fill all fields");
		} else {
			if (!(city.getText().toLowerCase()).equals(ChooseRestController.cityName.toLowerCase())) {
				notifyCity.setText("Sorry, We can only deliver to " + ChooseRestController.cityName);

			}
			char[] chars = recipient.getText().toCharArray();
			for (char c : chars) {
				if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
					notifyName.setText("Invalid name");
				}
			}
			String regexStr = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";
			if (!(phoneNum.getText()).matches(regexStr)) {
				notifyPhone.setText("Invalid phone number");
			}
			if (notifyAdd.getText().equals("") && notifyCity.getText().equals("") && notifyName.getText().equals("")
					&& notifyPhone.getText().equals("")) {
				String deliveryType;
				if (robot.isSelected()) {
					deliveryType = "By robot";
				} else {
					deliveryType = "Regular";
				}

				
				myDelivery = new Delivery(deliveryType, address.getText(), phoneNum.getText(), city.getText(),
						recipient.getText(), 1, 25);
				ShowOrderController.finalOrder.setOrderType(deliveryType);
				start(event, "OrderConfirm", "Order Confirmation", LoginScreenController.user.getFirstN());
			}
		}
	}

	/**This method is about choosing type of delivery
	 * @param event     pressing Regular on checkbox 
     
	 */
	@FXML
	void chooseRegular(ActionEvent event) {

		if (regular.isSelected()) {
			notify.setText("");
			robot.setSelected(false);
			
		}
		else {
			
			robot.setSelected(true);
			regular.setSelected(false);
			
			
		}
	}
	

	/**This method is about choosing type of delivery
	 * @param event     pressing robot on checkbox
     
	 */
	@FXML
	void chooseRobot(ActionEvent event) {
		if (robot.isSelected()) {
			regular.setSelected(false);
			
		}
		else {
			robot.setSelected(false);
			regular.setSelected(true);
		}
	}

	
	/**Abstract method for displaying labels to the screen
	 * @param string          empty string
	 */
	
	
	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN());
	}
}