package switchtwentytwenty.project.interfaceadapters.implrepositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.FamilyDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyJPA;
import switchtwentytwenty.project.datamodel.domainjpa.PersonIDJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.IFamilyRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.FamilyName;
import switchtwentytwenty.project.domain.valueobject.PersonID;
import switchtwentytwenty.project.domain.valueobject.RegistrationDate;
import switchtwentytwenty.project.exceptions.AccountNotRegisteredException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@RunWith(SpringRunner.class)
class FamilyRepositoryTest {

    @Mock
    IFamilyRepositoryJPA iFamilyRepositoryJPA;

    @Mock
    FamilyDataDomainAssembler familyDataDomainAssembler;

    @InjectMocks
    FamilyRepository familyRepository;

    @Captor
    ArgumentCaptor<FamilyIDJPA> captorFamilyIDJPA;

    // FamilyJPA

    String familyNameJPA = "Simpson";
    String registrationDateJPA = "12/12/2020";

    String adminEmailJPA = "email@email.com";
    PersonIDJPA adminIDJPA = new PersonIDJPA(adminEmailJPA);

    FamilyIDJPA familyIDJPA = new FamilyIDJPA("@admin@gmail.com");

    // End FamilyJPA

    // Family

    FamilyID familyID = new FamilyID("admin@gmail.com");
    String familyNameString = "Ribeiro";
    FamilyName familyName = new FamilyName(familyNameString);
    String date = "12/12/1990";
    RegistrationDate registrationDate = new RegistrationDate(date);
    String emailString = "admin@gmail.com";
    PersonID adminEmail = new PersonID(emailString);

    Family family = new Family(familyID, familyName, registrationDate, adminEmail);


    @Tag("US010")
    @Test
    void addFamily() {
        FamilyJPA familyJPA = new FamilyJPA(familyIDJPA, familyNameJPA, registrationDateJPA, adminIDJPA);

        when(familyDataDomainAssembler.toData(any(Family.class))).thenReturn(familyJPA);

        when(iFamilyRepositoryJPA.save(any(FamilyJPA.class))).thenReturn(familyJPA);

        assertDoesNotThrow(() -> familyRepository.add(family));
    }

    @Test
    void captorFindByID() {
        FamilyIDJPA expected = new FamilyIDJPA("@" + emailString);

        when(familyDataDomainAssembler.createFamilyIDJPA(any(FamilyID.class))).thenReturn(familyIDJPA);
        when(iFamilyRepositoryJPA.findById(any(FamilyIDJPA.class))).thenReturn(Optional.of(new FamilyJPA()));

        familyRepository.getByID(familyID);

        verify(iFamilyRepositoryJPA).findById(captorFamilyIDJPA.capture());

        FamilyIDJPA result = captorFamilyIDJPA.getValue();

        assertEquals(expected, result);
    }

    @Test
    void getByID() {
        Family expected = new Family(familyID, familyName, registrationDate, adminEmail);

        when(familyDataDomainAssembler.createFamilyIDJPA(any(FamilyID.class))).thenReturn(familyIDJPA);
        when(iFamilyRepositoryJPA.findById(any(FamilyIDJPA.class))).thenReturn(Optional.of(new FamilyJPA()));
        when(familyDataDomainAssembler.createAdminID(any(FamilyJPA.class))).thenReturn(adminEmail);
        when(familyDataDomainAssembler.createFamilyID(any(FamilyJPA.class))).thenReturn(familyID);
        when(familyDataDomainAssembler.createFamilyName(any(FamilyJPA.class))).thenReturn(familyName);
        when(familyDataDomainAssembler.createRegistrationDate(any(FamilyJPA.class))).thenReturn(registrationDate);

        Family result = familyRepository.getByID(familyID);
        assertEquals(expected, result);
    }

    @Test
    void getByIDTestThrowsExceptionWhenIDJPANotPresent() {
        FamilyID otherFamilyID = new FamilyID("otherFamily@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> familyRepository.getByID(otherFamilyID));
    }

    @DisplayName("Check if Family Exists - Do Not Throw - Family Exists")
    @Test
    void checkIfFamilyExistsDoNotThrow() {
        String stringFamilyID = "@family@id.com";

        FamilyID familyID = new FamilyID(stringFamilyID);

        FamilyIDJPA familyIDJPA = new FamilyIDJPA(stringFamilyID);

        when(familyDataDomainAssembler.createFamilyIDJPA(any(FamilyID.class))).thenReturn(familyIDJPA);
        when(iFamilyRepositoryJPA.existsFamilyJPAById(any(FamilyIDJPA.class))).thenReturn(true);

        assertDoesNotThrow(() -> familyRepository.checkIfFamilyExists(familyID));
    }

    @DisplayName("Check if Family Exists - Throws - Family Does Not Exists")
    @Test
    void checkIfFamilyExistsThrows() {
        String stringFamilyID = "@family@id.com";

        FamilyID familyID = new FamilyID(stringFamilyID);

        FamilyIDJPA familyIDJPA = new FamilyIDJPA(stringFamilyID);

        when(familyDataDomainAssembler.createFamilyIDJPA(any(FamilyID.class))).thenReturn(familyIDJPA);
        when(iFamilyRepositoryJPA.existsFamilyJPAById(any(FamilyIDJPA.class))).thenReturn(false);

        assertThrows(AccountNotRegisteredException.class, () -> familyRepository.checkIfFamilyExists(familyID));
    }
}