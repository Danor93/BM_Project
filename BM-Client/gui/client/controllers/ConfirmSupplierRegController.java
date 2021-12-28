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
	    private Button BackBtn;

	    @FXML
	    private Button btnConfirmSupplierRegistartion;

	    @FXML
	    private Button btnRefuseSupplierRegistartion;

	    /*this method for update the DB and return to the branch manager screen.*/
	    @FXML
	    void Back(ActionEvent event) throws IOException {
	    	ClientUI.chat.accept(new Message(MessageType.Supplier_Update,Suppliers));
	    	if(LoginScreenController.user.getRole().equals("CEO")) {
				startScreen(event, "CEOScreen", "CEO");
			}
			if(LoginScreenController.user.getRole().equals("BranchManager")) {
				startScreen(event, "BranchManagerScreen", "Branch Manager");
			}
	    }

	    /*this method is for the Combobox Selection*/
	    @FXML
	    void ChooseSupplier(ActionEvent event) {
	    	supplierName = ListofSupplier.getSelectionModel().getSelectedItem();
	    	btnConfirmSupplierRegistartion.setDisable(false);
			btnRefuseSupplierRegistartion.setDisable(false);
	    }

	    /*this method is for the "Confirm" Button for approved Supplier*/
	    @FXML
	    void ConfirmSupplierRegistartion(ActionEvent event) {
	    	ListofSupplier.setDisable(true);
			for (int i = 0; i < Suppliers.size(); i++) {
				if (Suppliers.get(i).getSupplierName().equals(supplierName)) {
					Suppliers.get(i).setSupplierStatus("Approved");
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
					Suppliers.get(i).setSupplierStatus("Not approved");
				}
			}
			ListofSupplier.getItems().clear();
			refreshcombobox(Suppliers);
			ListofSupplier.setPromptText("List of employers awaiting approval");
			ListofSupplier.setDisable(false);
	    }
		
		/*this method is for load the not approved and waiting supplier names.*/
		public void loadSupplierstoComboBox(ArrayList<Supplier> Suppliers) {
			for (Supplier s : Suppliers) {
				if (s.getSupplierStatus().equals("Not approved") || s.getSupplierStatus().equals("Waiting")) {
					ListofSupplier.getItems().add(s.getSupplierName());
				}
			}
		}
		
		/*this method is for load only the waiting Supplier names.*/
		public void refreshcombobox(ArrayList<Supplier> Suppliers) {
			for (Supplier s : Suppliers) {
				if (s.getSupplierStatus().equals("Waiting")) {
					ListofSupplier.getItems().add(s.getSupplierName());
				}
			}
		}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String Branch = LoginScreenController.user.getHomeBranch().toString();
		ClientUI.chat.accept(new Message(MessageType.get_Supplier,Branch));
		loadSupplierstoComboBox(Suppliers);
		if(LoginScreenController.user.getRole().equals("CEO")) {
			BackBtn.setText("Back to CEO Panel");
		}
		if(LoginScreenController.user.getRole().equals("BranchManager")) {
			BackBtn.setText("Back to Branch Manager Panel");
		}
		btnConfirmSupplierRegistartion.setDisable(true);
		btnRefuseSupplierRegistartion.setDisable(true);
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}

}
