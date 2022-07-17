package com.sofka.ticket;

import com.sofka.entities.Bicycle;
import com.sofka.entities.Ticket;
import com.sofka.entities.TicketStatus;
import com.sofka.entities.User;
import com.sofka.util.Utility;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFileTicket {

    private final String EXTERNAL_FILE = "./resources/tickets.txt";
    private Utility utility = new Utility();

    public ArrayList<Ticket> readFileTickets(){

        FileReader fileReader;
        BufferedReader bufferedReader;
        ArrayList<Ticket> ticketsFile = new ArrayList<>();

        try {

            File file = new File(EXTERNAL_FILE);

            if (file.length() != 0L){
                fileReader = new FileReader(file);

                bufferedReader = new BufferedReader(fileReader);

                String line;

                while ((line = bufferedReader.readLine()) != null){
                    addNewTicket(ticketsFile, line);
                }

                bufferedReader.close();
                fileReader.close();
            }

        }catch (Exception exception){
            utility.displayData("Read file failed. " +  exception.getMessage());
        }

        return ticketsFile;
    }

    private void addNewTicket(ArrayList<Ticket> tickets, String line) {
        List<String> newTicket;
        newTicket = Arrays.stream(line.split(";")).toList();
        tickets.add(new Ticket(newTicket.get(0),
                createBicycle(newTicket.get(1)),
                createUser(newTicket.get(2), newTicket.get(3)),
                LocalDate.parse(newTicket.get(4)),
                LocalTime.parse(newTicket.get(5)),
                validateTime(newTicket.get(6)),
                Boolean.parseBoolean(newTicket.get(7)),
                Boolean.parseBoolean(newTicket.get(8)),
                createTicketStatus(newTicket.get(9)),
                Double.parseDouble(newTicket.get(10))));
    }

    private User createUser(String id, String name){
        return new User(id, name);
    }

    private Bicycle createBicycle(String code){
        return new Bicycle(code);
    }

    private TicketStatus createTicketStatus(String status){
        return switch (status) {
            case "ACTIVE" -> TicketStatus.ACTIVE;
            case "PENDING" -> TicketStatus.PENDING;
            case "OK" -> TicketStatus.OK;
            default -> null;
        };
    }

    private LocalTime validateTime(String time){
        if (time.equals("null")){
            return null;
        }
        return LocalTime.parse(time);
    }

}
