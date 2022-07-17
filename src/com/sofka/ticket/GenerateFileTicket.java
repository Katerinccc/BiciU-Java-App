package com.sofka.ticket;

import com.sofka.entities.Ticket;
import com.sofka.util.Utility;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class GenerateFileTicket {

    private final String EXTERNAL_FILE = "./resources/tickets.txt";
    private Utility utility = new Utility();

    public void updateTicketsFileNewTicket(Ticket newTicket, ArrayList<Ticket> ticketsFile){
        ticketsFile = readCurrentTicketsFile();
        ticketsFile.add(newTicket);
        writeTicketsFile(ticketsFile);
    }

    public void updateTicketsFile(ArrayList<Ticket> ticketsUpdate){
        writeTicketsFile(ticketsUpdate);
    }

    private ArrayList<Ticket> readCurrentTicketsFile(){
        ReadFileTicket readFileTicket = new ReadFileTicket();
        return readFileTicket.readFileTickets();
    }

    private void writeTicketsFile(ArrayList<Ticket> tickets){
        try
        {
            File file = new File(EXTERNAL_FILE);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < tickets.size(); i++) {
                bufferedWriter.write(createNewLine(tickets.get(i)));
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
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
