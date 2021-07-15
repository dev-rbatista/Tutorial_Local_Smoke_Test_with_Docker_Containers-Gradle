package switchtwentytwenty.project.domain.valueobject;

import java.util.UUID;

public class InvoiceID implements ID {

    private final UUID id;

    public InvoiceID(UUID id) {
        this.id = id;
    }
}
