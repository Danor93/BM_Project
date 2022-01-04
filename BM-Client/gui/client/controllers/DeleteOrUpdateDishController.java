package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.text.Text;
import main.ClientUI;

/**
 * @author Aviel This class is for updating/deleting an existing dish from the
 *         menu.
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
	private Text userName;

	@FXML
	private ImageView homePage;

	@FXML
	private Button logout;

	@FXML
	private Button back;

	private String TypeOfDish;
	private String NameOfDish;
	private DishType dishtype;
	private float PriceOfDish;
	private int placeOfDish;

	/**
	 * This method meant to get back to supplier page
	 * 
	 * @param event = ActionEvent
	 */
	@FXML
	void backToHome(MouseEvent event) throws IOException {
		start(event, "SupplierScreen", "Supplier page", LoginScreenController.user.getFirstN());
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
	void backToDishes(ActionEvent event) throws IOException {
		start(event, "UpdateMenuScreen", "Update Menu", LoginScreenController.user.getFirstN());
	}

	/**
	 * A method to catch the type of dish, then add to ComboBox the appropriate
	 * dishes for this type of dish.
	 * 
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
	 * 
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
	 * 
	 * @param event = ActionEvent
	 */
	@FXML
	void ConfirmUpdate(ActionEvent event) throws IOException {
		boolean NameAndTypeCorrect = false;
		boolean CorrectPrice = false;
		boolean choiceDetailsIsValid = true;
		boolean choiceFactorIsValid = true;
		boolean continuedFlag = true;
		boolean choiceDetailsWithoutChoiceFactorFlag = true;
		boolean ingredientsIsValid = true;
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
				txtMiniLabel.setText("price must be valid!");
			else {
				try {
					PriceOfDish = Float.parseFloat(txtNewPriceDish.getText());
					CorrectPrice = true;
				} catch (Exception e) {
					txtMiniLabel.setText("The price must be valid number");
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
								start(event, "DeleteOrUpdateDish", "Create Menu", "");
							} else {
								txtMiniLabel.setText("You must enter the ingredients of the dish");
								ingredientsIsValid = true;
							}
						} else {
							txtMiniLabel.setText("If you entered a choice details, you must also enter choice factor");
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
				if (!CorrectPrice)
					txtMiniLabel.setText("price must be valid!");
				else
					txtMiniLabel.setText("Type must be selected!");
			}
		}
	}

	/**
	 * A method to deleting an existing dish from the menu.
	 * 
	 * @param event = ActionEvent
	 */
	@FXML
	void DeleteDish(ActionEvent event) throws IOException {
		boolean NameAndTypeCorrectToDelete = false;
		try {
			dishtype = DishType.toDishType(TypeOfDish);
			if (NameOfDish == null) {
				txtMiniLabel.setText("Name must be selected!");
			} else {
				NameAndTypeCorrectToDelete = true;
			}
		} catch (NullPointerException e) {
			txtMiniLabel.setText("Type must be selected!");
			// e.printStackTrace();
		}
		Dish dish = new Dish(NameOfDish, null, null, null, null, null, 0, dishtype);
		if (NameAndTypeCorrectToDelete) {
			dish.setRestCode(LoginScreenController.user.getId());
			ClientUI.chat.accept(new Message(MessageType.deleteDish, dish));
			dishes.remove(placeOfDish);
			start(event, "DeleteOrUpdateDish", "Create Menu", "");
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
		assert userName != null
				: "fx:id=\"userName\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert homePage != null
				: "fx:id=\"homePage\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
		assert logout != null : "fx:id=\"logout\" was not injected: check your FXML file 'DeleteOrUpdateDish.fxml'.";
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnDishType.getItems().addAll("Salad", "Starter", "Main dish", "Dessert", "Drink");
	}

	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN());
	}
}