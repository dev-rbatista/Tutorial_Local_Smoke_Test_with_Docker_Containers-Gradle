package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.category.ExternalStandardCategoryGroupFourDTO;

import static org.junit.jupiter.api.Assertions.*;

class ExternalCategoryDTODomainAssemblerGroupFourTest {

    ExternalCategoryDTODomainAssemblerGroupFour assembler = new ExternalCategoryDTODomainAssemblerGroupFour();

    @Test
    void createCategoryID() {
        ExternalStandardCategoryGroupFourDTO dto = new ExternalStandardCategoryGroupFourDTO();
        dto.setId("12");
        CategoryID expected = new CategoryID("12");

        CategoryID result = assembler.createCategoryID(dto);

        assertEquals(expected,result);
    }

    @Test
    void createCategoryName() {
        ExternalStandardCategoryGroupFourDTO dto = new ExternalStandardCategoryGroupFourDTO();
        dto.setName("CASA");
        CategoryName expected = new CategoryName("CASA");

        CategoryName result = assembler.createCategoryName(dto);

        assertEquals(expected,result);
    }

    @Test
    void createParentID() {
        ExternalStandardCategoryGroupFourDTO dto = new ExternalStandardCategoryGroupFourDTO();
        dto.setParentID("12");
        ParentCategoryPath expected = new ParentCategoryPath("12");

        ParentCategoryPath result = assembler.createParentID(dto);

        assertEquals(expected,result);
    }
}