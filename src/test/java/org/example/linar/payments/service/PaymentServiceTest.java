package org.example.linar.payments.service;

import org.example.linar.payments.model.Card;
import org.example.linar.payments.model.Payment;
import org.example.linar.payments.model.PaymentStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceTest {
    @Test
    void shouldPaySuccessfully() {
        Card card = new Card("123", "Linar", 1000, 500);
        Payment payment = new Payment(12, "123", 300);
        PaymentService paymentService = new PaymentService();

        boolean result = paymentService.pay(card, payment);

        assertTrue(result);
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
        assertEquals(700, card.getBalance());

    }
    @Test
    void paymentWithInsufficientBalanceShouldFail() {
        // Arrange
        Card card = new Card("1234", "Linar", 500, 2000);
        Payment payment = new Payment(2, "1234", 1000);
        PaymentService paymentService = new PaymentService();

        // Act
        boolean result = paymentService.pay(card, payment);

        // Assert
        assertFalse(result);
        assertEquals(500, card.getBalance());
        assertEquals(PaymentStatus.FAILED, payment.getStatus());
    }
    @Test
    void paymentEqualToBalanceShouldSucceed() {
        Card card = new Card("1234", "Linar", 500, 1000);
        Payment payment = new Payment(3, "1234", 500);
        PaymentService paymentService = new PaymentService();

        boolean result = paymentService.pay(card, payment);

        assertTrue(result);
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
        assertEquals(0, card.getBalance());
    }
    @Test
    void paymentExceedingLimitShouldFail() {
        Card card = new Card("1234", "Linar", 2000, 500);
        Payment payment = new Payment(3, "1234", 501);
        PaymentService paymentService = new PaymentService();

        boolean result = paymentService.pay(card, payment);

        assertFalse(result);
        assertEquals(PaymentStatus.FAILED, payment.getStatus());
        assertEquals(2000, card.getBalance());
    }
    @Test
    void paymentWithDifferentCardNumberShouldFail(){
        Card card = new Card("1111222233334444", "Linar", 1000, 500);
        Payment payment = new Payment(3, "5555666677778888", 300);
        PaymentService paymentService = new PaymentService();

        boolean result = paymentService.pay(card, payment);

        assertFalse(result);
        assertEquals(PaymentStatus.FAILED, payment.getStatus());
        assertEquals(1000, card.getBalance());
    }


    @ParameterizedTest (name = "Платеж с суммой {0} должен быть отклонен")
    @ValueSource (ints = {0, -1, -100})
    void paymentWithNonPositiveAmountShouldFail(int invalidAmount){
        Card card = new Card(
                "1111222233334444",
                "Linar",
                1000,
                500
        );
        Payment payment = new Payment(
                3,
                "1111222233334444",
                invalidAmount
        );
        PaymentService paymentService = new PaymentService();

        boolean result = paymentService.pay(card, payment);

        assertFalse(result);
        assertEquals(PaymentStatus.FAILED, payment.getStatus());
        assertEquals(1000, card.getBalance());
    }
}