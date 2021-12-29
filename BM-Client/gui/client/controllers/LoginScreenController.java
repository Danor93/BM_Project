package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Entities.Message;
import Entities.MessageType;
import Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ChatClient;
import main.ClientController;
import main.ClientUI;
import main.PopUpMessage;
import javafx.scene.control.PasswordField;

public class LoginScreenController extends Controller {
	public static User user = null;
	// public static boolean LoginFlag = false;
	public static String statusUser;
	public static boolean AlreadyLoggedInFlag = false;
	public static String fullCompanyName = null;
	// public static boolean WrongInputFlag = false;
	// public static String Name = null;
	public static String ID = null;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	@FXML
	private TextField txtUserName;

	@FXML
	private PasswordField PasswordField;

	@FXML
	private Button btnLogin;

	@FXML
	private Label WrongInputInLoggin;

	@FXML
	private ImageView BackImage;

	@FXML
	void ConnectSystem(ActionEvent event) throws IOException {
		String[] DivededUandP;
		StringBuilder str = new StringBuilder();
		str.append(txtUserName.getText());
		str.append("@");
		str.append(PasswordField.getText());
		ClientUI.chat.accept(new Message(MessageType.loginSystem, str.toString()));
		// if (LoginFlag) {
		// if (AlreadyLoggedInFlag) {
		// PopUpMessage.errorMessage("The User is Already logged in");
		// AlreadyLoggedInFlag = false;
		// LoginFlag = false;

		// LoginFlag = false;
		if (!statusUser.equals("Active")) {
			WrongInputInLoggin.setText(statusUser);
			statusUser = null;
		}

		if (user != null) {
			switch (user.getRole()) {
			case "BranchManager": {
				start(event, "BranchManagerScreen", "Branch Manager", user.getFirstN());
				break;
			}

			case "Customer": {
				start(event, "CustomerScreen", "CustomerScreen", user.getFirstN());
				break;
			}

			case "CEO": {
				start(event, "CEOScreen", "CEO", user.getFirstN());
				break;
			}

			case "Supplier": {
				start(event, "SupplierScreen", "Supplier", user.getFirstN());
				break;
			}

			default: {
				DivededUandP = ((String) user.getRole()).split("-");
				if (DivededUandP[0].equals("HR")) {
					StringBuilder CompanyName = new StringBuilder();
					for (int i = 1; i < DivededUandP.length; i++)
						CompanyName.append(DivededUandP[i] + " ");
					CompanyName.deleteCharAt(CompanyName.length() - 1);
					fullCompanyName = String.valueOf(CompanyName);
					start(event, "HRManagerScreen", "HR Manager", user.getFirstN());
				}

			}

			}
		}
	}

	/*
	 * 
	 * } else if (user.getRole().equals("Customer")) { start(event,
	 * "CustomerScreen", "CustomerScreen",user.getFirstN());
	 * 
	 * } else if (user.getRole().equals("CEO")) { startScreen(event, "CEOScreen",
	 * "CEO"); } else if (user.getRole().equals("Supplier")) { Name =
	 * user.getFirstN(); ID = user.getId(); startScreen(event, "SupplierScreen",
	 * "Supplier"); } else { DivededUandP = ((String) user.getRole()).split(" "); if
	 * (DivededUandP[0].equals("HR")) { CompanyName = new StringBuilder(); for (int
	 * i = 1; i < DivededUandP.length; i++) CompanyName.append(DivededUandP[i] +
	 * " "); CompanyName.deleteCharAt(CompanyName.length()-1); fullCompanyName =
	 * String.valueOf(CompanyName); startScreen(event, "HRManagerScreen",
	 * "HR Manager"); } } } }
	 */

	@FXML
	void getUserName(InputMethodEvent event) {

	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub

	}
}