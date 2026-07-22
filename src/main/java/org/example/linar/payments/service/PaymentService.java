package org.example.linar.payments.service;

import org.example.linar.payments.model.Card;
import org.example.linar.payments.model.Payment;
import org.example.linar.payments.model.PaymentStatus;

public class PaymentService {
    public boolean pay (Card card, Payment payment){
        if (payment == null){
            return false;
        }
        if (!validateCard(card)) {
            payment.markFailed();
            return false;
        }
        if (!card.getNumber().equals(payment.getCardNumber())){
            payment.markFailed();
            return false;
        }
        if (payment.getStatus() != PaymentStatus.CREATED){
            return false;
        }
        if (payment.getAmount() <= 0){
            payment.markFailed();
            return false;
        }
        if (!card.canPay(payment.getAmount())){
            payment.markFailed();
            return false;
        }
        card.withdraw(payment.getAmount());
        payment.markSuccess();
        return true;
    }
    public boolean refund(Card card, Payment payment) {
        if (card == null || payment == null) {

            return false;
        }
        if (!validateCard(card)) {
            return false;
        }
        if (!card.getNumber().equals(payment.getCardNumber())) {
            return false;
        }

        if (payment.getAmount() <= 0) {
            return false;
        }
        if (payment.getStatus() == PaymentStatus.SUCCESS){
            card.deposit(payment.getAmount());
            payment.markRefunded();
            return true;
        } else {
            return false;
        }


    }

    public boolean validateCard(Card card) {
    if (card == null){
        return false;
    }
    if (card.getNumber() == null || card.getNumber().isBlank()){
        return false;
    }
    if (card.getOwnerName() == null || card.getOwnerName().isBlank()) {
            return false;
    }
    if (card.getBalance() < 0){
        return false;
    }
    if (card.getLimit() <= 0) {
        return false;
    }
    return true;
    }


}
