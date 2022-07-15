package com.sofka.ui;

import com.sofka.entities.User;
import com.sofka.entities.UserType;
import com.sofka.util.DataUserType;
import com.sofka.util.Utility;

public class UIUser {

    private Utility utility = new Utility();

    public User createUser(){
        UserType userType = captureUserType();
        String userId = getUserId(userType);
        String userName = getUserName();
        int userAge = getUserAge();
        return new User(userId, userName, userAge, userType);
    }

    public UserType captureUserType(){

        String userType;

        do {
            utility.displayData("Indicate type of user. " +
                    "Enter S for Student or P for Professor:");
            userType = (String) utility.getDataUser(DataUserType.TEXT);
            validateUserChoice(userType);
        }while (!validateUserChoice(userType));

        return assignUserType(userType);
    }

    private UserType assignUserType(String userType){
        if (userType.equalsIgnoreCase("S")){
            return UserType.STUDENT;
        }
        return UserType.PROFESSOR;
    }

    private boolean validateUserChoice(String userData){
        return (userData.equalsIgnoreCase("S") || userData.equalsIgnoreCase("P"));
    }

    public String getUserId(UserType userType){
        utility.displayData("Enter user ID:");
        String userId = (String) utility.getDataUser(DataUserType.TEXT);

        if (userType.equals(UserType.STUDENT)){
            return "S-" + userId;
        }
        return "P-" + userId;
    }

    private String getUserName(){
        utility.displayData("Enter user full name:");
        return (String) utility.getDataUser(DataUserType.TEXT);
    }

    private int getUserAge(){
        int userAge;

        do {
            utility.displayData("Enter user age (needs to be 18 years or more to register):");
            userAge = (int) utility.getDataUser(DataUserType.INTEGER);
        }while (userAge < 18);
        return userAge;
    }

}
