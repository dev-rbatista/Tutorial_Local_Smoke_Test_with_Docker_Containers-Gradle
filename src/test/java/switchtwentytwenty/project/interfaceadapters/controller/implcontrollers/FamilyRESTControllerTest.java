package switchtwentytwenty.project.interfaceadapters.controller.implcontrollers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.assemblers.implassemblers.CategoryInputDTOAssembler;
import switchtwentytwenty.project.dto.assemblers.implassemblers.FamilyInputDTOAssembler;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonInputDTOAssembler;
import switchtwentytwenty.project.dto.assemblers.implassemblers.RelationInputDTOAssembler;
import switchtwentytwenty.project.dto.category.CreateCategoryDTO;
import switchtwentytwenty.project.dto.category.InputCustomCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryTreeDTO;
import switchtwentytwenty.project.dto.family.*;
import switchtwentytwenty.project.dto.person.InputPersonDTO;
import switchtwentytwenty.project.exceptions.InvalidEmailException;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@ExtendWith(MockitoExtension.class)
class FamilyRESTControllerTest {
    AddFamilyAndSetAdminDTO dto = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");
    AddFamilyAndSetAdminDTO invaliddto = new AddFamilyAndSetAdminDTO("tonyemail.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

    @Mock
    ICreateFamilyService createFamilyService;

    @Mock
    FamilyInputDTOAssembler familyAssembler;

    @Mock
    PersonInputDTOAssembler personAssembler;

    @Mock
    CategoryInputDTOAssembler categoryAssembler;

    @Mock
    RelationInputDTOAssembler relationAssembler;

    @Mock
    IGetFamilyDataService getFamilyDataService;

    @Mock
    IFamiliesOptionsService familiesOptionsService;

    @Mock
    IGetFamilyMembersAndRelationshipService getFamilyMembersAndRelationshipService;

    @Mock
    IChangeRelationService changeRelationService;

    @Mock
    IFamilyOptionsService familyOptionsService;

    @Mock
    ICreateRelationService createRelationService;

    @Mock
    IGetCustomCategoriesService getCustomCategoriesService;

    @Mock
    ICreateCustomCategoryService createCustomCategoryService;

    @InjectMocks
    FamilyRESTController familyRESTController;


    @Test
    @DisplayName("CreateFamilyAndSetAdmin function success case")
    void createFamilyAndSetAdmin() {
        AddFamilyAndSetAdminDTO addFamilyAndSetAdminDTO = new AddFamilyAndSetAdminDTO("tonyze@gmail.com", "TonyZe", "28/12/1990", 123123123, 919999999, "rua", "cidade", "23b", "1234-123", "password", "Silva", "12/12/1990");

        InputPersonDTO inputPersonDTO = new InputPersonDTO("tonyze@gmail.com", "TonyZe", "28/12/1990", 123123123, 919999999, "rua", "cidade", "23b", "1234-123", "password");

        InputFamilyDTO inputFamilyDTO = new InputFamilyDTO("Silva", "12/12/1990");

        OutputFamilyDTO outputFamilyDTO = new OutputFamilyDTO("Silva", "@tony@email.com", "tony@email.com", "12/12/1990");

        when(personAssembler.toInputPersonDTO(any(AddFamilyAndSetAdminDTO.class))).thenReturn(inputPersonDTO);
        when(familyAssembler.toInputFamilyDTO(any(AddFamilyAndSetAdminDTO.class))).thenReturn(inputFamilyDTO);
        when(createFamilyService.createFamilyAndAddAdmin(any(), any())).thenReturn(outputFamilyDTO);

        // Expected

        OutputFamilyDTO expectedOutputDTO = new OutputFamilyDTO("Silva", "@tony@email.com", "tony@email.com", "12/12/1990");

        Link expectedLink = linkTo(methodOn(FamilyRESTController.class).getFamily(outputFamilyDTO.getFamilyID())).withSelfRel();

        expectedOutputDTO.add(expectedLink);

        ResponseEntity expected = new ResponseEntity(expectedOutputDTO, HttpStatus.CREATED);

        ResponseEntity result = familyRESTController.createFamilyAndSetAdmin(addFamilyAndSetAdminDTO);


        assertNotNull(result);
        //assertEquals(result.getStatusCode(),HttpStatus.CREATED);
        assertEquals(result, expected);
    }


    @Test
    void testCreateFamilyAndSetAdmin() {
        InputPersonDTO inputPersonDTO = new InputPersonDTO("tonyze@gmail.com", "TonyZe", "28/12/1990", 123123123, 919999999, "rua", "cidade", "23b", "1234-123", "password");

        InputFamilyDTO inputFamilyDTO = new InputFamilyDTO("Silva", "12/12/1990");

        when(personAssembler.toInputPersonDTO(any(AddFamilyAndSetAdminDTO.class))).thenReturn(inputPersonDTO);
        when(familyAssembler.toInputFamilyDTO(any(AddFamilyAndSetAdminDTO.class))).thenReturn(inputFamilyDTO);
        when(createFamilyService.createFamilyAndAddAdmin(any(), any())).thenThrow(InvalidEmailException.class);

        ResponseEntity expected = new ResponseEntity("Error: " + null, HttpStatus.UNPROCESSABLE_ENTITY);
        ResponseEntity result = familyRESTController.createFamilyAndSetAdmin(invaliddto);

        assertEquals(expected.getBody(), result.getBody());
        assertEquals(expected.getStatusCode(), result.getStatusCode());
    }


    @Test
    void familiesOptionsTest() {
        OptionsDTO optionsDTO = new OptionsDTO();

        Link linkToFamilyOptions = linkTo(methodOn(FamilyRESTController.class).familiesOptions()).withSelfRel();
        Link linkToAddFamily = linkTo(methodOn(FamilyRESTController.class).createFamilyAndSetAdmin(new AddFamilyAndSetAdminDTO())).withRel("POST - Add new Family");

        optionsDTO.add(linkToFamilyOptions);
        optionsDTO.add(linkToAddFamily);

        when(familiesOptionsService.getFamiliesOptions()).thenReturn(optionsDTO);

        OptionsDTO expectedOptionsDTO = new OptionsDTO();

        Link expectedLinkToFamilyOptions = linkTo(methodOn(FamilyRESTController.class).familiesOptions()).withSelfRel();
        Link expectedLinkToAddFamily = linkTo(methodOn(FamilyRESTController.class).createFamilyAndSetAdmin(new AddFamilyAndSetAdminDTO())).withRel("POST - Add new Family");

        expectedOptionsDTO.add(expectedLinkToFamilyOptions);
        expectedOptionsDTO.add(expectedLinkToAddFamily);

        HttpHeaders header = new HttpHeaders();
        header.set("Allow", "POST, OPTIONS");

        ResponseEntity expected = new ResponseEntity<>(optionsDTO, header, HttpStatus.OK);
        ResponseEntity result = familyRESTController.familiesOptions();

        assertEquals(result, expected);
    }


    @Test
    void getFamilyTest() {
        OutputFamilyDTO returnedDTO = new OutputFamilyDTO();
        OutputFamilyDTO expected = new OutputFamilyDTO();
        expected.setFamilyName("Ravens");
        expected.setRegistrationDate("01/01/2021");
        expected.setFamilyID("@rifens@ravens.com");
        expected.setAdminID("rifens@ravens.com");
        expected.add(linkTo(methodOn(FamilyRESTController.class).getFamilyOptions("@rifens@ravens.com")).withSelfRel());
        when(getFamilyDataService.getFamilyData("@rifens@ravens.com")).thenReturn(returnedDTO);
        ResponseEntity expectedEntity = new ResponseEntity(expected,HttpStatus.OK);
        ResponseEntity result = familyRESTController.getFamily("@rifens@ravens.com");
        assertEquals(expectedEntity.toString(),result.toString());
    }

    @Test
    void getFamilyOptionsTest() {
        String familyID = "@tonize@gmail.com";

        OptionsDTO options = new OptionsDTO();
        Link optionOne = linkTo(methodOn(FamilyRESTController.class).getFamily(familyID)).withSelfRel();
        Link optionTwo = linkTo(methodOn(FamilyRESTController.class).createRelation(new CreateRelationDTO(), familyID)).withRel("OPTIONS - relations");
        Link optionThree = linkTo(methodOn(FamilyRESTController.class).getCategoriesOptions(familyID)).withRel("OPTIONS - categories");

        options.add(optionOne);
        options.add(optionTwo);
        options.add(optionThree);

        when(familyOptionsService.getFamilyOptions(familyID)).thenReturn(options);

        // Expected
        OptionsDTO exoectedOptions = new OptionsDTO();
        Link exoectedOptionOne = linkTo(methodOn(FamilyRESTController.class).getFamily(familyID)).withSelfRel();
        Link exoectedOptionTwo = linkTo(methodOn(FamilyRESTController.class).createRelation(new CreateRelationDTO(), familyID)).withRel("OPTIONS - relations");
        Link exoectedOptionThree = linkTo(methodOn(FamilyRESTController.class).getCategoriesOptions(familyID)).withRel("OPTIONS - categories");

        exoectedOptions.add(exoectedOptionOne);
        exoectedOptions.add(exoectedOptionTwo);
        exoectedOptions.add(exoectedOptionThree);

        HttpHeaders header = new HttpHeaders();
        header.set("Allow", "OPTIONS");

        ResponseEntity expectedResponse = new ResponseEntity(exoectedOptions, header, HttpStatus.OK);

        // Act

        ResponseEntity resultResponse = familyRESTController.getFamilyOptions(familyID);

        assertEquals(expectedResponse, resultResponse);
    }

    @Test
    void getCategoriesOption() {
        assertThrows(UnsupportedOperationException.class, () -> familyRESTController.getCategoriesOptions("@tonyze@gmail.com"));
    }

    @Test
    void getCategoriesSuccess() {
        String familyID = "@tonize@gmail.com";

        OutputCategoryTreeDTO expectedOutputCategoryTreeDTO = new OutputCategoryTreeDTO();
        Link expectedLink = linkTo(methodOn(FamilyRESTController.class).getCategories(familyID)).withSelfRel();
        expectedOutputCategoryTreeDTO.add(expectedLink);
        ResponseEntity expected = new ResponseEntity(expectedOutputCategoryTreeDTO, HttpStatus.OK);

        when(getCustomCategoriesService.getCustomCategories(any(String.class))).thenReturn(new OutputCategoryTreeDTO());

        ResponseEntity result = familyRESTController.getCategories(familyID);

        assertEquals(result, expected);
    }

    @Test
    void getCategoriesFail() {
        String familyID = "@tonize@gmail.com";

        ResponseEntity expected = new ResponseEntity("Error: Invalid ID", HttpStatus.BAD_REQUEST);

        when(getCustomCategoriesService.getCustomCategories(any(String.class))).thenThrow(new IllegalArgumentException("Invalid ID"));

        ResponseEntity result = familyRESTController.getCategories(familyID);

        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Create Relation success")
    void createRelationSuccessCase() {
        CreateRelationDTO createRelationDTO = new CreateRelationDTO("tonyze@gmail.com", "katia@gmail.com", "BFF");
        String familyID = createRelationDTO.getMemberOneID();
        InputRelationDTO inputRelationDTO = new InputRelationDTO(createRelationDTO, "tonyze@gmail.com");

        OutputRelationDTO outputRelationDTO = new OutputRelationDTO("tonyze", "katia", "BFF", "3");

        Mockito.when(familyAssembler.toInputRelationDTO(any(CreateRelationDTO.class), any(String.class))).thenReturn(inputRelationDTO);
        Mockito.when(createRelationService.createRelation(any(InputRelationDTO.class))).thenReturn(outputRelationDTO);

        Link optionsLink = linkTo(methodOn(FamilyRESTController.class).getFamilyOptions(familyID)).withSelfRel();

        OutputRelationDTO expectedRelationDTO = outputRelationDTO.add(optionsLink);

        ResponseEntity expected = new ResponseEntity(expectedRelationDTO, HttpStatus.CREATED);

        ResponseEntity result = familyRESTController.createRelation(createRelationDTO, familyID);

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Create Relation failure")
    void createRelationFailureCase() {
        InputRelationDTO inputRelationDTO = new InputRelationDTO(null, null, "BFF", "@tony");
        CreateRelationDTO createRelationDTO = new CreateRelationDTO(null, null, "BFF");
        Mockito.when(familyAssembler.toInputRelationDTO(any(CreateRelationDTO.class), any(String.class))).thenReturn(inputRelationDTO);
        Mockito.when(createRelationService.createRelation(any(InputRelationDTO.class))).thenThrow(IllegalArgumentException.class);

        ResponseEntity expected = new ResponseEntity("Error: null", HttpStatus.UNPROCESSABLE_ENTITY);

        ResponseEntity result = familyRESTController.createRelation(createRelationDTO, "@tony");

        assertEquals(expected, result);


    }

    @Test
    @DisplayName("Test for the retrieval of the list of a family members and their relations")
    void getFamilyMemberAndRelationsTestValidFamilyID() {
        when(getFamilyMembersAndRelationshipService.getFamilyMembersAndRelations(anyString())).thenReturn(new FamilyMemberAndRelationsListDTO());
        FamilyMemberAndRelationsListDTO outputDTO = new FamilyMemberAndRelationsListDTO();
        Link selfLink = linkTo(methodOn(FamilyRESTController.class).getFamilyMembersAndRelations("@admin@gmail.com")).withSelfRel();
        outputDTO.add(selfLink);
        ResponseEntity<FamilyMemberAndRelationsListDTO> expected = new ResponseEntity(outputDTO, HttpStatus.OK);

        ResponseEntity<FamilyMemberAndRelationsListDTO> result = familyRESTController.getFamilyMembersAndRelations("@admin@gmail.com");

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test for the retrieval of the list of a family members and their relations failing because the family is not registered")
    void getFamilyMemberAndRelationsTestFamilyNotRegistered() {
        when(getFamilyMembersAndRelationshipService.getFamilyMembersAndRelations(anyString())).thenThrow(IllegalArgumentException.class);
        ResponseEntity<FamilyMemberAndRelationsListDTO> expected = new ResponseEntity("Error: null", HttpStatus.BAD_REQUEST);

        ResponseEntity<FamilyMemberAndRelationsListDTO> result = familyRESTController.getFamilyMembersAndRelations("@admin@gmail.com");

        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Test for the change of a relation between family members in a Family")
    void changeRelationValidRelationInfo() {
        String familyID = "@admin@gmail.com";
        String relationID = "123";
        String relationshipDesignation = "Amante";

        ChangeRelationDTO changeRelationDTO = new ChangeRelationDTO();
        changeRelationDTO.setNewRelationDesignation(relationshipDesignation);

        InputChangeRelationDTO inputChangeRelationDTO = new InputChangeRelationDTO(relationID, relationshipDesignation, familyID);

        String memberOneID = "tonyze@admin.com";
        String memberTwoID = "moonika@gmail.com";
        OutputRelationDTO outputRelationDTO = new OutputRelationDTO(memberOneID, memberTwoID, relationshipDesignation, relationID);
        Link selfLink = linkTo(methodOn(FamilyRESTController.class).getFamilyMembersAndRelations(familyID)).withSelfRel();
        outputRelationDTO.add(selfLink);

        when(relationAssembler.toInputChangeRelationDTO(any(ChangeRelationDTO.class), anyString(), anyString())).thenReturn(inputChangeRelationDTO);
        when(changeRelationService.changeRelation(any(InputChangeRelationDTO.class))).thenReturn(outputRelationDTO);

        ResponseEntity expected = new ResponseEntity(outputRelationDTO, HttpStatus.OK);

        ResponseEntity result = familyRESTController.changeRelation(changeRelationDTO, familyID, relationID);

        assertEquals(expected, result);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Change Relation failure due familyID not existing")
    void changeRelationInvalidFamilyID() {

        String familyID = "123";
        String relationID = "123";
        String relationshipDesignation = "Amante";

        ChangeRelationDTO changeRelationDTO = new ChangeRelationDTO();
        changeRelationDTO.setNewRelationDesignation(relationshipDesignation);

        InputChangeRelationDTO inputChangeRelationDTO = new InputChangeRelationDTO(relationID, relationshipDesignation, familyID);

        when(relationAssembler.toInputChangeRelationDTO(any(ChangeRelationDTO.class), anyString(), anyString())).thenReturn(inputChangeRelationDTO);
        when(changeRelationService.changeRelation(any(InputChangeRelationDTO.class))).thenThrow(IllegalArgumentException.class);

        ResponseEntity expected = new ResponseEntity("Error: null", HttpStatus.NOT_MODIFIED);

        ResponseEntity result = familyRESTController.changeRelation(changeRelationDTO, familyID, relationID);

        assertEquals(expected, result);
        assertNotNull(result);
    }

    @Test
    void addCustomCategoryTestSuccess() {

        String familyIDString = "@tonyze@latinlover.com";
        String categoryNameString = "Batatas";
        String parentIDString = "Sopa";
        String categoryIDString = "13L";

        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO();
        createCategoryDTO.setCategoryDescription(categoryNameString);
        createCategoryDTO.setParentCategory(parentIDString);

        OutputCategoryDTO outputCategoryDTO = new OutputCategoryDTO();
        outputCategoryDTO.setCategoryID(categoryIDString);
        outputCategoryDTO.setCategoryName(categoryNameString);
        outputCategoryDTO.setFamilyID(familyIDString);
        outputCategoryDTO.setParentID(parentIDString);
        Link selfLink = linkTo(methodOn(FamilyRESTController.class).getCustomCategory(familyIDString, categoryIDString)).withSelfRel();
        outputCategoryDTO.add(selfLink);

        when(categoryAssembler.toInputCustomCategoryDTO(any(CreateCategoryDTO.class), anyString())).thenReturn(new InputCustomCategoryDTO(categoryNameString, parentIDString, familyIDString));
        when(createCustomCategoryService.createCustomCategory(any(InputCustomCategoryDTO.class))).thenReturn(outputCategoryDTO);
        ResponseEntity<OutputCategoryDTO> expected = new ResponseEntity(outputCategoryDTO, HttpStatus.CREATED);

        ResponseEntity<OutputCategoryDTO> result = familyRESTController.addCustomCategory(familyIDString, createCategoryDTO);

        assertEquals(expected, result);
    }

    @Test
    void addCustomCategoryTestFailureBlankParentCategory() {
        String familyIDString = "@tonyze@latinlover.com";
        String categoryNameString = "Batatas";
        String parentIDString = "";

        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO();
        createCategoryDTO.setCategoryDescription(categoryNameString);
        createCategoryDTO.setParentCategory(parentIDString);

        when(categoryAssembler.toInputCustomCategoryDTO(any(CreateCategoryDTO.class), anyString())).thenReturn(new InputCustomCategoryDTO(categoryNameString, parentIDString, familyIDString));
        when(createCustomCategoryService.createCustomCategory(any(InputCustomCategoryDTO.class))).thenThrow(IllegalArgumentException.class);
        ResponseEntity<OutputCategoryDTO> expected = new ResponseEntity("Error: null", HttpStatus.UNPROCESSABLE_ENTITY);

        ResponseEntity<OutputCategoryDTO> result = familyRESTController.addCustomCategory(familyIDString, createCategoryDTO);

        assertEquals(expected, result);
    }

    @Test
    void getFamilyTestFailureThrowsException() {
        when(getFamilyDataService.getFamilyData(anyString())).thenThrow(new InvalidDataAccessApiUsageException(""));

        ResponseEntity expected = new ResponseEntity("Error: ",HttpStatus.BAD_REQUEST);
        ResponseEntity result = familyRESTController.getFamily("@rifens@ravens.com");

        assertEquals(expected,result);
    }

    @Test
    void getCustomCategoryUnsupported() {
        String familyIDString = "@tonyze@latinlover.com";
        String categoryIDString = "13L";
        assertThrows(UnsupportedOperationException.class,  () -> familyRESTController.getCustomCategory(familyIDString, categoryIDString));
    }
}