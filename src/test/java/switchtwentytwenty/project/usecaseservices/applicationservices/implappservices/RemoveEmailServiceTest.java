package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.EmailAddress;
import switchtwentytwenty.project.domain.valueobject.PersonID;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonDTODomainAssembler;
import switchtwentytwenty.project.dto.person.InputRemoveEmailDTO;
import switchtwentytwenty.project.dto.person.OutputRemoveEmailDTO;
import switchtwentytwenty.project.exceptions.EmailNotRegisteredException;
import switchtwentytwenty.project.usecaseservices.irepositories.IPersonRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RemoveEmailServiceTest {

    @Mock
    IPersonRepository mockPersonRepository;

    @Mock
    PersonDTODomainAssembler mockPersonDTODomainAssembler;

    @Mock
    InputRemoveEmailDTO mockInputRemoveEmailDTO;

    @Mock
    Person mockSavedPerson;

    @Mock
    OutputRemoveEmailDTO expected;

    @Mock
    Person mockPerson;

    @InjectMocks
    RemoveEmailService removeEmailService;

    String emailString = "tonyze@latinlover.com";
    String emailToDeleteString = "tonyze69@gmail.com";

    PersonID personID = new PersonID(emailString);

    @BeforeEach()
    void setup() {
        Mockito.when(mockInputRemoveEmailDTO.getEmail()).thenReturn("tonyze69@gmail.com");
        Mockito.when(mockInputRemoveEmailDTO.getPersonID()).thenReturn("tonyze@latinlover.com");
    }

    @DisplayName("Remove Test Success")
    @Test
    void removeEmailSuccess() {

        Mockito.when(mockPersonRepository.getByID(any(PersonID.class))).thenReturn(mockPerson);
        Mockito.doNothing().when(mockPerson).removeEmail(any(EmailAddress.class));
        Mockito.when(mockPersonRepository.updatePerson(mockPerson)).thenReturn(mockSavedPerson);
        Mockito.when(mockPersonDTODomainAssembler.toOutputRemoveEmailDTO(mockSavedPerson.getEmails())).thenReturn(expected);

        OutputRemoveEmailDTO result = removeEmailService.removeEmail(mockInputRemoveEmailDTO);

        assertEquals(expected, result);
    }

    @DisplayName("Fail to remove. Person not exists")
    @Test
    void removeEmailFail() {
        Mockito.doThrow(EmailNotRegisteredException.class).when(mockPersonRepository).getByID(personID);

        assertThrows(EmailNotRegisteredException.class, () -> removeEmailService.removeEmail(mockInputRemoveEmailDTO));
    }

}