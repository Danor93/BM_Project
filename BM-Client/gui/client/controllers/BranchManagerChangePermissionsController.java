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
import main.ClientUI;
import main.PopUpMessage;
import javafx.fxml.Initializable;

public class BranchManagerChangePermissionsController extends Controller implements Initializable {

	/*
	 * Author:Danor this class for freeze an active account
	 */
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

	/* choose an account from the combo box */
	@FXML
	void ChooseAccount(ActionEvent event) {
		AccountName = ActiveAccountComboBox.getSelectionModel().getSelectedItem();
		ActiveBtn.setDisable(false);
		FreezeBtn.setDisable(false);
	}

	/* for the Active button */
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

	/* for the Freeze button */
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

	/* back to the branch manager screen. */
	@FXML
	void BackToBranchManager(ActionEvent event) throws IOException {
		startScreen(event, "BranchManagerScreen", "Branch Manager");
	}

	/* load account */
	public void loadAccounts(ArrayList<User> Users) {
		ActiveAccountComboBox.getItems().clear();
		ActiveAccountComboBox.setPromptText("Choose an Account");
		for (User u : Users) {
			ActiveAccountComboBox.getItems().add(u.getFirstN());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String Branch = LoginScreenController.user.getHomeBranch().toString();
		ClientUI.chat.accept(new Message(MessageType.get_accounts_for_freeze, Branch));
		ActiveAccount = false;
		FreezeAccount = false;
		ActiveBtn.setDisable(true);
		FreezeBtn.setDisable(true);
		loadAccounts(Users);
	}

}
