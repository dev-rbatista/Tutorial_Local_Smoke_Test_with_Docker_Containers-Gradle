package switchtwentytwenty.project.dto.category;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExternalStandardCategoryGroupFourDTOTest {

    @Test
    void getDesignation() {
        ExternalStandardCategoryGroupFourDTO dto = new ExternalStandardCategoryGroupFourDTO("CASA","12","10");
        String expected = "CASA";

        String result = dto.getName();

        assertEquals(expected,result);
    }

    @Test
    void setDesignation() {
        ExternalStandardCategoryGroupFourDTO dto = new ExternalStandardCategoryGroupFourDTO();
        dto.setName("CARRO");
        String expected = "CARRO";

        String result = dto.getName();

        assertEquals(expected,result);
    }

    @Test
    void getId() {
        ExternalStandardCategoryGroupFourDTO dto = new ExternalStandardCategoryGroupFourDTO("CASA","12","10");
        String expected = "12";

        String result = dto.getId();

        assertEquals(expected,result);
    }

    @Test
    void setId() {
        ExternalStandardCategoryGroupFourDTO dto = new ExternalStandardCategoryGroupFourDTO();
        dto.setId("12");
        String expected = "12";

        String result = dto.getId();

        assertEquals(expected,result);
    }

    @Test
    void getParentID() {
        ExternalStandardCategoryGroupFourDTO dto = new ExternalStandardCategoryGroupFourDTO("CASA","12","10");
        String expected = "10";

        String result = dto.getParentID();

        assertEquals(expected,result);
    }

    @Test
    void setParentID() {
        ExternalStandardCategoryGroupFourDTO dto = new ExternalStandardCategoryGroupFourDTO();
        dto.setParentID("10");
        String expected = "10";

        String result = dto.getParentID();

        assertEquals(expected,result);
    }

    @Test
    void testEqualsWithEqualDTOs() {
        ExternalStandardCategoryGroupFourDTO dtoOne = new ExternalStandardCategoryGroupFourDTO("CASA","12","10");
        ExternalStandardCategoryGroupFourDTO dtoTwo = new ExternalStandardCategoryGroupFourDTO("CASA","12","10");

        assertEquals(dtoOne,dtoTwo);
        assertNotSame(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsSameDTO() {
        ExternalStandardCategoryGroupFourDTO dtoOne = new ExternalStandardCategoryGroupFourDTO("CASA","12","10");
        ExternalStandardCategoryGroupFourDTO dtoTwo = dtoOne;

        assertEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsDifferentObjects() {
        ExternalStandardCategoryGroupFourDTO dto = new ExternalStandardCategoryGroupFourDTO("CASA","12","10");
        String notDTO = "not a DTO";

        assertNotEquals(dto,notDTO);
    }

    @Test
    void testEqualsDTOWithDifferentDesignations() {
        ExternalStandardCategoryGroupFourDTO dtoOne = new ExternalStandardCategoryGroupFourDTO("CASA","12","10");
        ExternalStandardCategoryGroupFourDTO dtoTwo = new ExternalStandardCategoryGroupFourDTO("CARRO","12","10");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsDTOWithDifferentID() {
        ExternalStandardCategoryGroupFourDTO dtoOne = new ExternalStandardCategoryGroupFourDTO("CASA","12","10");
        ExternalStandardCategoryGroupFourDTO dtoTwo = new ExternalStandardCategoryGroupFourDTO("CASA","11","10");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsDTOWithDifferentParentID() {
        ExternalStandardCategoryGroupFourDTO dtoOne = new ExternalStandardCategoryGroupFourDTO("CASA","12","10");
        ExternalStandardCategoryGroupFourDTO dtoTwo = new ExternalStandardCategoryGroupFourDTO("CASA","12","11");

        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testHashCodeSameHashCode() {
        ExternalStandardCategoryGroupFourDTO dtoOne = new ExternalStandardCategoryGroupFourDTO("CASA","12","10");
        ExternalStandardCategoryGroupFourDTO dtoTwo = new ExternalStandardCategoryGroupFourDTO("CASA","12","10");

        assertEquals(dtoOne.hashCode(),dtoTwo.hashCode());
        assertNotSame(dtoOne,dtoTwo);
    }

    @Test
    void testHashCodeDifferentHashCode() {
        ExternalStandardCategoryGroupFourDTO dtoOne = new ExternalStandardCategoryGroupFourDTO("CASA","12","10");
        ExternalStandardCategoryGroupFourDTO dtoTwo = new ExternalStandardCategoryGroupFourDTO("CARRO","12","10");

        assertNotEquals(dtoOne.hashCode(),dtoTwo.hashCode());
    }
}