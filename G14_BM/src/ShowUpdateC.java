
import java.io.IOException;

import gui.ShowUpdateController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShowUpdateC extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("BiteMe");
		Parent root = FXMLLoader.load(getClass().getResource("Showupdate.fxml"));
		Scene home = new Scene(root);
		primaryStage.setScene(home);
		primaryStage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}
}
