package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.person.InputPersonDTO;
import switchtwentytwenty.project.dto.person.OutputEmailDTO;
import switchtwentytwenty.project.dto.person.OutputPersonDTO;
import switchtwentytwenty.project.dto.person.OutputRemoveEmailDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {Person.class, PersonToDTO.class})
class PersonDTODomainAssemblerTest {


    Name name = new Name("TonyZe");
    BirthDate birthdate = new BirthDate("12/12/1970");
    PersonID personID = new PersonID("tonyze@gmail.com");
    VATNumber vat = new VATNumber(123456789);
    FamilyID familyID = new FamilyID("tonyze@gmail.com");
    String street = "Rua da amargura";
    String city = "Amadora";
    String zipCode = "4444-111";
    String doorNumber = "47";
    Address address = new Address(street, city, zipCode, doorNumber);

    InputPersonDTO inputPersonDTO = new InputPersonDTO("tonyze@gmail.com", "TonyZe", "12/12/1970",
            123456789, 999999999, "Rua da amargura", "Amadora", "47", "4444-111", "password");

    @Mock
    Person person;

    @Mock
    List<String> mockPhoneNumbers;

    @InjectMocks
    PersonDTODomainAssembler personToDTO;


    @Test
    @DisplayName("Should return a not null PersonDataDTO")
    void createPersonProfileDTOSuccess() {
        Mockito.when(person.id()).thenReturn(personID);
        Mockito.when(person.getName()).thenReturn(name);
        Mockito.when(person.getBirthdate()).thenReturn(birthdate);
        Mockito.when(person.getVat()).thenReturn(vat);
        Mockito.when(person.getAddress()).thenReturn(address);
        Mockito.when(person.getFamilyID()).thenReturn(familyID);


        OutputPersonDTO result = personToDTO.toDTO(person);

        assertNotNull(result);
    }

    /*
    @Test
    @DisplayName("Should return a DTO with PhoneNumbers in string format")
    void phoneNumberListInStringFormat(){
        PhoneNumber number1 = new PhoneNumber(919999999);
        PhoneNumber number2 = new PhoneNumber(918888888);
        List<PhoneNumber> phoneNumbers = new ArrayList();
        phoneNumbers.add(number1);
        phoneNumbers.add(number2);

        Mockito.when(person.id()).thenReturn(personID);
        Mockito.when(person.getName()).thenReturn(name);
        Mockito.when(person.getBirthdate()).thenReturn(birthdate);
        Mockito.when(person.getVat()).thenReturn(vat);
        Mockito.when(person.getAddress()).thenReturn(address);
        Mockito.when(person.getFamilyID()).thenReturn(familyID);
        Mockito.when(person.getPhoneNumbers()).thenReturn(phoneNumbers);

        PersonProfileDTO result = personToDTO.createPersonProfileDTO(person);



        assertEquals(result.getPhoneNumbers(),mockPhoneNumbers);
    }

     */



    @Test
    @DisplayName("Should return a not null PersonDataDTO with a phone number list")
    void createPersonProfileDTOSuccessTestPhoneNumbers() {
        PhoneNumber number1 = new PhoneNumber(919999999);
        PhoneNumber number2 = new PhoneNumber(918888888);
        List<PhoneNumber> phoneNumbers = new ArrayList();
        phoneNumbers.add(number1);
        phoneNumbers.add(number2);

        Mockito.when(person.id()).thenReturn(personID);
        Mockito.when(person.getName()).thenReturn(name);
        Mockito.when(person.getBirthdate()).thenReturn(birthdate);
        Mockito.when(person.getVat()).thenReturn(vat);
        Mockito.when(person.getAddress()).thenReturn(address);
        Mockito.when(person.getFamilyID()).thenReturn(familyID);
        Mockito.when(person.getPhoneNumbers()).thenReturn(phoneNumbers);

        List<Integer> expected = new ArrayList<>();
        expected.add(919999999);
        expected.add(918888888);

        OutputPersonDTO result = personToDTO.toDTO(person);

        assertEquals(expected, result.getPhoneNumbers());
    }

    @Test
    @DisplayName("Should return a not null PersonDataDTO with an EmailAddress list")
    void createPersonProfileDTOSuccessTestEmailAddress() {
        EmailAddress email1 = new EmailAddress("email1@email.com");
        EmailAddress email2 = new EmailAddress("email2@email.com");
        List<EmailAddress> emailAddressList = new ArrayList();
        emailAddressList.add(email1);
        emailAddressList.add(email2);

        Mockito.when(person.id()).thenReturn(personID);
        Mockito.when(person.getName()).thenReturn(name);
        Mockito.when(person.getBirthdate()).thenReturn(birthdate);
        Mockito.when(person.getVat()).thenReturn(vat);
        Mockito.when(person.getAddress()).thenReturn(address);
        Mockito.when(person.getFamilyID()).thenReturn(familyID);
        Mockito.when(person.getEmails()).thenReturn(emailAddressList);

        List<String> expected = new ArrayList<>();
        expected.add("email1@email.com");
        expected.add("email2@email.com");

        OutputPersonDTO result = personToDTO.toDTO(person);

        assertEquals(expected, result.getEmails());
    }

