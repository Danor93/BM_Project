package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.BusinessAccountTracking;
import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import Entities.Restaurant;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import main.ClientUI;
import Entities.RevenueReport;

public class BranchManagerChooseReportToViewController extends Controller implements Initializable {

	private static BranchManagerChooseReportToViewController instance = new BranchManagerChooseReportToViewController();
	public static String Branch, reportType, year;
	public static int month;
	public static StringBuilder details = new StringBuilder();
	public static RevenueReport revenueReport;
	
	public class RelavantData {

		private String Restaurant;
		private Integer NumberOfOrders;
		private Float Income;

		RelavantData(Restaurant rest) {
			float sum = 0;
			int numberoforders = 0;
			this.Restaurant = rest.getSupplierName();
			ArrayList<Order> Orders = BranchManagerChooseReportToViewController.revenueReport.getOrgizedData()
					.get(rest.getSupplierName());
			for (Order o : Orders) {

				if (o.getRestId().equals(rest.getRestCode())) {
					sum += o.getTotalPrice();
					numberoforders++;
				}
			}
			NumberOfOrders = numberoforders;
			Income = sum;

		}

		public String getRestaurant() {
			return this.Restaurant;
		}

		public Integer getNumberOfOrders() {
			return this.NumberOfOrders;
		}

		public Float getIncome() {
			return Income;
		}

		public String toString() {
			return "The Rstaurat is: " + Restaurant + " the number of orders is: " + String.valueOf(NumberOfOrders)
					+ " Total income is: " + Income.toString();
		}
	}

	ObservableList<RelavantData> revenueReportList;
	private ArrayList<RelavantData> RevenueData = new ArrayList<>();
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView BackImage;

	@FXML
	private ComboBox<String> BranchChoose;

	@FXML
	private Button GetReport;

	@FXML
	private ComboBox<Integer> Month;

	@FXML
	private Pane Orders;

	@FXML
	private TableView<String> PerformanceTable;

	@FXML
	private TableView<RelavantData> RevenueTable;

	@FXML
	private TableColumn<RelavantData, Float> Income;

	@FXML
	private TableColumn<RelavantData, Integer> NumberOfOrders;

	@FXML
	private TableColumn<RelavantData, String> Restaurant;

	@FXML
	private StackPane ReportPane;

	@FXML
	private ComboBox<String> ReportType;

	@FXML
	private Pane Revenue;

	@FXML
	private Pane main;

	@FXML
	private ComboBox<String> Year;

	@FXML
	private Button btnBack;

	@FXML
	private TableView<String> OrdersTable;

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
	void ChooseBranch(ActionEvent event) {
		Branch = BranchChoose.getSelectionModel().getSelectedItem();
		ReportType.setDisable(false);
	}

	@FXML
	void ChooseMonth(ActionEvent event) {
		month = Month.getSelectionModel().getSelectedItem();
		Year.setDisable(false);
	}

	@FXML
	void ChooseReportType(ActionEvent event) {
		reportType = ReportType.getSelectionModel().getSelectedItem();
		Month.setDisable(false);
	}

	@FXML
	void ChooseYear(ActionEvent event) {
		year = Year.getSelectionModel().getSelectedItem();
		GetReport.setDisable(false);
	}

	@FXML
	void getReport(ActionEvent event) {
		details.append(Branch);
		details.append("@");
		details.append(month);
		details.append("@");
		details.append(year);
		switch (reportType) {

		case "Revenue": {
			Revenue.toFront();
			RevenueTable.setVisible(true);
			RevenueReport();
		}

		case "Orders": {
			OrdersReport();
		}

		case "Performance": {
			PerformanceReport();
		}

		}
	}

	public void RevenueReport() {
		ClientUI.chat.accept(new Message(MessageType.get_Revenue_report, details.toString()));
		RevenueData = getRelavantDataArray();
		Restaurant.setCellValueFactory(new PropertyValueFactory<RelavantData, String>("Restaurant"));
		NumberOfOrders.setCellValueFactory(new PropertyValueFactory<RelavantData, Integer>("NumberOfOrders"));
		Income.setCellValueFactory(new PropertyValueFactory<RelavantData, Float>("Income"));
		revenueReportList = FXCollections.observableArrayList(RevenueData);
		RevenueTable.getItems().addAll(revenueReportList);
		RevenueTable.refresh();
	}

