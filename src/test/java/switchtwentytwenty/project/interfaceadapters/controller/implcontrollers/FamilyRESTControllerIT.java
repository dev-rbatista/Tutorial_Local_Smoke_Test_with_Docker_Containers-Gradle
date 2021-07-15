package switchtwentytwenty.project.interfaceadapters.controller.implcontrollers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import switchtwentytwenty.project.datamodel.assemblerjpa.iassemblersjpa.ICategoryDataDomainAssembler;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.FamilyDataDomainAssembler;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.PersonDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.*;
import switchtwentytwenty.project.datamodel.repositoryjpa.ICategoryRepositoryJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.IFamilyRepositoryJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.IPersonRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.aggregates.category.CategoryFactory;
import switchtwentytwenty.project.domain.aggregates.category.CustomCategory;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.OptionsDTO;
import switchtwentytwenty.project.dto.assemblers.implassemblers.*;
import switchtwentytwenty.project.dto.category.CreateCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryTreeDTO;
import switchtwentytwenty.project.dto.family.*;
import switchtwentytwenty.project.dto.person.FamilyMemberAndRelationsDTO;
import switchtwentytwenty.project.interfaceadapters.controller.icontrollers.IFamilyRESTController;
import switchtwentytwenty.project.interfaceadapters.implrepositories.CategoryRepository;
import switchtwentytwenty.project.interfaceadapters.implrepositories.FamilyRepository;
import switchtwentytwenty.project.interfaceadapters.implrepositories.PersonRepository;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.*;
import switchtwentytwenty.project.usecaseservices.applicationservices.implappservices.ChangeRelationService;
import switchtwentytwenty.project.usecaseservices.applicationservices.implappservices.CreateCustomCategoryService;
import switchtwentytwenty.project.usecaseservices.applicationservices.implappservices.GetFamilyMembersAndRelationshipService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class FamilyRESTControllerIT {
    AddFamilyAndSetAdminDTO dto = new AddFamilyAndSetAdminDTO("tony@email.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");
    AddFamilyAndSetAdminDTO invaliddto = new AddFamilyAndSetAdminDTO("tonyemail.com", "Silva", "12/12/1222", 999999999, 919999999, "Rua", "Cidade", "12B", "4400-123", "password", "Silva", "12/12/2000");

    // Category repo

    @Mock
    ICategoryRepositoryJPA mockCategoryRepositoryJPA;

    @Mock
    ICategoryDataDomainAssembler categoryDataDomainAssembler;

    @Mock
    CategoryFactory categoryFactoryMock;

    @Autowired
    CategoryRepository categoryRepository;

    // Family Repository

    @Mock
    IFamilyRepositoryJPA iFamilyRepositoryJPA;

    @Mock
    FamilyDataDomainAssembler familyDataDomainAssembler;

    @InjectMocks
    FamilyRepository familyRepository;

    // Person Repository

    @Mock
    IPersonRepositoryJPA iPersonRepositoryJPA;

    @Mock
    PersonDataDomainAssembler personDataDomainAssembler;

    @InjectMocks
    PersonRepository personRepository;

    // Autowired

    @Autowired
    IFamilyRESTController familyRESTController;

    @Autowired
    FamilyInputDTOAssembler familyAssembler;

    @Autowired
    PersonInputDTOAssembler personAssembler;

    @Autowired
    CategoryInputDTOAssembler categoryInputDTOAssembler;

    @Autowired
    RelationInputDTOAssembler relationAssembler;

    @Autowired
    ICreateFamilyService iCreateFamilyService;

    @Autowired
    IChangeRelationService changeRelationService;

    @Autowired
    IGetFamilyMembersAndRelationshipService getFamilyMembersAndRelationshipService;

    @Autowired
    IFamiliesOptionsService familiesOptionsService;

    @Autowired
    IFamilyOptionsService familyOptionsService;

    @Autowired
    ICreateRelationService createRelationService;

    @Autowired
    IGetCustomCategoriesService getCustomCategoriesService;

    @Autowired
    IGetFamilyDataService getFamilyDataService;

    @Autowired
    ICreateCustomCategoryService createCustomCategoryService;

    @Autowired
    FamilyDTODomainAssembler familyDTODomainAssembler;

    @Autowired
    CategoryDTODomainAssembler categoryDTODomainAssembler;

    @Autowired
    CategoryFactory categoryFactory;


    /*private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        // We can create mock based on the Interface or a Class
        // templateEngine = mock(TemplateEngine.class);

        // init mock with annotations
        //MockitoAnnotations.initMocks(this);

        // just another version of initilization of mocks with annotation
        // pay attention to tear down method - we have to call close method
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }*/

    @Test
    void testCreateFamilyAndSetAdmin() {
        ResponseEntity expected = new ResponseEntity("Error: This Email is not valid", HttpStatus.UNPROCESSABLE_ENTITY);
        ResponseEntity result = familyRESTController.createFamilyAndSetAdmin(invaliddto);

        assertEquals(expected, result);
    }

    @Test
    void familiesOptionsTest() {
        OptionsDTO optionsDTO = new OptionsDTO();
        Link link = linkTo(methodOn(FamilyRESTController.class).familiesOptions()).withSelfRel();
        Link linkToAddFamily = linkTo(methodOn(FamilyRESTController.class).createFamilyAndSetAdmin(new AddFamilyAndSetAdminDTO())).withRel("Add new Family");

        optionsDTO.add(link);
        optionsDTO.add(linkToAddFamily);

        HttpHeaders header = new HttpHeaders();
        header.set("Allow", "POST, OPTIONS");

        ResponseEntity expected = new ResponseEntity<>(optionsDTO, header, HttpStatus.OK);
        ResponseEntity result = familyRESTController.familiesOptions();

        OptionsDTO resultDTO = (OptionsDTO) result.getBody();
        OptionsDTO expectedDTO = (OptionsDTO) expected.getBody();

        assertEquals(resultDTO.getLinks(), expectedDTO.getLinks());
        assertEquals(result.getHeaders(), expected.getHeaders());
        assertEquals(result.getStatusCode(), expected.getStatusCode());
    }


  /*  @Test
    void addRelationTest() {
        CreateRelationDTO createRelationDTO = new CreateRelationDTO();

        assertThrows(UnsupportedOperationException.class, () -> familyRESTController.createRelation(createRelationDTO, "@tonize@gmail.com"));
    }*/


    @Test
    void getCategoriesSuccess() {
        String familyID = "@tonize@gmail.com";

        //categoryRepository.setCategoryRepositoryJPA(mockCategoryRepositoryJPA);

        OutputCategoryTreeDTO expectedOutputCategoryTreeDTO = new OutputCategoryTreeDTO();
        Link expectedLink = linkTo(methodOn(FamilyRESTController.class).getCategories(familyID)).withSelfRel();
        expectedOutputCategoryTreeDTO.add(expectedLink);
        ResponseEntity expected = new ResponseEntity(expectedOutputCategoryTreeDTO, HttpStatus.OK);

        when(mockCategoryRepositoryJPA.findAllByFamilyIDJPA(any(FamilyIDJPA.class))).thenReturn(new ArrayList<CategoryJPA>());
        when(mockCategoryRepositoryJPA.findAllByFamilyIDJPAIsNull()).thenReturn(new ArrayList<CategoryJPA>());

        ResponseEntity result = familyRESTController.getCategories(familyID);

        assertEquals(expected, result);
    }

    @DisplayName("Family Members And Relations IT - Success")
    @Test
    void getFamilyMembersAndRelationsITSuccess() {
        PersonRepository personRepository = new PersonRepository(iPersonRepositoryJPA, personDataDomainAssembler);
        FamilyRepository familyRepository = new FamilyRepository(iFamilyRepositoryJPA, familyDataDomainAssembler);

        IGetFamilyMembersAndRelationshipService iGetFamilyMembersAndRelationshipService = new GetFamilyMembersAndRelationshipService(familyDTODomainAssembler, personRepository, familyRepository);

        FamilyRESTController familyRESTController = new FamilyRESTController(iCreateFamilyService, familyAssembler, relationAssembler, personAssembler, iGetFamilyMembersAndRelationshipService, familiesOptionsService, familyOptionsService, createRelationService, getCustomCategoriesService, getFamilyDataService, categoryInputDTOAssembler, createCustomCategoryService, changeRelationService);


        // Mocking
        // Person Repo
        FamilyIDJPA familyIDJPA = new FamilyIDJPA("@tonyze@email.com");
        when(personDataDomainAssembler.createFamilyID(any(FamilyID.class))).thenReturn(familyIDJPA);

        when(personDataDomainAssembler.createFamilyID(any(FamilyID.class))).thenReturn(new FamilyIDJPA("@tonyze@email.com"));

        List<PersonJPA> personJPAList = new ArrayList();
        personJPAList.add(new PersonJPA(new PersonIDJPA("tony@email.com"), "tony", "19/02/1992", 999999999, new FamilyIDJPA("@tonyze@email.com")));
        personJPAList.add(new PersonJPA(new PersonIDJPA("katia@email.com"), "katia", "12/03/1999", 999999998, new FamilyIDJPA("@tonyze@email.com")));

        when(iPersonRepositoryJPA.findAllByFamilyid(any(FamilyIDJPA.class))).thenReturn(personJPAList);

        when(personDataDomainAssembler.createPersonID(any(PersonJPA.class))).thenReturn(new PersonID("tony@email.com")).thenReturn(new PersonID("katia@email.com"));
        when(personDataDomainAssembler.createName(any(PersonJPA.class))).thenReturn(new Name("tony")).thenReturn(new Name("katia"));
        when(personDataDomainAssembler.createBirthDate(any(PersonJPA.class))).thenReturn(new BirthDate("19/02/1992")).thenReturn(new BirthDate("12/03/1999"));
        when(personDataDomainAssembler.createEmailAdressList(any(PersonJPA.class))).thenReturn(new ArrayList<>()).thenReturn(new ArrayList<>());
        when(personDataDomainAssembler.createVATNumber(any(PersonJPA.class))).thenReturn(new VATNumber(999999999)).thenReturn(new VATNumber(999999998));
        when(personDataDomainAssembler.createPhoneNumberList(any(PersonJPA.class))).thenReturn(new ArrayList<>()).thenReturn(new ArrayList<>());
        when(personDataDomainAssembler.createAddress(any(PersonJPA.class))).thenReturn(new Address("Rua", "Covilhã", "0000-000", "1")).thenReturn(new Address("Rua", "Covilhã", "0000-000", "1"));
        when(personDataDomainAssembler.createFamilyID(any(PersonJPA.class))).thenReturn(new FamilyID("@tonyze@email.com")).thenReturn(new FamilyID("@tonyze@email.com"));

        // Family Repo
        FamilyJPA familyJPA = new FamilyJPA(new FamilyIDJPA("@tonyze@email.com"), "Zés", "27/05/2021", new PersonIDJPA("tonyze@email.com"));

        Optional<FamilyJPA> optionalFamilyJPA = Optional.of(familyJPA);

        when(familyDataDomainAssembler.createFamilyIDJPA(any(FamilyID.class))).thenReturn(familyIDJPA);
        when(iFamilyRepositoryJPA.findById(any(FamilyIDJPA.class))).thenReturn(optionalFamilyJPA);
        when(familyDataDomainAssembler.createFamilyID(any(FamilyJPA.class))).thenReturn(new FamilyID("@tonyze@email.com"));
        when(familyDataDomainAssembler.createFamilyName(any(FamilyJPA.class))).thenReturn(new FamilyName("Zés"));
        when(familyDataDomainAssembler.createRegistrationDate(any(FamilyJPA.class))).thenReturn(new RegistrationDate("27/05/2021"));
        when(familyDataDomainAssembler.createAdminID(any(FamilyJPA.class))).thenReturn(new PersonID("tonyze@email.com"));

        List<Relation> relationList = new ArrayList<>();
        Relation relation = new Relation(new PersonID("tony@email.com"), new PersonID("katia@email.com"), new RelationDesignation("Marido"));
        relationList.add(relation);

        when(familyDataDomainAssembler.createRelationList(any(FamilyJPA.class))).thenReturn(relationList);

        // Expected
        FamilyMemberAndRelationsListDTO familyMemberAndRelationsListDTO = new FamilyMemberAndRelationsListDTO();
        Integer relationID = Objects.hash(new PersonID("tony@email.com"), new PersonID("katia@email.com"));

        List<OutputPersonRelationDTO> relations = new ArrayList<>();
        relations.add(new OutputPersonRelationDTO("tony@email.com", "katia@email.com", "Marido", relationID.toString()));

        FamilyMemberAndRelationsDTO memberOne = new FamilyMemberAndRelationsDTO("tony", "tony@email.com", relations);
        // Link linkToOne = linkTo(methodOn(PersonRESTController.class).getProfileInfo("tony@email.com")).withSelfRel();
        // memberOne.add(linkToOne);

        FamilyMemberAndRelationsDTO memberTwo = new FamilyMemberAndRelationsDTO("katia", "katia@email.com", Collections.emptyList());
        // Link linkToTwo = linkTo(methodOn(PersonRESTController.class).getProfileInfo("katia@email.com")).withSelfRel();
        // memberTwo.add(linkToTwo);

        familyMemberAndRelationsListDTO.addDTO(memberOne);
        familyMemberAndRelationsListDTO.addDTO(memberTwo);

        Link link = linkTo(methodOn(FamilyRESTController.class).getFamilyMembersAndRelations("@tonyze@email.com")).withSelfRel();
        familyMemberAndRelationsListDTO.add(link);

        ResponseEntity expected = new ResponseEntity(familyMemberAndRelationsListDTO, HttpStatus.OK);

        String familyID = "@tonyze@email.com";

        ResponseEntity result = familyRESTController.getFamilyMembersAndRelations(familyID);

        assertEquals(expected, result);
    }

    @DisplayName("Family Members And Relations IT - Failure - Family Does not Exist")
    @Test
    void getFamilyMembersAndRelationsITFailure() {
        IGetFamilyMembersAndRelationshipService iGetFamilyMembersAndRelationshipService = new GetFamilyMembersAndRelationshipService(familyDTODomainAssembler, personRepository, familyRepository);

        FamilyRESTController familyRESTController = new FamilyRESTController(iCreateFamilyService, familyAssembler, relationAssembler, personAssembler, iGetFamilyMembersAndRelationshipService, familiesOptionsService, familyOptionsService, createRelationService, getCustomCategoriesService, getFamilyDataService, categoryInputDTOAssembler, createCustomCategoryService, changeRelationService);


        when(iFamilyRepositoryJPA.findById(any(FamilyIDJPA.class))).thenReturn(Optional.empty());

        ResponseEntity expected = new ResponseEntity("Error: Family does not exist", HttpStatus.BAD_REQUEST);

        String familyID = "@tonyze@email.com";

        ResponseEntity result = familyRESTController.getFamilyMembersAndRelations(familyID);

        assertEquals(expected, result);
    }

    @DisplayName("Add a custom category to the family - Success")
    @Test
    void addCustomCategorySuccess() {
        CategoryRepository categoryRepository = new CategoryRepository(mockCategoryRepositoryJPA, categoryDataDomainAssembler, categoryFactory);

        ICreateCustomCategoryService createCustomCategoryService = new CreateCustomCategoryService(categoryRepository, categoryDTODomainAssembler, categoryFactory);

        FamilyRESTController familyRESTController = new FamilyRESTController(iCreateFamilyService, familyAssembler, relationAssembler, personAssembler, getFamilyMembersAndRelationshipService, familiesOptionsService, familyOptionsService, createRelationService, getCustomCategoriesService, getFamilyDataService, categoryInputDTOAssembler, createCustomCategoryService, changeRelationService);

        // Input and Output DTOs
        String familyIDString = "@tonyze@latinlover.com";
        String categoryNameString = "BATATAS";
        String parentIDString = "Sopa";
        String categoryIDString = "2";

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

        // Category Value Objects
        FamilyID familyID = new FamilyID(familyIDString);
        CategoryName categoryName = new CategoryName(categoryNameString);
        ParentCategoryPath parentCategoryPath = new ParentCategoryPath(parentIDString);
        CategoryID categoryID = new CategoryID(categoryIDString);
        Category customCategory = new CustomCategory(categoryID, parentCategoryPath, categoryName, familyID);

        // Mock from category repository onwards
        when(categoryDataDomainAssembler.toData(any(Category.class))).thenReturn(new CategoryJPA());
        when(mockCategoryRepositoryJPA.save(any(CategoryJPA.class))).thenReturn(new CategoryJPA());
        when(categoryDataDomainAssembler.createCategoryID(any(CategoryJPA.class))).thenReturn(new CategoryID(categoryIDString));
        when(categoryDataDomainAssembler.createCategoryName(any(CategoryJPA.class))).thenReturn(new CategoryName(categoryNameString));
        when(categoryDataDomainAssembler.createFamilyID(any(CategoryJPA.class))).thenReturn(Optional.of(new FamilyID(familyIDString)));
        when(categoryDataDomainAssembler.createParentID(any(CategoryJPA.class))).thenReturn(new ParentCategoryPath(parentIDString));
        when(categoryFactoryMock.createCategory(any(CategoryID.class), any(CategoryName.class), any(ParentCategoryPath.class), any(Optional.class))).thenReturn(customCategory);


        ResponseEntity<OutputCategoryDTO> result = familyRESTController.addCustomCategory(familyIDString, createCategoryDTO);

        assertEquals(expected.toString(), result.toString());
    }

   @DisplayName("Add a custom category to the family with blank parent category - Fail")
    @Test
    void addCustomCategoryFailure() {
        ICreateCustomCategoryService createCustomCategoryService = new CreateCustomCategoryService(categoryRepository, categoryDTODomainAssembler, categoryFactory);
        FamilyRESTController familyRESTController = new FamilyRESTController(iCreateFamilyService, familyAssembler, relationAssembler, personAssembler, getFamilyMembersAndRelationshipService, familiesOptionsService, familyOptionsService, createRelationService, getCustomCategoriesService, getFamilyDataService, categoryInputDTOAssembler, createCustomCategoryService, changeRelationService);

        // Input and Output DTOs
        String familyIDString = "@tonyze@latinlover.com";
        String categoryNameString = "Batatas";
        String parentIDString = "";

        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO();
        createCategoryDTO.setCategoryDescription(categoryNameString);
        createCategoryDTO.setParentCategory(parentIDString);

        ResponseEntity<OutputCategoryDTO> expected = new ResponseEntity("Error: null", HttpStatus.UNPROCESSABLE_ENTITY);

        ResponseEntity<OutputCategoryDTO> result = familyRESTController.addCustomCategory(familyIDString, createCategoryDTO);

        assertEquals(expected.toString(), result.toString());
    }

    @DisplayName("Successfully Change relationship designation in a family")
    @Test
    void changeRelationSuccessWithValidInfo() {
        FamilyRepository familyRepository = new FamilyRepository(iFamilyRepositoryJPA, familyDataDomainAssembler);
        IChangeRelationService changeRelationService = new ChangeRelationService(familyRepository, familyDTODomainAssembler);
        FamilyRESTController familyRESTController = new FamilyRESTController(iCreateFamilyService, familyAssembler, relationAssembler, personAssembler, getFamilyMembersAndRelationshipService, familiesOptionsService, familyOptionsService, createRelationService, getCustomCategoriesService, getFamilyDataService, categoryInputDTOAssembler, createCustomCategoryService, changeRelationService);


        //criar as merdas por constructor - inserir mock do IrepositoryJPA no constructor do repo
        //Setup for running changeRelation
        String newDesignation = "NewDesignation";
        ChangeRelationDTO changeRelationDTO = new ChangeRelationDTO(newDesignation);
        String familyID = "@tonyze@gmail.com";
        String relationID = "123";

        //Setup for OutputRelationDTO
        String memberOneID = "tonyze@gmail.com";
        String memberTwoID = "moonika@gmail.com";
        OutputRelationDTO expectedDTO = new OutputRelationDTO(memberOneID, memberTwoID, newDesignation, relationID);
        Link selfLink = linkTo(methodOn(FamilyRESTController.class).getFamilyMembersAndRelations(familyID)).withSelfRel();
        expectedDTO.add(selfLink);

        ResponseEntity<OutputRelationDTO> expected = new ResponseEntity(expectedDTO, HttpStatus.OK);

        //Setup for mocking family in persistence
        FamilyJPA familyJPA = new FamilyJPA(new FamilyIDJPA("@tonyze@gmail.com"), "Zés", "27/05/2021", new PersonIDJPA("tonyze@gmail.com"));
        Optional<FamilyJPA> optionalFamilyJPA = Optional.of(familyJPA);

        FamilyIDJPA familyIDJPA = new FamilyIDJPA("@tonyze@gmail.com");
        when(familyDataDomainAssembler.createFamilyIDJPA(any(FamilyID.class))).thenReturn(familyIDJPA);
        when(iFamilyRepositoryJPA.findById(any(FamilyIDJPA.class))).thenReturn(optionalFamilyJPA);

        when(familyDataDomainAssembler.createFamilyID(any(FamilyJPA.class))).thenReturn(new FamilyID("@tonyze@gmail.com"));
        when(familyDataDomainAssembler.createFamilyName(any(FamilyJPA.class))).thenReturn(new FamilyName("Zés"));
        when(familyDataDomainAssembler.createRegistrationDate(any(FamilyJPA.class))).thenReturn(new RegistrationDate("27/05/2021"));
        when(familyDataDomainAssembler.createAdminID(any(FamilyJPA.class))).thenReturn(new PersonID("tonyze@gmail.com"));


        List<Relation> relationList = new ArrayList<>();
        Relation relation = new Relation(new PersonID("tony@gmail.com"), new PersonID("moonika@gmail.com"), new RelationDesignation("Marido"), new RelationID(123));
        relationList.add(relation);

        when(familyDataDomainAssembler.createRelationList(any(FamilyJPA.class))).thenReturn(relationList);

        FamilyJPA savedFamilyJPA = new FamilyJPA(new FamilyIDJPA("@tonyze@gmail.com"), "Zés", "27/05/2021", new PersonIDJPA("tonyze@gmail.com"));
        RelationJPA relationJPA = new RelationJPA(memberOneID, memberTwoID, newDesignation, 123, familyJPA);
        List<RelationJPA> relationJPAList = new ArrayList<>();
        relationJPAList.add(relationJPA);
        savedFamilyJPA.setRelationList(relationJPAList);
        when(iFamilyRepositoryJPA.save(any(FamilyJPA.class))).thenReturn(savedFamilyJPA);

        ResponseEntity<OutputRelationDTO> result = familyRESTController.changeRelation(changeRelationDTO, familyID, relationID);

        assertEquals(expected.getBody().toString(), result.getBody().toString());
        assertEquals(expected.getStatusCode(), result.getStatusCode());
    }
    @Test
    void changeRelationFailure() {
        Mockito.when(iFamilyRepositoryJPA.findById(any(FamilyIDJPA.class))).thenThrow(InvalidDataAccessApiUsageException.class);
        ResponseEntity<OutputRelationDTO> expected = new ResponseEntity("Error: ", HttpStatus.NOT_MODIFIED);
        ChangeRelationDTO changeRelationDTO = new ChangeRelationDTO("Designation");
        String familyID = "@fam@id.com";
        String relationID = "3";
        ResponseEntity<OutputRelationDTO> result = familyRESTController.changeRelation(changeRelationDTO, familyID, relationID);
        assertEquals(expected.getStatusCode(), result.getStatusCode());
    }
}