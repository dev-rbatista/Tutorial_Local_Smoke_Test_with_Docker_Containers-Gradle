package switchtwentytwenty.project.dto.category;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateCategoryDTOTest {

    CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO("desc","2L");


    @Test
    void getCategoryDescription() {
        String expected = "desc";
        String result = createCategoryDTO.getCategoryDescription();
        assertEquals(expected, result);
    }


    @Test
    void getParentID() {
        String expected = "2L";
        String result = createCategoryDTO.getParentCategory();
        assertEquals(expected, result);
    }

    @Test
    void setCategoryDescription() {
        createCategoryDTO.setCategoryDescription("newdesc");
        String expected = "newdesc";
        String result = createCategoryDTO.getCategoryDescription();
        assertEquals(expected, result);
    }

    @Test
    void setParentID() {
        createCategoryDTO.setParentCategory("5L");
        String expected = "5L";
        String result = createCategoryDTO.getParentCategory();
        assertEquals(expected, result);
    }

    @Test
    void noArgsConstructorTest() {
        CreateCategoryDTO noArgsDTO = new CreateCategoryDTO();
        noArgsDTO.setParentCategory("1L");
        noArgsDTO.setCategoryDescription("desc");

        assertNotNull(noArgsDTO);
    }
}