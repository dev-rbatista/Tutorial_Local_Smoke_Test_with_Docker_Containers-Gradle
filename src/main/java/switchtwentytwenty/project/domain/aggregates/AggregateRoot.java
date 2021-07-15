package switchtwentytwenty.project.domain.aggregates;

import switchtwentytwenty.project.domain.valueobject.ID;

public interface AggregateRoot<K extends ID> extends Entity<K> {
    //tirar K extends ID porque o AggregateRoot extende o Entity...?????

}
