package client.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import main.ChatClient;

public class LoginScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    void ConnectSystem(ActionEvent event) {
		StringBuilder str=new StringBuilder();
		str.append(txtUserName.getText());
		str.append("@");
		str.append(txtPassword.getText());
		Message msg = new Message(MessageType.loginSystem,str.toString());
		ChatClient.chatClient.handleMessageFromClientUI(msg);
		System.out.println("Succsesfuly");
    }

    @FXML
    void getUserName(InputMethodEvent event) {

    }

    @FXML
    void initialize() {
        assert txtUserName != null : "fx:id=\"txtUserName\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'LoginScreen.fxml'.";

    }
}