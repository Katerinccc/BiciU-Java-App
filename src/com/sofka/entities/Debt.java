package com.sofka.entities;

public class Debt {

    private DebtType debtType;
    private int amount;

    public Debt(DebtType debtType) {
        this.debtType = debtType;
        this.amount = assignAmount(debtType);
    }

    private int assignAmount(DebtType debtType){
        if (debtType.equals(DebtType.NO_TIME)){
            return 3;
        }
        return 5;
    }
}
