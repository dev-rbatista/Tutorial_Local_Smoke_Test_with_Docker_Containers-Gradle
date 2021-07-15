package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.dto.category.CreateCategoryDTO;
import switchtwentytwenty.project.dto.category.InputCustomCategoryDTO;

import static org.junit.jupiter.api.Assertions.*;

class CategoryInputDTOAssemblerTest {

    @Test
    void inputCustomCategory() {
        String categoryDescription = "Casa";
        String parent = "1";
        String familyID = "@tonyze@email.com";

        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO(categoryDescription, parent);

        CategoryInputDTOAssembler categoryInputDTOAssembler = new CategoryInputDTOAssembler();

        String expectedCategoryDescription = "Casa";
        String expectedParent = "1";
        String expectedFamilyID = "@tonyze@email.com";

        InputCustomCategoryDTO expected = new InputCustomCategoryDTO(expectedCategoryDescription, expectedParent, expectedFamilyID);

        InputCustomCategoryDTO result = categoryInputDTOAssembler.toInputCustomCategoryDTO(createCategoryDTO, familyID);

        assertEquals(expected, result);
    }
}