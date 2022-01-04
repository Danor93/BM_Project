package client.controllers;

	import java.io.IOException;

import Entities.Client;
import Entities.Message;
import Entities.MessageType;
import Entities.SingletonOrder;
import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
	import javafx.scene.image.ImageView;
	import javafx.scene.input.MouseEvent;
	import javafx.scene.text.Text;
import main.ClientUI;

	/** This class meant to identify the user as a costumer in the system
	 * @author Adi & Talia
	 *
	 */
	public class IdentifyW4cController extends Controller {

	    @FXML
	    private ImageView BackImage;

	    @FXML
	    private Button QR;

	    @FXML
	    private Label allertLbl;

	    @FXML
	    private Button back;

	    @FXML
	    private Button confirmBtn;

	    @FXML
	    private ImageView homePage;

	    @FXML
	    private Text userName;

	    @FXML
	    private TextField w4cManually;
	    
	    public static Client client=null; 

		/** This method meant to get back to login page and logout the customer
		 * @param event				pressing the "back" button 
		 * @throws IOException
		 */
	    @FXML
	    void back(ActionEvent event) throws IOException {
			SingletonOrder.getInstance().myOrder.clear();
	    	ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
			start(event, "LoginScreen", "Login","");

	    }

		/** This method meant to get back to costumer page
		 * @param event				pressing the "home" image 
		 * @throws IOException
		 */
	    @FXML
	    void backToHome(MouseEvent event) throws IOException {
	    	if(SingletonOrder.getInstance()!=null)
	    	{
	    		SingletonOrder.getInstance().myOrder.clear();
	    	}
	    	start(event, "CustomerScreen", "CustomerScreen",LoginScreenController.user.getFirstN());
	    	}

	    /**This method meant to confirm if the user is registered as a costumer
		 * @param event			meant to check the manually entering of W4C
		 */
		@FXML
		void confirm(ActionEvent event) {
			if(client==null)
			{
				Message msg=new Message(MessageType.IdentifyW4c,LoginScreenController.user.getId());
				ClientUI.chat.accept(msg);
			}
			
			if (w4cManually.getText().equals("Enter W4C code manually") || w4cManually.getText().equals("")) 
			{
				allertLbl.setText("Please enter W4C code or press the QR button");
			}
			
			else
			{
				if(w4cManually.getText().equals(client.getW4c_private()))
					switchScene(event);
				else
					allertLbl.setText("Wrong W4c, please try again or press the QR button");
			}
		}
	
		/**This method meant to get the W4C via QR
		 * @param event		meant to check the W4C with QR
		 */
		
		@FXML
		void getW4cFromQR(ActionEvent event) throws IOException {
			if(client==null)
			{
				ClientUI.chat.accept(new Message(MessageType.IdentifyW4c,LoginScreenController.user.getId()));
			}
			switchScene(event);
		}
		
		/**   
		 * @param event
		 */
		private void switchScene(ActionEvent event) {
			try {
				start(event,"ChooseRestaurant","Choose restaurant","");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		@Override
		public void display(String string) {
			userName.setText(LoginScreenController.user.getFirstN());
		}
	}