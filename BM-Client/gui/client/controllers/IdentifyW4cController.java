package client.controllers;

import java.io.IOException;

import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientUI;

/**
 * This class meant to identify the user as a costumer in the system
 * 
 * @author Adi & Talia
 *
 */

public class IdentifyW4cController extends Controller implements ControllerInterface {

	@FXML
	private Button QR;

	@FXML
	private Button confirmBtn;

	@FXML
	private TextField w4cManually;

	@FXML
	private Label allertLbl;

	@FXML
	private Button BackBtn;

	@FXML
	private ImageView BackImage;

	/**
	 * This method meant to confirm if the user is registered as a costumer
	 * 
	 * @param event meant to check the manually entering of W4C
	 */
	@FXML
	void confirm(ActionEvent event) throws IOException {
		if (w4cManually.getText().equals("Enter W4C code manually") || w4cManually.getText().equals("")) {
			allertLbl.setText("Please enter W4C code or press the QR button");
		}

		else {
		/*	if (!w4cManually.getText().equals(LoginScreenController())) {
				allertLbl.setText("Wrong W4c, please try again or press the QR button");
			}

			else {
				switchScene(event);
			}*/
		}

	}

	/**
	 * This method meant to get the W4C via QR
	 * 
	 * @param event meant to check the W4C with QR
	 */
	@FXML
	/**void getW4cFromQR(ActionEvent event) throws IOException {
		w4cManually.setText(LoginScreenController.user.getW4c());
		switchScene(event);
	}**/

	private void switchScene(ActionEvent event) throws IOException {
		startScreen(event, "ChooseRestaurant", "Choose Restaurant");
	}

	@Override
	public void Back(ActionEvent event) throws IOException {
		startScreen(event, "CustomerScreen", "Customer");
	}

}