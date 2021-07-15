package switchtwentytwenty.project.datamodel.domainjpa;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberJPATest {

    int phoneNumber = 963369963;
    Long phoneNumberID = 3L;
    Long otherPhoneNumberID = 6L;

    String id = "email@email.com";
    PersonIDJPA personIDJPA = new PersonIDJPA(id);

    String name = "TonyZe";
    String birthdate = "23/12/1992";
    Integer vat = 999999999;

    FamilyIDJPA familyIDJPA = new FamilyIDJPA(UUID.randomUUID().toString());
    PersonJPA personJPA = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);

    @Test
    @Tag("US010")
    void getIdTest() {
        Long expected = Long.valueOf(1);

        PhoneNumberJPA phoneNumberJPA = new PhoneNumberJPA(1L, phoneNumber, personJPA);

        Long result = phoneNumberJPA.getId();

        assertEquals(expected, result);
        assertNotEquals(0, result);
    }

    @Test
    @Tag("US010")
    void getPhoneNumberTest() {
        int expected = 963369963;

        PhoneNumberJPA phoneNumberJPA = new PhoneNumberJPA(null, phoneNumber, personJPA);

        int result = phoneNumberJPA.getNumber();

        assertEquals(expected, result);
    }

    @Test
    @Tag("US010")
    void getPersonJPATest() {
        PersonJPA expected = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);

        PhoneNumberJPA phoneNumberJPA = new PhoneNumberJPA(null, phoneNumber, personJPA);

        PersonJPA result = phoneNumberJPA.getPerson();

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }

    @Test
    @Tag("US010")
    void testEqualsSameObject() {
        PhoneNumberJPA phoneNumberJPAOne = new PhoneNumberJPA(null, phoneNumber, personJPA);
        PhoneNumberJPA phoneNumberJPATwo = phoneNumberJPAOne;

        assertEquals(phoneNumberJPAOne, phoneNumberJPATwo);
    }

    @Test
    @Tag("US010")
    void testEqualsNotSameObject() {
        PhoneNumberJPA phoneNumberJPAOne = new PhoneNumberJPA(null, phoneNumber, personJPA);
        PhoneNumberJPA phoneNumberJPATwo = new PhoneNumberJPA(null, phoneNumber, personJPA);

        assertEquals(phoneNumberJPAOne, phoneNumberJPATwo);
        assertNotSame(phoneNumberJPAOne, phoneNumberJPATwo);
    }

    @Test
    @Tag("US010")
    void testDifferentObjectWithIDs() {
        PhoneNumberJPA phoneNumberJPAOne = new PhoneNumberJPA(phoneNumberID, phoneNumber, personJPA);
        PhoneNumberJPA phoneNumberJPATwo = new PhoneNumberJPA(otherPhoneNumberID, phoneNumber, personJPA);

        assertNotEquals(phoneNumberJPAOne, phoneNumberJPATwo);
        assertNotSame(phoneNumberJPAOne, phoneNumberJPATwo);
    }

    @Test
    @Tag("US010")
    void testEqualsDifferentTypeOfObject() {
        PhoneNumberJPA phoneNumberJPAOne = new PhoneNumberJPA(null, phoneNumber, personJPA);

        assertNotEquals(phoneNumberJPAOne, personJPA);
    }

    @Test
    @Tag("US010")
    void testEqualsDifferentFromNull() {
        PhoneNumberJPA phoneNumberJPAOne = new PhoneNumberJPA(null, phoneNumber, personJPA);
        String nullString = null;

        assertNotEquals(phoneNumberJPAOne, nullString);
    }


    @Test
    @Tag("US010")
    void testEqualsNotEqual() {
        PhoneNumberJPA phoneNumberJPAOne = new PhoneNumberJPA(null, phoneNumber, personJPA);
        int otherPhoneNumber = 147741147;
        PhoneNumberJPA phoneNumberJPATwo = new PhoneNumberJPA(null, otherPhoneNumber, personJPA);

        assertNotEquals(phoneNumberJPAOne, phoneNumberJPATwo);
    }


    @Test
    @Tag("US010")
    void testHashCodeEqualObjects() {
        PhoneNumberJPA phoneNumberJPAOne = new PhoneNumberJPA(null, phoneNumber, personJPA);
        PhoneNumberJPA phoneNumberJPATwo = new PhoneNumberJPA(null, phoneNumber, personJPA);

        assertEquals(phoneNumberJPAOne.hashCode(), phoneNumberJPATwo.hashCode());
        assertNotSame(phoneNumberJPAOne, phoneNumberJPATwo);
    }

    @Test
    @Tag("US010")
    void testHashCodeDifferentObjects() {
        PhoneNumberJPA phoneNumberJPAOne = new PhoneNumberJPA(null, phoneNumber, personJPA);
        int otherPhoneNumber = 147741147;
        PhoneNumberJPA phoneNumberJPATwo = new PhoneNumberJPA(null, otherPhoneNumber, personJPA);


        assertNotEquals(phoneNumberJPAOne.hashCode(), phoneNumberJPATwo.hashCode());
    }

}