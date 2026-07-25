package org.example.linar.payments.service;
import org.example.linar.payments.model.Card;
import org.example.linar.payments.model.Payment;
import org.example.linar.payments.model.PaymentStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


}
