package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.ClientUI;

public class CustomerScreenController extends Controller implements ControllerInterface {

	public String TempName;
	public static CustomerScreenController cs;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnCreateOrder;

	@FXML
	private Button btnBack;

	@FXML
	private Label welcome;

	@FXML
	private ImageView BackImage;

	@Override
	public void Back(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, null));
		startScreen(event, "LoginScreen", "Login");
	}

	@FXML
	void createOrder(ActionEvent event) throws IOException {
		startScreen(event, "InsertCodeOfW4C", "Create Order");
	}

	public void display(String firstN) {
		welcome.setText("Welcome " + firstN);
	}

	@FXML
	void initialize() {
		setImage(BackImage, "background.jpeg");
		assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
		assert btnCreateOrder != null
				: "fx:id=\"btnCreateOrder\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
		assert welcome != null : "fx:id=\"welcome\" was not injected: check your FXML file 'CustomerScreen.fxml'.";

	}

}
