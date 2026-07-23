package org.example.linar.payments.model;

public class Payment {
    private int id;
    private String cardNumber;
    private int amount;
    private PaymentStatus status;
    public Payment(int id, String cardNumber, int amount){
        this.id = id;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.status = PaymentStatus.CREATED;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }
    public PaymentStatus getStatus(){
        return status;
    }


    public void markSuccess(){
        if (getStatus() == PaymentStatus.CREATED) {
            status = PaymentStatus.SUCCESS;
        }

    }
    public void markFailed(){
        if (getStatus() == PaymentStatus.CREATED){
            status = PaymentStatus.FAILED;
        }
    }
    public void markRefunded(){
        if (getStatus() == PaymentStatus.SUCCESS){
            status = PaymentStatus.REFUNDED;
        }

    }
}
