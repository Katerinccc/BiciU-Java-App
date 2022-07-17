package com.sofka.entities;

public class User {

    private String id;
    private String fullName;
    private int age;
    private UserType userType;
    private double debtAmount;

    public User(String id, String fullName, int age, UserType userType) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.userType = userType;
        this.debtAmount = 0;
    }

    public User(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public double getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(double debtAmount) {
        this.debtAmount = debtAmount;
    }
}
