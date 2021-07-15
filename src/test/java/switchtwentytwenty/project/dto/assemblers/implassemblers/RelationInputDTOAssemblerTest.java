package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.family.ChangeRelationDTO;
import switchtwentytwenty.project.dto.family.CreateRelationDTO;
import switchtwentytwenty.project.dto.family.InputChangeRelationDTO;
import switchtwentytwenty.project.dto.family.InputRelationDTO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class RelationInputDTOAssemblerTest {

    @Autowired
    RelationInputDTOAssembler relationAssembler;

    Name name1 = new Name("TonyZe");
    BirthDate birthdate1 = new BirthDate("12/12/1970");
    PersonID personID1 = new PersonID("tonyze@gmail.com");
    VATNumber vat1 = new VATNumber(123456789);
    FamilyID familyID = new FamilyID("tonyze@gmail.com");


    Name name2 = new Name("Katia");
    BirthDate birthdate2 = new BirthDate("24/10/1972");
    PersonID personID2 = new PersonID("katia@gmail.com");
    VATNumber vat2 = new VATNumber(123457689);


    @Test
    void toInputRelationDTOSuccessTwoMembersOfSameFamily() {
        Person memberOne = new Person(personID1, name1, birthdate1, vat1, familyID);
        Person memberTwo = new Person(personID1, name1, birthdate1, vat1, familyID);
        String memberOneID = memberOne.id().toString();
        String memberTwoID = memberTwo.id().toString();

        CreateRelationDTO createRelationDTO = new CreateRelationDTO(memberOneID, memberTwoID, "BFF");

        InputRelationDTO expected = new InputRelationDTO(createRelationDTO, familyID.toString());

        InputRelationDTO result = relationAssembler.toInputRelationDTO(createRelationDTO, familyID.toString());

        assertEquals(expected, result);
        assertNotNull(result);

    }

    @Test
    void toInputChangeRelationDTO() {
        ChangeRelationDTO changeRelationDTO = new ChangeRelationDTO("Pai");
        InputChangeRelationDTO expected = new InputChangeRelationDTO("2", "Pai", "@family@id.com");

        InputChangeRelationDTO result = relationAssembler.toInputChangeRelationDTO(changeRelationDTO, "@family@id.com","2");

        assertEquals(expected, result);
        assertNotSame(expected, result);
        assertNotNull(result);
    }
}