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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.ClientUI;
import main.PopUpMessage;
import javafx.scene.text.Text;

public class BranchManagerCloseAccountController extends Controller implements Initializable {

	/*
	 * Author:Danor
	 * This class is for close an account.
	 */
	
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
    

	@FXML
	void backToHome(MouseEvent event) throws IOException {
		start(event, "BranchManagerScreen", "Branch Manager", LoginScreenController.user.getUserName());
	}

	@FXML
	void logout(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event,"LoginScreen", "Login Screen","");
	}

    /*for the combo box*/
   @FXML
   void ChooseUserName(ActionEvent event) {
	   UserName = AccountComboBox.getSelectionModel().getSelectedItem().toString();
	   ConfirmBtn.setDisable(false);
   }
   
   /*for confirm button*/
   @FXML
   void ConfrimDelete(ActionEvent event) {
	   User user = null;
	   for(int i=0;i<Users.size();i++) {
		   if(Users.get(i).getFirstN().equals(UserName)) {
			  user=Users.get(i);
			  Users.remove(i);
		   }
	   }
	   ClientUI.chat.accept(new Message(MessageType.Delete_Account,user));
	   PopUpMessage.successMessage("Account " + UserName + " has been Deleted!");
	   loadAccounts(Users);
   }

    /*load account for the combo box*/
    public void loadAccounts(ArrayList<User> Users) {
    	for(User u:Users) {
    		AccountComboBox.getItems().add(u.getFirstN());
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.chat.accept(new Message(MessageType.get_Accounts,LoginScreenController.user.getHomeBranch().toString()));
		loadAccounts(Users);
		ConfirmBtn.setDisable(true);
	}

	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN() + " " + LoginScreenController.user.getLastN());
	}
}
