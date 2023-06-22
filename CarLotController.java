package com.example.carlotapp;

import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import java.util.HashMap;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class CarLotController implements Initializable {

    @FXML
    private TableView<Car> carTable;

    @FXML
    private TableColumn<Car, Integer> carIDColumn;

    @FXML
    private TableColumn<Car, Integer> modelYearColumn;

    @FXML
    private TableColumn<Car, String> makeColumn;

    @FXML
    private TableColumn<Car, String> modelColumn;

    @FXML
    private TableColumn<Car, Integer> priceColumn;

    @FXML
    private TableColumn<Car, String> dateSoldColumn;

    @FXML
    private ComboBox<Integer> yearComboBox;

    @FXML
    private Label totalCarsLabel;

    @FXML
    private Label totalSalesLabel;

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis manufacturerAxis;

    @FXML
    private NumberAxis carCountAxis;

    private CarDAO carDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carDAO = new CarDAO();

        setupTableColumns();
        populateTable();
        populateComboBox();
        updateStats();
        updateBarChart();
    }

    private void setupTableColumns() {
        carIDColumn.setCellValueFactory(new PropertyValueFactory<>("carID"));
        modelYearColumn.setCellValueFactory(new PropertyValueFactory<>("modelYear"));
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateSoldColumn.setCellValueFactory(new PropertyValueFactory<>("dateSold"));
    }

    private void populateTable() {
        List<Car> cars = carDAO.getAllCars();
        carTable.getItems().addAll(cars);
    }

    private void populateComboBox() {
        List<Integer> years = carDAO.getYears();
        yearComboBox.getItems().addAll(years);
    }

    private void updateStats() {
        List<Car> cars = carTable.getItems();
        int totalCars = cars.size();
        int totalSales = cars.stream().mapToInt(Car::getPrice).sum();

        totalCarsLabel.setText(String.valueOf(totalCars));
        totalSalesLabel.setText("$" + totalSales);
    }

    private void updateBarChart() {
        List<Car> cars = carTable.getItems();
        Map<String, Integer> manufacturerCounts = new HashMap<>();

        for (Car car : cars) {
            String manufacturer = car.getMake();
            manufacturerCounts.put(manufacturer, manufacturerCounts.getOrDefault(manufacturer, 0) + 1);
        }

        barChart.getData().clear();
        manufacturerAxis.getCategories().clear();

        for (String manufacturer : manufacturerCounts.keySet()) {
            manufacturerAxis.getCategories().add(manufacturer);
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> entry : manufacturerCounts.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        barChart.getData().add(series);


    }

    @FXML
    private void handleYearSelection() {
        int selectedYear = yearComboBox.getValue();
        List<Car> carsByYear = carDAO.getCarsByYear(selectedYear);
        carTable.getItems().clear();
        carTable.getItems().addAll(carsByYear);
        updateStats();
        updateBarChart();
    }
}
