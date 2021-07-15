package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationDateTest {

    @Test
    @Tag("US010")
    void registrationDateTest_Valid() {
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);

        assertNotNull(registrationDate);
    }

    @Test
    @Tag("US010")
    void registrationDateTest_ValidNull() {
        String date = null;
        RegistrationDate registrationDate = new RegistrationDate(date);

        assertNotNull(registrationDate);
    }

    @Test
    @Tag("US010")
    void registrationDateTest_ValidEmpty() {
        String date = "  ";
        RegistrationDate registrationDate = new RegistrationDate(date);

        assertNotNull(registrationDate);
    }

    @Test
    @Tag("US010")
    void registrationDateTest_ValidBlank() {
        String date = "";
        RegistrationDate registrationDate = new RegistrationDate(date);

        assertNotNull(registrationDate);
    }

    @Test
    @Tag("US010")
    void equalsTest_equalNotSame() {
        String date = "12/12/1990";
        RegistrationDate registrationDateOne = new RegistrationDate(date);
        RegistrationDate registrationDateTwo = new RegistrationDate(date);

        assertEquals(registrationDateOne, registrationDateTwo);
    }

    @Test
    @Tag("US010")
    void equalsTest_equalSame() {
        String date = "12/12/1990";
        RegistrationDate registrationDateOne = new RegistrationDate(date);
        RegistrationDate registrationDateTwo = registrationDateOne;

        assertEquals(registrationDateOne, registrationDateTwo);
    }

    @Test
    @Tag("US010")
    void equalsTest_notEqual() {
        String dateOne = "12/12/1990";
        RegistrationDate registrationDateOne = new RegistrationDate(dateOne);
        String dateTwo = "12/12/1991";
        RegistrationDate registrationDateTwo = new RegistrationDate(dateTwo);

        assertNotEquals(registrationDateOne, registrationDateTwo);
    }

    @Test
    @Tag("US010")
    void equalsTest_notEqualDifferentObject() {
        String dateOne = "12/12/1990";
        RegistrationDate registrationDateOne = new RegistrationDate(dateOne);
        String notRegistrationDate = "not a RegistrationDate";

        assertNotEquals(registrationDateOne, notRegistrationDate);
    }

    @Test
    @Tag("US010")
    void hashCodeTest_sameHashCode() {
        String date = "12/12/1990";
        RegistrationDate registrationDateOne = new RegistrationDate(date);
        RegistrationDate registrationDateTwo = new RegistrationDate(date);

        assertEquals(registrationDateOne.hashCode(), registrationDateTwo.hashCode());
    }

    @Test
    @Tag("US010")
    void hashCodeTest_differentHashCode() {
        String dateOne = "12/12/1990";
        RegistrationDate registrationDateOne = new RegistrationDate(dateOne);
        String dateTwo = null;
        RegistrationDate registrationDateTwo = new RegistrationDate(dateTwo);

        assertNotEquals(registrationDateOne.hashCode(), registrationDateTwo.hashCode());
    }

    @Test
    void toStringTest() {
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String expected = "12/12/1990";

        String result = registrationDate.toString();

        assertEquals(expected,result);
    }
}