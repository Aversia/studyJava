package org.example.linar.payments.model;

public class Card {
    private String number;
    private String ownerName;
    private int balance;
    private int limit;

    public Card(String number, String ownerName, int balance, int limit){
        this.number = number;
        this.ownerName = ownerName;
        this.balance = balance;
        this.limit = limit;
    }

    public String getNumber() {
        return number;
    }

    public int getBalance(){
        return balance;
    }

    public int getLimit(){
        return limit;
    }

    public String ownerNumber(){
        return ownerName;
    }

    public boolean hasEnoughMoney(int amount){
        return balance >= amount;
    }

    public boolean isLimitExceeded(int amount){
        return amount > limit;
    }

    public void withdraw(int amount) {
        if (balance >= amount) {
            balance = balance - amount;
        } else {
            System.out.println("Недостаточно средств");
        }
    }

    public void deposit(int amount){
        balance = balance + amount;
    }
    public boolean canPay(int amount){
        return !isLimitExceeded(amount) && hasEnoughMoney(amount);
    }
}
