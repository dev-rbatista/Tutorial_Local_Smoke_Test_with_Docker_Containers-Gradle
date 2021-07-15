package switchtwentytwenty.project.datamodel.domainjpa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryJPATest {

    // CategoryJPA data 1
    String categoryName = "compras";
    Long categoryIDJPA = 13L;
    String parentID = "12L";
    FamilyIDJPA familyIDJPA = new FamilyIDJPA();

    // CategoryJPA data 2
    String categoryNameTwo = "vendas";
    Long categoryIDJPATwo = 1L;
    String parentIDTwo = "0";
    FamilyIDJPA familyIDJPATwo = new FamilyIDJPA("email@email.com");

    @Test
    void noArgsConstructorTest() {
        CategoryJPA categoryJPA = new CategoryJPA();

        assertNotNull(categoryJPA);
    }

    @Test
    void noCategoryIDConstructorTest() {
        CategoryJPA categoryJPA = new CategoryJPA.Builder(categoryName).withParentID(parentID).withFamilyIDJPA(familyIDJPA).build();

        assertNotNull(categoryJPA);
    }

    @Test
    void allArgsConstructorTest() {
        CategoryJPA categoryJpa = new CategoryJPA.Builder(categoryName).withCategoryIDJPA(categoryIDJPA).withParentID(parentID).withFamilyIDJPA(familyIDJPA).build();

        assertNotNull(categoryJpa);
    }

    @Test
    void getCategoryNameTest() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryName, categoryIDJPA, parentID, familyIDJPA);
        String expected = "compras";

        String result = categoryJpa.getCategoryName();

        assertEquals(expected, result);
    }

    @Test
    void getCategoryNameSecondTest() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryNameTwo, categoryIDJPATwo, parentIDTwo, familyIDJPATwo);
        String expected = "vendas";

        String result = categoryJpa.getCategoryName();

        assertEquals(expected, result);
    }

    @Test
    void getCategoryIDTest() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryName, categoryIDJPA, parentID, familyIDJPA);
        Long expected = 13L;

        Long result = categoryJpa.getCategoryIDJPA();

        assertEquals(expected, result);
    }

    @Test
    void getCategoryIDSecondTest() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryNameTwo, categoryIDJPATwo, parentIDTwo, familyIDJPATwo);
        CategoryIDJPA expected = new CategoryIDJPA(13L);

        Long result = categoryJpa.getCategoryIDJPA();

        assertNotEquals(expected, result);
    }

    @Test
    void getParentIDTest() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryName, categoryIDJPA, parentID, familyIDJPA);
        String expected = "12L";

        String result = categoryJpa.getParentID();

        assertEquals(expected, result);
    }

    @Test
    void getParentIDSecondTest() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryNameTwo, categoryIDJPATwo, parentIDTwo, familyIDJPATwo);
        String expected = "0";

        String result = categoryJpa.getParentID();

        assertEquals(expected, result);
    }

    @Test
    void getFamilyIDTest() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryName, categoryIDJPA, parentID, null);

        FamilyIDJPA result = categoryJpa.getFamilyIDJPA();

        assertNull(result);
    }

    @Test
    void getFamilyIDSecondTest() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryNameTwo, categoryIDJPATwo, parentIDTwo, familyIDJPATwo);
        FamilyIDJPA expected = new FamilyIDJPA("email@email.com");

        FamilyIDJPA result = categoryJpa.getFamilyIDJPA();

        assertEquals(expected, result);
    }

    @Test
    void equalAndSameObjectsTest() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryName, categoryIDJPA, parentID, familyIDJPA);
        CategoryJPA categoryJPATwo = categoryJpa;

        assertEquals(categoryJpa, categoryJPATwo);
        assertSame(categoryJpa, categoryJPATwo);
    }

    @Test
    void equalAndNotSameObjectsTest() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryName, categoryIDJPA, parentID, familyIDJPA);
        CategoryJPA categoryJpaTwo = new CategoryJPA(categoryName, categoryIDJPA, parentID, familyIDJPA);

        assertEquals(categoryJpa, categoryJpaTwo);
        assertNotSame(categoryJpa, categoryJpaTwo);
    }

    @Test
    void notEqualObjectsTest() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryName, categoryIDJPA, parentID, familyIDJPA);
        CategoryJPA categoryJpaTwo = new CategoryJPA(categoryNameTwo, categoryIDJPA, parentID, familyIDJPA);

        assertNotEquals(categoryJpa,categoryJpaTwo);
    }

    @Test
    void notEqualObjectsTestDifferentCategoryIDs() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryName, categoryIDJPA, parentID, familyIDJPA);
        CategoryJPA categoryJpaTwo = new CategoryJPA(categoryName, categoryIDJPATwo, parentID, familyIDJPA);

        assertNotEquals(categoryJpa,categoryJpaTwo);
    }

    @Test
    void notEqualObjectsTestDifferentParentIDs() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryName, categoryIDJPA, parentID, familyIDJPA);
        CategoryJPA categoryJpaTwo = new CategoryJPA(categoryName, categoryIDJPA, parentIDTwo, familyIDJPA);

        assertNotEquals(categoryJpa,categoryJpaTwo);
    }

    @Test
    void notEqualDifferentClassObjectTest() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryName, categoryIDJPA, parentID, familyIDJPA);
        CategoryIDJPA categoryJpaTwo = new CategoryIDJPA();

        assertNotEquals(categoryJpa,categoryJpaTwo);
    }

    @Test
    void notEqualNullObjectTest() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryName, categoryIDJPA, parentID, familyIDJPA);
        CategoryJPA categoryJpaTwo = null;

        assertNotEquals(categoryJpa,categoryJpaTwo);
    }

    @Test
    void equalObjectsHashCodeTest() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryName, categoryIDJPA, parentID, familyIDJPA);
        CategoryJPA categoryJpaTwo = new CategoryJPA(categoryName, categoryIDJPA, parentID, familyIDJPA);

        assertEquals(categoryJpa.hashCode(), categoryJpaTwo.hashCode());
    }

    @Test
    void notEqualObjectsHashCodeTest() {
        CategoryJPA categoryJpa = new CategoryJPA(categoryName, categoryIDJPA, parentID, familyIDJPA);
        CategoryJPA categoryJpaTwo = new CategoryJPA(categoryNameTwo, categoryIDJPATwo, parentIDTwo, familyIDJPATwo);

        assertNotEquals(categoryJpa.hashCode(),categoryJpaTwo.hashCode());
    }


}