package switchtwentytwenty.project.domain.aggregates.family;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switchtwentytwenty.project.domain.aggregates.AggregateRoot;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.exceptions.RelationAlreadyRegisteredException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class Family implements AggregateRoot<FamilyID> {

    private FamilyID id;
    @Getter
    private FamilyName name;
    @Getter
    private RegistrationDate registrationDate;
    @Getter
    private PersonID admin;
    @Getter
    @Setter
    private List<Relation> relations = new ArrayList<>();

    public Family(FamilyID familyID, FamilyName familyName, RegistrationDate registrationDate, PersonID adminEmail) {
        this.id = familyID;
        this.name = familyName;
        this.registrationDate = registrationDate;
        this.admin = adminEmail;

    }

    @Override
    public boolean hasID(FamilyID familyID) { //Implementado do Agregate Root ?
        return this.id.equals(familyID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Family)) return false;
        Family family = (Family) o;
        return id.equals(family.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isPersonTheAdmin(PersonID loggedUserID) {
        return loggedUserID.equals(this.admin);
    }

    @Override
    public FamilyID id() {
        return this.id;
    }

    public void addRelation(Relation relation) {
        if (!isRelationAlreadyRegistered(relation)) {
            this.relations.add(relation);
        } else {
            throw new RelationAlreadyRegisteredException();
        }
    }

    public Relation getRelationByID(RelationID relationID) {

        for (Relation registeredRelation : relations) {
            if (relationID.equals(registeredRelation.getId())) {
                return registeredRelation;
            }
        }
        throw new IllegalArgumentException();
    }

    public Relation changeRelation(RelationID relationID, RelationDesignation newDesignation) {
        Relation oldRelation = getRelationByID(relationID);
        Relation newRelation = new Relation(oldRelation.getMemberA(), oldRelation.getMemberB(), newDesignation, relationID);
        relations.remove(oldRelation);
        relations.add(newRelation);
        return newRelation;
    }

    public boolean isRelationAlreadyRegistered(Relation relation) {
        for (Relation registeredRelation : relations) {
            if (relation.equals(registeredRelation)) {
                return true;
            }
        }
        return false;
    }

    public List<Relation> getRelationsByPersonID(PersonID id) {
        List<Relation> personRelations = new ArrayList<>();
        for (Relation relation : this.relations) {
            if (relation.isMemberA(id)) {
                personRelations.add(relation);
            }
        }
        return personRelations;
    }
}