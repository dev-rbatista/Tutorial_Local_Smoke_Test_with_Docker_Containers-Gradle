package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.Relation;
import switchtwentytwenty.project.domain.valueobject.RelationDesignation;
import switchtwentytwenty.project.domain.valueobject.RelationID;
import switchtwentytwenty.project.dto.assemblers.implassemblers.FamilyDTODomainAssembler;
import switchtwentytwenty.project.dto.family.ChangeRelationDTO;
import switchtwentytwenty.project.dto.family.InputChangeRelationDTO;
import switchtwentytwenty.project.dto.family.OutputRelationDTO;
import switchtwentytwenty.project.usecaseservices.irepositories.IFamilyRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ChangeRelationServiceTest {

    @Mock
    IFamilyRepository familyRepository;

    @Mock
    Family family;

    @Mock
    Family updatedFamily;

    @Mock
    Relation updatedRelation;

    @Mock
    FamilyDTODomainAssembler familyDTODomainAssembler;

    @Mock
    OutputRelationDTO outputRelationDTO;

    @InjectMocks
    ChangeRelationService changeRelationService;

    String relationID = "4";
    String familyIDString = "@tonyze@latinlover.com";
    FamilyID familyID = new FamilyID(familyIDString);
    ChangeRelationDTO changeRelationDTO = new ChangeRelationDTO(relationID);
    InputChangeRelationDTO inputChangeRelationDTO = new InputChangeRelationDTO(changeRelationDTO, familyID.toString(), relationID);

    @DisplayName("Change relation service: success when changing a existent relation")
    @Test
    void changeRelationSuccess() {

        Mockito.when(familyRepository.getByID(any(FamilyID.class))).thenReturn(family);
        Mockito.when(family.changeRelation(any(RelationID.class), any(RelationDesignation.class))).thenReturn(updatedRelation);
        Mockito.when(familyRepository.add(any(Family.class))).thenReturn(updatedFamily);
        Mockito.when(familyDTODomainAssembler.toOutputRelationDTO(updatedRelation)).thenReturn(outputRelationDTO);

        OutputRelationDTO expected = outputRelationDTO;
        OutputRelationDTO result = changeRelationService.changeRelation(inputChangeRelationDTO);

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void changeRelationSuccessNoArgs() {
        ChangeRelationService changeRelationService = new ChangeRelationService();
        assertNotNull(changeRelationService);
    }

}