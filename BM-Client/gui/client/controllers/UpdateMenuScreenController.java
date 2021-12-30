package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Parsing.Parsing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.ClientUI;
import javafx.scene.text.Text;

/**
 * @author Aviel This class is for select - Update/Delete existing dish, or add
 *         new dish into menu.
 */
public class UpdateMenuScreenController extends Controller {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView BackImage;

	@FXML
	private Label miniLabel;

	@FXML
	private Button btnAddNewDishToMenu;

	@FXML
	private Label txtMiniLabel;

	@FXML
	private Button btnDeleteOrUpdateDish;

	@FXML
	private Text userName;

	@FXML
	private ImageView homePage;

	@FXML
	private Button logout;

	/**
	 * A method to open a new screen (AddDishToMenu), and change value of
	 * 'indicator' to be true.
	 * 
	 * @param event = ActionEvent
	 */
	@FXML
	void AddNewDishToMenu(ActionEvent event) throws IOException {
		AddDishToMenuController.indicator = true;
		start(event, "AddDishToMenu", "Add new dish","");
	}

	/**
	 * A method to open a new screen (DeleteOrUpdateDish), once we have received
	 * from the database the existing dishes in this menu.
	 * 
	 * @param event = ActionEvent
	 */
	@FXML
	void DeleteOrUpdateDish(ActionEvent event) throws IOException {
		Message msg = new Message(MessageType.Show_Dishes, LoginScreenController.user.getId());
		ClientUI.chat.accept(msg);
		start(event, "DeleteOrUpdateDish", "Delete or Update dish","");
	}

	/**
	 * This method meant to get back to supplier page
	 * 
	 * @param event = ActionEvent
	 */
	@FXML
	void backToHome(MouseEvent event) throws IOException {
		start(event, "SupplierScreen", "Supplier page", "");
	}

	/**
	 * This method meant to get back to login page and logout the supplier
	 * 
	 * @param event = ActionEvent
	 */
	@FXML
	void logout(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login", "");
	}

	@FXML
	void initialize() {
		assert BackImage != null
				: "fx:id=\"BackImage\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
		assert miniLabel != null
				: "fx:id=\"miniLabel\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
		assert btnAddNewDishToMenu != null
				: "fx:id=\"btnAddNewDishToMenu\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
		assert txtMiniLabel != null
				: "fx:id=\"txtMiniLabel\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
		assert btnDeleteOrUpdateDish != null
				: "fx:id=\"btnDeleteOrUpdateDish\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
		assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
		assert homePage != null : "fx:id=\"homePage\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
		assert logout != null : "fx:id=\"logout\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";

	}

	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN());
	}
}