	ArrayList<RelavantData> getRelavantDataArray() {
		ArrayList<RelavantData> relevantDataArray = new ArrayList<>();
		for (Restaurant rest : revenueReport.getRestaurant()) {
			RelavantData r = new RelavantData(rest);
			System.out.println(r.toString());
			relevantDataArray.add(r);
		}

		return relevantDataArray;
	}

	public void OrdersReport() {
		ClientUI.chat.accept(new Message(MessageType.get_Orders_report, details.toString()));
	}

	public void PerformanceReport() {
		ClientUI.chat.accept(new Message(MessageType.get_Performance_report, details.toString()));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (LoginScreenController.user.getRole().equals("CEO")) {
			btnBack.setText("Back to CEO Screen");
			BranchChoose.getItems().add("North");
			BranchChoose.getItems().add("Center");
			BranchChoose.getItems().add("South");
			ReportType.setDisable(true);
			Month.setDisable(true);
			Year.setDisable(true);
			GetReport.setDisable(true);
		}
		if (LoginScreenController.user.getRole().equals("BranchManager")) {
			btnBack.setText("Back to Branch Manager Screen");
			BranchChoose.setDisable(true);
			Month.setDisable(true);
			Year.setDisable(true);
			GetReport.setDisable(true);
			Branch = LoginScreenController.user.getHomeBranch().toString();
		}
		main.toFront();
		ReportType.getItems().add("Revenue");
		ReportType.getItems().add("Orders");
		ReportType.getItems().add("Performance");
		for (int i = 1; i <= 12; i++) {
			Month.getItems().add(i);
		}
		Year.getItems().add("2021");
		Year.getItems().add("2020");
		Year.getItems().add("2019");
	}

	public static BranchManagerChooseReportToViewController getInstance() {
		return instance;
	}

	@FXML
	void initialize() {
		assert BackImage != null
				: "fx:id=\"BackImage\" was not injected: check your FXML file 'BranchManagerChooseReportToView.fxml'.";
		assert BranchChoose != null
				: "fx:id=\"BranchChoose\" was not injected: check your FXML file 'BranchManagerChooseReportToView.fxml'.";
		assert GetReport != null
				: "fx:id=\"GetReport\" was not injected: check your FXML file 'BranchManagerChooseReportToView.fxml'.";
		assert Month != null
				: "fx:id=\"Month\" was not injected: check your FXML file 'BranchManagerChooseReportToView.fxml'.";
		assert Orders != null
				: "fx:id=\"Orders\" was not injected: check your FXML file 'BranchManagerChooseReportToView.fxml'.";
		assert PerformanceTable != null
				: "fx:id=\"PerformanceTable\" was not injected: check your FXML file 'BranchManagerChooseReportToView.fxml'.";
		assert ReportPane != null
				: "fx:id=\"ReportPane\" was not injected: check your FXML file 'BranchManagerChooseReportToView.fxml'.";
		assert ReportType != null
				: "fx:id=\"ReportType\" was not injected: check your FXML file 'BranchManagerChooseReportToView.fxml'.";
		assert Revenue != null
				: "fx:id=\"Revenue\" was not injected: check your FXML file 'BranchManagerChooseReportToView.fxml'.";
		assert RevenueTable != null
				: "fx:id=\"RevenueTable\" was not injected: check your FXML file 'BranchManagerChooseReportToView.fxml'.";
		assert Year != null
				: "fx:id=\"Year\" was not injected: check your FXML file 'BranchManagerChooseReportToView.fxml'.";
		assert btnBack != null
				: "fx:id=\"btnBack\" was not injected: check your FXML file 'BranchManagerChooseReportToView.fxml'.";
		assert main != null
				: "fx:id=\"main\" was not injected: check your FXML file 'BranchManagerChooseReportToView.fxml'.";

	}
}
