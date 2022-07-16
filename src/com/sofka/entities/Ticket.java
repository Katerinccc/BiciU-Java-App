package com.sofka.entities;

import com.sofka.util.Utility;
import java.time.LocalDate;
import java.time.LocalTime;

public class Ticket {

    private Utility utility = new Utility();
    private String code;
    private Bicycle bicycle;
    private User user;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean haveHelmet;
    private boolean inGoodCondition;
    private TicketStatus ticketStatus;
    private int amount;

    public Ticket(String code,
                  Bicycle bicycle,
                  User user,
                  LocalDate date,
                  LocalTime startTime,
                  boolean haveHelmet,
                  boolean inGoodCondition,
                  TicketStatus ticketStatus,
                  int amount)
    {
        this.code = code;
        this.bicycle = bicycle;
        this.user = user;
        this.date = date;
        this.startTime = startTime;
        this.haveHelmet = haveHelmet;
        this.inGoodCondition = inGoodCondition;
        this.ticketStatus = ticketStatus;
        this.amount = amount;
    }

    public void displayTicket(){
        utility.displayData("A Ticket was generated:" +
                "\nCode: " + this.code +
                "\nBicycle: " + this.bicycle.getCode() +
                "\nUser: " + this.user.getId() +
                "\nName: " + this.user.getFullName() +
                "\nDate: " + this.date.toString() +
                "\nStart time: " + this.startTime.toString() +
                "\nEnd time: " + this.endTime.toString() +
                "\nHave helmet: " + this.haveHelmet +
                "\nGood Condition: " + this.inGoodCondition +
                "\nStatus: " + this.ticketStatus.toString().toLowerCase() +
                "\nAmount: " + this.amount);
    }

    public String getCode() {
        return code;
    }
}
