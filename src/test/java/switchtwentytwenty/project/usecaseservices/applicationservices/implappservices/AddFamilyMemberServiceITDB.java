package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.TestPropertySource;
import switchtwentytwenty.project.dto.person.InputAddFamilyMemberDTO;
import switchtwentytwenty.project.dto.person.OutputPersonDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class AddFamilyMemberServiceITDB {

    @Autowired
    AddFamilyMemberService service;

    String adminID = "tonyze@latinlover.com";
    String ID = "tonyZe@gamil.com";
    String name = "Tony";
    String invalidName = "     ";
    String birthDate = "1/1/1990";
    int vat = 234678912;
    Integer phone = 919997755;
    String street = "Rua";
    String city = "cite";
    String houseNum = "239";
    String zipCode = "1111-222";
    String password = "password";

    InputAddFamilyMemberDTO internalAddFamilyMemberDTO = new InputAddFamilyMemberDTO(adminID, ID, name, birthDate, vat, phone, street, city, houseNum, zipCode, password);

    InputAddFamilyMemberDTO internalAddFamilyMemberDTOUserAlreadyExists = new InputAddFamilyMemberDTO(adminID, "kvanessa@latina.com", name, birthDate, vat, phone, street, city, houseNum, zipCode, password);


    @Test
    @DisplayName("Integration test of AddFamilyMemberService with Repository: Successfully add a person")
    void addPersonSuccess() {

        OutputPersonDTO expected = new OutputPersonDTO();
        expected.setId(ID);
        expected.setName(name);
        expected.setBirthdate(birthDate);
        expected.setEmails(Collections.emptyList());
        List<Integer> phones = new ArrayList<>();
        phones.add(phone);
        expected.setPhoneNumbers(phones);
        expected.setVat(String.valueOf(vat));
        expected.setStreet(street);
        expected.setCity(city);
        expected.setZipCode(zipCode);
        expected.setDoorNumber(houseNum);
        expected.setFamilyID("@" + adminID);

        OutputPersonDTO result = service.addPerson(internalAddFamilyMemberDTO);
        assertNotNull(result);
        assertEquals(expected, result);
        assertNotSame(expected, result);
    }

    @Test
    @DisplayName("Test to assert an already registered email can't be registered again")
    void addPersonFail_PersonAlreadyRegistered() {

        assertThrows(InvalidDataAccessApiUsageException.class, () -> service.addPerson(internalAddFamilyMemberDTOUserAlreadyExists));

    }

}
