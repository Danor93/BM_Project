package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class RestListFormController extends Controller {

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

    @FXML
    void backToCity(ActionEvent event) {

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

}
