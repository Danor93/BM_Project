package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.BusinessAccountTracking;
import Entities.Employer;
import Entities.Message;
import Entities.MessageType;
import Entities.Order;
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
import main.PopUpMessage;

public class HRManagerConfirmationOfOpeningABusinessAccountController extends Controller implements Initializable {
	public static ArrayList<BusinessAccountTracking> trackingDetails = new ArrayList<BusinessAccountTracking>();

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
	private TableView<BusinessAccountTracking> table;

	@FXML
	private TableColumn<BusinessAccountTracking, String> ID;

	@FXML
	private TableColumn<BusinessAccountTracking, String> companyName;

	@FXML
	private TableColumn<BusinessAccountTracking, String> budget;

	ObservableList<BusinessAccountTracking> list;

	@FXML
	void back(ActionEvent event) throws IOException {
		startScreen(event, "HRManagerScreen", "HR Manager");
	}

	@FXML
	void confirmBusinessAccount(ActionEvent event) {
		BusinessAccountTracking orderToChange;
		list = table.getSelectionModel().getSelectedItems();
		orderToChange = list.get(0);	
		ClientUI.chat.accept(new Message(MessageType.update_status_approved_businessAccount, orderToChange));
		for (int i = 0; i < trackingDetails.size(); i++) {
				if (trackingDetails.get(i).equals(orderToChange))
					trackingDetails.remove(i);
			}
		orderToChange = null;
		list = FXCollections.observableArrayList(trackingDetails);
		table.setItems(list);
	}

	@FXML
	void refuseBusinessAccount(ActionEvent event) {
		BusinessAccountTracking orderToChange;
		list = table.getSelectionModel().getSelectedItems();
		orderToChange = list.get(0);
		ClientUI.chat.accept(new Message(MessageType.update_status_NotApproved_businessAccount, orderToChange));
		for (int i = 0; i < trackingDetails.size(); i++) {
				if (trackingDetails.get(i).equals(orderToChange))
					trackingDetails.remove(i);
		}

		orderToChange = null;
		list = FXCollections.observableArrayList(trackingDetails);
		table.setItems(list);
	}

	@FXML
	void initialize() {
		assert back != null
				: "fx:id=\"back\" was not injected: check your FXML file 'HRManagerConfirmationOfOpeningABusinessAccount.fxml'.";
		assert Refuse != null
				: "fx:id=\"Refuse\" was not injected: check your FXML file 'HRManagerConfirmationOfOpeningABusinessAccount.fxml'.";
		assert Confirm != null
				: "fx:id=\"Confirm\" was not injected: check your FXML file 'HRManagerConfirmationOfOpeningABusinessAccount.fxml'.";
		assert table != null
				: "fx:id=\"table\" was not injected: check your FXML file 'HRManagerConfirmationOfOpeningABusinessAccount.fxml'.";
		assert ID != null
				: "fx:id=\"ID\" was not injected: check your FXML file 'HRManagerConfirmationOfOpeningABusinessAccount.fxml'.";
		assert companyName != null
				: "fx:id=\"companyName\" was not injected: check your FXML file 'HRManagerConfirmationOfOpeningABusinessAccount.fxml'.";
		assert budget != null
				: "fx:id=\"budget\" was not injected: check your FXML file 'HRManagerConfirmationOfOpeningABusinessAccount.fxml'.";

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ID.setCellValueFactory(new PropertyValueFactory<BusinessAccountTracking, String>("ID"));
		companyName.setCellValueFactory(new PropertyValueFactory<BusinessAccountTracking, String>("companyName"));
		budget.setCellValueFactory(new PropertyValueFactory<BusinessAccountTracking, String>("budget"));
		ClientUI.chat.accept(new Message(MessageType.get_business_account_details, LoginScreenController.CompanyName));
		list = FXCollections.observableArrayList(trackingDetails);
		table.setItems(list);
	}
}