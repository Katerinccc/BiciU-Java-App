package com.sofka.ui;

import com.sofka.entities.DebtType;
import com.sofka.entities.Ticket;
import com.sofka.entities.TicketStatus;
import com.sofka.entities.User;
import com.sofka.ticket.GenerateFileTicket;
import com.sofka.util.DataUserType;
import com.sofka.util.Utility;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;

public class UIReturn {

    private Utility utility = new Utility();
    private ArrayList<Ticket> currentTickets = new ArrayList<>();
    private UITicket uiTicket = new UITicket();
    private ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> returnMenu (ArrayList<User> usersSystem){

        uiTicket.readCurrentTickets(currentTickets);
        users = usersSystem;

        if (!currentTickets.isEmpty()){
            returnBicycle();
            GenerateFileTicket generateFileTicket = new GenerateFileTicket();
            generateFileTicket.updateTicketsFile(currentTickets);
            return users;
        }else {
            utility.displayData("There are no tickets generated yet to return a bicycle.");
        }

        return usersSystem;
    }

    private void returnBicycle(){

        double totalDebt;

        Optional<Ticket> optionalTicket =  uiTicket.getTicket();

        if (optionalTicket.isEmpty()){
            utility.displayData("The code enter do not exists. Please validate and try again.");
        }else {
            Ticket currentTicket = optionalTicket.get();
            currentTicket.setEndTime(LocalTime.now());
            totalDebt = validateNoTime(currentTicket);
            totalDebt += validateHelmetStatus(currentTicket);
            totalDebt += validateDamagedStatus(currentTicket);
            User currentUser = validateUser(currentTicket, users);

            if (totalDebt == 0){
                currentTicket.setTicketStatus(TicketStatus.OK);
                utility.displayData("Return bicycle was successful" +
                        "No debts were generated to the user.");
            }else {
                assert currentUser != null;
                currentUser.setDebtAmount(totalDebt);
                currentTicket.setAmount(totalDebt);
                currentTicket.setTicketStatus(TicketStatus.PENDING);
                utility.displayData("Return bicycle was successful" +
                        "A debt to the user was generated by: " + totalDebt);

            }

        }

    }

    private int debtValues(DebtType debtType){
        return switch (debtType){
            case NO_TIME -> 3;
            case WITHOUT_HELMET, DAMAGED -> 5;
        };
    }

    private double validateNoTime(Ticket currentTicket){

        long difference =currentTicket.getStartTime().until(currentTicket.getEndTime(), ChronoUnit.MINUTES);

        //First 30 minutes of borrow are free.
        return Math.ceil(difference / 30 -1) * debtValues(DebtType.NO_TIME);
    }

    private int validateHelmetStatus(Ticket currentTicket){
        utility.displayData("The user return the helmet, Enter 1 for Yes or 0 for No");
        boolean validation = (boolean) utility.getDataUser(DataUserType.BOOLEAN);
        if (validation){
            return 0;
        }
        currentTicket.setHaveHelmet(false);
        return debtValues(DebtType.WITHOUT_HELMET);
    }

    private int validateDamagedStatus(Ticket currentTicket){
        int damageValue = 0;
        utility.displayData("The bicycle is in good condition? Enter 1 for Yes or 0 for No");
        boolean validationBicy = (boolean) utility.getDataUser(DataUserType.BOOLEAN);

        if (validationBicy){
        }else {
            currentTicket.setInGoodCondition(false);
            damageValue += debtValues(DebtType.DAMAGED);
        }

        utility.displayData("The helmet is in good condition? Enter 1 for Yes or 0 for No");
        boolean validationHelmet = (boolean) utility.getDataUser(DataUserType.BOOLEAN);

        if (validationHelmet){
            damageValue += 0;
        }else {
            currentTicket.setInGoodCondition(false);
            damageValue += debtValues(DebtType.DAMAGED);
        }

        return damageValue;
    }

    public User validateUser(Ticket currentTicket, ArrayList<User> users){

        Optional<User> currentUser = users.stream()
                .filter(user -> user.getId().equals(currentTicket.getUser().getId())).findFirst();

        if (currentUser.isEmpty()){
            return null;
        }

        return currentUser.get();
    }

}