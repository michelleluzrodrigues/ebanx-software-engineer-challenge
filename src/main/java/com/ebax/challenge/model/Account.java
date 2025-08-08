package com.ebax.challenge.model;

public class Account {
    private String id;
    private int balance;

    public Account(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }


    public boolean withdraw(int amount) {
        if (balance < amount) {
            return false;
        } else {
            this.balance -= amount;
            return true;
        }
    }

}
