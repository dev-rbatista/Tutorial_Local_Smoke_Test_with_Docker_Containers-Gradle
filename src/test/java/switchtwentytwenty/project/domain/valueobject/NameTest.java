package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import switchtwentytwenty.project.exceptions.InvalidNameException;

import static org.junit.jupiter.api.Assertions.*;

public class NameTest {

    @ParameterizedTest
    @Tag("US010")
    @ValueSource(strings = {"  "})
    @NullAndEmptySource
    @DisplayName("Test if a Name Object throws an error if the String is empty, blank or null")
    void shouldThrowIfNameNullBlankEmpty(String value) {
        assertThrows(InvalidNameException.class, () -> new Name(value));
    }

    @Test
    void constructorTest(){
        String name = "Jessica";
        Name result = new Name(name);

        assertNotNull(result);
    }

    @Test
    @Tag("US010")
    void equalsTestEqualName() {
        String nameOne = "Jessica";
        Name personNameOne = new Name(nameOne);
        Name personNameTwo = new Name(nameOne);

        assertEquals(personNameOne, personNameTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestSameName() {
        String nameOne = "Jessica";
        Name personNameOne = new Name(nameOne);
        Name personNameTwo = personNameOne;

        assertEquals(personNameOne, personNameTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentName() {
        String nameOne = "Jessica";
        Name personNameOne = new Name(nameOne);
        String nameTwo = "TonyZe";
        Name personNameTwo = new Name(nameTwo);

        assertNotEquals(personNameOne, personNameTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentObject() {
        String nameOne = "Jessica";
        Name personName = new Name(nameOne);
        String notPersonName = "notFamilyName";

        assertNotEquals(personName, notPersonName);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentFromNull() {
        String nameOne = "Jessica";
        Name personName = new Name(nameOne);
        String nullString = null;

        assertNotEquals(personName,nullString);
    }

    @Test
    @Tag("US010")
    void hashCodeTestSameHashCode() {
        String nameOne = "Jessica";
        Name personNameOne = new Name(nameOne);
        Name personNameTwo = new Name(nameOne);

        assertEquals(personNameOne.hashCode(), personNameTwo.hashCode());
    }

    @Test
    @Tag("US010")
    void hashCodeTestDifferentHashCode() {
        String nameOne = "Jessica";
        Name personNameOne = new Name(nameOne);
        String nameTwo = "TonyZe";
        Name personNameTwo = new Name(nameTwo);

        assertNotEquals(personNameOne.hashCode(), personNameTwo.hashCode());
    }

}
