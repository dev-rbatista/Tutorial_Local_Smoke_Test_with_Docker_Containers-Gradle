package switchtwentytwenty.project.datamodel.domainjpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "families")
public class FamilyJPA {


    @Id
    private FamilyIDJPA id;

    private String familyName;

    private String registrationDate;

    private PersonIDJPA adminID;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "familyJPA", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<RelationJPA> relationList = new ArrayList<>();

    public FamilyJPA(FamilyIDJPA id, String familyName, String registrationDate, PersonIDJPA adminID) {
        this.id = id;
        this.familyName = familyName;
        this.registrationDate = registrationDate;
        this.adminID = adminID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyJPA familyJPA = (FamilyJPA) o;
        return Objects.equals(id, familyJPA.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}