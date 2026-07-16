package org.example;

import org.example.linar.payments.model.Card;
import org.example.linar.payments.model.Payment;
import org.example.linar.payments.model.PaymentStatus;
import org.example.linar.payments.model.User;
public class Main {
    public static void main(String[] args) {
        User user = new User("Linar", 1000);
        Card card = new Card(
                "2200 4532 9873 7827",
                "Linar",
                10000,
                10000
                );
        Payment payment = new Payment(123, "2200 4532 9873 7827", 200);

        System.out.println("User name "  + user.getName());
        System.out.println("User balance " + user.getBalance());
        System.out.println("Has enough money? " + card.hasEnoughMoney(10000));
        card.deposit(100);
        System.out.println(payment.getStatus());
        System.out.println("Deposit 100 " + card.getBalance());
        payment.markSuccess();
        System.out.println(payment.getStatus());
        System.out.println("Withdraw 100000 " + card.getBalance());
        card.withdraw(100000);
        System.out.println("Limit " + card.isLimitExceeded(100));
        System.out.println(card.canPay(1000));
    }
}