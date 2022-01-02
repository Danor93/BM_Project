package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.RevenueReport;
import Entities.PerformanceReport;
import Entities.homeBranches;
import Entities.OrdersReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import main.ClientUI;

/**
 * @author Danor
 * this class  implements the functionality of the Branch Manager to view reports of Revenues, Orders And Performance. 
 */
public class BranchManagerChooseReportToViewController extends Controller implements Initializable {

	public String Branch, Type, year, month;
	public static ArrayList<RevenueReport> revenueArray = new ArrayList<>();
	public static ArrayList<OrdersReport> ordersArray = new ArrayList<>();
	public static ArrayList<PerformanceReport> performanceArray = new ArrayList<>();
	ObservableList<RevenueReport> RevenueList;
	ObservableList<OrdersReport> OrderList;
	ObservableList<PerformanceReport> PerformanceList;

	@FXML
	private ImageView BackImage;

	@FXML
	private ComboBox<String> BranchChoose;

	@FXML
	private Button GetReport;

	@FXML
	private ComboBox<Integer> Month;

	@FXML
	private TableColumn<OrdersReport, String> OrdDishTypeCol;

	@FXML
	private TableColumn<OrdersReport, Integer> OrdQuantityCol;

	@FXML
	private TableColumn<OrdersReport, String> OrdRestCol;

	@FXML
	private Pane Orders;

	@FXML
	private StackPane ReportPane;

	@FXML
	private ComboBox<String> ReportType;

	@FXML
	private TableColumn<RevenueReport, Float> RevIncomeCol;

	@FXML
	private TableColumn<RevenueReport, Integer> RevNumOforCol;

	@FXML
	private TableColumn<RevenueReport, String> RevResCol;

	@FXML
	private Pane Revenue;

	@FXML
	private TableView<RevenueReport> RevenueTable;

	@FXML
	private ComboBox<String> Year;

	@FXML
	private Pane main;

	@FXML
	private TableView<OrdersReport> orders;
	
	@FXML
	private ImageView homePage;

	@FXML
	private Button logout;

	@FXML
	private Text userName;
	
	@FXML
    private Pane preformancePane;
	
    @FXML
    private TableView<PerformanceReport> performanceTable;

    @FXML
    private TableColumn<PerformanceReport, String> perfRestNameCol;

    @FXML
    private TableColumn<PerformanceReport, Integer> perfTotalOrdersCol;

    @FXML
    private TableColumn<PerformanceReport, Integer> perfOnTimeCol;

    @FXML
    private TableColumn<PerformanceReport, Integer> perfAreLateCol;

    @FXML
    private TableColumn<PerformanceReport, Double> perfLateRateCol;

