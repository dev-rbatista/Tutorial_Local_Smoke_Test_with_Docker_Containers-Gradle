package switchtwentytwenty.project.interfaceadapters.controller.implcontrollers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.dto.category.CreateCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryTreeDTO;
import switchtwentytwenty.project.dto.family.AddFamilyAndSetAdminDTO;
import switchtwentytwenty.project.dto.family.OutputFamilyDTO;
import switchtwentytwenty.project.interfaceadapters.controller.icontrollers.IFamilyRESTController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class FamilyRESTControllerITDB {
    AddFamilyAndSetAdminDTO dto = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");
    AddFamilyAndSetAdminDTO invaliddto = new AddFamilyAndSetAdminDTO("tonyemail.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");


    @Autowired
    IFamilyRESTController familyRESTController;

    @Test
    void createFamilyAndSetAdmin() {

        Link expectedLink = linkTo(methodOn(FamilyRESTController.class).getFamily('@' + dto.getEmailID())).withSelfRel();

        OutputFamilyDTO outputFamilyDTO = new OutputFamilyDTO("Silva", "tony@email.com", "tony@email.com", "12/12/2000");

        outputFamilyDTO.add(expectedLink);

        ResponseEntity<OutputFamilyDTO> expected = new ResponseEntity(outputFamilyDTO, HttpStatus.CREATED);

        ResponseEntity<OutputFamilyDTO> result = familyRESTController.createFamilyAndSetAdmin(dto);

        assertEquals(expected.getStatusCode(), result.getStatusCode());
        assertEquals(expected.getBody().toString(), result.getBody().toString());
    }

    @Test
    @DisplayName("CreateFamilyAndSetAdmin function success case")
    void createFamilyAndSetAdminSuccessCase() {
        AddFamilyAndSetAdminDTO dto = new AddFamilyAndSetAdminDTO("teste@hotmail.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

        Link expectedLink = linkTo(methodOn(FamilyRESTController.class).getFamilyOptions('@' + dto.getEmailID())).withSelfRel();

        OutputFamilyDTO outputFamilyDTO = new OutputFamilyDTO("Silva", "@teste@hotmail.com", "teste@hotmail.com", "12/12/2000");

        outputFamilyDTO.add(expectedLink);

        ResponseEntity expected = new ResponseEntity(outputFamilyDTO, HttpStatus.CREATED);

        ResponseEntity result = familyRESTController.createFamilyAndSetAdmin(dto);


        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(expected.getBody().toString(), result.getBody().toString());
    }

    @Test
    void testCreateFamilyAndSetAdmin() {
        ResponseEntity expected = new ResponseEntity("Error: This Email is not valid", HttpStatus.UNPROCESSABLE_ENTITY);
        ResponseEntity result = familyRESTController.createFamilyAndSetAdmin(invaliddto);

        assertEquals(expected, result);
    }

 /*   @Test
    void addRelationTest() {
        CreateRelationDTO createRelationDTO = new CreateRelationDTO();

        assertThrows(UnsupportedOperationException.class,()->familyRESTController.createRelation(createRelationDTO, "tony@email.com"));
    }*/

    @Test
    void getFamilyNameTest() {
        String expected = "Ravens";
        ResponseEntity result = familyRESTController.getFamily("@rifens@ravens.com");
        OutputFamilyDTO resultDTO = (OutputFamilyDTO) result.getBody();
        assertNotNull(result);
        assertEquals(expected, resultDTO.getFamilyName());
    }

    @Test
    void getFamilyFailTest() {
        ResponseEntity expected = new ResponseEntity("Error: Family does not exist; nested exception is java.lang.IllegalArgumentException: Family does not exist", HttpStatus.BAD_REQUEST);
        ResponseEntity result = familyRESTController.getFamily("@riiiii@ravens.com");
        assertEquals(expected, result);
    }

    @Test
    void getFamilyFailTestAssertNotNull() {
        ResponseEntity result = familyRESTController.getFamily("@riiiii@ravens.com");
        assertNotNull(result);
        assertNotNull(result.getBody());
        assertNotNull(result.getStatusCode());
    }

    @Test
    void getFamilyIDTest() {
        String expected = "@rifens@ravens.com";
        ResponseEntity result = familyRESTController.getFamily("@rifens@ravens.com");
        OutputFamilyDTO resultDTO = (OutputFamilyDTO) result.getBody();
        assertNotNull(resultDTO);
        assertEquals(expected, resultDTO.getFamilyID());
    }

    @Test
    void getFamilyAdminIDTest() {
        String expected = "rifens@ravens.com";
        ResponseEntity result = familyRESTController.getFamily("@rifens@ravens.com");
        OutputFamilyDTO resultDTO = (OutputFamilyDTO) result.getBody();
        assertEquals(expected, resultDTO.getAdminID());
    }

    @Test
    void getFamilyRegistrationDateTest() {
        String expected = "1/1/2021";
        ResponseEntity result = familyRESTController.getFamily("@rifens@ravens.com");
        OutputFamilyDTO resultDTO = (OutputFamilyDTO) result.getBody();
        assertEquals(expected, resultDTO.getRegistrationDate());
    }

    @Test
    void getCategoriesSuccess() {
        String familyID = "@tonize@gmail.com";

        /*OutputCategoryDTO categoryCrypto = new OutputCategoryDTO("G3 CRYPTO", "1");
        OutputCategoryDTO categoryBeer = new OutputCategoryDTO("G3 BEER", "2");

         */

        Link expectedLink = linkTo(methodOn(FamilyRESTController.class).getCategories(familyID)).withSelfRel();
        OutputCategoryTreeDTO expectedOutputCategoryTreeDTO = new OutputCategoryTreeDTO();
        /*expectedOutputCategoryTreeDTO.addOutputCategoryDTO(categoryCrypto);
        expectedOutputCategoryTreeDTO.addOutputCategoryDTO(categoryBeer);

         */
        expectedOutputCategoryTreeDTO.add(expectedLink);

        ResponseEntity expected = new ResponseEntity(expectedOutputCategoryTreeDTO, HttpStatus.OK);


        ResponseEntity result = familyRESTController.getCategories(familyID);

        assertEquals(expected, result);
    }

    @Test
    void getCategoriesFail() {
        String familyID = null;

        ResponseEntity expected = new ResponseEntity("Error: Invalid ID", HttpStatus.BAD_REQUEST);

        ResponseEntity result = familyRESTController.getCategories(familyID);

        assertEquals(expected, result);
    }

    @Test
    void addCustomCategorySuccess() {
        String familyIDString = "@tonyze@latinlover.com";
        String categoryNameString = "Batatas";
        String parentIDString = "Sopa";
        String categoryIDString = "1";

        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO();
        createCategoryDTO.setCategoryDescription(categoryNameString);
        createCategoryDTO.setParentCategory(parentIDString);

        OutputCategoryDTO expectedOutputCategoryDTO = new OutputCategoryDTO();
        expectedOutputCategoryDTO.setCategoryID(categoryIDString);
        expectedOutputCategoryDTO.setCategoryName(categoryNameString);
        expectedOutputCategoryDTO.setFamilyID(familyIDString);
        expectedOutputCategoryDTO.setParentID(parentIDString);
        Link selfLink = linkTo(methodOn(FamilyRESTController.class).getCustomCategory(familyIDString, categoryIDString)).withSelfRel();
        expectedOutputCategoryDTO.add(selfLink);

        ResponseEntity<OutputCategoryDTO> expected = new ResponseEntity(expectedOutputCategoryDTO, HttpStatus.CREATED);

        ResponseEntity<OutputCategoryDTO> result = familyRESTController.addCustomCategory(familyIDString, createCategoryDTO);

        assertEquals(expected.toString(), result.toString());
    }
}