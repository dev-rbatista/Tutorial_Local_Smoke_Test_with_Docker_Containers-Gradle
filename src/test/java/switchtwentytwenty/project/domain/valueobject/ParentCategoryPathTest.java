package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParentCategoryPathTest {

    @Test
    void validateParentCategoryPathTestBlankInput(){
        assertThrows(IllegalArgumentException.class,()->new ParentCategoryPath("  "));
    }

    @Test
    void validateParentCategoryPathTestNullInputIsValid(){
        ParentCategoryPath result = new ParentCategoryPath(null);

        assertNotNull(result);
    }

    @Test
    void toStringTest(){
        ParentCategoryPath parentCategoryPath = new ParentCategoryPath("/external/categories/200345");
        String expected = "/external/categories/200345";

        String result = parentCategoryPath.toString();

        assertEquals(expected,result);
    }

    @Test
    void testEqualsSameParentCategoryPath(){
        ParentCategoryPath parentCategoryPathOne = new ParentCategoryPath("/asd/2000");
        ParentCategoryPath parentCategoryPathTwo = parentCategoryPathOne;

        assertEquals(parentCategoryPathOne,parentCategoryPathTwo);
    }

    @Test
    void testEqualsDifferentObjects(){
        ParentCategoryPath parentCategoryPath = new ParentCategoryPath("/asd/2000");
        String notAParentCategoryPath = "notParentCategory";

        assertNotEquals(parentCategoryPath,notAParentCategoryPath);
    }

    @Test
    void testHashCodeSameHashCode(){
        ParentCategoryPath parentCategoryPathOne = new ParentCategoryPath("/asd/2000");
        ParentCategoryPath parentCategoryPathTwo = new ParentCategoryPath("/asd/2000");

        assertEquals(parentCategoryPathOne.hashCode(),parentCategoryPathTwo.hashCode());
        assertNotSame(parentCategoryPathOne,parentCategoryPathTwo);
    }

    @Test
    void testHashCodeDifferentHashCodes(){
        ParentCategoryPath parentCategoryPathOne = new ParentCategoryPath("/asd/2000");
        ParentCategoryPath parentCategoryPathTwo = new ParentCategoryPath("/asd/2050");

        assertNotEquals(parentCategoryPathOne.hashCode(),parentCategoryPathTwo.hashCode());
    }

}