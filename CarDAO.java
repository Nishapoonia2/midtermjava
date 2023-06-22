package com.example.carlotapp;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    private Connection connection;

    public CarDAO() {
        // Initialize database connection
        // Replace with your database connection setup
        String url = "jdbc:mysql://localhost:3306/F22Midterm";
        String username = "student";
        String password = "student";

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM carSales";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int carID = resultSet.getInt("carID");
                int modelYear = resultSet.getInt("modelYear");
                String make = resultSet.getString("make");
                String model = resultSet.getString("model");
                int price = resultSet.getInt("price");
                LocalDate dateSold = resultSet.getDate("dateSold").toLocalDate();

                Car car = new Car(carID, modelYear, make, model, price, dateSold);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    public List<Integer> getYears() {
        List<Integer> years = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String query = "SELECT DISTINCT modelYear FROM carSales";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int year = resultSet.getInt("modelYear");
                years.add(year);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return years;
    }

    public List<Car> getCarsByYear(int year) {
        List<Car> cars = new ArrayList<>();

        try {
            String query = "SELECT * FROM carSales WHERE modelYear = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, year);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int carID = resultSet.getInt("carID");
                int modelYear = resultSet.getInt("modelYear");
                String make = resultSet.getString("make");
                String model = resultSet.getString("model");
                int price = resultSet.getInt("price");
                LocalDate dateSold = resultSet.getDate("dateSold").toLocalDate();

                Car car = new Car(carID, modelYear, make, model, price, dateSold);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }
}
