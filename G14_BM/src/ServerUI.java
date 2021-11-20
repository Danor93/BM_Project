
import java.io.IOException;

import gui.ServerUIFController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import gui.*;


public class ServerUI extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException
	{	
		ServerUIFController controller = new ServerUIFController();
		controller.start(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
