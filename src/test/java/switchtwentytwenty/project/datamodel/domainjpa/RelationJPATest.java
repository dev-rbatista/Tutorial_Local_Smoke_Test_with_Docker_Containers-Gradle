package switchtwentytwenty.project.datamodel.domainjpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelationJPATest {

    String PERSONIDONE = "tonyze@gmail.com";
    String PERSONIDTWO = "mariaze@gmail.com";
    String DESIGNATION = "amante";
    int ID = 123456;

    FamilyIDJPA FAMILYIDJPA = new FamilyIDJPA("@tonyze@gmail.com");
    String FAMILYNAME = "Zes";
    String REGISTRATIONDATE = "02/06/1992";
    PersonIDJPA PERSONIDJPA = new PersonIDJPA(PERSONIDONE);

    FamilyJPA FAMILYJPA = new FamilyJPA(FAMILYIDJPA, FAMILYNAME, REGISTRATIONDATE, PERSONIDJPA);

    @Test
    @DisplayName("Get PersonIDJPAOne from Relation")
    void getPersonIDJPAOne() {
        RelationJPA relationJPA = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);

        String expected = PERSONIDONE;

        String result = relationJPA.getPersonIDOne();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get PersonIDJPATwo from Relation")
    void getPersonIDJPATwo() {
        RelationJPA relationJPA = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);

        String expected = PERSONIDTWO;

        String result = relationJPA.getPersonIDTwo();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get designation from Relation")
    void getDesignation() {
        RelationJPA relationJPA = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);

        String expected = DESIGNATION;

        String result = relationJPA.getDesignation();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get id from Relation")
    void getId() {
        RelationJPA relationJPA = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);

        int expected = ID;

        int result = relationJPA.getId();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get FamilyJPA from relation")
    void getFamilyJPA() {
        RelationJPA relationJPA = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);

        FamilyJPA expected = new FamilyJPA(FAMILYIDJPA, FAMILYNAME, REGISTRATIONDATE, PERSONIDJPA);

        FamilyJPA result = relationJPA.getFamilyJPA();

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }

    @Test
    @DisplayName("Test if equals method works for same RelationJPA")
    void equalsSameRelationJPA() {
        RelationJPA relationJPAOne = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);
        RelationJPA relationJPATwo = relationJPAOne;

        assertEquals(relationJPAOne, relationJPATwo);
    }
    @Test
    @DisplayName("Test if equals method works for equal RelationJPA")
    void equalsEqualRelationJPA() {
        RelationJPA relationJPAOne = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);
        RelationJPA relationJPATwo = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);

        assertEquals(relationJPAOne, relationJPATwo);
        assertNotSame(relationJPAOne, relationJPATwo);
    }
    @Test
    @DisplayName("Test if equals method works for different PersonIDOne RelationJPA")
    void equalsDifferentPersonIDOneRelationJPA() {
        RelationJPA relationJPAOne = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);
        RelationJPA relationJPATwo = new RelationJPA("otherPersonIDOne", PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);

        assertNotEquals(relationJPAOne, relationJPATwo);
    }
    @Test
    @DisplayName("Test if equals method works for different PersonIDTwo RelationJPA")
    void equalsDifferentPersonIDTwoRelationJPA() {
        RelationJPA relationJPAOne = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);
        RelationJPA relationJPATwo = new RelationJPA(PERSONIDONE, "otherPersonIDTwo", DESIGNATION, ID, FAMILYJPA);

        assertNotEquals(relationJPAOne, relationJPATwo);
    }
    @Test
    @DisplayName("Test if equals method works for different designation RelationJPA")
    void equalsDifferentDesignationRelationJPA() {
        RelationJPA relationJPAOne = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);
        RelationJPA relationJPATwo = new RelationJPA(PERSONIDONE, PERSONIDTWO, "otherDesignation", ID, FAMILYJPA);

        assertNotEquals(relationJPAOne, relationJPATwo);
    }
    @Test
    @DisplayName("Test if equals method works for different id RelationJPA")
    void equalsDifferentIdRelationJPA() {
        RelationJPA relationJPAOne = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);
        RelationJPA relationJPATwo = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, 666, FAMILYJPA);

        assertNotEquals(relationJPAOne, relationJPATwo);
    }
    @Test
    @DisplayName("Test if equals method works for different FamilyJPA RelationJPA")
    void equalsDifferentFamilyJPARelationJPA() {
        RelationJPA relationJPAOne = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);

        FamilyJPA otherFamilyJPA = new FamilyJPA(new FamilyIDJPA("otherFamilyID"), FAMILYNAME, REGISTRATIONDATE, PERSONIDJPA);
        RelationJPA relationJPATwo = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, otherFamilyJPA);

        assertNotEquals(relationJPAOne, relationJPATwo);
    }
    @Test
    @DisplayName("Test if equals method works for different Object Types")
    void equalsTestComparingDifferentObject() {
        RelationJPA  relationJPA = new RelationJPA();
        String notARelationJPA = "not a relation JPA";

        assertNotEquals(relationJPA, notARelationJPA);
    }
    @Test
    @DisplayName("Test if hashcode method works for equal RelationJPA")
    void hashCodeSameEqualRelationJPA() {
        RelationJPA relationJPAOne = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);
        RelationJPA relationJPATwo = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);

        assertEquals(relationJPAOne.hashCode(), relationJPATwo.hashCode());
        assertNotSame(relationJPAOne.hashCode(), relationJPATwo.hashCode());
    }
    @Test
    @DisplayName("Test if hashcode method works for different id RelationJPA")
    void hashCodeDifferentRelationJPA() {
        RelationJPA relationJPAOne = new RelationJPA(PERSONIDONE, PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);
        RelationJPA relationJPATwo = new RelationJPA("other personIDOne", PERSONIDTWO, DESIGNATION, ID, FAMILYJPA);

        assertNotEquals(relationJPAOne.hashCode(), relationJPATwo.hashCode());
    }

}