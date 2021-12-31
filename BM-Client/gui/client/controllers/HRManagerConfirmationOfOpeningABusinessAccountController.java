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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.ClientUI;
import javafx.scene.text.Text;
import main.PopUpMessage;

/**
 * @author Aviel * This class is intended for approving / refusing new business
 *         account.
 */
public class HRManagerConfirmationOfOpeningABusinessAccountController extends Controller implements Initializable {
	public static ArrayList<BusinessAccountTracking> trackingDetails = new ArrayList<BusinessAccountTracking>();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView BackImage;

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

	@FXML
	private Text userName;

	@FXML
	private ImageView homePage;

	@FXML
	private Button logout;

	ObservableList<BusinessAccountTracking> list;

	/**
	 * This method meant to get back to HR manager page
	 * 
	 * @param event = ActionEvent
	 */
	@FXML
	void backToHome(MouseEvent event) throws IOException {
		start(event, "HRManagerScreen", "HR Manager page",LoginScreenController.user.getFirstN());
	}

	/**
	 * This method meant to get back to login page and logout the HR manager
	 * 
	 * @param event = ActionEvent
	 */
	@FXML
	void logout(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login", "");
	}

	/**
	 * A method of confirming a new business account waiting for approval.
	 * 
	 * @param event = ActionEvent
	 */
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

	/**
	 * A method of refusing a new business account waiting for approval.
	 * 
	 * @param event = ActionEvent
	 */
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
		assert BackImage != null
				: "fx:id=\"BackImage\" was not injected: check your FXML file 'HRManagerConfirmationOfOpeningABusinessAccount.fxml'.";
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
		assert userName != null
				: "fx:id=\"userName\" was not injected: check your FXML file 'HRManagerConfirmationOfOpeningABusinessAccount.fxml'.";
		assert homePage != null
				: "fx:id=\"homePage\" was not injected: check your FXML file 'HRManagerConfirmationOfOpeningABusinessAccount.fxml'.";
		assert logout != null
				: "fx:id=\"logout\" was not injected: check your FXML file 'HRManagerConfirmationOfOpeningABusinessAccount.fxml'.";
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ID.setCellValueFactory(new PropertyValueFactory<BusinessAccountTracking, String>("ID"));
		companyName.setCellValueFactory(new PropertyValueFactory<BusinessAccountTracking, String>("companyName"));
		budget.setCellValueFactory(new PropertyValueFactory<BusinessAccountTracking, String>("budget"));
		String DivededUandP[] = ((String) LoginScreenController.user.getRole()).split("-");
		String fullCompanyName = String.valueOf(DivededUandP[1]);
		ClientUI.chat.accept(new Message(MessageType.get_business_account_details, fullCompanyName));
		list = FXCollections.observableArrayList(trackingDetails);
		table.setItems(list);
	}

	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN());
	}
}