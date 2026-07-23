package org.example.linar.payments.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class PaymentTest {
        @Test
        void newPaymentShouldHaveCreatedStatus(){
            Payment payment = new Payment(123, "2122121", 123);
            PaymentStatus actualStatus = payment.getStatus();
            assertEquals(PaymentStatus.CREATED, actualStatus);

    }
        @Test
    void createdPaymentShouldBecomeSuccess(){
            Payment payment = new Payment(1234,"12342", 1000);
            payment.markSuccess();
            assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
        }
        @Test
    void createdPaymentShouldBecomeFailed(){
            Payment payment = new Payment(1234, "1234", 1000);
            payment.markFailed();
            assertEquals(PaymentStatus.FAILED, payment.getStatus());
        }
        @Test
    void successfulPaymentShouldBecomeRefunded(){
            Payment payment = new Payment(1234, "1234", 1000);
            payment.markSuccess();
            payment.markRefunded();
            assertEquals(PaymentStatus.REFUNDED, payment.getStatus());
        }
        @Test
    void successfulPaymentShouldNotBecomeFailed(){
            Payment payment = new Payment(1234, "1234", 1000);
            payment.markSuccess();
            payment.markFailed();
            assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
        }
        @Test
    void createdPaymentShouldNotBecomeRefunded() {
        Payment payment = new Payment(1234, "1234", 1000);

        payment.markRefunded();

        assertEquals(PaymentStatus.CREATED, payment.getStatus());
    }
}
