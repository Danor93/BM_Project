package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import main.ClientUI;

/**
 * @author Aviel 
 * This class is for supplier main screen.
 */
public class SupplierScreenController extends Controller implements ControllerInterface {
	public static boolean ExisingMenuFlag = false;
	@FXML
	private ResourceBundle resources;
	@FXML
	private Label ExistLbl;

	@FXML
	private URL location;

	@FXML
	private Button btnCreateMenu;

	@FXML
	private Button btnUpdateMenu;

	@FXML
	private Button btnUpdateOrderStatus;

	@FXML
	private Button btnBack;

	@FXML
	private ImageView backImg;

	@FXML
	private Text WelcomeLabel;

	@FXML
	private Button btnViewReceipt;

	private String[] DivededUandP = ((String) LoginScreenController.user.getRole()).split("-");

	/**
	 * This method meant to get back to login page and logout the supplier
	 * 
	 * @param event = ActionEvent
	 */
	@Override
	public void Back(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login", "");
	}

	@FXML
	void initialize() {
		assert backImg != null : "fx:id=\"backImg\" was not injected: check your FXML file 'SupplierScreen.fxml'.";
		assert ExistLbl != null : "fx:id=\"ExistLbl\" was not injected: check your FXML file 'SupplierScreen.fxml'.";
		assert btnCreateMenu != null
				: "fx:id=\"btnCreateMenu\" was not injected: check your FXML file 'SupplierScreen.fxml'.";
		assert btnUpdateMenu != null
				: "fx:id=\"btnUpdateMenu\" was not injected: check your FXML file 'SupplierScreen.fxml'.";
		assert btnUpdateOrderStatus != null
				: "fx:id=\"btnUpdateOrderStatus\" was not injected: check your FXML file 'SupplierScreen.fxml'.";
		assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'SupplierScreen.fxml'.";
		assert btnViewReceipt != null
				: "fx:id=\"btnViewReceipt\" was not injected: check your FXML file 'SupplierScreen.fxml'.";
	}

	/**
	 * A method for checking if there is a menu for this restaurant or not. If not,
	 * open a new screen accordingly (AddDishToMenu with 'Create Menu' label). If
	 * so, send an error message that the menu already exists.
	 * 
	 * @param event = ActionEvent
	 */
	@FXML
	void CreateMenu(ActionEvent event) throws IOException {
		if (DivededUandP[1].equals("Certified")) {
			ClientUI.chat.accept(new Message(MessageType.MenuExist, LoginScreenController.user.getId()));
			if (ExisingMenuFlag) {
				ExistLbl.setText("Menu already exists, you can update it");
			} else {
				AddDishToMenuController.indicator = false;
				start(event, "AddDishToMenu", "Create Menu",LoginScreenController.user.getFirstN());
			}
		} else
			ExistLbl.setText("Only the certified employee can create a menu.");
	}

	/**
	 * A method to open a new screen (UpdateMenuScreen).
	 * 
	 * @param event = ActionEvent
	 */
	@FXML
	void UpdateMenu(ActionEvent event) throws IOException {
		if (DivededUandP[1].equals("Certified")) {
			start(event, "UpdateMenuScreen", "Update Menu",LoginScreenController.user.getFirstN());
		} else
			ExistLbl.setText("Only the certified employee can create a menu.");
	}

	/**
	 * A method to open a new screen (ConfirmOrderApproval).
	 * 
	 * @param event = ActionEvent
	 */
	@FXML
	void UpdateOrderStatus(ActionEvent event) throws IOException {
		if (DivededUandP[1].equals("Approved")) {
			start(event, "ConfirmOrderApproval", "Update order status",LoginScreenController.user.getFirstN());
		} else
			ExistLbl.setText("Only the certified employee can update order status.");
	}

	/**
	 * A method to open a new screen (ViewReceipt).
	 * 
	 * @param event = ActionEvent
	 */
	@FXML
	void viewReceipt(ActionEvent event) throws IOException {
		if (DivededUandP[1].equals("Certified")) {
			start(event, "ViewReceipt", "View Receipt",LoginScreenController.user.getFirstN());
		} else
			ExistLbl.setText("Only the certified employee can view receipt.");
	}

	@Override
	public void display(String string) {
		WelcomeLabel.setText("Welcome, " + LoginScreenController.user.getFirstN());
	}
}