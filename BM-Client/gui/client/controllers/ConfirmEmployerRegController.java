package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Dish;
import Entities.Employer;
import Entities.Message;
import Entities.MessageType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientUI;

public class ConfirmEmployerRegController extends Controller {
	
	
	public static ArrayList<Employer> Employers= new ArrayList<Employer>();


	  @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ImageView BackImage;

	    @FXML
	    private ComboBox<String> ListofEmployers;

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
    public void start(Stage stage) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.get_Employer,null));
		loadEmployerstoComboBox(Employers);
    }
    
    public void loadEmployerstoComboBox(ArrayList<Employer> Employers) {
    	for(Employer e: Employers) {
    		if(e.getCompanyStatus()==false) {
    			ListofEmployers.getItems().add(e.getCompanyName());
    		}
    	}
    }

    @FXML
    void initialize() {
        assert BackImage != null : "fx:id=\"BackImage\" was not injected: check your FXML file 'ConfirmEmployerRegistartion.fxml'.";
        assert ListofEmployers != null : "fx:id=\"ListofEmployers\" was not injected: check your FXML file 'ConfirmEmployerRegistartion.fxml'.";
        assert btnBackToBranchManager != null : "fx:id=\"btnBackToBranchManager\" was not injected: check your FXML file 'ConfirmEmployerRegistartion.fxml'.";
        assert btnConfirmEmployerRegistartion != null : "fx:id=\"btnConfirmEmployerRegistartion\" was not injected: check your FXML file 'ConfirmEmployerRegistartion.fxml'.";
        assert btnRefuseEmployerRegistartion != null : "fx:id=\"btnRefuseEmployerRegistartion\" was not injected: check your FXML file 'ConfirmEmployerRegistartion.fxml'.";
        ListofEmployers.setDisable(false);

    }
}
