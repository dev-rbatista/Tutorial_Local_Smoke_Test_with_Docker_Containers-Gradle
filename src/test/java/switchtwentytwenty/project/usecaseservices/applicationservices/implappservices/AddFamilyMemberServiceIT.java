package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.PersonDataDomainAssembler;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonDTODomainAssembler;
import switchtwentytwenty.project.dto.person.InputAddFamilyMemberDTO;
import switchtwentytwenty.project.exceptions.InvalidNameException;
import switchtwentytwenty.project.interfaceadapters.implrepositories.PersonRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
@SpringBootTest
class AddFamilyMemberServiceIT {

    @Autowired
    AddFamilyMemberService service;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonDTODomainAssembler personDTODomainAssembler;

    @Autowired
    PersonDataDomainAssembler personDataDomainAssembler;


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

    InputAddFamilyMemberDTO invalidNameInternalAddFamilyMemberDTO = new InputAddFamilyMemberDTO(adminID, ID, invalidName, birthDate, vat, phone, street, city, houseNum, zipCode, password);


    @DisplayName("AddFamilyMemberService IT Test to check if an exception is thrown if an invalid Value object is attempted to be instanced")
    @Test
    void addPersonFail_InvalidName() {

        assertThrows(InvalidNameException.class, () -> service.addPerson(invalidNameInternalAddFamilyMemberDTO));
    }

/*    @DisplayName("AddFamilyMemberService IT Test to check if an exception is thrown if an empty password field is given")
    @Test
    void addPersonFail_NullPassword() {
        InputAddFamilyMemberDTO emptyPasswordDTO = new InputAddFamilyMemberDTO(adminID, ID, name, birthDate, vat, phone, street, city, houseNum, zipCode, null);
        assertThrows(InvalidDataAccessApiUsageException.class, () -> service.addPerson(emptyPasswordDTO));
    }*/

}