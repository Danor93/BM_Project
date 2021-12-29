package client.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.MyFile;
import Entities.homeBranches;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.ClientUI;
import main.PopUpMessage;
import javafx.fxml.Initializable;

public class BranchManagerUploadPDFController extends Controller implements Initializable {

	/*
	 * author:Danor
	 * this class is for the branch manager upload a quarterly pdf functionality */
	
	public static String Quertar;
	public static String Year;
	public static Stage stage;
	public static Boolean yearandqflag=false;
	public static Boolean succesUpload=false;
	public StringBuilder yearandQ=new StringBuilder();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button BackBtn;

	@FXML
	private ComboBox<String> QuertarComboBox;

	@FXML
	private Button UploadBtn;

	@FXML
	private ComboBox<String> YearComboBox;

	@FXML
	void Back(ActionEvent event) throws IOException {
		if (LoginScreenController.user.getRole().equals("CEO")) {
			startScreen(event, "CEOScreen", "CEO");
		}
		if (LoginScreenController.user.getRole().equals("BranchManager")) {
			startScreen(event, "BranchManagerScreen", "Branch Manager");
		}
	}

	@FXML
	void ChooseQuertar(ActionEvent event) {
		Quertar = QuertarComboBox.getSelectionModel().getSelectedItem();
		YearComboBox.getItems().add("2022");
		YearComboBox.getItems().add("2021");
		YearComboBox.setDisable(false);
	}

	@FXML
	void chooseYear(ActionEvent event) {
		Year = YearComboBox.getSelectionModel().getSelectedItem();
		yearandQ.append(Quertar);
		yearandQ.append("@");
		yearandQ.append(Year);
		UploadBtn.setDisable(false);
	}

	@FXML
	void UploadPDF(ActionEvent event) {
		ClientUI.chat.accept(new Message(MessageType.check_year_and_quertar, yearandQ.toString()));
		if(yearandqflag==true) {
			yearandqflag=false;
			try {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");
				fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
				File file = fileChooser.showOpenDialog(BranchManagerUploadPDFController.stage);
				if (file != null) {
					String path = file.getPath();
					File f = new File(path);
					MyFile msg = new MyFile(f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1));
					try {
						File newFile = new File(path);
						byte[] mybytearray = new byte[(int) newFile.length()];
						msg.initArray(mybytearray.length);
						msg.setSize(mybytearray.length);

						FileInputStream fis = new FileInputStream(newFile);
						BufferedInputStream bis = new BufferedInputStream(fis);

						bis.read(msg.getMybytearray(), 0, mybytearray.length);
						msg.setQuertar(Quertar);
						msg.setYear(Year);
						msg.setHomebranch((LoginScreenController.user.getHomeBranch()));
						ClientUI.chat.accept(new Message(MessageType.send_PDF, msg));
						if(succesUpload==true)
						{
							PopUpMessage.successMessage("Succes to upload the " + Year + "' " + Quertar + " PDF file!");
						}
						else {
							PopUpMessage.errorMessage("Could not upload " + Year + "' " + Quertar + " PDF file!,try again");
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			PopUpMessage.errorMessage("there is a report for " + Year + " " + Quertar + " already!");
		}	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		QuertarComboBox.getItems().add("1");
		QuertarComboBox.getItems().add("2");
		QuertarComboBox.getItems().add("3");
		QuertarComboBox.getItems().add("4");
		YearComboBox.setDisable(true);
		UploadBtn.setDisable(true);
	}
	

    @FXML
    void initialize() {
        assert BackBtn != null : "fx:id=\"BackBtn\" was not injected: check your FXML file 'BranchManagerUploadPDF.fxml'.";
        assert QuertarComboBox != null : "fx:id=\"QuertarComboBox\" was not injected: check your FXML file 'BranchManagerUploadPDF.fxml'.";
        assert UploadBtn != null : "fx:id=\"UploadBtn\" was not injected: check your FXML file 'BranchManagerUploadPDF.fxml'.";
        assert YearComboBox != null : "fx:id=\"YearComboBox\" was not injected: check your FXML file 'BranchManagerUploadPDF.fxml'.";

    }

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}

}
