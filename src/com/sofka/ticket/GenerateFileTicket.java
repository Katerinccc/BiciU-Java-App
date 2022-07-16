package com.sofka.ticket;

import com.sofka.entities.Ticket;
import com.sofka.util.Utility;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class GenerateFileTicket {

    private final String EXTERNAL_FILE = "./resources/tickets.txt";
    private Utility utility = new Utility();
    private ArrayList<Ticket> ticketsFile = new ArrayList<>();

    public void updateTicketsFile(Ticket newTicket){
        ticketsFile.add(newTicket);
        writeTicketsFile(EXTERNAL_FILE);
    }
    private void writeTicketsFile(String externalFile){
        try
        {
            FileWriter fileWriter = new FileWriter(externalFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < ticketsFile.size(); i++) {
                bufferedWriter.write(createNewLine(ticketsFile.get(i)));
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        }
        catch(Exception exception)
        {
            utility.displayData("Updated file failed.. " +  exception.getMessage());
        }
    }

    private String createNewLine(Ticket ticket){
        return String.format("%1$s;%2$s;%3$s;%4$s;%5$s;%6$s;%7$s;%8$s;%9$s;%10$s;%11$s",
                ticket.getCode(),
                ticket.getBicycle().getCode(),
                ticket.getUser().getId(),
                ticket.getUser().getFullName(),
                ticket.getDate(),
                ticket.getStartTime(),
                ticket.getEndTime(),
                ticket.getHaveHelmet(),
                ticket.isInGoodCondition(),
                ticket.getTicketStatus().toString(),
                ticket.getAmount());
    }

}
