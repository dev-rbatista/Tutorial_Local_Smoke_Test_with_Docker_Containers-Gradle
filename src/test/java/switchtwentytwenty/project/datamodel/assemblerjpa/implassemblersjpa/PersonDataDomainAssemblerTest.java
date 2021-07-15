package switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.datamodel.domainjpa.*;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class PersonDataDomainAssemblerTest {

    final String VALIDNAME = "TonyZe";
    final String VALIDEMAIL = "tonyze@latinlover.pt";
    final int VALIDVATNUMBER = 999999999;
    final Integer VALIDPHONENUMBER = 916969696;
    final String VALIDSTREET = "Rua";
    final String VALIDCITY = "Ermesinde";
    final String VALIDZIPCODE = "4700-111";
    final String VALIDADDRESSNUMBER = "69";
    final String VALIDBIRTHDATE = "01/03/1990";

    final String ANOTHERVALIDEMAIL = "anotheremail@here.com";

    Name tonyZeName;
    BirthDate tonyZeBirthDate;
    PersonID tonyZeEmail;
    VATNumber tonyZeVat;
    PhoneNumber tonyZePhone;
    Address tonyZeAddress;
    FamilyID familyID;
    EmailAddress emailAddress;

    @Autowired
    PersonDataDomainAssembler personDataDomainAssembler;

    @BeforeEach
    void createValidPerson() {
        tonyZeName = new Name(VALIDNAME);
        tonyZeBirthDate = new BirthDate(VALIDBIRTHDATE);
        tonyZeEmail = new PersonID(VALIDEMAIL);
        tonyZeVat = new VATNumber(VALIDVATNUMBER);
        tonyZePhone = new PhoneNumber(VALIDPHONENUMBER);
        tonyZeAddress = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER);
        familyID = new FamilyID(VALIDEMAIL);

        emailAddress = new EmailAddress(ANOTHERVALIDEMAIL);
    }


    @Test
    void toDataComparingEquals() {
        Person person = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        person.addEmail(emailAddress);

        PersonJPA expected = new PersonJPA(new PersonIDJPA(VALIDEMAIL), VALIDNAME, VALIDBIRTHDATE, VALIDVATNUMBER, new FamilyIDJPA(VALIDEMAIL));

        PersonJPA result = personDataDomainAssembler.toData(person);


        assertEquals(expected, result);
    }

    @Test
    void toDataComparingByAttribute() {
        Person person = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        person.addEmail(emailAddress);

        PersonJPA expected = new PersonJPA(new PersonIDJPA(VALIDEMAIL), VALIDNAME, VALIDBIRTHDATE, VALIDVATNUMBER, new FamilyIDJPA(VALIDEMAIL));

        PersonJPA result = personDataDomainAssembler.toData(person);


        PersonIDJPA expectedPersonIDJPA = new PersonIDJPA(VALIDEMAIL);
        PersonIDJPA resultPersonIDJPA = result.getId();

        String exepctedName = VALIDNAME;
        String resultName = result.getName();

        List<EmailAddressJPA> expectedEmails = new ArrayList<>();
        expectedEmails.add(new EmailAddressJPA(null, ANOTHERVALIDEMAIL, expected));
        List<EmailAddressJPA> resultEmail = result.getEmails();

        List<PhoneNumberJPA> expectedPhone = new ArrayList<>();
        expectedPhone.add(new PhoneNumberJPA(null, VALIDPHONENUMBER, expected));

        List<PhoneNumberJPA> resultPhones = result.getPhones();

        Integer expectedVat = VALIDVATNUMBER;
        Integer resultVat = result.getVat();

        AddressJPA expectedAddress = new AddressJPA(null, VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER, expected);
        AddressJPA resultAddressJPA = result.getAddress();

        FamilyIDJPA expectedFamilyIDJPA = new FamilyIDJPA("@" + VALIDEMAIL);
        FamilyIDJPA resultFamilyIDJPA = result.getFamilyid();

        assertEquals(expected, result);

        assertEquals(expectedPersonIDJPA, resultPersonIDJPA);
        assertEquals(exepctedName, resultName);
        assertEquals(expectedEmails, resultEmail);
        assertEquals(expectedVat, resultVat);
        assertEquals(expectedAddress, resultAddressJPA);
        assertEquals(expectedFamilyIDJPA, resultFamilyIDJPA);
        assertEquals(expectedPhone, resultPhones);
        assertTrue(!resultEmail.isEmpty());
        assertTrue(!resultPhones.isEmpty());
    }


    @Test
    void createAddress() {
        PersonJPA personJPA = new PersonJPA(new PersonIDJPA(VALIDEMAIL), VALIDNAME, VALIDBIRTHDATE, VALIDVATNUMBER, new FamilyIDJPA(VALIDEMAIL));
        AddressJPA addressJPA = new AddressJPA(null, VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER, personJPA);
        personJPA.setAddress(addressJPA);
        Address expected = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER);
        Address result = personDataDomainAssembler.createAddress(personJPA);

        assertNotNull(result);
        assertNotSame(expected, result);
        assertEquals(expected, result);

    }

    @Test
    void createPhoneNumberList() {
        PersonJPA personJPA = new PersonJPA(new PersonIDJPA(VALIDEMAIL), VALIDNAME, VALIDBIRTHDATE, VALIDVATNUMBER, new FamilyIDJPA(VALIDEMAIL));
        List<PhoneNumberJPA> phonesJPAList = new ArrayList<>();
        phonesJPAList.add(new PhoneNumberJPA(null, 919999999, personJPA));
        personJPA.setPhones(phonesJPAList);

        List<PhoneNumber> expected = new ArrayList<>();
        expected.add(new PhoneNumber(919999999));
        List<PhoneNumber> result = personDataDomainAssembler.createPhoneNumberList(personJPA);

        assertNotNull(result);
        assertNotSame(expected, result);
        assertEquals(expected, result);
    }

    @Test
    void createEmailAdressList() {
        PersonJPA personJPA = new PersonJPA(new PersonIDJPA(VALIDEMAIL), VALIDNAME, VALIDBIRTHDATE, VALIDVATNUMBER, new FamilyIDJPA(VALIDEMAIL));
        List<EmailAddressJPA> emailAddressJPAList = new ArrayList<>();
        emailAddressJPAList.add(new EmailAddressJPA(null, "email@email.com", personJPA));
        personJPA.setEmails(emailAddressJPAList);

        List<EmailAddress> expected = new ArrayList<>();
        expected.add(new EmailAddress("email@email.com"));
        List<EmailAddress> result = personDataDomainAssembler.createEmailAdressList(personJPA);

        assertNotNull(result);
        assertNotSame(expected, result);
        assertEquals(expected, result);
    }

    @Test
    void createVATNumber() {
        PersonJPA personJPA = new PersonJPA(new PersonIDJPA(VALIDEMAIL), VALIDNAME, VALIDBIRTHDATE, VALIDVATNUMBER, new FamilyIDJPA(VALIDEMAIL));

        VATNumber expected = new VATNumber(VALIDVATNUMBER);
        VATNumber result = personDataDomainAssembler.createVATNumber(personJPA);

        assertNotNull(result);
        assertNotSame(expected, result);
        assertEquals(expected, result);
    }

    @Test
    void createPersonID() {
        PersonJPA personJPA = new PersonJPA(new PersonIDJPA(VALIDEMAIL), VALIDNAME, VALIDBIRTHDATE, VALIDVATNUMBER, new FamilyIDJPA(VALIDEMAIL));

        PersonID expected = new PersonID(VALIDEMAIL);
        PersonID result = personDataDomainAssembler.createPersonID(personJPA);

        assertNotNull(result);
        assertNotSame(expected, result);
        assertEquals(expected, result);
    }

    @Test
    void createName() {
        PersonJPA personJPA = new PersonJPA(new PersonIDJPA(VALIDEMAIL), VALIDNAME, VALIDBIRTHDATE, VALIDVATNUMBER, new FamilyIDJPA(VALIDEMAIL));

        Name expected = new Name(VALIDNAME);
        Name result = personDataDomainAssembler.createName(personJPA);

        assertNotNull(result);
        assertNotSame(expected, result);
        assertEquals(expected, result);
    }

    @Test
    void createBirthDate() {
        PersonJPA personJPA = new PersonJPA(new PersonIDJPA(VALIDEMAIL), VALIDNAME, VALIDBIRTHDATE, VALIDVATNUMBER, new FamilyIDJPA(VALIDEMAIL));

        BirthDate expected = new BirthDate(VALIDBIRTHDATE);
        BirthDate result = personDataDomainAssembler.createBirthDate(personJPA);

        assertNotNull(result);
        assertNotSame(expected, result);
        assertEquals(expected, result);

    }

    @Test
    void createFamilyID() {
        PersonJPA personJPA = new PersonJPA(new PersonIDJPA(VALIDEMAIL), VALIDNAME, VALIDBIRTHDATE, VALIDVATNUMBER, new FamilyIDJPA(VALIDEMAIL));

        FamilyID expected = new FamilyID(VALIDEMAIL);
        FamilyID result = personDataDomainAssembler.createFamilyID(personJPA);

        assertNotNull(result);
        assertNotSame(expected, result);
        assertEquals(expected, result);
    }

    @Test
    void testCreateFamilyID() {
        familyID = new FamilyID(VALIDEMAIL);
        FamilyIDJPA expected = new FamilyIDJPA("@" + VALIDEMAIL);
        FamilyIDJPA result = personDataDomainAssembler.createFamilyID(familyID);
        assertEquals(expected, result);
        assertNotNull(result);
    }


}