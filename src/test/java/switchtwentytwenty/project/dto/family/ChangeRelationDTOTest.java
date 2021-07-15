package switchtwentytwenty.project.dto.family;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangeRelationDTOTest {

    @Test
    void testEquals() {
        ChangeRelationDTO changeRelationDTO1 = new ChangeRelationDTO("Son");
        ChangeRelationDTO changeRelationDTO2 = new ChangeRelationDTO("Son");

        assertEquals(changeRelationDTO1, changeRelationDTO2);
    }


    @Test
    void testEqualsFalseDifferentnewDesignation() {
        ChangeRelationDTO changeRelationDTO1 = new ChangeRelationDTO( "Son");
        ChangeRelationDTO changeRelationDTO2 = new ChangeRelationDTO( "Father");

        assertNotEquals(changeRelationDTO1, changeRelationDTO2);
    }

    @Test
    void testEqualsFalseDifferentObjectTypes() {
        ChangeRelationDTO changeRelationDTO1 = new ChangeRelationDTO( "Son");
        String changeRelationDTO2 = "a String";
        assertNotEquals(changeRelationDTO1, changeRelationDTO2);
    }

    @Test
    void testEqualsSameObject() {
        ChangeRelationDTO changeRelationDTO1 = new ChangeRelationDTO( "Son");
        ChangeRelationDTO changeRelationDTO2 = changeRelationDTO1;
        assertEquals(changeRelationDTO1, changeRelationDTO2);
    }

    @Test
    void testEqualsFalseNull() {
        ChangeRelationDTO changeRelationDTO1 = new ChangeRelationDTO( "Son");
        ChangeRelationDTO changeRelationDTO2 = null;

        assertNotEquals(changeRelationDTO1, changeRelationDTO2);
    }

    @Test
    void testHashCode() {
        ChangeRelationDTO changeRelationDTO1 = new ChangeRelationDTO( "Son");
        ChangeRelationDTO changeRelationDTO2 = new ChangeRelationDTO( "Son");

        assertEquals(changeRelationDTO1.hashCode(), changeRelationDTO2.hashCode());
        assertNotEquals(0, changeRelationDTO1.hashCode());
    }


    @Test
    void setNewRelationDesignation() {
        ChangeRelationDTO changeRelationDTO1 = new ChangeRelationDTO( "Son");
        changeRelationDTO1.setNewRelationDesignation("New");
        String expected = "New";
        String result = changeRelationDTO1.getNewRelationDesignation();
        assertEquals(expected, result);
    }



    @Test
    void getNewRelationDesignation() {
        ChangeRelationDTO changeRelationDTO1 = new ChangeRelationDTO( "Son");
        String expected = "Son";
        String result = changeRelationDTO1.getNewRelationDesignation();
        assertEquals(expected, result);
    }

    @Test
    void noArgsConstructorTest() {
        ChangeRelationDTO emptyChangeRelationDTO = new ChangeRelationDTO();
        assertNotNull(emptyChangeRelationDTO);
    }
}