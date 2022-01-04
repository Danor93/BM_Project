package client.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.MyFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.ClientUI;

/**
 * 
 * @author Lior This class handles the functionality of the CEO to download a
 *         PDF of the branch quarterly report.
 *
 */
public class CEODownloadQuarterlyReportController implements Initializable {

	public static ArrayList<String> yearsAndQuarter;
	public ArrayList<String> years = new ArrayList<String>();
	ObservableList<String> yearsObservableList;
	public static Stage stage;

	public String selectedBranch, selectedQuarterly, selectedYear;
	public StringBuilder branchAndYearAndQuarterlyString = new StringBuilder();
	public static MyFile downloadFileData;

	@FXML
	private ToggleGroup quarterlyGroup;

	@FXML
	private ToggleButton btn01;

	@FXML
	private ToggleButton btn02;

	@FXML
	private ToggleButton btn03;

	@FXML
	private ToggleButton btn04;

	@FXML
	private ToggleGroup branchGroup;

	@FXML
	private ToggleButton btnCenter;

	@FXML
	private ToggleButton btnNorth;

	@FXML
	private ToggleButton btnSouth;

	@FXML
	private ComboBox<String> comboBoxYear;

	@FXML
	private Button btnDownload;

	/**
	 * Initial boot
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBoxYear.setDisable(true);
		btn01.setDisable(true);
		btn02.setDisable(true);
		btn03.setDisable(true);
		btn04.setDisable(true);
		btnDownload.setDisable(true);
	}

	/**
	 * show a new window
	 * @param stage
	 * @param root
	 */
	public void start(Stage stage, Parent root) {
		Scene scene = new Scene(root);
		stage.setTitle("BiteMe Download Quarterly Report");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Select a branch after selection the relevant choice of year and quarterly
	 * will be available
	 * 
	 * @param event click one of btnNorth or btnCenter or btnSouth
	 */
	@FXML
	void selectBranch(ActionEvent event) {
		if (btnNorth.isSelected())
			selectedBranch = "North";
		else if (btnCenter.isSelected())
			selectedBranch = "Center";
		else if (btnSouth.isSelected())
			selectedBranch = "South";

		years = new ArrayList<String>();
		btn01.setSelected(false);
		btn02.setSelected(false);
		btn03.setSelected(false);
		btn04.setSelected(false);
		
		btn01.setDisable(true);
		btn02.setDisable(true);
		btn03.setDisable(true);
		btn04.setDisable(true);
		btnDownload.setDisable(true);

		ClientUI.chat.accept(new Message(MessageType.showRelevantYearsAndQuarterly, selectedBranch));

		for (String year : yearsAndQuarter) {
			String[] divYandQ = year.split("@");
			if (!years.contains(divYandQ[0]))
				years.add(divYandQ[0]);
		}
		yearsObservableList = FXCollections.observableArrayList(years);
		comboBoxYear.setItems(yearsObservableList);
		comboBoxYear.setDisable(years.isEmpty());

	}

	/**
	 * chose a year from comboBox after selection the the relevant choice of
	 * quarterly will be available
	 * 
	 * @param event chose a year
	 */
	@FXML
	void selectYear(ActionEvent event) {
		
		btn01.setSelected(false);
		btn02.setSelected(false);
		btn03.setSelected(false);
		btn04.setSelected(false);
		
		btn01.setDisable(true);
		btn02.setDisable(true);
		btn03.setDisable(true);
		btn04.setDisable(true);
		btnDownload.setDisable(true);
		
		selectedYear = comboBoxYear.getSelectionModel().getSelectedItem();

		for (String year : yearsAndQuarter) {
			String[] divYandQ = year.split("@");
			if (divYandQ[0].equals(selectedYear)) {
				switch (divYandQ[1]) {
				case "1":
					btn01.setDisable(false);
					break;
				case "2":
					btn02.setDisable(false);
					break;
				case "3":
					btn03.setDisable(false);
					break;
				case "4":
					btn04.setDisable(false);
					break;
				}

			}
		}

	}

	/**
	 * Select a quarterly this method create a StringBuilder with the details
	 * 
	 * @param event click one of btn01 or btn02 or btn03 or btn04
	 */
	@FXML
	void selectQuarterly(ActionEvent event) {
		if (btn01.isSelected())
			selectedQuarterly = "1";
		else if (btn02.isSelected())
			selectedQuarterly = "2";
		else if (btn03.isSelected())
			selectedQuarterly = "3";
		else if (btn04.isSelected())
			selectedQuarterly = "4";

		branchAndYearAndQuarterlyString.append(selectedBranch);
		branchAndYearAndQuarterlyString.append("@");
		branchAndYearAndQuarterlyString.append(selectedYear);
		branchAndYearAndQuarterlyString.append("@");
		branchAndYearAndQuarterlyString.append(selectedQuarterly);

		btnDownload.setDisable(false);

	}

	/**
	 * this method sent the details to server, the client can choose where he want
	 * the file will save get the relevant file data in MyFile format make a PDF
	 * file from MyFile format and download to the chosen path
	 * 
	 * @param event click on btnDownload
	 */
	@FXML
	void downloadReport(ActionEvent event) {

		ClientUI.chat.accept(new Message(MessageType.downloadPDF, branchAndYearAndQuarterlyString.toString()));
		try {

			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
			fileChooser.setInitialFileName(downloadFileData.getFileName());
			fileChooser.setTitle("Save");
			File folder = fileChooser.showSaveDialog(CEODownloadQuarterlyReportController.stage);
			if (folder != null) {
				String folderPath = folder.getAbsolutePath();
				File downloadFile = new File(folderPath);
				int len = downloadFileData.mybytearray.length;
				byte[] myByteArray = new byte[len];
				myByteArray = downloadFileData.getMybytearray();
				try {
					FileOutputStream fos = new FileOutputStream(downloadFile);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					bos.write(myByteArray, 0, len);
					bos.flush();
					fos.flush();
					bos.close();
					((Node) event.getSource()).getScene().getWindow().hide();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
