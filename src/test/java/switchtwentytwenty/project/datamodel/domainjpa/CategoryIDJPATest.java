package switchtwentytwenty.project.datamodel.domainjpa;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CategoryIDJPATest {

    @Test
    void categoryIDJPAConstructorTest(){
        long id = 12L;

        CategoryIDJPA categoryIDJPA = new CategoryIDJPA(id);

        assertNotNull(categoryIDJPA);
    }

    @Test
    void categoryIDJPANoArgConstructorTest(){

        CategoryIDJPA categoryIDJPA = new CategoryIDJPA();

        assertNotNull(categoryIDJPA);
    }

    @Test
    void categoryIDJPAEqualsTestEqualCategoryIDJPA(){
        long id = 12L;
        CategoryIDJPA categoryIDJPAOne = new CategoryIDJPA(id);
        CategoryIDJPA categoryIDJPATwo = new CategoryIDJPA(id);

        assertEquals(categoryIDJPAOne,categoryIDJPATwo);
        assertNotSame(categoryIDJPAOne,categoryIDJPATwo);
    }

    @Test
    void categoryIDJPAEqualsTestSameCategoryIDJPA(){
        long id = 12L;
        CategoryIDJPA categoryIDJPAOne = new CategoryIDJPA(id);
        CategoryIDJPA categoryIDJPATwo = categoryIDJPAOne;

        assertEquals(categoryIDJPAOne,categoryIDJPATwo);
    }

    @Test
    void categoryIDJPAEqualsTestNotEqualCategoryIDJPA(){
        long idOne = 12L;
        CategoryIDJPA categoryIDJPAOne = new CategoryIDJPA(idOne);
        long idTwo = 13L;
        CategoryIDJPA categoryIDJPATwo = new CategoryIDJPA(idTwo);

        assertNotEquals(categoryIDJPAOne,categoryIDJPATwo);
    }

    @Test
    void categoryIDJPAEqualsTestDifferentObjects(){
        long id = 12L;
        CategoryIDJPA categoryIDJPAOne = new CategoryIDJPA(id);
        LocalDate notCategoryIDJPA = LocalDate.now();

        assertNotEquals(categoryIDJPAOne,notCategoryIDJPA);
    }

    @Test
    void categoryIDJPAEqualsTestDifferentFromNull(){
        long id = 12L;
        CategoryIDJPA categoryIDJPAOne = new CategoryIDJPA(id);
        String nullString = null;

        assertNotEquals(categoryIDJPAOne,nullString);
    }

    @Test
    void categoryIDJPAHashCodeSameHashCode(){
        long id = 12L;
        CategoryIDJPA categoryIDJPAOne = new CategoryIDJPA(id);
        CategoryIDJPA categoryIDJPATwo = new CategoryIDJPA(id);

        assertEquals(categoryIDJPAOne.hashCode(),categoryIDJPATwo.hashCode());
        assertNotSame(categoryIDJPAOne,categoryIDJPATwo);
    }

    @Test
    void categoryIDJPAHashCodeDifferentHashCode(){
        long idOne = 12L;
        CategoryIDJPA categoryIDJPAOne = new CategoryIDJPA(idOne);
        long idTwo = 13L;
        CategoryIDJPA categoryIDJPATwo = new CategoryIDJPA(idTwo);

        assertNotEquals(categoryIDJPAOne.hashCode(),categoryIDJPATwo.hashCode());
    }

    @Test
    void categoryIDJPAToStringTest(){
        long idOne = 12L;
        CategoryIDJPA categoryIDJPAOne = new CategoryIDJPA(idOne);
        String expected = "CategoryIDJPA(id=12)";

        String result = categoryIDJPAOne.toString();

        assertEquals(expected,result);
    }

}