package switchtwentytwenty.project.domain.aggregates.category;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StandardCategoryTest {

    @Test
    void id() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        StandardCategory standardCategory = new StandardCategory(categoryName, categoryID, parentID);

        CategoryID expected = new CategoryID(1L);
        CategoryID result = standardCategory.id();

        assertNotSame(expected, result);
        assertEquals(expected, result);
    }

    @Test
    void hasID() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        StandardCategory standardCategory = new StandardCategory(categoryName, categoryID, parentID);

        CategoryID expected = new CategoryID(1L);
        CategoryID notExpected = new CategoryID(5L);

        assertTrue(standardCategory.hasID(expected));
        assertFalse(standardCategory.hasID(notExpected));

    }

    @Test
    void testEqualsSuccess() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        StandardCategory standardCategory = new StandardCategory(categoryName, categoryID, parentID);
        StandardCategory standardCategory2 = new StandardCategory(categoryName, categoryID, parentID);

        assertNotSame(standardCategory, standardCategory2);
        assertEquals(standardCategory, standardCategory2);
    }

    @Test
    void testEqualsSucessSelfCompare() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        StandardCategory standardCategory = new StandardCategory(categoryName, categoryID, parentID);
        StandardCategory standardCategory2 = new StandardCategory(categoryName, categoryID, parentID);

        assertNotSame(standardCategory, standardCategory2);
        assertEquals(standardCategory, standardCategory);
    }

    @Test
    void testEqualsFailCompareWithNull() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        StandardCategory standardCategory = new StandardCategory(categoryName, categoryID, parentID);
        StandardCategory standardCategory2 = null;

        assertNotSame(standardCategory, standardCategory2);
        assertFalse(standardCategory.equals(standardCategory2));
    }

    @Test
    void testEqualsSucessFailNotSameClass() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        StandardCategory standardCategory = new StandardCategory(categoryName, categoryID, parentID);
        String notACategory = "string";

        assertFalse(standardCategory.equals(notACategory));
    }

    @Test
    void testEqualsFalseCategoryID() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        StandardCategory standardCategory = new StandardCategory(categoryName, categoryID, parentID);
        StandardCategory standardCategory2 = new StandardCategory(categoryName, new CategoryID(7L), parentID);

        assertNotSame(standardCategory, standardCategory2);
        assertNotEquals(standardCategory, standardCategory2);
    }

    @Test
    void testEqualsDifferentCategoryName() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        StandardCategory standardCategory = new StandardCategory(categoryName, categoryID, parentID);
        StandardCategory standardCategory2 = new StandardCategory(new CategoryName("Casa"), categoryID, parentID);

        assertNotSame(standardCategory, standardCategory2);
        assertNotEquals(standardCategory, standardCategory2);
    }

    @Test
    void testHashCode() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        StandardCategory standardCategory = new StandardCategory(categoryName, categoryID, parentID);
        StandardCategory standardCategory2 = new StandardCategory(categoryName, categoryID, parentID);

        assertEquals(standardCategory.hashCode(), standardCategory2.hashCode());
        assertNotEquals(0, standardCategory.hashCode());
    }

    @Test
    void getCategoryName() {

        CategoryName categoryName = new CategoryName("name");
        StandardCategory standardCategory = new StandardCategory(categoryName);

        CategoryName expected = new CategoryName("name");
        CategoryName result = standardCategory.getCategoryName();
        assertEquals(expected, result);
    }

    @Test
    void getParentID() {

        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        StandardCategory standardCategory = new StandardCategory(categoryName, parentID);

        ParentCategoryPath expected = new ParentCategoryPath("2L");
        ParentCategoryPath result = standardCategory.getParentCategoryPath();
        assertEquals(expected, result);
    }

    @Test
    void setCategoryName() {
        CategoryName categoryName = new CategoryName("name");
        StandardCategory standardCategory = new StandardCategory();
        standardCategory.setCategoryName(categoryName);

        CategoryName expected = new CategoryName("name");
        CategoryName result = standardCategory.getCategoryName();
        assertEquals(expected, result);
    }

    @Test
    void setCategoryID() {
        CategoryID categoryID = new CategoryID(1L);
        StandardCategory standardCategory = new StandardCategory();
        standardCategory.setCategoryID(categoryID);

        CategoryID expected = new CategoryID(1L);
        CategoryID result = standardCategory.id();

        assertEquals(expected,result);
    }

    @Test
    void setParentID() {
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        StandardCategory standardCategory = new StandardCategory();
        standardCategory.setParentID(parentID);

        ParentCategoryPath expected = new ParentCategoryPath("2L");
        ParentCategoryPath result = standardCategory.getParentCategoryPath();

        assertEquals(expected,result);
    }

    @Test
    void getFamilyID() {
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        StandardCategory standardCategory = new StandardCategory(categoryName, parentID);
        Optional<FamilyID> result = standardCategory.getFamilyID();
        assertFalse(result.isPresent());
    }
}