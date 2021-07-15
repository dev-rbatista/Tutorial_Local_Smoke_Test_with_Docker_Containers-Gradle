package switchtwentytwenty.project.dto.person;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputAddFamilyMemberDTOTest {
    InputAddFamilyMemberDTO inputAddFamilyMemberDTO1 = new InputAddFamilyMemberDTO();
    InputAddFamilyMemberDTO inputAddFamilyMemberDTOSameas1 = new InputAddFamilyMemberDTO();

    @BeforeEach
    void setup() {
        inputAddFamilyMemberDTO1.setAdminID("admin@email.com");
        inputAddFamilyMemberDTO1.setAdminID("user@email.com");
        inputAddFamilyMemberDTO1.setName("User");
        inputAddFamilyMemberDTO1.setBirtDate("01/01/1111");
        inputAddFamilyMemberDTO1.setVatNumber(111111111);
        inputAddFamilyMemberDTO1.setPhone(919999999);
        inputAddFamilyMemberDTO1.setStreet("Street");
        inputAddFamilyMemberDTO1.setCity("City");
        inputAddFamilyMemberDTO1.setHouseNumber("1");
        inputAddFamilyMemberDTO1.setZipCode("1234-123");
        inputAddFamilyMemberDTO1.setPassword("password");

        inputAddFamilyMemberDTOSameas1.setAdminID("admin@email.com");
        inputAddFamilyMemberDTOSameas1.setAdminID("user@email.com");
        inputAddFamilyMemberDTOSameas1.setName("User");
        inputAddFamilyMemberDTOSameas1.setBirtDate("01/01/1111");
        inputAddFamilyMemberDTOSameas1.setVatNumber(111111111);
        inputAddFamilyMemberDTOSameas1.setPhone(919999999);
        inputAddFamilyMemberDTOSameas1.setStreet("Street");
        inputAddFamilyMemberDTOSameas1.setCity("City");
        inputAddFamilyMemberDTOSameas1.setHouseNumber("1");
        inputAddFamilyMemberDTOSameas1.setZipCode("1234-123");
        inputAddFamilyMemberDTOSameas1.setPassword("password");

    }

    @Test
    @DisplayName("Equals Mutation coverage assertions")
    void mutationKilling(){
        Assertions.assertEquals(inputAddFamilyMemberDTO1,inputAddFamilyMemberDTO1);
        AddFamilyMemberDTO addFamilyMemberDTO = new AddFamilyMemberDTO();
        Assertions.assertNotEquals(inputAddFamilyMemberDTO1,addFamilyMemberDTO);

    }
    @Test
    @DisplayName("Should return true if two identical InputAddFamilyMemberDTO objects are equal")
    void testEqualsTheyAreEqual() {
        Assertions.assertEquals(inputAddFamilyMemberDTO1, inputAddFamilyMemberDTOSameas1);
        Assertions.assertNotSame(inputAddFamilyMemberDTO1, inputAddFamilyMemberDTOSameas1);

    }

    @Test
    @DisplayName("Should return false if two different (Name) InputAddFamilyMemberDTO objects are compared")
    void testEqualsTheyAreNotEqualName() {
        inputAddFamilyMemberDTOSameas1.setName("Name");
        Assertions.assertNotEquals(inputAddFamilyMemberDTO1, inputAddFamilyMemberDTOSameas1);
    }

    @Test
    @DisplayName("Should return false if two different (adminID) InputAddFamilyMemberDTO objects are compared")
    void testEqualsTheyAreNotEqualAdminID() {
        inputAddFamilyMemberDTOSameas1.setAdminID("notadmin@gmail.com");
        Assertions.assertNotEquals(inputAddFamilyMemberDTO1, inputAddFamilyMemberDTOSameas1);
    }

    @Test
    @DisplayName("Should return false if two different (emailID) InputAddFamilyMemberDTO objects are compared")
    void testEqualsTheyAreNotEqualEmailID() {
        inputAddFamilyMemberDTOSameas1.setEmailID("notID@gmail.com");
        Assertions.assertNotEquals(inputAddFamilyMemberDTO1, inputAddFamilyMemberDTOSameas1);
    }

    @Test
    @DisplayName("Should return false if two different (Birthdate) InputAddFamilyMemberDTO objects are compared")
    void testEqualsTheyAreNotEqualBirthDate() {
        inputAddFamilyMemberDTOSameas1.setBirtDate("2/2/2222");
        Assertions.assertNotEquals(inputAddFamilyMemberDTO1, inputAddFamilyMemberDTOSameas1);
    }

    @Test
    @DisplayName("Should return false if two different (VAT) InputAddFamilyMemberDTO objects are compared")
    void testEqualsTheyAreNotEqualVAT() {
        inputAddFamilyMemberDTOSameas1.setVatNumber(123321123);
        Assertions.assertNotEquals(inputAddFamilyMemberDTO1, inputAddFamilyMemberDTOSameas1);
    }

    @Test
    @DisplayName("Should return false if two different (Phone) InputAddFamilyMemberDTO objects are compared")
    void testEqualsTheyAreNotEqualPhone() {
        inputAddFamilyMemberDTOSameas1.setPhone(911234567);
        Assertions.assertNotEquals(inputAddFamilyMemberDTO1, inputAddFamilyMemberDTOSameas1);
    }

    @Test
    @DisplayName("Should return false if two different (Street) InputAddFamilyMemberDTO objects are compared")
    void testEqualsTheyAreNotEqualStreet() {
        inputAddFamilyMemberDTOSameas1.setStreet("Name");
        Assertions.assertNotEquals(inputAddFamilyMemberDTO1, inputAddFamilyMemberDTOSameas1);
    }

    @Test
    @DisplayName("Should return false if two different (City) InputAddFamilyMemberDTO objects are compared")
    void testEqualsTheyAreNotEqualCity() {
        inputAddFamilyMemberDTOSameas1.setCity("Name");
        Assertions.assertNotEquals(inputAddFamilyMemberDTO1, inputAddFamilyMemberDTOSameas1);
    }

    @Test
    @DisplayName("Should return false if two different (HouseNumber) InputAddFamilyMemberDTO objects are compared")
    void testEqualsTheyAreNotEqualHouseNumber() {
        inputAddFamilyMemberDTOSameas1.setHouseNumber("Name");
        Assertions.assertNotEquals(inputAddFamilyMemberDTO1, inputAddFamilyMemberDTOSameas1);
    }

    @Test
    @DisplayName("Should return false if two different (ZipCode) InputAddFamilyMemberDTO objects are compared")
    void testEqualsTheyAreNotEqualZipCode() {
        inputAddFamilyMemberDTOSameas1.setZipCode("Name");
        Assertions.assertNotEquals(inputAddFamilyMemberDTO1, inputAddFamilyMemberDTOSameas1);
    }

    @Test
    @DisplayName("Should return false if two different (Password) InputAddFamilyMemberDTO objects are compared")
    void testEqualsTheyAreNotEqualPassword() {
        inputAddFamilyMemberDTOSameas1.setPassword("other");
        Assertions.assertNotEquals(inputAddFamilyMemberDTO1, inputAddFamilyMemberDTOSameas1);
    }

    @Test
    @DisplayName("Should return true if two identical InputAddFamilyMemberDTO have their hashcodes compared")
    void testHashCodeEqual() {
        Assertions.assertEquals(inputAddFamilyMemberDTO1.hashCode(), inputAddFamilyMemberDTOSameas1.hashCode());

    }

    @Test
    @DisplayName("Should return false if two different InputAddFamilyMemberDTO have their hashcodes compared")
    void testHashCodeTheyAreNotEqual() {
        inputAddFamilyMemberDTOSameas1.setName("Name");
        Assertions.assertNotEquals(inputAddFamilyMemberDTO1.hashCode(), inputAddFamilyMemberDTOSameas1.hashCode());

    }
}