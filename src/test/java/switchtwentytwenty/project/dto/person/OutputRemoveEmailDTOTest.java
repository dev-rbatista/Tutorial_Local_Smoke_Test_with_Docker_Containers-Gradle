package switchtwentytwenty.project.dto.person;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OutputRemoveEmailDTOTest {

    OutputRemoveEmailDTO expected = new OutputRemoveEmailDTO();
    List<String> expectedList = new ArrayList<>();
    String email = "tonyze@tvtel.pt";

    @Test
    void testEqualsSameObjectExpectingEquals() {
    OutputRemoveEmailDTO result = expected;

    assertEquals(expected, result);
    }

    @Test
    void testEqualsDifferentObjectExpectingNotEquals() {
    String result = "result";

    assertNotEquals(expected, result);
    }

    @Test
    void testEqualsCompareWithNullExpectingNotEquals() {
    OutputRemoveEmailDTO result = null;

    assertNotEquals(expected, result);
    }

    @Test
    void testEqualsExpectingEquals() {
        expected.setEmailAddresses(expectedList);

        OutputRemoveEmailDTO result = new OutputRemoveEmailDTO();
        result.setEmailAddresses(expectedList);

        assertEquals(expected, result);
    }

    @Test
    void testObjectsWithDifferentContentExpectingNotEquals() {
        expected.setEmailAddresses(expectedList);

        OutputRemoveEmailDTO result = new OutputRemoveEmailDTO();

        assertNotEquals(expected, result);
    }



    @Test
    void testHashCodeExpectingEquals() {
        OutputRemoveEmailDTO result = new OutputRemoveEmailDTO();

        assertEquals(expected.hashCode(), result.hashCode());
    }

    @Test
    void testHashCodeExpectingNotEquals() {
        OutputRemoveEmailDTO result = new OutputRemoveEmailDTO();
        result.setEmailAddresses(expectedList);

        assertNotEquals(expected.hashCode(), result.hashCode());
    }

    @Test
    void getEmailAddressesSuccessCase() {
        expectedList.add(email);
        expected.setEmailAddresses(expectedList);

        List<String> result = expected.getEmailAddresses();

        assertEquals(expectedList, result);

    }
}