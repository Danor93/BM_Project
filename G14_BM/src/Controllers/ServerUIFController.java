package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ServerUIFController {
	public static ServerUIFController serveruifconroller; 
	final public static int DEFAULT_PORT = 5555;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label DBNameLabel;

    @FXML
    private Label DBPasswordLabel;

    @FXML
    private Label DBUserLabel;

    @FXML
    private Button btnClose;

    @FXML
    private Button connectBtn;

    @FXML
    private Button disconnectBtn;

    @FXML
    private Label ipLabel;

    @FXML
    private Label portLabel;

    @FXML
    private TextField txDBPassword;

    @FXML
    private TextField txtDBName;

    @FXML
    private TextField txtDBUser;

    @FXML
    private TextField txtIP;

    @FXML
    private TextField txtPort;

    @FXML
    void ConnectServer(ActionEvent event) {
    	System.out.println("Hello!");
    }
    
    


    @FXML
    void initialize() {
        assert DBNameLabel != null : "fx:id=\"DBNameLabel\" was not injected: check your FXML file 'ServerUIF.fxml'.";
        assert DBPasswordLabel != null : "fx:id=\"DBPasswordLabel\" was not injected: check your FXML file 'ServerUIF.fxml'.";
        assert DBUserLabel != null : "fx:id=\"DBUserLabel\" was not injected: check your FXML file 'ServerUIF.fxml'.";
        assert btnClose != null : "fx:id=\"btnClose\" was not injected: check your FXML file 'ServerUIF.fxml'.";
        assert connectBtn != null : "fx:id=\"connectBtn\" was not injected: check your FXML file 'ServerUIF.fxml'.";
        assert disconnectBtn != null : "fx:id=\"disconnectBtn\" was not injected: check your FXML file 'ServerUIF.fxml'.";
        assert ipLabel != null : "fx:id=\"ipLabel\" was not injected: check your FXML file 'ServerUIF.fxml'.";
        assert portLabel != null : "fx:id=\"portLabel\" was not injected: check your FXML file 'ServerUIF.fxml'.";
        assert txDBPassword != null : "fx:id=\"txDBPassword\" was not injected: check your FXML file 'ServerUIF.fxml'.";
        assert txtDBName != null : "fx:id=\"txtDBName\" was not injected: check your FXML file 'ServerUIF.fxml'.";
        assert txtDBUser != null : "fx:id=\"txtDBUser\" was not injected: check your FXML file 'ServerUIF.fxml'.";
        assert txtIP != null : "fx:id=\"txtIP\" was not injected: check your FXML file 'ServerUIF.fxml'.";
        assert txtPort != null : "fx:id=\"txtPort\" was not injected: check your FXML file 'ServerUIF.fxml'.";

    }

}

