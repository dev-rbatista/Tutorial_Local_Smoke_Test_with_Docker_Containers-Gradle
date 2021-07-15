package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.authentication.JWTUserDetailsService;
import switchtwentytwenty.project.authentication.UserDao;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.FamilyDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyJPA;
import switchtwentytwenty.project.datamodel.domainjpa.PersonIDJPA;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.assemblers.implassemblers.FamilyDTODomainAssembler;
import switchtwentytwenty.project.dto.assemblers.implassemblers.RelationInputDTOAssembler;
import switchtwentytwenty.project.dto.family.ChangeRelationDTO;
import switchtwentytwenty.project.dto.family.InputChangeRelationDTO;
import switchtwentytwenty.project.dto.family.OutputRelationDTO;
import switchtwentytwenty.project.usecaseservices.irepositories.IFamilyRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;

@SpringBootTest
@RunWith(SpringRunner.class)
class ChangeRelationServiceIT {


    @Autowired
    RelationInputDTOAssembler relationInputDTOAssembler;

    @Mock
    IFamilyRepository familyRepository;

    @Autowired
    FamilyDataDomainAssembler familyDataDomainAssembler;

    FamilyDTODomainAssembler familyDTODomainAssembler = new FamilyDTODomainAssembler();

    @InjectMocks
    ChangeRelationService changeRelationService = new ChangeRelationService(familyRepository, familyDTODomainAssembler);

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserDao userDao;

    @InjectMocks
    JWTUserDetailsService jwtUserDetailsService;


    //Output Relation DTO

    final String memberOneID = "TonyZe";
    final  String memberTwoID = "Katia";
    final String relationDesignation = "BFF";
    final String relationID = "123";

    OutputRelationDTO updatedRelation = new OutputRelationDTO(memberOneID, memberTwoID, relationDesignation, relationID);

    // Family JPA
    FamilyIDJPA familyIDJPA = new FamilyIDJPA("Raimundos");
    FamilyJPA familyJPA = new FamilyJPA();


    //create a Family
    PersonID personOneID = new PersonID("tonyze@latinlover.com");
    PersonID personTwoID = new PersonID("katia@hotlatina.com");
    final String name = "Raimundos";
    final FamilyName familyName = new FamilyName(name);
    final String familyIDString = "@dimitri@crazyrussian.com";
    RegistrationDate familyRegDate = new RegistrationDate("12/12/2020");
    FamilyID familyID = new FamilyID(familyIDString);
    List<Relation> relations = new ArrayList<>();
    List<Relation> relations2 = new ArrayList<>();


    //create a Relation between 2 Family members
    RelationDesignation relationOneDesignation = new RelationDesignation("BFF");
    RelationDesignation relationTwoDesignation = new RelationDesignation("Lovers");
    RelationID relationOneID = new RelationID(123);
    Relation relationOne = new Relation(personOneID, personTwoID, relationOneDesignation, relationOneID);
    Relation relationTwo = new Relation(personOneID, personTwoID, relationTwoDesignation, relationOneID);


    @Test
    void something() {

        //add relation to Family
        relations.add(relationOne);
        relations2.add(relationTwo);
        Family family = new Family(familyID,familyName, familyRegDate, personOneID, relations );
        Family family2 = new Family(familyID,familyName, familyRegDate, personOneID, relations2 );

        //add family with relation to the repo
        //familyRepository.add(family);

        //create new relation to update the existent relation
        RelationDesignation newRelationOneDesignation = new RelationDesignation("Lovers");
        ChangeRelationDTO changeRelationDTO = new ChangeRelationDTO(newRelationOneDesignation.toString());
        RelationID newRelationID = new RelationID(123);
        InputChangeRelationDTO inputChangeRelationDTO = new InputChangeRelationDTO(changeRelationDTO, familyID.toString(), newRelationID.toString());

        //create FamilyJPA
        PersonIDJPA adminIDJPA = new PersonIDJPA(personOneID.toString());
        FamilyIDJPA familyIDJPA = new FamilyIDJPA(familyID.toString());

        FamilyJPA familyJPA = familyDataDomainAssembler.toData(family);
        FamilyJPA familyTwoJPA = familyDataDomainAssembler.toData(family2);

        //Family savedFamily = familyRepository.getByID(familyID);

        Mockito.when(familyRepository.getByID(any(FamilyID.class))).thenReturn(family);
        Mockito.when(familyRepository.add(any(Family.class))).thenReturn(family2);

        OutputRelationDTO expected = new OutputRelationDTO("tonyze@latinlover.com", "katia@hotlatina.com", "Lovers", "123");
        OutputRelationDTO result = changeRelationService.changeRelation(inputChangeRelationDTO);

        assertEquals(expected, result);

    }



}