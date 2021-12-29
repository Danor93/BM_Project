package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
	private ImageView BackImage;

	/**
	 * A method to return to the previous page. 
	 * @param event
	 */
	@Override
	public void Back(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
		startScreen(event, "LoginScreen", "Login");
	}

	@FXML
	void initialize() {
		assert ExistLbl != null : "fx:id=\"ExistLbl\" was not injected: check your FXML file 'SupplierScreen.fxml'.";
		assert btnCreateMenu != null
				: "fx:id=\"btnCreateMenu\" was not injected: check your FXML file 'SupplierScreen.fxml'.";
		assert btnUpdateMenu != null
				: "fx:id=\"btnUpdateMenu\" was not injected: check your FXML file 'SupplierScreen.fxml'.";
		assert btnUpdateOrderStatus != null
				: "fx:id=\"btnUpdateOrderStatus\" was not injected: check your FXML file 'SupplierScreen.fxml'.";
		assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'SupplierScreen.fxml'.";
	}

	/**
	 * A method for checking if there is a menu for this restaurant or not. 
	 * If not, open a new screen accordingly (AddDishToMenu with 'Create Menu' label). 
	 * If so, send an error message that the menu already exists.
	 * @param event
	 */
	@FXML
	void CreateMenu(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.MenuExist, LoginScreenController.user.getId()));
		if (ExisingMenuFlag == true) {
			ExistLbl.setText("Menu already exists, you can update it");
		} else {
			AddDishToMenuController.indicator = false;
			startScreen(event, "AddDishToMenu", "Create Menu");
		}

	}

	/**
	 * A method to open a new screen (UpdateMenuScreen).
	 * @param event
	 */
	@FXML
	void UpdateMenu(ActionEvent event) throws IOException {
		startScreen(event, "UpdateMenuScreen", "Update Menu");
	}

	/**
	 * A method to open a new screen (ConfirmOrderApproval).
	 * @param event
	 */
	@FXML
	void UpdateOrderStatus(ActionEvent event) throws IOException {
		startScreen(event, "ConfirmOrderApproval", "Update order status");
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}

}