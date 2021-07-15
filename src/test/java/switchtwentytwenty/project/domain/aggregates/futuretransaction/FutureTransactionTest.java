package switchtwentytwenty.project.domain.aggregates.futuretransaction;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.valueobject.ID;

import static org.junit.jupiter.api.Assertions.*;

class FutureTransactionTest {
    @Test
    void temporaryConstructorTest() {
        FutureTransaction futureTransaction = new FutureTransaction();
        assertNotNull(futureTransaction);
    }

    @Test
    void temporaryIDTest() {
        FutureTransaction futureTransaction = new FutureTransaction();
        ID result = futureTransaction.id();
        assertNull(result);
    }

    @Test
    void temporaryHasIDTest() {
        FutureTransaction futureTransaction = new FutureTransaction();
        boolean result = futureTransaction.hasID(null);
        assertFalse(result);
    }
}