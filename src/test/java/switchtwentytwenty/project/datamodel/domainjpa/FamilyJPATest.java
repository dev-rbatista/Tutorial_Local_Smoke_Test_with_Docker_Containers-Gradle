package switchtwentytwenty.project.datamodel.domainjpa;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FamilyJPATest {

    String familyName = "Simpson";
    String registrationDate = "12/12/2020";

    String adminEmail = "email@email.com";
    PersonIDJPA adminID = new PersonIDJPA(adminEmail);

    FamilyIDJPA familyID = new FamilyIDJPA("email@email.com");
    FamilyIDJPA familyIDTwo = new FamilyIDJPA("email2@email2.com");


    @Test
    @Tag("US010")
    void getFamilyNameTest() {
        String expected = "Simpson";
        FamilyJPA familyJPA = new FamilyJPA(familyID, familyName, registrationDate, adminID);

        String result = familyJPA.getFamilyName();

        assertEquals(expected,result);
    }

    @Test
    @Tag("US010")
    void getRegistrationDateTest() {
        String expected = "12/12/2020";
        FamilyJPA familyJPA = new FamilyJPA(familyID,familyName,registrationDate,adminID);

        String result = familyJPA.getRegistrationDate();

        assertEquals(expected,result);
    }

    @Test
    @Tag("US010")
    void getAdminEmailTest() {
        String expected = "email@email.com";
        FamilyJPA familyJPA = new FamilyJPA(familyID,familyName,registrationDate,adminID);

        String result = familyJPA.getAdminID().toString();

        assertEquals(expected,result);
    }

    @Test
    @Tag("US010")
    void getFamilyIDTest() {
        String expected = familyID.toString();
        FamilyJPA familyJPA = new FamilyJPA(familyID,familyName,registrationDate,adminID);

        String result = familyJPA.getId().toString();

        assertEquals(expected,result);
    }

    @Test
    @Tag("US010")
    void getRelationListTest() {
        FamilyJPA familyJPA = new FamilyJPA(familyID,familyName,registrationDate,adminID);
        List<RelationJPA> relationJPAList = new ArrayList<>();
        relationJPAList.add(new RelationJPA());
        familyJPA.setRelationList(relationJPAList);
        List<RelationJPA> expected = new ArrayList<>();
        expected.add(new RelationJPA());

        List<RelationJPA> result = familyJPA.getRelationList();

        assertEquals(expected,result);
    }

    @Test
    void equalsTestEqualFamilyJPA(){
        FamilyJPA familyJPAOne = new FamilyJPA(familyID,familyName,registrationDate,adminID);
        FamilyJPA familyJPATwo = new FamilyJPA(familyID,familyName,registrationDate,adminID);

        assertEquals(familyJPAOne,familyJPATwo);
        assertNotSame(familyJPAOne,familyJPATwo);
    }

    @Test
    void equalsTestSameFamilyJPA(){
        FamilyJPA familyJPAOne = new FamilyJPA(familyID,familyName,registrationDate,adminID);
        FamilyJPA familyJPATwo = familyJPAOne;

        assertEquals(familyJPAOne,familyJPATwo);
        assertSame(familyJPAOne,familyJPATwo);
    }

    @Test
    void equalsTestDifferentFamilyJPA(){
        FamilyJPA familyJPAOne = new FamilyJPA(familyID,familyName,registrationDate,adminID);
        FamilyJPA familyJPATwo = new FamilyJPA(familyIDTwo,familyName,registrationDate,adminID);

        assertNotEquals(familyJPAOne,familyJPATwo);
    }

    @Test
    void equalsTestDifferentObjects(){
        FamilyJPA familyJPAOne = new FamilyJPA(familyID,familyName,registrationDate,adminID);

        assertNotEquals(familyJPAOne,familyIDTwo);
    }

    @Test
    void equalsTestDifferentFromNull(){
        FamilyJPA familyJPAOne = new FamilyJPA(familyID,familyName,registrationDate,adminID);
        String nullString = null;

        assertNotEquals(familyJPAOne,nullString);
    }

    @Test
    void hashCodeEqualFamily() {
        FamilyJPA familyJPAOne = new FamilyJPA(familyID,familyName,registrationDate,adminID);
        FamilyJPA familyJPATwo = new FamilyJPA(familyID,familyName,registrationDate,adminID);

        assertEquals(familyJPAOne.hashCode(), familyJPATwo.hashCode());
        assertNotSame(familyJPAOne, familyJPATwo);
    }

    @Test
    void hashCodeDifferentFamily() {
        FamilyJPA familyJPAOne = new FamilyJPA(familyID,familyName,registrationDate,adminID);
        FamilyJPA familyJPATwo = new FamilyJPA(familyIDTwo,familyName,registrationDate,adminID);

        assertNotEquals(familyJPAOne.hashCode(), familyJPATwo.hashCode());
    }

    @Test
    void testToString() {
        FamilyJPA familyJPA = new FamilyJPA(familyID,familyName,registrationDate,adminID);
        String expected = "FamilyJPA(id=email@email.com, familyName=Simpson, registrationDate=12/12/2020, adminID=email@email.com, relationList=[])";

        String result = familyJPA.toString();

        assertEquals(expected,result);
    }
}