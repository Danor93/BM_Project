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
import Entities.SingletonOrder;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DeliveryController extends Controller {

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
	private CheckBox noShared;

	@FXML
	private Label notifyAdd;

	@FXML
	private Label notifyCity;

	@FXML
	private Label notifyName;

	@FXML
	private Label notifyPhone;

	@FXML
	private Text sharedLbl;

	@FXML
	private ImageView BackImage;

	@FXML
	private ImageView homePage;

	@FXML
	private Button logout;

	@FXML
	private Text userName;

	public ObservableList<String> observableList;

	public static Delivery myDelivery;

	private int countParticipants = 2;

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

	@FXML
	void personDec(ActionEvent event) {
		if (countParticipants > 2) {
			countParticipants--;
			numOfPeople.setText("" + countParticipants);
		}
	}

	@FXML
	void personInc(ActionEvent event) {
		countParticipants++;
		numOfPeople.setText("" + countParticipants);
	}

	@FXML
	void proceed(ActionEvent event) throws IOException {
		notify.setText("");
		notifyAdd.setText("");
		notifyCity.setText("");
		notifyName.setText("");
		notifyPhone.setText("");

		if (address.getText().equals("") || city.getText().equals("") || recipient.getText().equals("")
				|| phoneNum.getText().equals("")
				|| (!regular.isSelected() && !shared.isSelected() && !robot.isSelected())) {
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
				int numOfParticipants;
				String deliveryType;
				int deliPrice;

				if (shared.isSelected()) {
					if (robot.isSelected()) {
						deliveryType = "Shared by robot";
					} else {
						deliveryType = "Shared regular";
					}
					numOfParticipants = countParticipants;
					if (numOfParticipants > 2)
						deliPrice = 15 * numOfParticipants;
					else
						deliPrice = 20 * numOfParticipants;
				} else {
					if (robot.isSelected()) {
						deliveryType = "By robot";
					} else {
						deliveryType = "Regular";
					}
					numOfParticipants = 1;
					deliPrice = 25;
				}
				myDelivery = new Delivery(deliveryType, address.getText(), phoneNum.getText(), city.getText(),
						recipient.getText(), numOfParticipants, deliPrice);
				ShowOrderController.finalOrder.setOrderType(deliveryType);
				start(event, "OrderConfirm", "Order Confirmation", LoginScreenController.user.getFirstN());
			}
		}
	}

	@FXML
	void chooseRegular(ActionEvent event) {

		if (regular.isSelected()) {
			notify.setText("");
			chooseVisibleAndDisable(false, true);
			robot.setSelected(false);
			if (IdentifyW4cController.client instanceof BussinessAccount) {
				chooseVisibleAndDisable(true, false);
			}
		}
		else {
			chooseVisibleAndDisable(false, true);
		}
	}

	@FXML
	void chooseRobot(ActionEvent event) {
		if (robot.isSelected()) {
			chooseVisibleAndDisable(false, true);
			regular.setSelected(false);
			if (IdentifyW4cController.client instanceof BussinessAccount) {
				chooseVisibleAndDisable(true, false);
			}
		}
		else {
			chooseVisibleAndDisable(false, true);
		}
	}

	@FXML
	void chooseShared(ActionEvent event) {
		if (shared.isSelected()) {
			notify.setText("");
			noShared.setSelected(false);
			// robot.setSelected(false);
			minus.setVisible(true);
			minus.setDisable(false);
			plus.setVisible(true);
			plus.setDisable(false);
			numberPersons.setVisible(true);
			numOfPeople.setVisible(true);
		}
		else {
			shared.setSelected(false);
			noShared.setSelected(true);
			minus.setVisible(false);
			minus.setDisable(true);
			plus.setVisible(false);
			plus.setDisable(true);
			numberPersons.setVisible(false);
			numOfPeople.setVisible(false);
			// chooseVisibleAndDisable(false,true);
		}
	}

	@FXML
	void choosenoShared(ActionEvent event) {
		if (noShared.isSelected()) {
			shared.setSelected(false);
			minus.setVisible(false);
			minus.setDisable(true);
			plus.setVisible(false);
			plus.setDisable(true);
			numberPersons.setVisible(false);
			numOfPeople.setVisible(false);
		}

		else {
			shared.setSelected(true);
			minus.setVisible(true);
			minus.setDisable(false);
			plus.setVisible(true);
			plus.setDisable(false);
			numberPersons.setVisible(true);
			numOfPeople.setVisible(true);
		}

	}

	private void chooseVisibleAndDisable(boolean visible, boolean disable) {
		sharedLbl.setVisible(visible);
		shared.setVisible(visible);
		noShared.setVisible(visible);
	}

	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN());
	}
}