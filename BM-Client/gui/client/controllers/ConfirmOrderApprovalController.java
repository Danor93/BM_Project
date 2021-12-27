package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.control.cell.PropertyValueFactory;
import main.ClientUI;

public class ConfirmOrderApprovalController extends Controller implements Initializable {
	public static ArrayList<Order> allOrders = new ArrayList<Order>();

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

	ObservableList<Order> list;

	@FXML
	void back(ActionEvent event) throws IOException {
		startScreen(event, "SupplierScreen", "Supplier");
	}

	@FXML
	void confirmOrder(ActionEvent event) throws IOException {
		ArrayList<Order> ordersToChange = new ArrayList<Order>();
		list = table.getSelectionModel().getSelectedItems();
		for (int i = 0; i < list.size(); i++) {
			ordersToChange.add(list.get(i));
		}
		ClientUI.chat.accept(new Message(MessageType.Order_approved, ordersToChange));
		for (int i = 0; i < allOrders.size(); i++) {
			for (int j = 0; j < ordersToChange.size(); j++) {
				if (allOrders.get(i).equals(ordersToChange.get(j)))
					allOrders.remove(i);
			}
		}
		ordersToChange.clear();
		list = FXCollections.observableArrayList(allOrders);
		table.setItems(list);
	}

	@FXML
	void refuseOrder(ActionEvent event) throws IOException {
		ArrayList<Order> ordersToChange = new ArrayList<Order>();
		list = table.getSelectionModel().getSelectedItems();
		for (int i = 0; i < list.size(); i++) {
			ordersToChange.add(list.get(i));
		}
		ClientUI.chat.accept(new Message(MessageType.Order_not_approved, ordersToChange));
		for (int i = 0; i < allOrders.size(); i++) {
			for (int j = 0; j < ordersToChange.size(); j++) {
				if (allOrders.get(i).equals(ordersToChange.get(j)))
					allOrders.remove(i);
			}
		}
		ordersToChange.clear();
		list = FXCollections.observableArrayList(allOrders);
		table.setItems(list);
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

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderNumber.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderNum"));
		orderType.setCellValueFactory(new PropertyValueFactory<Order, OrderType>("orderType"));
		restName.setCellValueFactory(new PropertyValueFactory<Order, String>("restName"));
		totalPrice.setCellValueFactory(new PropertyValueFactory<Order, Float>("totalPrice"));
		timeOfOrder.setCellValueFactory(new PropertyValueFactory<Order, String>("timeOfOrder"));
		dateOfOrder.setCellValueFactory(new PropertyValueFactory<Order, String>("dateOfOrder"));
		orderStatus.setCellValueFactory(new PropertyValueFactory<Order, String>("orderStatus"));
		costumerID.setCellValueFactory(new PropertyValueFactory<Order, String>("costumerId"));
		ClientUI.chat.accept(new Message(MessageType.get_orders_to_approve, null));
		list = FXCollections.observableArrayList(allOrders);
		table.setItems(list);
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}
}