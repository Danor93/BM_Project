package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Employer;
import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.ClientUI;

public class HRManagerRegistrationOfEmployersController extends Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private TextField w4cBusiness;

    @FXML
    private TextField companyName;

    @FXML
    private Button btnConfirm;

    @FXML
    private Label mibiLabel;

    @FXML
    void Back(ActionEvent event) throws IOException {
		startScreen(event, "HRManagerScreen", "HR Manager");
    }

    @FXML
    void confirm(ActionEvent event) {
		Employer employer = new Employer(w4cBusiness.getText(), companyName.getText(), "waiting");
		ClientUI.chat.accept(new Message(MessageType.RegistrationOfEmployer, employer));
		mibiLabel.setText("Registration succeeded");
    }

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'HRManagerRegistrationOfEmployers.fxml'.";
        assert w4cBusiness != null : "fx:id=\"w4cBusiness\" was not injected: check your FXML file 'HRManagerRegistrationOfEmployers.fxml'.";
        assert companyName != null : "fx:id=\"companyName\" was not injected: check your FXML file 'HRManagerRegistrationOfEmployers.fxml'.";
        assert btnConfirm != null : "fx:id=\"btnConfirm\" was not injected: check your FXML file 'HRManagerRegistrationOfEmployers.fxml'.";
        assert mibiLabel != null : "fx:id=\"mibiLabel\" was not injected: check your FXML file 'HRManagerRegistrationOfEmployers.fxml'.";
    }
}