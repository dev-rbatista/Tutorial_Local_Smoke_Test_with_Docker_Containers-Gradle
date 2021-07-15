package switchtwentytwenty.project.domain.valueobject;


import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

public class Relation implements ValueObject, Serializable {
    @Getter
    private final PersonID memberA;
    @Getter
    private final PersonID memberB;
    @Getter
    private final RelationDesignation relationDesignation;
    @Getter
    private RelationID id;

    public Relation(PersonID memberA, PersonID memberB, RelationDesignation designation) {
        this.memberA = memberA;
        this.memberB = memberB;
        validateRelation();
        this.relationDesignation = designation;
        this.id = new RelationID(hashCode());
    }

    public Relation(PersonID memberA, PersonID memberB, RelationDesignation designation, RelationID relationID) {
        this.memberA = memberA;
        this.memberB = memberB;
        validateRelation();
        this.relationDesignation = designation;
        this.id = relationID;
    }

    private void validateRelation() {
        if (memberA.equals(memberB))
            throw new IllegalArgumentException("You entered the same person twice. A relation requires two different people");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relation relation = (Relation) o;
        return (memberA.equals(relation.memberA) && memberB.equals(relation.memberB)) || (memberA.equals(relation.memberB) && memberB.equals(relation.memberA));
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberA, memberB);
    }

    public boolean isMemberA(PersonID id) {
        return this.memberA.equals(id);
    }
}
