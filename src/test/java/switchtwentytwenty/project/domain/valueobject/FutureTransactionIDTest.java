package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FutureTransactionIDTest {

    @Test
    void constructorTest (){
        FutureTransactionID id = new FutureTransactionID(UUID.randomUUID());

        assertNotNull(id);
    }

}