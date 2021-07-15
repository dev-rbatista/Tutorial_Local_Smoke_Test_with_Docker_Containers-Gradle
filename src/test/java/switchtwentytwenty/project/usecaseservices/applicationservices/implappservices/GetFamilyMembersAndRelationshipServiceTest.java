package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.assemblers.implassemblers.FamilyDTODomainAssembler;
import switchtwentytwenty.project.dto.family.FamilyMemberAndRelationsListDTO;
import switchtwentytwenty.project.dto.family.OutputPersonRelationDTO;
import switchtwentytwenty.project.dto.person.FamilyMemberAndRelationsDTO;
import switchtwentytwenty.project.usecaseservices.irepositories.IFamilyRepository;
import switchtwentytwenty.project.usecaseservices.irepositories.IPersonRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class GetFamilyMembersAndRelationshipServiceTest {


    @Mock
    FamilyDTODomainAssembler familyDTODomainAssembler;

    @Mock
    IFamilyRepository familyRepository;

    @Mock
    IPersonRepository personRepository;

    @InjectMocks
    GetFamilyMembersAndRelationshipService getFamilyMembersAndRelationshipService;


    String familyIDString = new String("@tonyze@latinlover.com");
    FamilyName name = new FamilyName("tony");
    RegistrationDate registrationDate = new RegistrationDate("04/20/0420");
    PersonID adminEmail = new PersonID("tonyze@totiladmin.com");
    PersonID nonAdminEmail = new PersonID("tonyze@notadmin.com");
    Name memberAName = new Name(name.toString());
    BirthDate birthDate = new BirthDate("01/01/1200");
    VATNumber vatNumber = new VATNumber(123456789);
    FamilyID familyID = new FamilyID(familyIDString);
    Person memberA = new Person(adminEmail, memberAName, birthDate, vatNumber, familyID);
    Person memberB = new Person(nonAdminEmail, memberAName, birthDate, vatNumber, familyID);
    RelationDesignation relationDesignation = new RelationDesignation("relassssssão");
    Relation relation = new Relation(memberA.id(), memberB.id(), relationDesignation);
    Family family = new Family(familyID, name, registrationDate, adminEmail);
    List<Person> memberList = new ArrayList<>();
    OutputPersonRelationDTO relationDTO = new OutputPersonRelationDTO(memberA.id().toString(), memberB.id().toString(), relationDesignation.toString(), "3");
    OutputPersonRelationDTO relationDTOTwo = new OutputPersonRelationDTO(memberA.id().toString(), memberB.id().toString(), relationDesignation.toString(), "3");
    List<OutputPersonRelationDTO> outputPersonRelationDTOList = new ArrayList<>();


    @Test
    void getFamilyMembersAndRelations() {
        family.addRelation(relation);
        memberList.add(memberA);
        memberList.add(memberB);
        FamilyMemberAndRelationsListDTO expectedList = new FamilyMemberAndRelationsListDTO();


        outputPersonRelationDTOList.add(relationDTO);
        outputPersonRelationDTOList.add(relationDTOTwo);
        FamilyMemberAndRelationsDTO memberADTO = new FamilyMemberAndRelationsDTO(memberAName.toString(), memberA.id().toString(), outputPersonRelationDTOList);
        FamilyMemberAndRelationsDTO memberBDTO = new FamilyMemberAndRelationsDTO(memberAName.toString(), memberB.id().toString(), outputPersonRelationDTOList);

        expectedList.addDTO(memberADTO);
        expectedList.addDTO(memberBDTO);


        Mockito.when(familyDTODomainAssembler.familyIDToDomain(familyIDString)).thenReturn(familyID);
        Mockito.when(familyRepository.getByID(familyID)).thenReturn(family);
        Mockito.when(personRepository.findAllByFamilyID(familyID)).thenReturn(memberList);


        //Mock de createFamilyMemberAndRelationDTO
        Mockito.when(familyDTODomainAssembler.createFamilyMemberAndRelationsDTO(memberA, family)).thenReturn(memberADTO);
        Mockito.when(familyDTODomainAssembler.createFamilyMemberAndRelationsDTO(memberB, family)).thenReturn(memberBDTO);
        // Mockito.when(familyDTODomainAssembler.createFamilyMemberAndRelationsDTO(any(Person.class),any(Family.class))).thenReturn(memberADTO).thenReturn(memberBDTO);

        FamilyMemberAndRelationsListDTO resultList = new FamilyMemberAndRelationsListDTO();

        resultList = getFamilyMembersAndRelationshipService.getFamilyMembersAndRelations(familyID.toString());

        assertEquals(expectedList, resultList);
        List<FamilyMemberAndRelationsDTO> innerList = resultList.getFamilyMemberAndRelationsDTO();
        assertNotNull(innerList);
        assertFalse(innerList.isEmpty());
    }

   @Test
    void getFamilyMembersAndRelationsExpectingNotEquals() {
        family.addRelation(relation);
        memberList.add(memberA);
        memberList.add(memberB);
        FamilyMemberAndRelationsListDTO expectedList = new FamilyMemberAndRelationsListDTO();


        outputPersonRelationDTOList.add(relationDTO);
        outputPersonRelationDTOList.add(relationDTOTwo);
        FamilyMemberAndRelationsDTO memberADTO = new FamilyMemberAndRelationsDTO(memberAName.toString(), memberA.id().toString(), outputPersonRelationDTOList);
        FamilyMemberAndRelationsDTO memberBDTO = new FamilyMemberAndRelationsDTO(memberAName.toString(), memberB.id().toString(), outputPersonRelationDTOList);

        expectedList.addDTO(memberADTO);
        //expectedList.addDTO(memberBDTO);


        //Ver anyString
        Mockito.when(familyDTODomainAssembler.familyIDToDomain(anyString())).thenReturn(familyID);
        Mockito.when(familyRepository.getByID(familyID)).thenReturn(family);
        Mockito.when(personRepository.findAllByFamilyID(familyID)).thenReturn(memberList);


        //Mock de createFamilyMemberAndRelationDTO
        Mockito.when(familyDTODomainAssembler.createFamilyMemberAndRelationsDTO(memberA, family)).thenReturn(memberADTO);
        Mockito.when(familyDTODomainAssembler.createFamilyMemberAndRelationsDTO(memberB, family)).thenReturn(memberBDTO);

        FamilyMemberAndRelationsListDTO resultList = new FamilyMemberAndRelationsListDTO();

        FamilyID familyIDTwo = new FamilyID("@to@to.com");
        resultList = getFamilyMembersAndRelationshipService.getFamilyMembersAndRelations(familyIDTwo.getId());


        assertNotEquals(expectedList, resultList);
}



}