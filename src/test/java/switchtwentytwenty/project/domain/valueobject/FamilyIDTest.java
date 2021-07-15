package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FamilyIDTest {

    @Test
    @Tag("US010")
    void familyIDConstructorTest_Valid() {
        //Act
        FamilyID newFamilyID = new FamilyID("admin@gmail.com");
        //Assert
        assertNotNull(newFamilyID);
    }

    @Test
    @Tag("US010")
    void familyIDConstructorTest_InvalidNull() {
        //Arrange
        String familyID = null;
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new FamilyID(familyID)
        );
    }

    @Test
    @Tag("US010")
    void equalsTest_equalNotSame() {
        FamilyID familyIDOne = new FamilyID("admin@gmail.com");
        FamilyID familyIDTwo = new FamilyID("admin@gmail.com");

        assertNotSame(familyIDOne, familyIDTwo);
        assertEquals(familyIDOne, familyIDTwo);
    }

    @Test
    @Tag("US010")
    void equalsTest_equalSame() {
        FamilyID familyIDOne = new FamilyID("admin@gmail.com");
        FamilyID familyIDTwo = familyIDOne;

        assertSame(familyIDOne, familyIDTwo);
        assertEquals(familyIDOne, familyIDTwo);
    }

    @Test
    @Tag("US010")
    void equalsTest_notEqual() {
        FamilyID familyIDOne = new FamilyID("admin@gmail.com");
        FamilyID familyIDTwo = new FamilyID("admin2@gmail.com");

        assertNotEquals(familyIDOne, familyIDTwo);
    }

    @Test
    @Tag("US010")
    void equalsTest_notEqualDifferentObject() {
        FamilyID familyIDOne = new FamilyID("admin@gmail.com");
        String notFamilyID = "notFamilyID";

        assertNotEquals(familyIDOne, notFamilyID);
    }

    @Test
    @Tag("US010")
    void hashCodeTest_sameHashCode() {
        FamilyID familyIDOne = new FamilyID("admin@gmail.com");
        FamilyID familyIDTwo = new FamilyID("admin@gmail.com");

        assertEquals(familyIDOne.hashCode(), familyIDTwo.hashCode());
    }

    @Test
    @Tag("US010")
    void hashCodeTest_differentHashCode() {
        FamilyID familyIDOne = new FamilyID("admin@gmail.com");
        FamilyID familyIDTwo = new FamilyID("admin2@gmail.com");

        assertNotEquals(familyIDOne.hashCode(), familyIDTwo.hashCode());
    }

    @Test
    void setFamilyIDTest(){
        FamilyID familyID = new FamilyID();
        String expected = "admin@gmail.com";

        familyID.setId("admin@gmail.com");
        String result = familyID.toString();

        assertNotNull(familyID);
        assertEquals(expected,result);
    }

    @Test
    void getFamilyIDTest(){
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String expected = "@admin@gmail.com";

        String result = familyID.getId();

        assertEquals(expected,result);
    }

    @Test
    void getFamilyIDTestAlreadyChanged(){
        FamilyID familyID = new FamilyID("@admin@gmail.com");
        String expected = "@admin@gmail.com";

        String result = familyID.getId();

        assertEquals(expected,result);
    }



}