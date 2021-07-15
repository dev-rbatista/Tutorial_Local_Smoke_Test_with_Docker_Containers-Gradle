package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class RelationTest {

    PersonID personOneID = new PersonID("admin@gmail.com");
    PersonID personTwoID = new PersonID("parent@gmail.com");
    RelationDesignation designation = new RelationDesignation("Parent");
    RelationID relationID = new RelationID(125);

    @Test
    void constructorTest() {
        Relation relation = new Relation(personOneID, personTwoID, designation);

        assertNotNull(relation);
    }

    @Test
    void allArgsConstructorTest() {
        Relation relation = new Relation(personOneID, personTwoID, designation, relationID);

        assertNotNull(relation);
    }

    @Test
    void equalsTestSameRelation() {
        Relation relationOne = new Relation(personOneID, personTwoID, designation);
        Relation relationTwo = relationOne;

        assertEquals(relationOne, relationTwo);
    }

    @Test
    void equalsTestDifferentTypesOfObject() {
        Relation relation = new Relation(personOneID, personTwoID, designation);
        LocalDate notARelation = LocalDate.of(2001, 1, 1);

        assertNotEquals(relation, notARelation);
    }

    @Test
    void equalsTestDifferentFromNull() {
        Relation relation = new Relation(personOneID, personTwoID, designation);
        String nullString = null;

        assertNotEquals(relation, nullString);
    }

    @Test
    void equalsTestEqualRelations() {
        Relation relationOne = new Relation(personOneID, personTwoID, designation);
        Relation relationTwo = new Relation(personOneID, personTwoID, designation);

        assertEquals(relationOne, relationTwo);
        assertNotSame(relationOne, relationTwo);
    }


    @Test
    void equalsTestEqualRelationsMembersInDifferentOrder() {
        Relation relationOne = new Relation(personOneID, personTwoID, designation);
        Relation relationTwo = new Relation(personTwoID, personOneID, designation);

        assertEquals(relationOne, relationTwo);
        assertNotSame(relationOne, relationTwo);
    }

    @Test
    void equalsTestDifferentFamilyMemberOne() {
        Relation relationOne = new Relation(personOneID, personTwoID, designation);
        PersonID otherPersonOneID = new PersonID("otheremail@hotmail.com");
        Relation relationTwo = new Relation(otherPersonOneID, personTwoID, designation);

        assertNotEquals(relationOne, relationTwo);
    }

    @Test
    void equalsTestDifferentFamilyMemberTwo() {
        Relation relationOne = new Relation(personOneID, personTwoID, designation);
        PersonID otherPersonTwoID = new PersonID("otheremail@hotmail.com");
        Relation relationTwo = new Relation(personOneID, otherPersonTwoID, designation);

        assertNotEquals(relationOne, relationTwo);
    }

    @Test
    void equalsTestDifferentFamilyMemberOneDifferentOrder() {
        Relation relationOne = new Relation(personOneID, personTwoID, designation);
        PersonID otherPersonOneID = new PersonID("otheremail@hotmail.com");
        Relation relationTwo = new Relation(personTwoID, otherPersonOneID, designation);

        assertNotEquals(relationOne, relationTwo);
    }

    @Test
    void equalsTestDifferentFamilyMemberTwoDifferentOrder() {
        Relation relationOne = new Relation(personOneID, personTwoID, designation);
        PersonID otherPersonTwoID = new PersonID("otheremail@hotmail.com");
        Relation relationTwo = new Relation(otherPersonTwoID, personOneID, designation);

        assertNotEquals(relationOne, relationTwo);
    }

    @Test
    void hashCodeTest_sameHashCode() {
        Relation relationOne = new Relation(personOneID, personTwoID, designation);
        int expected = relationOne.hashCode();
        int result = Objects.hash(personOneID, personTwoID);

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }

    @Test
    void hashCodeTest_differentHashCode() {
        Relation relationOne = new Relation(personOneID, personTwoID, designation);
        PersonID otherPersonOneID = new PersonID("otheremail@hotmail.com");
        Relation relationTwo = new Relation(otherPersonOneID, personTwoID, designation);

        assertNotEquals(relationOne.hashCode(), relationTwo.hashCode());
    }

    @Test
    void getId() {
        Relation relationOne = new Relation(personOneID, personTwoID, designation);

        RelationID expected = new RelationID(Objects.hash(personOneID, personTwoID));

        RelationID result = relationOne.getId();

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }

    @Test
    void getMemberATest() {
        Relation relation = new Relation(personOneID, personTwoID, designation, relationID);
        PersonID expected = new PersonID("admin@gmail.com");

        PersonID result = relation.getMemberA();

        assertEquals(expected, result);
    }

    @Test
    void getMemberBTest() {
        Relation relation = new Relation(personOneID, personTwoID, designation, relationID);
        PersonID expected = new PersonID("parent@gmail.com");

        PersonID result = relation.getMemberB();

        assertEquals(expected, result);
    }

    @Test
    void getRelationDesignationTest() {
        Relation relation = new Relation(personOneID, personTwoID, designation, relationID);
        RelationDesignation expected = new RelationDesignation("Parent");

        RelationDesignation result = relation.getRelationDesignation();

        assertEquals(expected, result);
    }

    @Test
    void isMemberA() {
        Relation relation = new Relation(personOneID, personTwoID, designation, relationID);
        assertTrue(relation.isMemberA(personOneID));
        assertFalse(relation.isMemberA(personTwoID));
    }
    @Test
    void validateRelationTestInvalidRelationWithSameFamilyMember(){
        PersonID personOneID = new PersonID("admin@gmail.com");

        assertThrows(IllegalArgumentException.class,()->new Relation(personOneID,personOneID,designation));
    }

    @Test
    void validateRelationTestInvalidRelationWithSameFamilyMemberAllArgsConstructor(){
        PersonID personOneID = new PersonID("admin@gmail.com");

        assertThrows(IllegalArgumentException.class,()->new Relation(personOneID,personOneID,designation,new RelationID(20200)));
    }

}