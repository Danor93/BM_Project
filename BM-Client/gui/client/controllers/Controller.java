package client.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller  {
	/** setImage this method setImage for give imageview */
	public void setImage(ImageView img, String ImageName) {
		Image image;
		image = new Image(getClass().getResourceAsStream("/Image/" + ImageName));
		img.setImage(image);
	}
	
	public void startScreen(ActionEvent event,String fxmlName,String PanelName) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		Pane root = loader.load(getClass().getResource("/fxml/" + fxmlName + ".fxml").openStream());
		Scene scene = new Scene(root);			
		primaryStage.setTitle("BiteMe" + " " + PanelName + " " + "Panel");
		primaryStage.setScene(scene);
		//primaryStage.getIcons().add(new Image("/gui/ClientIcon.png"));
		primaryStage.show();
	}
}