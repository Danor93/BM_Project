package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Order;
import client.ChatClient;
import client.ClientController;
import client.ClientUI;
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
import javafx.stage.Stage;
import Entities.Order;
import Entities.Message;
import Entities.MessageType;

public class ShowFormController implements Initializable {
	
	 public ResourceBundle getResources() {
		return resources;
	}

	public URL getLocation() {
		return location;
	}
	@FXML
	private TableView<Order> table;
	
	ObservableList<Order> observableList;
    

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Order, String> colAddress;


    @FXML
    private TableColumn<Order, String> colOrdNum;

    @FXML
    private TableColumn<Order, String> colOrdTime;

    @FXML
    private TableColumn<Order, String> colPhone;

    @FXML
    private TableColumn<Order, String> colRestaurant;

    @FXML
    private TableColumn<Order, String> colType;
     
    @FXML
    private Button backBtn;
    
    @FXML
    private Button showOrderBtn;
    
    @FXML
    void backToMain(ActionEvent event) throws IOException 
    {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("Showupdate.fxml"));
		Scene scene = new Scene(root);		
		primaryStage.setTitle("BiteMe");
		primaryStage.setScene(scene);		
		primaryStage.show();
    }
    
    @FXML
    void showOrders(ActionEvent event) {
    	Message message = new Message(MessageType.Show_Orders, null);
    	showOrderBtn.setDisable(true);
    	ChatClient.chatClient.handleMessageFromClientUI(message);
    	loadOrders(ChatClient.orders);
    }
    
    public void loadOrders(ArrayList<Order> orderlist) {
		table.getItems().clear();
		for (Order order : orderlist) {
			table.getItems().add(order);
		}
		showOrderBtn.setDisable(false);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		ObservableList<Order> observableList = FXCollections.observableArrayList(ChatClient.orders);
		colRestaurant.setCellValueFactory(new PropertyValueFactory<Order,String>("restaurant"));
		colOrdNum.setCellValueFactory(new PropertyValueFactory<Order,String>("orderNumber"));
		colOrdTime.setCellValueFactory(new PropertyValueFactory<Order,String>("orderTime"));
		colPhone.setCellValueFactory(new PropertyValueFactory<Order,String>("phone"));
		colType.setCellValueFactory(new PropertyValueFactory<Order,String>("type"));
		colAddress.setCellValueFactory(new PropertyValueFactory<Order,String>("address"));
		table.setItems(observableList);
	}

}

