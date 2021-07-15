package switchtwentytwenty.project.datamodel.domainjpa;


import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Objects;

@ToString
@NoArgsConstructor
@Embeddable
public class CategoryIDJPA implements Serializable {

    @GeneratedValue
    private long id;

    public CategoryIDJPA(long id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryIDJPA that = (CategoryIDJPA) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
