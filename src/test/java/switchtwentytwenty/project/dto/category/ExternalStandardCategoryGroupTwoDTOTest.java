package switchtwentytwenty.project.dto.category;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExternalStandardCategoryGroupTwoDTOTest {

    @Test
    void getDesignation() {
        ExternalStandardCategoryGroupTwoDTO dto = new ExternalStandardCategoryGroupTwoDTO("CASA","12","10");
        String expected = "CASA";

        String result = dto.getDesignation();

        assertEquals(expected,result);
    }

    @Test
    void setDesignation() {
        ExternalStandardCategoryGroupTwoDTO dto = new ExternalStandardCategoryGroupTwoDTO();
        dto.setDesignation("CARRO");
        String expected = "CARRO";

        String result = dto.getDesignation();

        assertEquals(expected,result);
    }

    @Test
    void getId() {
        ExternalStandardCategoryGroupTwoDTO dto = new ExternalStandardCategoryGroupTwoDTO("CASA","12","10");
        String expected = "12";

        String result = dto.getId();

        assertEquals(expected,result);
    }

    @Test
    void setId() {
        ExternalStandardCategoryGroupTwoDTO dto = new ExternalStandardCategoryGroupTwoDTO();
        dto.setId("12");
        String expected = "12";

        String result = dto.getId();

        assertEquals(expected,result);
    }

    @Test
    void getParentID() {
        ExternalStandardCategoryGroupTwoDTO dto = new ExternalStandardCategoryGroupTwoDTO("CASA","12","10");
        String expected = "10";

        String result = dto.getParentID();

        assertEquals(expected,result);
    }

    @Test
    void setParentID() {
        ExternalStandardCategoryGroupTwoDTO dto = new ExternalStandardCategoryGroupTwoDTO();
        dto.setParentID("10");
        String expected = "10";

        String result = dto.getParentID();

        assertEquals(expected,result);
    }

    @Test
    void testEqualsWithEqualDTOs() {
        ExternalStandardCategoryGroupTwoDTO dtoOne = new ExternalStandardCategoryGroupTwoDTO("CASA","12","10");
        ExternalStandardCategoryGroupTwoDTO dtoTwo = new ExternalStandardCategoryGroupTwoDTO("CASA","12","10");

        assertEquals(dtoOne,dtoTwo);
        assertNotSame(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsSameDTO() {
        ExternalStandardCategoryGroupTwoDTO dtoOne = new ExternalStandardCategoryGroupTwoDTO("CASA","12","10");
        ExternalStandardCategoryGroupTwoDTO dtoTwo = dtoOne;

        assertEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsDifferentObjects() {
        ExternalStandardCategoryGroupTwoDTO dto = new ExternalStandardCategoryGroupTwoDTO("CASA","12","10");
        String notDTO = "not a DTO";

        assertNotEquals(dto,notDTO);
    }

    @Test
    void testEqualsDTOWithDifferentDesignations() {
        ExternalStandardCategoryGroupTwoDTO dtoOne = new ExternalStandardCategoryGroupTwoDTO("CASA","12","10");
        ExternalStandardCategoryGroupTwoDTO dtoTwo = new ExternalStandardCategoryGroupTwoDTO("CARRO","12","10");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsDTOWithDifferentID() {
        ExternalStandardCategoryGroupTwoDTO dtoOne = new ExternalStandardCategoryGroupTwoDTO("CASA","12","10");
        ExternalStandardCategoryGroupTwoDTO dtoTwo = new ExternalStandardCategoryGroupTwoDTO("CASA","11","10");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsDTOWithDifferentParentID() {
        ExternalStandardCategoryGroupTwoDTO dtoOne = new ExternalStandardCategoryGroupTwoDTO("CASA","12","10");
        ExternalStandardCategoryGroupTwoDTO dtoTwo = new ExternalStandardCategoryGroupTwoDTO("CASA","12","11");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testHashCodeSameHashCode() {
        ExternalStandardCategoryGroupTwoDTO dtoOne = new ExternalStandardCategoryGroupTwoDTO("CASA","12","10");
        ExternalStandardCategoryGroupTwoDTO dtoTwo = new ExternalStandardCategoryGroupTwoDTO("CASA","12","10");

        assertEquals(dtoOne.hashCode(),dtoTwo.hashCode());
        assertNotSame(dtoOne,dtoTwo);
    }

    @Test
    void testHashCodeDifferentHashCode() {
        ExternalStandardCategoryGroupTwoDTO dtoOne = new ExternalStandardCategoryGroupTwoDTO("CASA","12","10");
        ExternalStandardCategoryGroupTwoDTO dtoTwo = new ExternalStandardCategoryGroupTwoDTO("CARRO","12","10");

        assertNotEquals(dtoOne.hashCode(),dtoTwo.hashCode());
    }
}