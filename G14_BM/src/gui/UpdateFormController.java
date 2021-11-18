package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private Button fUpdateBtn;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblType;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtType;

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void sendDataUpdate(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'UpdateForm.fxml'.";
        assert fUpdateBtn != null : "fx:id=\"fUpdateBtn\" was not injected: check your FXML file 'UpdateForm.fxml'.";
        assert lblAddress != null : "fx:id=\"lblAddress\" was not injected: check your FXML file 'UpdateForm.fxml'.";
        assert lblType != null : "fx:id=\"lblType\" was not injected: check your FXML file 'UpdateForm.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'UpdateForm.fxml'.";
        assert txtType != null : "fx:id=\"txtType\" was not injected: check your FXML file 'UpdateForm.fxml'.";

    }
    
    

}
