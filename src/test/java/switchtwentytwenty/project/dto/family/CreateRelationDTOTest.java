package switchtwentytwenty.project.dto.family;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateRelationDTOTest {

    @Test
    void getMemberOneIDMustNotBeNull() {
        CreateRelationDTO createRelationDTO = new CreateRelationDTO("tonyze", "katia", "BFF");
        String expected = "tonyze";
        String result = createRelationDTO.getMemberOneID();
        assertEquals(expected, result);
        assertNotNull(result);
    }

    @Test
    void getMemberTwoID() {
        CreateRelationDTO createRelationDTO = new CreateRelationDTO("tonyze", "katia", "BFF");
        String expected = "katia";
        String result = createRelationDTO.getMemberTwoID();
        assertEquals(expected, result);
        assertNotNull(result);
    }

    @Test
    void getRelationDesignation() {
        CreateRelationDTO createRelationDTO = new CreateRelationDTO("tonyze", "katia", "BFF");
        String expected = "BFF";
        String result = createRelationDTO.getRelationDesignation();
        assertEquals(expected, result);
        assertNotNull(result);
    }

    @Test
    void noArgsConstructorSuccess(){
        CreateRelationDTO noArgsRelationDTO = new CreateRelationDTO();
        noArgsRelationDTO.setMemberOneID("tonyze");
        noArgsRelationDTO.setMemberTwoID("katia");
        noArgsRelationDTO.setRelationDesignation("BFF");

        assertNotNull(noArgsRelationDTO);
    }
}