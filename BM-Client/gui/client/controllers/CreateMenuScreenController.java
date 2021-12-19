package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Dish;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CreateMenuScreenController extends Controller implements ControllerInterface{
	public static ArrayList<Dish> dishes = new ArrayList<Dish>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Pane SaladsPane;

    @FXML
    private Pane MainDishesPain;
    
    @FXML
    private StackPane stackpane;
    
    @FXML
    private ImageView BackImage;    
    
    void HandleClicks(ActionEvent event) {
    	if(event.getSource() == btnSalad) {
    		miniLabel.setText("Salads");
    		SaladsPane.toFront();
    	}
    	else if (event.getSource() == btnMainDishes) {
    		miniLabel.setText("Main dishes");
    		MainDishesPain.toFront();
    	}
    }
    
    @FXML
    void openDesert(ActionEvent event) throws IOException {
    	startScreen(event,"AddDishToMenu","Add New Dish");
    }

    @FXML
    void openDrinks(ActionEvent event) throws IOException {
    	startScreen(event,"AddDishToMenu","Add New Dish");
    }

    @FXML
    void openMainDishes(ActionEvent event) throws IOException {
    	startScreen(event,"AddDishToMenu","Add New Dish");
    }

    @FXML
    void openSalads(ActionEvent event) throws IOException {
    	startScreen(event,"AddDishToMenu","Add New Dish");
    }

    @FXML
    void openStarts(ActionEvent event) throws IOException {
    	startScreen(event,"AddDishToMenu","Add New Dish");
    }

	@Override
	public void Back(ActionEvent event) throws IOException {
		startScreen(event,"SupplierScreen","Supplier");
	}

    @FXML
    void initialize() {
    	//setImage(BackImage, "background.png");
        assert btnSalad != null : "fx:id=\"btnSalad\" was not injected: check your FXML file 'CreateMenuScreen.fxml'.";
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'CreateMenuScreen.fxml'.";
        assert btnMainDishes != null : "fx:id=\"btnMainDishes\" was not injected: check your FXML file 'CreateMenuScreen.fxml'.";
        assert btnDessert != null : "fx:id=\"btnDessert\" was not injected: check your FXML file 'CreateMenuScreen.fxml'.";
        assert btnDrinks != null : "fx:id=\"btnDrinks\" was not injected: check your FXML file 'CreateMenuScreen.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'CreateMenuScreen.fxml'.";
        assert miniLabel != null : "fx:id=\"miniLabel\" was not injected: check your FXML file 'CreateMenuScreen.fxml'.";
        assert SaladsPane != null : "fx:id=\"SaladsPane\" was not injected: check your FXML file 'CreateMenuScreen.fxml'.";
        assert MainDishesPain != null : "fx:id=\"MainDishesPain\" was not injected: check your FXML file 'CreateMenuScreen.fxml'.";
    }
}