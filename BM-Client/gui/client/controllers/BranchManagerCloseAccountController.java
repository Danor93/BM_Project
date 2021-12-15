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
import main.ClientUI;

public class BranchManagerCloseAccountController extends Controller implements Initializable {

	public static ArrayList<User> Users;
	public static String userName;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackBtn;
    
    @FXML
    private Button ConfirmBtn;


    @FXML
    private ComboBox<String> AccountComboBox;
    
    @FXML
    void backToBranchManager(ActionEvent event) throws IOException {
    	startScreen(event, "BranchManagerScreen", "Branch Manager Main");
    }

   @FXML
   void ChooseUserName(ActionEvent event) {
	   userName = AccountComboBox.getSelectionModel().getSelectedItem();
   }
   
   @FXML
   void ConfrimDelete(ActionEvent event) {
	   User user = null;
	   for(int i=0;i<Users.size();i++) {
		   if(Users.get(i).getUserName().equals(userName)) {
			  user=Users.get(i);
			  Users.remove(i);
		   }
	   }
	   ClientUI.chat.accept(new Message(MessageType.Delete_Account,user));
   }
    
    
    @FXML
    void initialize() {
        assert BackBtn != null : "fx:id=\"BackBtn\" was not injected: check your FXML file 'BranchManagerCloseAccount.fxml'.";
        assert AccountComboBox != null : "fx:id=\"AccountComboBox\" was not injected: check your FXML file 'BranchManagerCloseAccount.fxml'.";
    }


    public void loadAccounts(ArrayList<User> Users) {
    	for(User u:Users) {
    		AccountComboBox.getItems().add(u.getUserName());
    	}
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.chat.accept(new Message(MessageType.get_Accounts,null));
		loadAccounts(Users);
	}
}
