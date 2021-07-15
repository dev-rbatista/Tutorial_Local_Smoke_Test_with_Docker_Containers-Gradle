package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.FamilyName;
import switchtwentytwenty.project.domain.valueobject.PersonID;
import switchtwentytwenty.project.domain.valueobject.RegistrationDate;
import switchtwentytwenty.project.dto.assemblers.implassemblers.FamilyDTODomainAssembler;
import switchtwentytwenty.project.dto.family.OutputFamilyDTO;
import switchtwentytwenty.project.usecaseservices.irepositories.IFamilyRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class GetFamilyDataServiceTest {


    @Mock
    IFamilyRepository familyRepository;

    @Mock
    FamilyDTODomainAssembler familyDTODomainAssembler;

    @InjectMocks
    GetFamilyDataService getFamilyDataService;


    @Test
    void getFamilyData() {

        FamilyID familyID = new FamilyID("@fam@id.com");
        Family family = new Family(familyID, new FamilyName("name"), new RegistrationDate("12/12/2000"), new PersonID("fam@id.com"));
        Mockito.when(familyDTODomainAssembler.familyIDToDomain("@fam@id.com")).thenReturn(familyID);
        Mockito.when(familyRepository.getByID(any(FamilyID.class))).thenReturn(family);
        OutputFamilyDTO expected = new OutputFamilyDTO("name", "@fam@id.com", "fam@id.com", "12/12/2000");
        Mockito.when(familyDTODomainAssembler.toOutputFamilyDTO(family)).thenReturn(expected);
        OutputFamilyDTO result = getFamilyDataService.getFamilyData("@fam@id.com");
        assertEquals(expected, result);
        assertNotNull(result);
    }
}