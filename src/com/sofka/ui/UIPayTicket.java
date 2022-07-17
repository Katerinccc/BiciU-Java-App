package com.sofka.ui;

import com.sofka.entities.Ticket;
import com.sofka.entities.TicketStatus;
import com.sofka.entities.User;
import com.sofka.ticket.GenerateFileTicket;
import com.sofka.util.DataUserType;
import com.sofka.util.Utility;

import java.util.ArrayList;
import java.util.Optional;

public class UIPayTicket {

    private Utility utility = new Utility();
    private ArrayList<Ticket> currentTickets = new ArrayList<>();
    private UITicket uiTicket = new UITicket();
    private ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> payMenu (ArrayList<User> usersSystem){

        uiTicket.readCurrentTickets(currentTickets);
        users = usersSystem;

        if (!currentTickets.isEmpty()){
            payTicket();
            GenerateFileTicket generateFileTicket = new GenerateFileTicket();
            generateFileTicket.updateTicketsFile(currentTickets);
            return users;

        }else {
            utility.displayData("There are no tickets generated yet to pay.");
        }

        return usersSystem;

    }

    private void payTicket(){
        Optional<Ticket> optionalTicket =  uiTicket.getTicket();

        if (optionalTicket.isEmpty()){
            utility.displayData("The code enter do not exists. Please validate and try again.");
        }else {
            Ticket currentTicket = optionalTicket.get();
            if (validateTicketStatus(currentTicket)){
                utility.displayData("Ticket status is OK, no payment is required.");
            }else {
                changeStatus(currentTicket);
            }
        }
    }

    private boolean validateTicketStatus(Ticket currentTicket){
        return currentTicket.getTicketStatus() == TicketStatus.OK;
    }

    private void changeStatus(Ticket currentTicket){
        utility.displayData("Proceed with Payment? Enter 1 for YES or 0 for NO");
        boolean validationPayment = (boolean) utility.getDataUser(DataUserType.BOOLEAN);

        if (validationPayment) {
            currentTicket.setTicketStatus(TicketStatus.OK);
            UIReturn uiReturn = new UIReturn();
            User user = uiReturn.validateUser(currentTicket, users);
            user.setDebtAmount(0);
            utility.displayData("Payment made successfully. " +
                    "Ticket status change to OK, the user can borrow bicycles again");
        }else {
            utility.displayData("Payment nod made. Ticket status will remain in Pending");
        }

    }

}
