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
import javafx.scene.input.MouseEvent;
import main.ClientUI;

public class AddDishToMenuController extends Controller implements Initializable {
	private boolean clearNameFlag = false;
	private boolean clearChoiceDetailsFlag = false;
	private boolean clearPriceFlag = false;
	private boolean clearInventoryFlag = false;
	private boolean clearChoiceFactorFlag = false;
	private boolean clearIngredientsFlag = false;
	private boolean clearRemovableIngredientsFlag = false;
	public static boolean indicator;// in case indicator=false then createMenu, if indicator=true then addNewDish
	public static String TypeOfDish;

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
		startScreen(event, "SupplierScreen", "Supplier page");
	}

	@FXML
	void ChooceTypeDish(ActionEvent event) {
		TypeOfDish = btnTypeDish.getSelectionModel().getSelectedItem();
	}

	@FXML
	void ConfirmNewDish(ActionEvent event) throws IOException {
		Dish dish = new Dish(null, null, null, null, null, null, 0, null);
		if (txtNameDish.getText().isEmpty())
			txtMiniLabel.setText("Name must be invailed!");
		else if (txtPriceDish.getText().isEmpty())
			txtMiniLabel.setText("price must be invailed!");
		else if (txtInventoryDish.getText().isEmpty())
			txtMiniLabel.setText("Inventory must be invailed!");
		else {
			try {
				Float price = Float.parseFloat(txtPriceDish.getText());
			} catch (Exception e) {
				txtMiniLabel.setText("The price must be invalid number");
				e.printStackTrace();
			}
			try {
				Integer inventory = Integer.parseInt(txtInventoryDish.getText());
			} catch (Exception e) {
				txtMiniLabel.setText("The inventory must be invalid number");
				e.printStackTrace();
			}

			System.out.println(TypeOfDish);
			try {
			//	dish = new Dish(txtNameDish.getText(), LoginScreenController.Name, null, null, null, null,
				//		Float.parseFloat(txtPriceDish.getText()),DishType.toDishType(TypeOfDish));
			} catch (NullPointerException e) {
				txtMiniLabel.setText("type must be selected!");
				e.printStackTrace();
			}
			dish.setRestCode(LoginScreenController.user.getId());
			if (txtChoiceDish.getText().equals("example: Size"))
				txtChoiceDish.setText("");
			dish.setChoiceFactor(txtChoiceDish.getText());
			if (txtChoiceDetailsDish.getText().equals("example: S/M/L"))
				txtChoiceDetailsDish.setText("");
			dish.setChoiceDetails(txtChoiceDetailsDish.getText());
			if (txtIngredients.getText()
					.equals("Put in the ingredients of the dish. \r\n"
							+ "Example: lettuce, cucumber, tomato, tuna and black olives. \r\n"
							+ "The salad is seasoned with parsley, olive oil and lemon."))
				txtIngredients.setText("");
			dish.setIngredients(txtIngredients.getText());
			if (txtIngredientsToRemove.getText().equals("Insert the removable dish ingredients. \r\n"
					+ "Example: cucumber, tomato, parsley, olive oil and lemon."))
				txtChoiceDetailsDish.setText("");
			dish.setExtra(txtIngredientsToRemove.getText());

			System.out.println(dish);
			CreateMenuScreenController.dishes.add(dish);
			ClientUI.chat.accept(new Message(MessageType.add_new_dish, dish));
			startScreen(event, "AddDishToMenu", "Delete or Update dish");
		}
	}

	@FXML
	void clearName(MouseEvent event) {
		if (clearNameFlag == false) {
			txtNameDish.clear();
			clearNameFlag = true;
		}
	}

	@FXML
	void clearPrice(MouseEvent event) {
		if (clearPriceFlag == false) {
			txtPriceDish.clear();
			clearPriceFlag = true;
		}
	}

	@FXML
	void clearInventory(MouseEvent event) {
		if (clearInventoryFlag == false) {
			txtInventoryDish.clear();
			clearInventoryFlag = true;
		}
	}

	@FXML
	void clearChoiceFactor(MouseEvent event) {
		if (clearChoiceFactorFlag == false) {
			txtChoiceDish.clear();
			clearChoiceFactorFlag = true;
		}
	}

	@FXML
	void clearChoiceDetails(MouseEvent event) {
		if (clearChoiceDetailsFlag == false) {
			txtChoiceDetailsDish.clear();
			clearChoiceDetailsFlag = true;
		}
	}

	@FXML
	void clearIngredients(MouseEvent event) {
		if (clearIngredientsFlag == false) {
			txtIngredients.clear();
			clearIngredientsFlag = true;
		}
	}

	@FXML
	void clearRemovableIngredients(MouseEvent event) {
		if (clearRemovableIngredientsFlag == false) {
			txtIngredientsToRemove.clear();
			clearRemovableIngredientsFlag = true;
		}
	}

	@FXML
	void initialize() {
		if (indicator == false)
			miniLabel.setText("Create Menu");
		else
			miniLabel.setText("Add new dish");

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
		btnTypeDish.getItems().addAll("Salad", "Starter", "Main dish", "Dessert", "Drink");
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}
}