package client;
import javafx.application.Application;

import javafx.stage.Stage;


import java.util.Vector;

import gui.ClientMenuUiController;
import gui.ShowUpdateController;
import client.ClientController;

public class ClientUI extends Application {
	
	public static Stage mainStage;

	//public static ClientController chat;

	public static ClientController chat; 

	public static void main( String args[] ) throws Exception
	   { 
		    launch(args);  
	   } // end main 
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		mainStage=primaryStage;	  		
		ClientMenuUiController firstPage = new ClientMenuUiController (); 
		firstPage.start(mainStage);
	}
	
	
}
