package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.Order;
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
import main.ChatClient;
import main.ClientUI;


public class RecConfirmController extends Controller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView BackImage;

    @FXML
    private Button confirm;

    @FXML
    private ImageView homePage;

    @FXML
    private Button logout;

    @FXML
    private TableColumn<Order,String> orderNum;

    @FXML
    private TableColumn<Order,String> products;

    @FXML
    private TableColumn<Order,String> restName;

    @FXML
    private TableView<Order> table;

    @FXML
    private TableColumn<Order,String> time;
    
    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private Text userName;
    
    ObservableList<Order> observableList;

    
	/** This method meant to get back to login page and logout the customer
	 * @param event				pressing the "back" button 
	 * @throws IOException
	 */
    @FXML
    void logout(ActionEvent event) throws IOException {
    	ClientUI.chat.accept(new Message(MessageType.Disconected, null));
		start(event, "LoginScreen", "Login","");

    }

    
	/** This method meant to get back to costumer page
	 * @param event				pressing the "home" image 
	 * @throws IOException
	 */
    @FXML
    void backToHome(MouseEvent event) throws IOException {
    	start(event, "CustomerScreen", "CustomerScreen","");
    }
    
    

    @FXML
    void confirmOrder(ActionEvent event) {
    	Order order=table.getSelectionModel().getSelectedItem();
    	int index=CustomerScreenController.orderConfirm.indexOf(order);
    	CustomerScreenController.orderConfirm.remove(order);
    	observableList = FXCollections.observableArrayList(CustomerScreenController.orderConfirm);
    	table.setItems(observableList);
    	order.setCostumerId(LoginScreenController.user.getId());
    	ClientUI.chat.accept(new Message(MessageType.orderDone, order));

    }


	/** this function meant to initialize the table
	 *
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		observableList = FXCollections.observableArrayList(CustomerScreenController.orderConfirm);
		//table.getItems().clear();
		orderNum.setCellValueFactory(new PropertyValueFactory<>("orderNum"));
		restName.setCellValueFactory(new PropertyValueFactory<>("restName"));
		time.setCellValueFactory(new PropertyValueFactory<>("timeOfOrder"));
		date.setCellValueFactory(new PropertyValueFactory<>("dateOfOrder"));
		products.setCellValueFactory(new PropertyValueFactory<>("dishes"));
		table.setItems(observableList);
	}
	

	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN());
		
	}

}
