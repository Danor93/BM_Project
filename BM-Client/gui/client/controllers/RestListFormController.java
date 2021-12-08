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
import javafx.scene.image.ImageView;
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
    
    @FXML
    private ImageView BackImage;
    
    public static ArrayList<Restaurant> restaurants=new ArrayList<>();

    @FXML
    void backToCity(ActionEvent event) throws IOException {
		startScreen(event,"ChooseRestaurant", "Choose Restaurant");
    }

    @FXML
    void proceedToOrder(ActionEvent event) {

    }
	
    @FXML
    void initialize() {
    	setImage(BackImage,"background.jpeg");
        assert BackImage != null : "fx:id=\"BackImage\" was not injected: check your FXML file 'restListForm.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'restListForm.fxml'.";
        assert colAdd != null : "fx:id=\"colAdd\" was not injected: check your FXML file 'restListForm.fxml'.";
        assert colOpen != null : "fx:id=\"colOpen\" was not injected: check your FXML file 'restListForm.fxml'.";
        assert colRes != null : "fx:id=\"colRes\" was not injected: check your FXML file 'restListForm.fxml'.";
        assert nextbtn != null : "fx:id=\"nextbtn\" was not injected: check your FXML file 'restListForm.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'restListForm.fxml'.";
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Message msg=new Message(MessageType.show_Restaurants,ChooseRestController.cityName);
		
	}

}
