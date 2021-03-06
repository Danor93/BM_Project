package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import extra.ClientConnection;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ServerConnection;
import querys.DBConnect;
import querys.Query;

public class ServerUIFController {
	public static ServerUIFController serveruifconroller;
	final public static int DEFAULT_PORT = 5555;
	public Stage stage;

	public static boolean flagon = false;
	Alert a = new Alert(AlertType.ERROR);

	@FXML
	private URL location;

	@FXML
	private TableView<ClientConnection> ClientTable;

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
	private TextField usertxt;

	@FXML
    private PasswordField Passtxt;

	@FXML
	private Button ImportBtn;

	@FXML
	void importDatabase(ActionEvent event) {
		Query.importData();
	}

	public void start(Stage primaryStage) throws IOException {
		FXMLLoader load = new FXMLLoader();
		primaryStage.setTitle("BiteMe Server Panel");
		Pane root = load.load(getClass().getResource("/fxml/ServerUIF.fxml").openStream());
		Scene home = new Scene(root);
		primaryStage.setScene(home);
		primaryStage.show();
	}

	@SuppressWarnings("unused")
	@FXML
	void ConnectServer(ActionEvent event) {
		ServerConnection.startServer(null, this);
		String username, password;
		if (Passtxt.getText().isEmpty()) {
			addToTextArea("You must fill the password box!");
		} else {
			username = "root";
			password = Passtxt.getText().toString();
			Connection connection = DBConnect.connect(username, password);
			if (flagon) {
				Statuslbl.setText("ON");
				Statuslbl.setStyle("-fx-text-fill: green");
				addToTextArea("Server listening for connections on port: " + DEFAULT_PORT);
				ClientTable.refresh();
			}
		}
	}

	@FXML
	void StopServer(ActionEvent event) {
		ServerConnection.stopServer(this);
		Statuslbl.setText("OFF");
		Statuslbl.setStyle("-fx-text-fill: red");
		addToTextArea("Server has stopped listening for connections on port: " + DEFAULT_PORT);
		ClientTable.getItems().clear();
		ClientTable.refresh();
		flagon = false;
	}

	/** This method add message to the log area */
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

	/** This method will update the table */
	/**
	 * @param client - Array list of clients who connected to BiteMe.
	 */
	public void Update(ArrayList<ClientConnection> client) {
		addToTextArea("New connection: " + client);
		ObservableList<ClientConnection> data = FXCollections.observableArrayList(client);
		ClientTable.setItems(data);
		ClientTable.refresh();
	}

	public void message(String msg, String title) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		serveruifconroller = this;
		IpCol.setCellValueFactory(new PropertyValueFactory<ClientConnection, String>("ipAddress"));
		HostCol.setCellValueFactory(new PropertyValueFactory<ClientConnection, String>("hostName"));
		StatusCol.setCellValueFactory(new PropertyValueFactory<ClientConnection, String>("status"));
	}
}