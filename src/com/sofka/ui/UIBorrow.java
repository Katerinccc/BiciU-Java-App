package com.sofka.ui;

import com.sofka.entities.Bicycle;
import com.sofka.entities.BicycleType;
import com.sofka.entities.Ticket;
import com.sofka.entities.User;
import com.sofka.entities.UserType;
import com.sofka.ticket.GenerateTicket;
import com.sofka.util.DataUserType;
import com.sofka.util.Utility;
import java.util.ArrayList;
import java.util.Optional;

public class UIBorrow {

    private Utility utility = new Utility();
    private UIUser uiUser = new UIUser();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Bicycle> bicycles = new ArrayList<>();

    public ArrayList<Bicycle> borrowMenu(ArrayList<Bicycle> bicyclesSystem,
                                         ArrayList<User> usersSystem){
        users = usersSystem;
        bicycles = bicyclesSystem;

        User currentUser = getUser();

        if (validateUser(currentUser)){
            tryBorrowBicycleSelected(currentUser);
        }

        return bicycles;
    }

    private boolean validateUser(User currentUser){
        return currentUser != null && !validateUserHasDebt(currentUser);
    }

    private User getUser(){
        UserType userType = uiUser.captureUserType();
        String userId = uiUser.getUserId(userType);

        Optional<User> optionalUser = users.stream().filter(user -> user.getId().equals(userId)).findFirst();

        if (optionalUser.isEmpty()){
            utility.displayData("The ID enter is not register yet, please validate and try again.");
            return null;
        }

        return optionalUser.get();
    }

    private boolean validateUserHasDebt(User user){
        return user.getDebtAmount() != 0;
    }

    private BicycleType selectBicycleType(){
        String userChoice;

        do {
            utility.displayData("Indicate the type of bicycle that the user wants." +
                    "Enter M for Mountain or R for Road");
            userChoice = (String) utility.getDataUser(DataUserType.TEXT);
            validateUserChoice(userChoice);
        }while (!validateUserChoice(userChoice));

        return assignBicycleType(userChoice);

    }

    private BicycleType assignBicycleType(String userType){
        if (userType.equalsIgnoreCase("M")){
            return BicycleType.MOUNTAIN;
        }
        return BicycleType.ROAD;
    }

    private boolean validateUserChoice(String userData){
        return (userData.equalsIgnoreCase("M") || userData.equalsIgnoreCase("R"));
    }

    private Bicycle selectBicycle(BicycleType bicycleType){

        Optional<Bicycle> optionalBicycle = bicycles.stream()
                        .filter(bicycle -> bicycle.getBicycleType().equals(bicycleType)
                        && bicycle.isAvailable()).findFirst();

        if (optionalBicycle.isEmpty()){
            utility.displayData("The are not bicycles available of the type selected, please try another one.");
            return null;
        }

        return optionalBicycle.get();
    }

    private void tryBorrowBicycleSelected(User currentUser){

        BicycleType bicycleTypeChoice = selectBicycleType();

        if (selectBicycle(bicycleTypeChoice) != null){

            Bicycle bicycleSelected = selectBicycle(bicycleTypeChoice);

            assert bicycleSelected != null;
            bicycleSelected.borrowBicycle();

            utility.displayData("Bicycle chosen successfully!");
            bicycleSelected.displayBicycle();

            GenerateTicket generateTicket = new GenerateTicket();
            Ticket ticketGenerated = generateTicket.generateNewTicket(bicycleSelected, currentUser);
            ticketGenerated.displayTicket();

        }
    }
}
