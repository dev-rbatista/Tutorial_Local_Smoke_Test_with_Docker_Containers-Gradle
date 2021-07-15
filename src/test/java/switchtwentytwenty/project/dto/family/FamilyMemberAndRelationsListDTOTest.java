package switchtwentytwenty.project.dto.family;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.dto.person.FamilyMemberAndRelationsDTO;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.FamilyRESTController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

class FamilyMemberAndRelationsListDTOTest {


    @Test
    public void AllArgsConstructor() {
        List<FamilyMemberAndRelationsDTO> familyMemberAndRelationsDTO = new ArrayList<>();
        FamilyMemberAndRelationsListDTO familyMemberAndRelationsListDTO = new FamilyMemberAndRelationsListDTO(familyMemberAndRelationsDTO);

        assertNotNull(familyMemberAndRelationsListDTO);

    }

    @Test
    void getFamilyMemberAndRelationsDTO() {

        List<FamilyMemberAndRelationsDTO> familyMemberAndRelationsDTO = new ArrayList<>();
        FamilyMemberAndRelationsDTO dto = new FamilyMemberAndRelationsDTO();
        familyMemberAndRelationsDTO.add(dto);
        FamilyMemberAndRelationsListDTO familyMemberAndRelationsListDTO = new FamilyMemberAndRelationsListDTO(familyMemberAndRelationsDTO);

        List<FamilyMemberAndRelationsDTO> result = familyMemberAndRelationsListDTO.getFamilyMemberAndRelationsDTO();

        assertNotNull(result);
        assertEquals(familyMemberAndRelationsDTO, result);
    }

    @Test
    void testEqualObjectsExpectingEquals() {
        List<FamilyMemberAndRelationsDTO> DTOlist = new ArrayList<>();
        FamilyMemberAndRelationsDTO dto = new FamilyMemberAndRelationsDTO();
        DTOlist.add(dto);

        FamilyMemberAndRelationsListDTO expected = new FamilyMemberAndRelationsListDTO(DTOlist);

        FamilyMemberAndRelationsListDTO result = new FamilyMemberAndRelationsListDTO(DTOlist);

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }

    @Test
    void testSameObjectExpectingEquals() {
        List<FamilyMemberAndRelationsDTO> DTOlist = new ArrayList<>();
        FamilyMemberAndRelationsDTO dto = new FamilyMemberAndRelationsDTO();
        DTOlist.add(dto);

        FamilyMemberAndRelationsListDTO expected = new FamilyMemberAndRelationsListDTO(DTOlist);

        FamilyMemberAndRelationsListDTO result = expected;

        assertEquals(expected, result);
    }


    @Test
    void testEquals_expectingNotEquals() {
        List<FamilyMemberAndRelationsDTO> DTOlist = new ArrayList<>();
        FamilyMemberAndRelationsDTO dto = new FamilyMemberAndRelationsDTO();
        DTOlist.add(dto);

        FamilyMemberAndRelationsListDTO expected = new FamilyMemberAndRelationsListDTO(DTOlist);

        FamilyMemberAndRelationsListDTO result = new FamilyMemberAndRelationsListDTO();

        assertNotEquals(expected, result);
        assertNotSame(expected, result);
    }

    @Test
    void testEqualsWithNullObjectExpectingNotEquals() {
        List<FamilyMemberAndRelationsDTO> dtoList = new ArrayList<>();
        FamilyMemberAndRelationsDTO dto = new FamilyMemberAndRelationsDTO();
        dtoList.add(dto);
        FamilyMemberAndRelationsListDTO expected = new FamilyMemberAndRelationsListDTO(dtoList);

        FamilyMemberAndRelationsListDTO result = null;

        assertNotEquals(expected, result);
    }

    @Test
    void testEqualsCompareWithStringExpectingNotEquals() {
        List<FamilyMemberAndRelationsDTO> dtoList = new ArrayList<>();
        FamilyMemberAndRelationsDTO dto = new FamilyMemberAndRelationsDTO();
        dtoList.add(dto);
        FamilyMemberAndRelationsListDTO expected = new FamilyMemberAndRelationsListDTO(dtoList);

        String result = "3";

        assertNotEquals(expected, result);
    }

    @Test
    void testSameHashCodeExpectingEquals() {
        List<FamilyMemberAndRelationsDTO> dtoList = new ArrayList<>();
        FamilyMemberAndRelationsDTO dto = new FamilyMemberAndRelationsDTO();
        dtoList.add(dto);
        FamilyMemberAndRelationsListDTO expected = new FamilyMemberAndRelationsListDTO(dtoList);

        List<FamilyMemberAndRelationsDTO> resultList = new ArrayList<>();
        resultList.add(dto);
        FamilyMemberAndRelationsListDTO result = new FamilyMemberAndRelationsListDTO(resultList);

        assertEquals(expected.hashCode(), result.hashCode());
    }

    @Test
    void testDifferentHashCodeExpectingNotEquals() {
        FamilyMemberAndRelationsListDTO expected = new FamilyMemberAndRelationsListDTO();

        List<FamilyMemberAndRelationsDTO> resultList = new ArrayList<>();
        FamilyMemberAndRelationsDTO dto = new FamilyMemberAndRelationsDTO();
        resultList.add(dto);
        FamilyMemberAndRelationsListDTO result = new FamilyMemberAndRelationsListDTO(resultList);


        assertNotEquals(expected.hashCode(), result.hashCode());
    }

    @Test
    void testEqualsDifferentLinks() {
        FamilyMemberAndRelationsListDTO expected = new FamilyMemberAndRelationsListDTO();

        List<FamilyMemberAndRelationsDTO> resultList = new ArrayList<>();
        FamilyMemberAndRelationsDTO dto = new FamilyMemberAndRelationsDTO();
        resultList.add(dto);
        Link link = linkTo(FamilyRESTController.class).withSelfRel();
        expected.add(link);
        FamilyMemberAndRelationsListDTO result = new FamilyMemberAndRelationsListDTO(resultList);

        assertNotEquals(expected, result);
    }
}