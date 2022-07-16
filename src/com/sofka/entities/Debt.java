package com.sofka.entities;

public class Debt {

    private DebtType debtType;
    private int amount;

    public Debt(DebtType debtType) {
        this.debtType = debtType;
        this.amount = assignAmount(debtType);
    }

    private int assignAmount(DebtType debtType){
        return switch (debtType) {
            case NO_TIME -> 3;
            case NO_DEBT -> 0;
            case WITHOUT_HELMET, DAMAGED -> 5;
        };
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
