package com.sofka.ui;

import com.sofka.entities.Ticket;
import com.sofka.entities.TicketStatus;
import com.sofka.ticket.ReadFileTicket;
import com.sofka.util.DataUserType;
import com.sofka.util.Utility;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UITicket {

    private Utility utility = new Utility();
    private Integer option = 0;
    private ArrayList<Ticket> currentTickets = new ArrayList<>();

    public UITicket() {
        if(currentTickets.size() == 0) {
            currentTickets = readCurrentTickets();
        }
    }

    public void ticketMenu(){

        if (!currentTickets.isEmpty()){
            do {
                utility.displayData("1. Display all tickets.");
                utility.displayData("2. Search ticket by code");
                utility.displayData("3. Search by status");
                utility.displayData("0. Return to main menu");
                option = (Integer) utility.getDataUser(DataUserType.INTEGER);
                options(option);
            }while (option != 0);
        }else {
            utility.displayData("There are no tickets generated yet.");
        }
    }

    private void options (int option) {

        utility.displayData("-------------------------------------------------------------------------------");

        switch (option) {
            case 1 -> displayAllTickets();
            case 2 -> displayTicketCode();
            case 3 -> displayTicketStatus();
            case 0 -> utility.displayData("You will be redirect to main menu.");
            default -> utility.displayData("Enter a valid option.");
        }

        utility.displayData("-------------------------------------------------------------------------------");
    }

    public ArrayList<Ticket> readCurrentTickets(){
        return new ReadFileTicket().readFileTickets();
    }

    private void displayAllTickets(){
        displayFormat(currentTickets);
    }

    private void displayTicketCode(){
        Optional<Ticket> optionalTicket = getTicket();

        if (optionalTicket.isEmpty()){
            utility.displayData("The code enter do not exists. Please validate and try again.");
        }else {
            ArrayList<Ticket> ticketSearch = new ArrayList<>();
            ticketSearch.add(optionalTicket.get());
            displayFormat(ticketSearch);
        }

    }

    public Optional<Ticket> getTicket() {
        String ticketCode;
        utility.displayData("Enter ticket code to search (Format T-NNN)");
        ticketCode = (String) utility.getDataUser(DataUserType.TEXT);

        return currentTickets.stream().filter(ticket -> ticket.getCode()
                .equals(ticketCode)).findFirst();
    }

    private int choiceTicketStatus(){

        int choice;

        do {
            utility.displayData("Select status to display tickets;");
            utility.displayData("1. Ok");
            utility.displayData("2. Pending");
            utility.displayData("3. Active");
            choice = (int) utility.getDataUser(DataUserType.INTEGER);
        }while (!validateChoice(choice));

        return choice;
    }

    private boolean validateChoice(int choice){
        return choice == 1 || choice == 2 || choice == 3;
    }

    private TicketStatus selectTicketStatus(int choice){
        return switch (choice) {
            case 1 -> TicketStatus.OK;
            case 2 -> TicketStatus.PENDING;
            case 3 -> TicketStatus.ACTIVE;
            default -> null;
        };
    }

    private void displayTicketStatus(){
        TicketStatus statusChoose = selectTicketStatus(choiceTicketStatus());

        List<Ticket> tickets =  currentTickets.stream()
                .filter(ticket -> ticket.getTicketStatus().equals(statusChoose)).toList();

        if (tickets.isEmpty()) {
            utility.displayData("There are no tickets generated for the status chosen.");
        }else {
            ArrayList<Ticket> ticketsDisplay = new ArrayList<>(tickets);
            displayFormat(ticketsDisplay);
        }

    }

    private void displayFormat(ArrayList<Ticket> ticketsDisplay){

        String header = String.format("Code | UserId | Name | Amount $ | Status ");
        utility.displayData(header);
        String body;

        for (Ticket ticket:ticketsDisplay) {
            body = String.format("%1$s | %2$s | %3$s |  %4$s  | %5$s ",
                    ticket.getCode(),
                    ticket.getUser().getId(),
                    ticket.getUser().getFullName(),
                    ticket.getAmount(),
                    ticket.getTicketStatus().toString().toLowerCase());
            utility.displayData(body);
        }

    }

}
