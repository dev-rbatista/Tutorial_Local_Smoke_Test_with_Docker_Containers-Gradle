package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.exceptions.InvalidZipCodeException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    final String VALIDSTREET = "Rua da Amargura";
    final String VALIDCITY = "Porto";
    final String VALIDZIPCODE = "4405-586";
    final String VALIDDOORNUMBER = "14";

    @Test
    void testToString() {
        Address address = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDDOORNUMBER);
        String expected = "Address{street=Rua da Amargura, city=Porto, zipCode=4405-586, number=14}";
        String result = address.toString();
        assertEquals(expected, result);

    }

    @Test
    @Tag("US010")
    void constructorValidData() {
        Address result = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDDOORNUMBER);

        assertNotNull(result);
    }

    @Test
    @Tag("US010")
    void constructorInvalidData() {
        String invalidZipCode = "";

        assertThrows(InvalidZipCodeException.class, () -> new Address(VALIDSTREET, VALIDCITY, invalidZipCode, VALIDDOORNUMBER));
    }

    @Test
    @Tag("US010")
    void equalsTestSameAddresses() {
        Address addressOne = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDDOORNUMBER);
        Address addressTwo = addressOne;

        assertEquals(addressOne, addressTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentTypesOfObject() {
        Address addressOne = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDDOORNUMBER);
        LocalDate notACity = LocalDate.of(2001, 1, 1);

        assertNotEquals(addressOne, notACity);
    }

    @Test
    @Tag("US010")
    void equalsTestEqualAddresses() {
        Address addressOne = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDDOORNUMBER);
        Address addressTwo = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDDOORNUMBER);

        assertEquals(addressOne, addressTwo);
        assertNotSame(addressOne, addressTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentStreets() {
        Address addressOne = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDDOORNUMBER);
        Address addressTwo = new Address("Rua de Baixo", VALIDCITY, VALIDZIPCODE, VALIDDOORNUMBER);

        assertNotEquals(addressOne, addressTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentCities() {
        Address addressOne = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDDOORNUMBER);
        Address addressTwo = new Address(VALIDSTREET, "Matosinhos", VALIDZIPCODE, VALIDDOORNUMBER);

        assertNotEquals(addressOne, addressTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentZipCodes() {
        Address addressOne = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDDOORNUMBER);
        Address addressTwo = new Address(VALIDSTREET, VALIDCITY, "4000-001", VALIDDOORNUMBER);

        assertNotEquals(addressOne, addressTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentDoorNumbers() {
        Address addressOne = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDDOORNUMBER);
        Address addressTwo = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, "58A");

        assertNotEquals(addressOne, addressTwo);
    }

    @Test
    @Tag("US010")
    void hashCodeTest_sameHashCode() {
        Address addressOne = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDDOORNUMBER);
        Address addressTwo = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDDOORNUMBER);

        assertEquals(addressOne.hashCode(), addressTwo.hashCode());
        assertNotSame(addressOne, addressTwo);
    }

    @Test
    @Tag("US010")
    void hashCodeTest_differentHashCode() {
        Address addressOne = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDDOORNUMBER);
        Address addressTwo = new Address(VALIDSTREET, "Matosinhos", VALIDZIPCODE, VALIDDOORNUMBER);

        assertNotEquals(addressOne.hashCode(), addressTwo.hashCode());
    }

    @Test
    void getIdSuccess() {
        Address address = new Address(1L, new Street("rua"), new City("cidade"), new ZipCode("1234-123"), new DoorNumber("12"));
        Long expected = 1L;
        Long result = address.getId();

        assertNotNull(result);
        assertEquals(expected, result);
        assertNotEquals(0L, result);

    }
}