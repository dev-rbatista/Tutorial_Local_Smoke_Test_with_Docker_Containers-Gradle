package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonDTODomainAssembler;
import switchtwentytwenty.project.dto.person.InputGetProfileDTO;
import switchtwentytwenty.project.dto.person.OutputPersonDTO;
import switchtwentytwenty.project.exceptions.EmailNotRegisteredException;
import switchtwentytwenty.project.usecaseservices.irepositories.IPersonRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetFamilyMemberProfileServiceTest {

    @Mock
    IPersonRepository personRepository;

    @Mock
    PersonDTODomainAssembler mockPersonToDTO;

    @InjectMocks
    GetFamilyMemberProfileService getFamilyMemberProfileService;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @SneakyThrows
    @AfterEach
    void tearDown() throws Exception{
        closeable.close();
    }


    @DisplayName("Get Profile - Success")
    @Test
    void getFamilyMemberProfileTest() {
        // Arrange

        InputGetProfileDTO inputGetProfileDTO = new InputGetProfileDTO("tonyze@gmail.com");

        // PersonID
        PersonID personID = new PersonID("tonyze@gmail.com");

        // Person
        final String VALIDNAME = "TonyZe";
        final String VALIDEMAIL = "tonyze@gmail.com";
        final int VALIDVATNUMBER = 999999999;
        final Integer VALIDPHONENUMBER = 916969696;
        final String VALIDSTREET = "Rua";
        final String VALIDCITY = "Ermesinde";
        final String VALIDZIPCODE = "4700-111";
        final String VALIDADDRESSNUMBER = "69";
        final String VALIDBIRTHDATE = "01/03/1990";

        Name tonyZeName = new Name(VALIDNAME);
        BirthDate tonyZeBirthDate = new BirthDate(VALIDBIRTHDATE);
        PersonID tonyZeEmail = new PersonID(VALIDEMAIL);
        VATNumber tonyZeVat = new VATNumber(VALIDVATNUMBER);
        PhoneNumber tonyZePhone = new PhoneNumber(VALIDPHONENUMBER);
        Address tonyZeAddress = new Address(VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER);
        FamilyID familyID = new FamilyID(VALIDEMAIL);

        Person person = new Person(tonyZeName, tonyZeBirthDate, tonyZeEmail, tonyZeVat, tonyZePhone, tonyZeAddress, familyID);

        // OutputPersonDTO

        List<String> emails = Collections.emptyList();

        List<Integer> phones = new ArrayList<>();
        phones.add(912837465);

        OutputPersonDTO outputPersonDTO = new OutputPersonDTO(VALIDEMAIL, VALIDNAME, VALIDBIRTHDATE, emails, phones, "999999999", VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER, VALIDEMAIL);

        // Expected OutputPersonDTO

        List<String> expectedEmails = Collections.emptyList();

        List<Integer> expectedPhones = new ArrayList<>();
        expectedPhones.add(912837465);

        OutputPersonDTO expectedOutputPersonDTO = new OutputPersonDTO(VALIDEMAIL, VALIDNAME, VALIDBIRTHDATE, expectedEmails, expectedPhones, "999999999", VALIDSTREET, VALIDCITY, VALIDZIPCODE, VALIDADDRESSNUMBER, VALIDEMAIL);

        // Mocking

        when(mockPersonToDTO.createPersonID(any(InputGetProfileDTO.class))).thenReturn(personID);

        when(personRepository.getByID(any(PersonID.class))).thenReturn(person);

        when(mockPersonToDTO.toDTO(person)).thenReturn(outputPersonDTO);

        // Act

        OutputPersonDTO resultOutputPersonDTO = getFamilyMemberProfileService.getFamilyMemberProfile(inputGetProfileDTO);

        // Assert

        assertEquals(expectedOutputPersonDTO, resultOutputPersonDTO);
    }

    @DisplayName("Get Profile - Failure - throw EmailNotRegisteredException")
    @Test
    void getFamilyMemberProfileTestFailure() {
        // Arrange

        InputGetProfileDTO inputGetProfileDTO = new InputGetProfileDTO("tonyze@gmail.com");

        // PersonID
        PersonID personID = new PersonID("tonyze@gmail.com");


        // Mocking

        when(mockPersonToDTO.createPersonID(any(InputGetProfileDTO.class))).thenReturn(personID);

        when(personRepository.getByID(any(PersonID.class))).thenThrow(EmailNotRegisteredException.class);

        // Act & Assert

        assertThrows(EmailNotRegisteredException.class, () -> getFamilyMemberProfileService.getFamilyMemberProfile(inputGetProfileDTO));


    }
}