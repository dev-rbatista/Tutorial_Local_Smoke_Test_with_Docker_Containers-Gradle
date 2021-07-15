package switchtwentytwenty.project.usecaseservices.irepositories;

import switchtwentytwenty.project.domain.aggregates.Entity;
import switchtwentytwenty.project.domain.valueobject.ID;

public interface Repository<T extends Entity,K extends ID>{

    /**
     *
     * @param id
     * @return
     */
    public T getByID(K id);


    T add(T entity);
}