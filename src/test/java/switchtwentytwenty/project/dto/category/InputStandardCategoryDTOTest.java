package switchtwentytwenty.project.dto.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputStandardCategoryDTOTest {

    @Test
    @DisplayName("Test to see if the getCategoryName method functions correctly")
    void getCategoryName() {
        InputStandardCategoryDTO inputStandardCategoryDTO = new InputStandardCategoryDTO("House", "1L");
        String expected = "House";
        String result = inputStandardCategoryDTO.getCategoryName();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test to see if the getParentID method functions correctly")
    void getParentID() {
        InputStandardCategoryDTO inputStandardCategoryDTO = new InputStandardCategoryDTO("House", "1L");
        String expected = "1L";
        String result = inputStandardCategoryDTO.getParentID();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should return true if two identical InputCategoryDTO objects are compared using the equals method")
    void testEquals() {
        InputStandardCategoryDTO inputStandardCategoryDTO1 = new InputStandardCategoryDTO("House", "1L");
        InputStandardCategoryDTO inputStandardCategoryDTO2 = new InputStandardCategoryDTO("House", "1L");
        InputStandardCategoryDTO nullDTO = null;
        String notDTO = "string";
        assertEquals(inputStandardCategoryDTO1, inputStandardCategoryDTO2);
        assertNotSame(inputStandardCategoryDTO1, inputStandardCategoryDTO2);
        assertTrue(inputStandardCategoryDTO1.equals(inputStandardCategoryDTO1));
        assertFalse(inputStandardCategoryDTO1.equals(nullDTO));
        assertFalse(inputStandardCategoryDTO1.equals(notDTO));

    }

    @Test
    @DisplayName("Should return false if two different InputCategoryDTO objects are compared using the equals method")
    void testEqualsFalseDifferentParentID() {
        InputStandardCategoryDTO inputStandardCategoryDTO1 = new InputStandardCategoryDTO("House", "1L");
        InputStandardCategoryDTO inputStandardCategoryDTO2 = new InputStandardCategoryDTO("House", "2L");

        assertNotEquals(inputStandardCategoryDTO1, inputStandardCategoryDTO2);
        assertNotSame(inputStandardCategoryDTO1, inputStandardCategoryDTO2);

    }
    @Test
    @DisplayName("Should return false if two different InputCategoryDTO objects are compared using the equals method")
    void testEqualsFalseDifferentCategoryName() {
        InputStandardCategoryDTO inputStandardCategoryDTO1 = new InputStandardCategoryDTO("House", "1L");
        InputStandardCategoryDTO inputStandardCategoryDTO2 = new InputStandardCategoryDTO("otherHouse", "1L");

        assertNotEquals(inputStandardCategoryDTO1, inputStandardCategoryDTO2);
        assertNotSame(inputStandardCategoryDTO1, inputStandardCategoryDTO2);

    }

    @Test
    @DisplayName("Should return true if two identical InputCategoryDTO objects are compared using their hash codes")
    void testHashCode() {
        InputStandardCategoryDTO inputStandardCategoryDTO1 = new InputStandardCategoryDTO("House", "1L");
        InputStandardCategoryDTO inputStandardCategoryDTO2 = new InputStandardCategoryDTO("House", "1L");

        assertEquals(inputStandardCategoryDTO1.hashCode(), inputStandardCategoryDTO2.hashCode());
        assertNotSame(inputStandardCategoryDTO1, inputStandardCategoryDTO2);

    }

    @Test
    @DisplayName("Should return false if two different InputCategoryDTO objects are compared using their hash codes")
    void testHashCodeFalse() {
        InputStandardCategoryDTO inputStandardCategoryDTO1 = new InputStandardCategoryDTO("House", "1L");
        InputStandardCategoryDTO inputStandardCategoryDTO2 = new InputStandardCategoryDTO("House", "2L");

        assertNotEquals(inputStandardCategoryDTO1.hashCode(), inputStandardCategoryDTO2.hashCode());
        assertNotSame(inputStandardCategoryDTO1, inputStandardCategoryDTO2);

    }


}