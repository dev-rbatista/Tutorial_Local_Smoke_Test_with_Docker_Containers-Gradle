package switchtwentytwenty.project.interfaceadapters.implrepositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.aggregates.category.CategoryFactory;
import switchtwentytwenty.project.domain.aggregates.category.StandardCategory;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.assemblers.implassemblers.ExternalCategoryDTODomainAssemblerGroupFour;
import switchtwentytwenty.project.dto.category.ExternalStandardCategoryGroupFourDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExternalCategoryRepositoryGroupFourTest {

    @Mock
    ExternalCategoryDTODomainAssemblerGroupFour assemblerGroupFour;

    @Mock
    CategoryFactory categoryFactory;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    ExternalCategoryRepositoryGroupFour externalCategoryRepositoryGroupFour;

    CategoryName categoryName = new CategoryName("CASA");
    CategoryID categoryID = new CategoryID("12");
    ParentCategoryPath parentID = new ParentCategoryPath("10");
    Category category = new StandardCategory(categoryName,categoryID,parentID);

    @Test
    void getCategoryListTest() {
        externalCategoryRepositoryGroupFour.setResource("http://localhost:8080");
        ExternalStandardCategoryGroupFourDTO dtoOne = new ExternalStandardCategoryGroupFourDTO("CASA","12","10");
        ExternalStandardCategoryGroupFourDTO[] dtoArray = new ExternalStandardCategoryGroupFourDTO[1];
        dtoArray[0] = dtoOne;

        when(restTemplate.getForObject(anyString(),any())).thenReturn(dtoArray);
        when(assemblerGroupFour.createCategoryName(any(ExternalStandardCategoryGroupFourDTO.class))).thenReturn(categoryName);
        when(assemblerGroupFour.createCategoryID(any(ExternalStandardCategoryGroupFourDTO.class))).thenReturn(categoryID);
        when(assemblerGroupFour.createParentID(any(ExternalStandardCategoryGroupFourDTO.class))).thenReturn(parentID);
        when(categoryFactory.createCategory(any(CategoryName.class),any(CategoryID.class),any(ParentCategoryPath.class))).thenReturn(category);

        List<Category> expected = new ArrayList<>();
        expected.add(category);

        List<Category> result = externalCategoryRepositoryGroupFour.getCategoryList();

        assertEquals(expected,result);
    }

    @Test
    void setResourceTest() {
        externalCategoryRepositoryGroupFour.setResource("http://localhost:8080/categories");

        String expected = "http://localhost:8080/categories";

        String result = externalCategoryRepositoryGroupFour.getResource();

        assertEquals(expected,result);
    }
}