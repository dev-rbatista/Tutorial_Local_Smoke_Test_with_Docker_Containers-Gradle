package switchtwentytwenty.project.interfaceadapters.controller.implcontrollers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.PersonDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.PersonIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.PersonJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.IPersonRepositoryJPA;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonDTODomainAssembler;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonInputDTOAssembler;
import switchtwentytwenty.project.dto.person.OutputPersonDTO;
import switchtwentytwenty.project.interfaceadapters.implrepositories.PersonRepository;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IAddEmailService;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IAddFamilyMemberService;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IRemoveEmailService;
import switchtwentytwenty.project.usecaseservices.applicationservices.implappservices.GetFamilyMemberProfileService;
import switchtwentytwenty.project.usecaseservices.applicationservices.implappservices.PeopleOptionsService;
import switchtwentytwenty.project.usecaseservices.applicationservices.implappservices.PersonOptionsService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SpringBootTest
class PersonRESTControllerIT {


    // Person Repository
    @Mock
    IPersonRepositoryJPA iPersonRepositoryJPA;

    @Mock
    PersonDataDomainAssembler personDataDomainAssembler;

    // Get Family Member Profile Service
    @Autowired
    PersonDTODomainAssembler personDTODomainAssembler;

    @InjectMocks
    PersonRepository personRepository;

    @Autowired
    IRemoveEmailService removeEmailService;

    // PersonRESTController

    @Autowired
    IAddEmailService addEmailService;

    @Autowired
    PersonInputDTOAssembler personInputDTOAssembler;

    @Autowired
    PersonInputDTOAssembler profileInternalExternalAssembler;

    @Autowired
    PersonOptionsService personOptionsService;

    @Autowired
    IAddFamilyMemberService iAddFamilyMemberService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }


    @DisplayName("Get Profile - Controller - Integration Test - Success")
    @Test
    void integrationTestSuccessCase() {
        // Init Classes
        GetFamilyMemberProfileService getFamilyMemberProfileService = new GetFamilyMemberProfileService(personRepository, personDTODomainAssembler);
        PeopleOptionsService peopleOptionsService = new PeopleOptionsService();
        PersonRESTController personRESTController = new PersonRESTController(peopleOptionsService, personOptionsService, profileInternalExternalAssembler, getFamilyMemberProfileService, iAddFamilyMemberService, personInputDTOAssembler, addEmailService, removeEmailService);

        // PersonID
        String personID = "tonyze@gmail.com";

        // PersonJPA
        PersonJPA personJPA = new PersonJPA();

        // Person
        final String VALIDNAME = "TonyZe";
        final String VALIDEMAIL = "tonyze@latinlover.pt";
        final int VALIDVATNUMBER = 999999999;
        final Integer VALIDPHONENUMBER = 916969696;
        final String VALIDSTREET = "Rua";
        final String VALIDCITY = "Ermesinde";
        final String VALIDZIPCODE = "4700-111";
        final String VALIDADDRESSNUMBER = "69";
        final String VALIDBIRTHDATE = "1/03/1990";

        Name tonyZeName = new Name(VALIDNAME);
        BirthDate tonyZeBirthDate = new BirthDate(VALIDBIRTHDATE);
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        VATNumber tonyZeVat = new VATNumber(VALIDVATNUMBER);
        PhoneNumber tonyZePhone = new PhoneNumber(VALIDPHONENUMBER);
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(tonyZePhone);
        Address tonyZeAddress = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER);
        FamilyID familyID = new FamilyID(VALIDEMAIL);

        //Person person = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);

        // Mocking
        when(iPersonRepositoryJPA.existsById(any(PersonIDJPA.class))).thenReturn(true);
        when(iPersonRepositoryJPA.findById(any(PersonIDJPA.class))).thenReturn(Optional.of(personJPA));
        when(personDataDomainAssembler.createPersonID(any(PersonJPA.class))).thenReturn(tonyZeEmail);
        when(personDataDomainAssembler.createName(any(PersonJPA.class))).thenReturn(tonyZeName);
        when(personDataDomainAssembler.createBirthDate(any(PersonJPA.class))).thenReturn(tonyZeBirthDate);
        when(personDataDomainAssembler.createEmailAdressList(any(PersonJPA.class))).thenReturn(Collections.emptyList());
        when(personDataDomainAssembler.createVATNumber(any(PersonJPA.class))).thenReturn(tonyZeVat);
        when(personDataDomainAssembler.createPhoneNumberList(any(PersonJPA.class))).thenReturn(phoneNumbers);
        when(personDataDomainAssembler.createAddress(any(PersonJPA.class))).thenReturn(tonyZeAddress);
        when(personDataDomainAssembler.createFamilyID(any(PersonJPA.class))).thenReturn(familyID);

        // Expected
        List<String> expectedEmails = Collections.emptyList();

        List<Integer> expectedPhones = new ArrayList<>();
        expectedPhones.add(VALIDPHONENUMBER);

        OutputPersonDTO expectedOutputPersonDTO = new OutputPersonDTO(VALIDEMAIL, VALIDNAME, VALIDBIRTHDATE, expectedEmails, expectedPhones, "999999999", VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER, VALIDEMAIL);

        Link expectedLink = linkTo(methodOn(PersonRESTController.class).personOptions(personID)).withSelfRel();
        Link expectedLink2 = linkTo(methodOn(FamilyRESTController.class).getFamilyOptions(familyID.getId())).withRel("Family Link");
        expectedOutputPersonDTO.add(expectedLink);
        expectedOutputPersonDTO.add(expectedLink2);


        ResponseEntity expectedResponse = new ResponseEntity(expectedOutputPersonDTO, HttpStatus.OK);

        // Result
        ResponseEntity resultResponse = personRESTController.getProfileInfo(personID);

        // Assert
        assertEquals(expectedResponse.toString(), resultResponse.toString());
    }

    @DisplayName("Get Profile - Controller - Integration Test - Failure - EmailNotRegisteredException")
    @Test
    void integrationTestFailureCaseEmailNotRegistred() {
        // Init Classes
        GetFamilyMemberProfileService getFamilyMemberProfileService = new GetFamilyMemberProfileService(personRepository, personDTODomainAssembler);
        PeopleOptionsService peopleOptionsService = new PeopleOptionsService();
        PersonRESTController personRESTController = new PersonRESTController(peopleOptionsService, personOptionsService, profileInternalExternalAssembler, getFamilyMemberProfileService, iAddFamilyMemberService, personInputDTOAssembler, addEmailService,removeEmailService);

        // PersonID
        String personID = "tonyze@gmail.com";

        // Mocking
        when(iPersonRepositoryJPA.existsById(any(PersonIDJPA.class))).thenReturn(false);

        // Expected

        ResponseEntity expectedResponse = new ResponseEntity("Error: Email is not registered to any person", HttpStatus.UNPROCESSABLE_ENTITY);

        // Result
        ResponseEntity resultResponse = personRESTController.getProfileInfo(personID);

        // Assert
        assertEquals(expectedResponse.toString(), resultResponse.toString());
    }


}