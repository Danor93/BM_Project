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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DishOptionsController extends Controller implements Initializable{

    @FXML
    private Button add;

    @FXML
    private Button addToOrder;

    @FXML
    private Button back;

    @FXML
    private ListView<String> chosenList;

    @FXML
    private ImageView cookLevel;

    @FXML
    private ListView<String> optionsList;

    @FXML
    private Button remove;

    @FXML
    private ComboBox<String> choice;

    @FXML
    private Text choiceLabel;

    @FXML
    private Text userName;
    

    @FXML
    private Text notify;

    
    private ArrayList<String> chosenExtra=new ArrayList<>();    
    ObservableList <String>observableListExtra; 
    
    private ArrayList<String> UnChosenExtra=new ArrayList<>();    
    ObservableList <String>observableListUnExtra; 

    @FXML
    void addDishToOrder(ActionEvent event) throws IOException 
    {
    	if(!ChoosingDishesController.chosenDish.getChoiceFactor().equals(""))
    	{
    		String op=choice.getSelectionModel().getSelectedItem();
    		if(op==null)
    		{
    			notify.setFill(Color.RED);
    			notify.setText("In order to proceed you must choose " +ChoosingDishesController.chosenDish.getChoiceFactor());
    		}
    		
    		else
    		{
    			Dish temp=ChoosingDishesController.chosenDish;
    			addDishToMyOrder(op.toString(),event);
    			
    		}
    			
    	}
    	else
    	{
    		addDishToMyOrder(null,event);
    	}

    }
    private void addDishToMyOrder(String op,ActionEvent event) throws IOException
    {
    	
    	Dish temp=ChoosingDishesController.chosenDish;
    	Dish dish;
    	StringBuilder b;
		if(chosenExtra!=null)
		{
			b=new StringBuilder();
			for(String s:chosenExtra)
			{
				b.append(s+" ");	
			}
			dish=new Dish(temp.getDishName(),temp.getSupplierName(),temp.getChoiceFactor(),op,temp.getIngredients(),b.toString(),temp.getPrice(),temp.getDishType());

		}
		
		else 
		{
			 dish=new Dish(temp.getDishName(),temp.getSupplierName(),temp.getChoiceFactor(),op,temp.getIngredients(),null,temp.getPrice(),temp.getDishType());

		}
		dish.setQuentity(ChoosingDishesController.quentity);
		dish.setRestCode(RestListFormController.chosenRst.getRestCode());
		SingletonOrder.getInstance().myOrder.add(dish);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/MenuScreen.fxml"));
		Parent root=load.load();
		MenuScreenController aFrame = load.getController();
		//aFrame.display(RestListFormController.chosenRst.getSupplierName(),"The dish was successfully added to your order");
		aFrame.display("The dish was successfully added to your order");
		aFrame.start(primaryStage,root);
		
	}
    	
    

    @FXML
    void backToDishes(ActionEvent event) throws IOException 
    {
    	Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/DishesOfKindScreen.fxml"));
		Parent root=load.load();
		ChoosingDishesController aFrame = load.getController();
		aFrame.start(primaryStage, root);

    }

    @FXML
    void removeChange(ActionEvent event) 
    {
    	if(!chosenList.getItems().isEmpty())
    	{
        	String op=chosenList.getSelectionModel().getSelectedItem();
        	if(op!=null)
        	{
        		op=op.toString();
        		chosenList.getItems().remove(op);
        		chosenExtra.remove(op);
        		UnChosenExtra.add(op);
        		
        		for(String choiceType:optionsList.getItems())
        		{
        			if(UnChosenExtra.contains(choiceType))
        			{
        				UnChosenExtra.remove(choiceType);
        			}
        				
        		}
        		
        		observableListUnExtra=FXCollections.observableArrayList(UnChosenExtra);	
        		optionsList.getItems().removeAll();
        		optionsList.getItems().addAll(observableListUnExtra);
        		//optionsList.setItems(observableListUnExtra);
        	}
        	
        	else
        	{
        		notify.setFill(Color.RED);
        		notify.setText("Please choose at least one change to remove ");
        		
        		
        	}
        	
    	}
    	
    	else
    	{
    		notify.setFill(Color.RED);
    		notify.setText("There are no changes to remove");
    		
    	}



    }

    @FXML
    void selectChange(ActionEvent event) 
    {
    	if(!optionsList.getItems().isEmpty())
    	{
        	String op=optionsList.getSelectionModel().getSelectedItem();
        	if(op!=null)
        	{
        		op=op.toString();
        		optionsList.getItems().remove(op);
        		UnChosenExtra.remove(op);
        		chosenExtra.add(op);
        		observableListExtra=FXCollections.observableArrayList(chosenExtra);		
        		chosenList.setItems(observableListExtra);	
        	}
        	
        	else
        	{
        		notify.setFill(Color.RED);
        		notify.setText("Please choose at least one change ");
        		
        	}
    	}
    	
    	else {
    		notify.setFill(Color.RED);
    		notify.setText("There are no changes to choose ");
    	}


    }

    
  /*  public void setValue()
    {
		choiceLabel.setText(ChoosingDishesController.chosenDish.getChoiceFactor()+" :");

    }*/
	
	public void start(Stage stage, Parent root) {
		Scene scene = new Scene(root);		
		stage.setScene(scene);
		stage.show();
		
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
		if(!ChoosingDishesController.chosenDish.getChoiceFactor().equals(""))
		{
			String[] getChoices=ChoosingDishesController.chosenDish.getChoiceDetails().split("/");
			ObservableList <String>observableList1=FXCollections.observableArrayList(createList(getChoices));
			choice.setItems(observableList1);
		}
		
		if(!ChoosingDishesController.chosenDish.getExtra().equals(""))
		{
			String[] getChoices=ChoosingDishesController.chosenDish.getExtra().split("/");
			ObservableList <String>observableList2=FXCollections.observableArrayList(createList(getChoices));
			//optionsList.getItems().addAll(observableList2);
			optionsList.setItems(observableList2);

		}
	}
	private ArrayList<String> createList(String[] splitedData)
	{
		ArrayList<String> choices=new ArrayList<>();
		for(String ch:splitedData)
		{
			choices.add(ch);
		}
		
		return choices;
		
	}
	@Override
	public void display(String string) {
		choiceLabel.setText(ChoosingDishesController.chosenDish.getChoiceFactor()+" :");
		
	}

	
}
