package switchtwentytwenty.project.domain.aggregates.ledger;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.valueobject.ID;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LedgerTest {

    @Test
    void temporaryConstructorTest() {
        Ledger ledger = new Ledger(UUID.randomUUID());
        assertNotNull(ledger);
    }

    @Test
    void temporaryIDTest() {
        Ledger ledger = new Ledger(UUID.randomUUID());
        ID result = ledger.id();
        assertNull(result);
    }

    @Test
    void temporaryHasIDTest() {
        Ledger ledger = new Ledger(UUID.randomUUID());
        boolean result = ledger.hasID(null);
        assertFalse(result);
    }
}