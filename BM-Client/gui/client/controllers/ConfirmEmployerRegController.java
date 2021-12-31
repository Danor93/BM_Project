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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.ClientUI;
import javafx.scene.text.Text;

public class ConfirmEmployerRegController extends Controller implements Initializable {

	/*
	 * Author:Danor 
	 * this Class for handle the confirm or refuse registration for the Branch Manager of Employers.
	 */

	public static ArrayList<Employer> Employers = new ArrayList<Employer>();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> ListofEmployers;

	@FXML
	private Button BackBtn;

	@FXML
	private Button btnConfirmEmployerRegistartion;

	@FXML
	private Button btnRefuseEmployerRegistartion;
	
    @FXML
    private ImageView homePage;

    @FXML
    private Button logout;

    @FXML
    private Text userName;

	public static String companyName;
	
	@FXML
	void backToHome(MouseEvent event) throws IOException {
		start(event, "BranchManagerScreen", "Branch Manager", LoginScreenController.user.getUserName());
	}

	@FXML
	void logout(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event,"LoginScreen", "Login Screen","");
	}

	/* this method is for the Combobox Selection */
	@FXML
	void ChooseCompany(ActionEvent event) {
		companyName = ListofEmployers.getSelectionModel().getSelectedItem();
		btnConfirmEmployerRegistartion.setDisable(false);
		btnRefuseEmployerRegistartion.setDisable(false);
	}

	/* this method for update the DB and return to the branch manager screen. */
	@FXML
	void Back(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Employer_Update, Employers));
		if (LoginScreenController.user.getRole().equals("CEO")) {
			start(event, "CEOScreen", "CEO",LoginScreenController.user.getFirstN());
		}
		if (LoginScreenController.user.getRole().equals("BranchManager")) {
			start(event, "BranchManagerScreen", "Branch Manager",LoginScreenController.user.getFirstN());
		}
	}

	/* this method is for the "Confirm" Button for approved company */
	@FXML
	void ConfirmEmployerRegistartion(ActionEvent event) {
		ListofEmployers.setDisable(true);
		for (int i = 0; i < Employers.size(); i++) {
			if (Employers.get(i).getCompanyName().equals(companyName)) {
				Employers.get(i).setCompanyStatus("Approved");
			}
		}
		ListofEmployers.getItems().clear();
		loadEmployerstoComboBox(Employers);
		ListofEmployers.setPromptText("List of employers awaiting for approval");
		ListofEmployers.setDisable(false);
	}

	/* this method is for the "Refuse" Button for Not Approved company */
	@FXML
	void RefuseEmployerRegistartion(ActionEvent event) {
		ListofEmployers.setDisable(true);
		for (int i = 0; i < Employers.size(); i++) {
			if (Employers.get(i).getCompanyName().equals(companyName)) {
				Employers.get(i).setCompanyStatus("Not approved");
			}
		}
		ListofEmployers.getItems().clear();
		refreshcombobox(Employers);
		ListofEmployers.setPromptText("List of employers awaiting approval");
		ListofEmployers.setDisable(false);

	}

	/* this method is for load the not approved and waiting company names. */
	public void loadEmployerstoComboBox(ArrayList<Employer> Employers) {
		for (Employer e : Employers) {
			if (e.getCompanyStatus().equals("Not approved") || e.getCompanyStatus().equals("Waiting")) {
				ListofEmployers.getItems().add(e.getCompanyName());
			}
		}
	}

	/* this method is for load only the waiting company names. */
	public void refreshcombobox(ArrayList<Employer> Employers) {
		for (Employer e : Employers) {
			if (e.getCompanyStatus().equals("Waiting")) {
				ListofEmployers.getItems().add(e.getCompanyName());
			}
		}
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.chat.accept(new Message(MessageType.get_Employer, null));
		loadEmployerstoComboBox(Employers);
		btnConfirmEmployerRegistartion.setDisable(true);
		btnRefuseEmployerRegistartion.setDisable(true);
		
	}

    @Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN() + " " + LoginScreenController.user.getLastN());
	}
}
