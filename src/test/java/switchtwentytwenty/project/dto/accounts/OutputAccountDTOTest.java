package switchtwentytwenty.project.dto.accounts;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.AccountRESTController;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


class OutputAccountDTOTest {
    String ACCOUNTID = "3";
    String PERSONOWNERID = "tonyZe@latinlover.com";
    String DESIGNATION = "Noitadas";

    @Test
    void getAccountIDTest() {
        OutputAccountDTO outputAccountDTOOne = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, DESIGNATION);

        String expected = "3";

        String result = outputAccountDTOOne.getAccountID();

        assertEquals(expected, result);
    }
    @Test
    void getOwnerIDTest() {
        OutputAccountDTO outputAccountDTOOne = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, DESIGNATION);

        String expected = "tonyZe@latinlover.com";

        String result = outputAccountDTOOne.getOwnerID();

        assertEquals(expected, result);
    }
    @Test
    void getDesignationTest() {
        OutputAccountDTO outputAccountDTOOne = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, DESIGNATION);

        String expected = "Noitadas";

        String result = outputAccountDTOOne.getDesignation();

        assertEquals(expected, result);
    }
    @Test
    void setAccountIDTest(){
        OutputAccountDTO outputAccountDTOOne = new OutputAccountDTO();
        outputAccountDTOOne.setAccountID(ACCOUNTID);

        String expected = "3";

        String result = outputAccountDTOOne.getAccountID();

        assertEquals(expected, result);
    }
    @Test
    void setOwnerIDTest(){
        OutputAccountDTO outputAccountDTOOne = new OutputAccountDTO();
        outputAccountDTOOne.setOwnerID(PERSONOWNERID);

        String expected = "tonyZe@latinlover.com";

        String result = outputAccountDTOOne.getOwnerID();

        assertEquals(expected, result);
    }
    @Test
    void setDesignationTest(){
        OutputAccountDTO outputAccountDTOOne = new OutputAccountDTO();
        outputAccountDTOOne.setDesignation(DESIGNATION);

        String expected = "Noitadas";

        String result = outputAccountDTOOne.getDesignation();

        assertEquals(expected, result);
    }
    @Test
    void equalsTest() {
        OutputAccountDTO outputAccountDTOOne = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, DESIGNATION);
        OutputAccountDTO outputAccountDTOTwo = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, DESIGNATION);

        assertEquals(outputAccountDTOOne, outputAccountDTOTwo);
        assertNotSame(outputAccountDTOOne, outputAccountDTOTwo);
    }
    @Test
    void equalsIsDifferentObjectTest() {
        OutputAccountDTO outputAccountDTOOne = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, DESIGNATION);
        OutputAccountDTO outputAccountDTOTwo = new OutputAccountDTO("not original accountID", "not original ownerID", "not original designation");

        assertNotEquals(outputAccountDTOOne, outputAccountDTOTwo);
    }

    @Test
    void equalsAccountIDIsDifferentAccountID() {
        OutputAccountDTO outputAccountDTOOne = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, DESIGNATION);
        OutputAccountDTO outputAccountDTOTwo = new OutputAccountDTO("not original accountID", PERSONOWNERID, DESIGNATION);

        assertNotEquals(outputAccountDTOOne, outputAccountDTOTwo);
    }
    @Test
    void equalsAccountIDIsDifferentOwnerID() {
        OutputAccountDTO outputAccountDTOOne = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, DESIGNATION);
        OutputAccountDTO outputAccountDTOTwo = new OutputAccountDTO(ACCOUNTID, "not original email", DESIGNATION);

        assertNotEquals(outputAccountDTOOne, outputAccountDTOTwo);
    }
    @Test
    void equalsAccountIDIsDifferentDesignation() {
        OutputAccountDTO outputAccountDTOOne = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, DESIGNATION);
        OutputAccountDTO outputAccountDTOTwo = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, "not original designation");

        assertNotEquals(outputAccountDTOOne, outputAccountDTOTwo);
    }

    @Test
    void equalsAccountIDIsDifferentLink() {
        OutputAccountDTO outputAccountDTOOne = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, DESIGNATION);
        OutputAccountDTO outputAccountDTOTwo = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, DESIGNATION);
        Link link = linkTo(AccountRESTController.class).withSelfRel();

        outputAccountDTOTwo.add(link);

        assertNotEquals(outputAccountDTOOne, outputAccountDTOTwo);
    }

    @Test
    void equalsIsDifferentClassTest() {
        OutputAccountDTO outputAccountDTOOne = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, DESIGNATION);
        String notOutputAccountDTO = "I'm not an OutputAccountDTO object";

        assertNotEquals(outputAccountDTOOne, notOutputAccountDTO);
    }
    @Test
    void equalsIsSameObjectTest() {
        OutputAccountDTO outputAccountDTOOne = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, DESIGNATION);
        OutputAccountDTO outputAccountDTOTwo = outputAccountDTOOne;

        assertEquals(outputAccountDTOOne, outputAccountDTOOne);
    }
    @Test
    void hashCodeIsEqualTest() {
        OutputAccountDTO outputAccountDTOOne = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, DESIGNATION);
        OutputAccountDTO outputAccountDTOTwo = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, DESIGNATION);

        assertEquals(outputAccountDTOOne.hashCode(), outputAccountDTOTwo.hashCode());
    }
    @Test
    void hashCodeIsDifferentTest() {
        OutputAccountDTO outputAccountDTOOne = new OutputAccountDTO(ACCOUNTID, PERSONOWNERID, DESIGNATION);
        OutputAccountDTO outputAccountDTOTwo = new OutputAccountDTO("5", PERSONOWNERID, DESIGNATION);

        assertNotEquals(outputAccountDTOOne.hashCode(), outputAccountDTOTwo.hashCode());
    }

}