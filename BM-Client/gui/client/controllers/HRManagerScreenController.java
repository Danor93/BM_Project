package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import Entities.Employer;
import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import main.ClientUI;

/**
 * @author Aviel
 * This class is for HR Manager main screen.
 */
public class HRManagerScreenController extends Controller{
	public static ArrayList<String> w4cBusiness = new ArrayList<String>();
	public static boolean RegistrationFlag = false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView BackImage;

    @FXML
    private Button btnRegistrationOfEmploye;

    @FXML
    private Button btnConfirmationOfOpeningABusinessAccount;

    @FXML
    private Button btnBack;

    @FXML
    private Label ExistLbl;
    
	private boolean RandomSuccess = false;
	private boolean marker = false;

	/**
	 * A method to return to the previous page. 
	 * @param event = ActionEvent
	 */
    @FXML
    void Back(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		startScreen(event, "LoginScreen", "Login");
    }

	/**
	 * A method to open a new screen (HRManagerConfirmationOfOpeningABusinessAccount).
	 * @param event = ActionEvent
	 */
    @FXML
    void ConfirmationOfOpeningABusinessAccount(ActionEvent event) throws IOException {
		startScreen(event, "HRManagerConfirmationOfOpeningABusinessAccount", "ConfirmationOfOpeningABusinessAccount");
    }

	/**
	 * A method for checking whether the employer has already registered for the database or not.
	 * If not, the HR registers the employer.
	 * If so, send an error message that the employer already registered.
	 * @param event = ActionEvent
	 */
    @FXML
    void RegistrationOfEmploye(ActionEvent event) throws IOException {
		String w4cNew=null;
		ClientUI.chat.accept(new Message(MessageType.getAllW4CBusiness, null));
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

		String DivededUandP[] = ((String) LoginScreenController.user.getRole()).split("-");
		String fullCompanyName = String.valueOf(DivededUandP[1]);

		Employer employer = new Employer(w4cNew, fullCompanyName, "Waiting");
		ClientUI.chat.accept(new Message(MessageType.RegistrationOfEmployer, employer));
		if (RegistrationFlag) {
			ExistLbl.setText("Registration succeeded");
		} else {
			ExistLbl.setText("Registration failed! The employe already signed");
		}
    }

    @FXML
    void initialize() {
        assert BackImage != null : "fx:id=\"BackImage\" was not injected: check your FXML file 'HRManagerScreen.fxml'.";
        assert btnRegistrationOfEmploye != null : "fx:id=\"btnRegistrationOfEmployers\" was not injected: check your FXML file 'HRManagerScreen.fxml'.";
        assert btnConfirmationOfOpeningABusinessAccount != null : "fx:id=\"btnConfirmationOfOpeningABusinessAccount\" was not injected: check your FXML file 'HRManagerScreen.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'HRManagerScreen.fxml'.";
        assert ExistLbl != null : "fx:id=\"ExistLbl\" was not injected: check your FXML file 'HRManagerScreen.fxml'.";
    }

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}
}