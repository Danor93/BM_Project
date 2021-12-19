package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Dish;
import Entities.DishType;
import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import main.ChatClient;
import main.ClientUI;

public class AddNewSaladToMenuController extends Controller {

	@FXML
	private Label txtMiniLabel;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView BackImage;

	@FXML
	private Label miniLabel;

	@FXML
	private TextField txtSaladName;

	@FXML
	private TextField txtPriceOfSalad;

	@FXML
	private TextField txtInventorySalad;

	@FXML
	private Button btnConfirm;

	@FXML
	private CheckBox SmallMark;

	@FXML
	private CheckBox MediumMark;

	@FXML
	private CheckBox LargeMark;

	@FXML
	private CheckBox OneSizeMark;

	@FXML
	private TextArea txtIngredients;

	@FXML
	private Button btnBack;

	@FXML
	void Back(ActionEvent event) throws IOException {
		startScreen(event, "CreateMenuScreen", "Create Menu");
	}

	@FXML
	void ConfirmNewSalad(ActionEvent event) throws IOException {
		Dish dish;
		if (txtSaladName.getText().isEmpty()) {
			miniLabel.setText("Name must be invailed!");
		} else if (txtPriceOfSalad.getText().isEmpty()) {
			miniLabel.setText("price must be invailed!");
		} else if (!OneSizeMark.isSelected()) {
			if (!(SmallMark.isSelected()) && !(MediumMark.isSelected()) && !(LargeMark.isSelected()))
				txtMiniLabel.setText("Choose at least 1 size");

			else {
				StringBuilder sizeStr = new StringBuilder();
				if (SmallMark.isSelected()) {
					sizeStr.append("S/");
				}
				if (MediumMark.isSelected()) {
					sizeStr.append("M/");
				}
				if (LargeMark.isSelected()) {
					sizeStr.append("L/");
				}
				if (OneSizeMark.isSelected()) {
					sizeStr.append("O");
				}
				dish = new Dish(txtSaladName.getText(), LoginScreenController.Name,
						null, null, null, null, Float.parseFloat(txtPriceOfSalad.getText()), Integer.parseInt(txtInventorySalad.getText()),
						DishType.toDishType("Salad"));
				//dish.setSize(sizeStr.toString());//fix
				dish.setExtra(txtIngredients.getText());
				dish.setRestCode(LoginScreenController.ID);
				System.out.println(dish);
				CreateMenuScreenController.dishes.add(dish);
				ClientUI.chat.accept(new Message(MessageType.add_new_dish, dish));
				startScreen(event, "CreateMenuScreen", "Create Menu");
			}
		}
	}

	@FXML
	void OneSize(ActionEvent event) {
		SmallMark.setSelected(false);
		LargeMark.setSelected(false);
		LargeMark.setSelected(false);
	}

	@FXML
	void initialize() {
		//setImage(BackImage, "background.png");
		assert BackImage != null
				: "fx:id=\"BackImage\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
		assert miniLabel != null
				: "fx:id=\"miniLabel\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
		assert txtSaladName != null
				: "fx:id=\"txtSaladName\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
		assert txtPriceOfSalad != null
				: "fx:id=\"txtPriceOfSalad\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
		assert btnConfirm != null
				: "fx:id=\"btnConfirm\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
		assert SmallMark != null
				: "fx:id=\"SmallMark\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
		assert MediumMark != null
				: "fx:id=\"MediumMark\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
		assert LargeMark != null
				: "fx:id=\"LargeMark\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
		assert OneSizeMark != null
				: "fx:id=\"OneSizeMark\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
		assert txtIngredients != null
				: "fx:id=\"txtIngredients\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
		assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
		assert txtMiniLabel != null
				: "fx:id=\"txtMiniLabel\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
		assert txtInventorySalad != null
				: "fx:id=\"txtInvenorySalads\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
	}
}