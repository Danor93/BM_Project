package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Dish;
import Entities.Message;
import Entities.MessageType;
import Entities.SingletonOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.ClientUI;

/**
 * @author User
 *
 */
/**
 * @author User
 *
 */
public class DishOptionsController extends AbstractController implements Initializable{

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
    private ImageView homePage;

    @FXML
    private Button logout;
    

    @FXML
    private Text notify;

    
    private ArrayList<String> chosenExtra=new ArrayList<>();    
    ObservableList <String>observableListExtra; 
    
    private ArrayList<String> UnChosenExtra=new ArrayList<>();    
    ObservableList <String>observableListUnExtra; 
    
    

    /** This method meant to get back to costumer page
	 * @param event				pressing the "home" image 
	 * @throws IOException
	 */
    @FXML
    void backToHome(MouseEvent event) throws IOException {
    	if(SingletonOrder.getInstance()!=null)
    	{
    		SingletonOrder.getInstance().myOrder.clear();
    	}
    	start(event, "CustomerScreen", "CustomerScreen",LoginScreenController.user.getFirstN());    }
    
	/** This method meant to get back to login page and logout the customer
	 * @param event				pressing the "logout" button 
	 * @throws IOException
	 */
    @FXML
    void logout(ActionEvent event) throws IOException {
		SingletonOrder.getInstance().myOrder.clear();
    	ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login","");

    }

    
    /**This method meant to add the selected dish to the order with a choice factor(if it has one) and with it's choice details 
     * @param event               
     * @throws IOException
     */
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
    
    /**this method creates a dish with it's chosen details and places it in the dishes array list.
     *  it also gets us back to the menu screen with an appropriate label if the dish successfully added to the array list.
     * @param op                the choice factor that the customer chose
     * @param event             pressing the button "add to my order"
     * @throws IOException
     */
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
		start(event,"MenuScreen","Restaurant's menu","The dish was successfully added to your order");
		
	}
    	
    
	/** This method meant to get back to the dishes of a specific kind page
	 * @param event				pressing the "back" button 
	 * @throws IOException
	 */
    @FXML
    void backToDishes(ActionEvent event) throws IOException 
    {
		start(event, "DishesOfKindScreen", "Restaurant's starters", MenuScreenController.chosenFoodType);
    }

    
    /**This method meant to remove a choice detail that the customer has already chose,allowing him to drawback from his decision 
     * @param event      pressing the "<-" button
     */
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

    
    /**This method meant to add a choice detail that the customer chose from the given list 
     * @param event      pressing the "->" button
     */
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
			optionsList.setItems(observableList2);

		}
	}
	
	/**The method takes an array of choices that the supplier entered for the customer to choose and converts it to array list
	 * @param splitedData             array of strings with choices
	 * @return
	 */
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
		userName.setText(LoginScreenController.user.getFirstN());
		choiceLabel.setText(ChoosingDishesController.chosenDish.getChoiceFactor()+" :");
		
	}

	
}
