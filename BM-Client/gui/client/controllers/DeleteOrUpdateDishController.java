package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Dish;
import Entities.DishType;
import Entities.Message;
import Entities.MessageType;
import Parsing.Parsing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.ClientUI;

public class DeleteOrUpdateDishController extends Controller implements Initializable {
	public static ArrayList<Dish> dishes = new ArrayList<Dish>();
	public static String TypeOfDish;
	public static String NameOfDish;
	public static int PlaceOfDish;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView BackImage;

	@FXML
	private Label miniLabel;

	@FXML
	private TextField txtNewPriceDish;

	@FXML
	private TextField txtNewInventoryDish;

	@FXML
	private Button btnDeleteDish;

	@FXML
	private TextArea txtNewIngredients;

	@FXML
	private Button btnBack;

	@FXML
	private Label txtMiniLabel;

	@FXML
	private TextField txtNewChoiceDish;

	@FXML
	private TextField txtNewChoiceDetailsDish;

	@FXML
	private ComboBox<String> btnDish;

	@FXML
	private TextArea txtNewIngredientsToRemove;

	@FXML
	private ComboBox<String> btnDishType;

	@FXML
	private Button btnConfirm;

	@FXML
	void BackToUpdateMenu(ActionEvent event) throws IOException {
		startScreen(event, "UpdateMenuScreen", "Update menu");
	}

	@FXML
	void ChoocTypeOfDish(ActionEvent event) {
		btnDish.getItems().clear();
		for (int i = 0; i < dishes.size(); i++) {
			System.out.println(dishes.get(i).getDishName().toString() + "\n");
		}
		TypeOfDish = btnDishType.getSelectionModel().getSelectedItem();
		System.out.println("TypeOfDish= " + TypeOfDish);
		for (int i = 0; i < dishes.size(); i++) {
			System.out.println(DeleteOrUpdateDishController.dishes.get(i).getDishName());
			if (DishType.typeToString(dishes.get(i).getDishType()).equals(TypeOfDish)) {
				btnDish.getItems().add(dishes.get(i).getDishName().toString());
			}
		}
	}

	@FXML
	void ChooseDish(ActionEvent event) {
		NameOfDish = btnDish.getSelectionModel().getSelectedItem();
		for (int i = 0; i < dishes.size(); i++) {
			if (NameOfDish.equals(dishes.get(i).getDishName().toString())) {
				txtNewPriceDish.setText(String.valueOf(dishes.get(i).getPrice()));
				txtNewInventoryDish.setText(String.valueOf(dishes.get(i).getInventory()));
				txtNewChoiceDish.setText(dishes.get(i).getChoiceFactor());
				txtNewChoiceDetailsDish.setText(dishes.get(i).getDetailsOfChoice());
				txtNewIngredients.setText(dishes.get(i).getIngredients());
				txtNewIngredientsToRemove.setText(dishes.get(i).getExtra());
				PlaceOfDish = i;
			}
		}
	}

	@FXML
	void ConfirmUpdate(ActionEvent event) throws IOException {
		Dish dish = new Dish(null, null, 0, 0, null);
		if (txtNewPriceDish.getText().isEmpty())
			txtMiniLabel.setText("price must be invailed!");
		else if (txtNewInventoryDish.getText().isEmpty())
			txtMiniLabel.setText("Inventory must be invailed!");
		else {
			try {
				Float price = Float.parseFloat(txtNewPriceDish.getText());
			} catch (Exception e) {
				txtMiniLabel.setText("The price must be invalid number");
				e.printStackTrace();
			}
			try {
				Integer inventory = Integer.parseInt(txtNewInventoryDish.getText());
			} catch (Exception e) {
				txtMiniLabel.setText("The inventory must be invalid number");
				e.printStackTrace();
			}
			try {
				String dishType = btnDishType.getSelectionModel().getSelectedItem();
			} catch (NullPointerException e) {
				txtMiniLabel.setText("Type must be selected!");
				e.printStackTrace();
			}
			try {
				String dishName = btnDish.getSelectionModel().getSelectedItem();
			} catch (NullPointerException e) {
				txtMiniLabel.setText("Name must be selected!");
				e.printStackTrace();
			}
			dish = new Dish(btnDish.getSelectionModel().getSelectedItem(), LoginScreenController.Name,
					Float.parseFloat(txtNewPriceDish.getText()), Integer.parseInt(txtNewInventoryDish.getText()),
					DishType.toDishType(TypeOfDish));
			dish.setRestCode(LoginScreenController.ID);
			dish.setChoiceFactor(txtNewChoiceDish.getText());
			dish.setDetailsOfChoice(txtNewChoiceDetailsDish.getText());
			dish.setIngredients(txtNewIngredients.getText());
			dish.setExtra(txtNewIngredientsToRemove.getText());
			System.out.println(dish);
			ClientUI.chat.accept(new Message(MessageType.updateDish, dish));
			startScreen(event, "DeleteOrUpdateDish", "Create Menu");
		}
	}

	@FXML
	void DeleteDish(ActionEvent event) {

	}

	@FXML
	void initialize() {
		assert BackImage != null
				: "fx:id=\"BackImage\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert miniLabel != null
				: "fx:id=\"miniLabel\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert txtNewPriceDish != null
				: "fx:id=\"txtNewPriceDish\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert txtNewInventoryDish != null
				: "fx:id=\"txtNewInventoryDish\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert btnDeleteDish != null
				: "fx:id=\"btnDeleteDish\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert txtNewIngredients != null
				: "fx:id=\"txtNewIngredients\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert txtMiniLabel != null
				: "fx:id=\"txtMiniLabel\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert txtNewChoiceDish != null
				: "fx:id=\"txtNewChoiceDish\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert txtNewChoiceDetailsDish != null
				: "fx:id=\"txtNewChoiceDetailsDish\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert btnDish != null : "fx:id=\"btnDish\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert txtNewIngredientsToRemove != null
				: "fx:id=\"txtNewIngredientsToRemove\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert btnDishType != null
				: "fx:id=\"btnDishType\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert btnConfirm != null
				: "fx:id=\"btnConfirm\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnDishType.getItems().addAll("Salad", "Starter", "Main dish", "Dessert", "Drink");
	}
}