package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Dish;
import Entities.Employer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ConfirmEmployerRegController extends Controller {
	
	public static ArrayList<Employer> Employers= new ArrayList<Employer>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Employer> ListofEmployers;

    @FXML
    private Button btnBackToBranchManager;

    @FXML
    private Button btnConfirmEmployerRegistartion;

    @FXML
    private Button btnRefuseEmployerRegistartion;

    @FXML
    void BackToBranchManagerScreen(ActionEvent event) throws IOException {
    	startScreen(event, "BranchManagerScreen", "Branch Manager");
    }

    @FXML
    void ConfirmEmployerRegistartion(ActionEvent event) {
    	
    	

    }

    @FXML
    void RefuseEmployerRegistartion(ActionEvent event) {

    }
    
    @FXML
    public void start(Stage stage) {
    	
    }

    @FXML
    void initialize() {
        assert ListofEmployers != null : "fx:id=\"ListofEmployers\" was not injected: check your FXML file 'ConfirmEpmloyerRegistartion.fxml'.";
        assert btnBackToBranchManager != null : "fx:id=\"btnBackToBranchManager\" was not injected: check your FXML file 'ConfirmEpmloyerRegistartion.fxml'.";
        assert btnConfirmEmployerRegistartion != null : "fx:id=\"btnConfirmEmployerRegistartion\" was not injected: check your FXML file 'ConfirmEpmloyerRegistartion.fxml'.";
        assert btnRefuseEmployerRegistartion != null : "fx:id=\"btnRefuseEmployerRegistartion\" was not injected: check your FXML file 'ConfirmEpmloyerRegistartion.fxml'.";

    }

}
