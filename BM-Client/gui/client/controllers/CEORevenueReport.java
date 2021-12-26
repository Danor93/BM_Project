package client.controllers;

import java.io.IOException;

import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import main.ClientUI;

public class CEORevenueReport extends Controller implements ControllerInterface{

	@FXML
	private ImageView BackImage;

	@FXML
	private BarChart<?, ?> barChartReport;

	@FXML
	private Button btnBack;
	
	@FXML
	void initialize() {
		setImage(BackImage, "background.png");
	}

	@Override
	public void Back(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, null));
		startScreen(event, "CEOScreen", "CEO");
		
	}

}
