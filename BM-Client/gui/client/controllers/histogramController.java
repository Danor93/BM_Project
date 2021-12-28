package client.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class histogramController extends Controller implements Initializable {

    @FXML
    private ImageView BackImage;

    @FXML
    private Button back;

    @FXML
    private ImageView homePage;

    @FXML
    private NumberAxis ordersNum;

    @FXML
    private CategoryAxis rest;

    @FXML
    private Text userName;

    @FXML
    private BarChart<?, ?> chart;

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void backToHome(MouseEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}

}
