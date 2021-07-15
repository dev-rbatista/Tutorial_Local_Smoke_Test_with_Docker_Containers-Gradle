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
import switchtwentytwenty.project.dto.assemblers.implassemblers.ExternalCategoryDTODomainAssemblerGroupTwo;
import switchtwentytwenty.project.dto.category.ExternalStandardCategoryGroupTwoDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExternalCategoryRepositoryGroupTwoTest {

    @Mock
    ExternalCategoryDTODomainAssemblerGroupTwo assemblerGroupTwo;

    @Mock
    CategoryFactory categoryFactory;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    ExternalCategoryRepositoryGroupTwo externalCategoryRepositoryGroupTwo;

    CategoryName categoryName = new CategoryName("CASA");
    CategoryID categoryID = new CategoryID("12");
    ParentCategoryPath parentID = new ParentCategoryPath("10");
    Category category = new StandardCategory(categoryName, categoryID, parentID);

    @Test
    void getCategoryListTest() {
        externalCategoryRepositoryGroupTwo.setResource("http://localhost:8080");
        ExternalStandardCategoryGroupTwoDTO dtoOne = new ExternalStandardCategoryGroupTwoDTO("CASA", "12", "10");
        ExternalStandardCategoryGroupTwoDTO[] dtoArray = new ExternalStandardCategoryGroupTwoDTO[1];
        dtoArray[0] = dtoOne;

        when(restTemplate.getForObject(anyString(), any())).thenReturn(dtoArray);
        when(assemblerGroupTwo.createCategoryName(any(ExternalStandardCategoryGroupTwoDTO.class))).thenReturn(categoryName);
        when(assemblerGroupTwo.createCategoryID(any(ExternalStandardCategoryGroupTwoDTO.class))).thenReturn(categoryID);
        when(assemblerGroupTwo.createParentID(any(ExternalStandardCategoryGroupTwoDTO.class))).thenReturn(parentID);
        when(categoryFactory.createCategory(any(CategoryName.class), any(CategoryID.class), any(ParentCategoryPath.class))).thenReturn(category);

        List<Category> expected = new ArrayList<>();
        expected.add(category);

        List<Category> result = externalCategoryRepositoryGroupTwo.getCategoryList();

        assertEquals(expected, result);
    }

    @Test
    void setResourceTest() {
        externalCategoryRepositoryGroupTwo.setResource("http://localhost:8080/categories");

        String expected = "http://localhost:8080/categories";

        String result = externalCategoryRepositoryGroupTwo.getResource();

        assertEquals(expected, result);
    }
}