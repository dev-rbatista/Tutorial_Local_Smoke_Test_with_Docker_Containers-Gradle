package switchtwentytwenty.project.datamodel.domainjpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class OwnerIDJPA {

    @Getter
    @Setter
    private String ownerID;

    public OwnerIDJPA(String ownerID){
        this.ownerID = ownerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OwnerIDJPA)) return false;
        OwnerIDJPA that = (OwnerIDJPA) o;
        return Objects.equals(ownerID, that.ownerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerID);
    }

    @Override
    public String toString() {
        return this.ownerID;
    }
}
