package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import main.ClientUI;

public class HRManagerScreenController extends Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView BackImage;

    @FXML
    private Button btnRegistrationOfEmployers;

    @FXML
    private Button btnConfirmationOfOpeningABusinessAccount;

    @FXML
    private Button btnBack;

    @FXML
    private Label ExistLbl;

    @FXML
    void Back(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, null));
		startScreen(event, "LoginScreen", "Login");
    }

    @FXML
    void ConfirmationOfOpeningABusinessAccount(ActionEvent event) throws IOException {
		startScreen(event, "HRManagerConfirmationOfOpeningABusinessAccount", "ConfirmationOfOpeningABusinessAccount");
    }

    @FXML
    void RegistrationOfEmployers(ActionEvent event) throws IOException {
		startScreen(event, "HRManagerRegistrationOfEmployers", "RegistrationOfEmployers");
    }

    @FXML
    void initialize() {
        assert BackImage != null : "fx:id=\"BackImage\" was not injected: check your FXML file 'HRManagerScreen.fxml'.";
        assert btnRegistrationOfEmployers != null : "fx:id=\"btnRegistrationOfEmployers\" was not injected: check your FXML file 'HRManagerScreen.fxml'.";
        assert btnConfirmationOfOpeningABusinessAccount != null : "fx:id=\"btnConfirmationOfOpeningABusinessAccount\" was not injected: check your FXML file 'HRManagerScreen.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'HRManagerScreen.fxml'.";
        assert ExistLbl != null : "fx:id=\"ExistLbl\" was not injected: check your FXML file 'HRManagerScreen.fxml'.";
    }
}