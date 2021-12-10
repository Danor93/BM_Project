package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddNewSaladToMenuController extends Controller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView BackImage;

    @FXML
    private Label miniLabel;

    @FXML
    private Button btnPlusType;

    @FXML
    private Button btnPlusSize;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnAddAnotherDish;

    @FXML
    void AddAnotherDish(ActionEvent event) {

    }

    @FXML
    void ConfirmNewSalad(ActionEvent event) {

    }

    @FXML
    void PlusSize(ActionEvent event) {

    }

    @FXML
    void PlusType(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	setImage(BackImage, "background.jpeg");
        assert BackImage != null : "fx:id=\"BackImage\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
        assert miniLabel != null : "fx:id=\"miniLabel\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
        assert btnPlusType != null : "fx:id=\"btnPlusType\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
        assert btnPlusSize != null : "fx:id=\"btnPlusSize\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
        assert btnConfirm != null : "fx:id=\"btnConfirm\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";
        assert btnAddAnotherDish != null : "fx:id=\"btnAddAnotherDish\" was not injected: check your FXML file 'AddNewSaladToMenu.fxml'.";

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}