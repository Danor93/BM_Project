package client.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

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
    void Back(ActionEvent event) {

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
		// TODO Auto-generated method stub
		
	}

}
