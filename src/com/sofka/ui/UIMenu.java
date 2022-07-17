package com.sofka.ui;

import com.sofka.Bicycle.GenerateBicycle;
import com.sofka.entities.Bicycle;
import com.sofka.entities.User;
import com.sofka.util.Utility;

import java.io.File;
import java.util.ArrayList;

public class UIMenu {

    private Utility utility = new Utility();
    private Integer option = 0;
    private GenerateBicycle generateBicycle = new GenerateBicycle();
    private ArrayList<User> usersSystem = new ArrayList<>();
    private ArrayList<Bicycle> bicycleSystem = new ArrayList<>();
    private final String EXTERNAL_FILE = "./resources/bicycles.txt";

    public void mainMenu(){

        bicycleSystem = generateBicycle.createBicycles(EXTERNAL_FILE);

        do {
            utility.displayData("Welcome to BiciU system, Select and option:");
            utility.displayData("1. Register user");
            utility.displayData("2. Borrow bicycle");
            utility.displayData("3. Return bicycle");
            utility.displayData("4. Pay tickets");
            utility.displayData("5. Tickets history");
            utility.displayData("0. Exit the system.");
            option = (Integer) utility.getDataUser(com.sofka.util.DataUserType.INTEGER);
            options(option);
        }while (option != 0);

        File ticketsFile = new File("./resources/tickets.txt");
        ticketsFile.delete();

    }

    private void options (int option) {

        utility.displayData("-------------------------------------------------------------------------------");

        switch (option) {
            case 1 -> {
                UIUser uiUser = new UIUser();
                usersSystem.add(uiUser.createUser());
                utility.displayData("User register successfully.");
            }
            case 2 -> {
                UIBorrow uiBorrow = new UIBorrow();
                bicycleSystem = uiBorrow.borrowMenu(bicycleSystem, usersSystem);
            }
            case 3 -> utility.displayData("Option not available yet");
            case 4 -> utility.displayData("Option not available yet");
            case 5 -> {
                UITicket uiTicket = new UITicket();
                uiTicket.ticketMenu();
            }
            case 0 -> utility.displayData("You have exit the system BiciU successfully.");
            default -> utility.displayData("Enter a valid option.");
        }

        utility.displayData("-------------------------------------------------------------------------------");
    }
}
