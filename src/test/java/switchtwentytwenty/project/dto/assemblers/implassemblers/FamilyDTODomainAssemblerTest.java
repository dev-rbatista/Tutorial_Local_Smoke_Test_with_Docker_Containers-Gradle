package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.family.*;
import switchtwentytwenty.project.dto.person.FamilyMemberAndRelationsDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FamilyDTODomainAssemblerTest {


    FamilyDTODomainAssembler familyDTODomainAssembler = new FamilyDTODomainAssembler();


    @Test
    void toDTO() {
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);

        Family family = new Family(familyID, familyName, registrationDate, adminEmail);

        OutputFamilyDTO expected = new OutputFamilyDTO(familyNameString, familyID.toString(), emailString, registrationDate.toString());

        OutputFamilyDTO result = familyDTODomainAssembler.toOutputFamilyDTO(family);

        assertEquals(expected, result);
        assertNotNull(result);
    }

    @Test
    void createFamilyNameTest() {
        String familyName = "Simpson";
        String registrationDate = "11/09/2020";
        InputFamilyDTO inputFamilyDTO = new InputFamilyDTO(familyName, registrationDate);
        FamilyName expected = new FamilyName(familyName);

        FamilyName result = familyDTODomainAssembler.createFamilyName(inputFamilyDTO);

        assertEquals(expected, result);
    }

    @Test
    void createRegistrationDateTest() {
        String familyName = "Simpson";
        String registrationDate = "11/09/2020";
        InputFamilyDTO inputFamilyDTO = new InputFamilyDTO(familyName, registrationDate);
        RegistrationDate expected = new RegistrationDate(registrationDate);

        RegistrationDate result = familyDTODomainAssembler.createRegistrationDate(inputFamilyDTO);

        assertEquals(expected, result);
    }

    @Test
    void personIDOneToDomain() {
        InputRelationDTO inputRelationDTO = new InputRelationDTO();
        inputRelationDTO.setPersonIDOne("admin@mail.net");
        PersonID expected = new PersonID("admin@mail.net");

        PersonID result = familyDTODomainAssembler.personIDOneToDomain(inputRelationDTO);

        assertEquals(expected, result);
    }

    @Test
    void personIDTwoToDomain() {
        InputRelationDTO inputRelationDTO = new InputRelationDTO();
        inputRelationDTO.setPersonIDTwo("person@mail.net");
        PersonID expected = new PersonID("person@mail.net");

        PersonID result = familyDTODomainAssembler.personIDTwoToDomain(inputRelationDTO);

        assertEquals(expected, result);
    }

    @Test
    void relationDesignationToDomain() {
        InputRelationDTO inputRelationDTO = new InputRelationDTO();
        inputRelationDTO.setDesignation("Friends");
        RelationDesignation expected = new RelationDesignation("Friends");

        RelationDesignation result = familyDTODomainAssembler.relationDesignationToDomain(inputRelationDTO);

        assertEquals(expected, result);
    }

    @Test
    void familyIDToDomain() {
        InputRelationDTO inputRelationDTO = new InputRelationDTO();
        inputRelationDTO.setFamilyID("@person@mail.net");
        FamilyID expected = new FamilyID("@person@mail.net");

        FamilyID result = familyDTODomainAssembler.familyIDToDomain(inputRelationDTO);

        assertEquals(expected, result);
    }

    @Test
    void testToDTO() {
        PersonID personIDA = new PersonID("admin@mail.net");
        PersonID personIDB = new PersonID("person@mail.net");
        RelationDesignation designation = new RelationDesignation("Friends");
        RelationID id = new RelationID(13);
        Relation relation = new Relation(personIDA, personIDB, designation, id);
        OutputRelationDTO expected = new OutputRelationDTO("admin@mail.net", "person@mail.net", "Friends", "13");

        OutputRelationDTO result = familyDTODomainAssembler.toOutputRelationDTO(relation);

        assertEquals(expected.getLinks(), result.getLinks());
        assertEquals(expected.getMemberOneID(), result.getMemberOneID());
        assertEquals(expected.getMemberTwoID(), result.getMemberTwoID());
        assertEquals(expected.getRelationDesignation(), result.getRelationDesignation());
    }

    @Test
    void createFamilyMemberAndRelationsDTO() {
        // Person
        PersonID personID = new PersonID("tonyZe@gmail.com");
        Name name = new Name("Tony");
        BirthDate birthDate = new BirthDate("19/02/1990");
        VATNumber vatNumber = new VATNumber(123123123);
        FamilyID familyID = new FamilyID("tonyZe@gmail.com");
        Person person = new Person(personID, name, birthDate, vatNumber, familyID);

        // Family
        FamilyName familyName = new FamilyName("Sopranos");
        RegistrationDate date = new RegistrationDate("12/1/90");
        Family family = new Family(familyID, familyName, date, personID);

        // Two relations
        PersonID personID1 = new PersonID("rute@gmail.com");
        PersonID personID2 = new PersonID("javascri@gmail.com");
        RelationDesignation descrip1 = new RelationDesignation("filho");
        RelationDesignation descrip2 = new RelationDesignation("primo");
        Relation relation1 = new Relation(personID, personID1, descrip1);
        Relation relation2 = new Relation(personID, personID2, descrip2);

        // Add Relations to Family
        family.addRelation(relation1);
        family.addRelation(relation2);

        // Two relations DTO
        OutputPersonRelationDTO outputRelationDTO1 = new OutputPersonRelationDTO("tonyZe@gmail.com", "rute@gmail.com", "filho", "-1800451932");
        OutputPersonRelationDTO outputRelationDTO2 = new OutputPersonRelationDTO("tonyZe@gmail.com", "javascri@gmail.com", "primo", "-274045927");

        List<OutputPersonRelationDTO> outputRelationDTOList = new ArrayList<>();
        outputRelationDTOList.add(outputRelationDTO1);
        outputRelationDTOList.add(outputRelationDTO2);

        //Link link = linkTo(methodOn(FamilyRESTController.class)).withRel("x");

        FamilyMemberAndRelationsDTO expected = new FamilyMemberAndRelationsDTO();
        expected.setName("Tony");
        expected.setPersonID("tonyZe@gmail.com");
        expected.setRelations(outputRelationDTOList);
        //expected.add(link);

        FamilyMemberAndRelationsDTO result = familyDTODomainAssembler.createFamilyMemberAndRelationsDTO(person, family);
        //result.add(link);

        assertEquals(expected, result);

    }

    @Test
    void testFamilyIDToDomain() {
        String familyID = "tony@gmail.com";
        FamilyID expected = new FamilyID("tony@gmail.com");

        FamilyID result = familyDTODomainAssembler.familyIDToDomain(familyID);

        assertEquals(expected, result);
    }

    @Test
    void toOutputPersonRelationDTO() {
        Relation relation = new Relation(new PersonID("id@id.com"), new PersonID("id2@id.com"), new RelationDesignation("friends"));
        OutputPersonRelationDTO expected = new OutputPersonRelationDTO("id@id.com", "id2@id.com", "friends", relation.getId().toString());
        OutputPersonRelationDTO result = familyDTODomainAssembler.toOutputPersonRelationDTO(relation);
        assertEquals(expected, result);

    }

    @Test
    void testCreateFamilyMemberAndRelationsDTO() {
        // Person
        PersonID personID = new PersonID("tonyZe@gmail.com");
        Name name = new Name("Tony");
        BirthDate birthDate = new BirthDate("19/02/1990");
        VATNumber vatNumber = new VATNumber(123123123);
        FamilyID familyID = new FamilyID("tonyZe@gmail.com");
        Person person = new Person(personID, name, birthDate, vatNumber, familyID);

        // Family
        FamilyName familyName = new FamilyName("Sopranos");
        RegistrationDate date = new RegistrationDate("12/1/90");
        Family family = new Family(familyID, familyName, date, personID);

        // Two relations
        PersonID personID1 = new PersonID("rute@gmail.com");
        PersonID personID2 = new PersonID("javascri@gmail.com");
        RelationDesignation descrip1 = new RelationDesignation("filho");
        RelationDesignation descrip2 = new RelationDesignation("primo");
        Relation relation1 = new Relation(personID, personID1, descrip1);
        Relation relation2 = new Relation(personID, personID2, descrip2);

        // Add Relations to Family
        family.addRelation(relation1);
        //family.addRelation(relation2);

        FamilyMemberAndRelationsDTO expected = new FamilyMemberAndRelationsDTO();
        expected.setName("Tony");
        expected.setPersonID("tonyZe@gmail.com");
        List<OutputPersonRelationDTO> outputRelations = new ArrayList<>();
        OutputPersonRelationDTO outputRelationsDTO = new OutputPersonRelationDTO();
        outputRelationsDTO.setRelationID(relation1.getId().toString());
        outputRelationsDTO.setMemberOneID("tonyZe@gmail.com");
        outputRelationsDTO.setMemberTwoID("rute@gmail.com");
        outputRelationsDTO.setRelationDesignation("filho");
        outputRelations.add(outputRelationsDTO);
        expected.setRelations(outputRelations);

        FamilyMemberAndRelationsDTO result = familyDTODomainAssembler.createFamilyMemberAndRelationsDTO(person, family);
        assertEquals(expected, result);
    }
}