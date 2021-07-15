package switchtwentytwenty.project.dto.family;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputChangeRelationDTOTest {

    @Test
    void noArgsConstructor() {
        InputChangeRelationDTO emptyInputChangeRelationDTO = new InputChangeRelationDTO();
        assertNotNull(emptyInputChangeRelationDTO);
    }

    @Test
    void testConstructor() {
        InputChangeRelationDTO expected = new InputChangeRelationDTO("3", "Des", "@fam@id.com");
        ChangeRelationDTO changeRelationDTO = new ChangeRelationDTO("Des");
        InputChangeRelationDTO result = new InputChangeRelationDTO(changeRelationDTO, "@fam@id.com","3");
        assertEquals(expected, result);
        assertNotSame(expected, result);
    }

    @Test
    void testEquals() {
        InputChangeRelationDTO inputChangeRelationDTO1 = new InputChangeRelationDTO("3", "Des", "@fam@id.com");
        InputChangeRelationDTO inputChangeRelationDTO2 = new InputChangeRelationDTO("3", "Des", "@fam@id.com");
        assertEquals(inputChangeRelationDTO1, inputChangeRelationDTO2);
        assertNotSame(inputChangeRelationDTO1, inputChangeRelationDTO2);
    }

    @Test
    void testEqualsSameObject() {
        InputChangeRelationDTO inputChangeRelationDTO1 = new InputChangeRelationDTO("3", "Des", "@fam@id.com");
        InputChangeRelationDTO inputChangeRelationDTO2 = inputChangeRelationDTO1;
        assertEquals(inputChangeRelationDTO1, inputChangeRelationDTO2);

    }

    @Test
    void testEqualsFalseNullObject() {
        InputChangeRelationDTO inputChangeRelationDTO1 = new InputChangeRelationDTO("3", "Des", "@fam@id.com");
        InputChangeRelationDTO inputChangeRelationDTO2 = null;
        assertNotEquals(inputChangeRelationDTO1, inputChangeRelationDTO2);

    }

    @Test
    void testEqualsFalseNotSameObject() {
        InputChangeRelationDTO inputChangeRelationDTO1 = new InputChangeRelationDTO("3", "Des", "@fam@id.com");
        String inputChangeRelationDTO2 = "a String";

        assertNotEquals(inputChangeRelationDTO1, inputChangeRelationDTO2);

    }

    @Test
    void testEqualsFalseDifferentRelationID() {
        InputChangeRelationDTO inputChangeRelationDTO1 = new InputChangeRelationDTO("3", "Des", "@fam@id.com");
        InputChangeRelationDTO inputChangeRelationDTO2 = new InputChangeRelationDTO("4", "Des", "@fam@id.com");

        assertNotEquals(inputChangeRelationDTO1, inputChangeRelationDTO2);

    }

    @Test
    void testEqualsFalseDifferentDesignation() {
        InputChangeRelationDTO inputChangeRelationDTO1 = new InputChangeRelationDTO("3", "Des", "@fam@id.com");
        InputChangeRelationDTO inputChangeRelationDTO2 = new InputChangeRelationDTO("3", "Designation", "@fam@id.com");

        assertNotEquals(inputChangeRelationDTO1, inputChangeRelationDTO2);

    }

    @Test
    void testEqualsFalseDifferentfamilyId() {
        InputChangeRelationDTO inputChangeRelationDTO1 = new InputChangeRelationDTO("3", "Des", "@fam@id.com");
        InputChangeRelationDTO inputChangeRelationDTO2 = new InputChangeRelationDTO("3", "Des", "@family@id.com");

        assertNotEquals(inputChangeRelationDTO1, inputChangeRelationDTO2);

    }

    @Test
    void testHashCode() {
        InputChangeRelationDTO inputChangeRelationDTO1 = new InputChangeRelationDTO("3", "Des", "@fam@id.com");
        InputChangeRelationDTO inputChangeRelationDTO2 = new InputChangeRelationDTO("3", "Des", "@fam@id.com");
        assertEquals(inputChangeRelationDTO1.hashCode(), inputChangeRelationDTO2.hashCode());
        assertNotEquals(0, inputChangeRelationDTO1.hashCode());
    }

    @Test
    void setRelationID() {
        String expected = "4";
        InputChangeRelationDTO inputChangeRelationDTO = new InputChangeRelationDTO();
        inputChangeRelationDTO.setRelationID(expected);
        String result = inputChangeRelationDTO.getRelationID();
        assertEquals(expected, result);
    }

    @Test
    void setNewDesignation() {
        String expected = "4";
        InputChangeRelationDTO inputChangeRelationDTO = new InputChangeRelationDTO();
        inputChangeRelationDTO.setNewDesignation(expected);
        String result = inputChangeRelationDTO.getNewDesignation();
        assertEquals(expected, result);
    }

    @Test
    void setFamilyID() {
        String expected = "4";
        InputChangeRelationDTO inputChangeRelationDTO = new InputChangeRelationDTO();
        inputChangeRelationDTO.setFamilyID(expected);
        String result = inputChangeRelationDTO.getFamilyID();
        assertEquals(expected, result);
    }

    @Test
    void getRelationID() {
        String expected = "4";
        InputChangeRelationDTO inputChangeRelationDTO = new InputChangeRelationDTO("4","Des","@family@id.com");
        String result = inputChangeRelationDTO.getRelationID();
        assertEquals(expected, result);
    }

    @Test
    void getNewDesignation() {
        String expected = "Des";
        InputChangeRelationDTO inputChangeRelationDTO = new InputChangeRelationDTO("4","Des","@family@id.com");
        String result = inputChangeRelationDTO.getNewDesignation();
        assertEquals(expected, result);
    }

    @Test
    void getFamilyID() {
        String expected = "@family@id.com";
        InputChangeRelationDTO inputChangeRelationDTO = new InputChangeRelationDTO("4","Des","@family@id.com");
        String result = inputChangeRelationDTO.getFamilyID();
        assertEquals(expected, result);
    }
}