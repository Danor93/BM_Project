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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import main.ClientUI;

public class AddDishToMenuController extends Controller implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView BackImage;

	@FXML
	private Label miniLabel;

	@FXML
	private TextField txtNameDish;

	@FXML
	private TextField txtPriceDish;

	@FXML
	private TextField txtInventoryDish;

	@FXML
	private Button btnConfirm;

	@FXML
	private TextArea txtIngredients;

	@FXML
	private Button btnBack;

	@FXML
	private Label txtMiniLabel;

	@FXML
	private TextField txtChoiceDish;

	@FXML
	private TextField txtChoiceDetailsDish;

	@FXML
	private ComboBox<String> btnTypeDish;

	@FXML
	private TextArea txtIngredientsToRemove;

	@FXML
	void Back(ActionEvent event) throws IOException {
		startScreen(event, "CreateMenuScreen", "Create Menu");
	}

	@FXML
	void ConfirmNewDish(ActionEvent event) throws IOException {
		Dish dish;
		if (txtNameDish.getText().isEmpty())
			miniLabel.setText("Name must be invailed!");
		else if (txtPriceDish.getText().isEmpty())
			miniLabel.setText("price must be invailed!");
		else if (btnTypeDish.toString().equals("example: Salad"))
			miniLabel.setText("type must be selected!");
		else if (txtInventoryDish.getText().isEmpty())
			miniLabel.setText("Inventory must be invailed!");
		else {
			dish = new Dish(txtNameDish.getText(), LoginScreenController.Name, Float.parseFloat(txtPriceDish.getText()),
					Integer.parseInt(txtInventoryDish.getText()),
					DishType.toDishType(btnTypeDish.getValue().toString()));
			dish.setExtra(txtIngredientsToRemove.getText());
			dish.setIngredients(txtIngredients.getText());
			dish.setRestCode(LoginScreenController.ID);
			dish.setChoiceFactor(txtChoiceDish.getText());
			dish.setDetailsOfChoice(txtChoiceDetailsDish.getText());
			System.out.println(DishType.toDishType(btnTypeDish.getValue().toString()));
			System.out.println(dish);
			CreateMenuScreenController.dishes.add(dish);
			ClientUI.chat.accept(new Message(MessageType.add_new_dish, dish));
			startScreen(event, "CreateMenuScreen", "Create Menu");
		}
	}

	@FXML
	void initialize() {
		assert BackImage != null : "fx:id=\"BackImage\" was not injected: check your FXML file 'AddDishToMenu.fxml'.";
		assert miniLabel != null : "fx:id=\"miniLabel\" was not injected: check your FXML file 'AddDishToMenu.fxml'.";
		assert txtNameDish != null
				: "fx:id=\"txtNameDish\" was not injected: check your FXML file 'AddDishToMenu.fxml'.";
		assert txtPriceDish != null
				: "fx:id=\"txtPriceDish\" was not injected: check your FXML file 'AddDishToMenu.fxml'.";
		assert txtInventoryDish != null
				: "fx:id=\"txtInventoryDish\" was not injected: check your FXML file 'AddDishToMenu.fxml'.";
		assert btnConfirm != null : "fx:id=\"btnConfirm\" was not injected: check your FXML file 'AddDishToMenu.fxml'.";
		assert txtIngredients != null
				: "fx:id=\"txtIngredients\" was not injected: check your FXML file 'AddDishToMenu.fxml'.";
		assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'AddDishToMenu.fxml'.";
		assert txtMiniLabel != null
				: "fx:id=\"txtMiniLabel\" was not injected: check your FXML file 'AddDishToMenu.fxml'.";
		assert txtChoiceDish != null
				: "fx:id=\"txtChoiseDish\" was not injected: check your FXML file 'AddDishToMenu.fxml'.";
		assert txtChoiceDetailsDish != null
				: "fx:id=\"txtChoiseDetailsDish\" was not injected: check your FXML file 'AddDishToMenu.fxml'.";
		assert btnTypeDish != null
				: "fx:id=\"btnTypeDish\" was not injected: check your FXML file 'AddDishToMenu.fxml'.";
		assert txtIngredientsToRemove != null
				: "fx:id=\"txtIngredientsToRemove\" was not injected: check your FXML file 'AddDishToMenu.fxml'.";
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnTypeDish.getItems().addAll("Salad", "Starter", "MainDish", "Dessert", "Drink");
	}
}
