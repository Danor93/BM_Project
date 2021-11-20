package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UpdateFormController {

	ShowUpdateController showupdatecontroller;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button backBtn;

	@FXML
    private Button UpdateBtn;

	@FXML
	private Label lblAddress;

	@FXML
	private Label lblType;

	@FXML
	private TextField txtAddress;

	@FXML
	private TextField txtType;

	@FXML
	void back(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Pane root = loader.load(getClass().getResource("/gui/Showupdate.fxml").openStream());
		// UpdateFormController updateFormController = loader.getController();
		// studentFormController.loadStudent(ChatClient.s1);
		Scene scene = new Scene(root);
		// scene.getStylesheets().add(getClass().getResource("/gui/StudentForm.css").toExternalForm());
		primaryStage.setTitle("Update window");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("/gui/ClientIcon.png"));
		primaryStage.show();
	}

	@FXML
	void sendDataUpdate(ActionEvent event) {

	}

	@FXML
	void initialize() {
		assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'UpdateForm.fxml'.";
		assert UpdateBtn != null : "fx:id=\"fUpdateBtn\" was not injected: check your FXML file 'UpdateForm.fxml'.";
		assert lblAddress != null : "fx:id=\"lblAddress\" was not injected: check your FXML file 'UpdateForm.fxml'.";
		assert lblType != null : "fx:id=\"lblType\" was not injected: check your FXML file 'UpdateForm.fxml'.";
		assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'UpdateForm.fxml'.";
		assert txtType != null : "fx:id=\"txtType\" was not injected: check your FXML file 'UpdateForm.fxml'.";
	}

}
