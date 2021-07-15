package switchtwentytwenty.project.domain.aggregates.ledger;

import switchtwentytwenty.project.domain.valueobject.ID;

public class Payment implements Transaction {

    @Override
    public ID id() {
        return null;
    }

    @Override
    public boolean hasID(ID id) {
        return false;
    }
}
