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
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Controller {
	/** setImage this method setImage for give imageview */
	public void setImage(ImageView img, String ImageName) {
		Image image;
		image = new Image(getClass().getResourceAsStream("/Image/" + ImageName));
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
}