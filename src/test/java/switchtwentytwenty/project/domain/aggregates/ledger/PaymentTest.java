package switchtwentytwenty.project.domain.aggregates.ledger;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.valueobject.ID;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void temporaryConstructorTest() {
        Payment payment = new Payment();
        assertNotNull(payment);
    }

    @Test
    void temporaryIDTest() {
        Payment payment = new Payment();
        ID result = payment.id();
        assertNull(result);
    }

    @Test
    void temporaryHasIDTest() {
        Payment payment = new Payment();
        boolean result = payment.hasID(null);
        assertFalse(result);
    }
}