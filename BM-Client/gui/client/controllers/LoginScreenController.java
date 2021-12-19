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

public class LoginScreenController extends Controller {
	public static User user = new User(null, null, null, null, null, null, null, null);
	public static boolean LoginFlag = false;
	public static boolean AlreadyLoggedInFlag = false;
	public static boolean WrongInputFlag = false;
	public static String Name = null;
	public static String ID = null;
	public static ActionEvent mainevent;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	@FXML
	private TextField txtUserName;

	@FXML
	private TextField txtPassword;

	@FXML
	private Button btnLogin;

	@FXML
	private Label WrongInputInLoggin;


    @FXML
    private ImageView BackImage;

	@FXML
	void ConnectSystem(ActionEvent event) throws IOException {
		mainevent = event;
		StringBuilder str = new StringBuilder();
		str.append(txtUserName.getText());
		str.append("@");
		str.append(txtPassword.getText());
		Message msg = new Message(MessageType.loginSystem, str.toString());
		ClientUI.chat.accept(msg);
		if (LoginFlag) {
			if (AlreadyLoggedInFlag) {
				PopUpMessage.errorMessage("The User is Already logged in");
				AlreadyLoggedInFlag = false;
				LoginFlag = false;
			} else {
				LoginFlag = false;
				if (user.getRole().equals("BranchManager")) {
					startScreen(event, "BranchManagerScreen", "Branch Manager");

				} else if (user.getRole().equals("Customer")) {
					startScreen(event, "CustomerScreen", "Customer");
				} else if (user.getRole().equals("CEO")) {
					startScreen(event, "CEOScreen", "CEO");
				} else if (user.getRole().equals("Supplier")) {
					Name = user.getFirstN();
					ID = user.getId();
					startScreen(event, "SupplierScreen", "Supplier");
				}
			}
		}

		else {
			PopUpMessage.errorMessage("Wrong UserName/Password!");
		}

	}

	@FXML
	void getUserName(InputMethodEvent event) {

	}

    @FXML
    void initialize() {
    	//setImage(BackImage, "loginPage.jpg");
        assert BackImage != null : "fx:id=\"BackImage\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert WrongInputInLoggin != null : "fx:id=\"WrongInputInLoggin\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert txtUserName != null : "fx:id=\"txtUserName\" was not injected: check your FXML file 'LoginScreen.fxml'.";

    }

}