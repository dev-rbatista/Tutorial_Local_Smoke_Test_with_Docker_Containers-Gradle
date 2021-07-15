package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.authentication.DAOUser;
import switchtwentytwenty.project.authentication.JWTUserDetailsService;
import switchtwentytwenty.project.authentication.UserDTO;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.assemblers.implassemblers.FamilyDTODomainAssembler;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonDTODomainAssembler;
import switchtwentytwenty.project.dto.family.InputFamilyDTO;
import switchtwentytwenty.project.dto.family.OutputFamilyDTO;
import switchtwentytwenty.project.dto.person.InputPersonDTO;
import switchtwentytwenty.project.exceptions.InvalidNameException;
import switchtwentytwenty.project.exceptions.PersonAlreadyRegisteredException;
import switchtwentytwenty.project.usecaseservices.irepositories.IFamilyRepository;
import switchtwentytwenty.project.usecaseservices.irepositories.IPersonRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
class CreateFamilyServiceTest {

    @Mock
    IFamilyRepository familyRepository;

    @Mock
    IPersonRepository personRepository;

    @Mock
    JWTUserDetailsService jwtUserDetailsService;

    @Mock
    PersonDTODomainAssembler personDTODomainAssembler;

    @Mock
    FamilyDTODomainAssembler familyDTODomainAssembler;

    @Mock
    Person admin;

    @Mock
    Family family;

    @Mock
    DAOUser daoUser;

    @Mock
    InputFamilyDTO inputFamilyDTO;

    @Mock
    InputPersonDTO inputPersonDTO;

    @InjectMocks
    CreateFamilyService createFamilyService;

    @Captor
    ArgumentCaptor<UserDTO> captor;

    String familyName = "Silva";
    String familyID = "@admin@gmail.com";
    String adminID = "admin@gmail.com";
    String registrationDate = "12/5/1999";
    String name = "Margarida";
    String birthDate = "10/02/1978";
    int vat = 999333999;
    Integer phone = 931321321;
    String street = "Rua de Cima";
    String city = "Porto";
    String houseNumber = "64C";
    String zipCode = "4000-001";
    Name adminName = new Name(name);
    BirthDate adminBirthDate = new BirthDate(birthDate);
    VATNumber adminVat = new VATNumber(vat);
    PhoneNumber adminPhone = new PhoneNumber(phone);
    Address adminAddress = new Address(street,city,zipCode,houseNumber);


    @BeforeEach
    void setup(){
        Mockito.when(inputPersonDTO.unpackEmail()).thenReturn("admin@email.com");
    }

    @Test
    @Tag("US010")
    @DisplayName("createFamilyAndAdmin Test - Valid data doesn't throw exception")
    void createFamilyAndAddAdminTestValidData(){
        Mockito.when(personDTODomainAssembler.createName(any(InputPersonDTO.class))).thenReturn(new Name("Pedro"));
        Mockito.when(personDTODomainAssembler.createBirthDate(any(InputPersonDTO.class))).thenReturn(new BirthDate("12/12/1222"));
        Mockito.when(personDTODomainAssembler.createPersonID(any(InputPersonDTO.class))).thenReturn(new PersonID("antoniojose@apreciadordelatinas.com"));
        Mockito.when(personDTODomainAssembler.createVATNumber(any(InputPersonDTO.class))).thenReturn(new VATNumber(333333333));
        Mockito.when(personDTODomainAssembler.createPhoneNumber(any(InputPersonDTO.class))).thenReturn(new PhoneNumber(919999999));
        Mockito.when(personDTODomainAssembler.createAddress(any(InputPersonDTO.class))).thenReturn(new Address("rua","cidade","1234-123","69420"));

        Mockito.when(familyDTODomainAssembler.createFamilyName(any(InputFamilyDTO.class))).thenReturn(new FamilyName("Antunes"));
        Mockito.when(familyDTODomainAssembler.createRegistrationDate(any(InputFamilyDTO.class))).thenReturn(new RegistrationDate("12/12/1222"));
        Mockito.when(personRepository.add(any(Person.class))).thenReturn(admin);
        Mockito.when(familyRepository.add(any(Family.class))).thenReturn(family);
        Mockito.when(jwtUserDetailsService.save(any(UserDTO.class))).thenReturn(daoUser);
        OutputFamilyDTO outputFamilyDTOexpected = new OutputFamilyDTO(familyName,familyID,adminID,registrationDate);
        Mockito.when(familyDTODomainAssembler.toOutputFamilyDTO(any(Family.class))).thenReturn(outputFamilyDTOexpected);

        OutputFamilyDTO outputFamilyDTO = createFamilyService.createFamilyAndAddAdmin(inputFamilyDTO, inputPersonDTO);
        Assertions.assertNotNull(outputFamilyDTO);
    }

