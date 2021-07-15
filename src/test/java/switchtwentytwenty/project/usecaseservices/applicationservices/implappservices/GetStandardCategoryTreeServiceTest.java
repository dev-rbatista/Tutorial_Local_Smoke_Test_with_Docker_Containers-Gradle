package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.aggregates.category.StandardCategory;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.assemblers.iassemblers.ICategoryDTODomainAssembler;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryTreeDTO;
import switchtwentytwenty.project.usecaseservices.irepositories.ICategoryRepository;
import switchtwentytwenty.project.usecaseservices.irepositories.IExternalCategoryRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetStandardCategoryTreeServiceTest {

    @Mock
    ICategoryRepository mockCategoryRepository;

    @Mock
    ICategoryDTODomainAssembler mockCategoryDTODomainAssembler;

    @Mock
    IExternalCategoryRepository mockExternalRepository;

    @InjectMocks
    GetStandardCategoryTreeService getStandardCategoryTreeService;

    String categoryName1 = "Bebedeira";
    String categoryID1 = "17L";
    String parentID1 = "5";
    OutputCategoryDTO outputCategoryDTO1 = new OutputCategoryDTO(categoryName1, categoryID1, parentID1);

    String categoryName2 = "Jantares";
    String categoryID2 = "18L";
    String parentID2 = "5";
    OutputCategoryDTO outputCategoryDTO2 = new OutputCategoryDTO(categoryName2, categoryID2, parentID2);

    // Category 1
    Long categoryID1long = 17L;
    CategoryName categoryName1x = new CategoryName(categoryName1);
    CategoryID categoryID1x = new CategoryID(categoryID1long);
    ParentCategoryPath parentCategoryPath1x = new ParentCategoryPath(parentID1);

    Category category1 = new StandardCategory(categoryName1x, categoryID1x, parentCategoryPath1x);

    // Category 2
    Long categoryID2long = 18L;
    CategoryName categoryName2x = new CategoryName(categoryName2);
    CategoryID categoryID2x = new CategoryID(categoryID2long);
    ParentCategoryPath parentCategoryPath2x = new ParentCategoryPath(parentID2);

    Category category2 = new StandardCategory(categoryName2x, categoryID2x, parentCategoryPath2x);

    @Test
    @DisplayName("Get Standard category Tree successfully")
    void getStandardCategoryTree_Success() {
        List<Category> categoryListx = new ArrayList<>();
        categoryListx.add(category1);
        categoryListx.add(category2);

        when(mockCategoryRepository.getStandardCategoryList()).thenReturn(categoryListx);
        when(mockCategoryDTODomainAssembler.toDTO(any(Category.class))).thenReturn(outputCategoryDTO1).thenReturn(outputCategoryDTO2);
        OutputCategoryTreeDTO result = getStandardCategoryTreeService.getStandardCategoryTreeOwn();

        OutputCategoryTreeDTO expected = new OutputCategoryTreeDTO();
        expected.addOutputCategoryDTO(outputCategoryDTO1);
        expected.addOutputCategoryDTO(outputCategoryDTO2);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get Standard category Tree successfully")
    void getStandardCategoryTreeAll_Success() {
        List<Category> categoryListOne = new ArrayList<>();
        categoryListOne.add(category1);
        List<Category> categoryListTwo = new ArrayList<>();
        categoryListTwo.add(category2);

        getStandardCategoryTreeService.setIExternalCategoryRepository(mockExternalRepository);
        when(mockCategoryRepository.getStandardCategoryList()).thenReturn(categoryListOne);
        when(mockCategoryDTODomainAssembler.toDTO(any(Category.class))).thenReturn(outputCategoryDTO1).thenReturn(outputCategoryDTO2);
        when(mockExternalRepository.getCategoryList()).thenReturn(categoryListTwo);
        OutputCategoryTreeDTO result = getStandardCategoryTreeService.getStandardCategoryTreeAll();

        OutputCategoryTreeDTO expected = new OutputCategoryTreeDTO();
        expected.addOutputCategoryDTO(outputCategoryDTO1);
        expected.addOutputCategoryDTO(outputCategoryDTO2);

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Fail to get Standard category Tree")
    void getStandardCategoryTree_Fail() {
        when(mockCategoryRepository.getStandardCategoryList()).thenThrow(NullPointerException.class);
        assertThrows(NullPointerException.class, () -> getStandardCategoryTreeService.getStandardCategoryTreeOwn());
    }

    @Test
    @DisplayName("Fail to get Standard category Tree from All")
    void getStandardCategoryTreeAll_Fail() {
        when(mockCategoryRepository.getStandardCategoryList()).thenThrow(NullPointerException.class);
        assertThrows(NullPointerException.class, () -> getStandardCategoryTreeService.getStandardCategoryTreeAll());
    }

    @Test
    void setIExternalCategoryRepositoryTest(){
        getStandardCategoryTreeService.setIExternalCategoryRepository(mockExternalRepository);

        IExternalCategoryRepository result = getStandardCategoryTreeService.getIExternalCategoryRepository();

        assertNotNull(result);
        assertEquals(result,mockExternalRepository);
    }

    @Test
    void setIExternalCategoryRepositoryTestDefaultExternalRepository(){
        IExternalCategoryRepository result = getStandardCategoryTreeService.getIExternalCategoryRepository();

        assertNotNull(result);
        assertNotEquals(result,mockExternalRepository);
    }
}