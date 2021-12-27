package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.ClientUI;

/** This class describes the costumer side in the system-first page after logging into the BM system
 * @author Adi & Talia
 *
 */
/**
 * @author Adi
 *
 */
/**
 * @author Adi
 *
 */
public class CustomerScreenController extends Controller implements Initializable {

	public static ArrayList<Order> orderConfirm;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView backImg;
    
    @FXML
    private Button btnCreateOrder;
    
    @FXML
    private Button btnLogout;

    @FXML
    private Button btnOrderRec;


    @FXML
    private Label welcome;


    /**This method gets us back to login page
     * @param event				pressing the button "logout"
     * @throws IOException		the logout method may throw an exception
     */

    @FXML
    void logout(ActionEvent event) throws IOException {
    	ClientUI.chat.accept(new Message(MessageType.Disconected, null));
		start(event, "LoginScreen", "Login","");
    }


    /**This method get us to Order confirmation page
     * @param event           pressing the button "Order Receipt Confirmation"
     * @throws IOException    the showOrders method may throw an exception
     */
    @FXML
    void showOrders(ActionEvent event) throws IOException 
    {
    	Message msg = new Message(MessageType.ClientConfirm,LoginScreenController.user.getId());
    	ClientUI.chat.accept(msg);
    	start(event,"confirmPage","Orders to confirm","");
    	

    }
    

    

    /**This method proceed the order creation process
     * @param event				pressing the "create order" button
     * @throws IOException		the start method may throw an exception		
     */
    @FXML
    void createOrder(ActionEvent event) throws IOException {
    	start(event,"InsertCodeOfW4C","Insert W4C code","");
    }

    
	/**This method meant to display the name of the user to the page
	 * @param firstN		user's first name		
	 */
	public void display(String firstN) {
		welcome.setText("Welcome "+firstN);
	}
	
  /*  @FXML
    void initialize() {
    	setImage(backImg,"/Image/Nbackground.jpg");
        assert backImg != null : "fx:id=\"backImg\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert btnCreateOrder != null : "fx:id=\"btnCreateOrder\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert btnOrderRec != null : "fx:id=\"btnOrderRec\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert welcome != null : "fx:id=\"welcome\" was not injected: check your FXML file 'CustomerScreen.fxml'.";

    }*/

	@Override
	public void initialize(URL location, ResourceBundle resources) {
    //	setImage(backImg,"/Image/Nbackground.jpg");
		
	}

}

	


