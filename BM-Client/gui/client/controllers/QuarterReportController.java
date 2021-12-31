package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.ClientUI;

public class QuarterReportController extends Controller implements Initializable{

    @FXML
    private Label brachLbl;
    
    @FXML
    private Label quaterlyLbl;
    
    @FXML
    private Label yearlbl;

    @FXML
    private ImageView BackImage;

    @FXML
    private Button back;

    @FXML
    private CheckBox choose1;

    @FXML
    private CheckBox choose2;

    @FXML
    private ChoiceBox<String> chooseBranch1;

    @FXML
    private ChoiceBox<String> chooseBranch2;

    @FXML
    private ImageView homePage;

    @FXML
    private Button proceed;

    @FXML
    private ChoiceBox<String> quater1;

    @FXML
    private ChoiceBox<String> quater2;

    @FXML
    private Text userName;

    @FXML
    private ChoiceBox<String> year1;

    @FXML
    private ChoiceBox<String> year2;
    
    @FXML
    private Label notify;
    
    ObservableList<String> observableList1,observableList2,observableList3;
    
    public static Map<String,ArrayList<Float>> report1=null;
    public static Map<String,ArrayList<Float>> report2=null;
    public static ArrayList<String> years;
    
	/** This method meant to get back to login page and logout the customer
	 * @param event				pressing the "back" button 
	 * @throws IOException
	 */
    @FXML
    void back(ActionEvent event) throws IOException {
		start(event, "CEOScreen", "CEO Screen",LoginScreenController.user.getFirstN());
    }

	/** This method meant to get back to costumer page
	 * @param event				pressing the "home" image 
	 * @throws IOException
	 */
    @FXML
    void backToHome(MouseEvent event) throws IOException {
    	start(event, "CEOScreen", "CEO Screen","");
    }

    @FXML
    void proceed(ActionEvent event) throws IOException 
    {
       	StringBuilder b;
    	if(!choose2.isSelected()&&!choose1.isSelected())
    	{
    		notify.setText("Select how many reports you would like to view");
    	}
    	else
    	{
    		if(quater1.getSelectionModel().getSelectedItem()!=null&&chooseBranch1.getSelectionModel().getSelectedItem()!=null&&year1.getSelectionModel().getSelectedItem()!=null)
        	{
        		b=new StringBuilder();
        		b.append(quater1.getSelectionModel().getSelectedItem().toString());
        		b.append(",");
        		b.append(chooseBranch1.getSelectionModel().getSelectedItem().toString());
        		b.append(",");
        		b.append(year1.getSelectionModel().getSelectedItem().toString());
        		if(choose2.isSelected())
            	{
        			if(quater2.getSelectionModel().getSelectedItem()!=null&&chooseBranch2.getSelectionModel().getSelectedItem()!=null&&year2.getSelectionModel().getSelectedItem()!=null)
        			{
        				ClientUI.chat.accept(new Message(MessageType.ShowHistogram,b.toString()));	
        				if(report1!=null)
        				{
        					StringBuilder b1=new StringBuilder();
            				b1.append(quater2.getSelectionModel().getSelectedItem().toString());
            				b1.append(",");
            				b1.append(chooseBranch2.getSelectionModel().getSelectedItem().toString());
            				b1.append(",");
                    		b1.append(year2.getSelectionModel().getSelectedItem().toString());	
            				ClientUI.chat.accept(new Message(MessageType.ShowHistogram,b1.toString()));
            				if(report2!=null)
            				{
            					start(event,"histogram","Quarterly report","");
            				}
            				else
            				{
            					report1=null;
            					notify.setText("The second report does not exist");
            				}	
        				}
        				else
        				{
        					notify.setText("The first report does not exist");
        				}
        			}
        			else
        			{
        				notify.setText("Please fill all fields");
        			}
            	}
        		else
        		{
        			ClientUI.chat.accept(new Message(MessageType.ShowHistogram,b.toString()));
        			if(report1!=null)
        			{
    					start(event,"histogram","Quarterly report","");
        			}
        			else
        			{
        				notify.setText("This report does not exist");
        			}
        		}
        	}
        	else
        	{
        		notify.setText("Please fill all fields");
        	}
    	}
    }
    
    @FXML
    void choose1Rep(ActionEvent event) {
    	if(choose1.isSelected())
    	{
    		choose2.setSelected(false);
    		chooseBranch2.setVisible(false);
    		quater2.setVisible(false);
    		year2.setVisible(false);
    		brachLbl.setVisible(false);
    		quaterlyLbl.setVisible(false);
    		yearlbl.setVisible(false);
    	}
    	else
    	{
    		choose2.setSelected(true);
    		chooseBranch2.setVisible(true);
    		quater2.setVisible(true);
    		year2.setVisible(true);
    		brachLbl.setVisible(true);
    		quaterlyLbl.setVisible(true);
    		yearlbl.setVisible(true);
    	}
    }

    @FXML
    void choose2Rep(ActionEvent event) {
    	if(choose2.isSelected())
    	{
    		choose1.setSelected(false);
    		chooseBranch2.setVisible(true);
    		quater2.setVisible(true);
    		year2.setVisible(true);
    		brachLbl.setVisible(true);
    		quaterlyLbl.setVisible(true);
    		yearlbl.setVisible(true);	 
    	}
    	else
    	{
    		choose1.setSelected(true);
    		choose2.setSelected(false);
    		chooseBranch2.setVisible(false);
    		quater2.setVisible(false);
    		year2.setVisible(false);
    		brachLbl.setVisible(false);
    		quaterlyLbl.setVisible(false);
    		yearlbl.setVisible(false);
    	}
    }

	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN());		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		ClientUI.chat.accept(new Message(MessageType.getYears,null));
		ArrayList<String> branches=new ArrayList<>();
		ArrayList<String> quater=new ArrayList<>();
		branches.add("north");
		branches.add("center");
		branches.add("south");
		quater.add("1");
		quater.add("2");
		quater.add("3");
		quater.add("4");
		observableList1=FXCollections.observableArrayList(branches);
		observableList2=FXCollections.observableArrayList(quater);
		observableList3=FXCollections.observableArrayList(years);
		chooseBranch1.setItems(observableList1);
		chooseBranch2.setItems(observableList1);
		quater1.setItems(observableList2);
		quater2.setItems(observableList2);
		year1.setItems(observableList3);
		year2.setItems(observableList3);
	}
}
