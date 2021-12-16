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
import main.ClientUI;

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
	private Button btnBack;

	@FXML
	private Label txtMiniLabel;

	@FXML
	private Button btnDeleteOrUpdateDish;

	@FXML
	void AddNewDishToMenu(ActionEvent event) {

	}

	@FXML
	void BackToSupplier(ActionEvent event) throws IOException {
		startScreen(event, "SupplierScreen", "Supplier page");
	}

	@FXML
	void DeleteOrUpdateDish(ActionEvent event) throws IOException {
		Message msg = new Message(MessageType.Show_Dishes, LoginScreenController.user.getId());
		ClientUI.chat.accept(msg);
		startScreen(event, "DeleteOrUpdateDish", "Delete or Update dish");
	}

	@FXML
	void initialize() {
		assert BackImage != null
				: "fx:id=\"BackImage\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
		assert miniLabel != null
				: "fx:id=\"miniLabel\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
		assert btnAddNewDishToMenu != null
				: "fx:id=\"btnAddNewDishToMenu\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
		assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
		assert txtMiniLabel != null
				: "fx:id=\"txtMiniLabel\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
		assert btnDeleteOrUpdateDish != null
				: "fx:id=\"btnDeleteOrUpdateDish\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";

	}
}