package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.authentication.JWTUserDetailsService;
import switchtwentytwenty.project.authentication.UserDao;
import switchtwentytwenty.project.datamodel.domainjpa.PersonJPA;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.FamilyDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.PersonDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.PersonIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.IFamilyRepositoryJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.IPersonRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.assemblers.implassemblers.FamilyDTODomainAssembler;
import switchtwentytwenty.project.dto.family.InputFamilyDTO;
import switchtwentytwenty.project.dto.person.InputPersonDTO;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonDTODomainAssembler;
import switchtwentytwenty.project.exceptions.InvalidNameException;
import switchtwentytwenty.project.interfaceadapters.implrepositories.FamilyRepository;
import switchtwentytwenty.project.interfaceadapters.implrepositories.PersonRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CreateFamilyServiceIT {


    CreateFamilyService createFamilyService;

    @Mock
    FamilyDataDomainAssembler familyDataDomainAssembler;

    @Mock
    IFamilyRepositoryJPA familyRepositoryJPA;

    @InjectMocks
    FamilyRepository familyRepository;

    @Mock
    PersonDataDomainAssembler personDataDomainAssembler;

    @Mock
    IPersonRepositoryJPA iPersonRepositoryJPA;

    @InjectMocks
    PersonRepository personRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserDao userDao;

    @InjectMocks
    JWTUserDetailsService jwtUserDetailsService;

    @Autowired
    PersonDTODomainAssembler personDTODomainAssembler;
    @Autowired
    FamilyDTODomainAssembler familyDTODomainAssembler;


    final String VALIDNAME = "JessicaMicaela";
    final String VALIDEMAIL = "jessicaMicaela@latinlover.pt";
    final int VALIDVATNUMBER = 999999999;
    final Integer VALIDPHONENUMBER = 916969696;
    final String VALIDSTREET = "Rua";
    final String VALIDCITY = "Ermesinde";
    final String VALIDZIPCODE = "4700-111";
    final String VALIDADDRESSNUMBER = "69B";
    final String VALIDBIRTHDATE = "01/03/1990";
    final String PASSWORD = "password";

    InputPersonDTO inputPersonDTO = new InputPersonDTO(VALIDEMAIL, VALIDNAME, VALIDBIRTHDATE, VALIDVATNUMBER,
            VALIDPHONENUMBER, VALIDSTREET, VALIDCITY, VALIDADDRESSNUMBER, VALIDZIPCODE, PASSWORD);
    //
    final String VALID_FAMILY_NAME = "Simpson";
    final String VALID_REGISTRATION_DATE = "01/03/1990";
    InputFamilyDTO inputFamilyDTO = new InputFamilyDTO(VALID_FAMILY_NAME, VALID_REGISTRATION_DATE);

    PhoneNumber phoneNumber = new PhoneNumber(VALIDPHONENUMBER);


    @Test
    @Tag("US010")
    void createFamilyAndAddAdminValidData() {
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(phoneNumber);

        // Arrange FamilyRepository
        when(familyRepositoryJPA.findById(any(FamilyIDJPA.class))).thenReturn(Optional.empty());
        when(familyDataDomainAssembler.toData(any(Family.class))).thenReturn(new FamilyJPA());
        when(familyRepositoryJPA.save(any(FamilyJPA.class))).thenReturn(new FamilyJPA(new FamilyIDJPA(VALIDEMAIL), "Silva", "12/12/1999", new PersonIDJPA(VALIDEMAIL)));
        when(familyDataDomainAssembler.createFamilyID(any(FamilyJPA.class))).thenReturn(new FamilyID(VALIDEMAIL));
        when(familyDataDomainAssembler.createFamilyName(any(FamilyJPA.class))).thenReturn(new FamilyName("Silva"));
        when(familyDataDomainAssembler.createRegistrationDate(any(FamilyJPA.class))).thenReturn(new RegistrationDate("12/12/1999"));
        when(familyDataDomainAssembler.createAdminID(any(FamilyJPA.class))).thenReturn(new PersonID(VALIDEMAIL));

        // Arrange PersonRepository
        when(iPersonRepositoryJPA.findById(any(PersonIDJPA.class))).thenReturn(Optional.empty());
        when(personDataDomainAssembler.toData(any(Person.class))).thenReturn(new PersonJPA());
        when(iPersonRepositoryJPA.save(any(PersonJPA.class))).thenReturn(new PersonJPA(new PersonIDJPA(VALIDEMAIL), "TonyZe", "12/12/199", 123456789, new FamilyIDJPA(VALIDEMAIL)));
        when(personDataDomainAssembler.createPersonID(any(PersonJPA.class))).thenReturn(new PersonID("email@here.com"));
        when(personDataDomainAssembler.createName(any(PersonJPA.class))).thenReturn(new Name("Rui"));
        when(personDataDomainAssembler.createBirthDate(any(PersonJPA.class))).thenReturn(new BirthDate("12/12/1999"));
        when(personDataDomainAssembler.createEmailAdressList(any(PersonJPA.class))).thenReturn(Collections.emptyList());
        when(personDataDomainAssembler.createVATNumber(any(PersonJPA.class))).thenReturn(new VATNumber(123456789));
        when(personDataDomainAssembler.createPhoneNumberList(any(PersonJPA.class))).thenReturn(phoneNumbers);
        when(personDataDomainAssembler.createAddress(any(PersonJPA.class))).thenReturn(new Address("Rua", "Covilhã", "6200-000", "1"));
        when(personDataDomainAssembler.createFamilyID(any(PersonJPA.class))).thenReturn(new FamilyID("email@here.com"));

        createFamilyService = new CreateFamilyService(personRepository, familyRepository, personDTODomainAssembler, familyDTODomainAssembler, jwtUserDetailsService);

        assertDoesNotThrow(() -> createFamilyService.createFamilyAndAddAdmin(inputFamilyDTO, inputPersonDTO));
    }

    @Test
    @Tag("US010")
    void createFamilyAndAddAdminInvalidNameThrowsException() {
        // Arrange FamilyRepository
        when(familyRepositoryJPA.findById(any(FamilyIDJPA.class))).thenReturn(Optional.empty());
        when(familyDataDomainAssembler.toData(any(Family.class))).thenReturn(new FamilyJPA());
        when(familyRepositoryJPA.save(any(FamilyJPA.class))).thenReturn(new FamilyJPA());
        // Arrange PersonRepository
        when(iPersonRepositoryJPA.findById(any(PersonIDJPA.class))).thenReturn(Optional.empty());
        when(personDataDomainAssembler.toData(any(Person.class))).thenReturn(new PersonJPA());
        when(iPersonRepositoryJPA.save(any(PersonJPA.class))).thenReturn(new PersonJPA());

        createFamilyService = new CreateFamilyService(personRepository, familyRepository, personDTODomainAssembler, familyDTODomainAssembler, jwtUserDetailsService);

        InputPersonDTO invalidInputPersonDTO = new InputPersonDTO(VALIDEMAIL, "", VALIDBIRTHDATE, VALIDVATNUMBER,
                VALIDPHONENUMBER, VALIDSTREET, VALIDCITY, VALIDADDRESSNUMBER, VALIDZIPCODE, PASSWORD);

        assertThrows(InvalidNameException.class, () -> createFamilyService.createFamilyAndAddAdmin(inputFamilyDTO, invalidInputPersonDTO));
    }

}