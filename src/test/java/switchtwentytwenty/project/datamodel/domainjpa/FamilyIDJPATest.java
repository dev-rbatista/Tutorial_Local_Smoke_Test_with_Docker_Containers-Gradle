package switchtwentytwenty.project.datamodel.domainjpa;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FamilyIDJPATest {

    String familyID = "1";
    String familyIDTwo = "2";

    @Tag("US010")
    @Test
    void equalsTestSameFamilyIDJPA() {
        FamilyIDJPA familyIDJPAOne = new FamilyIDJPA(familyID);
        FamilyIDJPA familyIDJPATwo = familyIDJPAOne;

        assertEquals(familyIDJPAOne, familyIDJPATwo);
    }

    @Tag("US010")
    @Test
    void equalsTestEqualFamilyIDJPAs() {
        FamilyIDJPA familyIDJPA = new FamilyIDJPA(familyID);
        FamilyIDJPA familyIDJPATwo = new FamilyIDJPA(familyID);

        assertEquals(familyIDJPA, familyIDJPATwo);
    }

    @Tag("US010")
    @Test
    void equalsTestDifferentFamilyIDJPAs() {
        FamilyIDJPA familyIDJPA = new FamilyIDJPA(familyID);
        FamilyIDJPA familyIDJPATwo = new FamilyIDJPA(familyIDTwo);

        assertNotEquals(familyIDJPA, familyIDJPATwo);
    }

    @Tag("US010")
    @Test
    void equalsTestDifferentObjects() {
        FamilyIDJPA familyIDJPA = new FamilyIDJPA(familyID);

        assertNotEquals(familyIDJPA, familyIDTwo);
    }

    @Tag("US010")
    @Test
    void equalsTestDifferentFromNull() {
        FamilyIDJPA familyIDJPA = new FamilyIDJPA(familyID);
        String nullString = null;

        assertNotEquals(familyIDJPA, nullString);
    }

    @Tag("US010")
    @Test
    void testToString() {
        String expected = "1";

        FamilyIDJPA familyIDJPA = new FamilyIDJPA(familyID);

        String result = familyIDJPA.toString();

        assertEquals(expected, result);
    }

    @Tag("US010")
    @Test
    void getFamilyID() {
        String expected = "1";

        FamilyIDJPA familyIDJPA = new FamilyIDJPA(familyID);


        String result = familyIDJPA.getFamilyID();

        assertEquals(expected, result);

    }

    @Test
    void setFamilyID() {
        FamilyIDJPA familyIDJPA = new FamilyIDJPA(familyID);
        String expected = "2";

        familyIDJPA.setFamilyID("2");
        String result = familyIDJPA.getFamilyID();

        assertEquals(expected,result);
    }

    @Test
    void hashCodeEqualFamilies() {
        FamilyIDJPA familyIDJPA = new FamilyIDJPA(familyID);
        FamilyIDJPA familyIDJPATwo = new FamilyIDJPA(familyID);

        assertEquals(familyIDJPA.hashCode(), familyIDJPATwo.hashCode());
        assertNotSame(familyIDJPA, familyIDJPATwo);
    }

    @Test
    void hashCodeDifferentFamilies() {
        FamilyIDJPA familyIDJPA = new FamilyIDJPA(familyID);
        FamilyIDJPA familyIDJPATwo = new FamilyIDJPA(familyIDTwo);

        assertNotEquals(familyIDJPA.hashCode(), familyIDJPATwo.hashCode());
    }
}