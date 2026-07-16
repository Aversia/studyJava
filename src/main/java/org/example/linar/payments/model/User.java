package org.example.linar.payments.model;

public class User {
    private String name;
    private int balance;
    public User(String name, int balance){
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }
    
    public int getBalance() {
        return balance;
    }
}
