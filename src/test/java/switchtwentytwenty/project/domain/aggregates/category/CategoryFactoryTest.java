package switchtwentytwenty.project.domain.aggregates.category;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class CategoryFactoryTest {

    CategoryFactory categoryFactory = new CategoryFactory();

    @Test
    void createStandardCategory() {
        CategoryName categoryName = new CategoryName("Casa");
        ParentCategoryPath parentCategoryPath = new ParentCategoryPath("2");
        Category expected = new StandardCategory(new CategoryName("Casa"), new ParentCategoryPath("2"));
        Category result = categoryFactory.createCategory(categoryName, parentCategoryPath);

        assertNotSame(expected, result);
        assertEquals(expected, result);
    }

    @Test
    void createStandardCategoryFamilyIDEmpty() {
        CategoryID categoryID = new CategoryID(2L);
        CategoryName categoryName = new CategoryName("Casa");
        ParentCategoryPath parentCategoryPath = new ParentCategoryPath("2");
        Optional<FamilyID> familyID = Optional.empty();
        Category expected = new StandardCategory(new CategoryName("Casa"), new CategoryID(2L), new ParentCategoryPath("2"));
        Category result = categoryFactory.createCategory(categoryID, categoryName, parentCategoryPath, familyID);

        assertNotSame(expected, result);
        assertEquals(expected, result);
    }

    @Test
    void createStandardCategoryWithAlreadyExistingID() {
        CategoryName categoryName = new CategoryName("Casa");
        ParentCategoryPath parentCategoryPath = new ParentCategoryPath("2");
        CategoryID categoryID = new CategoryID("1");
        Category expected = new StandardCategory(new CategoryName("Casa"), new CategoryID("1"), new ParentCategoryPath("2"));
        Category result = categoryFactory.createCategory(categoryName, categoryID, parentCategoryPath);

        assertNotSame(expected, result);
        assertEquals(expected, result);
    }

    @Test
    void createCustomCategory() {
        CategoryID categoryID = new CategoryID(2L);
        CategoryName categoryName = new CategoryName("Casa");
        ParentCategoryPath parentCategoryPath = new ParentCategoryPath("2");
        Optional<FamilyID> familyID = Optional.of(new FamilyID("@family@id.com"));
        Category expected = new CustomCategory(new CategoryID(2L), new ParentCategoryPath("2"), new CategoryName("Casa"), new FamilyID("@family@id.com"));
        Category result = categoryFactory.createCategory(categoryID, categoryName, parentCategoryPath, familyID);

        assertNotSame(expected, result);
        assertEquals(expected, result);
    }

    @Test
    void createCategory() {

        CategoryName categoryName = new CategoryName("Casa");
        ParentCategoryPath parentCategoryPath = new ParentCategoryPath("2");
        FamilyID familyID = new FamilyID("@family@id.com");
        Category result = categoryFactory.createCategory(categoryName, parentCategoryPath, familyID);
        CustomCategory expected = new CustomCategory(parentCategoryPath, categoryName, familyID);

        assertEquals(expected, result);
    }
}