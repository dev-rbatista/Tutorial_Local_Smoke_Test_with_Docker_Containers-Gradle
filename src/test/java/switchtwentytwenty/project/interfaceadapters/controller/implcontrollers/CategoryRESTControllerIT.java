package switchtwentytwenty.project.interfaceadapters.controller.implcontrollers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.CategoryDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.CategoryJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.ICategoryRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.aggregates.category.CategoryFactory;
import switchtwentytwenty.project.domain.aggregates.category.StandardCategory;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.assemblers.implassemblers.CategoryDTODomainAssembler;
import switchtwentytwenty.project.dto.category.CreateCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;
import switchtwentytwenty.project.interfaceadapters.implrepositories.CategoryRepository;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.ICategoriesOptionsService;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IGetStandardCategoryTreeService;
import switchtwentytwenty.project.usecaseservices.applicationservices.implappservices.CreateStandardCategoryService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SpringBootTest
@RunWith(SpringRunner.class)
class CategoryRESTControllerIT {

    @Mock
    ICategoryRepositoryJPA categoryRepositoryJPA;

    @Mock
    CategoryFactory categoryFactory;

    @Mock
    CategoryDataDomainAssembler categoryDataDomainAssembler;

    @InjectMocks
    CategoryRepository categoryRepository;

    @Autowired
    CategoryFactory serviceCategoryFactory;

    @Autowired
    CategoryDTODomainAssembler categoryDTODomainAssembler;

    @Autowired
    ICategoriesOptionsService optionsService;

    @Autowired
    IGetStandardCategoryTreeService getStandardCategoryTreeService;


    @Test
    void createStandardCategory() {
        CreateStandardCategoryService createStandardCategoryService = new CreateStandardCategoryService(categoryRepository, serviceCategoryFactory, categoryDTODomainAssembler);
        CategoryRESTController categoryRESTController = new CategoryRESTController(createStandardCategoryService, optionsService, getStandardCategoryTreeService);


        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO("Casa", "/casdsa");

        Category category = new StandardCategory(new CategoryName("Casa"), new CategoryID(0l), new ParentCategoryPath("/casdsa"));

        when(categoryDataDomainAssembler.toData(any(Category.class))).thenReturn(new CategoryJPA());
        when(categoryRepositoryJPA.save(any(CategoryJPA.class))).thenReturn(new CategoryJPA());
        when(categoryDataDomainAssembler.createCategoryID(any(CategoryJPA.class))).thenReturn(new CategoryID(2l));
        when(categoryDataDomainAssembler.createCategoryName(any(CategoryJPA.class))).thenReturn(new CategoryName("Casa"));
        when(categoryDataDomainAssembler.createParentID(any(CategoryJPA.class))).thenReturn(new ParentCategoryPath("/casdsa"));
        when(categoryDataDomainAssembler.createFamilyID(any(CategoryJPA.class))).thenReturn(Optional.empty());


        when(categoryFactory.createCategory(any(CategoryID.class), any(CategoryName.class), any(ParentCategoryPath.class), any(Optional.class))).thenReturn(category);

        OutputCategoryDTO expectedOutputCategoryDTO = new OutputCategoryDTO("Casa", "0", "/casdsa");
        HttpStatus status = HttpStatus.CREATED;
        Link selfLink = linkTo(methodOn(CategoryRESTController.class).getCategory(expectedOutputCategoryDTO.getCategoryID().toString())).withSelfRel();


        expectedOutputCategoryDTO.add(selfLink);

        ResponseEntity expected = new ResponseEntity(expectedOutputCategoryDTO, status);

        ResponseEntity result = categoryRESTController.createStandardCategory(createCategoryDTO);

        assertEquals(expected.toString(), result.toString());
    }
}