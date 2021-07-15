package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.aggregates.category.CustomCategory;
import switchtwentytwenty.project.domain.aggregates.category.StandardCategory;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDTODomainAssemblerTest {

    CategoryID categoryID = new CategoryID(3L);
    CategoryName categoryName = new CategoryName("category");
    ParentCategoryPath parentCategoryPath = new ParentCategoryPath("parent");
    FamilyID familyID = new FamilyID("@tonyze@hotmail.com");
    Optional<FamilyID> familyIDOptional = Optional.of(familyID);
    Category category = new CustomCategory(categoryID, parentCategoryPath,  categoryName, familyID);
    CategoryDTODomainAssembler categoryDTODomainAssembler = new CategoryDTODomainAssembler();

    CategoryName name = new CategoryName("Casa");
    CategoryID id = new CategoryID(20030L);
    ParentCategoryPath parentID = new ParentCategoryPath("/external/20040");
   // FamilyID familyID = new FamilyID("@admin@gmail.com");


    @Test
    void toDTOSuccessCase() {
        OutputCategoryDTO expected = new OutputCategoryDTO(categoryName.toString(), categoryID.toString(), parentCategoryPath.toString());
        expected.setFamilyID(familyID.getId());

        OutputCategoryDTO result = categoryDTODomainAssembler.toDTO(category);
        result.setFamilyID(familyID.getId());

        assertEquals(expected, result);
    }



    @Test
    void testToDTOStandardCategory() {
        Category category = new StandardCategory(name,id,parentID);
        CategoryDTODomainAssembler assembler = new CategoryDTODomainAssembler();
        OutputCategoryDTO expected = new OutputCategoryDTO("CASA","20030","/external/20040");

        OutputCategoryDTO result = assembler.toDTO(category);

        assertEquals(expected.toString(),result.toString());
        assertEquals(expected.getCategoryID(),result.getCategoryID());
        assertEquals(expected.getParentID(),result.getParentID());
        assertEquals(expected.getCategoryName(),result.getCategoryName());
    }

    @Test
    void testToDTOCustomCategory() {
        Category category = new CustomCategory(id,parentID,name,familyID);
        CategoryDTODomainAssembler assembler = new CategoryDTODomainAssembler();
        OutputCategoryDTO expected = new OutputCategoryDTO("CASA","20030","/external/20040","@tonyze@hotmail.com");

        OutputCategoryDTO result = assembler.toDTO(category);

        assertEquals(expected.toString(),result.toString());
        assertEquals(expected.getCategoryID(),result.getCategoryID());
        assertEquals(expected.getParentID(),result.getParentID());
        assertEquals(expected.getFamilyID(),result.getFamilyID());
        assertEquals(expected.getCategoryName(),result.getCategoryName());
    }

    @Test
    void testCreateCategoryName() {
        CategoryDTODomainAssembler assembler = new CategoryDTODomainAssembler();
        CategoryName expected = new CategoryName("Casa");

        CategoryName result = assembler.createCategoryName("Casa");

        assertEquals(expected,result);
    }

    @Test
    void testCreateParentCategoryPath() {
        CategoryDTODomainAssembler assembler = new CategoryDTODomainAssembler();
        ParentCategoryPath expected = new ParentCategoryPath("/external/20020");

        ParentCategoryPath result = assembler.createParentCategoryPath("/external/20020");

        assertEquals(expected,result);

    }
}