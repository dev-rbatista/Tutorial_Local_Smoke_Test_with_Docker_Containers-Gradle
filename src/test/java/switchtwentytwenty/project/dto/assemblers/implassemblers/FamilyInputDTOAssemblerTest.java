package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.dto.family.AddFamilyAndSetAdminDTO;
import switchtwentytwenty.project.dto.family.CreateRelationDTO;
import switchtwentytwenty.project.dto.family.InputFamilyDTO;
import switchtwentytwenty.project.dto.family.InputRelationDTO;
import switchtwentytwenty.project.dto.person.InputPersonDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
class FamilyInputDTOAssemblerTest {

    @Autowired
    FamilyInputDTOAssembler familyAssembler;

    @Autowired
    PersonInputDTOAssembler personAssembler;


    @Test
    void toInputPersonDTO() {

        AddFamilyAndSetAdminDTO dto = new AddFamilyAndSetAdminDTO("ttony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        InputPersonDTO expected = new InputPersonDTO("ttony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password");

        InputPersonDTO result = personAssembler.toInputPersonDTO(dto);

        assertEquals(expected, result);
        assertNotNull(result);
    }

    @Test
    void toInputFamilyDTO() {

        AddFamilyAndSetAdminDTO dto = new AddFamilyAndSetAdminDTO("ttony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        InputFamilyDTO expected = new InputFamilyDTO("Silva", "12/12/2000");

        InputFamilyDTO result = familyAssembler.toInputFamilyDTO(dto);

        assertEquals(expected, result);
        assertNotNull(result);
    }

    @Test
    void toInputRelationSetPersonOneID() {

        CreateRelationDTO createRelationDTO = new CreateRelationDTO("tonyze", "katia", "BFF");
        InputRelationDTO inputRelationDTO = new InputRelationDTO(createRelationDTO, "@tonyze");
        String memberOneID = createRelationDTO.getMemberOneID();
        InputRelationDTO expected = new InputRelationDTO(createRelationDTO, "@tonyze");
        CreateRelationDTO createRelationDTO1 = new CreateRelationDTO(memberOneID, "katia", "BFF");
        InputRelationDTO result = familyAssembler.toInputRelationDTO(createRelationDTO1, "@tonyze");

        assertEquals(expected, result);
    }

    @Test
    void toInputRelationSetPersonTwoID() {

        CreateRelationDTO createRelationDTO = new CreateRelationDTO("tonyze", "katia", "BFF");
        InputRelationDTO inputRelationDTO = new InputRelationDTO(createRelationDTO, "@tonyze");
        String memberTwoID = createRelationDTO.getMemberTwoID();
        InputRelationDTO expected = new InputRelationDTO(createRelationDTO, "@tonyze");
        CreateRelationDTO createRelationDTO1 = new CreateRelationDTO("tonyze", memberTwoID, "BFF");
        InputRelationDTO result = familyAssembler.toInputRelationDTO(createRelationDTO1, "@tonyze");

        assertEquals(expected, result);
    }

    @Test
    void toInputRelationSetDesignation() {

        CreateRelationDTO createRelationDTO = new CreateRelationDTO("tonyze", "katia", "BFF");
        InputRelationDTO inputRelationDTO = new InputRelationDTO(createRelationDTO, "@tonyze");
        String designation = createRelationDTO.getRelationDesignation();
        InputRelationDTO expected = new InputRelationDTO(createRelationDTO, "@tonyze");
        CreateRelationDTO createRelationDTO1 = new CreateRelationDTO("tonyze", "katia", designation);
        InputRelationDTO result = familyAssembler.toInputRelationDTO(createRelationDTO1, "@tonyze");

        assertEquals(expected, result);
    }
}