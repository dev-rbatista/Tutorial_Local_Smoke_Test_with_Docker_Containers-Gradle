package switchtwentytwenty.project.datamodel.domainjpa;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AddressJPATest {

    String street = "Rua";
    String city = "Covilândia";
    String zip = "6215-000";
    String doorNumber = "1";
    Long addressID = 3L;
    Long otherAddressID = 6L;

    String id = "emaiil@email.com";
    PersonIDJPA personIDJPA = new PersonIDJPA(id);

    String name = "TonyZe";
    String birthdate = "23/12/1992";
    Integer vat = 999999999;

    FamilyIDJPA familyIDJPA = new FamilyIDJPA(UUID.randomUUID().toString());
    PersonJPA personJPA = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);


    @Test
    @Tag("US010")
    void getId() {
        Long expected = Long.valueOf(1);

        AddressJPA addressJPA = new AddressJPA(null,street, city, zip, doorNumber, personJPA);
        addressJPA.setId(1L);
        Long result = addressJPA.getId();

        assertEquals(expected, result);
        assertNotEquals(0L, result);
    }

    @Test
    @Tag("US010")
    void getStreet() {
        String expected = "Rua";

        AddressJPA addressJPA = new AddressJPA(null,street, city, zip, doorNumber, personJPA);

        String result = addressJPA.getStreet();

        assertEquals(expected, result);
    }

    @Test
    @Tag("US010")
    void getCity() {
        String expected = "Covilândia";

        AddressJPA addressJPA = new AddressJPA(null,street, city, zip, doorNumber, personJPA);

        String result = addressJPA.getCity();

        assertEquals(expected, result);
    }

    @Test
    @Tag("US010")
    void getZipCode() {
        String expected = "6215-000";

        AddressJPA addressJPA = new AddressJPA(null,street, city, zip, doorNumber, personJPA);

        String result = addressJPA.getZipCode();

        assertEquals(expected, result);
    }

    @Test
    @Tag("US010")
    void getDoorNumber() {
        String expected = "1";

        AddressJPA addressJPA = new AddressJPA(null,street, city, zip, doorNumber, personJPA);

        String result = addressJPA.getDoorNumber();

        assertEquals(expected, result);
    }

    @Test
    @Tag("US010")
    void getPerson() {
        String id = "emaiil@email.com";
        PersonIDJPA personIDJPA = new PersonIDJPA(id);

        String name = "TonyZe";
        String birthdate = "23/12/1992";
        Integer vat = 999999999;

        PersonJPA expected = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);

        AddressJPA addressJPA = new AddressJPA(null,street, city, zip, doorNumber, personJPA);

        PersonJPA result = addressJPA.getPerson();

        assertEquals(expected, result);
    }


    @Test
    @Tag("US010")
    void testEqualsSameAddressJPA() {
        AddressJPA addressJPAOne = new AddressJPA(null,street, city, zip, doorNumber, personJPA);
        AddressJPA addressJPATwo = addressJPAOne;

        assertEquals(addressJPAOne, addressJPATwo);
    }

    @Test
    @Tag("US010")
    void testEqualsEqualAddressJPAs() {
        AddressJPA addressJPAOne = new AddressJPA(null,street, city, zip, doorNumber, personJPA);
        AddressJPA addressJPATwo = new AddressJPA(null,street, city, zip, doorNumber, personJPA);

        assertEquals(addressJPAOne, addressJPATwo);
        assertNotSame(addressJPAOne, addressJPATwo);
    }

    @Test
    @Tag("US010")
    void testEqualsDifferentStreetAddressJPAs() {
        AddressJPA addressJPAOne = new AddressJPA(null,street, city, zip, doorNumber, personJPA);
        String otherStreet = "Rua de Baixo";
        AddressJPA addressJPATwo = new AddressJPA(null,otherStreet, city, zip, doorNumber, personJPA);

        assertNotEquals(addressJPAOne, addressJPATwo);
    }

    @Test
    @Tag("US010")
    void testEqualsDifferentIDs() {
        AddressJPA addressJPAOne = new AddressJPA(addressID,street, city, zip, doorNumber, personJPA);
        AddressJPA addressJPATwo = new AddressJPA(otherAddressID,street, city, zip, doorNumber, personJPA);

        assertNotEquals(addressJPAOne, addressJPATwo);
    }

    @Test
    @Tag("US010")
    void testEqualsDifferentCityAddressJPAs() {
        AddressJPA addressJPAOne = new AddressJPA(null,street, city, zip, doorNumber, personJPA);
        String otherCity = "Vila Nova de Gaia";
        AddressJPA addressJPATwo = new AddressJPA(null,street, otherCity, zip, doorNumber, personJPA);

        assertNotEquals(addressJPAOne, addressJPATwo);
    }

    @Test
    @Tag("US010")
    void testEqualsDifferentZipCodeAddressJPAs() {
        AddressJPA addressJPAOne = new AddressJPA(null,street, city, zip, doorNumber, personJPA);
        String otherZip = "4130-155";
        AddressJPA addressJPATwo = new AddressJPA(null,street, city, otherZip, doorNumber, personJPA);

        assertNotEquals(addressJPAOne, addressJPATwo);
    }

    @Test
    @Tag("US010")
    void testEqualsDifferentDoorNumberAddressJPAs() {
        AddressJPA addressJPAOne = new AddressJPA(null,street, city, zip, doorNumber, personJPA);
        String otherDoorNumber = "44F";
        AddressJPA addressJPATwo = new AddressJPA(null,street, city, zip, otherDoorNumber, personJPA);

        assertNotEquals(addressJPAOne, addressJPATwo);
    }

    @Test
    @Tag("US010")
    void testEqualsDifferentObjects() {
        AddressJPA addressJPA = new AddressJPA(null,street, city, zip, doorNumber, personJPA);
        String notAddressJPA = "Rua de Baixo";

        assertNotEquals(addressJPA, notAddressJPA);
    }

    @Test
    @Tag("US010")
    void testEqualsDifferentFromNull() {
        AddressJPA addressJPA = new AddressJPA(null,street, city, zip, doorNumber, personJPA);
        String nullString = null;

        assertNotEquals(addressJPA, nullString);
    }

    @Test
    @Tag("US010")
    void hashCodeSameEqualObjects() {
        AddressJPA addressJPA = new AddressJPA(null,street, city, zip, doorNumber, personJPA);

        AddressJPA addressJPATwo = new AddressJPA(null,street, city, zip, doorNumber, personJPA);

        assertEquals(addressJPA.hashCode(), addressJPATwo.hashCode());
        assertNotSame(addressJPA, addressJPATwo);
    }

    @Test
    @Tag("US010")
    void hashCodeDifferentObjects() {
        AddressJPA addressJPA = new AddressJPA(null,street, city, zip, doorNumber, personJPA);

        AddressJPA addressJPATwo = new AddressJPA(null,"street", "city", "zip", "doorNumber", personJPA);

        assertNotEquals(addressJPA.hashCode(), addressJPATwo.hashCode());
    }


}