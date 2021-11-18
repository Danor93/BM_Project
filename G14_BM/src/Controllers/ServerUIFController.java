package Controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import Server.ServerConnection;
import extra.ClientConnection;
import Server.EchoServer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ServerUIFController {
	public static ServerUIFController serveruifconroller;
	final public static int DEFAULT_PORT = 5555;
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<?> ClientTable;

	@FXML
	private TableColumn<ClientConnection, String> HostCol;

	@FXML
	private TableColumn<ClientConnection, String> IpCol;

	@FXML
	private TextArea ServerLogTxt;

	@FXML
	private TableColumn<ClientConnection, String> StatusCol;

	@FXML
	private Label Statuslbl;

	@FXML
	private Button btnClose;

	@FXML
	private Button connectBtn;

	@FXML
	private Button disconnectBtn;

	@FXML
	private Label ipLabel;

	@FXML
	private Button ClearLogBtn;

	@FXML
	void ConnectServer(ActionEvent event) {

		ServerConnection.startServer(null, this);
		Statuslbl.setText("ON");
		Statuslbl.setStyle("-fx-text-fill: green");
		addToTextArea("Server listening for connections on port: " + DEFAULT_PORT);

	}

	@FXML
	void StopServer(ActionEvent event) {
		ServerConnection.stopServer(this);
		Statuslbl.setText("OFF");
		Statuslbl.setStyle("-fx-text-fill: red");
		addToTextArea("Server has stopped listening for connections on port: " + DEFAULT_PORT);
		// clientTableConnection.getItems().clear();
		// clientTableConnection.refresh();
		// Query.logoutAllUsers();
	}
	
	/**This method add message to the log area*/
	public void addToTextArea(String msg) {
		String timeStamp = new SimpleDateFormat("[dd.MM.yyyy]  [HH:mm:ss]  ").format(Calendar.getInstance().getTime());
		Platform.runLater(() -> ServerLogTxt.appendText(timeStamp + msg + "\n"));
	}
	
	public void Close(ActionEvent event) {
		this.StopServer(event);
		Stage stage = (Stage) btnClose.getScene().getWindow();
	    stage.close();
	}
	
	  @FXML
	    void clearLog(ActionEvent event) {
		  ServerLogTxt.clear();
	    }
	  
	  public Label getLabelStatusServer() {
			return Statuslbl;
		}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		serveruifconroller = this;
		IpCol.setCellValueFactory(new PropertyValueFactory<ClientConnection, String>("ipAddress"));
		HostCol.setCellValueFactory(new PropertyValueFactory<ClientConnection, String>("hostName"));
		StatusCol.setCellValueFactory(new PropertyValueFactory<ClientConnection, String>("status"));

	}

}
