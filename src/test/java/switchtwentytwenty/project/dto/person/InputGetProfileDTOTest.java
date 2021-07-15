package switchtwentytwenty.project.dto.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputGetProfileDTOTest {

    @Test
    void unpackIDTest() {
        InputGetProfileDTO inputGetProfileDTO = new InputGetProfileDTO("admin@email.com");
        String expected = "admin@email.com";

        String result = inputGetProfileDTO.unpackID();

        assertEquals(expected,result);
    }

    @Test
    void setID() {
        InputGetProfileDTO inputGetProfileDTO = new InputGetProfileDTO();
        inputGetProfileDTO.setId("admin@email.com");
        String expected = "admin@email.com";

        String result = inputGetProfileDTO.unpackID();

        assertEquals(expected,result);
    }

    @Test
    void testEqualsForEqualDTOs() {
        InputGetProfileDTO inputGetProfileDTOOne = new InputGetProfileDTO("admin@gmail.com");
        InputGetProfileDTO inputGetProfileDTOTwo = new InputGetProfileDTO("admin@gmail.com");

        assertEquals(inputGetProfileDTOOne,inputGetProfileDTOTwo);
        assertNotSame(inputGetProfileDTOOne,inputGetProfileDTOTwo);
    }

    @Test
    void testEqualsForSameDTO() {
        InputGetProfileDTO inputGetProfileDTOOne = new InputGetProfileDTO("admin@gmail.com");
        InputGetProfileDTO inputGetProfileDTOTwo = inputGetProfileDTOOne;

        assertEquals(inputGetProfileDTOOne,inputGetProfileDTOTwo);
        assertSame(inputGetProfileDTOOne,inputGetProfileDTOTwo);
    }

    @Test
    void testEqualsForDifferentDTOs() {
        InputGetProfileDTO inputGetProfileDTOOne = new InputGetProfileDTO("admin@gmail.com");
        InputGetProfileDTO inputGetProfileDTOTwo = new InputGetProfileDTO("notadmin@gmail.com");

        assertNotEquals(inputGetProfileDTOOne,inputGetProfileDTOTwo);
    }

    @Test
    void testEqualsForDifferentObjects() {
        InputGetProfileDTO inputGetProfileDTO = new InputGetProfileDTO("admin@gmail.com");
        String notADTO = "notadmin@gmail.com";

        assertNotEquals(inputGetProfileDTO,notADTO);
    }

    @Test
    void testEqualsForDifferentFromNull() {
        InputGetProfileDTO inputGetProfileDTO = new InputGetProfileDTO("admin@gmail.com");
        String nullString = null;

        assertNotEquals(inputGetProfileDTO,nullString);
    }

    @Test
    void testHashCodeSameHashCode() {
        InputGetProfileDTO inputGetProfileDTOOne = new InputGetProfileDTO("admin@gmail.com");
        InputGetProfileDTO inputGetProfileDTOTwo = new InputGetProfileDTO("admin@gmail.com");

        assertEquals(inputGetProfileDTOOne.hashCode(),inputGetProfileDTOTwo.hashCode());
        assertNotSame(inputGetProfileDTOOne,inputGetProfileDTOTwo);
    }

    @Test
    void testHashCodeDifferentHashCodes() {
        InputGetProfileDTO inputGetProfileDTOOne = new InputGetProfileDTO("admin@gmail.com");
        InputGetProfileDTO inputGetProfileDTOTwo = new InputGetProfileDTO("notadmin@gmail.com");

        assertNotEquals(inputGetProfileDTOOne.hashCode(),inputGetProfileDTOTwo.hashCode());
    }

}