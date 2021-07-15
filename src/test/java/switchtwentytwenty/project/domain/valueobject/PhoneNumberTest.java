package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import switchtwentytwenty.project.exceptions.InvalidPhoneNumberException;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {

    final Integer VALIDPHONE = 999999999;
    PhoneNumber phone;


    @DisplayName("Test the creation of a valid PhoneNumber")
    @Test
    @Tag("US010")
    void shouldNotThrowCreateValidPhoneNumber(){
        assertDoesNotThrow(()->phone = new PhoneNumber(VALIDPHONE));
    }

    @DisplayName("Test the creation of a null PhoneNumber")
    @Test
    @Tag("US010")
    void shouldNotThrowCreateNullPhoneNumber(){
        assertDoesNotThrow(()->phone = new PhoneNumber(null));
    }


    @DisplayName("Test that a negative, zero and boundary values throw exceptions")
    @ParameterizedTest
    @Tag("US010")
    @ValueSource(ints = {-1, 0, 99999999,1000000000})
    void shouldThrowBoundaryValuesPhoneNumber(int value){
        assertThrows(InvalidPhoneNumberException.class, ()-> phone = new PhoneNumber(value) );
    }

    @Test
    void equalsTestEqualPhoneNumber() {
        PhoneNumber phoneNumberOne = new PhoneNumber(931234567);
        PhoneNumber phoneNumberTwo = new PhoneNumber(931234567);

        assertEquals(phoneNumberOne, phoneNumberTwo);
        assertNotSame(phoneNumberOne, phoneNumberTwo);
    }

    @Test
    void equalsTestSamePhoneNumber() {
        PhoneNumber phoneNumberOne = new PhoneNumber(931234567);
        PhoneNumber phoneNumberTwo = phoneNumberOne;

        assertEquals(phoneNumberOne, phoneNumberTwo);
    }

    @Test
    void equalsTestDifferentPhoneNumber() {
        PhoneNumber phoneNumberOne = new PhoneNumber(931234567);
        PhoneNumber phoneNumberTwo = new PhoneNumber(961234567);

        assertNotEquals(phoneNumberOne, phoneNumberTwo);
    }

    @Test
    void equalsTestDifferentObject() {
        PhoneNumber phoneNumberOne = new PhoneNumber(931234567);
        String notPhoneNumber = "notPhoneNumber";

        assertNotEquals(phoneNumberOne, notPhoneNumber);
    }

    @Test
    void equalsTestDifferentFromNull() {
        PhoneNumber phoneNumber = new PhoneNumber(931234567);
        String nullString = null;

        assertNotEquals(phoneNumber, nullString);
    }

    @Test
    void hashCodeTestSameHashCode() {
        PhoneNumber phoneNumberOne = new PhoneNumber(931234567);
        PhoneNumber phoneNumberTwo = new PhoneNumber(931234567);

        assertEquals(phoneNumberOne.hashCode(), phoneNumberTwo.hashCode());
        assertNotSame(phoneNumberOne, phoneNumberTwo);
    }

    @Test
    void hashCodeTestDifferentHashCode() {
        PhoneNumber phoneNumberOne = new PhoneNumber(931234567);
        PhoneNumber phoneNumberTwo = new PhoneNumber(961234567);

        assertNotEquals(phoneNumberOne.hashCode(), phoneNumberTwo.hashCode());
    }

    @Test
    void getPhoneNumber(){
        PhoneNumber phoneNumber = new PhoneNumber(931234567);
        Integer expected = 931234567;

        Integer result = phoneNumber.getNumber();

        assertEquals(expected,result);
    }

    @Test
    void getIDTest(){
        PhoneNumber phoneNumber = new PhoneNumber(11L,931234567);
        long expected = 11L;

        long result = phoneNumber.getId();

        assertEquals(expected,result);
    }

    @Test
    void toStringTest() {
        PhoneNumber phoneNumber = new PhoneNumber(931234567);
        String expected = "931234567";

        String result = phoneNumber.toString();

        assertEquals(expected,result);
    }

}