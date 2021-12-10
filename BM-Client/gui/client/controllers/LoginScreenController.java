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

public class LoginScreenController extends Controller {
	public static User user;
	public static boolean LoginFlag = false;
	public static boolean AlreadyLoggedInFlag = false;
	public static boolean WrongInputFlag = false;
	public static String Name = null;
	public static ActionEvent mainevent;
	// public static String Name;

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
    private ImageView loginImage;

	@FXML
	void ConnectSystem(ActionEvent event) throws IOException {
		mainevent = event;
		StringBuilder str = new StringBuilder();
		str.append(txtUserName.getText());
		str.append("@");
		str.append(txtPassword.getText());
		Message msg = new Message(MessageType.loginSystem, str.toString());
		
		ClientUI.chat.accept(msg);
		
		if(LoginFlag)
		{
			LoginFlag=false;
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			if (user.getRole().equals("BranchManager")) {
				BranchManagerScreenController aFrame = new BranchManagerScreenController();
				aFrame.start(primaryStage);

			} else if (user.getRole().equals("Customer")) {	
				FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/CustomerScreen.fxml"));
				Parent root=load.load();
				CustomerScreenController aFrame = load.getController();
				//aFrame.display(user.getFirstN());
				aFrame.start(primaryStage,root);
				
				
			} else if (user.getRole().equals("CEO")) {
				CEOScreenController aFrame = new CEOScreenController();
				aFrame.start(primaryStage);
			}
			else if (user.getRole().equals("Supplier")) {
				SupplierScreenController aFrame = new SupplierScreenController();
				aFrame.start(primaryStage);
			}
		}
		
		else
		{
			WrongInputInLoggin.setText("User name or password are incorrect, please try again!");
		}
		
	}
	
	@FXML
	void getUserName(InputMethodEvent event) {

	}
	
    public void start(Stage primaryStage) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/fxml/LoginScreen.fxml").openStream());
		Scene scene = new Scene(root);			
		primaryStage.setTitle("BiteMe Login Panel");
		primaryStage.setScene(scene);
		primaryStage.show();

	}


	@FXML
	void initialize() {
		super.setImage(loginImage, "LoginScreen.jpeg");
		assert txtUserName != null : "fx:id=\"txtUserName\" was not injected: check your FXML file 'LoginScreen.fxml'.";
		assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'LoginScreen.fxml'.";
		assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'LoginScreen.fxml'.";
	}
}