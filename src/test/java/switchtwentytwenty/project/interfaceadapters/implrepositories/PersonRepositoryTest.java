package switchtwentytwenty.project.interfaceadapters.implrepositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.PersonDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.PersonIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.PersonJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.IPersonRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.exceptions.EmailNotRegisteredException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonRepositoryTest {

    @Mock
    IPersonRepositoryJPA iPersonRepositoryJPA;

    @Mock
    PersonDataDomainAssembler personDataDomainAssembler;

    @InjectMocks
    PersonRepository personRepository;

    @Captor
    ArgumentCaptor<PersonIDJPA> captorPersonIDJPA;

    // Person
    String VALIDNAME = "TonyZe";
    String VALIDEMAIL = "tonyze@latinlover.pt";
    int VALIDVATNUMBER = 999999999;
    int VALIDPHONENUMBER = 916969696;
    String VALIDSTREET = "Rua";
    String VALIDCITY = "Ermesinde";
    String VALIDZIPCODE = "4700-111";
    String VALIDADDRESSNUMBER = "69";
    String VALIDBIRTHDATE = "01/03/1990";

    Name tonyZeName= new Name(VALIDNAME);
    BirthDate tonyZeBirthDate = new BirthDate(VALIDBIRTHDATE);
    PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
    VATNumber tonyZeVat = new VATNumber(VALIDVATNUMBER);
    PhoneNumber tonyZePhone = new PhoneNumber(VALIDPHONENUMBER);
    Address tonyZeAddress = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER);
    FamilyID familyID = new FamilyID(VALIDEMAIL);

    PersonID mariaZeEmail = new PersonID("mariaZe@gmail.com");

    Person person = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
    Person personTwo = new Person(tonyZeName, tonyZeBirthDate, mariaZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);

    FamilyIDJPA familyIDJPA = new FamilyIDJPA("@tonyze@latinlover.pt");
    PersonIDJPA personIDJPAOne = new PersonIDJPA(VALIDEMAIL);
    PersonIDJPA personIDJPATwo = new PersonIDJPA("mariaZe@gmail.com");
    PersonJPA personJPAOne = new PersonJPA(personIDJPAOne, VALIDNAME, VALIDBIRTHDATE, VALIDVATNUMBER, familyIDJPA);
    PersonJPA personJPATwo = new PersonJPA(personIDJPATwo, VALIDNAME, VALIDBIRTHDATE, VALIDVATNUMBER, familyIDJPA);
    List<PersonJPA> personJPAList = new ArrayList<>();

    List<Person> personList = new ArrayList<>();
    private AutoCloseable closeble;

    @BeforeEach
    void setUp() {
        closeble = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeble.close();
    }

    /* UNIT TESTS */

    @Test
    void addPersonDoesNotThrow() {
        PersonJPA personJPA = new PersonJPA();

        when(personDataDomainAssembler.toData(any(Person.class))).thenReturn(personJPA);
        when(iPersonRepositoryJPA.save(any(PersonJPA.class))).thenReturn(personJPA);

        assertDoesNotThrow(() -> personRepository.add(person));
    }

    @Test
    void addPersonSuccess() {
        PhoneNumber tonyZePhone = new PhoneNumber(VALIDPHONENUMBER);
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(tonyZePhone);
        PersonJPA personJPA = new PersonJPA();

        when(personDataDomainAssembler.toData(any(Person.class))).thenReturn(personJPA);
        when(iPersonRepositoryJPA.save(any(PersonJPA.class))).thenReturn(personJPA);
        when(personDataDomainAssembler.createPersonID(any(PersonJPA.class))).thenReturn(tonyZeEmail);
        when(personDataDomainAssembler.createName(any(PersonJPA.class))).thenReturn(tonyZeName);
        when(personDataDomainAssembler.createBirthDate(any(PersonJPA.class))).thenReturn(tonyZeBirthDate);
        when(personDataDomainAssembler.createEmailAdressList(any(PersonJPA.class))).thenReturn(Collections.emptyList());
        when(personDataDomainAssembler.createVATNumber(any(PersonJPA.class))).thenReturn(tonyZeVat);
        when(personDataDomainAssembler.createPhoneNumberList(any(PersonJPA.class))).thenReturn(phoneNumbers);
        when(personDataDomainAssembler.createAddress(any(PersonJPA.class))).thenReturn(tonyZeAddress);
        when(personDataDomainAssembler.createFamilyID(any(PersonJPA.class))).thenReturn(familyID);

        Person expected = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        Person result = personRepository.add(person);

        assertEquals(expected, result);
    }

    @Test
    void addPersonDoesThrow() {
        PersonJPA personJPA = new PersonJPA();

        when(iPersonRepositoryJPA.findById(any(PersonIDJPA.class))).thenReturn(Optional.of(new PersonJPA()));

        assertThrows(IllegalStateException.class, () -> personRepository.add(person));
    }

    @Test
    void updatePersonSuccess() {
        PhoneNumber tonyZePhone = new PhoneNumber(VALIDPHONENUMBER);
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(tonyZePhone);
        PersonJPA personJPA = new PersonJPA();

        when(personDataDomainAssembler.toData(any(Person.class))).thenReturn(personJPA);
        when(iPersonRepositoryJPA.save(any(PersonJPA.class))).thenReturn(personJPA);
        when(personDataDomainAssembler.createPersonID(any(PersonJPA.class))).thenReturn(tonyZeEmail);
        when(personDataDomainAssembler.createName(any(PersonJPA.class))).thenReturn(tonyZeName);
        when(personDataDomainAssembler.createBirthDate(any(PersonJPA.class))).thenReturn(tonyZeBirthDate);
        when(personDataDomainAssembler.createEmailAdressList(any(PersonJPA.class))).thenReturn(Collections.emptyList());
        when(personDataDomainAssembler.createVATNumber(any(PersonJPA.class))).thenReturn(tonyZeVat);
        when(personDataDomainAssembler.createPhoneNumberList(any(PersonJPA.class))).thenReturn(phoneNumbers);
        when(personDataDomainAssembler.createAddress(any(PersonJPA.class))).thenReturn(tonyZeAddress);
        when(personDataDomainAssembler.createFamilyID(any(PersonJPA.class))).thenReturn(familyID);

        Person expected = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);
        Person result = personRepository.updatePerson(person);

        assertEquals(expected, result);
    }

    @Test
    void getByIDTestPersonNotFoundThrowEmailNotRegisteredException() {
        Optional<PersonJPA> optional = Optional.empty();

        when(iPersonRepositoryJPA.findById(any(PersonIDJPA.class))).thenReturn(optional);

        assertThrows(EmailNotRegisteredException.class,()->personRepository.getByID(new PersonID("notregistered@gmail.com")));
    }
    @Test
    void findAllByFamilyIDSuccess() {
        PhoneNumber tonyZePhone = new PhoneNumber(VALIDPHONENUMBER);
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(tonyZePhone);
        personJPAList.add(personJPAOne);
        personJPAList.add(personJPATwo);

        when(personDataDomainAssembler.createFamilyID(any(FamilyID.class))).thenReturn(familyIDJPA);
        when(iPersonRepositoryJPA.findAllByFamilyid(any(FamilyIDJPA.class))).thenReturn(personJPAList);

        when(personDataDomainAssembler.createPersonID(personJPAOne)).thenReturn(tonyZeEmail);
        when(personDataDomainAssembler.createName(personJPAOne)).thenReturn(tonyZeName);
        when(personDataDomainAssembler.createBirthDate(personJPAOne)).thenReturn(tonyZeBirthDate);
        when(personDataDomainAssembler.createEmailAdressList(personJPAOne)).thenReturn(Collections.emptyList());
        when(personDataDomainAssembler.createVATNumber(personJPAOne)).thenReturn(tonyZeVat);
        when(personDataDomainAssembler.createPhoneNumberList(personJPAOne)).thenReturn(phoneNumbers);
        when(personDataDomainAssembler.createAddress(personJPAOne)).thenReturn(tonyZeAddress);
        when(personDataDomainAssembler.createFamilyID(personJPAOne)).thenReturn(familyID);

        when(personDataDomainAssembler.createPersonID(personJPATwo)).thenReturn(tonyZeEmail);
        when(personDataDomainAssembler.createName(personJPATwo)).thenReturn(tonyZeName);
        when(personDataDomainAssembler.createBirthDate(personJPATwo)).thenReturn(tonyZeBirthDate);
        when(personDataDomainAssembler.createPersonID(personJPATwo)).thenReturn(mariaZeEmail);
        when(personDataDomainAssembler.createVATNumber(personJPATwo)).thenReturn(tonyZeVat);
        when(personDataDomainAssembler.createPhoneNumberList(personJPATwo)).thenReturn(phoneNumbers);
        when(personDataDomainAssembler.createAddress(personJPATwo)).thenReturn(tonyZeAddress);
        when(personDataDomainAssembler.createFamilyID(personJPATwo)).thenReturn(familyID);

        List<Person> expected = new ArrayList<>();
        expected.add(person);
        expected.add(personTwo);

        List<Person> result = personRepository.findAllByFamilyID(familyID);

        assertEquals(expected, result);
    }

}