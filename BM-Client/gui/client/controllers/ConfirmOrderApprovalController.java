package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ConfirmOrderApprovalController extends Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBackToSupplier;

    @FXML
    private Button btnConfirmOrderApproval;

    @FXML
    private Button btnRefuseOrderApproval;

    @FXML
    void BackToSupplierScreen(ActionEvent event) throws IOException {
		startScreen(event,"SupplierScreen","Supplier");
    }

    @FXML
    void ConfirmOrderApproval(ActionEvent event) {

    }

    @FXML
    void RefuseOrderApproval(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnBackToSupplier != null : "fx:id=\"btnBackToSupplier\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
        assert btnConfirmOrderApproval != null : "fx:id=\"btnConfirmOrderApproval\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
        assert btnRefuseOrderApproval != null : "fx:id=\"btnRefuseOrderApproval\" was not injected: check your FXML file 'ConfirmOrderApproval.fxml'.";
    }
}