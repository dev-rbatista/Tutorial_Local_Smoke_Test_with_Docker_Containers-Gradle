package switchtwentytwenty.project.domain.aggregates;

import switchtwentytwenty.project.domain.valueobject.ID;

public interface Entity <K extends ID> {

    public K id();

    public boolean hasID(K id);

}
