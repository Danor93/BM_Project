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

/**
 * @author Danor
 * this class implements the functionality of the Branch Manager to confirm an Employer.
 */
public class ConfirmEmployerRegController extends Controller implements Initializable {
	public static ArrayList<Employer> Employers = new ArrayList<Employer>();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> ListofEmployers;

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
	
	/**
	 * for update the DB and return to the Branch Manager Screen.
	 * @param event - back to the home screen of the Branch Manager
	 */
	@FXML
	void backToHome(MouseEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Employer_Update, Employers));
		start(event, "BranchManagerScreen", "Branch Manager",LoginScreenController.user.getFirstN());
	}

	/**
	 * @param event - logout the user.
	 */
	@FXML
	void logout(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event,"LoginScreen", "Login Screen","");
	}

	/**
	 * for the Company combo box choose.
	 * @param event - for the combo box.
	 */
	@FXML
	void ChooseCompany(ActionEvent event) {
		companyName = ListofEmployers.getSelectionModel().getSelectedItem();
		btnConfirmEmployerRegistartion.setDisable(false);
		btnRefuseEmployerRegistartion.setDisable(false);
	}


	/**
	 * this method handles the confirm functionality of the Branch Manager to confirm Employer.
	 * @param event - for the Confirm Button.
	 */
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


	/**
	 * this method handles the confirm functionality of the Branch Manager to Refuse an a Employer.
	 * @param event - for the Refuse Button.
	 */
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

	
	/**
	 * this method load account to the combo box.
	 * @param Employers - employers from the server to confirm/refuse.
	 */
	public void loadEmployerstoComboBox(ArrayList<Employer> Employers) {
		for (Employer e : Employers) {
			if (e.getCompanyStatus().equals("Waiting")) {
				ListofEmployers.getItems().add(e.getCompanyName());
			}
		}
	}

	
	/**
	 * this method refresh the combo box after the user choose to confirm or refuse.
	 * @param Employers - employers from the server to confirm/refuse.
	 */
	public void refreshcombobox(ArrayList<Employer> Employers) {
		for (Employer e : Employers) {
			if (e.getCompanyStatus().equals("Waiting")) {
				ListofEmployers.getItems().add(e.getCompanyName());
			}
		}
	}

	/**
	 * initialize the combo box with the employers form the server and button functionality and style.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.chat.accept(new Message(MessageType.get_Employer, null));
		loadEmployerstoComboBox(Employers);
		btnConfirmEmployerRegistartion.setDisable(true);
		btnRefuseEmployerRegistartion.setDisable(true);
		btnConfirmEmployerRegistartion.getStylesheets().add("/css/buttons.css");
		btnRefuseEmployerRegistartion.getStylesheets().add("/css/buttons.css");
		logout.getStylesheets().add("/css/buttons.css");
	}

	/**
	 * display the name of the user.
	 */
    @Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN() + " " + LoginScreenController.user.getLastN());
	}
}