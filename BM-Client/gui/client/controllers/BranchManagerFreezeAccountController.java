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

public class BranchManagerFreezeAccountController extends Controller implements Initializable {

	/*
	 * Author:Danor this class for freeze an active account
	 */
	public static ArrayList<User> Users = new ArrayList<>();
	public static String AccountName;
	public static String AccountID;
	public static Boolean FreezeAccount = true;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> ActiveAccountComboBox;

	@FXML
	private Button ConfrimBtn;

	@FXML
	private Button BackBMBtn;

	/* choose an account from the combo box */
	@FXML
	void ChooseAccount(ActionEvent event) {
		AccountName = ActiveAccountComboBox.getSelectionModel().getSelectedItem();
		ConfrimBtn.setDisable(false);
	}

	/* for the confirm button */
	@FXML
	void ConfrimFreezeAccount(ActionEvent event) {
		for (int i = 0; i < Users.size(); i++) {
			if (Users.get(i).getFirstN().equals(AccountName)) {
				AccountID = Users.get(i).getId();
				ClientUI.chat.accept(new Message(MessageType.check_if_account_freeze, Users.get(i).getId()));
			}

			if (FreezeAccount == false) {
				ClientUI.chat.accept(new Message(MessageType.Account_For_Freeze, AccountID));
				PopUpMessage.successMessage(AccountName + " has been freezed!");
				break;
			} else {
				PopUpMessage.errorMessage("The Account Already Freeze!");
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
		for (User u : Users) {
			ActiveAccountComboBox.getItems().add(u.getFirstN());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.chat.accept(new Message(MessageType.get_accounts_for_freeze, null));
		FreezeAccount = true;
		ConfrimBtn.setDisable(true);
		loadAccounts(Users);
	}

}
