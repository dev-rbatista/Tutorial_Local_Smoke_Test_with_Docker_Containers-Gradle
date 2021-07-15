package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.assemblers.implassemblers.FamilyDTODomainAssembler;
import switchtwentytwenty.project.dto.family.InputRelationDTO;
import switchtwentytwenty.project.dto.family.OutputRelationDTO;
import switchtwentytwenty.project.usecaseservices.irepositories.IFamilyRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CreateRelationServiceTest {

    @Mock
    IFamilyRepository familyRepository;
    @Mock
    FamilyDTODomainAssembler familyDTODomainAssembler;
    @Mock
    Family family;
    @Mock
    Family savedFamily;
    @Mock
    Relation savedRelation;
    @Mock
    OutputRelationDTO outputRelationDTO;


    @InjectMocks
    CreateRelationService createRelationService;


    String personIDOneString = "tonyze@latinlover.com";
    String personIDTwoString = "mariaze@latinlover.com";
    String designationString = "amante";
    String familyIDString = "@tonyze@latinlover.com";
    FamilyID familyID = new FamilyID(familyIDString);
    InputRelationDTO inputRelationDTO = new InputRelationDTO(personIDOneString, personIDTwoString, designationString, familyIDString);

    @DisplayName("Create relation service: Successfully add relation to family")
    @Test
    void createRelationSuccess(){
        Mockito.when(familyDTODomainAssembler.personIDOneToDomain(any(InputRelationDTO.class))).thenReturn(new PersonID(personIDOneString));
        Mockito.when(familyDTODomainAssembler.personIDTwoToDomain(any(InputRelationDTO.class))).thenReturn(new PersonID(personIDTwoString));
        Mockito.when(familyDTODomainAssembler.relationDesignationToDomain(any(InputRelationDTO.class))).thenReturn(new RelationDesignation(designationString));
        Mockito.when(familyDTODomainAssembler.familyIDToDomain(any(InputRelationDTO.class))).thenReturn(familyID);
        Mockito.when(familyRepository.getByID(any(FamilyID.class))).thenReturn(family);
        Mockito.doNothing().when(family).addRelation(any(Relation.class));
        Mockito.when(familyRepository.add(any(Family.class))).thenReturn(savedFamily);
        Mockito.when(savedFamily.getRelationByID(any(RelationID.class))).thenReturn(savedRelation);
        Mockito.when(familyDTODomainAssembler.toOutputRelationDTO(savedRelation)).thenReturn(outputRelationDTO);

        OutputRelationDTO expected = outputRelationDTO;
        OutputRelationDTO result = createRelationService.createRelation(inputRelationDTO);

        assertEquals(expected, result);
    }
    @DisplayName("Create relation service: Successfully add relation to family")
    @Test
    void createRelationSuccessDoesNotThrow(){
        Mockito.when(familyDTODomainAssembler.personIDOneToDomain(any(InputRelationDTO.class))).thenReturn(new PersonID(personIDOneString));
        Mockito.when(familyDTODomainAssembler.personIDTwoToDomain(any(InputRelationDTO.class))).thenReturn(new PersonID(personIDTwoString));
        Mockito.when(familyDTODomainAssembler.relationDesignationToDomain(any(InputRelationDTO.class))).thenReturn(new RelationDesignation(designationString));
        Mockito.when(familyDTODomainAssembler.familyIDToDomain(any(InputRelationDTO.class))).thenReturn(familyID);
        Mockito.when(familyRepository.getByID(any(FamilyID.class))).thenReturn(family);
        Mockito.doNothing().when(family).addRelation(any(Relation.class));
        Mockito.when(familyRepository.add(any(Family.class))).thenReturn(savedFamily);
        Mockito.when(savedFamily.getRelationByID(any(RelationID.class))).thenReturn(savedRelation);
        Mockito.when(familyDTODomainAssembler.toOutputRelationDTO(savedRelation)).thenReturn(outputRelationDTO);

        assertDoesNotThrow(() -> createRelationService.createRelation(inputRelationDTO));
    }
    @DisplayName("Create relation service: Fail to  add relation to family because it already exists")
    @Test
    void createRelationFailureRelationAlreadyRegistered(){
        Mockito.when(familyDTODomainAssembler.personIDOneToDomain(any(InputRelationDTO.class))).thenReturn(new PersonID(personIDOneString));
        Mockito.when(familyDTODomainAssembler.personIDTwoToDomain(any(InputRelationDTO.class))).thenReturn(new PersonID(personIDTwoString));
        Mockito.when(familyDTODomainAssembler.relationDesignationToDomain(any(InputRelationDTO.class))).thenReturn(new RelationDesignation(designationString));
        Mockito.when(familyDTODomainAssembler.familyIDToDomain(any(InputRelationDTO.class))).thenReturn(familyID);
        Mockito.when(familyRepository.getByID(any(FamilyID.class))).thenReturn(family);
        Mockito.doThrow(IllegalArgumentException.class).when(family).addRelation(any(Relation.class));

        assertThrows(IllegalArgumentException.class,() -> createRelationService.createRelation(inputRelationDTO));
    }
}