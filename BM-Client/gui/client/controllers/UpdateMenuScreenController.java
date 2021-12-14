package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class UpdateMenuScreenController extends Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView BackImage;

    @FXML
    private Button btnSalad;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnMainDishes;

    @FXML
    private Button btnDessert;

    @FXML
    private Button btnDrinks;

    @FXML
    private Button btnBack;

    @FXML
    private Label miniLabel;

    @FXML
    void Back(ActionEvent event) throws IOException{
		startScreen(event,"SupplierScreen","Supplier");
    }

    @FXML
    void openDesert(MouseEvent event) {

    }

    @FXML
    void openDrinks(MouseEvent event) {

    }

    @FXML
    void openMainDishes(MouseEvent event) {

    }

    @FXML
    void openSalads(MouseEvent event) {

    }

    @FXML
    void openStarts(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert BackImage != null : "fx:id=\"BackImage\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
        assert btnSalad != null : "fx:id=\"btnSalad\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
        assert btnMainDishes != null : "fx:id=\"btnMainDishes\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
        assert btnDessert != null : "fx:id=\"btnDessert\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
        assert btnDrinks != null : "fx:id=\"btnDrinks\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";
        assert miniLabel != null : "fx:id=\"miniLabel\" was not injected: check your FXML file 'UpdateMenuScreen.fxml'.";

    }
}