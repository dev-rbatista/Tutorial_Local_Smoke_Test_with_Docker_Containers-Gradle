package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import switchtwentytwenty.project.exceptions.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BirthDateTest {
    final String VALIDBIRTHDATE = "01/03/2021";

    @DisplayName("Valid Birthdate construction")
    @Test
    @Tag("US010")
    void shouldNotThrowCreateValidBirthDate(){
        assertDoesNotThrow(()-> new BirthDate(VALIDBIRTHDATE));
    }


    @DisplayName("Invalid Birthdate construction")
    @Tag("US010")
    @ParameterizedTest
    @ValueSource(strings = {"   ","1222-32-12", "tonyZe"})
    @NullAndEmptySource
    void shouldThrowCreateInValidBirthDate(String value){
        assertThrows(InvalidDateException.class, ()-> new BirthDate(value));
    }

    @Test
    void equalsTestEqualBirthDates(){
        BirthDate birthDateOne = new BirthDate("01/03/2021");
        BirthDate birthDateTwo = new BirthDate("01/03/2021");

        assertEquals(birthDateOne,birthDateTwo);
        assertNotSame(birthDateOne,birthDateTwo);
    }

    @Test
    void equalsTestSameBirthDate(){
        BirthDate birthDateOne = new BirthDate("01/03/2021");
        BirthDate birthDateTwo = birthDateOne;

        assertEquals(birthDateOne,birthDateTwo);
    }

    @Test
    void equalsTestDifferentBirthDate(){
        BirthDate birthDateOne = new BirthDate("01/03/2021");
        BirthDate birthDateTwo = new BirthDate("01/03/2020");

        assertNotEquals(birthDateOne,birthDateTwo);
    }

    @Test
    void equalsTestDifferentObjects(){
        BirthDate birthDate = new BirthDate("01/03/2021");
        LocalDate notBirthDate = LocalDate.now();

        assertNotEquals(birthDate,notBirthDate);
    }

    @Test
    void equalsTestDifferentFromNull(){
        BirthDate birthDate = new BirthDate("01/03/2021");
        String nullString = null;

        assertNotEquals(birthDate,nullString);
    }

    @Test
    void hashCodeTestSameHashCode(){
        BirthDate birthDateOne = new BirthDate("01/03/2021");
        BirthDate birthDateTwo = new BirthDate("01/03/2021");

        assertEquals(birthDateOne.hashCode(),birthDateTwo.hashCode());
        assertNotSame(birthDateOne,birthDateTwo);
    }

    @Test
    void hashCodeTestDifferentHashCode(){
        BirthDate birthDateOne = new BirthDate("01/03/2021");
        BirthDate birthDateTwo = new BirthDate("01/03/2020");

        assertNotEquals(birthDateOne.hashCode(),birthDateTwo.hashCode());
    }

    @Test
    void toStringTest() {
        BirthDate birthDate = new BirthDate("01/03/2021");
        String expected = "1/3/2021";

        String result = birthDate.toString();

        assertEquals(expected,result);
    }

}