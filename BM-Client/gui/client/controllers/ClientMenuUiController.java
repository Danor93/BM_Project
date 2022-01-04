package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.ClientController;
import main.ClientUI;
import main.PopUpMessage;

/** 
 * @author Aviel 
 * @author Sahar 
 * This gui class is for server connecting
 */
public class ClientMenuUiController extends Controller {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button ConnectBtn;

	@FXML
	private TextField ipTxt;

	@FXML
	private ImageView LogoImage;

	/**
	 * This method connecting the server according to the localhost and port 5555
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void ConnectToServer(ActionEvent event) throws IOException {
		String ip;
		if (ipTxt.getText().isEmpty()) {
			PopUpMessage.errorMessage("you must enter an ip!");
		} else {
			ip = ipTxt.getText().toString();
			ClientUI.chat = new ClientController(ip, 5555);
			start(event, "BeforLogin", "Description", "");
		}
	}

	/**
	 * This method is to open fxml screen.
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("BiteMe Main Client Panel");
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/ClientMainUi.fxml"));
		Scene home = new Scene(root);
		primaryStage.setScene(home);
		primaryStage.show();
	}

	@Override
	public void display(String string) {

	}
}