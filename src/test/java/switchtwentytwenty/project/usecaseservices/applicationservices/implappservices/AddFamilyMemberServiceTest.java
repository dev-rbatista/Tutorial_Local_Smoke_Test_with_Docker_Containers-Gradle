package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.authentication.DAOUser;
import switchtwentytwenty.project.authentication.JWTUserDetailsService;
import switchtwentytwenty.project.authentication.UserDTO;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.PersonID;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonDTODomainAssembler;
import switchtwentytwenty.project.dto.person.InputAddFamilyMemberDTO;
import switchtwentytwenty.project.dto.person.OutputPersonDTO;
import switchtwentytwenty.project.exceptions.InvalidNameException;
import switchtwentytwenty.project.exceptions.PersonAlreadyRegisteredException;
import switchtwentytwenty.project.usecaseservices.irepositories.IPersonRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
class AddFamilyMemberServiceTest {

    @Mock
    IPersonRepository personRepository;

    @Mock
    PersonDTODomainAssembler personDTODomainAssembler;

    @Mock
    JWTUserDetailsService jwtUserDetailsService;

    @Mock
    Person admin;

    @Mock
    Person familyMember;

    @Mock
    Person savedFamilyMember;

    @Mock
    OutputPersonDTO outputPersonDTO;

    @Mock
    DAOUser daoUser;

    @InjectMocks
    AddFamilyMemberService addFamilyMemberService;

    @Captor
    ArgumentCaptor<UserDTO> captor;



    // NEW DTO
    InputAddFamilyMemberDTO internalAddFamilyMemberDTO = new InputAddFamilyMemberDTO("tonyze@latinlover.com","jhakhdakj@latinas.com", "TonyZe", "10/10/1999", 123456789, 961962963, "Rua das Irma's Beleza e do Primo Flavio", "Gaia", "100", "4400", "password");
    InputAddFamilyMemberDTO internalAddFamilyMemberDTOInvalidName = new InputAddFamilyMemberDTO("tonyze@latinlover.com","jhakhdakj@latinas.com", null, "10/10/1999", 123456789, 961962963, "Rua das Irma's Beleza e do Primo Flavio", "Gaia", "100", "4400", "password");

    FamilyID familyID = new FamilyID("tonyze@latinlover.com");


    @DisplayName("Unit test of AddFamilyMemberService: Successfully added a person")
    @Test
    @Tag("US101")
    void addPersonSuccessDoesNotThrow() {

        Mockito.when(personRepository.getByID(any(PersonID.class))).thenReturn(admin);
        Mockito.when(admin.getFamilyID()).thenReturn(familyID);
        Mockito.when(personRepository.add(any(Person.class))).thenReturn(savedFamilyMember);
        Mockito.when(personDTODomainAssembler.toDTO(savedFamilyMember)).thenReturn(outputPersonDTO);
        Mockito.when(jwtUserDetailsService.save(any(UserDTO.class))).thenReturn(daoUser);

        assertDoesNotThrow(() -> addFamilyMemberService.addPerson(internalAddFamilyMemberDTO));
    }

    @Test
    void captorDAOUser() {
        Mockito.when(personRepository.getByID(any(PersonID.class))).thenReturn(admin);
        Mockito.when(admin.getFamilyID()).thenReturn(familyID);
        Mockito.when(personRepository.add(any(Person.class))).thenReturn(savedFamilyMember);
        Mockito.when(personDTODomainAssembler.toDTO(savedFamilyMember)).thenReturn(outputPersonDTO);
        Mockito.when(jwtUserDetailsService.save(any(UserDTO.class))).thenReturn(daoUser);

        addFamilyMemberService.addPerson(internalAddFamilyMemberDTO);

        verify(jwtUserDetailsService).save(captor.capture());

        UserDTO userDTO = captor.getValue();
        String expectedUsername = "jhakhdakj@latinas.com";
        String expectedPassword = "password";
        String expectedRole = "familyMember";

        String resultUsername = userDTO.getUsername();
        String resultPassowrd = userDTO.getPassword();
        String resultRole = userDTO.getRole();

        assertEquals(expectedUsername, resultUsername);
        assertEquals(expectedPassword, resultPassowrd);
        assertEquals(expectedRole, resultRole);
    }

    @DisplayName("Unit test of AddFamilyMemberService: Successfully added a person")
    @Test
    @Tag("US101")
    void addPersonSuccess() {

        Mockito.when(personRepository.getByID(any(PersonID.class))).thenReturn(admin);
        Mockito.when(admin.getFamilyID()).thenReturn(familyID);
        Mockito.when(personRepository.add(any(Person.class))).thenReturn(savedFamilyMember);
        Mockito.when(personDTODomainAssembler.toDTO(savedFamilyMember)).thenReturn(outputPersonDTO);
        Mockito.when(jwtUserDetailsService.save(any(UserDTO.class))).thenReturn(daoUser);

        OutputPersonDTO expected = outputPersonDTO;
        OutputPersonDTO result = addFamilyMemberService.addPerson(internalAddFamilyMemberDTO);

        assertEquals(expected, result);
    }



    @Test
    @Tag("US101")
    @DisplayName("Test failure where user is already registered")
    void addPersonFailAlreadyRegistered() {

        Mockito.when(personRepository.getByID(any(PersonID.class))).thenReturn(admin);
        Mockito.when(admin.getFamilyID()).thenReturn(familyID);
        Mockito.when(personRepository.add(any(Person.class))).thenThrow(PersonAlreadyRegisteredException.class);


        assertThrows(PersonAlreadyRegisteredException.class,() -> addFamilyMemberService.addPerson(internalAddFamilyMemberDTO));

    }


    @Test
    @DisplayName("Test fails when the person name is invalid and throws an InvalidNameException")
    void addPersonFailEmptyPassword() {

        Mockito.when(personRepository.getByID(any(PersonID.class))).thenReturn(admin);
        Mockito.when(admin.getFamilyID()).thenReturn(familyID);
        Mockito.when(personRepository.add(any(Person.class))).thenReturn(savedFamilyMember);
        Mockito.when(personDTODomainAssembler.toDTO(savedFamilyMember)).thenReturn(outputPersonDTO);
        Mockito.when(jwtUserDetailsService.save(any(UserDTO.class))).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,()-> addFamilyMemberService.addPerson(internalAddFamilyMemberDTO));

    }

    @Test
    @DisplayName("Test fails when the person name is invalid and throws an InvalidNameException")
    void addPersonFailInvalidValueObject() {

        Mockito.when(personRepository.getByID(any(PersonID.class))).thenReturn(admin);
        Mockito.when(admin.getFamilyID()).thenReturn(familyID);
        Mockito.when(personDTODomainAssembler.createName(internalAddFamilyMemberDTO)).thenThrow(InvalidNameException.class);

        assertThrows(InvalidNameException.class,()-> addFamilyMemberService.addPerson(internalAddFamilyMemberDTO));

    }

}