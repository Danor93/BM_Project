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
import main.PopUpMessage;

public class AddDishToMenuController extends Controller implements Initializable {
	public static boolean indicator;// in case indicator=false then createMenu, if indicator=true then addNewDish
	public static String TypeOfDish;
	public static boolean dishAdd = false;

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
	private TextArea txtExtra;

	private boolean typeDishIsValid = true;
	private boolean choiceDetailsIsValid = true;
	private boolean choiceFactorIsValid = true;
	private boolean continuedFlag = true;
	private boolean choiceDetailsWithoutChoiceFactorFlag = true;
	private boolean ingredientsIsValid = true;

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
			txtMiniLabel.setText("Price must be invailed!");

		else {
			try {
				Float price = Float.parseFloat(txtPriceDish.getText());
			} catch (Exception e) {
				txtMiniLabel.setText("Price must be invailed number!");
				e.printStackTrace();
			}
			try {
				dish = new Dish(txtNameDish.getText(), LoginScreenController.Name, null, null, null, null,
						Float.parseFloat(txtPriceDish.getText()), DishType.toDishType(TypeOfDish));
			} catch (NullPointerException e) {
				e.printStackTrace();
				typeDishIsValid = false;
			}
			if (typeDishIsValid == true) {
				dish.setRestCode(LoginScreenController.ID);
				if (txtChoiceDish.getText().isEmpty()) {
					dish.setChoiceFactor("");
					choiceFactorIsValid = false;
					if (!txtChoiceDetailsDish.getText().isEmpty()) {
						choiceDetailsWithoutChoiceFactorFlag = false;
					}
				} else {
					dish.setChoiceFactor(txtChoiceDish.getText());
				}
				if (choiceDetailsWithoutChoiceFactorFlag) {
					if (txtChoiceDetailsDish.getText().isEmpty()) {
						if (choiceFactorIsValid) {
							continuedFlag = false;
						} else {
							dish.setChoiceDetails("");
						}
					} else {
						String[] choiceDetailsDish = ((String[]) txtChoiceDetailsDish.getText().split("/"));
						if (choiceDetailsDish.length == 1) {
							choiceDetailsIsValid = false;
						} else
							dish.setChoiceDetails(txtChoiceDetailsDish.getText());
					}
				}
				if (continuedFlag) {
					if (choiceDetailsIsValid) {
						if (choiceDetailsWithoutChoiceFactorFlag) {
							if (txtIngredients.getText().isEmpty())
								ingredientsIsValid = false;
							else
								dish.setIngredients(txtIngredients.getText());
							if (ingredientsIsValid) {
								if (txtExtra.getText().isEmpty())
									dish.setExtra("");
								else
									dish.setExtra(txtExtra.getText());
								CreateMenuScreenController.dishes.add(dish);
								ClientUI.chat.accept(new Message(MessageType.add_new_dish, dish));
								if (dishAdd == false) {
									txtMiniLabel.setText("Update failed. This dish is already on the menu");
								} else {
									startScreen(event, "AddDishToMenu", "Delete or Update dish");
								}
							} else {
								txtMiniLabel.setText("You must enter the ingredients of the dish");
								ingredientsIsValid = true;
							}
						} else {
							txtMiniLabel.setText(
									"You cannot enter a value for the Choice details without entering a value for the Choice factor");
							choiceDetailsWithoutChoiceFactorFlag = true;
						}
					} else {
						txtMiniLabel.setText("You must separate the choice details by the character - '/'");
						choiceDetailsIsValid = true;
					}
				} else {
					txtMiniLabel.setText("If you entered a choice factor, you must also enter choice details");
					continuedFlag = true;
				}
			} else {
				txtMiniLabel.setText("Type must be selected!");
				typeDishIsValid = true;
			}
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
		assert txtExtra != null
				: "fx:id=\"txtIngredientsToRemove\" was not injected: check your FXML file 'AddDishToMenu.fxml'.";
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (indicator == false)
			miniLabel.setText("Create Menu");
		else
			miniLabel.setText("Add new dish");

		btnTypeDish.getItems().addAll("Salad", "Starter", "Main dish", "Dessert", "Drink");
	}
}