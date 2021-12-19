package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Employer;
import Entities.Message;
import Entities.MessageType;
import Entities.Supplier;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import main.ClientUI;
import javafx.fxml.Initializable;

public class ConfirmSupplierRegController extends Controller implements Initializable {
	
	/*
	 * Author:Danor
	 * this Class for handle the confirm or refuse registration for the Branch Manager of suppliers.
	 * */

		public static ArrayList<Supplier> Suppliers = new ArrayList<>();
		public static String supplierName;
	
	 	@FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ImageView BackImage;

	    @FXML
	    private ComboBox<String> ListofSupplier;

	    @FXML
	    private Button btnBackToBranchManager;

	    @FXML
	    private Button btnConfirmSupplierRegistartion;

	    @FXML
	    private Button btnRefuseSupplierRegistartion;

	    /*this method for update the DB and return to the branch manager screen.*/
	    @FXML
	    void BackToBranchManagerScreen(ActionEvent event) throws IOException {
	    	ClientUI.chat.accept(new Message(MessageType.Supplier_Update,Suppliers));
	    	startScreen(event, "BranchManagerScreen", "Branch Manager Main");
	    }

	    /*this method is for the Combobox Selection*/
	    @FXML
	    void ChooseSupplier(ActionEvent event) {
	    	supplierName = ListofSupplier.getSelectionModel().getSelectedItem();
	    }

	    /*this method is for the "Confirm" Button for approved Supplier*/
	    @FXML
	    void ConfirmSupplierRegistartion(ActionEvent event) {
	    	ListofSupplier.setDisable(true);
			for (int i = 0; i < Suppliers.size(); i++) {
				if (Suppliers.get(i).getSupplierName().equals(supplierName)) {
					Suppliers.get(i).setSupplierStatus("approved");
				}
			}
			ListofSupplier.getItems().clear();
			loadSupplierstoComboBox(Suppliers);
			ListofSupplier.setPromptText("List of employers awaiting approval");
			ListofSupplier.setDisable(false);
	    }

	    /*this method is for the "Refuse" Button for Not Approved Supplier*/
	    @FXML
	    void RefuseSupplierRegistartion(ActionEvent event) {
	    	ListofSupplier.setDisable(true);
			for (int i = 0; i < Suppliers.size(); i++) {
				if (Suppliers.get(i).getSupplierName().equals(supplierName)) {
					Suppliers.get(i).setSupplierStatus("not approved");
				}
			}
			ListofSupplier.getItems().clear();
			refreshcombobox(Suppliers);
			ListofSupplier.setPromptText("List of employers awaiting approval");
			ListofSupplier.setDisable(false);
	    }
		
		/*this method is for load the not approved and waiting supplier names.*/
		public void loadSupplierstoComboBox(ArrayList<Supplier> Employers) {
			for (Supplier s : Suppliers) {
				if (s.getSupplierStatus().equals("not approved") || s.getSupplierStatus().equals("waiting")) {
					ListofSupplier.getItems().add(s.getSupplierName());
				}
			}
		}
		
		/*this method is for load only the waiting Supplier names.*/
		public void refreshcombobox(ArrayList<Supplier> Suppliers) {
			for (Supplier s : Suppliers) {
				if (s.getSupplierStatus().equals("waiting")) {
					ListofSupplier.getItems().add(s.getSupplierName());
				}
			}
		}

	    @FXML
	    void initialize() {
	        assert BackImage != null : "fx:id=\"BackImage\" was not injected: check your FXML file 'ConfirmSupplierRegistartion.fxml'.";
	        assert ListofSupplier != null : "fx:id=\"ListofSupplier\" was not injected: check your FXML file 'ConfirmSupplierRegistartion.fxml'.";
	        assert btnBackToBranchManager != null : "fx:id=\"btnBackToBranchManager\" was not injected: check your FXML file 'ConfirmSupplierRegistartion.fxml'.";
	        assert btnConfirmSupplierRegistartion != null : "fx:id=\"btnConfirmSupplierRegistartion\" was not injected: check your FXML file 'ConfirmSupplierRegistartion.fxml'.";
	        assert btnRefuseSupplierRegistartion != null : "fx:id=\"btnRefuseSupplierRegistartion\" was not injected: check your FXML file 'ConfirmSupplierRegistartion.fxml'.";
	    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.chat.accept(new Message(MessageType.get_Supplier, null));
		loadSupplierstoComboBox(Suppliers);
	}

}
