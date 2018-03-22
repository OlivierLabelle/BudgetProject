package com.example.android.budgetproject;

/**
 * Created by olivier on 2018-03-12.
 */

public class Transaction {

    private double depense;
    private String description;
    private int date;
    //ToDo image ??

    public Transaction(){}

    public Transaction(double depense, String description, int date) {
        this.depense = depense;
        this.description = description;
        this.date = date;
    }

    public double getDepense() {
        return depense;
    }

    public void setDepense(double depense) {
        this.depense = depense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
