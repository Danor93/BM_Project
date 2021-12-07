package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerScreenController extends Controller {

	public String TempName; 
	public static CustomerScreenController cs; 
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreateOrder;

    @FXML
    private Button btnBack;
    
    @FXML
    private Label welcome;
    
    @FXML
    private ImageView BackImage;

    
    public void start(Stage primaryStage,Parent root) throws IOException{
		/*FXMLLoader load = new FXMLLoader();
		primaryStage.setTitle("BiteMe");
		Pane root;
		
		try {
			root = load.load(getClass().getResource("/client/controllers/CustomerScreen.fxml").openStream());
			//welcome.setText(LoginScreenController.name);
			Scene home = new Scene(root);
			primaryStage.setScene(home);
			//welcome.setText(LoginScreenController.name);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
    	Scene home = new Scene(root);
		primaryStage.setScene(home);
		primaryStage.show();
		
	}

    @FXML
    void Back(ActionEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		LoginScreenController loginScreenController=new LoginScreenController();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		loginScreenController.start(stage);
    }
    

    @FXML
    void createOrder(ActionEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
    	IdentifyW4cController identifyW4cController=new IdentifyW4cController();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		identifyW4cController.start(stage);
    }

	public void display(String firstN) {
		welcome.setText("Welcome "+firstN);
	}
	
    @FXML
    void initialize() {
    	setImage(BackImage,"background.jpeg");
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert btnCreateOrder != null : "fx:id=\"btnCreateOrder\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert welcome != null : "fx:id=\"welcome\" was not injected: check your FXML file 'CustomerScreen.fxml'.";

    }

}
