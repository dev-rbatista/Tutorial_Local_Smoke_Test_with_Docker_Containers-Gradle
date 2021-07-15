package switchtwentytwenty.project.dto.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.FamilyRESTController;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

class OutputEmailDTOTest {

    @Test
    @DisplayName("Should return true if the unpackEmail method is working correctly")
    void unpackEmail() {
        OutputEmailDTO outputEmailDTO = new OutputEmailDTO();
        outputEmailDTO.setEmail("tonyze@gmail.com");

        String expected = "tonyze@gmail.com";
        String result = outputEmailDTO.getEmail();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should return true if two identical OutputEmailDTO objects are compared with the equals method")
    void testEquals() {
        OutputEmailDTO outputEmailDTOOne = new OutputEmailDTO();
        OutputEmailDTO outputEmailDTOTwo = new OutputEmailDTO();
        outputEmailDTOOne.setEmail("tonyze@gmail.com");
        outputEmailDTOTwo.setEmail("tonyze@gmail.com");

        assertEquals(outputEmailDTOOne, outputEmailDTOTwo);
        assertNotSame(outputEmailDTOOne, outputEmailDTOTwo);
    }

    @Test
    void testEqualsDifferentLinks() {
        OutputEmailDTO outputEmailDTOOne = new OutputEmailDTO();
        outputEmailDTOOne.setEmail("tonyze@gmail.com");
        OutputEmailDTO outputEmailDTOTwo = new OutputEmailDTO();
        outputEmailDTOTwo.setEmail("tonyze@gmail.com");
        Link link = linkTo(FamilyRESTController.class).withRel("Add New Family");
        outputEmailDTOTwo.add(link);

        assertNotEquals(outputEmailDTOOne, outputEmailDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two different OutputEmailDTO objects are compared with the equals method")
    void testEqualsFalse() {
        OutputEmailDTO outputEmailDTOOne = new OutputEmailDTO();
        OutputEmailDTO outputEmailDTOTwo = new OutputEmailDTO();
        outputEmailDTOOne.setEmail("tonyze@gmail.com");
        outputEmailDTOTwo.setEmail("tonyze2@gmail.com");

        assertNotEquals(outputEmailDTOOne, outputEmailDTOTwo);
    }

    @Test
    void testEqualsSameOutputEmailDTO() {
        OutputEmailDTO outputEmailDTOOne = new OutputEmailDTO();
        outputEmailDTOOne.setEmail("tonyze@gmail.com");
        OutputEmailDTO outputEmailDTOTwo = outputEmailDTOOne;

        assertEquals(outputEmailDTOOne, outputEmailDTOTwo);
    }

    @Test
    void testEqualsDifferentObjects() {
        OutputEmailDTO outputEmailDTO = new OutputEmailDTO();
        String notOutputEmailDTO = "not a DTO";

        assertNotEquals(outputEmailDTO, notOutputEmailDTO);
    }

    @Test
    void testEqualsDifferentFromNull() {
        OutputEmailDTO outputEmailDTO = new OutputEmailDTO();
        outputEmailDTO.setEmail("tonyze@gmail.com");
        String nullString = null;

        assertNotEquals(outputEmailDTO, nullString);
    }

    @Test
    @DisplayName("Should return true if two identical OutputEmailDTO objects have their hashcodes compared")
    void testHashCode() {
        OutputEmailDTO outputEmailDTOOne = new OutputEmailDTO();
        OutputEmailDTO outputEmailDTOTwo = new OutputEmailDTO();
        outputEmailDTOOne.setEmail("tonyze@gmail.com");
        outputEmailDTOTwo.setEmail("tonyze@gmail.com");

        assertNotSame(outputEmailDTOOne,outputEmailDTOTwo);
        assertEquals(outputEmailDTOOne.hashCode(),outputEmailDTOTwo.hashCode());
    }

    @Test
    @DisplayName("Should return false if two different OutputEmailDTO objects have their hashcodes compared")
    void testHashCodeFalse() {
        OutputEmailDTO outputEmailDTOOne = new OutputEmailDTO();
        OutputEmailDTO outputEmailDTOTwo = new OutputEmailDTO();
        outputEmailDTOOne.setEmail("tonyze@gmail.com");
        outputEmailDTOTwo.setEmail("tonyze2@gmail.com");

        assertNotEquals(outputEmailDTOOne.hashCode(),outputEmailDTOTwo.hashCode());
    }

    @Test
    void equalsWithNull() {
        OutputEmailDTO outputEmailDTOOne = new OutputEmailDTO("tonyze@gmail.com");
        OutputEmailDTO outputEmailDTOTwo = null;

        assertNotEquals(outputEmailDTOOne, outputEmailDTOTwo);
    }

    @Test
    void equalsWithAnotherClass() {
        OutputEmailDTO outputEmailDTOOne = new OutputEmailDTO("tonyze@gmail.com");
        String outputEmailDTOTwo = "tonyze@gmail.com";

        assertNotEquals(outputEmailDTOOne, outputEmailDTOTwo);
    }

}