package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.ErrorManager;

import javafx.scene.control.Label;

import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import Entities.OrderType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.ClientUI;
import main.PopUpMessage;

/**
 * @author Aviel
 * This class is for approval of orders awaiting approval.
 */
public class ConfirmOrderApprovalController extends Controller implements Initializable {
	public static ArrayList<Order> allOrders = new ArrayList<Order>();
	public static String phoneNumber;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button back;

	@FXML
	private Button Refuse;

	@FXML
	private Button Confirm;

	@FXML
	private Button Send;

	@FXML
	private TableView<Order> table;

	@FXML
	private TableColumn<Order, Integer> orderNumber;

	@FXML
	private TableColumn<Order, OrderType> orderType;

	@FXML
	private TableColumn<Order, String> restName;

	@FXML
	private TableColumn<Order, Float> totalPrice;

	@FXML
	private TableColumn<Order, String> timeOfOrder;

	@FXML
	private TableColumn<Order, String> dateOfOrder;

	@FXML
	private TableColumn<Order, String> orderStatus;

	@FXML
	private TableColumn<Order, String> costumerID;

	@FXML
	private Label labelArrivalTime;

	@FXML
	private TextField setArrivalTimeIsPlaaned;

	ObservableList<Order> list;
	boolean confirmedArrivalTimeFlag = false; // flag to check if the arrival time is valid.
	boolean waitForArrivalTimeFlag = false; // flag to check if we need to wait for arrival time
	boolean RegularOrSharedFlag = false; // flag to check if the delivery method = regular / shared
	private String ArrivalTime; 