    @FXML
    private TableColumn<PerformanceReport, Double> perfAvgCol;
	
	
	/**
	 * @param event - back to the home screen of the Branch Manager/CEO.
	 */
	@FXML
	void backToHome(MouseEvent event) throws IOException {
		if (LoginScreenController.user.getRole().equals("CEO")) {
			start(event, "CEOScreen", "CEO",LoginScreenController.user.getFirstN());
		}
		if (LoginScreenController.user.getRole().equals("BranchManager")) {
			start(event, "BranchManagerScreen", "Branch Manager",LoginScreenController.user.getFirstN());
		}
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
	 * choosing branch from the combo box (CEO Only).
	 * @param event - for the combo box.
	 */
	@FXML
	void ChooseBranch(ActionEvent event) {
		Branch = BranchChoose.getSelectionModel().getSelectedItem();
		ReportType.setDisable(false);
	}

	/**
	 * choosing Month from the combo box
	 * @param event - for the combo box.
	 */
	@FXML
	void ChooseMonth(ActionEvent event) {
		month = String.valueOf(Month.getSelectionModel().getSelectedItem());
		Year.setDisable(false);
	}

	/**
	 * @param event - for the combo box.
	 */
	@FXML
	void ChooseReportType(ActionEvent event) {
		Month.setDisable(false);
	}

	/**
	 * choosing Year from the combo box.
	 * @param event - for the combo box.
	 */
	@FXML
	void ChooseYear(ActionEvent event) {
		year = Year.getSelectionModel().getSelectedItem().toString();
		GetReport.setDisable(false);
	}

	/**
	 * show the user the report he wanted by branch,year,month.
	 * @param event - for the Get Report button.
	 */
	@FXML
	void getReport(ActionEvent event) {
		year = Year.getSelectionModel().getSelectedItem().toString();
		month =Month.getSelectionModel().getSelectedItem().toString();
		Branch =homeBranches.BranchToString(LoginScreenController.user.getHomeBranch());
		StringBuilder details = new StringBuilder();
		details.append(Branch);
		details.append("@");
		details.append(month);
		details.append("@");
		details.append(year);
		if (ReportType.getSelectionModel().getSelectedItem() != null) {
			switch (ReportType.getSelectionModel().getSelectedItem().toString()) {
			
			case "Revenue": {	
				/*orders.setVisible(false);
				performanceTable.setVisible(false);*/
				RevenueReport(details);
				break;
			}

			case "Orders": {	
				/*RevenueTable.setVisible(false);
				performanceTable.setVisible(false);*/
				OrdersReport(details);
				break;
			}

			case "Performance": {
				/*orders.setVisible(false);
				RevenueTable.setVisible(false);*/
				PerformanceReport(details);
				break;
			}
			}
		} else {
			System.out.println("label ERROR!");
		}
	}

	/**
	 * a method for handle the Revenue table and show it in the screen.
	 * @param details - details of the Branch,Year,Month.
	 */
	public void RevenueReport(StringBuilder details) {
		RevenueTable.setVisible(true);
		Revenue.toFront();
		RevResCol.setCellValueFactory(new PropertyValueFactory<RevenueReport, String>("resName"));
		RevNumOforCol.setCellValueFactory(new PropertyValueFactory<RevenueReport, Integer>("ordersamount"));
		RevIncomeCol.setCellValueFactory(new PropertyValueFactory<RevenueReport, Float>("Income"));
		ClientUI.chat.accept(new Message(MessageType.get_Revenue_report, details.toString()));
		RevenueList = FXCollections.observableArrayList(revenueArray);
		RevenueTable.setItems(RevenueList);
	}

	/**
	 * a method for handle the Orders table and show it in the screen.
	 * @param details - details of the Branch,Year,Month.
	 */
	public void OrdersReport(StringBuilder details) {
		orders.setVisible(true);
		Orders.toFront();
		OrdDishTypeCol.setCellValueFactory(new PropertyValueFactory<OrdersReport, String>("DishType"));
		OrdQuantityCol.setCellValueFactory(new PropertyValueFactory<OrdersReport, Integer>("Quantity"));
		OrdRestCol.setCellValueFactory(new PropertyValueFactory<OrdersReport, String>("ResName"));
		ClientUI.chat.accept(new Message(MessageType.get_Orders_report, details.toString()));
		OrderList = FXCollections.observableArrayList(ordersArray);
		orders.setItems(OrderList);
	}

	public void PerformanceReport(StringBuilder details) {
		performanceTable.setVisible(true);
		preformancePane.toFront();
		perfRestNameCol.setCellValueFactory(new PropertyValueFactory<PerformanceReport,String>("ResName"));
		perfTotalOrdersCol.setCellValueFactory(new PropertyValueFactory<PerformanceReport,Integer>("total_orders"));
		perfOnTimeCol.setCellValueFactory(new PropertyValueFactory<PerformanceReport,Integer>("onTime"));
		perfAreLateCol.setCellValueFactory(new PropertyValueFactory<PerformanceReport,Integer>("areLate"));
		perfLateRateCol.setCellValueFactory(new PropertyValueFactory<PerformanceReport,Double>("lateRate"));
		perfAvgCol.setCellValueFactory(new PropertyValueFactory<PerformanceReport,Double>("AvarageCookingTime"));
		ClientUI.chat.accept(new Message(MessageType.get_Performance_report, details.toString()));
		PerformanceList = FXCollections.observableArrayList(performanceArray);
		performanceTable.setItems(PerformanceList);
	}

	/**
	 * initialize the screen buttons and combo box.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		main.toFront();
		ReportType.getItems().add("Revenue");
		ReportType.getItems().add("Orders");
		ReportType.getItems().add("Performance");
		for (int i = 1; i <= 12; i++) {
			Month.getItems().add(i);
		}
		Year.getItems().add("2022");
		Year.getItems().add("2021");

		if (LoginScreenController.user.getRole().equals("CEO")) {
			BranchChoose.getItems().add("North");
			BranchChoose.getItems().add("Center");
			BranchChoose.getItems().add("South");
			ReportType.setDisable(true);
			Month.setDisable(true);
			Year.setDisable(true);
			GetReport.setDisable(true);
		}
		if (LoginScreenController.user.getRole().equals("BranchManager")) {
			BranchChoose.setDisable(true);
			Month.setDisable(true);
			Year.setDisable(true);
			Branch = LoginScreenController.user.getHomeBranch().toString();
		}
		GetReport.getStylesheets().add("/css/buttons.css");
		logout.getStylesheets().add("/css/buttons.css");
		RevenueTable.getStylesheets().add("/css/tableview.css");
		orders.getStylesheets().add("/css/tableview.css");
	}

	/**
	 * display the name of the user.
	 */
	@Override
	public void display(String string) {
		userName.setText(LoginScreenController.user.getFirstN() + " " + LoginScreenController.user.getLastN());
	}
}