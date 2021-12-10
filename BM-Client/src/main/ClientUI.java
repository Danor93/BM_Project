package main;
import javafx.application.Application;
import javafx.stage.Stage;
import client.controllers.ClientMenuUiController;

public class ClientUI extends Application {
	
	public static Stage mainStage;

	public static ClientController chat; 

	public static void main( String args[] ) throws Exception
	   { 
		    launch(args);  
	   }  
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		mainStage=primaryStage;	  		
		ClientMenuUiController firstPage = new ClientMenuUiController (); 
		firstPage.start(mainStage);
	}	
}