	/**
	 * A method to return to the previous page. 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void back(ActionEvent event) throws IOException {
		startScreen(event, "SupplierScreen", "Supplier");
	}

	/**
	 * A method of confirming an order waiting for approval.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void confirmOrder(ActionEvent event) throws IOException {
		Order orderToChange;
		list = table.getSelectionModel().getSelectedItems();
		orderToChange = list.get(0);
		if (!orderToChange.getOrderStatus().equals("Approved")) { // If the order is already approved, there is no need to confirm it again.
			if (!orderToChange.getUseRefund().equals("0")) { // Check if the customer is interested in paying by refund
				orderToChange.setTotalPrice(
						orderToChange.getTotalPrice() - Float.parseFloat(orderToChange.getUseRefund()));
				ClientUI.chat.accept(new Message(MessageType.Use_Refund, orderToChange));
			}			
			if (orderToChange.getUseBudget() == 1) // Check if the customer is a business customer and is interested in paying by budget
				ClientUI.chat.accept(new Message(MessageType.Use_Budget, orderToChange));			
			ClientUI.chat.accept(new Message(MessageType.Order_approved, orderToChange)); // Change the status of the database to Approved
			for (int i = 0; i < allOrders.size(); i++) {
					if (allOrders.get(i).equals(orderToChange))
						allOrders.get(i).setOrderStatus("Approved");
				}
			orderToChange = null;;
			table.refresh();
			list = FXCollections.observableArrayList(allOrders);
			table.setItems(list);
		}
	}

	/**
	 * A method of refusing an order waiting for approval.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void refuseOrder(ActionEvent event) throws IOException {
		Order orderToChange;
		list = table.getSelectionModel().getSelectedItems();
		orderToChange = list.get(0);
		ClientUI.chat.accept(new Message(MessageType.Order_not_approved, orderToChange)); // Change the status of the database to Not approved
		for (int i = 0; i < allOrders.size(); i++) {
				if (allOrders.get(i).equals(orderToChange))
					allOrders.remove(i);
			}
		orderToChange = null;
		list = FXCollections.observableArrayList(allOrders);
		table.setItems(list);
	}

	/**
	 * A method of Sending an order waiting for send.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void SendOrder(ActionEvent event) {
		boolean continueFlag = true;
		Order orderToChange;
		list = table.getSelectionModel().getSelectedItems();
		orderToChange = list.get(0);
		if (!orderToChange.getOrderStatus().equals("Approved")) // If the order has not yet been confirmed, we will not be able to send it to the customer.
			PopUpMessage.errorMessage("Order must be approved before sended to client");
		else {
			for (int i = 0; i < allOrders.size(); i++) {
					if (allOrders.get(i).equals(orderToChange))
						allOrders.remove(i);
				}
			ClientUI.chat.accept(new Message(MessageType.get_Phone_Number, orderToChange));
			StringBuilder str = new StringBuilder();
			str.append("successfully. The phone is - ");
			str.append(phoneNumber);
			if (orderToChange.getOrderType().equals("Regular")
					|| orderToChange.getOrderType().equals("Shared")) { // If the shipping method is - Regular/Shared, then we must add the estimated time of arrival to the customer.
				RegularOrSharedFlag = true;
			}
			if (RegularOrSharedFlag) {
				if (setArrivalTimeIsPlaaned.getText().isEmpty()) {
					waitForArrivalTimeFlag = false;
					PopUpMessage.errorMessage("You have to insert arrival time");

				} else {
					ArrivalTime = setArrivalTimeIsPlaaned.getText();
					try {
						LocalTime.parse(ArrivalTime);
						waitForArrivalTimeFlag = true;
					} catch (DateTimeParseException | NullPointerException e) {
						PopUpMessage.errorMessage("Time must be invalid");
					}
				}
				if (waitForArrivalTimeFlag)
					str.append(" Arrival time is planned to - " + ArrivalTime);
				else {
					RegularOrSharedFlag = false;
					continueFlag = false;
					str.setLength(0);
					orderToChange = null;
					setArrivalTimeIsPlaaned.clear();
				}

			}
			if (continueFlag) {
				RegularOrSharedFlag = false;
				waitForArrivalTimeFlag = false;
				ClientUI.chat.accept(new Message(MessageType.Order_sended, orderToChange)); // Change the status of the database to Sended
				PopUpMessage.simulationMessage(str.toString());
				orderToChange = null;
				list = FXCollections.observableArrayList(allOrders);
				table.setItems(list);
				str.setLength(0);
				setArrivalTimeIsPlaaned.clear();
			}
		}
	}

	@FXML
	void initialize() {
		assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
		assert Refuse != null : "fx:id=\"Refuse\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
		assert Confirm != null
				: "fx:id=\"Confirm\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
		assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
		assert orderNumber != null
				: "fx:id=\"orderNumber\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
		assert orderType != null
				: "fx:id=\"orderType\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
		assert restName != null
				: "fx:id=\"restName\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
		assert totalPrice != null
				: "fx:id=\"totalPrice\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
		assert timeOfOrder != null
				: "fx:id=\"timeOfOrder\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
		assert dateOfOrder != null
				: "fx:id=\"dateOfOrder\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
		assert orderStatus != null
				: "fx:id=\"orderStatus\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
		assert costumerID != null
				: "fx:id=\"costumerID\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
		assert Send != null : "fx:id=\"Send\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
		assert setArrivalTimeIsPlaaned != null
				: "fx:id=\"setArrivalTimeIsPlaaned\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
		assert labelArrivalTime != null
				: "fx:id=\"labelArrivalTime\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelArrivalTime.setVisible(true);
		setArrivalTimeIsPlaaned.setVisible(true);
		orderNumber.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderNum"));
		orderType.setCellValueFactory(new PropertyValueFactory<Order, OrderType>("orderType"));
		restName.setCellValueFactory(new PropertyValueFactory<Order, String>("restName"));
		totalPrice.setCellValueFactory(new PropertyValueFactory<Order, Float>("totalPrice"));
		timeOfOrder.setCellValueFactory(new PropertyValueFactory<Order, String>("timeOfOrder"));
		dateOfOrder.setCellValueFactory(new PropertyValueFactory<Order, String>("dateOfOrder"));
		orderStatus.setCellValueFactory(new PropertyValueFactory<Order, String>("orderStatus"));
		costumerID.setCellValueFactory(new PropertyValueFactory<Order, String>("costumerId"));
		ClientUI.chat.accept(new Message(MessageType.get_orders_to_approve, LoginScreenController.user.getId()));
		list = FXCollections.observableArrayList(allOrders);
		table.setItems(list);
	}
}