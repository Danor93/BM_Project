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

public class AddNewMainDishToMenuConrtoller extends Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label miniLabel;

    @FXML
    private TextField txtMainDishName;

    @FXML
    private TextField txtPriceOfMainDish;

    @FXML
    private TextField txtInventoryMainDish;

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
    private CheckBox MediumCookLevelMark;

    @FXML
    private CheckBox MediumWellCookLevelMark;

    @FXML
    private CheckBox WellDoneCookLavelMark;

    @FXML
    private CheckBox OneWayMark;

    @FXML
    void Back(ActionEvent event) throws IOException {
		startScreen(event, "CreateMenuScreen", "Create Menu");
    }

    @FXML
    void ConfirmNew(ActionEvent event) {

    }

    @FXML
    void OneSize(ActionEvent event) {
		SmallMark.setSelected(false);
		LargeMark.setSelected(false);
		LargeMark.setSelected(false);
    }

    @FXML
    void OneWay(ActionEvent event) {
    	MediumCookLevelMark.setSelected(false);
    	MediumWellCookLevelMark.setSelected(false);
    	WellDoneCookLavelMark.setSelected(false);
    }

    @FXML
    void initialize() {
        assert miniLabel != null : "fx:id=\"miniLabel\" was not injected: check your FXML file 'AddNewMainDishToMenu.fxml'.";
        assert txtMainDishName != null : "fx:id=\"txtMainDishName\" was not injected: check your FXML file 'AddNewMainDishToMenu.fxml'.";
        assert txtPriceOfMainDish != null : "fx:id=\"txtPriceOfMainDish\" was not injected: check your FXML file 'AddNewMainDishToMenu.fxml'.";
        assert txtInventoryMainDish != null : "fx:id=\"txtInventoryMainDish\" was not injected: check your FXML file 'AddNewMainDishToMenu.fxml'.";
        assert btnConfirm != null : "fx:id=\"btnConfirm\" was not injected: check your FXML file 'AddNewMainDishToMenu.fxml'.";
        assert SmallMark != null : "fx:id=\"SmallMark\" was not injected: check your FXML file 'AddNewMainDishToMenu.fxml'.";
        assert MediumMark != null : "fx:id=\"MediumMark\" was not injected: check your FXML file 'AddNewMainDishToMenu.fxml'.";
        assert LargeMark != null : "fx:id=\"LargeMark\" was not injected: check your FXML file 'AddNewMainDishToMenu.fxml'.";
        assert OneSizeMark != null : "fx:id=\"OneSizeMark\" was not injected: check your FXML file 'AddNewMainDishToMenu.fxml'.";
        assert txtIngredients != null : "fx:id=\"txtIngredients\" was not injected: check your FXML file 'AddNewMainDishToMenu.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'AddNewMainDishToMenu.fxml'.";
        assert txtMiniLabel != null : "fx:id=\"txtMiniLabel\" was not injected: check your FXML file 'AddNewMainDishToMenu.fxml'.";
        assert MediumCookLevelMark != null : "fx:id=\"MediumCookLevelMark\" was not injected: check your FXML file 'AddNewMainDishToMenu.fxml'.";
        assert MediumWellCookLevelMark != null : "fx:id=\"MediumWellCookLevelMark\" was not injected: check your FXML file 'AddNewMainDishToMenu.fxml'.";
        assert WellDoneCookLavelMark != null : "fx:id=\"WellDoneCookLavelMark\" was not injected: check your FXML file 'AddNewMainDishToMenu.fxml'.";
        assert OneWayMark != null : "fx:id=\"OneWayMark\" was not injected: check your FXML file 'AddNewMainDishToMenu.fxml'.";
    }
}