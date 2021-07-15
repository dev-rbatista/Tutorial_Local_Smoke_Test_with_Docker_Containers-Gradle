package switchtwentytwenty.project.domain.aggregates.invoice;

import switchtwentytwenty.project.domain.aggregates.AggregateRoot;
import switchtwentytwenty.project.domain.valueobject.InvoiceID;
import switchtwentytwenty.project.domain.valueobject.ID;

public class Invoice implements AggregateRoot {

    private InvoiceID id;

    @Override
    public ID id() {
        return null;
    }

    @Override
    public boolean hasID(ID id) {
        return false;
    }
}
