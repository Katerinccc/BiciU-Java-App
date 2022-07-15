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

    private UserType captureUserType(){

        String userType;

        do {
            utility.displayData("Select the type of user to Register. " +
                    "Enter S for Student or P for Professor");
            userType = (String) utility.getDataUser(DataUserType.TEXT);
            validateUserType(userType);
        }while (!validateUserType(userType));

        return assignUserType(userType);
    }

    private UserType assignUserType(String userType){
        if (userType.equalsIgnoreCase("S")){
            return UserType.STUDENT;
        }
        return UserType.PROFESSOR;
    }

    private boolean validateUserType(String userData){
        return (userData.equalsIgnoreCase("S") || userData.equalsIgnoreCase("P"));
    }

    private String getUserId(UserType userType){
        utility.displayData("Enter user ID");
        String userId = (String) utility.getDataUser(DataUserType.TEXT);

        if (userType.equals(UserType.STUDENT)){
            return "S-" + userId;
        }
        return "P-" + userId;
    }

    private String getUserName(){
        utility.displayData("Enter user name");
        return (String) utility.getDataUser(DataUserType.TEXT);
    }

    private int getUserAge(){
        utility.displayData("Enter user age");
        return (int) utility.getDataUser(DataUserType.INTEGER);
    }

}
