package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Dish;
import Entities.DishType;
import Entities.SingletonOrder;
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
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChoosingDishesController extends Controller implements Initializable {

    @FXML
    private Button addToOrder;

    @FXML
    private Button back;

    @FXML
    private Text foodKind;

    @FXML
    private ListView<String> list;

    
    @FXML
    private TextArea ingredients;

    
    @FXML
    private Text notify;
    
    @FXML
    private Text dishLbl;
    
    @FXML
    private Button minus;

    @FXML
    private Button plus;
    
    @FXML
    private Label quantity;

    @FXML
    private AnchorPane ingPane;
    
    @FXML
    private Text price;

    public ArrayList<String> dishNames;
	public ArrayList<Dish> dishListOfType;
	public static Dish chosenDish;
	public static int quentity=1;

    @FXML
    void addDishToOrder(ActionEvent event) throws IOException 
    {
    	String selectedDish =list.getSelectionModel().getSelectedItem();
    	
    	if(selectedDish==null)
    	{
    		notify.setText("Please choose dish");
    	}
    	
    	else
    	{
    		
    		int indexOfDish=dishNames.indexOf(selectedDish);
        	chosenDish=dishListOfType.get(indexOfDish);
        	if(!chosenDish.getChoiceFactor().equals("")||!chosenDish.getExtra().equals(""))
        	{
        		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DishOptions.fxml"));
        		Parent root=load.load();
        		DishOptionsController aFrame = load.getController();
        		aFrame.setValue();
        		aFrame.start(primaryStage, root);
        	}
        	
        	else
        	{
        		chosenDish.setRestCode(RestListFormController.chosenRst.getRestCode());
        		chosenDish.setQuentity(quentity);
        		SingletonOrder.getInstance().myOrder.add(chosenDish);
        		notify.setFill(Color.GREEN);
        		notify.setText("The dish was successfully added to your order");
        	}
    	}
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
    
    @FXML
    void getDishPrice(MouseEvent event)
    {
    	quantity.setText("1");
    	quentity=1;
    	String s="price : ";
    	String selectedDish =list.getSelectionModel().getSelectedItem();
    	int indexOfDish=dishNames.indexOf(selectedDish);
    	ingPane.setVisible(true);
    	dishLbl.setText(selectedDish);
    	price.setText(s+dishListOfType.get(indexOfDish).getPrice()+"$");
    	ingredients.setText(dishListOfType.get(indexOfDish).getIngredients());
    	minus.setDisable(false);
    	plus.setDisable(false);

    }
    
    @FXML
    void decQuentity(ActionEvent event) {
    	if(quentity>1)
    		quentity--;
    	
    	quantity.setText(""+quentity);
    	

    }
    
    
    @FXML
    void incQuentity(ActionEvent event) {
    	quentity++;
    	quantity.setText(""+quentity);
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
		dishNames=new ArrayList<>();
		dishListOfType= new ArrayList<>();
		for(Dish dish:RestListFormController.dishes)
		{
			if((MenuScreenController.chosenFoodType).equals(DishType.fromTypeToStr(dish.getDishType())))
			{
				System.out.println(dish.getDishName());
				dishNames.add(dish.getDishName());
				dishListOfType.add(dish);
			}
					
		}

		ObservableList<String> observableList = FXCollections.observableArrayList(dishNames);
		list.getItems().addAll(observableList);	
	}
}



