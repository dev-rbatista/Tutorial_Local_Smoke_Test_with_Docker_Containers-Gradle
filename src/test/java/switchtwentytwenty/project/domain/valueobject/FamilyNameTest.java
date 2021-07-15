package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FamilyNameTest {

    @Test
    @Tag("US010")
    void familyNameConstructorTest_Valid() {
        //Arrange
        String familyName = "Ribeiro";
        //Act
        FamilyName newFamilyName = new FamilyName(familyName);
        //Assert
        assertNotNull(newFamilyName);
    }

    @Test
    @Tag("US010")
    void familyNameConstructorTest_ValidTrimmedString() {
        //Arrange
        String familyName = "   Ribeiro   ";
        String expectedString = "Ribeiro";
        FamilyName expected = new FamilyName(expectedString);
        //Act
        FamilyName result = new FamilyName(familyName);
        //Assert
        assertEquals(expected,result);
    }

    @Test
    @Tag("US010")
    void familyNameConstructorTest_InvalidNull() {
        //Arrange
        String familyName = null;
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new FamilyName(familyName)
        );
    }

    @Test
    @Tag("US010")
    void familyNameConstructorTest_InvalidEmpty() {
        //Arrange
        String familyName = "";
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new FamilyName(familyName)
        );
    }

    @Test
    @Tag("US010")
    void familyNameConstructorTest_InvalidBlank() {
        //Arrange
        String familyName = "  ";
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new FamilyName(familyName)
        );
    }

    @Test
    @Tag("US010")
    void equalsTest_equalNotSame() {
        String nameOne = "Ribeiro";
        FamilyName familyNameOne = new FamilyName(nameOne);
        FamilyName familyNameTwo = new FamilyName(nameOne);

        assertEquals(familyNameOne, familyNameTwo);
    }

    @Test
    @Tag("US010")
    void equalsTest_equalSame() {
        String nameOne = "Ribeiro";
        FamilyName familyNameOne = new FamilyName(nameOne);
        FamilyName familyNameTwo = familyNameOne;

        assertEquals(familyNameOne, familyNameTwo);
    }

    @Test
    @Tag("US010")
    void equalsTest_notEqual() {
        String nameOne = "Ribeiro";
        FamilyName familyNameOne = new FamilyName(nameOne);
        String nameTwo = "Moreira";
        FamilyName familyNameTwo = new FamilyName(nameTwo);

        assertNotEquals(familyNameOne, familyNameTwo);
    }

    @Test
    @Tag("US010")
    void equalsTest_notEqualDifferentObject() {
        String nameOne = "Ribeiro";
        FamilyName familyNameOne = new FamilyName(nameOne);
        String notFamilyName = "notFamilyName";

        assertNotEquals(familyNameOne, notFamilyName);
    }

    @Test
    @Tag("US010")
    void hashCodeTest_sameHashCode() {
        String nameOne = "Ribeiro";
        FamilyName familyNameOne = new FamilyName(nameOne);
        FamilyName familyNameTwo = new FamilyName(nameOne);

        assertEquals(familyNameOne.hashCode(), familyNameTwo.hashCode());
    }

    @Test
    @Tag("US010")
    void hashCodeTest_differentHashCode() {
        String nameOne = "Ribeiro";
        FamilyName familyNameOne = new FamilyName(nameOne);
        String nameTwo = "Moreira";
        FamilyName familyNameTwo = new FamilyName(nameTwo);

        assertNotEquals(familyNameOne.hashCode(), familyNameTwo.hashCode());
    }

    @Test
    void familyNametoStringTest (){
        String name = "Ribeiro";
        FamilyName familyName = new FamilyName(name);

        String result = familyName.toString();

        assertEquals(name,result);
    }
}