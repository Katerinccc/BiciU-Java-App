package com.sofka.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Ticket {
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

}
