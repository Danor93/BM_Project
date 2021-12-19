package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Dish;
import Parsing.Parsing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.ChatClient;

public class TableUpdateMenuScreenController extends Controller implements Initializable{
	public ResourceBundle getResources() {
		return resources;
	}

	public URL getLocation() {
		return location;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Dish> table;

    @FXML
    private TableColumn<Dish, String> colDishName;

    @FXML
    private TableColumn<Dish, String> colDishType;

    @FXML
    private TableColumn<Dish, Float> colPrice;

    @FXML
    private TableColumn<Dish, Integer> colInventory;

    @FXML
    private TableColumn<Dish, String> colChoiceFactor;

    @FXML
    private TableColumn<Dish, String> colChoiceDetails;

    @FXML
    private TableColumn<Dish, String> colIngredients;

    @FXML
    private TableColumn<Dish, String> colExtra;

    @FXML
    private Button backBtn;

    @FXML
    private Button ToAddMoreDishesBtn;

    @FXML
    void ToAddMoreDishes(ActionEvent event) {

    }

    @FXML
    void backToSupplier(ActionEvent event) throws IOException {
		startScreen(event, "SupplierScreen", "Supplier page");
    }

    @FXML
    void initialize() {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'TableUpdateMenuScreen.fxml'.";
        assert colDishName != null : "fx:id=\"colDishName\" was not injected: check your FXML file 'TableUpdateMenuScreen.fxml'.";
        assert colDishType != null : "fx:id=\"colDishType\" was not injected: check your FXML file 'TableUpdateMenuScreen.fxml'.";
        assert colPrice != null : "fx:id=\"colPrice\" was not injected: check your FXML file 'TableUpdateMenuScreen.fxml'.";
        assert colInventory != null : "fx:id=\"colInventory\" was not injected: check your FXML file 'TableUpdateMenuScreen.fxml'.";
        assert colChoiceFactor != null : "fx:id=\"colChoiceFactor\" was not injected: check your FXML file 'TableUpdateMenuScreen.fxml'.";
        assert colChoiceDetails != null : "fx:id=\"colChoiceDetails\" was not injected: check your FXML file 'TableUpdateMenuScreen.fxml'.";
        assert colIngredients != null : "fx:id=\"colIngredients\" was not injected: check your FXML file 'TableUpdateMenuScreen.fxml'.";
        assert colExtra != null : "fx:id=\"colExtra\" was not injected: check your FXML file 'TableUpdateMenuScreen.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'TableUpdateMenuScreen.fxml'.";
        assert ToAddMoreDishesBtn != null : "fx:id=\"ToAddMoreDishesBtn\" was not injected: check your FXML file 'TableUpdateMenuScreen.fxml'.";

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Dish> observableList = FXCollections.observableArrayList(Parsing.dishes);
		table.getItems().clear();
		colDishName.setCellValueFactory(new PropertyValueFactory<>("dishName"));
		colDishType.setCellValueFactory(new PropertyValueFactory<>("dishType"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		colInventory.setCellValueFactory(new PropertyValueFactory<>("inventory"));
		colChoiceFactor.setCellValueFactory(new PropertyValueFactory<>("choiceFactor"));
		colChoiceDetails.setCellValueFactory(new PropertyValueFactory<>("choiceDetails"));
		colIngredients.setCellValueFactory(new PropertyValueFactory<>("Ingredients"));
		colExtra.setCellValueFactory(new PropertyValueFactory<>("extra"));
		table.setItems(observableList);		
	}
}