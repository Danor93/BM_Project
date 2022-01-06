package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.ClientUI;
import main.PopUpMessage;

/**
 * @author Danor 
 * this class implements the functionality of the Branch Manager to close an account.
 */
public class BranchManagerCloseAccountController extends AbstractController implements Initializable {

	public static ArrayList<User> Users;
	public static String UserName;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button ConfirmBtn;

	@FXML
	private ImageView homePage;

	@FXML
	private Button logout;

	@FXML
	private Text userName;

	@FXML
	private ComboBox<String> AccountComboBox;

	/**
	 * @param event - back to the home screen of the Branch Manager
	 */
	@FXML
	void backToHome(MouseEvent event) throws IOException {
		start(event, "BranchManagerScreen", "Branch Manager", LoginScreenController.user.getUserName());
	}

	/**
	 * @param event - logout the user.
	 */
	@FXML
	void logout(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login Screen", "");
	}

	/**
	 * choosing a user name from the combo box.
	 * 
	 * @param event - for the combo box
	 */
	@FXML
	void ChooseUserName(ActionEvent event) {
		UserName = AccountComboBox.getSelectionModel().getSelectedItem().toString();
		ConfirmBtn.setDisable(false);
	}

	/**
	 * confirm the delete of the user.
	 * 
	 * @param event - for the Confirm button.
	 */
	@FXML
	void ConfrimDelete(ActionEvent event) {
		User user = null;
		for (int i = 0; i < Users.size(); i++) {
			if (Users.get(i).getFirstN().equals(UserName)) {
				user = Users.get(i);
				Users.remove(i);
			}
		}
		ClientUI.chat.accept(new Message(MessageType.Delete_Account, user));
		PopUpMessage.successMessage("Account " + UserName + " has been Deleted!");
		loadAccounts(Users);
	}

	/**
	 * load account for the combo box.
	 * @param Users - the users from the server to delete.
	 */
	public void loadAccounts(ArrayList<User> Users) {
		for (User u : Users) {
			AccountComboBox.getItems().add(u.getFirstN());
		}
	}

	/**
	 * initialize the screen buttons and combo box.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.chat
				.accept(new Message(MessageType.get_Accounts, LoginScreenController.user.getHomeBranch().toString()));
		loadAccounts(Users);
		ConfirmBtn.setDisable(true);
		ConfirmBtn.getStylesheets().add("/css/buttons.css");
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
