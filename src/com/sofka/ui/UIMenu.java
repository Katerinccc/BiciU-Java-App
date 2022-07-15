package com.sofka.ui;

import com.sofka.util.Utility;

public class UIMenu {

    private Utility utility = new Utility();
    private Integer option = 0;

    public void mainMenu(){

        do {
            utility.displayData("Welcome to BiciU system");
            utility.displayData("1. Register user");
            utility.displayData("2. Borrow bicy");
            utility.displayData("3. Return bicy");
            utility.displayData("4. Pay tickets");
            utility.displayData("5. Tickets history");
            utility.displayData("0. Exit the system");
            option = (Integer) utility.getDataUser(com.sofka.util.DataUserType.INTEGER);
            options(option);
        }while (option != 0);

    }

    private void options (int option) {

        utility.displayData("-------------------------------------------------------------------------------");

        switch (option) {
            case 1 -> utility.displayData("Option not available yet");
            case 2 -> utility.displayData("Option not available yet");
            case 3 -> utility.displayData("Option not available yet");
            case 4 -> utility.displayData("Option not available yet");
            case 5 -> utility.displayData("Option not available yet");
            case 0 -> utility.displayData("You have exit the system BiciU successfully.");
            default -> utility.displayData("Enter a valid option.");
        }

        utility.displayData("-------------------------------------------------------------------------------");
    }
}