package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Dish;
import Entities.DishType;
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
import javafx.scene.control.Cell;
import javafx.scene.control.ListView;
import javafx.scene.control.skin.VirtualFlow;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ShowOrderController extends Controller implements Initializable{

    @FXML
    private Button back;

    @FXML
    private ListView<String> listOrder;

    @FXML
    private Button next;

    @FXML
    private Button remove;

    @FXML
    private Button totalPrice;
    
    ObservableList<String> orders;
    
    ArrayList<String> myDishes=new ArrayList<>();
    
    
   

    @FXML
    void back(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/MenuScreen.fxml"));
		Parent root=load.load();
		MenuScreenController aFrame = load.getController();
		aFrame.start(primaryStage, root);

    }

    @FXML
    void proceedToDelivery(ActionEvent event) {

    }

    @FXML
    void removeOrder(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		
		for(Dish dish: SingletonOrder.getInstance().myOrder)
		{
			StringBuilder dishString=new StringBuilder();
			dishString.append(dish.getDishName()+":       ");
			if(dish.getChoiceFactor()!=null)
			{
				dishString.append(dish.getChoiceFactor()+": ");
				dishString.append(dish.getChoiceDetails()+"       ");
			}
			
			if(dish.getExtra()!=null)
			{
				dishString.append(dish.getExtra()+"      ");
			}
			
			dishString.append("         "+dish.getPrice()+"$");
			myDishes.add(dishString.toString());
		}
		
		orders=FXCollections.observableArrayList(myDishes);
		listOrder.setItems(orders);
		
	    /*  if(!listOrder.getItems().isEmpty())
	        {
	            VirtualFlow ch=(VirtualFlow) listOrder.getChildrenUnmodifiable().get(0);
	            Font anyfont=new Font("System",16);
	            for (int i = 0; i < ch.getCellCount(); i++)
	            {
	                Cell cell= ch.getCell(i);
	                cell.setFont(anyfont);
	                cell.setTextFill(Color.RED);
	            }
	        }*/

	}

	public void start(Stage primaryStage, Parent root) {
		Scene scene=new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}

