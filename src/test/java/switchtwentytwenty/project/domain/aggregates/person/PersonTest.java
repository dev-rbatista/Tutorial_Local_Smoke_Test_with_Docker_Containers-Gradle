package switchtwentytwenty.project.domain.aggregates.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.exceptions.EmailAlreadyRegisteredException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    final String VALIDNAME = "TonyZe";
    final String VALIDEMAIL = "tonyze@latinlover.pt";
    final int VALIDVATNUMBER = 999999999;
    final Integer VALIDPHONENUMBER = 916969696;
    final String VALIDSTREET = "Rua";
    final String VALIDCITY = "Ermesinde";
    final String VALIDZIPCODE = "4700-111";
    final String VALIDADDRESSNUMBER = "69";
    final String VALIDBIRTHDATE = "01/03/1990";
    Person validPerson;
    Person tonyZe;
    Name tonyZeName;
    BirthDate tonyZeBirthDate;
    VATNumber tonyZeVat;
    PhoneNumber tonyZePhone;
    Address tonyZeAddress;
    FamilyID familyID;

    @BeforeEach
    void createValidPerson() {
        Name tonyZeName = new Name(VALIDNAME);
        BirthDate tonyZeBirthDate = new BirthDate(VALIDBIRTHDATE);
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        VATNumber tonyZeVat = new VATNumber(VALIDVATNUMBER);
        PhoneNumber tonyZePhone = new PhoneNumber(VALIDPHONENUMBER);
        Address tonyZeAddress = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER);
        FamilyID familyID = new FamilyID(VALIDEMAIL);
        validPerson = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
    }

    @DisplayName("Check if method isSameEmail returns true when email is the same")
    @Test
    @Tag("US010")
    void shouldAssertTrueIsSameEmail() {
        PersonID test = new PersonID(VALIDEMAIL);
        assertTrue(validPerson.hasID(test));
    }

    @DisplayName("Check if method isSameEmail returns false when email is not the same")
    @Test
    @Tag("US010")
    void shouldAssertFalseIsNotSameEmail() {
        PersonID test = new PersonID("tonyze@latinhater.pt");
        assertFalse(validPerson.hasID(test));
    }

    @DisplayName("Valid Person Constructor Test")
    @Test
    @Tag("US010")
    void shouldNotThrowValidPersonConstructor() {
        assertNotNull(validPerson);
        Name tonyZeName = new Name(VALIDNAME);
        BirthDate tonyZeBirthDate = new BirthDate(VALIDBIRTHDATE);
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        VATNumber tonyZeVat = new VATNumber(VALIDVATNUMBER);
        PhoneNumber tonyZePhone = new PhoneNumber(VALIDPHONENUMBER);
        Address tonyZeAddress = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER);

        FamilyID familyID = new FamilyID(VALIDEMAIL);
        assertDoesNotThrow(() -> tonyZe = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID));
    }

    @Test
    void addEmailTestAlreadyRegisteredAsSecondaryShouldThrowException() {
        Name tonyZeName = new Name(VALIDNAME);
        BirthDate tonyZeBirthDate = new BirthDate(VALIDBIRTHDATE);
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        VATNumber tonyZeVat = new VATNumber(VALIDVATNUMBER);
        PhoneNumber tonyZePhone = new PhoneNumber(VALIDPHONENUMBER);
        Address tonyZeAddress = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER);
        tonyZe = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        String otherEmail = "otherEmail@gmail.com";
        EmailAddress emailToAdd = new EmailAddress(otherEmail);
        tonyZe.addEmail(emailToAdd);

        assertThrows(EmailAlreadyRegisteredException.class, () -> tonyZe.addEmail(emailToAdd));
    }

    @Test
    void addEmailTestShouldNotThrowException() {
        Name tonyZeName = new Name(VALIDNAME);
        BirthDate tonyZeBirthDate = new BirthDate(VALIDBIRTHDATE);
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        VATNumber tonyZeVat = new VATNumber(VALIDVATNUMBER);
        PhoneNumber tonyZePhone = new PhoneNumber(VALIDPHONENUMBER);
        Address tonyZeAddress = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER);
        tonyZe = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        String otherEmail = "otherEmail@gmail.com";
        EmailAddress emailToAdd = new EmailAddress(otherEmail);

        assertDoesNotThrow(() -> tonyZe.addEmail(emailToAdd));
    }

    @Test
    void addEmailTestAlreadyRegisteredAsPrimaryShouldThrowException() {
        Name tonyZeName = new Name(VALIDNAME);
        BirthDate tonyZeBirthDate = new BirthDate(VALIDBIRTHDATE);
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        VATNumber tonyZeVat = new VATNumber(VALIDVATNUMBER);
        PhoneNumber tonyZePhone = new PhoneNumber(VALIDPHONENUMBER);
        Address tonyZeAddress = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER);
        tonyZe = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        EmailAddress emailToAdd = new EmailAddress(VALIDEMAIL);

        assertThrows(EmailAlreadyRegisteredException.class, () -> tonyZe.addEmail(emailToAdd));
    }

    @Test
    void addEmailTestAddingSecondEmail() {
        Name tonyZeName = new Name(VALIDNAME);
        BirthDate tonyZeBirthDate = new BirthDate(VALIDBIRTHDATE);
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        VATNumber tonyZeVat = new VATNumber(VALIDVATNUMBER);
        PhoneNumber tonyZePhone = new PhoneNumber(VALIDPHONENUMBER);
        Address tonyZeAddress = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER);
        tonyZe = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        String otherEmailOne = "otherEmail@gmail.com";
        EmailAddress emailToAddOne = new EmailAddress(otherEmailOne);
        tonyZe.addEmail(emailToAddOne);
        String otherEmailTwo = "ThirdEmail@gmail.com";
        EmailAddress emailToAddTwo = new EmailAddress(otherEmailTwo);

        assertDoesNotThrow(() -> tonyZe.addEmail(emailToAddTwo));
    }

    @Test
    @Tag("US010")
    void shouldNotThrowValidPersonConstructorWithoutPhoneNumber() {
        Name tonyZeName = new Name(VALIDNAME);
        BirthDate tonyZeBirthDate = new BirthDate(VALIDBIRTHDATE);
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        VATNumber tonyZeVat = new VATNumber(VALIDVATNUMBER);
        Address tonyZeAddress = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER);

        FamilyID familyID = new FamilyID(VALIDEMAIL);

        tonyZe = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, null, tonyZeAddress, familyID);
        assertNotNull(tonyZe);
    }

    @Test
    @Tag("US010")
    void equalsTestSamePerson() {
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        Person personOne = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        Person personTwo = personOne;

        assertEquals(personOne, personTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestEqualPersons() {
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        Person personOne = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        Person personTwo = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);

        assertEquals(personOne, personTwo);
        assertNotSame(personOne, personTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentPersons() {
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        String otherValidEmail = "Jessica@gmail.com";
        PersonID otherEmail = new PersonID(otherValidEmail);
        Person personOne = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        Person personTwo = new Person(tonyZeName, tonyZeBirthDate, otherEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);

        assertNotEquals(personOne, personTwo);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentObjects() {
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        Person person = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        String notAPerson = "Jessica@gmail.com";

        assertNotEquals(person, notAPerson);
    }

    @Test
    @Tag("US010")
    void equalsTestDifferentFromNull() {
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        Person person = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        String nullString = null;

        assertNotEquals(person, nullString);
    }

    @Test
    @Tag("US010")
    void hashCodeTestSameHashCode() {
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        Person personOne = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        Person personTwo = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);

        assertEquals(personOne.hashCode(), personTwo.hashCode());
        assertNotSame(personOne, personTwo);
    }

    @Test
    @Tag("US010")
    void hashCodeTestDifferentHashCodes() {
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        String otherValidEmail = "Jessica@gmail.com";
        PersonID otherEmail = new PersonID(otherValidEmail);
        Person personOne = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        Person personTwo = new Person(tonyZeName, tonyZeBirthDate, otherEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);

        assertNotEquals(personOne.hashCode(), personTwo.hashCode());
    }

    @Test
    void constructorTestPersonAllArgumentConstructor() {
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        Person person = new Person(tonyZeEmail, tonyZeName, tonyZeBirthDate, null, tonyZeVat, null, tonyZeAddress, familyID);

        assertNotNull(person);
    }

    @Test
    void setAddressTest() {
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        Person personOne = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        Address otherAddress = new Address("otherStreet", "otherCity", "1111-111", "12RC");
        personOne.setAddress(otherAddress);

        Address result = personOne.getAddress();

        assertEquals(otherAddress, result);
    }

    @Test
    void constructorTest() {
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        Person result = new Person(tonyZeEmail, tonyZeName, tonyZeBirthDate, tonyZeVat, familyID);

        assertNotNull(result);
    }

    @Test
    void removeEmailTestingEmailToBeRemovedNotOnListExpectingSameList() {
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        Person person = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        person.addEmail(new EmailAddress("newemail@email.com"));
        person.addEmail(new EmailAddress("senhortony@boytoy.com"));
        person.addEmail(new EmailAddress("vaiserparaapagar@jafoste.com"));

        List<EmailAddress> expected = person.getEmails();

        person.removeEmail(new EmailAddress("estenaoestanalista@tony.pt"));
        List<EmailAddress> result = person.getEmails();

        assertEquals(expected, result);

    }

    @Test
    void removeEmailRemovingOneEmailExpectingDifferentList() {
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        Person person = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        person.addEmail(new EmailAddress("newemail@email.com"));
        person.addEmail(new EmailAddress("senhortony@boytoy.com"));
        EmailAddress emailToDelete = new EmailAddress("vaiserparaapagar@jafoste.com");
        person.addEmail(emailToDelete);

        List<EmailAddress> expected = person.getEmails();

        person.removeEmail(emailToDelete);
        List<EmailAddress> result = person.getEmails();

        assertNotEquals(expected, result);

    }
}