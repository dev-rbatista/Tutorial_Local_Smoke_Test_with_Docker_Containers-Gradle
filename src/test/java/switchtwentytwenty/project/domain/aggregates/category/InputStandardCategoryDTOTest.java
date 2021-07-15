package switchtwentytwenty.project.domain.aggregates.category;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InputStandardCategoryDTOTest {

    InputCategoryDTO inputCategoryDTO = new InputCategoryDTO(1L, "desc", 2L, "familyID");

    @Test
    void getCategoryID() {
        Long result = inputCategoryDTO.getCategoryID();
        Long expected = 1L;

        assertEquals(result, expected);

    }

    @Test
    void getDescription() {
        String result = inputCategoryDTO.getDescription();
        String expected = "desc";

        assertEquals(result, expected);
    }

    @Test
    void getParentID() {
        Long result = inputCategoryDTO.getParentID();
        Long expected = 2L;

        assertEquals(result, expected);
    }

    @Test
    void getFamilyID() {
        String result = inputCategoryDTO.getFamilyID();
        String expected = "familyID";

        assertEquals(result, expected);
    }

    @Test
    void setCategoryID() {
        inputCategoryDTO.setCategoryID(5L);
        Long result = inputCategoryDTO.getCategoryID();
        Long expected = 5L;

        assertEquals(result, expected);
    }

    @Test
    void setDescription() {
        inputCategoryDTO.setDescription("newdesc");
        String result = inputCategoryDTO.getDescription();
        String expected = "newdesc";

        assertEquals(result, expected);
    }

    @Test
    void setParentID() {
        inputCategoryDTO.setParentID(5L);
        Long result = inputCategoryDTO.getParentID();
        Long expected = 5L;

        assertEquals(result, expected);
    }

    @Test
    void setFamilyID() {
        inputCategoryDTO.setFamilyID("newfamilyID");
        String result = inputCategoryDTO.getFamilyID();
        String expected = "newfamilyID";

        assertEquals(result, expected);
    }

    @Test
    void noArgsConstructorTest() {
        InputCategoryDTO noArgsDTO = new InputCategoryDTO();
        noArgsDTO.setCategoryID(2L);
        noArgsDTO.setParentID(5L);
        noArgsDTO.setDescription("desc");
        noArgsDTO.setFamilyID("familyID");

        assertNotNull(noArgsDTO);
    }
}