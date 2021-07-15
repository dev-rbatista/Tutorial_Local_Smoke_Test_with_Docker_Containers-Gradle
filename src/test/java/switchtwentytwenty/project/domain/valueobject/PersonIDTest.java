package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonIDTest {

    @Test
    void constructorTest(){
        PersonID id = new PersonID("admin@gmail.com");

        assertNotNull(id);
    }

    @Test
    void constructorTestNoArgumentConstructor(){
        PersonID id = new PersonID();

        assertNotNull(id);
    }

    @Test
    void equalsTestEqualPersonID() {
        PersonID idOne = new PersonID("admin@gmail.com");
        PersonID idTwo = new PersonID("admin@gmail.com");

        assertEquals(idOne, idTwo);
    }

    @Test
    void equalsTestSamePersonID() {
        PersonID idOne = new PersonID("admin@gmail.com");
        PersonID idTwo = idOne;

        assertEquals(idOne, idTwo);
    }

    @Test
    void equalsTestDifferentPersonID() {
        PersonID idOne = new PersonID("admin@gmail.com");
        PersonID idTwo = new PersonID("otheremail@gmail.com");

        assertNotEquals(idOne, idTwo);
    }

    @Test
    void equalsTestDifferentObject() {
        PersonID id = new PersonID("admin@gmail.com");
        String notID = "notFamilyName";

        assertNotEquals(id, notID);
    }

    @Test
    void equalsTestDifferentFromNull() {
        PersonID id = new PersonID("admin@gmail.com");
        String nullString = null;

        assertNotEquals(id, nullString);
    }

    @Test
    void hashCodeTestSameHashCode() {
        PersonID idOne = new PersonID("admin@gmail.com");
        PersonID idTwo = new PersonID("admin@gmail.com");

        assertEquals(idOne.hashCode(), idTwo.hashCode());
    }

    @Test
    void hashCodeTestDifferentHashCode() {
        PersonID idOne = new PersonID("admin@gmail.com");
        PersonID idTwo = new PersonID("otheremail@gmail.com");

        assertNotEquals(idOne.hashCode(), idTwo.hashCode());
    }

}