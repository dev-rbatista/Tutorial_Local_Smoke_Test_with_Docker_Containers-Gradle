package switchtwentytwenty.project.dto.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class InputRemoveEmailDTOTest {

    String email = "zemanel@gmail.com";
    String personID = "zemanel@hotmail.com";
    String emailTwo = "votorino@gmail.com";
    String personIDTwo = "votorino@hotmail.com";

    @DisplayName("Get email")
    @Test
    void getEmail() {
        InputRemoveEmailDTO dto = new InputRemoveEmailDTO(email, personID);
        assertEquals(dto.getEmail(), email);
    }

    @DisplayName("Get personID")
    @Test
    void getPersonID() {
        InputRemoveEmailDTO dto = new InputRemoveEmailDTO(email, personID);
        assertEquals(dto.getPersonID(), personID);
    }

    @DisplayName("Set email")
    @Test
    void setEmail() {
        InputRemoveEmailDTO dtoOne = new InputRemoveEmailDTO();
        dtoOne.setEmail(emailTwo);
        InputRemoveEmailDTO dtoTwo = new InputRemoveEmailDTO();
        dtoTwo.setEmail(emailTwo);

        assertEquals(dtoOne, dtoTwo);
        assertNotSame(dtoOne, dtoTwo);
    }

    @DisplayName("Set personID")
    @Test
    void setPersonID() {
        InputRemoveEmailDTO dtoOne = new InputRemoveEmailDTO();
        dtoOne.setPersonID(personIDTwo);
        InputRemoveEmailDTO dtoTwo = new InputRemoveEmailDTO();
        dtoTwo.setPersonID(personIDTwo);

        assertEquals(dtoOne, dtoTwo);
        assertNotSame(dtoOne, dtoTwo);
    }

    @DisplayName("Different Objects, same arguments")
    @Test
    void testEqualsSameArguments() {
        InputRemoveEmailDTO dtoOne = new InputRemoveEmailDTO();
        dtoOne.setPersonID(personIDTwo);
        dtoOne.setEmail(emailTwo);
        InputRemoveEmailDTO dtoTwo = new InputRemoveEmailDTO();
        dtoTwo.setPersonID(personIDTwo);
        dtoTwo.setEmail(emailTwo);

        assertEquals(dtoOne, dtoTwo);
        assertNotSame(dtoOne, dtoTwo);
    }

    @DisplayName("Same Object")
    @Test
    void testEqualsSameObject() {
        InputRemoveEmailDTO dtoOne = new InputRemoveEmailDTO();
        dtoOne.setPersonID(personIDTwo);
        dtoOne.setEmail(emailTwo);
        InputRemoveEmailDTO dtoTwo = dtoOne;

        assertEquals(dtoOne, dtoTwo);
        assertSame(dtoOne, dtoTwo);
    }

    @DisplayName("Different Objects, one of them is null")
    @Test
    void testEqualsObjectNull() {
        InputRemoveEmailDTO dtoOne = new InputRemoveEmailDTO();
        dtoOne.setPersonID(personIDTwo);
        dtoOne.setEmail(emailTwo);
        InputRemoveEmailDTO dtoTwo = null;

        assertNotEquals(dtoOne, dtoTwo);
        assertNotSame(dtoOne, dtoTwo);
    }

    @DisplayName("Different Objects class")
    @Test
    void testEqualsDifferentObjectClass() {
        InputRemoveEmailDTO dtoOne = new InputRemoveEmailDTO();
        dtoOne.setPersonID(personIDTwo);
        dtoOne.setEmail(emailTwo);
        String dtoTwo = "zemanel@gmail.com";

        assertNotEquals(dtoOne, dtoTwo);
        assertNotSame(dtoOne, dtoTwo);
    }

    @DisplayName("Different Objects, different personID")
    @Test
    void testNotEqualsDifferentPersonID() {
        InputRemoveEmailDTO dtoOne = new InputRemoveEmailDTO();
        dtoOne.setPersonID(personID);
        dtoOne.setEmail(email);
        InputRemoveEmailDTO dtoTwo = new InputRemoveEmailDTO();
        dtoTwo.setPersonID(personIDTwo);
        dtoTwo.setEmail(email);

        assertNotEquals(dtoOne, dtoTwo);
        assertNotSame(dtoOne, dtoTwo);
    }

    @DisplayName("Different Objects, different email")
    @Test
    void testNotEqualsDifferentEmail() {
        InputRemoveEmailDTO dtoOne = new InputRemoveEmailDTO();
        dtoOne.setPersonID(personID);
        dtoOne.setEmail(email);
        InputRemoveEmailDTO dtoTwo = new InputRemoveEmailDTO();
        dtoTwo.setPersonID(personID);
        dtoTwo.setEmail(emailTwo);

        assertNotEquals(dtoOne, dtoTwo);
        assertNotSame(dtoOne, dtoTwo);
    }

    @DisplayName("Different Objects Same hashcode")
    @Test
    void testHashCodeSuccess() {
        InputRemoveEmailDTO dtoOne = new InputRemoveEmailDTO();
        dtoOne.setPersonID(personID);
        dtoOne.setEmail(email);
        InputRemoveEmailDTO dtoTwo = new InputRemoveEmailDTO();
        dtoTwo.setPersonID(personID);
        dtoTwo.setEmail(email);

        assertNotSame(dtoOne, dtoTwo);
        assertEquals(dtoOne.hashCode(), dtoTwo.hashCode());
    }

    @DisplayName("Different Objects Different hashcode")
    @Test
    void testHashCodeFail() {
        InputRemoveEmailDTO dtoOne = new InputRemoveEmailDTO();
        dtoOne.setPersonID(personID);
        dtoOne.setEmail(email);
        InputRemoveEmailDTO dtoTwo = new InputRemoveEmailDTO();
        dtoTwo.setPersonID(personID);
        dtoTwo.setEmail(emailTwo);

        assertNotSame(dtoOne, dtoTwo);
        assertNotEquals(dtoOne.hashCode(), dtoTwo.hashCode());
    }
}