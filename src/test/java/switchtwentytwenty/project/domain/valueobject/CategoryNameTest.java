package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.exceptions.InvalidNameException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class CategoryNameTest {

    @Test
    void categoryNameConstructorValid() {
        String name = "Compras";
        CategoryName categoryName = new CategoryName(name);

        assertNotNull(categoryName);
    }

    @Test
    void categoryNameConstructor_Invalid_Null() {
        String name = null;
        assertThrows(InvalidNameException.class, () -> {
            new CategoryName(name);
        });
    }
    @Test
    void categoryNameConstructor_Invalid_Empty() {
        String name = "";
        assertThrows(InvalidNameException.class, () -> {
            new CategoryName(name);
        });
    }
    @Test
    void categoryNameConstructor_Invalid_Blank() {
        String name = "      ";
        assertThrows(InvalidNameException.class, () -> {
            new CategoryName(name);
        });
    }

    @Test
    void equalsTestEqualCategoryName(){
        CategoryName categoryNameOne = new CategoryName("Utilities");
        CategoryName categoryNameTwo = new CategoryName("Utilities");

        assertEquals(categoryNameOne,categoryNameTwo);
        assertNotSame(categoryNameOne,categoryNameTwo);
    }

    @Test
    void equalsTestSameCategoryName(){
        CategoryName categoryNameOne = new CategoryName("Utilities");
        CategoryName categoryNameTwo = categoryNameOne;

        assertEquals(categoryNameOne,categoryNameTwo);
    }

    @Test
    void equalsTestDifferentCategoryName(){
        CategoryName categoryNameOne = new CategoryName("Utilities");
        CategoryName categoryNameTwo = new CategoryName("Compras");

        assertNotEquals(categoryNameOne,categoryNameTwo);
    }

    @Test
    void equalsTestDifferentObjects(){
        CategoryName categoryName = new CategoryName("Utilities");
        LocalDate notCategoryName = LocalDate.now();

        assertNotEquals(categoryName,notCategoryName);

    }

    @Test
    void equalsTestDifferentFromNull(){
        CategoryName categoryName = new CategoryName("Utilities");
        String nullString = null;

        assertNotEquals(categoryName,nullString);

    }


    @Test
    void hashCodeTestSameHashCode(){
        CategoryName categoryNameOne = new CategoryName("Utilities");
        CategoryName categoryNameTwo = new CategoryName("Utilities");

        assertEquals(categoryNameOne.hashCode(),categoryNameTwo.hashCode());
        assertNotSame(categoryNameOne,categoryNameTwo);
    }

    @Test
    void hashCodeTestDifferentHashCode(){
        CategoryName categoryNameOne = new CategoryName("Utilities");
        CategoryName categoryNameTwo = new CategoryName("Compras");

        assertNotEquals(categoryNameOne.hashCode(),categoryNameTwo.hashCode());
    }

    @Test
    void toStringTest(){
        String name = "Compras";
        CategoryName categoryName = new CategoryName(name);
        String expected = "COMPRAS";

        String result = categoryName.toString();

        assertEquals(expected,result);
    }

    @Test
    void toStringTestTrimmedInput(){
        String name = "  Compras  ";
        CategoryName categoryName = new CategoryName(name);
        String expected = "COMPRAS";

        String result = categoryName.toString();

        assertEquals(expected,result);
    }

}
