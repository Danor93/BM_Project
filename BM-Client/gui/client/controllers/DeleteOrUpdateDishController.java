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
	public static float PriceOfDish;
	public static int InvOfDish;
	public DishType dishtype;
	public static int PlaceOfDish;
	private boolean NameAndTypeCorrect = false;
	private boolean CorrectPrice = false;
	private boolean CorrectInv = false;

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
			System.out.println("this here!"+dishes.get(i).getDishName().toString() );
		}
		TypeOfDish = btnDishType.getSelectionModel().getSelectedItem();
		System.out.println("TypeOfDish= " + TypeOfDish);
		for (int i = 0; i < dishes.size(); i++) {
			// System.out.println("all the dishes of the supp : "+ d.getDishName());
			if (dishes.get(i) != null) {
				if (DishType.fromTypeToStr(dishes.get(i).getDishType()).equals(TypeOfDish)) {
					System.out.println("dishes in this type: " + dishes.get(i).getDishName());
					btnDish.getItems().add(dishes.get(i).getDishName());
				}
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
				//txtNewChoiceDetailsDish.setText(dishes.get(i).getDetailsOfChoice());//fix
				txtNewIngredients.setText(dishes.get(i).getIngredients());
				txtNewIngredientsToRemove.setText(dishes.get(i).getExtra());
				PlaceOfDish = i;
			}
		}
	}

	@FXML
	void ConfirmUpdate(ActionEvent event) throws IOException {
		Dish dish = new Dish(null, null, null, null, null, null, 0, 0, null);

		try {
			dishtype = DishType.toDishType(TypeOfDish);
			// NameOfDish = btnDish.getSelectionModel().getSelectedItem();
			if (NameOfDish == null) {
				txtMiniLabel.setText("Name must be selected!");
			} else {
				System.out.println("NameOfdish=" + NameOfDish);
				NameAndTypeCorrect = true;
			}

		} catch (NullPointerException e) {
			txtMiniLabel.setText("Type must be selected!");
			e.printStackTrace();
		}
		if (NameAndTypeCorrect) {
			if (txtNewPriceDish.getText().isEmpty())
				txtMiniLabel.setText("price must be invailed!");
			else if (txtNewInventoryDish.getText().isEmpty())
				txtMiniLabel.setText("Inventory must be invailed!");
			else {
				try {
					PriceOfDish = Float.parseFloat(txtNewPriceDish.getText());
					CorrectPrice = true;
				} catch (Exception e) {
					txtMiniLabel.setText("The price must be invalid number");
					e.printStackTrace();
				}
				try {
					InvOfDish = Integer.parseInt(txtNewInventoryDish.getText());
					CorrectInv = true;
				} catch (Exception e) {
					txtMiniLabel.setText("The inventory must be invalid number");
					e.printStackTrace();
				}

			}
			if (NameAndTypeCorrect && CorrectPrice && CorrectInv) {
				String SupplierName = LoginScreenController.Name;
				//dish = new Dish(NameOfDish, SupplierName, PriceOfDish, InvOfDish, dishtype);/*need to be fit the the ned dish entity.*/
				dish.setRestCode(LoginScreenController.ID);
				if (txtNewChoiceDish.getText().equals("null")) {
					txtNewChoiceDish.setText("");
				}
				dish.setChoiceFactor(txtNewChoiceDish.getText());
				if (txtNewChoiceDetailsDish.getText().equals("null")) {
					txtNewChoiceDetailsDish.setText("");
				}
				//dish.setDetailsOfChoice(txtNewChoiceDetailsDish.getText());//fix.
				if (txtNewIngredients.getText().equals("null")) {
					txtNewIngredients.setText("");
				}

				dish.setIngredients(txtNewIngredients.getText());
				if (txtNewIngredientsToRemove.getText().equals("null")) {
					txtNewIngredientsToRemove.setText("");
				}
				dish.setExtra(txtNewIngredientsToRemove.getText());
				System.out.println(dish.toString());
				ClientUI.chat.accept(new Message(MessageType.updateDish, dish));
				startScreen(event, "DeleteOrUpdateDish", "Create Menu");
				dish = null;
			}
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