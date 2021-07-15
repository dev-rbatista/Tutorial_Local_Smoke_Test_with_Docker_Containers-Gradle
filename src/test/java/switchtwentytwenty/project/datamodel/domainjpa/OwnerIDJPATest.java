package switchtwentytwenty.project.datamodel.domainjpa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerIDJPATest {
    String personOwnerID = "email@email.com";
    String familyOwnerID = "@tonyZe@lationlover.com";
    
    @Test
    void getOwnerID() {
        String expected = "email@email.com";

        OwnerIDJPA ownerIDJPA = new OwnerIDJPA(personOwnerID);

        String result = ownerIDJPA.toString();

        assertEquals(expected, result);
    }
    @Test
    void setOwnerID() {
        OwnerIDJPA ownerIDJPA = new OwnerIDJPA();
        ownerIDJPA.setOwnerID(personOwnerID);

        String expected = "email@email.com";
        String result = ownerIDJPA.getOwnerID();

        assertEquals(expected, result);
    }
    
    @Test
    void equalsTestSameOwnerIDJPA() {
        OwnerIDJPA ownerIDJPAOne = new OwnerIDJPA(personOwnerID);
        OwnerIDJPA ownerIDJPATwo = ownerIDJPAOne;

        assertEquals(ownerIDJPAOne, ownerIDJPATwo);
    }

    @Test
    void equalsTestEqualOwnerIDJPAs() {
        OwnerIDJPA ownerIDJPAOne = new OwnerIDJPA(personOwnerID);
        OwnerIDJPA ownerIDJPATwo = new OwnerIDJPA(personOwnerID);

        assertEquals(ownerIDJPAOne, ownerIDJPATwo);
        assertNotSame(ownerIDJPAOne, ownerIDJPATwo);
    }
    @Test
    void equalsTestEqualOwnerIDJPAsFromFamilyID() {
        OwnerIDJPA ownerIDJPAOne = new OwnerIDJPA(familyOwnerID);
        OwnerIDJPA ownerIDJPATwo = new OwnerIDJPA(familyOwnerID);

        assertEquals(ownerIDJPAOne, ownerIDJPATwo);
        assertNotSame(ownerIDJPAOne, ownerIDJPATwo);
    }


    @Test
    void equalsTestDifferentOwnerIDJPAs() {
        OwnerIDJPA ownerIDJPAOne = new OwnerIDJPA(personOwnerID);
        OwnerIDJPA ownerIDJPATwo = new OwnerIDJPA("test@gmail.com");

        assertNotEquals(ownerIDJPAOne, ownerIDJPATwo);
    }

    @Test
    void equalsTestDifferentObjects() {
        OwnerIDJPA ownerIDJPA = new OwnerIDJPA(personOwnerID);

        assertNotEquals(ownerIDJPA, personOwnerID);
    }

    @Test
    void equalsTestDifferentFromNull() {
        OwnerIDJPA ownerIDJPA = new OwnerIDJPA(personOwnerID);
        String nullString = null;

        assertNotEquals(ownerIDJPA, nullString);
    }

    @Test
    void hashCodeSameHashCode(){
        OwnerIDJPA ownerIDJPAOne = new OwnerIDJPA(personOwnerID);
        OwnerIDJPA ownerIDJPATwo = new OwnerIDJPA(personOwnerID);

        assertEquals(ownerIDJPAOne.hashCode(), ownerIDJPATwo.hashCode());
        assertNotSame(ownerIDJPAOne, ownerIDJPATwo);
    }

    @Test
    void hashCodeDifferentHashCode(){
        OwnerIDJPA ownerIDJPAOne = new OwnerIDJPA(personOwnerID);
        OwnerIDJPA ownerIDJPATwo = new OwnerIDJPA("test@gmail.com");

        assertNotEquals(ownerIDJPAOne.hashCode(), ownerIDJPATwo.hashCode());
    }

}