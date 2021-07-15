package switchtwentytwenty.project.domain.aggregates.category;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;

import static org.junit.jupiter.api.Assertions.*;

class CustomCategoryTest {

    @Test
    void testEquals() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);
        CustomCategory customCategory2 = new CustomCategory(categoryID, parentID, categoryName, familyID);

        assertNotSame(customCategory, customCategory2);
        assertEquals(customCategory, customCategory2);
    }

    @Test
    void testEqualsFalseCategoryID() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);
        CustomCategory customCategory2 = new CustomCategory(new CategoryID(5L), parentID, categoryName, familyID);


        assertNotEquals(customCategory, customCategory2);
    }

    @Test
    void testEqualsFalseParentID() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);
        CustomCategory customCategory2 = new CustomCategory(categoryID, new ParentCategoryPath("6L"), categoryName, familyID);

        assertNotSame(customCategory, customCategory2);
        assertNotEquals(customCategory, customCategory2);
    }

    @Test
    void testEqualsFalseCategoryName() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);
        CustomCategory customCategory2 = new CustomCategory(categoryID, parentID, new CategoryName("newname"), familyID);

        assertNotSame(customCategory, customCategory2);
        assertNotEquals(customCategory, customCategory2);
    }

    @Test
    void testEqualsFalseFamilyID() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);
        CustomCategory customCategory2 = new CustomCategory(categoryID, parentID, categoryName, new FamilyID("we@we.com"));

        assertNotSame(customCategory, customCategory2);
        assertNotEquals(customCategory, customCategory2);
    }


    @Test
    void testHashCode() {
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CustomCategory customCategory = new CustomCategory(parentID, categoryName, familyID);
        CustomCategory customCategory2 = new CustomCategory(parentID, categoryName, familyID);

        assertNotSame(customCategory, customCategory2);
        assertEquals(customCategory.hashCode(), customCategory2.hashCode());
        assertNotEquals(0,customCategory2.hashCode());
    }

    @Test
    void id() {
        CategoryID categoryID = new CategoryID(1L);
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);

        CategoryID expected = new CategoryID(1L);
        CategoryID result = customCategory.id();

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }

    @Test
    void hasID() {
        CategoryID categoryID = new CategoryID(1L);
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);

        CategoryID correctID = new CategoryID(1L);
        CategoryID wrongID = new CategoryID(5L);
        assertTrue(customCategory.hasID(correctID));
        assertFalse(customCategory.hasID(wrongID));
    }


    @Test
    void getParentID() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);

        ParentCategoryPath expected = new ParentCategoryPath("2L");
        ParentCategoryPath result = customCategory.getParentCategoryPath();

        assertEquals(expected, result);
    }

    @Test
    void getCategoryName() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);

        CategoryName expected = new CategoryName("name");
        CategoryName result = customCategory.getCategoryName();
        assertEquals(expected, result);

    }

    @Test
    void getFamilyID() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);

        FamilyID expected = new FamilyID("familyid@gmail.com");
        FamilyID result = customCategory.getFamilyID().get();

        assertEquals(expected, result);
    }

    @Test
    void setCategoryID() {
        CategoryID newID = new CategoryID(6L);
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);

        customCategory.setCategoryID(newID);
        CategoryID expected = new CategoryID(6L);
        CategoryID result = customCategory.id();

        assertEquals(expected, result);


    }

    @Test
    void setParentID() {
        ParentCategoryPath newID = new ParentCategoryPath("6L");
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);

        customCategory.setParentID(newID);
        ParentCategoryPath expected = new ParentCategoryPath("6L");
        ParentCategoryPath result = customCategory.getParentCategoryPath();

        assertEquals(expected, result);
    }

    @Test
    void setCategoryName() {
        CategoryName newName = new CategoryName("6L");
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);

        customCategory.setCategoryName(newName);
        CategoryName expected = new CategoryName("6L");
        CategoryName result = customCategory.getCategoryName();

        assertEquals(expected, result);

    }

    @Test
    void setFamilyID() {
        FamilyID newFamilyID = new FamilyID("newid@gmail.com");
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);

        customCategory.setFamilyID(newFamilyID);
        FamilyID expected = new FamilyID("newid@gmail.com");
        FamilyID result = customCategory.getFamilyID().get();

        assertEquals(expected, result);
    }

    @Test
    void noArgsConstructorTest() {

        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        CustomCategory customCategory = new CustomCategory();

        customCategory.setCategoryID(categoryID);
        customCategory.setCategoryName(categoryName);
        customCategory.setFamilyID(familyID);
        customCategory.setParentID(parentID);

        assertNotNull(customCategory);

    }

    @Test
    void testEqualsSameObject() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);

        assertTrue(customCategory.equals(customCategory));
    }

    @Test
    void testEqualsFalseCompareWithNull() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);
        CustomCategory nullCustomCategory = null;
        assertFalse(customCategory.equals(nullCustomCategory));
    }

    @Test
    void testEqualsFalseDifferentClass() {
        CategoryID categoryID = new CategoryID(1L);
        ParentCategoryPath parentID = new ParentCategoryPath("2L");
        CategoryName categoryName = new CategoryName("name");
        FamilyID familyID = new FamilyID("familyid@gmail.com");
        CustomCategory customCategory = new CustomCategory(categoryID, parentID, categoryName, familyID);
        String notACategory = "string";
        assertFalse(customCategory.equals(notACategory));
    }
}