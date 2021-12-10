package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Dish;
import Entities.DishType;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChoosingDishesController implements Initializable {

    @FXML
    private Button addToOrder;

    @FXML
    private Button back;

    @FXML
    private Text foodKind;

    @FXML
    private ListView<?> list;

    @FXML
    private Label priceLabel;
    
   

    @FXML
    void addDishToOrder(ActionEvent event) {

    }

    @FXML
    void backToMenu(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/MenuScreen.fxml"));
		Parent root=load.load();
		MenuScreenController aFrame = load.getController();
		aFrame.display(RestListFormController.chosenRst.getSupplierName());
		aFrame.start(primaryStage,root);

    }

	public void start(Stage stage, Parent root) {
		Scene scene = new Scene(root);		
		stage.setScene(scene);
		stage.show();
		
	}

	public void display(String string) 
	{
		foodKind.setText(string);
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		ArrayList<String> dishType=new ArrayList<>();
		ArrayList<Float> priceOfDish= new ArrayList<>();
		for(Dish dish:RestListFormController.dishes)
		{
			if(DishType.toDishType( foodKind.getText()).equals(dish.getDishType()))
			{
				dishType.add(dish.getDishName());
				priceOfDish.add(dish.getPrice());
			}
					
		}
		
		
		
		ObservableList<String> observableList = FXCollections.observableArrayList(dishType);
		
		
	}

}
