package client.controllers;

import java.io.IOException;

import Entities.Message;
import Entities.MessageType;
import main.ClientUI;
import main.PopUpMessage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public abstract class  Controller  {

	/** setImage this method setImage for give imageview */
	public void setImage(ImageView img, String ImageName) {
		Image image;
		image = new Image(getClass().getResource(ImageName).toExternalForm());
		img.setImage(image);
	}

	public void startScreen(ActionEvent event, String fxmlName, String PanelName) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader();
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Pane root = loader.load(getClass().getResource("/fxml/" + fxmlName + ".fxml").openStream());
			Scene scene = new Scene(root);
			primaryStage.setTitle("BiteMe" + " " + PanelName + " " + "Panel");
			primaryStage.setScene(scene);
			// primaryStage.getIcons().add(new Image("/gui/ClientIcon.png"));
			primaryStage.show();
			if (!(fxmlName.equals("LoginScreen"))) {
				primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

					@Override
					public void handle(WindowEvent event) {
						event.consume();
						boolean ans = PopUpMessage.confirmDialog("Do you want to logout and exit from system?",
								primaryStage);
						if (ans) {
							ClientUI.chat.accept(new Message(MessageType.Disconected, null));
							primaryStage.close();
						}
					}

				});
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void start(ActionEvent event,String fxmlName,String title,String toDisplay) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/"+fxmlName+".fxml"));
		Parent root=load.load();
		Controller aFrame = load.getController();
		aFrame.display(toDisplay);
		Scene scene = new Scene(root);			
		primaryStage.setTitle("BiteMe" + " " + title);
		primaryStage.setScene(scene);
		primaryStage.show();	
		
		if (!(fxmlName.equals("LoginScreen"))) {
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					event.consume();
					boolean ans = PopUpMessage.confirmDialog("Do you want to logout and exit from system?",
							primaryStage);
					if (ans) {
						ClientUI.chat.accept(new Message(MessageType.Disconected, null));
						primaryStage.close();
					}
				}

			});
		}
	}
	
	public void start(MouseEvent event,String fxmlName,String title,String toDisplay) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/"+fxmlName+".fxml"));
		Parent root=load.load();
		Controller aFrame = load.getController();
		aFrame.display(toDisplay);
		Scene scene = new Scene(root);			
		primaryStage.setTitle("BiteMe" + " " + title);
		primaryStage.setScene(scene);
		primaryStage.show();	
		
		if (!(fxmlName.equals("LoginScreen"))) {
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					event.consume();
					boolean ans = PopUpMessage.confirmDialog("Do you want to logout and exit from system?",
							primaryStage);
					if (ans) {
						ClientUI.chat.accept(new Message(MessageType.Disconected, null));
						primaryStage.close();
					}
				}

			});
		}
	}

	
	public abstract void display(String string);
}