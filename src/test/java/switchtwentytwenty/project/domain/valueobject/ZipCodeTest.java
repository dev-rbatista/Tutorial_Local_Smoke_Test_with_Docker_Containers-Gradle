package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import switchtwentytwenty.project.exceptions.InvalidZipCodeException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ZipCodeTest {
    final String VALIDZIPCODE = "4430-222";

    @Test
    @Tag("US010")
    void constructorTestValidData(){
        ZipCode result = new ZipCode(VALIDZIPCODE);
        assertNotNull(result);
    }

    @ParameterizedTest
    @Tag("US010")
    @ValueSource(strings = {"  "})
    @NullAndEmptySource
    @DisplayName("Test if a ZipCode throws an exception if the String is empty, blank or null")
    void constructorTestInValidDataNullBlankEmpty(String value){
        assertThrows(InvalidZipCodeException.class, () -> new ZipCode(value));
    }

    @ParameterizedTest
    @Tag("US010")
    @ValueSource(strings = {"1111111","111-111","1111-11","A111-111","1111-A11","*111-111","1111-$11","11111-11","111-1111","1111--111"})
    @DisplayName("Test if a ZipCode throws an exception if the String represents an invalid ZipCode")
    void constructorTestInValidData(String value){
        assertThrows(InvalidZipCodeException.class, () -> new ZipCode(value));
    }

    @Test
    @Tag("US010")
    void equalsTestSameZipCode(){
        ZipCode zipCodeOne = new ZipCode(VALIDZIPCODE);
        ZipCode zipCodeTwo = zipCodeOne;

        assertEquals(zipCodeOne,zipCodeTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentTypesOfObject(){
        ZipCode zipCode = new ZipCode(VALIDZIPCODE);
        LocalDate notAZipCode = LocalDate.of(2001,1,1);

        assertNotEquals(zipCode,notAZipCode);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentFromNull(){
        ZipCode zipCode = new ZipCode(VALIDZIPCODE);
        String nullString = null;

        assertNotEquals(zipCode, nullString);
    }

    @Test
    @Tag("US010")
    void equalsTestEqualZipCodes(){
        ZipCode zipCodeOne = new ZipCode(VALIDZIPCODE);
        ZipCode zipCodeTwo = new ZipCode(VALIDZIPCODE);

        assertEquals(zipCodeOne,zipCodeTwo);
        assertNotSame(zipCodeOne,zipCodeTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentZipCodes(){
        ZipCode zipCodeOne = new ZipCode(VALIDZIPCODE);
        ZipCode zipCodeTwo = new ZipCode("4000-100");

        assertNotEquals(zipCodeOne,zipCodeTwo);
    }

    @Test
    @Tag("US010")
    void hashCodeTest_sameHashCode() {
        ZipCode zipCodeOne = new ZipCode(VALIDZIPCODE);
        ZipCode zipCodeTwo = new ZipCode(VALIDZIPCODE);

        assertEquals(zipCodeOne.hashCode(), zipCodeTwo.hashCode());
        assertNotSame(zipCodeOne,zipCodeTwo);
    }

    @Test
    @Tag("US010")
    void hashCodeTest_differentHashCode() {
        ZipCode zipCodeOne = new ZipCode(VALIDZIPCODE);
        ZipCode zipCodeTwo = new ZipCode("4000-100");

        assertNotEquals(zipCodeOne.hashCode(), zipCodeTwo.hashCode());
    }

}