    @Test
    @Tag("US010")
    @DisplayName("createFamilyAndAdmin Test - Invalid person name, should throw exception")
    void createFamilyAndAddAdminTestInvalidName(){
        Mockito.when(personDTODomainAssembler.createName(any(InputPersonDTO.class))).thenThrow(InvalidNameException.class);

        Mockito.when(personRepository.add(any(Person.class))).thenReturn(admin);
        Mockito.when(familyRepository.add(any(Family.class))).thenReturn(family);
        Mockito.when(familyDTODomainAssembler.toOutputFamilyDTO(any(Family.class))).thenReturn(new OutputFamilyDTO(familyName,familyID,adminID,registrationDate));

        assertThrows(InvalidNameException.class,() -> createFamilyService.createFamilyAndAddAdmin(inputFamilyDTO, inputPersonDTO));
    }

    @Test
    @Tag("US010")
    @DisplayName("createFamilyAndAdmin Test - Person already registered throw exception")
    void createFamilyAndAddAdminTestPersonAlreadyRegistered(){
        Mockito.when(inputPersonDTO.unpackEmail()).thenReturn("admin@gmail.com");

        Mockito.when(personDTODomainAssembler.createName(any(InputPersonDTO.class))).thenReturn(adminName);
        Mockito.when(personDTODomainAssembler.createBirthDate(any(InputPersonDTO.class))).thenReturn(adminBirthDate);
        Mockito.when(personDTODomainAssembler.createVATNumber(any(InputPersonDTO.class))).thenReturn(adminVat);
        Mockito.when(personDTODomainAssembler.createPhoneNumber(any(InputPersonDTO.class))).thenReturn(adminPhone);
        Mockito.when(personDTODomainAssembler.createAddress(any(InputPersonDTO.class))).thenReturn(adminAddress);

        Mockito.doThrow(PersonAlreadyRegisteredException.class).when(personRepository).add(any());
        Mockito.when(familyRepository.add(any(Family.class))).thenReturn(family);
        Mockito.when(familyDTODomainAssembler.toOutputFamilyDTO(any(Family.class))).thenReturn(new OutputFamilyDTO(familyName,familyID,adminID,registrationDate));

        assertThrows(PersonAlreadyRegisteredException.class,() -> createFamilyService.createFamilyAndAddAdmin(inputFamilyDTO, inputPersonDTO));
    }


    @Test
    void captorDAOUser() {
        Mockito.when(personDTODomainAssembler.createName(any(InputPersonDTO.class))).thenReturn(new Name("Pedro"));
        Mockito.when(personDTODomainAssembler.createBirthDate(any(InputPersonDTO.class))).thenReturn(new BirthDate("12/12/1222"));
        Mockito.when(personDTODomainAssembler.createPersonID(any(InputPersonDTO.class))).thenReturn(new PersonID("antoniojose@apreciadordelatinas.com"));
        Mockito.when(personDTODomainAssembler.createVATNumber(any(InputPersonDTO.class))).thenReturn(new VATNumber(333333333));
        Mockito.when(personDTODomainAssembler.createPhoneNumber(any(InputPersonDTO.class))).thenReturn(new PhoneNumber(919999999));
        Mockito.when(personDTODomainAssembler.createAddress(any(InputPersonDTO.class))).thenReturn(new Address("rua","cidade","1234-123","69420"));
        Mockito.when(inputPersonDTO.unpackPassword()).thenReturn("password");

        Mockito.when(familyDTODomainAssembler.createFamilyName(any(InputFamilyDTO.class))).thenReturn(new FamilyName("Antunes"));
        Mockito.when(familyDTODomainAssembler.createRegistrationDate(any(InputFamilyDTO.class))).thenReturn(new RegistrationDate("12/12/1222"));
        Mockito.when(personRepository.add(any(Person.class))).thenReturn(admin);
        Mockito.when(familyRepository.add(any(Family.class))).thenReturn(family);
        Mockito.when(jwtUserDetailsService.save(any(UserDTO.class))).thenReturn(daoUser);

        OutputFamilyDTO outputFamilyDTOexpected = new OutputFamilyDTO(familyName,familyID,adminID,registrationDate);
        Mockito.when(familyDTODomainAssembler.toOutputFamilyDTO(any(Family.class))).thenReturn(outputFamilyDTOexpected);

        createFamilyService.createFamilyAndAddAdmin(inputFamilyDTO, inputPersonDTO);

        verify(jwtUserDetailsService).save(captor.capture());

        UserDTO userDTO = captor.getValue();
        String expectedUsername = "admin@email.com";
        String expectedPassword = "password";
        String expectedRole = "familyAdministrator";

        String resultUsername = userDTO.getUsername();
        String resultPassowrd = userDTO.getPassword();
        String resultRole = userDTO.getRole();

        assertEquals(expectedUsername, resultUsername);
        assertEquals(expectedPassword, resultPassowrd);
        assertEquals(expectedRole, resultRole);
    }
}