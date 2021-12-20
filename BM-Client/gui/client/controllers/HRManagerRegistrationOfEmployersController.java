package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Random;

import Entities.Employer;
import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.ClientUI;

public class HRManagerRegistrationOfEmployersController extends Controller {
	public static boolean RegistrationFlag = false;
	public static ArrayList<String> w4cBusiness = new ArrayList<String>();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnBack;

	@FXML
	private TextField companyName;

	@FXML
	private Button btnConfirm;

	@FXML
	private Label mibiLabel;

	private boolean RandomSuccess = false;
	private boolean marker = false;

	@FXML
	void Back(ActionEvent event) throws IOException {
		startScreen(event, "HRManagerScreen", "HR Manager");
	}

	@FXML
	void confirm(ActionEvent event) {
		String w4cNew=null;
		while (RandomSuccess == false) {
			Random rand = new Random(); // instance of random class
			int int_random = rand.nextInt(1000);
			w4cNew = "B"+String.valueOf(int_random);
			for (String s : w4cBusiness) {
				if (w4cNew.equals(s)) {
					marker = true;
				}
			}
			if (marker == false)
				RandomSuccess = true;
		}
		RandomSuccess = false;

		Employer employer = new Employer(w4cNew, companyName.getText(), "waiting");
		ClientUI.chat.accept(new Message(MessageType.RegistrationOfEmployer, employer));
		if (RegistrationFlag) {
			mibiLabel.setText("Registration succeeded");
			System.out.println(employer.getCompanyName() + " " + employer.getW4cBussines());
		} else {
			mibiLabel.setText("Registration failed!");
		}
	}

	@FXML
	void initialize() {
		assert btnBack != null
				: "fx:id=\"btnBack\" was not injected: check your FXML file 'HRManagerRegistrationOfEmployers.fxml'.";
		assert companyName != null
				: "fx:id=\"companyName\" was not injected: check your FXML file 'HRManagerRegistrationOfEmployers.fxml'.";
		assert btnConfirm != null
				: "fx:id=\"btnConfirm\" was not injected: check your FXML file 'HRManagerRegistrationOfEmployers.fxml'.";
		assert mibiLabel != null
				: "fx:id=\"mibiLabel\" was not injected: check your FXML file 'HRManagerRegistrationOfEmployers.fxml'.";
	}
}