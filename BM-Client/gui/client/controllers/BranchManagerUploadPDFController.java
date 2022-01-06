package client.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.MyFile;
import Interfaces.IUploadPDFileInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.ClientUI;
import main.PopUpMessage;

/**
 * @author Danor
 * this class handles the functionality of the Branch Manager to upload a PDF of the quarterly report.
 */
public class BranchManagerUploadPDFController extends AbstractController implements Initializable {
	public static String Quertar;
	public static String Year;
	public static Boolean yearandqflag=false;
	public static Boolean succesUpload=false;
	public IUploadPDFileInterface IUpload;
	public StringBuilder yearandQ=new StringBuilder();
	
	public BranchManagerUploadPDFController() {
		IUpload=new UploadPDFileInterface();
	}
	
	public BranchManagerUploadPDFController(IUploadPDFileInterface IUPI) {
		this.IUpload=IUPI;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> QuertarComboBox;

	@FXML
	private Button UploadBtn;

	@FXML
	private ComboBox<String> YearComboBox;
	
    @FXML
    private ImageView homePage;

    @FXML
    private Button logout;

    @FXML
    private Text userName;
    
    public class UploadPDFileInterface implements IUploadPDFileInterface{

    	public UploadPDFileInterface() {
    		
    	}
    	
    	public IUploadPDFileInterface getInterface() {
			return this;
		}
    	
		@Override
		public boolean UploadPDF(ActionEvent event) throws Exception {
			ClientUI.chat.accept(new Message(MessageType.check_year_and_quertar, yearandQ.toString()));
			if(yearandqflag) {
				yearandqflag=false;
				try {
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Open Resource File");
					fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
					Stage stage = new Stage();
					File file = fileChooser.showOpenDialog(stage);
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
							msg.setQuarter(Quertar);
							msg.setYear(Year);
							msg.setHomebranch((LoginScreenController.user.getHomeBranch()));
							Timestamp date = new java.sql.Timestamp(new Date().getTime());
							msg.setDate(date.toString());
							bis.close();
							ClientUI.chat.accept(new Message(MessageType.send_PDF, msg));
							if(succesUpload)
							{
								PopUpMessage.successMessage("Succes to upload the " + Year + "' " + Quertar + " PDF file!");
								return true;
							}
							else {
								PopUpMessage.errorMessage("Could not upload " + Year + "' " + Quertar + " PDF file!,try again");
								return false;
							}
						} catch (Exception e) {
							e.printStackTrace();
							return false;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			else {
				PopUpMessage.errorMessage("there is a report for " + Year + " " + Quertar + " already!");
				return false;
			}
			return false;
		}
    }
    

	/**
	 * @param event - back to the home screen of the Branch Manager
	 */
	@FXML
	void backToHome(MouseEvent event) throws IOException {
		start(event, "BranchManagerScreen", "Branch Manager",LoginScreenController.user.getFirstN());
	}

	/**
	 * @param event - logout the user.
	 */
	@FXML
	void logout(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
		start(event,"LoginScreen", "Login Screen","");
	}

	/**
	 * for choosing a Quarter
	 * @param event - for the combo box.
	 */
	@FXML
	void ChooseQuertar(ActionEvent event) {
		Quertar = QuertarComboBox.getSelectionModel().getSelectedItem().toString();
		YearComboBox.getItems().add("2022");
		YearComboBox.getItems().add("2021");
		YearComboBox.setDisable(false);
	}

	/**
	 * for choosing a Year
	 * @param event - for the combo box.
	 */
	@FXML
	void chooseYear(ActionEvent event) {
		Year = YearComboBox.getSelectionModel().getSelectedItem().toString();
		yearandQ.append(Quertar);
		yearandQ.append("@");
		yearandQ.append(Year);
		UploadBtn.setDisable(false);
	}

	/**
	 * this method handles the upload the PDF file and send it to the DB.
	 * @param event - for the Upload PDF button.
	 * @throws Exception 
	 */
	@FXML
	void UploadPDF(ActionEvent event) throws Exception {
		IUpload.UploadPDF(event);
	}

	/**
	 * initialize the combo box and the button functionality and style.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		QuertarComboBox.getItems().add("1");
		QuertarComboBox.getItems().add("2");
		QuertarComboBox.getItems().add("3");
		QuertarComboBox.getItems().add("4");
		YearComboBox.setDisable(true);
		UploadBtn.setDisable(true);
		UploadBtn.getStylesheets().add("/css/buttons.css");
		logout.getStylesheets().add("/css/buttons.css");
	}

	/**
	 * display the name of the user.
	 */
    @Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN() + " " + LoginScreenController.user.getLastN());
	}

}
