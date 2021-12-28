package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Dish;
import Entities.Message;
import Entities.MessageType;
import Entities.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.ClientUI;


/**This class shows the customer list of restaurant in specific city 
 * @author Adi & Talia
 * 
 *
 */
public class RestListFormController extends Controller implements Initializable {

	@FXML
	private ImageView BackImage;

	@FXML
	private Button backBtn;

	@FXML
	private Text cityName;


    @FXML
    private TableColumn<Restaurant, String> colAdd;

    @FXML
    private TableColumn<Restaurant, String> colOpen;

    @FXML
    private TableColumn<Restaurant, String> colRes;

    @FXML
    private Button nextbtn;
    
    @FXML
    private ImageView homePage;

    @FXML
    private Button logout;
    

    @FXML
    private Text userName;

    @FXML
    private TableView<Restaurant> table;
    
    public static ArrayList<Restaurant> restaurants=new ArrayList<>();
    
    public static ArrayList<Dish> dishes=new ArrayList<>();

    
    public static Restaurant chosenRst;
    
    
	/** This method meant to get back to costumer page
	 * @param event				pressing the "home" image 
	 * @throws IOException
	 */
    @FXML
    void backToHome(MouseEvent event) throws IOException {
    	start(event, "CustomerScreen", "CustomerScreen","");
    }
    
    
	/** This method meant to get back to login page and logout the customer
	 * @param event				pressing the "logout" button 
	 * @throws IOException
	 */

    @FXML
    void logout(ActionEvent event) throws IOException {
    	ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login","");
    }
    

	/** This method meant to get back to choosing city
	 * @param event				pressing the "back" button 
	 * @throws IOException
	 */

    @FXML
    void backToCity(ActionEvent event) throws IOException {
		start(event,"ChooseRestaurant","Choose city","");

    }

    
    /**This method proceed to the menu of the restaurant
     * @param event		pressing the next button
     * @throws IOException
     */
    @FXML
    void proceedToOrder(ActionEvent event) throws IOException {
		String supplier=table.getSelectionModel().getSelectedItem().getSupplierName();
		if(supplier!=null)
		{
			String address=table.getSelectionModel().getSelectedItem().getAddress();
			Message msg= new Message(MessageType.get_Dishes,table.getSelectionModel().getSelectedItem().getRestCode());
			ClientUI.chat.accept(msg);

			
			for(Restaurant r:restaurants)
			{
				if(supplier.equals(r.getSupplierName())&& address.equals(r.getAddress()))
					chosenRst=r;
				
			}
			start(event,"MenuScreen","Restaurant's menu","");
			
			
			

			//aFrame.display(supplier,"");
			//aFrame.start(primaryStage,root);
		}
    }

	
	/**This method is initializes the table with the restaurant in the wanted city  
	 *
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Message msg=new Message(MessageType.show_Restaurants,ChooseRestController.cityName);
		ClientUI.chat.accept(msg);
		ObservableList<Restaurant> observableList = FXCollections.observableArrayList(restaurants);
		colAdd.setCellValueFactory(new PropertyValueFactory<>("address"));
		colOpen.setCellValueFactory(new PropertyValueFactory<>("openning"));
		colRes.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
		table.setItems(observableList);
	}

	
	/**
	 * @param city
	 */
	public void display(String city) {
		cityName.setText(city);
		userName.setText(LoginScreenController.user.getFirstN());

	}

}
