package com.sofka.ticket;

import com.sofka.entities.Bicycle;
import com.sofka.entities.Ticket;
import com.sofka.entities.TicketStatus;
import com.sofka.entities.User;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class GenerateTicket {

    private ReadFileTicket readFileTicket = new ReadFileTicket();

    public Ticket generateNewTicket(Bicycle bicycle, User user){

        ArrayList<Ticket> ticketsFile = readFileTicket.readFileTickets();

        String code = generateConsecutiveCode(ticketsFile);
        Ticket newTicket = new Ticket(code,
                            bicycle,
                            user,
                            LocalDate.now(),
                            LocalTime.now(),
                            null,
                            true,
                            true,
                            TicketStatus.ACTIVE,
                            0);

        GenerateFileTicket generateFileTicket = new GenerateFileTicket();
        generateFileTicket.updateTicketsFileNewTicket(newTicket);

        return newTicket;
    }

    private String generateConsecutiveCode(ArrayList<Ticket> ticketsFile){

        if (!ticketsFile.isEmpty()){
            int lastIndex = ticketsFile.size() - 1;
            String lastCode = ticketsFile.get(lastIndex).getCode();
            int codeNumber = Integer.parseInt(lastCode.replaceAll("[^0-9]", ""));

            return "T-"+ padLeftZeros(String.valueOf(codeNumber+1), 3);
        }

        return "T-001";
    }

    private String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }


}
