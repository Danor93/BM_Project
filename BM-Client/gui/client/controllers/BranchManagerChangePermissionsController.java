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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.ClientUI;
import main.PopUpMessage;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * @author Danor
 * this class implements the functionality of the Branch Manager to Change Permissions of the users.
 */
public class BranchManagerChangePermissionsController extends Controller implements Initializable {


	public static ArrayList<User> Users = new ArrayList<>();
	public static String AccountName;
	public static String AccountID;
	public static Boolean ActiveAccount;
	public static Boolean FreezeAccount;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> ActiveAccountComboBox;

	@FXML
	private Button ActiveBtn;

	@FXML
	private Button FreezeBtn;

	@FXML
	private Button BackBMBtn;

	@FXML
	private ImageView homePage;

	@FXML
	private Button logout;

	@FXML
	private Text userName;

	/**
	 * @param event - back to the home page of Branch Manager.
	 */
	@FXML
	void backToHome(MouseEvent event) throws IOException {
		start(event, "BranchManagerScreen", "Branch Manager", "");
	}

	/**
	 * @param event - logout from the Branch Manager user.
	 */
	@FXML
	void logout(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event,"LoginScreen", "Login Screen","");
	}

	
	/** 
	 * @param event - choose an account from the combo box 
	 */
	@FXML
	void ChooseAccount(ActionEvent event) {
		AccountName = ActiveAccountComboBox.getSelectionModel().getSelectedItem();
		ActiveBtn.setDisable(false);
		FreezeBtn.setDisable(false);
	}

	/**
	 * @param event - for the Active button
	 */
	@FXML
	void ActiveAccount(ActionEvent event) {
		for (int i = 0; i < Users.size(); i++) {
			if (Users.get(i).getFirstN().equals(AccountName)) {
				AccountID = Users.get(i).getId();
				ClientUI.chat.accept(new Message(MessageType.check_account_status_Active, Users.get(i).getId()));
			}
			if (!ActiveAccount) {
				ClientUI.chat.accept(new Message(MessageType.Update_Status_to_Active, AccountID));
				PopUpMessage.successMessage(AccountName + " has been change to Active!");
				ActiveAccountComboBox.getItems().remove(AccountName);
				ActiveAccount = false;
				break;
			} else {
				PopUpMessage.errorMessage("The Account " + AccountName + " Already Active!");
				break;
			}
		}
	}

	/**
	 * @param event - for the Freeze button
	 */
	@FXML
	void FreezeAccount(ActionEvent event) {
		for (int i = 0; i < Users.size(); i++) {
			if (Users.get(i).getFirstN().equals(AccountName)) {
				AccountID = Users.get(i).getId();
				ClientUI.chat.accept(new Message(MessageType.check_account_status_Freeze, Users.get(i).getId()));
			}
			if (!FreezeAccount) {
				ClientUI.chat.accept(new Message(MessageType.Update_Status_to_Freeze, AccountID));
				PopUpMessage.successMessage(AccountName + " has been change to Freeze!");
				ActiveAccountComboBox.getItems().remove(AccountName);
				FreezeAccount = false;
				break;
			} else {
				PopUpMessage.errorMessage("The Account " + AccountName + " Already Freeze!");
				break;
			}
		}
	}


	/**
	 * load accounts into the combo box.
	 * @param Users - users from the server.
	 */
	public void loadAccounts(ArrayList<User> Users) {
		ActiveAccountComboBox.getItems().clear();
		ActiveAccountComboBox.setPromptText("Choose an Account");
		for (User u : Users) {
			ActiveAccountComboBox.getItems().add(u.getFirstN());
		}
	}

	/**
	 * initialize the screen.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String Branch = LoginScreenController.user.getHomeBranch().toString();
		ClientUI.chat.accept(new Message(MessageType.get_accounts_for_freeze, Branch));
		ActiveAccount = false;
		FreezeAccount = false;
		ActiveBtn.setDisable(true);
		FreezeBtn.setDisable(true);
		ActiveBtn.getStylesheets().add("/css/buttons.css");
		FreezeBtn.getStylesheets().add("/css/buttons.css");
		loadAccounts(Users);
	}

	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN() + " " + LoginScreenController.user.getLastN());
	}

}
