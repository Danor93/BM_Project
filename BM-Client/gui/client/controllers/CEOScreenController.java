package client.controllers;

import java.io.IOException;
import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import main.ClientUI;

public class CEOScreenController extends Controller{

    @FXML
    private ImageView BackImage;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnConfirmEmployerRegistration;

    @FXML
    private Button btnConfirmSupplierRegistration;

    @FXML
    private Button btnOpenNewAccount;

    @FXML
    private Button btnUploadPDF;

    @FXML
    private Button btnViewBranchsReports;

    @FXML
    private Button quater;

    @FXML
    private Text WelcomeLabel;

    @FXML
    void Back(ActionEvent event) throws IOException {
    	ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
    	startScreen(event, "LoginScreen","Login Screen");
    }

    @FXML
    void ConfirmEmployerReg(ActionEvent event) {

    }

    @FXML
    void ConfirmSupplierReg(ActionEvent event) {

    }

    @FXML
    void OpenNewAccount(ActionEvent event) {

    }

    @FXML
    void UploadPDF(ActionEvent event) {

    }

    @FXML
    void open(ActionEvent event) throws IOException {
		start(event, "CEOChooseQReports", "Show quaterly reports","");


    }

	@Override
	public void display(String string) {
		WelcomeLabel.setText("Welcome, " + LoginScreenController.user.getFirstN());
	}

}
