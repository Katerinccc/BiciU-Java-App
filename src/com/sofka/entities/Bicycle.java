package com.sofka.entities;

public class Bicycle {

    private String name;
    private BicycleType bicycleType;
    private String color;
    private boolean isAvailable;

    public Bicycle(String name, BicycleType bicycleType, String color, boolean isAvailable) {
        this.name = name;
        this.bicycleType = bicycleType;
        this.color = color;
        this.isAvailable = isAvailable;
    }
}
