package switchtwentytwenty.project.dto.family;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutputPersonRelationDTOTest {

    String PERSONIDONE = "tonyZe@latinlover.com";
    String PERSONIDTWO = "mariaZe@latinlover.com";
    String DESIGNATION = "Amante";
    String RELATIONID = "123456";

    @Test
    void getRelationIDTest() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);

        String expected = "123456";

        String result = outputRelationDTOOne.getRelationID();

        assertEquals(expected, result);
    }

    @Test
    void getMemberIDOneTest() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);

        String expected = "tonyZe@latinlover.com";

        String result = outputRelationDTOOne.getMemberOneID();

        assertEquals(expected, result);
    }

    @Test
    void getMemberIDTwoTest() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);

        String expected = "mariaZe@latinlover.com";

        String result = outputRelationDTOOne.getMemberTwoID();

        assertEquals(expected, result);
    }

    @Test
    void getDesignationTest() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);

        String expected = "Amante";

        String result = outputRelationDTOOne.getRelationDesignation();

        assertEquals(expected, result);
    }

    @Test
    void setRelationIDTest() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO();
        outputRelationDTOOne.setRelationID(RELATIONID);

        String expected = "123456";

        String result = outputRelationDTOOne.getRelationID();

        assertEquals(expected, result);
    }

    @Test
    void setMemberIDOneTest() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO();
        outputRelationDTOOne.setMemberOneID(PERSONIDONE);

        String expected = "tonyZe@latinlover.com";

        String result = outputRelationDTOOne.getMemberOneID();

        assertEquals(expected, result);
    }

    @Test
    void setMemberIDTwoTest() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO();
        outputRelationDTOOne.setMemberTwoID(PERSONIDTWO);

        String expected = "mariaZe@latinlover.com";

        String result = outputRelationDTOOne.getMemberTwoID();

        assertEquals(expected, result);
    }

    @Test
    void setDesignationTest() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO();
        outputRelationDTOOne.setRelationDesignation(DESIGNATION);

        String expected = "Amante";

        String result = outputRelationDTOOne.getRelationDesignation();

        assertEquals(expected, result);
    }

    @Test
    void equalsTest() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputPersonRelationDTO outputRelationDTOTwo = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);

        assertEquals(outputRelationDTOOne, outputRelationDTOTwo);
        assertNotSame(outputRelationDTOOne, outputRelationDTOTwo);
    }

    @Test
    void equalsIsDifferentObjectTest() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputPersonRelationDTO outputRelationDTOTwo = new OutputPersonRelationDTO("not original memberIDOne", "not original memberIDTwo", "not original designation", "not original relation id");

        assertNotEquals(outputRelationDTOOne, outputRelationDTOTwo);
    }

    @Test
    void equalsRelationIsDifferentMemberIDOne() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputPersonRelationDTO outputRelationDTOTwo = new OutputPersonRelationDTO("not original memberIDOne", PERSONIDTWO, DESIGNATION, RELATIONID);

        assertNotEquals(outputRelationDTOOne, outputRelationDTOTwo);
    }

    @Test
    void equalsRelationIDIsDifferentMemberIDTwo() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputPersonRelationDTO outputRelationDTOTwo = new OutputPersonRelationDTO(PERSONIDONE, "not original memberIDTwo", DESIGNATION, RELATIONID);

        assertNotEquals(outputRelationDTOOne, outputRelationDTOTwo);
    }

    @Test
    void equalsRelationIDIsDifferentDesignation() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputPersonRelationDTO outputRelationDTOTwo = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, "not original designation", RELATIONID);

        assertNotEquals(outputRelationDTOOne, outputRelationDTOTwo);
    }

    @Test
    void equalsRelationIsDifferentRelationID() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputPersonRelationDTO outputRelationDTOTwo = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, "not original relationID");

        assertNotEquals(outputRelationDTOOne, outputRelationDTOTwo);
    }



    @Test
    void equalsIsDifferentClassTest() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        String notOutputRelationDTO = "I'm not an OutputPersonRelationDTO object";

        assertNotEquals(outputRelationDTOOne, notOutputRelationDTO);
    }

    @Test
    void equalsTestDifferentFromNull() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        String nullString = null;

        assertNotEquals(outputRelationDTOOne, nullString);
    }

    @Test
    void equalsIsSameObjectTest() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);

        assertEquals(outputRelationDTOOne, outputRelationDTOOne);
    }

    @Test
    void hashCodeIsEqualTest() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputPersonRelationDTO outputRelationDTOTwo = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);

        assertEquals(outputRelationDTOOne.hashCode(), outputRelationDTOTwo.hashCode());
    }

    @Test
    void hashCodeIsDifferentTest() {
        OutputPersonRelationDTO outputRelationDTOOne = new OutputPersonRelationDTO(PERSONIDONE, PERSONIDTWO, DESIGNATION, RELATIONID);
        OutputPersonRelationDTO outputRelationDTOTwo = new OutputPersonRelationDTO("7", PERSONIDTWO, DESIGNATION, RELATIONID);

        assertNotEquals(outputRelationDTOOne.hashCode(), outputRelationDTOTwo.hashCode());
    }

}