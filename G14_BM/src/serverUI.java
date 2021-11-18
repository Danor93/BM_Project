
import java.io.IOException;

import Controllers.ServerUIFController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class serverUI extends Application {
	
	//did you see this?

	@Override
	public void start(Stage primaryStage) throws IOException
	{
		ServerUIFController controller;
		FXMLLoader load= new FXMLLoader();
		primaryStage.setTitle("BiteMe");
		Parent root=FXMLLoader.load(getClass().getResource("ServerUIF.fxml"));
		controller =load.getController();
		Scene home=new Scene(root);
		primaryStage.setScene(home);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
