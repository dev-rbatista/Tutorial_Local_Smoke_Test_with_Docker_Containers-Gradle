package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CategoryIDTest {

    Long categoryID = 1L;
    Long differentCategoryID = 2L;

    @Test
    void categoryIDConstructorValid() {
        CategoryID id = new CategoryID(categoryID);

        assertNotNull(id);
    }

    @Test
    void equalsTestDifferentCategoryIDs() {
        CategoryID idOne = new CategoryID(categoryID);
        CategoryID idTwo = new CategoryID(differentCategoryID);
        assertNotEquals(idOne, idTwo);
    }

    @Test
    void equalsTestSameCategoryIDs() {
        CategoryID idOne = new CategoryID(categoryID);
        CategoryID idTwo = new CategoryID(categoryID);
        assertEquals(idOne, idTwo);
        assertNotSame(idOne, idTwo);
    }

    @Test
    void equalsTestSameObject() {
        CategoryID idOne = new CategoryID(categoryID);
        CategoryID idTwo = idOne;

        assertEquals(idOne, idTwo);
    }

    @Test
    void equalsTestDifferentObjects() {
        CategoryID id = new CategoryID(categoryID);
        LocalDate notID = LocalDate.now();

        assertNotEquals(id, notID);
    }

    @Test
    void hashCodeTestDifferentHashCode() {
        CategoryID idOne = new CategoryID(categoryID);
        CategoryID idTwo = new CategoryID(differentCategoryID);
        assertNotEquals(idOne.hashCode(), idTwo.hashCode());
    }

    @Test
    void hashCodeTestSameHashCode() {
        CategoryID idOne = new CategoryID(categoryID);
        CategoryID idTwo = new CategoryID(categoryID);
        assertEquals(idOne.hashCode(), idTwo.hashCode());
        assertNotSame(idOne, idTwo);
    }

    @Test
    void testToString() {
        CategoryID id = new CategoryID(categoryID);
        String expected = "1";
        String result = id.toString();

        assertEquals(expected, result);
    }

    @Test
    void getId() {
        CategoryID id = new CategoryID(categoryID);
        Long expected = 1L;
        Long result = Long.valueOf(id.getId());

        assertEquals(expected, result);

    }

    @Test
    void categoryIDConstructorWithString(){
        CategoryID id = new CategoryID("1");

        assertNotNull(id);
    }
}