    @Test
    void createAddressTest() {
        String street = "Rua da amargura";
        String city = "Amadora";
        String zipCode = "4444-111";
        String doorNumber = "47";
        Address expected = new Address(street, city, zipCode, doorNumber);

        Address result = personToDTO.createAddress(inputPersonDTO);

        assertEquals(expected,result);
    }

    @Test
    void createPhoneNumberTest() {
        PhoneNumber expected = new PhoneNumber(999999999);

        PhoneNumber result = personToDTO.createPhoneNumber(inputPersonDTO);

        assertEquals(expected,result);
    }

    @Test
    void createVATNumberTest() {
        VATNumber expected = new VATNumber(123456789);

        VATNumber result = personToDTO.createVATNumber(inputPersonDTO);

        assertEquals(expected,result);
    }

    @Test
    void createPersonIDTest() {
        PersonID expected = new PersonID("tonyze@gmail.com");

        PersonID result = personToDTO.createPersonID(inputPersonDTO);

        assertEquals(expected,result);
    }

    @Test
    void createNameTest() {
        Name expected = new Name("TonyZe");

        Name result = personToDTO.createName(inputPersonDTO);

        assertEquals(expected,result);
    }

    @Test
    void createBirthDateTest() {
        BirthDate expected = new BirthDate("12/12/1970");

        BirthDate result = personToDTO.createBirthDate(inputPersonDTO);

        assertEquals(expected,result);
    }

    @Test
    void toDTOTest(){
        Person person = new Person(personID,name,birthdate,null,vat,null,address,familyID);
        OutputPersonDTO expected = new OutputPersonDTO("tonyze@gmail.com","TonyZe","12/12/1970",new ArrayList<>(),new ArrayList<>(),"123456789","Rua da amargura","Amadora","4444-111","47","@tonyze@gmail.com");

        OutputPersonDTO result = personToDTO.toDTO(person);

        assertEquals(expected,result);
    }

    @Test
    void toEmailDTOTest() {
        List emails = new ArrayList();
        emails.add(new EmailAddress("toze@hotmail.com"));
        Person person = new Person(personID, name, birthdate, emails, vat, null, address, familyID);
        OutputEmailDTO expected = new OutputEmailDTO("toze@hotmail.com");

        OutputEmailDTO result = personToDTO.toEmailDTO(person);

        assertEquals(expected, result);
    }

    @Test
    void getPersonEmailsList() {
        Person person = new Person(personID, name, birthdate, null, vat, null, address, familyID);
        List<String> expected = new ArrayList<>();

        List<String> result = personToDTO.toDTO(person).getEmails();

        assertEquals(expected, result);
    }

    @Test
    void getPersonPhoneNumberList() {
        Person person = new Person(personID, name, birthdate, null, vat, null, address, familyID);
        List<Integer> expected = new ArrayList<>();

        List<Integer> result = personToDTO.toDTO(person).getPhoneNumbers();

        assertEquals(expected, result);
    }


    @Test
    void toOutputRemoveEmailDTOTestCorrectConversionOfListsExpectingEquals() {
        String emailOne = "tonyze@email.com";
        String emailTwo = "tonyze@emailtwo.com";
        List<String> expected = new ArrayList<>();
        expected.add(emailOne);
        expected.add(emailTwo);

        List<EmailAddress> emailAddresses = new ArrayList<>();
        EmailAddress emailAddressOne = new EmailAddress(emailOne);
        EmailAddress emailAddressTwo = new EmailAddress(emailTwo);
        emailAddresses.add(emailAddressOne);
        emailAddresses.add(emailAddressTwo);

        PersonDTODomainAssembler assembler = new PersonDTODomainAssembler();

        OutputRemoveEmailDTO result = assembler.toOutputRemoveEmailDTO(emailAddresses);

        assertEquals(expected, result.getEmailAddresses());
    }

    @Test
    void toOutputRemoveEmailDTOTestDifferentListsExpectingNotEquals() {
        String emailOne = "tonyze@email.com";
        String emailTwo = "tonyze@emailtwo.com";
        List<String> expected = new ArrayList<>();
        expected.add(emailOne);
        expected.add(emailTwo);

        List<EmailAddress> emailAddresses = new ArrayList<>();

        PersonDTODomainAssembler assembler = new PersonDTODomainAssembler();

        OutputRemoveEmailDTO result = assembler.toOutputRemoveEmailDTO(emailAddresses);

        assertNotEquals(expected, result.getEmailAddresses());
    }
}