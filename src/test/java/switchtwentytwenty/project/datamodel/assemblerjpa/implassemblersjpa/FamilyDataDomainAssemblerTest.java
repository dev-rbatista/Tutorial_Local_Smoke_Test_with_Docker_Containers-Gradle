package switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyJPA;
import switchtwentytwenty.project.datamodel.domainjpa.PersonIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.RelationJPA;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.valueobject.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FamilyDataDomainAssemblerTest {


    FamilyDataDomainAssembler familyDataDomainAssembler = new FamilyDataDomainAssembler();

    @Test
    void toData() {
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);
        Family family = new Family(familyID, familyName, registrationDate, adminEmail);

        FamilyJPA expected = new FamilyJPA(new FamilyIDJPA(familyID.toString()), familyName.toString(), registrationDate.toString(), new PersonIDJPA(adminEmail.toString()));

        FamilyJPA result = familyDataDomainAssembler.toData(family);

        assertEquals(expected, result);
        assertNotNull(result);
    }


    @Test
    @DisplayName("Test to assert that the FamilyID is successfully created")
    void createFamilyID() {
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);

        FamilyJPA familyJPA = new FamilyJPA(new FamilyIDJPA(familyID.toString()), familyName.toString(), registrationDate.toString(), new PersonIDJPA(adminEmail.toString()));

        FamilyID expected = new FamilyID("admin@gmail.com");
        FamilyID result = familyDataDomainAssembler.createFamilyID(familyJPA);

        assertNotNull(result);
        assertNotSame(expected, result);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test to assert that the FamilyName is successfully created")
    void createFamilyName() {
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);

        FamilyJPA familyJPA = new FamilyJPA(new FamilyIDJPA(familyID.toString()), familyName.toString(), registrationDate.toString(), new PersonIDJPA(adminEmail.toString()));

        FamilyName expected = new FamilyName(familyNameString);
        FamilyName result = familyDataDomainAssembler.createFamilyName(familyJPA);

        assertNotNull(result);
        assertNotSame(expected, result);
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Test to assert that the RegistrationDate is successfully created")
    void createRegistrationDate() {
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);

        FamilyJPA familyJPA = new FamilyJPA(new FamilyIDJPA(familyID.toString()), familyName.toString(), registrationDate.toString(), new PersonIDJPA(adminEmail.toString()));

        RegistrationDate expected = new RegistrationDate(date);
        RegistrationDate result = familyDataDomainAssembler.createRegistrationDate(familyJPA);

        assertNotNull(result);
        assertNotSame(expected, result);
        assertEquals(expected, result);
    }

    @Test
    void createAdminID() {
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);

        FamilyJPA familyJPA = new FamilyJPA(new FamilyIDJPA(familyID.toString()), familyName.toString(), registrationDate.toString(), new PersonIDJPA(adminEmail.toString()));

        PersonID expected = new PersonID(emailString);
        PersonID result = familyDataDomainAssembler.createAdminID(familyJPA);

        assertNotNull(result);
        assertNotSame(expected, result);
        assertEquals(expected, result);
    }

    @Test
    void createFamilyIDJPA() {
        FamilyID familyID = new FamilyID("admin@gmail.com");

        FamilyIDJPA expected = new FamilyIDJPA("@admin@gmail.com");
        FamilyIDJPA result = familyDataDomainAssembler.createFamilyIDJPA(familyID);

        assertNotNull(result);
        assertNotSame(expected, result);
        assertEquals(expected, result);

    }

    @Test
    void generateRelationsJPAList() {

        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "tony@gmail.com";
        PersonID adminEmail = new PersonID(emailString);
        FamilyID familyID = new FamilyID(emailString);
        FamilyJPA familyJPA = new FamilyJPA(new FamilyIDJPA(familyID.toString()), familyName.toString(), registrationDate.toString(), new PersonIDJPA(adminEmail.toString()));

        PersonID memberOneID = new PersonID("tonyze@gmail.com");
        PersonID memberTwoID = new PersonID("katia@gmail.com");
        RelationDesignation relationDesignation = new RelationDesignation("BFF");
        RelationID relationID = new RelationID(13);
        Relation relation = new Relation(memberOneID, memberTwoID, relationDesignation, relationID);
        List<Relation> relations = new ArrayList<>();
        relations.add(relation);
        int expected = 1;
        int result = familyDataDomainAssembler.generateRelationsJPAList(relations, familyJPA).size();

        assertEquals(expected, result);
    }

    @Test
    void generateRelationList() {
        List<RelationJPA> relationsJPA = new ArrayList<>();
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "tony@gmail.com";
        PersonID adminEmail = new PersonID(emailString);
        FamilyID familyID = new FamilyID(emailString);
        FamilyJPA familyJPA = new FamilyJPA(new FamilyIDJPA(familyID.toString()), familyName.toString(), registrationDate.toString(), new PersonIDJPA(adminEmail.toString()));

        String memberOneJPAID = "tonyze@gmail.com";
        String memberTwoJPAID = "katia@gmail.com";
        String relationJPADesignation = "BFF";
        int relationJPAID = 23;

        RelationJPA relationJPA = new RelationJPA(memberOneJPAID, memberTwoJPAID, relationJPADesignation, relationJPAID, familyJPA);
        relationsJPA.add(relationJPA);

        int expected = 1;
        int result = familyDataDomainAssembler.generateRelationList(relationsJPA).size();

        assertEquals(expected, result);
    }

    @Test
    void toDataTestFamilyWithRelations() {
        FamilyID familyID = new FamilyID("admin@gmail.com");
        String familyNameString = "Ribeiro";
        FamilyName familyName = new FamilyName(familyNameString);
        String date = "12/12/1990";
        RegistrationDate registrationDate = new RegistrationDate(date);
        String emailString = "admin@gmail.com";
        PersonID adminEmail = new PersonID(emailString);
        Family family = new Family(familyID, familyName, registrationDate, adminEmail);
        family.addRelation(new Relation(new PersonID("admin@email.com"),new PersonID("other@email.com"),new RelationDesignation("Parent"), new RelationID(20300)));
        List<RelationJPA> relationJPAList = new ArrayList<>();
        FamilyJPA familyJPA = new FamilyJPA(new FamilyIDJPA("@admin@gmail.com"), "Sousa", "11/12/2020", new PersonIDJPA("admin@gmail.com"));
        relationJPAList.add(new RelationJPA("admin@email.com", "other@email.com", "Parent", 20300, familyJPA));

        FamilyJPA expected = new FamilyJPA(new FamilyIDJPA(familyID.toString()), familyName.toString(), registrationDate.toString(), new PersonIDJPA(adminEmail.toString()));
        expected.setRelationList(relationJPAList);

        FamilyJPA result = familyDataDomainAssembler.toData(family);

        assertEquals(expected, result);
        assertNotNull(result);
        assertEquals(expected.getRelationList(),result.getRelationList());
    }

    @Test
    void createRelationListTest(){
        FamilyJPA familyJPA = new FamilyJPA(new FamilyIDJPA("@admin@gmail.com"), "Sousa", "11/12/2020", new PersonIDJPA("admin@gmail.com"));
        List<RelationJPA> relationJPAList = new ArrayList<>();
        relationJPAList.add(new RelationJPA("admin@email.com", "other@email.com", "Parent", 20300, familyJPA));
        familyJPA.setRelationList(relationJPAList);
        List<Relation> expected = new ArrayList<>();
        expected.add(new Relation(new PersonID("admin@email.com"), new PersonID("other@email.com"), new RelationDesignation("Parent"), new RelationID(20300)));

        List<Relation> result = familyDataDomainAssembler.createRelationList(familyJPA);

        assertEquals(expected, result);
    }

}