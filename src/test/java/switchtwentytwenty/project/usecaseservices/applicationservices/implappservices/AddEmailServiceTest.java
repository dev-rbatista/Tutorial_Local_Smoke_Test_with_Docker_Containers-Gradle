package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.EmailAddress;
import switchtwentytwenty.project.domain.valueobject.PersonID;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonDTODomainAssembler;
import switchtwentytwenty.project.dto.person.AddEmailDTO;
import switchtwentytwenty.project.dto.person.InputEmailDTO;
import switchtwentytwenty.project.dto.person.OutputEmailDTO;
import switchtwentytwenty.project.exceptions.EmailAlreadyRegisteredException;
import switchtwentytwenty.project.exceptions.InvalidEmailException;
import switchtwentytwenty.project.usecaseservices.irepositories.IPersonRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringRunner.class)
class AddEmailServiceTest {

    @Mock
    IPersonRepository mockPersonRepository;

    @Mock
    Person tonyZe;

    @Mock
    PersonDTODomainAssembler mockPersonDTODomainAssembler;

    @Mock
    InputEmailDTO mockInternalEmailDTO;

    @InjectMocks
    AddEmailService addEmailService;


    PersonID IDTonyZe = new PersonID("tonyZe@gmail.com");
    EmailAddress tonyEmail = new EmailAddress("tonyZe@gmail.com");
    AddEmailDTO emailDTO = new AddEmailDTO(tonyEmail.toString());

    //AddEmailDTO tonyDTO = new AddEmailDTO("tonyZe@gmail.com","tonyZe@gmail.com");
    //AddEmailDTO tonyInvalidPersonID = new AddEmailDTO("tony.com", "tonyZe@gmail.com");
    //AddEmailDTO tonyInvalidEmail = new AddEmailDTO("tonyZe@gmail.com","tony.com");

    @BeforeEach
    void setup() {
        Mockito.when(mockInternalEmailDTO.unpackUserID()).thenReturn("tonyze@latinlover.com");
        Mockito.when(mockInternalEmailDTO.unpackEmail()).thenReturn("tonyze@addemail.com");
    }

    @DisplayName("Successfully add email.")
    @Test
    void SucceedToAddEmail() {
        OutputEmailDTO outputEmailDTO = new OutputEmailDTO("tonyze@addemail.com");

        Mockito.when(mockPersonRepository.getByID(any(PersonID.class))).thenReturn(tonyZe);
        Mockito.doNothing().when(tonyZe).addEmail(any(EmailAddress.class));
        Mockito.when(mockPersonRepository.updatePerson(any(Person.class))).thenReturn(tonyZe);
        Mockito.when(mockPersonDTODomainAssembler.toEmailDTO(any(Person.class))).thenReturn(outputEmailDTO);

        OutputEmailDTO result = addEmailService.addEmail(mockInternalEmailDTO);

        assertEquals(outputEmailDTO,result);
    }

    @DisplayName("Fail to add email when an Value Object is invalid")
    @Test
    void failToAddEmailWhenAnExceptionIsThrownBecauseOfInvalidObject() {
        Mockito.when(mockPersonRepository.getByID(any(PersonID.class))).thenReturn(tonyZe);
        Mockito.doThrow(InvalidEmailException.class).when(tonyZe).addEmail(any(EmailAddress.class));
        Mockito.when(mockPersonRepository.updatePerson(tonyZe)).thenReturn(tonyZe);

        assertThrows(InvalidEmailException.class, () -> addEmailService.addEmail(mockInternalEmailDTO));
    }

    @DisplayName("Fail to add email when email is already registered ")
    @Test
    void failToAddEmailWhenEmailIsAlreadyRegistered() {

        Mockito.when(mockPersonRepository.getByID(any(PersonID.class))).thenReturn(tonyZe);
        Mockito.doThrow(EmailAlreadyRegisteredException.class).when(tonyZe).addEmail(any(EmailAddress.class));
        Mockito.when(mockPersonRepository.updatePerson(tonyZe)).thenReturn(tonyZe);

        assertThrows(EmailAlreadyRegisteredException.class, () -> addEmailService.addEmail(mockInternalEmailDTO));
    }



    /*
    //TODO: adicionar metodo update no FamilyRepository (update sem fazer o check se a pessoa existe, o .add faz isso). Corrigir teste
    @Test
    @Disabled
    void addEmailSuccess() {
        Mockito.when(mockPersonRepository.getByID(IDTonyZe)).thenReturn(tonyZe);
        Mockito.doNothing().when(tonyZe).addEmail(tonyEmail);
        Mockito.when(mockPersonRepository.add(tonyZe)).thenReturn(tonyZe);

        assertDoesNotThrow(()->addEmailService.addEmail(tonyDTO));
    }

    @Test
    void addEmailFailWhenPersonIDFormatIsInvalid() {
        assertThrows(InvalidEmailException.class,()-> addEmailService.addEmail(tonyInvalidPersonID));
    }

    @Test
    void addEmailFailWhenEmailToAddHasInvalidFormat() {
        assertThrows(InvalidEmailException.class,()-> addEmailService.addEmail(tonyInvalidEmail));
    }

    @Test
    void addEmailFailWhenLoggedUserIDIsNotRegisteredInTheApplication() {
        Mockito.when(mockPersonRepository.getByID(IDTonyZe)).thenThrow(EmailNotRegisteredException.class);

        assertThrows(EmailNotRegisteredException.class,()-> addEmailService.addEmail(tonyDTO));
    }

    @Test
    void addEmailFailWhenEmailIsAlreadyRegisteredInThePerson() {
        Mockito.when(mockPersonRepository.getByID(IDTonyZe)).thenReturn(tonyZe);
        Mockito.doThrow(EmailAlreadyRegisteredException.class).when(tonyZe).addEmail(tonyEmail);

        assertThrows(EmailAlreadyRegisteredException.class,()-> addEmailService.addEmail(tonyDTO));
    }

     */


}