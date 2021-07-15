package switchtwentytwenty.project.datamodel.domainjpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class FamilyIDJPA implements Serializable {
    @Getter
    @Setter
    private String familyID;

    public FamilyIDJPA(String familyID) {
        this.familyID = familyID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyIDJPA that = (FamilyIDJPA) o;
        return familyID.equals(that.familyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyID);
    }

    @Override
    public String toString() {
        return this.familyID;
    }
}