package switchtwentytwenty.project.domain.valueobject;

import java.util.UUID;

public class FutureTransactionID implements ID {

    private final UUID id;

    public FutureTransactionID(UUID id) {
        this.id = id;
    }
}
