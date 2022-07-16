package com.sofka.entities;

import com.sofka.interfaces.Borrow;
import com.sofka.util.Utility;

public class Bicycle implements Borrow {

    private String code;
    private BicycleType bicycleType;
    private String color;
    private boolean isAvailable;
    private Utility utility = new Utility();

    public Bicycle(String code, BicycleType bicycleType, String color, boolean isAvailable) {
        this.code = code;
        this.bicycleType = bicycleType;
        this.color = color;
        this.isAvailable = isAvailable;
    }

    @Override
    public void borrowBicycle() {
        this.isAvailable = false;
    }

    public BicycleType getBicycleType() {
        return bicycleType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getCode() {
        return code;
    }

    public void displayBicycle(){
        utility.displayData("Bicycle chosen:" +
                            "\nCode: " + this.code +
                            "\nType: " + this.getBicycleType().toString() +
                            "\nColor: " + this.color);
    }
}
