package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
    private BarChart<String, Float> chart1;

    @FXML
    private BarChart<String, Float> chart2;

    @FXML
    private ImageView homePage;

    @FXML
    private Text userName;

    @FXML
    private CategoryAxis xRest1;

    @FXML
    private CategoryAxis xRest2;

    @FXML
    private NumberAxis yIncome1;

    @FXML
    private NumberAxis yIncome2;

    @FXML
    void back(ActionEvent event) throws IOException {
    	QuarterReportController.report1=null;
    	QuarterReportController.report2=null;
    	start(event,"CEOChooseQReports", "Choose Quarter Report",LoginScreenController.user.getFirstN());
    }

    @FXML
    void backToHome(MouseEvent event) throws IOException {
    	start(event,"CEOScreen", "CEO", LoginScreenController.user.getFirstN());
    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		XYChart.Series set1=new XYChart.Series<>();
		XYChart.Series set2=new XYChart.Series<>();
		
		for(String rest: QuarterReportController.report1.keySet())
		{
			String data= rest+"\n"+QuarterReportController.report1.get(rest).get(0)+" Orders";
			set1.getData().add(new XYChart.Data(data,QuarterReportController.report1.get(rest).get(1)));
			
		}
		if(QuarterReportController.report2!=null)
		{
			for(String rest: QuarterReportController.report2.keySet())
			{
				String data= rest+"\n"+QuarterReportController.report2.get(rest).get(0)+" Orders";
				set2.getData().add(new XYChart.Data(data,QuarterReportController.report2.get(rest).get(1)));
				
			}
			chart2.getData().addAll(set2);
			
			chart2.setVisible(true);
		
			
			
		}
		chart1.getData().addAll(set1);
		
		
	}

	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN());
	}

}
