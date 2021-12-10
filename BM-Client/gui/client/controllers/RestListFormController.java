package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Dish;
import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import Entities.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.ChatClient;
import main.ClientUI;



/**This class shows the customer list of restaurant in specific city 
 * @author Adi & Talia
 * 
 *
 */

public class RestListFormController extends Controller implements Initializable, ControllerInterface {


	@FXML
	private Button backBtn;


    @FXML
    private TableColumn<Restaurant, String> colAdd;

    @FXML
    private TableColumn<Restaurant, String> colOpen;

    @FXML
    private TableColumn<Restaurant, String> colRes;

    @FXML
    private Button nextbtn;
    
    @FXML
    private Text cityName;

    @FXML
    private TableView<Restaurant> table;
    
    public static ArrayList<Restaurant> restaurants=new ArrayList<>();
    
    public static ArrayList<Dish> dishes=new ArrayList<>();

    
    public static Restaurant chosenRst;
    

	private ImageView BackImage;

    
    /**This method proceed to the menu of the restaurant
     * @param event		pressing the next button
     * @throws IOException
     */
    @FXML
    void proceedToOrder(ActionEvent event) throws IOException {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/MenuScreen.fxml"));
		Parent root=load.load();
		MenuScreenController aFrame = load.getController();
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
			aFrame.display(supplier);
			aFrame.start(primaryStage,root);
		} 	
    }

	@FXML
	void initialize() {
		setImage(BackImage, "background.png");
		assert BackImage != null : "fx:id=\"BackImage\" was not injected: check your FXML file 'restListForm.fxml'.";
		assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'restListForm.fxml'.";
		assert colAdd != null : "fx:id=\"colAdd\" was not injected: check your FXML file 'restListForm.fxml'.";
		assert colOpen != null : "fx:id=\"colOpen\" was not injected: check your FXML file 'restListForm.fxml'.";
		assert colRes != null : "fx:id=\"colRes\" was not injected: check your FXML file 'restListForm.fxml'.";
		assert nextbtn != null : "fx:id=\"nextbtn\" was not injected: check your FXML file 'restListForm.fxml'.";
		assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'restListForm.fxml'.";
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
		Message msg = new Message(MessageType.show_Restaurants, ChooseRestController.cityName);
	}

	@Override
	public void Back(ActionEvent event) throws IOException {
		startScreen(event, "ChooseRestaurant", "Choose Restaurant");
	}

}
