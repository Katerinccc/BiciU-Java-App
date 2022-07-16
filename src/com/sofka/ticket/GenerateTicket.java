package com.sofka.ticket;

import com.sofka.entities.Bicycle;
import com.sofka.entities.Ticket;
import com.sofka.entities.TicketStatus;
import com.sofka.entities.User;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class GenerateTicket {

    private ArrayList<Ticket> tickets = new ArrayList<>();

    public Ticket generateNewTicket(Bicycle bicycle, User user){
        String code = generateConsecutiveCode();
        return new Ticket(code,
                bicycle,
                user,
                LocalDate.now(),
                LocalTime.now(),
                true,
                true,
                TicketStatus.ACTIVE,
                0);
    }

    private String generateConsecutiveCode(){

        int lastIndex = tickets.size() - 1;
        String lastCode = tickets.get(lastIndex).getCode();
        int codeNumber = Integer.parseInt(lastCode.replaceAll("[^0-9]", ""));

        return "T-"+ padLeftZeros(String.valueOf(codeNumber+1), 3);

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
