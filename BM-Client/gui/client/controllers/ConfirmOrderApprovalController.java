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
	boolean confirmedArrivalTimeFlag = false;
	boolean waitForArrivalTimeFlag = false;
	boolean RegularOrSharedFlag = false;
	private String ArrivalTime;

	@FXML
	void back(ActionEvent event) throws IOException {
		startScreen(event, "SupplierScreen", "Supplier");
	}

	@FXML
	void confirmOrder(ActionEvent event) throws IOException {
		Order orderToChange;
		list = table.getSelectionModel().getSelectedItems();
		orderToChange = list.get(0);
		if (!orderToChange.getOrderStatus().equals("Approved")) {
			if (!orderToChange.getUseRefund().equals("0")) {
				orderToChange.setTotalPrice(
						orderToChange.getTotalPrice() - Float.parseFloat(orderToChange.getUseRefund()));
				ClientUI.chat.accept(new Message(MessageType.Use_Refund, orderToChange));
			}			
			if (orderToChange.getUseBudget() == 1)
				ClientUI.chat.accept(new Message(MessageType.Use_Budget, orderToChange));			
			ClientUI.chat.accept(new Message(MessageType.Order_approved, orderToChange));
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

	@FXML
	void refuseOrder(ActionEvent event) throws IOException {
		Order orderToChange;
		list = table.getSelectionModel().getSelectedItems();
		orderToChange = list.get(0);
		ClientUI.chat.accept(new Message(MessageType.Order_not_approved, orderToChange));
		for (int i = 0; i < allOrders.size(); i++) {
				if (allOrders.get(i).equals(orderToChange))
					allOrders.remove(i);
			}
		orderToChange = null;
		list = FXCollections.observableArrayList(allOrders);
		table.setItems(list);
	}

	@FXML
	void SendOrder(ActionEvent event) {
		boolean continueFlag = true;
		Order orderToChange;
		list = table.getSelectionModel().getSelectedItems();
		orderToChange = list.get(0);
		if (!orderToChange.getOrderStatus().equals("Approved"))
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
					|| orderToChange.getOrderType().equals("Shared")) {
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
				ClientUI.chat.accept(new Message(MessageType.Order_sended, orderToChange));
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

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}
}