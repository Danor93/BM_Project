package client.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class BeforLoginScreenController extends Controller implements ControllerInterface{

    @FXML
    private ImageView BackImage;

    @FXML
    private Button btnEnter;

    @FXML
    void Enter(ActionEvent event) throws IOException {
    	start(event,"LoginScreen","Login","");

    }

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Back(ActionEvent event) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
