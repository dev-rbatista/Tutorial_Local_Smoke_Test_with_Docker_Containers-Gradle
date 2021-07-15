package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LedgerIDTest {

    @Test
    void constructorTest (){
        LedgerID id = new LedgerID(UUID.randomUUID());

        assertNotNull(id);
    }
}