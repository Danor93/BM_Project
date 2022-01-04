package client.controllers;

import java.io.IOException;
import java.util.Optional;

import Entities.BussinessAccount;
import Entities.Dish;
import Entities.Message;
import Entities.MessageType;
import Entities.SingletonOrder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.ClientUI;

/**
 * This class meant to show the customer his final bill with all of the details
 * he chose for his order, allowing him to use refund and give his approval to
 * pass the order to the supplier
 * 
 * @author Adi
 * @author Talia
 *
 */
public class OrderConfimController extends Controller {

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

	public static int part;

	/**
	 * This method meant to get back to costumer page
	 * 
	 * @param event pressing the "home" image
	 * @throws IOException
	 */
	@FXML
	void backToHome(MouseEvent event) throws IOException {
		if (SingletonOrder.getInstance() != null) {
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
	 * this method sets the total price to be without the refund (The costumer chose
	 * that he doesn't want to use refund)
	 * 
	 * @param event pressing the checkbox "no"
	 */
	@FXML
	void chooseNo(ActionEvent event) {
		if (no.isSelected()) {
			yes.setSelected(false);
			refundDec.setText("");
			totalPrice.setText("Total price of: " + calPrice + "$");
		}

	}

	/**
	 * this method sets the total price label to the price after refund (The
	 * costumer chose that he wants to use refund)
	 * 
	 * @param event pressing the checkbox "yes"
	 */
	@FXML
	void chooseYes(ActionEvent event) {
		if (yes.isSelected()) {
			priceAfterRef = calPrice;

			no.setSelected(false);
			if (Float.parseFloat(ShowOrderController.refund) >= calPrice) {
				refundDec.setText("-" + priceAfterRef + "$ credit");
				priceAfterRef = 0;
			} else {
				refundDec.setText("-" + ShowOrderController.refund + "$ credit");
				priceAfterRef -= Float.parseFloat(ShowOrderController.refund);
			}
			totalPrice.setText("Total price of: " + priceAfterRef + "$");
		} else {
			no.setSelected(true);
			refundDec.setVisible(false);
			totalPrice.setText("Total price of: " + calPrice + "$");
		}
	}

	/**
	 * this method inserts the order to the DB, first inserts the used refund and
	 * total price to the entity of the order, inserts the order entity to the DB
	 * and gets his AI order number. after that, it inserts the dishes in the order
	 * to the DB and the delivery if he has one. the method also alerts the customer
	 * that his order got accepted in the system and passed to the supplier
	 * 
	 * @param event pressing "approve" button
	 * @throws IOException
	 */
	@FXML
	void approve(ActionEvent event) throws IOException {
		if (ShowOrderController.refund != null) {
			if (yes.isSelected()) {
				ShowOrderController.finalOrder.setTotalPrice(priceAfterRef);
				if (Float.parseFloat(ShowOrderController.refund) > calPrice) {
					ShowOrderController.finalOrder.setUseRefund(Float.toString(calPrice));
				} else
					ShowOrderController.finalOrder.setUseRefund(ShowOrderController.refund);
			}
		} else {
			ShowOrderController.finalOrder.setTotalPrice(calPrice);
		}
		ClientUI.chat.accept(new Message(MessageType.InsertOrder, ShowOrderController.finalOrder));
		SingletonOrder.getInstance().myOrder.get(0).setOrderNumber(ShowOrderController.finalOrder.getOrderNum());
		ClientUI.chat.accept(new Message(MessageType.InsertDishesOrder, SingletonOrder.getInstance().myOrder));
		if (!ShowOrderController.finalOrder.getOrderType().equals("Take Away")) {
			if (ShowOrderController.finalOrder.getOrderType().equals("Regular")
					|| ShowOrderController.finalOrder.getOrderType().equals("By robot")) {
				DeliveryController.myDelivery.setOrderNum(ShowOrderController.finalOrder.getOrderNum());
				ClientUI.chat.accept(new Message(MessageType.InsertDelivery, DeliveryController.myDelivery));
			} else {
				StringBuilder b = new StringBuilder();
				b.append(ShowOrderController.finalOrder.getOrderType());
				b.append("@");
				b.append(part);
				b.append("@");
				b.append(ShowOrderController.finalOrder.getOrderNum());
				ClientUI.chat.accept(new Message(MessageType.InsertShared, b.toString()));
			}

		}
		if (isSuccess != null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Order excepted");
			alert.getDialogPane().setPrefSize(500, 200);
			String alertText;
			alertText = "The system received your order and waiting for the supplier's approval.";
			if (ShowOrderController.finalOrder.getUseBudget() == 1) {
				BussinessAccount buss = (BussinessAccount) IdentifyW4cController.client;
				if (Float.parseFloat(buss.getBudget()) < ShowOrderController.finalOrder.getTotalPrice()) {
					alertText = "The system received your order and waiting for the supplier's approval.\nPlease note that the remaining charge will be taken from your credit card";
				}
			}
			alert.setContentText(alertText);
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK || result.get() == ButtonType.CLOSE) {
				SingletonOrder.getInstance().myOrder.clear();
				start(event, "CustomerScreen", "Costumer Screen", LoginScreenController.user.getFirstN());
			}
		}
	}

	/**
	 * This method meant to get back to choosing supply details
	 * 
	 * @param event pressing the "back" button
	 * @throws IOException
	 */
	@FXML
	void back(ActionEvent event) throws IOException {
		start(event, "DeliveryOrPickUp", "Your supply details", LoginScreenController.user.getFirstN());
	}

	/**
	 * This method meant to show the customer all of his [selected order details-
	 * the dishes he chose and their price, his selected supplying method including
	 * the discount if he chose an early order and even his refund details(if he has
	 * one for the restaurant he chose) The method also calculates the total price
	 * for the order (without refund)
	 *
	 */
	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN());
		calPrice = ShowOrderController.finalOrder.getTotalPrice();
		if (ShowOrderController.refund != null) {
			refundNotify.setText("You have a " + ShowOrderController.refund + "$ credit. Would you like to use it?");
			no.setVisible(true);
			yes.setVisible(true);
			no.setDisable(false);
			yes.setDisable(false);
		}
		orderDetails.appendText("Your order is: \n\n");
		for (Dish dish : SingletonOrder.getInstance().myOrder) {
			orderDetails.appendText(dish.getDishName() + ": \n");
			if (!dish.getChoiceFactor().equals("") && dish.getChoiceFactor() != null) {
				orderDetails.appendText(dish.getChoiceFactor() + ": " + dish.getChoiceDetails() + "\n");
			}
			if (dish.getExtra() != null) {
				orderDetails.appendText(dish.getExtra() + "\n");
			}
			orderDetails.appendText("Dish price: " + dish.getPrice() + "*" + dish.getQuentity() + "$\n\n");
		}
		if (!ShowOrderController.finalOrder.getOrderType().equals("Take Away")) {
			if (ShowOrderController.finalOrder.getOrderType().equals("Regular")
					|| ShowOrderController.finalOrder.getOrderType().equals("By robot")) {
				orderDetails.appendText("Delivery details: \n");
				orderDetails.appendText("Recipient name: " + DeliveryController.myDelivery.getRecipient() + "\n");
				orderDetails.appendText("Recipient Address: " + DeliveryController.myDelivery.getAddress() + ","
						+ DeliveryController.myDelivery.getCity() + "\n");
				orderDetails.appendText("Recipient phone: " + DeliveryController.myDelivery.getPhone() + "\n");
				orderDetails.appendText("Delivery type: " + DeliveryController.myDelivery.getDeliveryType() + "\n");
				orderDetails.appendText(
						"Number of participants:" + DeliveryController.myDelivery.getParticipantsNum() + "\n");
				orderDetails.appendText("Price of delivery:" + DeliveryController.myDelivery.getDeliPrice() + "$\n");
				calPrice += DeliveryController.myDelivery.getDeliPrice();
			}

			else {
				orderDetails.appendText(ShowOrderController.finalOrder.getOrderType());
				String[] div = ShowOrderController.finalOrder.getOrderType().split("-");
				Message msg = new Message(MessageType.priceShare, Integer.parseInt(div[1]));
				ClientUI.chat.accept(msg);
				part++;
				if (part > 2) {
					orderDetails.appendText("\nPrice of Delivery: 15$\n");
					calPrice += 15;
				} else {
					orderDetails.appendText("\nPrice of Delivery: 20$\n");
					calPrice += 20;
				}
			}

		} else {
			orderDetails.appendText("Take Away- Free of charge\n");
		}
		if (ShowOrderController.finalOrder.getEarlyOrder().equals("yes")) {
			orderDetails.appendText("-10% Early order");
			calPrice -= calPrice * 0.1;
		}
		totalPrice.setText("Total price of: " + calPrice + "$");
	}
}