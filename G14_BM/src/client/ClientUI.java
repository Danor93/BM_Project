package client;
import javafx.application.Application;

import javafx.stage.Stage;


import java.util.Vector;
import gui.ShowUpdateController;
import client.ClientController;

public class ClientUI extends Application {

	//public static ClientController chat; //only one instance

	public static void main( String args[] ) throws Exception
	   { 
		    launch(args);  
	   } // end main
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		// chat= new ClientController("localhost", 5555);
		// TODO Auto-generated method stub
						  		
		ShowUpdateController firstPage = new ShowUpdateController (); // create StudentFrame
		 
		firstPage.start(primaryStage);
	}
	
	
}
