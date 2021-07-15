package switchtwentytwenty.project.domain.valueobject;

import java.util.UUID;

public class LedgerID implements ID {

    private final UUID id;

    public LedgerID(UUID id) {
        this.id = id;
    }

}
