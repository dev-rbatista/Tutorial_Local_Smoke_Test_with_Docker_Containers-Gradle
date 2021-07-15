package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceIDTest {

    @Test
    void constructorTest (){
        InvoiceID id = new InvoiceID(UUID.randomUUID());

        assertNotNull(id);
    }
}