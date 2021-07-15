package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.datamodel.assemblerjpa.iassemblersjpa.ICategoryDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.CategoryJPA;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.ICategoryRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.aggregates.category.CategoryFactory;
import switchtwentytwenty.project.domain.aggregates.category.CustomCategory;
import switchtwentytwenty.project.domain.aggregates.category.StandardCategory;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.assemblers.implassemblers.CategoryDTODomainAssembler;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryTreeDTO;
import switchtwentytwenty.project.interfaceadapters.implrepositories.CategoryRepository;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IGetCustomCategoriesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class GetCustomCategoriesServiceIT {

    @Autowired
    CategoryDTODomainAssembler categoryDTODomainAssembler;

    IGetCustomCategoriesService getCustomCategoriesService;

    @InjectMocks
    CategoryRepository categoryRepository;

    @Mock
    ICategoryRepositoryJPA categoryRepositoryJPA;

    @Mock
    ICategoryDataDomainAssembler categoryDataDomainAssembler;

    @Mock
    CategoryFactory categoryFactory;

    // Category Strings
    String stringStandardCategoryName = "STANDARD CATEGORY NAME";
    Long stringStandardCategoryID = 3L;
    String stringStandardCategoryParent = "path to parent category";

    String stringCustomCategoryName = "CUSTOM CATEGORY NAME";
    Long stringCustomCategoryID = 14L;
    String stringCustomCategoryParent = "path to parent category";
    String stringCustomCategoryFamilyID = "@tonyze@gmail.com";

    String stringCustomCategoryName2 = "CUSTOM CATEGORY NAME 2";
    Long stringCustomCategoryID2 = 15L;

    // Category Value Objects
    CategoryName standardCategoryName = new CategoryName(stringStandardCategoryName);
    CategoryID standardCategoryID = new CategoryID(stringStandardCategoryID);
    ParentCategoryPath standardCategoryParent = new ParentCategoryPath(stringStandardCategoryParent);

    CategoryName customCategoryName = new CategoryName(stringCustomCategoryName);
    CategoryID customCategoryID = new CategoryID(stringCustomCategoryID);
    ParentCategoryPath customCategoryParent = new ParentCategoryPath(stringCustomCategoryParent);
    FamilyID customCategoryFamilyID = new FamilyID(stringCustomCategoryFamilyID);

    CategoryName customCategoryName2 = new CategoryName(stringCustomCategoryName2);
    CategoryID customCategoryID2 = new CategoryID(stringCustomCategoryID2);
    ParentCategoryPath customCategoryParent2 = new ParentCategoryPath(stringCustomCategoryParent);
    FamilyID customCategoryFamilyID2 = new FamilyID(stringCustomCategoryFamilyID);

    // Categories
    Category standardCategory = new StandardCategory(standardCategoryName, standardCategoryID, standardCategoryParent);
    Category customCategory = new CustomCategory(customCategoryID,customCategoryParent, customCategoryName, customCategoryFamilyID);
    Category customCategory2 = new CustomCategory(customCategoryID2,customCategoryParent2, customCategoryName2, customCategoryFamilyID2);

    // CategoryOutputDTOs
    OutputCategoryDTO standardOutputCategoryDTO = new OutputCategoryDTO(stringStandardCategoryName, stringStandardCategoryID.toString(), stringStandardCategoryParent);
    OutputCategoryDTO customOutputCategoryDTO = new OutputCategoryDTO(stringCustomCategoryName, stringCustomCategoryID.toString(), stringCustomCategoryParent, stringCustomCategoryFamilyID);
    OutputCategoryDTO customOutputCategoryDTO2 = new OutputCategoryDTO(stringCustomCategoryName2, stringCustomCategoryID2.toString(), stringCustomCategoryParent, stringCustomCategoryFamilyID);

    // Category JPA
    CategoryJPA standardCategoryJPA = new CategoryJPA.Builder(stringStandardCategoryName).withCategoryIDJPA(stringStandardCategoryID).withParentID(stringStandardCategoryParent).build();
    CategoryJPA customCategoryJPA1 = new CategoryJPA.Builder(stringCustomCategoryName).withCategoryIDJPA(stringCustomCategoryID).withFamilyIDJPA(new FamilyIDJPA(stringCustomCategoryFamilyID)).withParentID(stringCustomCategoryParent).build();
    CategoryJPA customCategoryJPA2 = new CategoryJPA.Builder(stringCustomCategoryName2).withCategoryIDJPA(stringCustomCategoryID2).withFamilyIDJPA(new FamilyIDJPA(stringCustomCategoryFamilyID)).withParentID(stringCustomCategoryParent).build();

    @Test
    void getCategories() {
        String familyID = "@tonyze@email.com";

        OutputCategoryTreeDTO expected = new OutputCategoryTreeDTO();
        expected.addOutputCategoryDTO(standardOutputCategoryDTO);
        expected.addOutputCategoryDTO(customOutputCategoryDTO);
        expected.addOutputCategoryDTO(customOutputCategoryDTO2);

        List<CategoryJPA> standardCategoryJPAList = new ArrayList<>();
        standardCategoryJPAList.add(standardCategoryJPA);

        List<CategoryJPA> customCategoryJPAList = new ArrayList<>();
        customCategoryJPAList.add(customCategoryJPA1);
        customCategoryJPAList.add(customCategoryJPA2);

        when(categoryDataDomainAssembler.toData(any(FamilyID.class))).thenReturn(new FamilyIDJPA(stringCustomCategoryFamilyID));

        when(categoryRepositoryJPA.findAllByFamilyIDJPAIsNull()).thenReturn(standardCategoryJPAList);
        when(categoryRepositoryJPA.findAllByFamilyIDJPA(any(FamilyIDJPA.class))).thenReturn(customCategoryJPAList);

        when(categoryDataDomainAssembler.createCategoryID(any(CategoryJPA.class))).thenReturn(standardCategoryID).thenReturn(customCategoryID).thenReturn(customCategoryID2);
        when(categoryDataDomainAssembler.createCategoryName(any(CategoryJPA.class))).thenReturn(standardCategoryName).thenReturn(customCategoryName).thenReturn(customCategoryName2);
        when(categoryDataDomainAssembler.createFamilyID(any(CategoryJPA.class))).thenReturn(Optional.empty()).thenReturn(Optional.of(customCategoryFamilyID)).thenReturn(Optional.of(customCategoryFamilyID2));
        when(categoryDataDomainAssembler.createParentID(any(CategoryJPA.class))).thenReturn(standardCategoryParent).thenReturn(customCategoryParent).thenReturn(customCategoryParent2);

        when(categoryFactory.createCategory(any(), any(), any(), any())).thenReturn(standardCategory, customCategory, customCategory2);

        getCustomCategoriesService = new GetCustomCategoriesService(categoryRepository, categoryDTODomainAssembler);

        OutputCategoryTreeDTO result = getCustomCategoriesService.getCustomCategories(familyID);

        assertEquals(expected, result);

    }
}
