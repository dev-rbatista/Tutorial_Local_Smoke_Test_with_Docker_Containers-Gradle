package switchtwentytwenty.project.dto.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.FamilyRESTController;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

class OutputCategoryDTOTest {

    @Test
    @DisplayName("Test to check if the getCategoryName method functions correctly")
    void getCategoryName() {
        OutputCategoryDTO outputCategoryDTO = new OutputCategoryDTO("Name", "2L", "3L");
        String expected = "Name";
        String result = outputCategoryDTO.getCategoryName();
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test to check if the getCategoryID method functions correctly")
    void getCategoryID() {
        OutputCategoryDTO outputCategoryDTO = new OutputCategoryDTO("Name", "2L", "3L");
        String expected = "2L";
        String result = outputCategoryDTO.getCategoryID();
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test to check if the getParentID method functions correctly")
    void getParentID() {
        OutputCategoryDTO outputCategoryDTO = new OutputCategoryDTO("Name", "2L", "3L");
        String expected = "3L";
        String result = outputCategoryDTO.getParentID();
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should return true if two identical OutputCategoryDTO objects are compared using the equals method")
    void testEquals() {
        OutputCategoryDTO outputCategoryDTOOne = new OutputCategoryDTO("Name", "2L", "3L");
        OutputCategoryDTO outputCategoryDTOTwo = new OutputCategoryDTO("Name", "2L", "3L");

        Assertions.assertEquals(outputCategoryDTOOne, outputCategoryDTOTwo);
        Assertions.assertNotSame(outputCategoryDTOOne, outputCategoryDTOTwo);
    }

    @Test
    @DisplayName("Should return true if it compares two of the same OutputCategoryDTO objects")
    void testEqualsSameObject() {
        OutputCategoryDTO outputCategoryDTOOne = new OutputCategoryDTO("Name", "2L", "3L");
        OutputCategoryDTO outputCategoryDTOTwo = outputCategoryDTOOne;

        Assertions.assertEquals(outputCategoryDTOOne, outputCategoryDTOTwo);
    }

    @Test
    @DisplayName("Should return false if it compares OutputCategoryDTO to an object of another class")
    void testEqualsDifferentClass() {
        OutputCategoryDTO outputCategoryDTOOne = new OutputCategoryDTO("Name", "2L", "3L");
        String otherClass = "This is another class";

        Assertions.assertNotEquals(outputCategoryDTOOne, otherClass);
    }

    @Test
    @DisplayName("Should return false if it compares OutputCategoryDTO to a null object")
    void testEqualsWithNullObject() {
        OutputCategoryDTO outputCategoryDTOOne = new OutputCategoryDTO("Name", "2L", "3L");
        String nullString = null;

        Assertions.assertNotEquals(outputCategoryDTOOne, nullString);
    }

    @Test
    @DisplayName("Should return false if two different OutputCategoryDTO objects are compared using the equals method")
    void testEqualsFail() {
        OutputCategoryDTO outputCategoryDTOOne = new OutputCategoryDTO("Name", "2L", "3L");
        OutputCategoryDTO outputCategoryDTOTwo = new OutputCategoryDTO("Names", "2L", "3L");

        Assertions.assertNotEquals(outputCategoryDTOOne, outputCategoryDTOTwo);
        Assertions.assertNotSame(outputCategoryDTOOne, outputCategoryDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two OutputCategoryDTO objects are compared while having different categoryName")
    void testEqualsDifferentCategoryName() {
        OutputCategoryDTO outputCategoryDTOOne = new OutputCategoryDTO("Name", "2L", "3L");
        OutputCategoryDTO outputCategoryDTOTwo = new OutputCategoryDTO("otherName", "2L", "3L");

        Assertions.assertNotEquals(outputCategoryDTOOne, outputCategoryDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two OutputCategoryDTO objects are compared while having different categoryID")
    void testEqualsDifferentCategoryID() {
        OutputCategoryDTO outputCategoryDTOOne = new OutputCategoryDTO("Name", "2L", "3L");
        OutputCategoryDTO outputCategoryDTOTwo = new OutputCategoryDTO("Name", "5L", "3L");

        Assertions.assertNotEquals(outputCategoryDTOOne, outputCategoryDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two OutputCategoryDTO objects are compared while having different parentID")
    void testEqualsDifferentParentID() {
        OutputCategoryDTO outputCategoryDTOOne = new OutputCategoryDTO("Name", "2L", "3L");
        OutputCategoryDTO outputCategoryDTOTwo = new OutputCategoryDTO("Name", "2L", "5L");

        Assertions.assertNotEquals(outputCategoryDTOOne, outputCategoryDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two OutputCategoryDTO objects are compared while having different FamilyID")
    void testEqualsDifferentFamilyID() {
        OutputCategoryDTO outputCategoryDTOOne = new OutputCategoryDTO("Name", "2L", "3L","@admin@gmail.com");
        OutputCategoryDTO outputCategoryDTOTwo = new OutputCategoryDTO("Name", "2L", "3L","@notAdmin@gmail.com");

        Assertions.assertNotEquals(outputCategoryDTOOne, outputCategoryDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two OutputCategoryDTO objects are compared while having different links added")
    void testEqualsDifferentLinks() {
        OutputCategoryDTO outputCategoryDTOOne = new OutputCategoryDTO("Name", "2L", "3L","@admin@gmail.com");
        Link linkOne = linkTo(methodOn(FamilyRESTController.class).familiesOptions()).withRel("POST - Add New Family");
        outputCategoryDTOOne.add(linkOne);
        OutputCategoryDTO outputCategoryDTOTwo = new OutputCategoryDTO("Name", "2L", "3L","@admin@gmail.com");
        Link linkTwo = linkTo(methodOn(FamilyRESTController.class).familiesOptions()).withRel("Add New Family");
        outputCategoryDTOOne.add(linkTwo);

        Assertions.assertNotEquals(outputCategoryDTOOne, outputCategoryDTOTwo);
    }

    @Test
    @DisplayName("Should return true if two identical OutputCategoryDTO objects are compared using their hashcodes")
    void testHashCode() {
        OutputCategoryDTO outputCategoryDTOOne = new OutputCategoryDTO("Name", "2L", "3L");
        OutputCategoryDTO outputCategoryDTOTwo = new OutputCategoryDTO("Name", "2L", "3L");

        Assertions.assertEquals(outputCategoryDTOOne.hashCode(), outputCategoryDTOTwo.hashCode());
        Assertions.assertNotSame(outputCategoryDTOOne, outputCategoryDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two different OutputCategoryDTO objects are compared using their hashcodes")
    void testHashCodeFail() {
        OutputCategoryDTO outputCategoryDTOOne = new OutputCategoryDTO("Name", "2L", "3L");
        OutputCategoryDTO outputCategoryDTOTwo = new OutputCategoryDTO("Names", "2L", "3L");

        Assertions.assertNotEquals(outputCategoryDTOOne.hashCode(), outputCategoryDTOTwo.hashCode());
        Assertions.assertNotSame(outputCategoryDTOOne, outputCategoryDTOTwo);
    }

    @Test
    void setCategoryName() {
        OutputCategoryDTO outputCategoryDTOOne = new OutputCategoryDTO("Names", "2L", "3L");
        outputCategoryDTOOne.setCategoryName("NewName");
        String expected = "NewName";
        String result = outputCategoryDTOOne.getCategoryName();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void setCategoryID() {
        OutputCategoryDTO outputCategoryDTOOne = new OutputCategoryDTO("Names", "2L", "3L");
        outputCategoryDTOOne.setCategoryID("5L");
        String expected = "5L";
        String result = outputCategoryDTOOne.getCategoryID();
        Assertions.assertEquals(expected, result);

    }

    @Test
    void setParentID() {
        OutputCategoryDTO outputCategoryDTOOne = new OutputCategoryDTO("Names", "2L", "3L");
        outputCategoryDTOOne.setParentID("5L");
        String expected = "5L";
        String result = outputCategoryDTOOne.getParentID();
        Assertions.assertEquals(expected, result);
    }

    @Test
    void noArgsConstructorTest() {
        OutputCategoryDTO outputCategoryDTO = new OutputCategoryDTO();
        outputCategoryDTO.setCategoryID("3L");
        assertNotNull(outputCategoryDTO);
    }

    @Test
    void AllArgsConstructor() {
        OutputCategoryDTO result = new OutputCategoryDTO("name", "2", "1", "@family@id.com");
        assertNotNull(result);
    }

    @Test
    void allArgsConstructorTest(){
        OutputCategoryDTO outputCategoryDTO = new OutputCategoryDTO("Home","20040", "api/categories/20020","@tonyze@gmail.com");
        Assertions.assertNotNull(outputCategoryDTO);
    }

    @Test
    void getFamilyIDTest() {
        OutputCategoryDTO outputCategoryDTO = new OutputCategoryDTO("Home","20040", "api/categories/20020","@tonyze@gmail.com");
        String expected = "@tonyze@gmail.com";

        String result = outputCategoryDTO.getFamilyID();

        Assertions.assertEquals(expected,result);
    }

    @Test
    void setFamilyIDTest() {
        OutputCategoryDTO outputCategoryDTO = new OutputCategoryDTO();
        String expected = "@tonyze@gmail.com";

        outputCategoryDTO.setFamilyID("@tonyze@gmail.com");
        String result = outputCategoryDTO.getFamilyID();

        Assertions.assertEquals(expected,result);
    }




}