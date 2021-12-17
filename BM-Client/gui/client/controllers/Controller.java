package client.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class  Controller  {
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
		primaryStage.show();
	}
	
	
	public void start(ActionEvent event,String fxmlName,String title,String toDisplay) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/"+fxmlName+".fxml"));
		Parent root=load.load();
		Controller aFrame = load.getController();
		//aFrame.display(toDisplay);
		Scene scene = new Scene(root);			
		stage.setTitle("BiteMe" + " " + title);
		stage.setScene(scene);
		stage.show();		
	}
	

	//public abstract void display(String string);
	

}