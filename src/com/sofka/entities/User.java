package com.sofka.entities;

public class User {

    private String id;
    private String fullName;
    private int age;
    private UserType userType;

    public User(String id, String fullName, int age, UserType userType) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

}
