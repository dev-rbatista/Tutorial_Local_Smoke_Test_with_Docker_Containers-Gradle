package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.dto.family.AddFamilyAndSetAdminDTO;
import switchtwentytwenty.project.dto.person.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonInputDTOAssemblerTest {

    PersonInputDTOAssembler personInputDTOAssembler = new PersonInputDTOAssembler();


    @Test
    void toInputAddFamilyMemberDTOTest(){
        AddFamilyMemberDTO addFamilyMemberDTO = new AddFamilyMemberDTO("admin@email.com","admin@email.com","Lisa","11/10/1992",123123321,919999111,"Rua de Cima","Porto","36B","4000-123", "password");
        InputAddFamilyMemberDTO expected = new InputAddFamilyMemberDTO("admin@email.com","admin@email.com","Lisa","11/10/1992",123123321,919999111,"Rua de Cima","Porto","36B","4000-123", "password");

        InputAddFamilyMemberDTO result = personInputDTOAssembler.toInputAddFamilyMemberDTO(addFamilyMemberDTO);

        assertEquals(expected,result);
    }

    @Test
    void toInternalGetProfileDTOTest() {
        String personID = "admin@gmail.com";
        InputGetProfileDTO expected = new InputGetProfileDTO("admin@gmail.com");

        InputGetProfileDTO result = personInputDTOAssembler.toInternalGetProfileDTO(personID);

        assertEquals(expected,result);
    }

    @Test
    void toInputEmailDTOTest() {
        AddEmailDTO addEmailDTO = new AddEmailDTO("otheremail@gmail.com");
        InputEmailDTO expected = new InputEmailDTO("admin@gmail.com","otheremail@gmail.com");

        InputEmailDTO result = personInputDTOAssembler.toInputEmailDTO(addEmailDTO, "admin@gmail.com");

        assertEquals(expected,result);
    }

    @Test
    void toInputPersonDTOTest() {
        AddFamilyAndSetAdminDTO addFamilyAndSetAdminDTO = new AddFamilyAndSetAdminDTO("admin@email.com","Lisa","11/10/1992",123123321,919999111,"Rua de Cima","Porto","36B","4000-123", "password", "Simpson","11/03/2014");
        InputPersonDTO expected = new InputPersonDTO("admin@email.com","Lisa","11/10/1992",123123321,919999111,"Rua de Cima","Porto","36B","4000-123", "password");

        InputPersonDTO result = personInputDTOAssembler.toInputPersonDTO(addFamilyAndSetAdminDTO);

        assertEquals(expected,result);
    }

    @Test
    void toInputRemoveEmailSuccess() {
        String email = "zemanel@gmail.com";
        String userID = "zemanel@hotmail.com";
        InputRemoveEmailDTO result = new InputRemoveEmailDTO(email, userID);
        InputRemoveEmailDTO expected = personInputDTOAssembler.toInputRemoveEmail(email, userID);

        assertEquals(result, expected);
    }
}
