package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.ClientUI;
import main.PopUpMessage;
import javafx.scene.text.Text;

public class ConfirmSupplierRegController extends Controller implements Initializable {

	public static Supplier supplier;
	public static Boolean confirmRegFlag = false;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField AddressTXT;

	@FXML
	private ImageView BackImage;

	@FXML
	private TextField CityTXT;

	@FXML
	private TextField ConfirmEmpTxt;

	@FXML
	private Label InvaildEmployeeID;

	@FXML
	private Label InvaildopeningTime;

	@FXML
	private TextField OpeningTimeTXT;

	@FXML
	private TextField ResIDTXT;

	@FXML
	private TextField ResNameTXT;

	@FXML
	private Button btnConfirmSupplierRegistartion;

	@FXML
	private Label invaildName;

	@FXML
	private Label InvaildCityName;

	@FXML
	private Label InvaildResID;

	@FXML
	private Label InvaildAddress;
	
    @FXML
    private ImageView homePage;

    @FXML
    private Button logout;

    @FXML
    private Text userName;

	@FXML
	void backToHome(MouseEvent event) throws IOException {
		start(event, "BranchManagerScreen", "Branch Manager", LoginScreenController.user.getUserName());
	}

	@FXML
	void logout(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event,"LoginScreen", "Login Screen","");
	}

	@FXML
	void ConfirmSupplierRegistartion(ActionEvent event) {
		Boolean ConfirmFlag = true;
		if (AddressTXT.getText().isEmpty() || CityTXT.getText().isEmpty() || ConfirmEmpTxt.getText().isEmpty()
				|| OpeningTimeTXT.getText().isEmpty() || ResIDTXT.getText().isEmpty()
				|| ResNameTXT.getText().isEmpty()) {
			PopUpMessage.errorMessage("you must fill all of the fileds!");
		} else {
			while (ConfirmFlag) {
				char[] charsResName = ResNameTXT.getText().toCharArray();
				for (char c : charsResName) {

					if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
						invaildName.setVisible(true);
					}
				}

				char[] charsCityName = CityTXT.getText().toCharArray();

				for (char c : charsCityName) {

					if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
						InvaildCityName.setVisible(true);
					}
				}

				char[] charsAddress = AddressTXT.getText().toCharArray();

				for (char c : charsAddress) {

					if (!Character.isLetter(c) && !Character.isSpaceChar(c) && !Character.isDigit(c)) {
						InvaildAddress.setVisible(true);
					}
				}

				/**
				 * String regexStr = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$"; if
				 * (!(ResIDTXT.getText()).matches(regexStr)) {
				 * InvaildEmployeeID.setVisible(true); } if
				 * (!ConfirmEmpTxt.getText().matches(regexStr)) { InvaildResID.setVisible(true);
				 * }
				 **/

				char[] charresid = ResIDTXT.getText().toCharArray();

				for (char c : charresid) {

					if (!Character.isDigit(c)) {
						InvaildResID.setVisible(true);
					}
				}

				char[] charEmpId = ConfirmEmpTxt.getText().toCharArray();

				for (char c : charEmpId) {

					if (!Character.isDigit(c)) {
						InvaildEmployeeID.setVisible(true);
					}
				}

				if (invaildName.isVisible() && InvaildAddress.isVisible() && InvaildCityName.isVisible()
						&& InvaildEmployeeID.isVisible() && InvaildopeningTime.isVisible()
						&& InvaildResID.isVisible()) {
					ConfirmFlag = false;
				}
				else {
					break;
				}
			}
			supplier = new Supplier(Integer.parseInt(ResIDTXT.getText()), ResNameTXT.getText(),
					OpeningTimeTXT.getText(), CityTXT.getText(), AddressTXT.getText(), "Approved",
					LoginScreenController.user.getHomeBranch(), ConfirmEmpTxt.getText());

			ClientUI.chat.accept(new Message(MessageType.check_suppliers_details, supplier));
			if (confirmRegFlag) {
				PopUpMessage
						.successMessage("The supplier " + supplier.getSupplierName() + " has been added succesfuly!");
				confirmRegFlag = false;
			} else {
				PopUpMessage.errorMessage("details are not match to the DB,check again please.");
			}
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN() + " " + LoginScreenController.user.getLastN());
	}

}