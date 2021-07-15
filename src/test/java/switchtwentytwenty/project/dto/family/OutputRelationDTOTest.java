package switchtwentytwenty.project.dto.family;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.FamilyRESTController;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

class OutputRelationDTOTest {
    String PERSONIDONE = "tonyZe@latinlover.com";
    String PERSONIDTWO = "mariaZe@latinlover.com";
    String DESIGNATION = "Amante";
    String RELATIONID = "123456";

    @Test
    void getRelationIDTest() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);

        String expected = "123456";

        String result = outputRelationDTOOne.getRelationID();

        assertEquals(expected, result);
    }

    @Test
    void getMemberIDOneTest() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);

        String expected = "tonyZe@latinlover.com";

        String result = outputRelationDTOOne.getMemberOneID();

        assertEquals(expected, result);
    }

    @Test
    void getMemberIDTwoTest() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);

        String expected = "mariaZe@latinlover.com";

        String result = outputRelationDTOOne.getMemberTwoID();

        assertEquals(expected, result);
    }

    @Test
    void getDesignationTest() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);

        String expected = "Amante";

        String result = outputRelationDTOOne.getRelationDesignation();

        assertEquals(expected, result);
    }

    @Test
    void setRelationIDTest() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO();
        outputRelationDTOOne.setRelationID(RELATIONID);

        String expected = "123456";

        String result = outputRelationDTOOne.getRelationID();

        assertEquals(expected, result);
    }

    @Test
    void setMemberIDOneTest() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO();
        outputRelationDTOOne.setMemberOneID(PERSONIDONE);

        String expected = "tonyZe@latinlover.com";

        String result = outputRelationDTOOne.getMemberOneID();

        assertEquals(expected, result);
    }

    @Test
    void setMemberIDTwoTest() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO();
        outputRelationDTOOne.setMemberTwoID(PERSONIDTWO);

        String expected = "mariaZe@latinlover.com";

        String result = outputRelationDTOOne.getMemberTwoID();

        assertEquals(expected, result);
    }

    @Test
    void setDesignationTest() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO();
        outputRelationDTOOne.setRelationDesignation(DESIGNATION);

        String expected = "Amante";

        String result = outputRelationDTOOne.getRelationDesignation();

        assertEquals(expected, result);
    }

    @Test
    void equalsTest() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputRelationDTO outputRelationDTOTwo = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);

        assertEquals(outputRelationDTOOne, outputRelationDTOTwo);
        assertNotSame(outputRelationDTOOne, outputRelationDTOTwo);
    }

    @Test
    void equalsIsDifferentObjectTest() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputRelationDTO outputRelationDTOTwo = new OutputRelationDTO("not original memberIDOne", "not original memberIDTwo", "not original designation", "not original relation id");

        assertNotEquals(outputRelationDTOOne, outputRelationDTOTwo);
    }

    @Test
    void equalsRelationIsDifferentMemberIDOne() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputRelationDTO outputRelationDTOTwo = new OutputRelationDTO("not original memberIDOne", PERSONIDTWO, DESIGNATION, RELATIONID);

        assertNotEquals(outputRelationDTOOne, outputRelationDTOTwo);
    }

    @Test
    void equalsRelationIDIsDifferentMemberIDTwo() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputRelationDTO outputRelationDTOTwo = new OutputRelationDTO(PERSONIDONE, "not original memberIDTwo", DESIGNATION, RELATIONID);

        assertNotEquals(outputRelationDTOOne, outputRelationDTOTwo);
    }

    @Test
    void equalsRelationIDIsDifferentDesignation() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputRelationDTO outputRelationDTOTwo = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, "not original designation", RELATIONID);

        assertNotEquals(outputRelationDTOOne, outputRelationDTOTwo);
    }

    @Test
    void equalsRelationIsDifferentRelationID() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputRelationDTO outputRelationDTOTwo = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, "not original relationID");

        assertNotEquals(outputRelationDTOOne, outputRelationDTOTwo);
    }

    @Test
    void equalsRelationIDIsDifferentLink() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputRelationDTO outputRelationDTOTwo = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        Link link = linkTo(FamilyRESTController.class).withSelfRel();

        outputRelationDTOTwo.add(link);

        assertNotEquals(outputRelationDTOOne, outputRelationDTOTwo);
    }

    @Test
    void equalsIsDifferentClassTest() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        String notOutputRelationDTO = "I'm not an OutputRelationDTO object";

        assertNotEquals(outputRelationDTOOne, notOutputRelationDTO);
    }

    @Test
    void equalsTestDifferentFromNull() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        String nullString = null;

        assertNotEquals(outputRelationDTOOne, nullString);
    }

    @Test
    void equalsIsSameObjectTest() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);

        assertEquals(outputRelationDTOOne, outputRelationDTOOne);
    }

    @Test
    void hashCodeIsEqualTest() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputRelationDTO outputRelationDTOTwo = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);

        assertEquals(outputRelationDTOOne.hashCode(), outputRelationDTOTwo.hashCode());
    }

    @Test
    void hashCodeIsDifferentTest() {
        OutputRelationDTO outputRelationDTOOne = new OutputRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputRelationDTO outputRelationDTOTwo = new OutputRelationDTO("7", PERSONIDTWO, DESIGNATION, RELATIONID);

        assertNotEquals(outputRelationDTOOne.hashCode(), outputRelationDTOTwo.hashCode());
    }

}