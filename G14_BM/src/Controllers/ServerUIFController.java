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
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="DBNameLabel"
	private Label DBNameLabel; // Value injected by FXMLLoader

	@FXML // fx:id="DBPasswordLabel"
	private Label DBPasswordLabel; // Value injected by FXMLLoader

	@FXML // fx:id="DBUserLabel"
	private Label DBUserLabel; // Value injected by FXMLLoader

	@FXML // fx:id="Statuslbl"
	private Label Statuslbl; // Value injected by FXMLLoader

	public Label getLabelStatusServer() {
		return Statuslbl;
	}

	@FXML // fx:id="btnClose"
	private Button btnClose; // Value injected by FXMLLoader

	@FXML // fx:id="connectBtn"
	private Button connectBtn; // Value injected by FXMLLoader

	@FXML // fx:id="disconnectBtn"
	private Button disconnectBtn; // Value injected by FXMLLoader

	@FXML // fx:id="ipLabel"
	private Label ipLabel; // Value injected by FXMLLoader

	@FXML // fx:id="portLabel"
	private Label portLabel; // Value injected by FXMLLoader

	@FXML // fx:id="txDBPassword"
	private TextField txDBPassword; // Value injected by FXMLLoader

	@FXML // fx:id="txtDBName"
	private TextField txtDBName; // Value injected by FXMLLoader

	@FXML // fx:id="txtDBUser"
	private TextField txtDBUser; // Value injected by FXMLLoader

	@FXML // fx:id="txtIP"
	private TextField txtIP; // Value injected by FXMLLoader

	@FXML // fx:id="txtPort"
	private TextField txtPort; // Value injected by FXMLLoader

	@FXML
	void ConnectServer(ActionEvent event) {

		// ServerConnection.startServer(null, this);
		Statuslbl.setText("ON");
		Statuslbl.setStyle("-fx-text-fill: green");
		// addToTextArea("Server listening for connections on port: " + DEFAULT_PORT);

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert DBNameLabel != null : "fx:id=\"DBNameLabel\" was not injected: check your FXML file 'ServerUIF.fxml'.";
		assert DBPasswordLabel != null
				: "fx:id=\"DBPasswordLabel\" was not injected: check your FXML file 'ServerUIF.fxml'.";
		assert DBUserLabel != null : "fx:id=\"DBUserLabel\" was not injected: check your FXML file 'ServerUIF.fxml'.";
		assert Statuslbl != null : "fx:id=\"Statuslbl\" was not injected: check your FXML file 'ServerUIF.fxml'.";
		assert btnClose != null : "fx:id=\"btnClose\" was not injected: check your FXML file 'ServerUIF.fxml'.";
		assert connectBtn != null : "fx:id=\"connectBtn\" was not injected: check your FXML file 'ServerUIF.fxml'.";
		assert disconnectBtn != null
				: "fx:id=\"disconnectBtn\" was not injected: check your FXML file 'ServerUIF.fxml'.";
		assert ipLabel != null : "fx:id=\"ipLabel\" was not injected: check your FXML file 'ServerUIF.fxml'.";
		assert portLabel != null : "fx:id=\"portLabel\" was not injected: check your FXML file 'ServerUIF.fxml'.";
		assert txDBPassword != null : "fx:id=\"txDBPassword\" was not injected: check your FXML file 'ServerUIF.fxml'.";
		assert txtDBName != null : "fx:id=\"txtDBName\" was not injected: check your FXML file 'ServerUIF.fxml'.";
		assert txtDBUser != null : "fx:id=\"txtDBUser\" was not injected: check your FXML file 'ServerUIF.fxml'.";
		assert txtIP != null : "fx:id=\"txtIP\" was not injected: check your FXML file 'ServerUIF.fxml'.";
		assert txtPort != null : "fx:id=\"txtPort\" was not injected: check your FXML file 'ServerUIF.fxml'.";

	}

}
