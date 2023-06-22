package com.example.carlotapp;

import java.time.LocalDate;
import javafx.beans.property.*;

public class Car {
    private IntegerProperty carID;
    private IntegerProperty modelYear;
    private StringProperty make;
    private StringProperty model;
    private IntegerProperty price;
    private ObjectProperty<LocalDate> dateSold;

    public Car(int carID, int modelYear, String make, String model, int price, LocalDate dateSold) {
        this.carID = new SimpleIntegerProperty(carID);
        this.modelYear = new SimpleIntegerProperty(modelYear);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.price = new SimpleIntegerProperty(price);
        this.dateSold = new SimpleObjectProperty<>(dateSold);
    }

    // Getters and setters for properties

    public IntegerProperty carIDProperty() {
        return carID;
    }

    public int getCarID() {
        return carID.get();
    }

    public void setCarID(int carID) {
        this.carID.set(carID);
    }

    public IntegerProperty modelYearProperty() {
        return modelYear;
    }

    public int getModelYear() {
        return modelYear.get();
    }

    public void setModelYear(int modelYear) {
        this.modelYear.set(modelYear);
    }

    public StringProperty makeProperty() {
        return make;
    }

    public String getMake() {
        return make.get();
    }

    public void setMake(String make) {
        this.make.set(make);
    }

    public StringProperty modelProperty() {
        return model;
    }

    public String getModel() {
        return model.get();
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public int getPrice() {
        return price.get();
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public ObjectProperty<LocalDate> dateSoldProperty() {
        return dateSold;
    }

    public LocalDate getDateSold() {
        return dateSold.get();
    }

    public void setDateSold(LocalDate dateSold) {
        this.dateSold.set(dateSold);
    }

    // Other properties and methods
}
