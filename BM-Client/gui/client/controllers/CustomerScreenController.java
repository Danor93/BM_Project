package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import Entities.SingletonOrder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import main.ClientUI;

/**
 * @author Adi
 * @author talia
 * This class describes the costumer side in the system-first page after logging into the BM system
 */
public class CustomerScreenController extends AbstractController implements Initializable {

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
		SingletonOrder.getInstance().myOrder.clear();
    	ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
  
	}

}