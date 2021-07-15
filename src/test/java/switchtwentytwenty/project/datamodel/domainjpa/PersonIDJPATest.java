package switchtwentytwenty.project.datamodel.domainjpa;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonIDJPATest {

    String personID = "email@email.com";

    @Tag("US010")
    @Test
    void getPersonId() {
        String expected = "email@email.com";

        PersonIDJPA personIDJPA = new PersonIDJPA(personID);

        String result = personIDJPA.toString();

        assertEquals(expected, result);
    }

    @Tag("US010")
    @Test
    void equalsTestSamePersonIDJPA() {
        PersonIDJPA personIDJPAOne = new PersonIDJPA(personID);
        PersonIDJPA personIDJPATwo = personIDJPAOne;

        assertEquals(personIDJPAOne, personIDJPATwo);
    }

    @Tag("US010")
    @Test
    void equalsTestEqualPersonIDJPAs() {
        PersonIDJPA personIDJPAOne = new PersonIDJPA(personID);
        PersonIDJPA personIDJPATwo = new PersonIDJPA(personID);

        assertEquals(personIDJPAOne, personIDJPATwo);
        assertNotSame(personIDJPAOne, personIDJPATwo);
    }

    @Tag("US010")
    @Test
    void equalsTestDifferentPersonIDJPAs() {
        PersonIDJPA personIDJPAOne = new PersonIDJPA(personID);
        PersonIDJPA personIDJPATwo = new PersonIDJPA("test@gmail.com");

        assertNotEquals(personIDJPAOne, personIDJPATwo);
    }

    @Tag("US010")
    @Test
    void equalsTestDifferentObjects() {
        PersonIDJPA personIDJPA = new PersonIDJPA(personID);

        assertNotEquals(personIDJPA, personID);
    }

    @Tag("US010")
    @Test
    void equalsTestDifferentFromNull() {
        PersonIDJPA personIDJPA = new PersonIDJPA(personID);
        String nullString = null;

        assertNotEquals(personIDJPA, nullString);
    }

    @Test
    void hashCodeSameHashCode(){
        PersonIDJPA personIDJPAOne = new PersonIDJPA(personID);
        PersonIDJPA personIDJPATwo = new PersonIDJPA(personID);

        assertEquals(personIDJPAOne.hashCode(), personIDJPATwo.hashCode());
        assertNotSame(personIDJPAOne, personIDJPATwo);
    }

    @Test
    void hashCodeDifferentHashCode(){
        PersonIDJPA personIDJPAOne = new PersonIDJPA(personID);
        PersonIDJPA personIDJPATwo = new PersonIDJPA("test@gmail.com");

        assertNotEquals(personIDJPAOne.hashCode(), personIDJPATwo.hashCode());
    }

}