package switchtwentytwenty.project.dto.person;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddFamilyMemberDTOTest {

    String adminID = "tonyAdmin@gmail.com";
    String emailID = "tonyZe@gmail.com";
    String name = "Tony";
    String birthDate = "1/1/1991";
    Integer vatNumber = 2224466;
    Integer phone = 919998877;
    String street = "Rua";
    String city = "Cidade";
    String houseNumber = "03";
    String zipCode = "4321-333";

    AddFamilyMemberDTO familyMemberDTO = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");

    @Test
    void constructorTestNoArgumentsConstructor(){
        AddFamilyMemberDTO emptyFamilyMemberDTO = new AddFamilyMemberDTO();

        Assertions.assertNotNull(emptyFamilyMemberDTO);
    }

    @Test
    void getAdminID() {
        String result = familyMemberDTO.getAdminID();

        String expected = "2L";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void setAdminIDTest() {
        familyMemberDTO.setAdminID(adminID);
        String result = familyMemberDTO.getAdminID();

        String expected = "tonyAdmin@gmail.com";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void getEmailIDTest() {
        String result = familyMemberDTO.getEmailID();

        String expected = "3L";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void setEmailIDTest() {
        familyMemberDTO.setEmailID(emailID);
        String result = familyMemberDTO.getEmailID();

        String expected = "tonyZe@gmail.com";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void getNameTest() {
        String result = familyMemberDTO.getName();

        String expected = "TonyZe";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void setNameTest() {
        familyMemberDTO.setName(name);
        String result = familyMemberDTO.getName();

        String expected = "Tony";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void getBirtDateTest() {
        String result = familyMemberDTO.getBirthDate();

        String expected = "12/02/1999";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void setBirtDateTest() {
        familyMemberDTO.setBirthDate(birthDate);
        String result = familyMemberDTO.getBirthDate();

        String expected = "1/1/1991";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void getVatNumberTest() {
        Integer result = familyMemberDTO.getVatNumber();

        Integer expected = 123456789;

        Assertions.assertEquals(expected, result);
    }

    @Test
    void setVatNumberTest() {
        familyMemberDTO.setVatNumber(vatNumber);
        Integer result = familyMemberDTO.getVatNumber();

        Integer expected = 2224466;

        Assertions.assertEquals(expected, result);
    }

    @Test
    void getPhoneTest() {
        Integer result = familyMemberDTO.getPhone();

        Integer expected = 961962963;

        Assertions.assertEquals(expected, result);
    }

    @Test
    void setPhoneTest() {
        familyMemberDTO.setPhone(phone);
        Integer result = familyMemberDTO.getPhone();

        Integer expected = 919998877;

        Assertions.assertEquals(expected, result);
    }

    @Test
    void getStreetTest() {
        String result = familyMemberDTO.getStreet();

        String expected = "Rua da Estrada";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void setStreetTest() {
        familyMemberDTO.setStreet(street);
        String result = familyMemberDTO.getStreet();

        String expected = "Rua";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void getCityTest() {
        String result = familyMemberDTO.getCity();

        String expected = "Porto";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void setCityTest() {
        familyMemberDTO.setCity(city);
        String result = familyMemberDTO.getCity();

        String expected = "Cidade";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void getHouseNumberTest() {
        String result = familyMemberDTO.getHouseNumber();

        String expected = "12";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void setHouseNumberTest() {
        familyMemberDTO.setHouseNumber(houseNumber);
        String result = familyMemberDTO.getHouseNumber();

        String expected = "03";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void getZipCodeTest() {
        String result = familyMemberDTO.getZipCode();

        String expected = "4000";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void setZipCodeTest() {
        familyMemberDTO.setZipCode(zipCode);
        String result = familyMemberDTO.getZipCode();

        String expected = "4321-333";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void equalsTestEqualDTOs() {
        AddFamilyMemberDTO familyMemberDTOOne = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");
        AddFamilyMemberDTO familyMemberDTOTwo = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");

        Assertions.assertEquals(familyMemberDTOOne,familyMemberDTOTwo);
        Assertions.assertNotSame(familyMemberDTOOne,familyMemberDTOTwo);
    }

    @Test
    void equalsTestSameDTOs() {
        AddFamilyMemberDTO familyMemberDTOOne = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");
        AddFamilyMemberDTO familyMemberDTOTwo = familyMemberDTOOne;

        Assertions.assertEquals(familyMemberDTOOne,familyMemberDTOTwo);
    }

    @Test
    void equalsTestDifferentObject() {
        AddFamilyMemberDTO familyMemberDTO = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");
        String notDTO = "not DTO";

        Assertions.assertNotEquals(familyMemberDTO,notDTO);
    }


    @Test
    void equalsTestDifferentAdminIDsInDTO() {
        AddFamilyMemberDTO familyMemberDTOOne = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");
        AddFamilyMemberDTO familyMemberDTOTwo = new AddFamilyMemberDTO("3L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");

        Assertions.assertNotEquals(familyMemberDTOOne,familyMemberDTOTwo);
    }

    @Test
    void equalsTestDifferentEmailIDsInDTO() {
        AddFamilyMemberDTO familyMemberDTOOne = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");
        AddFamilyMemberDTO familyMemberDTOTwo = new AddFamilyMemberDTO("2L","teste@email.com", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");

        Assertions.assertNotEquals(familyMemberDTOOne,familyMemberDTOTwo);
    }

    @Test
    void equalsTestDifferentNamesInDTO() {
        AddFamilyMemberDTO familyMemberDTOOne = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");
        AddFamilyMemberDTO familyMemberDTOTwo = new AddFamilyMemberDTO("2L","3L", "Jessica", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");

        Assertions.assertNotEquals(familyMemberDTOOne,familyMemberDTOTwo);
    }

    @Test
    void equalsTestDifferentBirthDatesInDTO() {
        AddFamilyMemberDTO familyMemberDTOOne = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");
        AddFamilyMemberDTO familyMemberDTOTwo = new AddFamilyMemberDTO("2L","3L", "TonyZe", "05/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");

        Assertions.assertNotEquals(familyMemberDTOOne,familyMemberDTOTwo);
    }

    @Test
    void equalsTestDifferentVATNumbersInDTO() {
        AddFamilyMemberDTO familyMemberDTOOne = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 987654321,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");
        AddFamilyMemberDTO familyMemberDTOTwo = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");

        Assertions.assertNotEquals(familyMemberDTOOne,familyMemberDTOTwo);
    }

    @Test
    void equalsTestDifferentPhoneNumbersInDTO() {
        AddFamilyMemberDTO familyMemberDTOOne = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961234567, "Rua da Estrada", "Porto", "12", "4000", "password");
        AddFamilyMemberDTO familyMemberDTOTwo = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");

        Assertions.assertNotEquals(familyMemberDTOOne,familyMemberDTOTwo);
    }

    @Test
    void equalsTestDifferentStreetsInDTO() {
        AddFamilyMemberDTO familyMemberDTOOne = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");
        AddFamilyMemberDTO familyMemberDTOTwo = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua de Cima", "Porto", "12", "4000", "password");

        Assertions.assertNotEquals(familyMemberDTOOne,familyMemberDTOTwo);
    }

    @Test
    void equalsTestDifferentCitiesInDTO() {
        AddFamilyMemberDTO familyMemberDTOOne = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");
        AddFamilyMemberDTO familyMemberDTOTwo = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "São Pedro do Sul", "12", "4000", "password");

        Assertions.assertNotEquals(familyMemberDTOOne,familyMemberDTOTwo);
    }

    @Test
    void equalsTestDifferentHouseNumbersInDTO() {
        AddFamilyMemberDTO familyMemberDTOOne = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");
        AddFamilyMemberDTO familyMemberDTOTwo = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "22A", "4000", "password");

        Assertions.assertNotEquals(familyMemberDTOOne,familyMemberDTOTwo);
    }

    @Test
    void equalsTestDifferentZipCodesInDTO() {
        AddFamilyMemberDTO familyMemberDTOOne = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");
        AddFamilyMemberDTO familyMemberDTOTwo = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4123-001", "password");

        Assertions.assertNotEquals(familyMemberDTOOne,familyMemberDTOTwo);
    }

    @Test
    void equalsTestDifferentPasswordsInDTO() {
        AddFamilyMemberDTO familyMemberDTOOne = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4123-001", "password");
        AddFamilyMemberDTO familyMemberDTOTwo = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4123-001", "admin");

        Assertions.assertNotEquals(familyMemberDTOOne,familyMemberDTOTwo);
    }

    @Test
    void testHashCodeSameHashCode() {
        AddFamilyMemberDTO familyMemberDTOOne = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");
        AddFamilyMemberDTO familyMemberDTOTwo = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");

        Assertions.assertEquals(familyMemberDTOOne.hashCode(),familyMemberDTOTwo.hashCode());
        Assertions.assertNotSame(familyMemberDTOOne,familyMemberDTOTwo);
    }

    @Test
    void testHashCodeDifferentHashCode() {
        AddFamilyMemberDTO familyMemberDTOOne = new AddFamilyMemberDTO("2L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");
        AddFamilyMemberDTO familyMemberDTOTwo = new AddFamilyMemberDTO("3L","3L", "TonyZe", "12/02/1999", 123456789,961962963, "Rua da Estrada", "Porto", "12", "4000", "password");

        Assertions.assertNotEquals(familyMemberDTOOne.hashCode(),familyMemberDTOTwo.hashCode());
    }
}