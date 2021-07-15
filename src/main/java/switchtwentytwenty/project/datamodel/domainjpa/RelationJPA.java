package switchtwentytwenty.project.datamodel.domainjpa;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "relations")
public class RelationJPA {

    private String personIDOne;
    private String personIDTwo;
    private String designation;

    @Id
    private int id;

    @ManyToOne()
    @JoinColumn(name = "family", nullable = true)
    private FamilyJPA familyJPA;

    public RelationJPA(String personIDOne, String personIDTwo, String designation, int id, FamilyJPA familyJPA) {
        this.personIDOne = personIDOne;
        this.personIDTwo = personIDTwo;
        this.designation = designation;
        this.id = id;
        this.familyJPA = familyJPA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelationJPA)) return false;
        RelationJPA that = (RelationJPA) o;
        return id == that.id && Objects.equals(personIDOne, that.personIDOne) && Objects.equals(personIDTwo, that.personIDTwo) && Objects.equals(designation, that.designation) && Objects.equals(familyJPA, that.familyJPA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personIDOne, personIDTwo, designation, id, familyJPA);
    }
}
