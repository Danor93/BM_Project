package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddNewDrinkToMenuController extends Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label miniLabel;

    @FXML
    private TextField txtDrinkName;

    @FXML
    private TextField txtPriceOfDrink;

    @FXML
    private TextField txtInventoryDrink;

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
    private Label txtMiniLabel;

    @FXML
    void Back(ActionEvent event) throws IOException {
		startScreen(event, "CreateMenuScreen", "Create Menu");
    }

    @FXML
    void ConfirmNewDrink(ActionEvent event) {

    }

    @FXML
    void OneSize(ActionEvent event) {
		SmallMark.setSelected(false);
		LargeMark.setSelected(false);
		LargeMark.setSelected(false);
    }

    @FXML
    void initialize() {
        assert miniLabel != null : "fx:id=\"miniLabel\" was not injected: check your FXML file 'AddNewDrinkToMenu.fxml'.";
        assert txtDrinkName != null : "fx:id=\"txtDrinkName\" was not injected: check your FXML file 'AddNewDrinkToMenu.fxml'.";
        assert txtPriceOfDrink != null : "fx:id=\"txtPriceOfDrink\" was not injected: check your FXML file 'AddNewDrinkToMenu.fxml'.";
        assert txtInventoryDrink != null : "fx:id=\"txtInventoryDrink\" was not injected: check your FXML file 'AddNewDrinkToMenu.fxml'.";
        assert btnConfirm != null : "fx:id=\"btnConfirm\" was not injected: check your FXML file 'AddNewDrinkToMenu.fxml'.";
        assert SmallMark != null : "fx:id=\"SmallMark\" was not injected: check your FXML file 'AddNewDrinkToMenu.fxml'.";
        assert MediumMark != null : "fx:id=\"MediumMark\" was not injected: check your FXML file 'AddNewDrinkToMenu.fxml'.";
        assert LargeMark != null : "fx:id=\"LargeMark\" was not injected: check your FXML file 'AddNewDrinkToMenu.fxml'.";
        assert OneSizeMark != null : "fx:id=\"OneSizeMark\" was not injected: check your FXML file 'AddNewDrinkToMenu.fxml'.";
        assert txtIngredients != null : "fx:id=\"txtIngredients\" was not injected: check your FXML file 'AddNewDrinkToMenu.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'AddNewDrinkToMenu.fxml'.";
        assert txtMiniLabel != null : "fx:id=\"txtMiniLabel\" was not injected: check your FXML file 'AddNewDrinkToMenu.fxml'.";
    }

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}
}