package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.datamodel.assemblerjpa.iassemblersjpa.ICategoryDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.CategoryJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.ICategoryRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.aggregates.category.CategoryFactory;
import switchtwentytwenty.project.domain.aggregates.category.StandardCategory;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.assemblers.implassemblers.CategoryDTODomainAssembler;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryTreeDTO;
import switchtwentytwenty.project.interfaceadapters.implrepositories.CategoryRepository;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IGetStandardCategoryTreeService;
import switchtwentytwenty.project.usecaseservices.irepositories.IExternalCategoryRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class GetStandardCategoryTreeServiceIT {

    @Autowired
    CategoryDTODomainAssembler categoryDTODomainAssembler;

    @Autowired
    IExternalCategoryRepository externalCategoryRepository;

    IGetStandardCategoryTreeService service;

    @InjectMocks
    CategoryRepository categoryRepository;

    @Mock
    ICategoryRepositoryJPA categoryRepositoryJPA;

    @Mock
    ICategoryDataDomainAssembler categoryDataDomainAssembler;

    @Mock
    CategoryFactory categoryFactory;

    // OutputCategoryDTO 1
    String categoryName1 = "BEBEDEIRA";
    String categoryID1 = "17";
    String parentID1 = "5";
    Category category = new StandardCategory(new CategoryName(categoryName1), new CategoryID(17L), new ParentCategoryPath(parentID1));
    OutputCategoryDTO outputCategoryDTO1 = new OutputCategoryDTO(categoryName1, categoryID1, parentID1);

    // OutputCategoryDTO 2
    String categoryName2 = "JANTARES";
    String categoryID2 = "18";
    String parentID2 = "5";
    Category categoryTwo = new StandardCategory(new CategoryName(categoryName2), new CategoryID(18L), new ParentCategoryPath(parentID2));
    OutputCategoryDTO outputCategoryDTO2 = new OutputCategoryDTO(categoryName2, categoryID2, parentID2);

    // Category 1 JPA
    CategoryJPA categoryJPA = new CategoryJPA();
    CategoryJPA categoryJPATwo = new CategoryJPA();

    // Category 1
    Long categoryID1long = 17L;
    CategoryName categoryName1x = new CategoryName(categoryName1);
    CategoryID categoryID1x = new CategoryID(categoryID1long);
    ParentCategoryPath parentCategoryPath1x = new ParentCategoryPath(parentID1);

    // Category 2
    Long categoryID2long = 18L;
    CategoryName categoryName2x = new CategoryName(categoryName2);
    CategoryID categoryID2x = new CategoryID(categoryID2long);
    ParentCategoryPath parentCategoryPath2x = new ParentCategoryPath(parentID2);


    @DisplayName("Get Standard Category Tree Successfully")
    @org.junit.jupiter.api.Test
    void getStandardCategoryTree() {

        // CategoryList
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        categoryList.add(categoryTwo);

        // CategoryJPAList
        List<CategoryJPA> categoryJPAList = new ArrayList<>();
        categoryJPAList.add(categoryJPA);
        categoryJPAList.add(categoryJPATwo);

        // Get CategoryJPA list
        when(categoryRepositoryJPA.findAllByFamilyIDJPAIsNull()).thenReturn(categoryJPAList);

        // Create Value Objects Category 1 from Data to Domain
        when(categoryDataDomainAssembler.createCategoryID(any(CategoryJPA.class))).thenReturn(categoryID1x).thenReturn(categoryID2x);
        when(categoryDataDomainAssembler.createCategoryName(any(CategoryJPA.class))).thenReturn(categoryName1x).thenReturn(categoryName2x);
        when(categoryDataDomainAssembler.createParentID(any(CategoryJPA.class))).thenReturn(parentCategoryPath1x).thenReturn(parentCategoryPath2x);

        when(categoryFactory.createCategory(any(), any(), any(), any())).thenReturn(category).thenReturn(categoryTwo);
        // Create Value Objects Category 2 from Data to Domain
        //when(categoryDataDomainAssembler.createCategoryID(any(CategoryJPA.class))).thenReturn(categoryID2x);
        //when(categoryDataDomainAssembler.createCategoryName(any(CategoryJPA.class))).thenReturn(categoryName2x);
        //when(categoryDataDomainAssembler.createParentID(any(CategoryJPA.class))).thenReturn(parentCategoryPath2x);

        // Create Category 1 and 2
        //Category category1 = new StandardCategory(categoryName1x, categoryID1x, parentCategoryPath1x);
        //Category category2 = new StandardCategory(categoryName2x, categoryID2x, parentCategoryPath2x);

        // Add Categories to List
        //categoryList.add(category1);
        //categoryList.add(category2);

        //when(categoryRepository.getStandardCategoryList()).thenReturn(categoryList);

        service = new GetStandardCategoryTreeService(categoryRepository, categoryDTODomainAssembler);

        OutputCategoryTreeDTO result = service.getStandardCategoryTreeOwn();

        OutputCategoryTreeDTO expected = new OutputCategoryTreeDTO();
        expected.addOutputCategoryDTO(outputCategoryDTO1);
        expected.addOutputCategoryDTO(outputCategoryDTO2);

        //assertDoesNotThrow(()-> service.getStandardCategoryTree());
        assertEquals(expected, result);


    }
}