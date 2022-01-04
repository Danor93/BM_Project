package client.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class BeforLoginScreenController extends Controller{

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
}
