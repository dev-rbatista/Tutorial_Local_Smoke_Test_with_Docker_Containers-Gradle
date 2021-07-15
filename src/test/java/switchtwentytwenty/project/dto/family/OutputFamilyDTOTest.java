package switchtwentytwenty.project.dto.family;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.FamilyRESTController;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

class OutputFamilyDTOTest {

    @Test
    @DisplayName("Test to check if the getFamilyName method functions correctly")
    void getFamilyName() {
        OutputFamilyDTO outputFamilyDTO = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        String expected = "Silva";

        String result = outputFamilyDTO.getFamilyName();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void setFamilyNameTest() {
        OutputFamilyDTO outputFamilyDTO = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        String expected = "Costa";

        outputFamilyDTO.setFamilyName("Costa");
        String result = outputFamilyDTO.getFamilyName();

        Assertions.assertEquals(expected, result);
    }


    @Test
    @DisplayName("Test to check if the getAdminID method functions correctly")
    void getAdminID() {
        OutputFamilyDTO outputFamilyDTO = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        String expected = "silva@gmail.com";

        String result = outputFamilyDTO.getAdminID();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void setAdminIDTest() {
        OutputFamilyDTO outputFamilyDTO = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        String expected = "costa@gmail.com";

        outputFamilyDTO.setAdminID("costa@gmail.com");
        String result = outputFamilyDTO.getAdminID();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void getRegistrationDateTest() {
        OutputFamilyDTO outputFamilyDTO = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        String expected = "12/12/1990";

        String result = outputFamilyDTO.getRegistrationDate();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void setRegistrationDateTest() {
        OutputFamilyDTO outputFamilyDTO = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        String expected = "01/03/2021";

        outputFamilyDTO.setRegistrationDate("01/03/2021");
        String result = outputFamilyDTO.getRegistrationDate();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void getFamilyIDTest() {
        OutputFamilyDTO outputFamilyDTO = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        String expected = "@silva@gmail.com";

        String result = outputFamilyDTO.getFamilyID();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void setFamilyIDTest() {
        OutputFamilyDTO outputFamilyDTO = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        String expected = "@costa@gmail.com";

        outputFamilyDTO.setFamilyID("@costa@gmail.com");
        String result = outputFamilyDTO.getFamilyID();

        Assertions.assertEquals(expected, result);
    }


    @Test
    @DisplayName("Should return true if two identical OutputFamilyDTO objects are compared using the equals method")
    void testEquals() {
        OutputFamilyDTO outputFamilyDTO1 = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        OutputFamilyDTO outputFamilyDTO2 = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");

        Assertions.assertEquals(outputFamilyDTO1, outputFamilyDTO2);
        Assertions.assertNotSame(outputFamilyDTO1, outputFamilyDTO2);

    }

    @Test
    @DisplayName("Should return false if two different OutputFamilyDTO objects are compared using the equals method")
    void testEqualsFail() {
        OutputFamilyDTO outputFamilyDTO1 = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        OutputFamilyDTO outputFamilyDTO2 = new OutputFamilyDTO("Silvas", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");

        Assertions.assertNotEquals(outputFamilyDTO1, outputFamilyDTO2);
        Assertions.assertNotSame(outputFamilyDTO1, outputFamilyDTO2);

    }

    @Test
    void testEqualsDifferentFamilyIDsInDTOs() {
        OutputFamilyDTO outputFamilyDTOOne = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        OutputFamilyDTO outputFamilyDTOTwo = new OutputFamilyDTO("Silva", "@admin@gmail.com", "silva@gmail.com", "12/12/1990");

        Assertions.assertNotEquals(outputFamilyDTOOne, outputFamilyDTOTwo);
    }

    @Test
    void testEqualsDifferentAdminIDsInDTOs() {
        OutputFamilyDTO outputFamilyDTOOne = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        OutputFamilyDTO outputFamilyDTOTwo = new OutputFamilyDTO("Silva", "@silva@gmail.com", "admin@gmail.com", "12/12/1990");

        Assertions.assertNotEquals(outputFamilyDTOOne, outputFamilyDTOTwo);
    }

    @Test
    void testEqualsDifferentRegistrationDatesInDTOs() {
        OutputFamilyDTO outputFamilyDTOOne = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        OutputFamilyDTO outputFamilyDTOTwo = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/2/2015");

        Assertions.assertNotEquals(outputFamilyDTOOne, outputFamilyDTOTwo);
    }

    @Test
    void testEqualsDifferentLinksInDTOs() {
        OutputFamilyDTO outputFamilyDTOOne = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        OutputFamilyDTO outputFamilyDTOTwo = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/2/2015");
        Link link = linkTo(FamilyRESTController.class).withSelfRel();

        outputFamilyDTOOne.add(link);


        Assertions.assertNotEquals(outputFamilyDTOOne, outputFamilyDTOTwo);
    }

    @Test
    @DisplayName("Should return true if two identical OutputFamilyDTO objects are compared using their hash code")
    void testHashCode() {
        OutputFamilyDTO outputFamilyDTO1 = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        OutputFamilyDTO outputFamilyDTO2 = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");

        Assertions.assertEquals(outputFamilyDTO1.hashCode(), outputFamilyDTO2.hashCode());
        Assertions.assertNotSame(outputFamilyDTO1, outputFamilyDTO2);
    }

    @Test
    @DisplayName("Should return false if two different OutputFamilyDTO objects are compared using their hash code")
    void testHashCodeFail() {
        OutputFamilyDTO outputFamilyDTO1 = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        OutputFamilyDTO outputFamilyDTO2 = new OutputFamilyDTO("Silvas", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");

        Assertions.assertNotEquals(outputFamilyDTO1.hashCode(), outputFamilyDTO2.hashCode());
        Assertions.assertNotSame(outputFamilyDTO1, outputFamilyDTO2);
    }

    @Test
    void equalsSameObject() {
        OutputFamilyDTO outputFamilyDTO1 = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");

        assertEquals(outputFamilyDTO1, outputFamilyDTO1);
    }

    @Test
    void equalsWithNull() {
        OutputFamilyDTO outputFamilyDTO1 = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        OutputFamilyDTO outputFamilyDTO2 = null;

        assertNotEquals(outputFamilyDTO1, outputFamilyDTO2);
    }

    @Test
    void equalsDifferentClass() {
        OutputFamilyDTO outputFamilyDTO1 = new OutputFamilyDTO("Silva", "@silva@gmail.com", "silva@gmail.com", "12/12/1990");
        String outputFamilyDTO2 = "Silva";

        assertNotEquals(outputFamilyDTO1, outputFamilyDTO2);
    }

    @Test
    void noArgsConstructorTest() {
        OutputFamilyDTO outputFamilyDTO = new OutputFamilyDTO();

        assertNotNull(outputFamilyDTO);
    }


}