package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import switchtwentytwenty.project.exceptions.InvalidStreetException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StreetTest {

    final String VALIDSTREET = "Rua da Amargura";

    @Test
    @Tag("US010")
    void constructorTestValidData(){
        Street result = new Street(VALIDSTREET);
        assertNotNull(result);
    }

    @ParameterizedTest
    @Tag("US010")
    @ValueSource(strings = {"  "})
    @NullAndEmptySource
    @DisplayName("Test if a Street Object throws an error if the String is empty, blank or null")
    void constructorTestInValidData(String value){
        assertThrows(InvalidStreetException.class, () -> new Street(value));
    }

    @Test
    @Tag("US010")
    void equalsTestSameStreet(){
        Street streetOne = new Street(VALIDSTREET);
        Street streetTwo = streetOne;

        assertEquals(streetOne,streetTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentTypesOfObject(){
        Street streetOne = new Street(VALIDSTREET);
        LocalDate notAStreet = LocalDate.of(2001,1,1);

        assertNotEquals(streetOne,notAStreet);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentFromNull(){
        Street streetOne = new Street(VALIDSTREET);
        String nullString = null;

        assertNotEquals(streetOne,nullString);
    }

    @Test
    @Tag("US010")
    void equalsTestEqualStreets(){
        Street streetOne = new Street(VALIDSTREET);
        Street streetTwo = new Street(VALIDSTREET);

        assertEquals(streetOne,streetTwo);
        assertNotSame(streetOne,streetTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentStreets(){
        Street streetOne = new Street(VALIDSTREET);
        Street streetTwo = new Street("Rua de Cima");

        assertNotEquals(streetOne,streetTwo);
    }

    @Test
    @Tag("US010")
    void hashCodeTest_sameHashCode() {
        Street streetOne = new Street(VALIDSTREET);
        Street streetTwo = new Street(VALIDSTREET);

        assertEquals(streetOne.hashCode(), streetTwo.hashCode());
        assertNotSame(streetOne,streetTwo);
    }

    @Test
    @Tag("US010")
    void hashCodeTest_differentHashCode() {
        Street streetOne = new Street(VALIDSTREET);
        Street streetTwo = new Street("Rua de Cima");

        assertNotEquals(streetOne.hashCode(), streetTwo.hashCode());
    }
}