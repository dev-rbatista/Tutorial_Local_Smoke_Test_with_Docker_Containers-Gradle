package switchtwentytwenty.project.dto.family;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputFamilyDTOTest {

    @Test
    @DisplayName("Should return true if two identical InputFamilyDTO objects are compared using the equals method")
    void testEquals() {
        InputFamilyDTO inputFamilyDTO1 = new InputFamilyDTO("Silva", "12/12/1990");
        InputFamilyDTO inputFamilyDTO2 = new InputFamilyDTO("Silva", "12/12/1990");

        assertEquals(inputFamilyDTO1, inputFamilyDTO2);
        assertNotSame(inputFamilyDTO1, inputFamilyDTO2);
    }

    @Test
    @DisplayName("Should return false if two different InputFamilyDTO objects are compared using the equals method")
    void testEqualsFail() {
        InputFamilyDTO inputFamilyDTO1 = new InputFamilyDTO("Silvas", "12/12/1990");
        InputFamilyDTO inputFamilyDTO2 = new InputFamilyDTO("Silva", "12/12/1990");

        assertNotEquals(inputFamilyDTO1, inputFamilyDTO2);
        assertNotSame(inputFamilyDTO1, inputFamilyDTO2);
    }

    @Test
    void testEqualsDifferentObjects() {
        InputFamilyDTO inputFamilyDTO = new InputFamilyDTO("Silva", "12/12/1990");
        String notDTO = "Not a DTO";

        assertNotEquals(inputFamilyDTO, notDTO);
    }

    @Test
    void testEqualsDifferentFromNull() {
        InputFamilyDTO inputFamilyDTO = new InputFamilyDTO("Silva", "12/12/1990");
        String nullString = null;

        assertNotEquals(inputFamilyDTO, nullString);
    }


    @Test
    void testEqualsSameDTO() {
        InputFamilyDTO inputFamilyDTOOne = new InputFamilyDTO("Silva", "12/12/1990");
        InputFamilyDTO inputFamilyDTOTwo = inputFamilyDTOOne;

        assertEquals(inputFamilyDTOOne, inputFamilyDTOTwo);
    }

    @Test
    @DisplayName("Should return true if two identical InputFamilyDTO objects are compared using their hash codes")
    void testHashCode() {
        InputFamilyDTO inputFamilyDTO1 = new InputFamilyDTO("Silva", "12/12/1990");
        InputFamilyDTO inputFamilyDTO2 = new InputFamilyDTO("Silva", "12/12/1990");

        assertEquals(inputFamilyDTO1.hashCode(), inputFamilyDTO2.hashCode());
        assertNotSame(inputFamilyDTO1, inputFamilyDTO2);
    }

    @Test
    @DisplayName("Should return false if two different InputFamilyDTO objects are compared using their hash codes")
    void testHashCodeFail() {
        InputFamilyDTO inputFamilyDTO1 = new InputFamilyDTO("Silva", "12/12/1990");
        InputFamilyDTO inputFamilyDTO2 = new InputFamilyDTO("Silvas", "12/12/1990");

        assertNotEquals(inputFamilyDTO1.hashCode(), inputFamilyDTO2.hashCode());
        assertNotSame(inputFamilyDTO1, inputFamilyDTO2);
    }
}