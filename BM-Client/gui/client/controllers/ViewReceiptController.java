package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.BusinessAccountTracking;
import Entities.Message;
import Entities.MessageType;
import Entities.Receipt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.ClientUI;

public class ViewReceiptController extends Controller implements Initializable{
	public static ArrayList<Receipt> receipts = new ArrayList<Receipt>();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView BackImage;

    @FXML
    private TableView<Receipt> table;

    @FXML
    private TableColumn<Receipt, Integer> orderNumber;

    @FXML
    private TableColumn<Receipt, String> restaurant;

    @FXML
    private TableColumn<Receipt, Float> totalPrice;

    @FXML
    private TableColumn<Receipt, Float> priceAfterCommission;

    @FXML
    private Text userName;

    @FXML
    private ImageView homePage;

    @FXML
    private Button logout;
    
    @FXML
    private Label miniLabel;
    
	ObservableList<Receipt> list;

	/**
	 * This method meant to get back to supplier page
	 * 
	 * @param event = ActionEvent
	 */
	@FXML
	void backToHome(MouseEvent event) throws IOException {
		start(event, "SupplierScreen", "Supplier page", "");
	}

	/**
	 * This method meant to get back to login page and logout the supplier
	 * 
	 * @param event = ActionEvent
	 */
	@FXML
	void logout(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login", "");
	}

    @FXML
    void initialize() {
        assert BackImage != null : "fx:id=\"BackImage\" was not injected: check your FXML file 'ViewReceipt.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'ViewReceipt.fxml'.";
        assert orderNumber != null : "fx:id=\"orderNumber\" was not injected: check your FXML file 'ViewReceipt.fxml'.";
        assert restaurant != null : "fx:id=\"restaurant\" was not injected: check your FXML file 'ViewReceipt.fxml'.";
        assert totalPrice != null : "fx:id=\"totalPrice\" was not injected: check your FXML file 'ViewReceipt.fxml'.";
        assert priceAfterCommission != null : "fx:id=\"priceAfterCommission\" was not injected: check your FXML file 'ViewReceipt.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'ViewReceipt.fxml'.";
        assert homePage != null : "fx:id=\"homePage\" was not injected: check your FXML file 'ViewReceipt.fxml'.";
        assert logout != null : "fx:id=\"logout\" was not injected: check your FXML file 'ViewReceipt.fxml'.";
        assert miniLabel != null : "fx:id=\"miniLabel\" was not injected: check your FXML file 'ViewReceipt.fxml'.";
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderNumber.setCellValueFactory(new PropertyValueFactory<Receipt, Integer>("orderNumber"));
		restaurant.setCellValueFactory(new PropertyValueFactory<Receipt, String>("restaurant"));
		totalPrice.setCellValueFactory(new PropertyValueFactory<Receipt, Float>("totalPrice"));
		priceAfterCommission.setCellValueFactory(new PropertyValueFactory<Receipt, Float>("priceAfterCommission"));
		ClientUI.chat.accept(new Message(MessageType.get_orders_Approved, LoginScreenController.user.getId()));
		list = FXCollections.observableArrayList(receipts);
		table.setItems(list);
	}

	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN());
		float totalPriceAfterCommission=0;
		for(Receipt r : receipts)
			totalPriceAfterCommission += r.getPriceAfterCommission();
		miniLabel.setText("The total price to be delivered to the supplier is -"+totalPriceAfterCommission);
	}
}