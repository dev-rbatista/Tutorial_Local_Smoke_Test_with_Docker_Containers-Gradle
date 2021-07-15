package switchtwentytwenty.project.domain.aggregates.ledger;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.valueobject.ID;

import static org.junit.jupiter.api.Assertions.*;

class TransferTest {

    @Test
    void temporaryConstructorTest() {
        Transfer transfer = new Transfer();
        assertNotNull(transfer);
    }

    @Test
    void temporaryIDTest() {
        Transfer transfer = new Transfer();
        ID result = transfer.id();
        assertNull(result);
    }

    @Test
    void temporaryHasIDTest() {
        Transfer transfer = new Transfer();
        boolean result = transfer.hasID(null);
        assertFalse(result);
    }
}