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

/** This class meant to identify the user as a costumer in the system
 * @author Adi & Talia
 *
 */
public class IdentifyW4cController extends Controller {

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


	/**This method meant to confirm if the user is registered as a costumer
	 * @param event			meant to check the manually entering of W4C
	 */
	@FXML
	void confirm(ActionEvent event) {
		if (w4cManually.getText().equals("Enter W4C code manually") || w4cManually.getText().equals("")) {
			allertLbl.setText("Please enter W4C code or press the QR button");
		}

		else {
			if (!w4cManually.getText().equals(LoginScreenController.user.getW4c())) {
				allertLbl.setText("Wrong W4c, please try again or press the QR button");
			}

			else {
				switchScene(event);
			}
		}

	}

	/**This method meant to get the W4C via QR
	 * @param event		meant to check the W4C with QR
	 */
	@FXML
	void getW4cFromQR(ActionEvent event) {
		w4cManually.setText(LoginScreenController.user.getW4c());
		switchScene(event);
	}

	/** 
	 * @param primaryStage
	 * @throws IOException
	 */
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/fxml/InsertCodeOfW4C.fxml").openStream());
		Scene scene = new Scene(root);
		primaryStage.setTitle("BiteMe");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**   
	 * @param event
	 */
	private void switchScene(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		ChooseRestController chooseRestController = new ChooseRestController();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		try {
			chooseRestController.start(stage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}