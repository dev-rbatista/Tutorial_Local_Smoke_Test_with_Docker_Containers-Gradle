package switchtwentytwenty.project.dto.person;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputPersonDTOTest {

    @Test
    @DisplayName("Should return true if two identical InputPersonDTOs are compared with the equals method")
    void testEquals() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        InputPersonDTO inputPersonDTOTwo = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");

        Assertions.assertNotSame(inputPersonDTOOne,inputPersonDTOTwo);
        Assertions.assertEquals(inputPersonDTOOne,inputPersonDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two  InputPersonDTOs with different emailID are compared with the equals method")
    void testEqualsFalseDifferentEmailID() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("AAAAtonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        InputPersonDTO inputPersonDTOTwo = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");

        Assertions.assertNotEquals(inputPersonDTOOne,inputPersonDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two different InputPersonDTOs with different names are compared with the equals method")
    void testEqualsFalseDifferentName() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        InputPersonDTO inputPersonDTOTwo = new InputPersonDTO("tonyze@gmail.com","other","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");

        Assertions.assertNotEquals(inputPersonDTOOne,inputPersonDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two different InputPersonDTOs with different birthDates are compared with the equals method")
    void testEqualsFalseDifferentBirthDate() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        InputPersonDTO inputPersonDTOTwo = new InputPersonDTO("tonyze@gmail.com","TonyZe","01/01/2000",123123123,919999999,"rua","cidade","23b","1234-123", "password");

        Assertions.assertNotEquals(inputPersonDTOOne,inputPersonDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two different InputPersonDTOs with different vatNumbers are compared with the equals method")
    void testEqualsFalseDifferentVATNumbers() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        InputPersonDTO inputPersonDTOTwo = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",321321321,919999999,"rua","cidade","23b","1234-123", "password");

        Assertions.assertNotEquals(inputPersonDTOOne,inputPersonDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two different InputPersonDTOs with different phoneNumbers are compared with the equals method")
    void testEqualsFalseDifferentPhoneNumbers() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        InputPersonDTO inputPersonDTOTwo = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,911234567,"rua","cidade","23b","1234-123", "password");

        Assertions.assertNotEquals(inputPersonDTOOne,inputPersonDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two different InputPersonDTOs with different street are compared with the equals method")
    void testEqualsFalseDifferentStreet() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        InputPersonDTO inputPersonDTOTwo = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"other","cidade","23b","1234-123", "password");

        Assertions.assertNotEquals(inputPersonDTOOne,inputPersonDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two different InputPersonDTOs with different city are compared with the equals method")
    void testEqualsFalseDifferentCity() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        InputPersonDTO inputPersonDTOTwo = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","other","23b","1234-123", "password");

        Assertions.assertNotEquals(inputPersonDTOOne,inputPersonDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two different InputPersonDTOs with different city are compared with the equals method")
    void testEqualsFalseDifferentHouseNumbers() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        InputPersonDTO inputPersonDTOTwo = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","11","1234-123", "password");

        Assertions.assertNotEquals(inputPersonDTOOne,inputPersonDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two different InputPersonDTOs with different zipCodes are compared with the equals method")
    void testEqualsFalseDifferentZipCodes() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        InputPersonDTO inputPersonDTOTwo = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","4000-123", "password");

        Assertions.assertNotEquals(inputPersonDTOOne,inputPersonDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two different InputPersonDTOs with different Passwords are compared with the equals method")
    void testEqualsFalseDifferentPasswords() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        InputPersonDTO inputPersonDTOTwo = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "admin");

        Assertions.assertNotEquals(inputPersonDTOOne,inputPersonDTOTwo);
    }



    @Test
    void testEqualsSameObject() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        InputPersonDTO inputPersonDTOTwo = inputPersonDTOOne;

        Assertions.assertEquals(inputPersonDTOOne,inputPersonDTOTwo);
    }

    @Test
    void testEqualsDifferentObjectTypes() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        String notInputPersonDTO = "test String";

        Assertions.assertNotEquals(inputPersonDTOOne,notInputPersonDTO);
    }

    @Test
    void testEqualsDifferentFromNull() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        String nullString = null;

        Assertions.assertNotEquals(inputPersonDTOOne,nullString);
    }

    @Test
    @DisplayName("Should return true if two identical InputPersonDTOs have their hashcodes compared")
    void testHashCode() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        InputPersonDTO inputPersonDTOTwo = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");

        Assertions.assertNotSame(inputPersonDTOOne,inputPersonDTOTwo);
        Assertions.assertEquals(inputPersonDTOOne.hashCode(),inputPersonDTOTwo.hashCode());
    }

    @Test
    @DisplayName("Should return false if two different InputPersonDTOs have their hashcodes compared")
    void testHashCodeFalse() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("AAAAAtonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        InputPersonDTO inputPersonDTOTwo = new InputPersonDTO("tonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");

        Assertions.assertNotEquals(inputPersonDTOOne.hashCode(),inputPersonDTOTwo.hashCode());
    }

    @Test
    @DisplayName("Test if password is being returned")
    void testUnpackPassword() {
        InputPersonDTO inputPersonDTOOne = new InputPersonDTO("AAAAAtonyze@gmail.com","TonyZe","28/12/1990",123123123,919999999,"rua","cidade","23b","1234-123", "password");
        String expected = "password";

        String result = inputPersonDTOOne.unpackPassword();

        Assertions.assertEquals(expected,result);
    }
}