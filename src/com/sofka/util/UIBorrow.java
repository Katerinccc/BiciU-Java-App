package com.sofka.util;

import com.sofka.entities.Bicycle;
import com.sofka.entities.BicycleType;
import com.sofka.entities.User;
import com.sofka.entities.UserType;
import com.sofka.ui.UIUser;
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

        if (!validateUserEnable()){
            return bicycles;
        }

        BicycleType bicycleTypeChoice = selectBicycleType();

        if (selectBicycle(bicycleTypeChoice) == null){
            return bicycles;
        }

        Bicycle bicycleUser = selectBicycle(bicycleTypeChoice);
        borrowBicycleSelected(bicycleUser);
        return bicycles;
    }

    private boolean validateUserEnable(){
        User currentUser = validateUserExists();

        if (currentUser == null){
            return false;
        }

        if (validateUserHasDebt(currentUser)){
            return false;
        }

        return true;
    }

    private User validateUserExists(){
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

        if (user.getDebt() == null){
            return false;
        }

        return user.getDebt().getAmount() != 0;

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

    private void borrowBicycleSelected(Bicycle bicycleSelected){
        Bicycle bicycle = bicycles.stream()
                .filter(bicy -> bicy.getCode().equals(bicycleSelected.getCode())).findFirst().get();

        bicycle.borrowBicycle();

        utility.displayData("Bicycle chosen successfully!");
        bicycleSelected.displayBicycle();

    }


}
