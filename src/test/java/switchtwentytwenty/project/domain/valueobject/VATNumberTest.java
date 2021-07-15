package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import switchtwentytwenty.project.exceptions.InvalidVATException;

import static org.junit.jupiter.api.Assertions.*;

class VATNumberTest {
    final int VALIDVAT = 999999999;
    VATNumber vatNumber;


    @DisplayName("Test the creation of a valid VATNumber")
    @Test
    @Tag("US010")
    void shouldNotThrowCreateValidVAT(){
        assertDoesNotThrow(()->vatNumber = new VATNumber(VALIDVAT));
    }

    @DisplayName("Test that a negative, zero and boundary values throw exceptions")
    @ParameterizedTest
    @Tag("US010")
    @ValueSource(ints = {-1, 0, 99999999,1000000000})
    void shouldThrowBoundaryValuesVATNumber(int value){
        assertThrows(InvalidVATException.class, ()-> vatNumber = new VATNumber(value) );
    }

    @Test
    void equalsTestEqualVATNumber() {
        VATNumber vatNumberOne = new VATNumber(999999999);
        VATNumber vatNumberTwo = new VATNumber(999999999);

        assertEquals(vatNumberOne, vatNumberTwo);
        assertNotSame(vatNumberOne, vatNumberTwo);
    }

    @Test
    void equalsTestSameVATNumber() {
        VATNumber vatNumberOne = new VATNumber(999999999);
        VATNumber vatNumberTwo = vatNumberOne;

        assertEquals(vatNumberOne, vatNumberTwo);
    }

    @Test
    void equalsTestDifferentVATNumber() {
        VATNumber vatNumberOne = new VATNumber(999999999);
        VATNumber vatNumberTwo = new VATNumber(199999999);

        assertNotEquals(vatNumberOne, vatNumberTwo);
    }

    @Test
    void equalsTestDifferentObject() {
        VATNumber vatNumber = new VATNumber(999999999);
        String notVATNumber = "notVATNumber";

        assertNotEquals(vatNumber, notVATNumber);
    }

    @Test
    void hashCodeTestSameHashCode() {
        VATNumber vatNumberOne = new VATNumber(999999999);
        VATNumber vatNumberTwo = new VATNumber(999999999);

        assertEquals(vatNumberOne.hashCode(), vatNumberTwo.hashCode());
        assertNotSame(vatNumberOne, vatNumberTwo);
    }

    @Test
    void hashCodeTestDifferentHashCode() {
        VATNumber vatNumberOne = new VATNumber(999999999);
        VATNumber vatNumberTwo = new VATNumber(199999999);

        assertNotEquals(vatNumberOne.hashCode(), vatNumberTwo.hashCode());
    }

    @Test
    void vatNumberToStringTest() {
        VATNumber vatNumber = new VATNumber(999999999);
        String expected = "999999999";

        String result = vatNumber.toString();

        assertEquals(expected,result);
    }


}