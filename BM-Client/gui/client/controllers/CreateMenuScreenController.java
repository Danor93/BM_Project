package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

public class CreateMenuScreenController extends Controller {

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
    
    @FXML
    void BackToSupplierPage(ActionEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/SupplierScreen.fxml"));
		Scene scene = new Scene(root);		
		primaryStage.setTitle("BiteMe Supplier Panel");
		primaryStage.setScene(scene);		
		primaryStage.show();
    }

    @FXML
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
    	setImage(BackImage, "background.jpeg");
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

	public void start(Stage primaryStage) throws IOException {
		FXMLLoader load = new FXMLLoader();
		primaryStage.setTitle("BiteMe Create Menu Panel");
		Pane root = load.load(getClass().getResource("/fxml/CreateMenuScreen.fxml").openStream());
		Scene home = new Scene(root);
		primaryStage.setScene(home);
		primaryStage.show();	
	}
}