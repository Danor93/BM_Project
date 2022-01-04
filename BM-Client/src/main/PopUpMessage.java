package main;

import java.util.Optional;

import main.PopUpMessage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*
 * Author:Danor
 * PopUpMessage this class handle all popup messages for the user
 */

public class PopUpMessage {

	
	/**confirmDialog show to user Confirm msg*/
	public static boolean confirmDialog(String msg, Stage stage) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(msg);
        alert.initOwner(stage);
        
		Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
		stage2.getIcons().add(new Image("/icons/warning.png")); // To add an icon
		
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(PopUpMessage.class.getResource("/css/fontSize.css").toExternalForm());
		dialogPane.getStyleClass().add("font18");
		
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        	return true;
        }else {
        	return false;
        }
	}
	
	
	/**
	 * method errorMessage This method will opens new error alert box with the message that
	 * user write
	 * @param msg   - string that user put and will shows in the alert box
	 */
	public static void errorMessage(String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("/icons/close.png")); // To add an icon
		
		
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(PopUpMessage.class.getResource("/css/fontSize.css").toExternalForm());
		dialogPane.getStyleClass().add("font18");

		alert.showAndWait();
	}
	
	/**
	 * method successMessage This method will opens new success alert box with the message that
	 * user write
	 * @param msg   - string that user put and will shows in the alert box
	 */
	@SuppressWarnings("unused")
	public static void successMessage(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(PopUpMessage.class.getResource("/css/fontSize.css").toExternalForm());
		dialogPane.getStyleClass().add("font18");

		alert.showAndWait();
	}
	
	/**
	 * method simulationMessage This method will opens new simulation alert box with the message that
	 * user write
	 * @param msg   - string that user put and will shows in the alert box
	 */
	public static void simulationMessage(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.getDialogPane().setPrefSize(400, 250);
		alert.setTitle("Simulation");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(PopUpMessage.class.getResource("/css/fontSize.css").toExternalForm());
		dialogPane.getStyleClass().add("font18");
		alert.showAndWait();
	}
}
