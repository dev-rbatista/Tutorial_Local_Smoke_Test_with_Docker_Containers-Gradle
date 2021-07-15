package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.category.ExternalStandardCategoryGroupTwoDTO;

import static org.junit.jupiter.api.Assertions.*;

class ExternalCategoryDTODomainAssemblerGroupTwoTest {

    ExternalCategoryDTODomainAssemblerGroupTwo assembler = new ExternalCategoryDTODomainAssemblerGroupTwo();

    @Test
    void createCategoryID() {
        ExternalStandardCategoryGroupTwoDTO dto = new ExternalStandardCategoryGroupTwoDTO();
        dto.setId("12");
        CategoryID expected = new CategoryID("12");

        CategoryID result = assembler.createCategoryID(dto);

        assertEquals(expected,result);
    }

    @Test
    void createCategoryName() {
        ExternalStandardCategoryGroupTwoDTO dto = new ExternalStandardCategoryGroupTwoDTO();
        dto.setDesignation("CASA");
        CategoryName expected = new CategoryName("CASA");

        CategoryName result = assembler.createCategoryName(dto);

        assertEquals(expected,result);
    }

    @Test
    void createParentID() {
        ExternalStandardCategoryGroupTwoDTO dto = new ExternalStandardCategoryGroupTwoDTO();
        dto.setParentID("12");
        ParentCategoryPath expected = new ParentCategoryPath("12");

        ParentCategoryPath result = assembler.createParentID(dto);

        assertEquals(expected,result);
    }
}