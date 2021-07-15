package switchtwentytwenty.project.interfaceadapters.controller.implcontrollers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.domain.valueobject.PersonID;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonInputDTOAssembler;
import switchtwentytwenty.project.dto.person.*;
import switchtwentytwenty.project.exceptions.EmailAlreadyRegisteredException;
import switchtwentytwenty.project.exceptions.EmailNotRegisteredException;
import switchtwentytwenty.project.exceptions.InvalidEmailException;
import switchtwentytwenty.project.exceptions.PersonAlreadyRegisteredException;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.*;
import switchtwentytwenty.project.usecaseservices.applicationservices.implappservices.PeopleOptionsService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PersonRESTControllerTest {


    @Mock
    PersonInputDTOAssembler mockPersonInputDTOAssembler;

    @Mock
    IAddEmailService mockAddEmailService;

    @Mock
    IGetFamilyMemberProfileService getFamilyMemberProfileService;

    @Mock
    IAddFamilyMemberService mockAddFamilyMemberService;

    @Mock
    IRemoveEmailService mockRemoveEmailService;


    @Mock
    InputAddFamilyMemberDTO anInternalAddFamilyMemberDTO;

    @Mock
    IPersonOptionsService personOptionsService;

    @Mock
    PersonInputDTOAssembler profileInternalExternalAssembler;

    @Mock
    PeopleOptionsService peopleOptionsServiceMock;
    //@Mock
    //OutputPersonDTO anOutputPersonDTO;

    @InjectMocks
    PersonRESTController personRESTController;
    // Este Person precisa de ser instanciado para o email lhe ser adicionado após a implementação e poder testar se adiciona?
    //Ou pode ser mocked e o Spring faz magia negra e sabe que é o Tony e adiciona-lhe o email?
    //Person personToAddEmail;

    String emailAddressAsID = "tonyze@latinlover.com";

    PersonID personID = new PersonID(emailAddressAsID);
    String email = "tony@emailtoadd.com";
    String invalidEmailToAdd = "invalidemail.com";
    String emailIDAfterAddingToDatabase = "3L";
    InputEmailDTO internalEmailDTO = new InputEmailDTO(emailAddressAsID, email);


    OutputEmailDTO outputEmailDTO = new OutputEmailDTO(email);

    AddEmailDTO addEmailDTO = new AddEmailDTO(email);
    AddEmailDTO INVALIDAddEmailDTO = new AddEmailDTO(invalidEmailToAdd);
    InputEmailDTO INVALIDInternalEmailDTO = new InputEmailDTO(emailAddressAsID, INVALIDAddEmailDTO.unpackEmail());

    AddFamilyMemberDTO addFamilyMemberDTO = new AddFamilyMemberDTO("2L", "3L", "tony", "12/02/1999", 123456789, 961962963, "Rua da Estrada", "Porto", "12", "4000", "password");
    OutputPersonDTO realOutPutPersonDTO = new OutputPersonDTO();
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        // We can create mock based on the Interface or a Class
//    	templateEngine = mock(TemplateEngine.class);

        // init mock with annotations
        //MockitoAnnotations.initMocks(this);

        // just another version of initilization of mocks with annotation
        // pay attention to tear down method - we have to call close method
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Success case of adding an email to a Person")
    void successCaseInAddEmail() {
        when(mockPersonInputDTOAssembler.toInputEmailDTO(addEmailDTO, emailAddressAsID)).thenReturn(internalEmailDTO);
        when(mockAddEmailService.addEmail(internalEmailDTO)).thenReturn(outputEmailDTO);

        OutputEmailDTO expectedOutputEmailDTO = new OutputEmailDTO(emailAddressAsID);
        Link link = linkTo(methodOn(PersonRESTController.class).getProfileInfo(emailAddressAsID)).withSelfRel();
        expectedOutputEmailDTO.add(link);

        ResponseEntity expected = new ResponseEntity(expectedOutputEmailDTO, HttpStatus.OK);

        ResponseEntity result = personRESTController.addEmail(addEmailDTO, emailAddressAsID);

        assertEquals(expected.getBody().toString(), result.getBody().toString());
        assertEquals(expected.getStatusCode(), result.getStatusCode());
    }

    @DisplayName("Fail test when Email is already registered in the Person")
    @Test
    void failCaseInAddEmailWhenProvidedEmailIsAlreadyRegisteredInThePerson() {

        when(mockPersonInputDTOAssembler.toInputEmailDTO(any(AddEmailDTO.class), any(String.class))).thenReturn(internalEmailDTO);
        when(mockAddEmailService.addEmail(any(InputEmailDTO.class))).thenThrow(new EmailAlreadyRegisteredException());

        ResponseEntity expected = new ResponseEntity("Error: Email is already registered", HttpStatus.BAD_REQUEST);

        ResponseEntity result = personRESTController.addEmail(addEmailDTO, emailAddressAsID);

        assertEquals(expected, result);
    }

    @DisplayName("Fail test when Email is in invalid format")
    @Test
    void failCaseInAddEmailWhenProvidedEmailIsWrongfullyInsertedExpectingInvalidEmailException() {

        when(mockPersonInputDTOAssembler.toInputEmailDTO(any(AddEmailDTO.class), any(String.class))).thenReturn(INVALIDInternalEmailDTO);
        when(mockAddEmailService.addEmail(any(InputEmailDTO.class))).thenThrow(new InvalidEmailException("This Email is not valid"));

        //Sem certeza que Bad_Request se enquadra neste HttpStatus
        ResponseEntity expected = new ResponseEntity("Error: This Email is not valid", HttpStatus.BAD_REQUEST);

        ResponseEntity result = personRESTController.addEmail(INVALIDAddEmailDTO, emailAddressAsID);

        assertEquals(expected, result);
    }

    @DisplayName("Success - Get Profile Info")
    @Test
    void successCaseInGetProfileInfo() {
        String personID = "tony@gmail.com";

        InputGetProfileDTO inputGetProfileDTO = new InputGetProfileDTO();

        when(profileInternalExternalAssembler.toInternalGetProfileDTO(any(String.class))).thenReturn(inputGetProfileDTO);

        OutputPersonDTO outputPersonDTO = new OutputPersonDTO();

        when(getFamilyMemberProfileService.getFamilyMemberProfile(inputGetProfileDTO)).thenReturn(outputPersonDTO);

        OutputPersonDTO expectedOutputPersonDTO = new OutputPersonDTO();

        Link expectedLink = linkTo(methodOn(PersonRESTController.class).personOptions(personID)).withSelfRel();
        Link familyLink = linkTo(methodOn(FamilyRESTController.class).getFamilyOptions(outputPersonDTO.getFamilyID())).withRel("Family Link");

        expectedOutputPersonDTO.add(expectedLink);
        expectedOutputPersonDTO.add(familyLink);

        ResponseEntity expectedResponse = new ResponseEntity(expectedOutputPersonDTO, HttpStatus.OK);

        ResponseEntity resultResponse = personRESTController.getProfileInfo(personID);

        //assertEquals(expectedResponse, resultResponse);
        assertEquals(expectedResponse.getBody(), resultResponse.getBody());
        assertEquals(expectedResponse.getStatusCode(), resultResponse.getStatusCode());
    }

    @DisplayName("Unsuccess - Get Profile Info - throws EmailNotRegisteredException")
    @Test
    void unsuccessCaseInGetProfileInfo() {
        String personID = "tony@gmail.com";

        InputGetProfileDTO inputGetProfileDTO = new InputGetProfileDTO();

        when(profileInternalExternalAssembler.toInternalGetProfileDTO(any(String.class))).thenReturn(inputGetProfileDTO);

        when(getFamilyMemberProfileService.getFamilyMemberProfile(any(InputGetProfileDTO.class))).thenThrow(new EmailNotRegisteredException());

        ResponseEntity expected = new ResponseEntity("Error: Email is not registered to any person", HttpStatus.UNPROCESSABLE_ENTITY);

        ResponseEntity result = personRESTController.getProfileInfo(personID);

        assertEquals(expected.getBody(), result.getBody());
        assertEquals(expected.getStatusCode(), result.getStatusCode());

    }

    @DisplayName("Controller-level Unit test for a success case in adding family member")
    @Test
    void successCaseInAddFamilyMember() {
        OutputPersonDTO expectedOutputPersonDTO = new OutputPersonDTO();

        expectedOutputPersonDTO.setId("id@gmail.com");
        realOutPutPersonDTO.setId("id@gmail.com");
        when(mockPersonInputDTOAssembler.toInputAddFamilyMemberDTO(addFamilyMemberDTO)).thenReturn(anInternalAddFamilyMemberDTO);
        when(mockAddFamilyMemberService.addPerson(any(InputAddFamilyMemberDTO.class))).thenReturn(realOutPutPersonDTO);


        Link link = linkTo(methodOn(PersonRESTController.class).personOptions("id@gmail.com")).withRel("Person Options");
        expectedOutputPersonDTO.add(link);

        ResponseEntity expected = new ResponseEntity(expectedOutputPersonDTO, HttpStatus.CREATED);

        ResponseEntity result = personRESTController.addFamilyMember(addFamilyMemberDTO);


        assertEquals(expected.getBody(), result.getBody());
        assertEquals(expected.getStatusCode(), result.getStatusCode());
    }

    @DisplayName("Controller-level Unit test for a failure case in adding family member")
    @Test
    void failCaseInAddFamilyMember() {
        when(mockPersonInputDTOAssembler.toInputAddFamilyMemberDTO(any(AddFamilyMemberDTO.class))).thenReturn(anInternalAddFamilyMemberDTO);
        when(mockAddFamilyMemberService.addPerson(any(InputAddFamilyMemberDTO.class))).thenThrow(new PersonAlreadyRegisteredException("Person is already registered in the database"));


        ResponseEntity expected = new ResponseEntity("Error: Person is already registered in the database", HttpStatus.BAD_REQUEST);

        ResponseEntity result = personRESTController.addFamilyMember(addFamilyMemberDTO);


        assertEquals(expected.getBody(), result.getBody());
        assertEquals(expected.getStatusCode(), result.getStatusCode());
    }


    @Test
    void personOptionsSuccesCase() {
        OptionsDTO expectedOptionsDTO = new OptionsDTO();
        Link linkToPersonOptions = linkTo(methodOn(PersonRESTController.class).personOptions(personID.toString())).withSelfRel();
        Link linkToAddEmail = linkTo(methodOn(PersonRESTController.class).addEmail(new AddEmailDTO(), personID.toString())).withRel("POST - Add new Email");
        Link linkToGetProfileInfo = linkTo(methodOn(PersonRESTController.class).getProfileInfo(personID.toString())).withRel("GET - Get Profile Info");

        expectedOptionsDTO.add(linkToPersonOptions);
        expectedOptionsDTO.add(linkToAddEmail);
        expectedOptionsDTO.add(linkToGetProfileInfo);

        when(personOptionsService.getPersonOptions(personID.toString())).thenReturn(expectedOptionsDTO);

        HttpHeaders header = new HttpHeaders();
        header.set("Allow", "OPTIONS");

        ResponseEntity expected = new ResponseEntity(expectedOptionsDTO, header, HttpStatus.OK);

        ResponseEntity result = personRESTController.personOptions(personID.toString());

        assertNotNull(result);
        assertEquals(expected.getBody(), result.getBody());
        assertEquals(expected.getStatusCode(), result.getStatusCode());
        assertEquals(expected.getHeaders(), result.getHeaders());
    }

    @Test
    void peopleOptionsTest() {
        OptionsDTO optionsDTO = new OptionsDTO();

        Link linkToPeopleOptions = linkTo(methodOn(PersonRESTController.class).peopleOptions()).withSelfRel();

        optionsDTO.add(linkToPeopleOptions);

        when(peopleOptionsServiceMock.getPeopleOptions()).thenReturn(optionsDTO);

        OptionsDTO expectedOptionsDTO = new OptionsDTO();

        Link expectedLinkToPeopleOptions = linkTo(methodOn(PersonRESTController.class).peopleOptions()).withSelfRel();

        expectedOptionsDTO.add(expectedLinkToPeopleOptions);

        HttpHeaders header = new HttpHeaders();
        header.set("Allow", "POST, OPTIONS");

        ResponseEntity expected = new ResponseEntity<>(optionsDTO, header, HttpStatus.OK);
        ResponseEntity result = personRESTController.peopleOptions();

        assertEquals(result, expected);
    }


    @Test
    void removeEmailAddressExpectingEqualLists() {
        String emailOne = "tonyze@trump.com";
        String emailTwo = "tony@songoku.pt";
        String emailToRemove = "emailtoremove@clix.pt";
        List<String> emailList = new ArrayList<>();
        emailList.add(emailOne);
        emailList.add(emailTwo);
        String personID = "tony@personid.com";

        InputRemoveEmailDTO inputRemoveEmailDTO = new InputRemoveEmailDTO(emailToRemove, personID);
        OutputRemoveEmailDTO outputRemoveEmailDTO = new OutputRemoveEmailDTO();
        outputRemoveEmailDTO.setEmailAddresses(emailList);

        when(mockPersonInputDTOAssembler.toInputRemoveEmail(emailToRemove, personID)).thenReturn(inputRemoveEmailDTO);
        when(mockRemoveEmailService.removeEmail(inputRemoveEmailDTO)).thenReturn(outputRemoveEmailDTO);

        ResponseEntity expected = new ResponseEntity(outputRemoveEmailDTO, HttpStatus.OK);
        ResponseEntity result = personRESTController.removeEmailAddress(emailToRemove, personID);

        assertEquals(expected, result);

    }

    @Test
    void removeEmailAddressDifferentListsExpectingNotEquals() {
        String emailOne = "tonyze@trump.com";
        String emailTwo = "tony@songoku.pt";
        String emailToRemove = "emailtoremove@clix.pt";
        List<String> emailList = new ArrayList<>();
        emailList.add(emailOne);
        emailList.add(emailTwo);
        emailList.add(emailToRemove);
        String personID = "tony@personid.com";

        InputRemoveEmailDTO inputRemoveEmailDTO = new InputRemoveEmailDTO(emailToRemove, personID);
        OutputRemoveEmailDTO outputRemoveEmailDTO = new OutputRemoveEmailDTO();
        outputRemoveEmailDTO.setEmailAddresses(emailList);

        when(mockPersonInputDTOAssembler.toInputRemoveEmail(any(String.class), any(String.class))).thenReturn(new InputRemoveEmailDTO());
        when(mockRemoveEmailService.removeEmail(any(InputRemoveEmailDTO.class))).thenReturn(new OutputRemoveEmailDTO());

        ResponseEntity expected = new ResponseEntity(outputRemoveEmailDTO, HttpStatus.OK);
        ResponseEntity result = personRESTController.removeEmailAddress(emailToRemove, personID);

        assertNotEquals(expected, result);

    }

    @Test
    void removeEmailAddressTestExceptionCatch() {
        String emailOne = "tonyze@trump.com";
        String emailTwo = "tony@songoku.pt";
        String emailToRemove = "emailtoremove@clix.pt";
        List<String> emailList = new ArrayList<>();
        emailList.add(emailOne);
        emailList.add(emailTwo);
        emailList.add(emailToRemove);
        String personID = "tony@personid.com";

        InputRemoveEmailDTO inputRemoveEmailDTO = new InputRemoveEmailDTO(emailToRemove, personID);
        OutputRemoveEmailDTO outputRemoveEmailDTO = new OutputRemoveEmailDTO();
        outputRemoveEmailDTO.setEmailAddresses(emailList);

        when(mockPersonInputDTOAssembler.toInputRemoveEmail(emailToRemove, personID)).thenReturn(inputRemoveEmailDTO);
        when(mockRemoveEmailService.removeEmail(inputRemoveEmailDTO)).thenThrow(new IllegalStateException("Email is not registered to any person"));

        //assertThrows(EmailNotRegisteredException.class, () -> {personRESTController.removeEmailAddress(emailToRemove, personID);});
        ResponseEntity expected = new ResponseEntity("Error: Email is not registered to any person", HttpStatus.NOT_MODIFIED);
        ResponseEntity result = personRESTController.removeEmailAddress(emailToRemove, personID);

        assertEquals(expected.getBody(), result.getBody());

    }

}