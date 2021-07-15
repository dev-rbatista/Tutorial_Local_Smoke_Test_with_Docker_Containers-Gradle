package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RelationDesignationTest {

    String designation = "Parent";

    @Test
    void equalsTestSameRelationDesignation(){
        RelationDesignation relationDesignationOne = new RelationDesignation(designation);
        RelationDesignation relationDesignationTwo = relationDesignationOne;

        assertEquals(relationDesignationOne,relationDesignationTwo);
    }

    @Test
    void equalsTestDifferentTypesOfObject(){
        RelationDesignation relationDesignation = new RelationDesignation(designation);
        LocalDate notARelationDesignation = LocalDate.of(2001,1,1);

        assertNotEquals(relationDesignation,notARelationDesignation);
    }

    @Test
    void equalsTestDifferentFromNull(){
        RelationDesignation relationDesignation = new RelationDesignation(designation);
        String nullString = null;

        assertNotEquals(relationDesignation,nullString);
    }

    @Test
    void equalsTestEqualRelationDesignations(){
        RelationDesignation relationDesignationOne = new RelationDesignation(designation);
        RelationDesignation relationDesignationTwo = new RelationDesignation(designation);

        assertEquals(relationDesignationOne,relationDesignationTwo);
        assertNotSame(relationDesignationOne,relationDesignationTwo);
    }

    @Test
    void equalsTestDifferentRelationDesignations(){
        RelationDesignation relationDesignationOne = new RelationDesignation(designation);
        String otherDesignation = "Cousin";
        RelationDesignation relationDesignationTwo = new RelationDesignation(otherDesignation);

        assertNotEquals(relationDesignationOne,relationDesignationTwo);
    }

    @Test
    void hashCodeTest_sameHashCode() {
        RelationDesignation relationDesignationOne = new RelationDesignation(designation);
        RelationDesignation relationDesignationTwo = new RelationDesignation(designation);

        assertEquals(relationDesignationOne.hashCode(),relationDesignationTwo.hashCode());
        assertNotSame(relationDesignationOne,relationDesignationTwo);
    }

    @Test
    void hashCodeTest_differentHashCode() {
        RelationDesignation relationDesignationOne = new RelationDesignation(designation);
        String otherDesignation = "Cousin";
        RelationDesignation relationDesignationTwo = new RelationDesignation(otherDesignation);

        assertNotEquals(relationDesignationOne.hashCode(),relationDesignationTwo.hashCode());
    }

}