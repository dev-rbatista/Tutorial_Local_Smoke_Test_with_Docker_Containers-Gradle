package switchtwentytwenty.project.domain.aggregates.invoice;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.valueobject.ID;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {
    @Test
    void temporaryConstructorTest() {
        Invoice invoice = new Invoice();
        assertNotNull(invoice);
    }

    @Test
    void temporaryIDTest() {
        Invoice invoice = new Invoice();
        ID result = invoice.id();
        assertNull(result);
    }

    @Test
    void temporaryHasIDTest() {
        Invoice invoice = new Invoice();
        boolean result = invoice.hasID(null);
        assertFalse(result);
    }
}