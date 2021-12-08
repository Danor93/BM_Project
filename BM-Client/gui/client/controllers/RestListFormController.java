package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class RestListFormController extends Controller implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> colAdd;

    @FXML
    private TableColumn<?, ?> colOpen;

    @FXML
    private TableColumn<?, ?> colRes;

    @FXML
    private Button nextbtn;

    @FXML
    private TableView<?> table;
    
    public static ArrayList<Restaurant> restaurants=new ArrayList<>();

    @FXML
    void backToCity(ActionEvent event) throws IOException {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/ChooseRestaurant.fxml"));
		Parent root=load.load();
		ChooseRestController aFrame = load.getController();
		aFrame.start(primaryStage);

    }

    @FXML
    void proceedToOrder(ActionEvent event) {

    }

	public void start(Stage stage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Message msg=new Message(MessageType.show_Restaurants,ChooseRestController.cityName);
		
	}

}
