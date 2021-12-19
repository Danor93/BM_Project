package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class CustomerScreenController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreateOrder;

    @FXML
    private Button btnBack;
    
    @FXML
    private Label welcome;
    

    @FXML
    private ImageView backImage;

    
    /**This method gets us back to login page
     * @param event				pressing the button "back"
     * @throws IOException		the start method may throw an exception
     */
    @FXML
    void Back(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, null));
		startScreen(event, "LoginScreen", "Login");
    }
    

    /**This method proceed the order creation process
     * @param event				pressing the "create order" button
     * @throws IOException		the start method may throw an exception		
     */
    @FXML
    void createOrder(ActionEvent event) throws IOException {
    	startScreen(event,"InsertCodeOfW4C","Insert W4C code");
    }

    
	/**This method meant to display the first name of the user to the page
	 * @param firstN		user's first name		
	 */
	public void display(String firstN) {
		//welcome.setText("Welcome "+firstN);
	}
	
	@FXML
	void initialize() {
		//setImage(backImage, "backg.png");
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert btnCreateOrder != null : "fx:id=\"btnCreateOrder\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
	}

}

	


