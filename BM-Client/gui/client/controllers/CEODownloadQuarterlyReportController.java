package client.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import Entities.Message;
import Entities.MessageType;
import Entities.MyFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.ClientUI;
import main.PopUpMessage;

/**
 * 
 * @author Lior
 *
 */
public class CEODownloadQuarterlyReportController implements Initializable {

	public static ArrayList<String> years;
	ObservableList<String> yearsObservableList;
	public static Stage stage;

	public static String selectedBranch, selectedQuarterly, selectedYear;
	public static Boolean branchAndQuarterlyAndYearFlaf = false;
	public static Boolean succesload = false;
	public StringBuilder branchAndQuarterlyAndYearString = new StringBuilder();
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		yearsObservableList = FXCollections.observableArrayList("2019", "2020", "2021");
		comboBoxYear.setItems(yearsObservableList);

		comboBoxYear.setDisable(true);
		btn01.setDisable(true);
		btn02.setDisable(true);
		btn03.setDisable(true);
		btn04.setDisable(true);
		btnDownload.setDisable(true);

	}

	public void start(Stage stage, Parent root) {
		Scene scene = new Scene(root);
		stage.setTitle("BiteMe Download Quarterly Report");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void selectBranch(ActionEvent event) {
		if (btnNorth.isSelected())
			selectedBranch = "north";
		else if (btnCenter.isSelected())
			selectedBranch = "center";
		else if (btnSouth.isSelected())
			selectedBranch = "south";

		btn01.setDisable(false);
		btn02.setDisable(false);
		btn03.setDisable(false);
		btn04.setDisable(false);

	}

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

		comboBoxYear.setDisable(false);

	}

	@FXML
	void selectYear(ActionEvent event) {
		selectedYear = comboBoxYear.getSelectionModel().getSelectedItem();

		branchAndQuarterlyAndYearString.append(selectedBranch);
		branchAndQuarterlyAndYearString.append("@");
		branchAndQuarterlyAndYearString.append(selectedQuarterly);
		branchAndQuarterlyAndYearString.append("@");
		branchAndQuarterlyAndYearString.append(selectedYear);

		btnDownload.setDisable(false);

	}

	@FXML
	void downloadReport(ActionEvent event) {

		ClientUI.chat
				.accept(new Message(MessageType.checkDownloadFileDetails, branchAndQuarterlyAndYearString.toString()));
		if (branchAndQuarterlyAndYearFlaf == true) {
			branchAndQuarterlyAndYearFlaf = false;

			try {

				FileChooser fileChooser = new FileChooser();
				// fileChooser.getExtensionFilters().addAll(new
				// FileChooser.ExtensionFilter("PDF", "*.pdf"));
				// fileChooser.setInitialFileName(downloadFileData.getFileName());
				fileChooser.setTitle("Save");
				File folder = fileChooser.showSaveDialog(CEODownloadQuarterlyReportController.stage);
				if (folder != null) {
					String folderPath = folder.getAbsolutePath();
					File downloadFile = new File(folderPath);
					int len = downloadFileData.getMybytearray().length;
					byte[] myByteArray = new byte[len];

					try {
						FileOutputStream fos = new FileOutputStream(downloadFile);
						BufferedOutputStream bos = new BufferedOutputStream(fos);

						bos.write(myByteArray, 0, len);
						bos.flush();
						fos.flush();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// PopUpMessage.errorMessage("there is a report for " + Year + " " + Quertar + "
			// already!");
		}

		System.out.println("branch:" + selectedBranch);
		System.out.println("yesr:" + selectedYear);
		System.out.println("quarterly:" + selectedQuarterly);

	}

}
