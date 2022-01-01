package main;

import java.io.IOException;

import controllers.ServerUIFController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ServerUI extends Application {
	public static Stage mainStage;

	@Override
	public void start(Stage primaryStage) throws IOException
	{	
		mainStage=primaryStage;
		ServerUIFController controller = new ServerUIFController();
		controller.start(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
