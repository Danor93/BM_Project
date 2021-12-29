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

/**
 * @author Aviel
 * This class is for updating/deleting an existing dish from the menu.
 */
public class DeleteOrUpdateDishController extends Controller implements Initializable {
	public static ArrayList<Dish> dishes = new ArrayList<Dish>();

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
	
	private String TypeOfDish;
	private String NameOfDish;
	private DishType dishtype;
	private float PriceOfDish;
	private boolean NameAndTypeCorrect = false;
	private boolean NameAndTypeCorrectToDelete = false;
	private boolean CorrectPrice = false;
	private boolean choiceDetailsIsValid = true;
	private boolean choiceFactorIsValid = true;
	private boolean continuedFlag = true;
	private boolean choiceDetailsWithoutChoiceFactorFlag = true;
	private boolean ingredientsIsValid = true;
	private int placeOfDish;

	@FXML
	void BackToUpdateMenu(ActionEvent event) throws IOException {
		startScreen(event, "UpdateMenuScreen", "Update menu");
	}

	/**
	 * A method to catch the type of dish, then add to ComboBox the appropriate dishes for this type of dish.
	 * @param event
	 */
	@FXML
	void ChoocTypeOfDish(ActionEvent event) {
		btnDish.getItems().clear();
		TypeOfDish = btnDishType.getSelectionModel().getSelectedItem();
		for (int i = 0; i < dishes.size(); i++) {
			if (dishes.get(i) != null) {
				if (DishType.fromTypeToStr(dishes.get(i).getDishType()).equals(TypeOfDish)) {
					btnDish.getItems().add(dishes.get(i).getDishName());
				}
			}
		}
	}

	/**
	 * A method to catch the specific dish, and then inserting the existing fields
	 * @param event
	 */
	@FXML
	void ChooseDish(ActionEvent event) {
		NameOfDish = btnDish.getSelectionModel().getSelectedItem();
		for (int i = 0; i < dishes.size(); i++) {
			if (NameOfDish.equals(dishes.get(i).getDishName().toString())) {
				txtNewPriceDish.setText(String.valueOf(dishes.get(i).getPrice()));
				txtNewChoiceDish.setText(dishes.get(i).getChoiceFactor());
				txtNewChoiceDetailsDish.setText(dishes.get(i).getChoiceDetails());
				txtNewIngredients.setText(dishes.get(i).getIngredients());
				txtNewIngredientsToRemove.setText(dishes.get(i).getExtra());
				placeOfDish = i;
			}
		}
	}

	/**
	 * A method to confirm update of specific dish.
	 * @param event = ActionEvent
	 */
	@FXML
	void ConfirmUpdate(ActionEvent event) throws IOException {
		Dish dish = new Dish(null, null, null, null, null, null, 0, null);
		try {
			dishtype = DishType.toDishType(TypeOfDish);
			if (NameOfDish == null) {
				txtMiniLabel.setText("Name must be selected!");
			} else {
				System.out.println("NameOfdish=" + NameOfDish);
				NameAndTypeCorrect = true;
			}

		} catch (NullPointerException e) {
			txtMiniLabel.setText("Type must be selected!");
		}
		if (NameAndTypeCorrect) {
			if (txtNewPriceDish.getText().isEmpty())
				txtMiniLabel.setText("price must be invailed!");
			else {
				try {
					PriceOfDish = Float.parseFloat(txtNewPriceDish.getText());
					CorrectPrice = true;
				} catch (Exception e) {
					txtMiniLabel.setText("The price must be invalid number");
					e.printStackTrace();
				}
			}
			if (NameAndTypeCorrect && CorrectPrice) {
				String SupplierName = AddDishToMenuController.restName;
				dish = new Dish(NameOfDish, SupplierName, null, null, null, null, PriceOfDish, dishtype);
				dish.setRestCode(LoginScreenController.user.getId());
				if (txtNewChoiceDish.getText().isEmpty()) {
					dish.setChoiceFactor("");
					choiceFactorIsValid = false;
					if (!txtNewChoiceDetailsDish.getText().isEmpty()) {
						choiceDetailsWithoutChoiceFactorFlag = false;
					}
				} else {
					dish.setChoiceFactor(txtNewChoiceDish.getText());
				}
				if (choiceDetailsWithoutChoiceFactorFlag) {
					if (txtNewChoiceDetailsDish.getText().isEmpty()) {
						if (choiceFactorIsValid) {
							continuedFlag = false;
						} else {
							dish.setChoiceDetails("");
						}
					} else {
						String[] DivededUandP = ((String[]) txtNewChoiceDetailsDish.getText().split("/"));
						if (DivededUandP.length == 1) {
							choiceDetailsIsValid = false;
						} else
							dish.setChoiceDetails(txtNewChoiceDetailsDish.getText());
					}
				}
				if (continuedFlag) {
					if (choiceDetailsIsValid) {
						if (choiceDetailsWithoutChoiceFactorFlag) {
							if (txtNewIngredients.getText().isEmpty())
								ingredientsIsValid = false;
							else
								dish.setIngredients(txtNewIngredients.getText());
							if (ingredientsIsValid) {
								if (txtNewIngredientsToRemove.getText().isEmpty())
									dish.setExtra("");
								else
									dish.setExtra(txtNewIngredientsToRemove.getText());

								System.out.println(dish);
								dishes.set(placeOfDish, dish);
								ClientUI.chat.accept(new Message(MessageType.updateDish, dish));
								startScreen(event, "DeleteOrUpdateDish", "Create Menu");
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
						txtMiniLabel.setText("You must separate the choice details by the character - '/'\"");
						choiceDetailsIsValid = true;
					}
				} else {
					txtMiniLabel.setText("If you entered a choice factor, you must also enter choice details");
					continuedFlag = true;
				}
			} else {
				txtMiniLabel.setText("Type must be selected!");
			}
		}
	}

	/**
	 * A method to deleting an existing dish from the menu.
	 * @param event = ActionEvent
	 */
	@FXML
	void DeleteDish(ActionEvent event) throws IOException {
		try {
			dishtype = DishType.toDishType(TypeOfDish);
			if (NameOfDish == null) {
				txtMiniLabel.setText("Name must be selected!");
			} else {
				System.out.println("NameOfdish=" + NameOfDish);
				NameAndTypeCorrectToDelete = true;
			}

		} catch (NullPointerException e) {
			txtMiniLabel.setText("Type must be selected!");
			e.printStackTrace();
		}
		Dish dish = new Dish(NameOfDish, null, null, null, null, null, 0, dishtype);
		if (NameAndTypeCorrectToDelete) {
			dish.setRestCode(LoginScreenController.user.getId());
			System.out.println(dish.toString());
			ClientUI.chat.accept(new Message(MessageType.deleteDish, dish));
			dishes.remove(placeOfDish);
			startScreen(event, "DeleteOrUpdateDish", "Create Menu");
			dish = null;
		}
	}

	@FXML
	void initialize() {
		assert BackImage != null
				: "fx:id=\"BackImage\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert miniLabel != null
				: "fx:id=\"miniLabel\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert txtNewPriceDish != null
				: "fx:id=\"txtNewPriceDish\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
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

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}
}