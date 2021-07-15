package switchtwentytwenty.project.dto.category;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputCustomCategoryDTOTest {

    @Test
    void instatiationSuccess() {
        String categoryName = "Casa";
        String parentID = null;
        String familyID = "@tonyze@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTO = new InputCustomCategoryDTO(categoryName, parentID, familyID);

        assertNotNull(inputCustomCategoryDTO);
    }

    @Test
    void getCategoryName() {
        String categoryName = "Casa";
        String parentID = null;
        String familyID = "@tonyze@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTO = new InputCustomCategoryDTO(categoryName, parentID, familyID);

        String expected = "Casa";

        String result = inputCustomCategoryDTO.getCategoryName();

        assertEquals(expected, result);
    }

    @Test
    void getParentID() {
        String categoryName = "Casa";
        String parentID = "1";
        String familyID = "@tonyze@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTO = new InputCustomCategoryDTO(categoryName, parentID, familyID);

        String expected = "1";

        String result = inputCustomCategoryDTO.getParentID();

        assertEquals(expected, result);
    }

    @Test
    void getFamilyID() {
        String categoryName = "Casa";
        String parentID = "1";
        String familyID = "@tonyze@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTO = new InputCustomCategoryDTO(categoryName, parentID, familyID);

        String expected = "@tonyze@email.com";

        String result = inputCustomCategoryDTO.getFamilyID();

        assertEquals(expected, result);
    }

    @Test
    void equalsSameInstance() {
        String categoryName = "Casa";
        String parentID = "1";
        String familyID = "@tonyze@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTO = new InputCustomCategoryDTO(categoryName, parentID, familyID);

        assertEquals(inputCustomCategoryDTO, inputCustomCategoryDTO);
        assertSame(inputCustomCategoryDTO, inputCustomCategoryDTO);
    }

    @Test
    void equalsDifferentInstance() {
        String categoryName = "Casa";
        String parentID = "1";
        String familyID = "@tonyze@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTO = new InputCustomCategoryDTO(categoryName, parentID, familyID);
        InputCustomCategoryDTO inputCustomCategoryDTOTwo = new InputCustomCategoryDTO(categoryName, parentID, familyID);

        assertEquals(inputCustomCategoryDTOTwo, inputCustomCategoryDTO);
        assertNotSame(inputCustomCategoryDTO, inputCustomCategoryDTOTwo);
    }

    @Test
    void equalsWithNull() {
        String categoryName = "Casa";
        String parentID = "1";
        String familyID = "@tonyze@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTO = new InputCustomCategoryDTO(categoryName, parentID, familyID);
        InputCustomCategoryDTO inputCustomCategoryDTOTwo = null;

        assertNotEquals(inputCustomCategoryDTO, inputCustomCategoryDTOTwo);
    }

    @Test
    void equalsWithOtherClass() {
        String categoryName = "Casa";
        String parentID = "1";
        String familyID = "@tonyze@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTO = new InputCustomCategoryDTO(categoryName, parentID, familyID);
        String inputCustomCategoryDTOTwo = "inputCustomCategoryDTOTwo";

        assertNotEquals(inputCustomCategoryDTO, inputCustomCategoryDTOTwo);
    }

    @Test
    void equalsWithDifferentName() {
        String categoryName = "Casa";
        String parentID = "1";
        String familyID = "@tonyze@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTO = new InputCustomCategoryDTO(categoryName, parentID, familyID);

        String categoryNameTwo = "Cas";
        String parentIDTwo = "1";
        String familyIDTwo = "@tonyze@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTOTwo = new InputCustomCategoryDTO(categoryNameTwo, parentIDTwo, familyIDTwo);

        assertNotEquals(inputCustomCategoryDTO, inputCustomCategoryDTOTwo);
    }

    @Test
    void equalsWithDifferentParentCategory() {
        String categoryName = "Casa";
        String parentID = "1";
        String familyID = "@tonyze@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTO = new InputCustomCategoryDTO(categoryName, parentID, familyID);

        String categoryNameTwo = "Casa";
        String parentIDTwo = "2";
        String familyIDTwo = "@tonyze@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTOTwo = new InputCustomCategoryDTO(categoryNameTwo, parentIDTwo, familyIDTwo);

        assertNotEquals(inputCustomCategoryDTO, inputCustomCategoryDTOTwo);
    }

    @Test
    void equalsWithDifferentFamilyID() {
        String categoryName = "Casa";
        String parentID = "1";
        String familyID = "@tonyze@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTO = new InputCustomCategoryDTO(categoryName, parentID, familyID);

        String categoryNameTwo = "Casa";
        String parentIDTwo = "1";
        String familyIDTwo = "@katia@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTOTwo = new InputCustomCategoryDTO(categoryNameTwo, parentIDTwo, familyIDTwo);

        assertNotEquals(inputCustomCategoryDTO, inputCustomCategoryDTOTwo);
    }

    @Test
    void hashCodeSame() {
        String categoryName = "Casa";
        String parentID = "1";
        String familyID = "@tonyze@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTO = new InputCustomCategoryDTO(categoryName, parentID, familyID);
        InputCustomCategoryDTO inputCustomCategoryDTOTwo = new InputCustomCategoryDTO(categoryName, parentID, familyID);

        int hashOne = inputCustomCategoryDTO.hashCode();
        int hashTwo = inputCustomCategoryDTOTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }

    @Test
    void hashCodeNotSame() {
        String categoryName = "Casa";
        String parentID = "1";
        String familyID = "@tonyze@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTO = new InputCustomCategoryDTO(categoryName, parentID, familyID);

        String categoryNameTwo = "Casa";
        String parentIDTwo = "1";
        String familyIDTwo = "@katia@email.com";
        InputCustomCategoryDTO inputCustomCategoryDTOTwo = new InputCustomCategoryDTO(categoryNameTwo, parentIDTwo, familyIDTwo);

        int hashOne = inputCustomCategoryDTO.hashCode();
        int hashTwo = inputCustomCategoryDTOTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);

    }
}