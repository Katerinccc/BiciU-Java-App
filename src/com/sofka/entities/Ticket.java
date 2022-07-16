package com.sofka.entities;

import com.sofka.util.Utility;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Ticket {

    private Utility utility = new Utility();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
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
        utility.displayData("\nA Ticket was generated: \n" +
                "\nCode: " + this.code +
                "\nBicycle: " + this.bicycle.getCode() +
                "\nUser: " + this.user.getId() +
                "\nName: " + this.user.getFullName() +
                "\nDate: " + this.date.toString() +
                "\nStart time: " + formatter.format(this.startTime) +
                "\nEnd time: " + " - " +
                "\nHave helmet: " + this.haveHelmet +
                "\nGood Condition: " + this.inGoodCondition +
                "\nStatus: " + this.ticketStatus.toString().toLowerCase() +
                "\nAmount: " + this.amount);
    }

    public String getCode() {
        return code;
    }

    public Bicycle getBicycle() {
        return bicycle;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean getHaveHelmet() {
        return haveHelmet;
    }

    public boolean isInGoodCondition() {
        return inGoodCondition;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public int getAmount() {
        return amount;
    }
}
