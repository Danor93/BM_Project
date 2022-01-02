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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.ClientUI;

/**
 * 
 * @author Lior
 * this class is for the main screen of the CEO to choose which action he want.
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

	@FXML
	private Text WelcomeLabel;

	/**
	 * for back to login screen
	 */
	@Override
	public void Back(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
		start(event, "LoginScreen", "Login","");
	}

	/**
	 * for view the branch monthly reports
	 * @param event click on btnViewBranchsReports
	 * @throws IOException
	 */
	@FXML
	void ViewBranchManagerReport(ActionEvent event) throws IOException {
		start(event, "BranchManagerChooseReportToView", "View Report",LoginScreenController.user.getUserName());
	}

	/**
	 * for view revenue report, the next screen is choosing witch parameters
	 * @param event click on btnViewRevenueReport
	 * @throws IOException
	 */
	@FXML
	void revenueReport(ActionEvent event) throws IOException {
		start(event, "CEOChooseQReports", "Revenue Report",LoginScreenController.user.getUserName());
	}

	/**
	 * for download PDF from Database
	 * @param event click on btnDownloadQuarterlyReport
	 * @throws IOException
	 */
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

	/**
	 * display the name of user
	 */
	@Override
	public void display(String string) {
		WelcomeLabel.setText("Welcome, " + LoginScreenController.user.getFirstN() );
	}

}