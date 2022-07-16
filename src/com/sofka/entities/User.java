package com.sofka.entities;

public class User {

    private String id;
    private String fullName;
    private int age;
    private UserType userType;
    private Debt debt;

    public User(String id, String fullName, int age, UserType userType) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.userType = userType;
        this.debt = new Debt(DebtType.NO_DEBT);
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Debt getDebt() {
        return debt;
    }

    public void setDebt(Debt debt) {
        this.debt = debt;
    }
}
