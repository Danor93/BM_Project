package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientUI;

/**
 * 
 * @author Lior
 *
 */

public class CEOScreenController extends Controller implements ControllerInterface, Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnDownloadQuarterlyReport;

	@FXML
	private Button btnViewBranchsReports;

	@FXML
	private Button btnViewRevenueReport;

	@FXML
	private Button btnLogout;

	@FXML
	private ImageView BackImage;

	@Override
	public void Back(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, null));
		startScreen(event, "LoginScreen", "Login");
	}

	@FXML
	void ViewBranchManagerReport(ActionEvent event) throws IOException {
		startScreen(event, "BranchManagerChooseReportToView", "View Report");
	}
	
	@FXML
	void revenueReport(ActionEvent event) throws IOException {
		startScreen(event, "BarChartRevenueReport", "Revenue Report");
	}

	@FXML
	void downloadQuarterlyReport(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/CEODownloadQuarterlyReport.fxml"));
		Parent root = load.load();
		CEODownloadQuarterlyReportController aFrame = load.getController();
		aFrame.start(stage, root);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}