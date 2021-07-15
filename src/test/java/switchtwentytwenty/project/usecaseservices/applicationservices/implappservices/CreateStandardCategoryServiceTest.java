package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.aggregates.category.CategoryFactory;
import switchtwentytwenty.project.domain.aggregates.category.StandardCategory;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.assemblers.iassemblers.ICategoryDTODomainAssembler;
import switchtwentytwenty.project.dto.category.InputStandardCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;
import switchtwentytwenty.project.usecaseservices.irepositories.ICategoryRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateStandardCategoryServiceTest {

    @Mock
    ICategoryRepository categoryRepository;

    @Mock
    ICategoryDTODomainAssembler categoryDTODomainAssembler;

    @Mock
    CategoryFactory categoryFactory;

    @InjectMocks
    CreateStandardCategoryService createStandardCategoryService;

    @Test
    void createStandardCategorySuccess() {

        InputStandardCategoryDTO inputStandardCategoryDTO = new InputStandardCategoryDTO("Casa", "/sadsd/20043");

        CategoryName categoryName = new CategoryName("Casa");
        when(categoryDTODomainAssembler.createCategoryName(anyString())).thenReturn(categoryName);
        when(categoryDTODomainAssembler.createParentCategoryPath(anyString())).thenReturn(new ParentCategoryPath("/sadsd"));
        when(categoryFactory.createCategory(any(CategoryName.class), any(ParentCategoryPath.class))).thenReturn(new StandardCategory());
        when(categoryRepository.add(any(Category.class))).thenReturn(new StandardCategory());

        OutputCategoryDTO outputCategoryDTO = new OutputCategoryDTO("Casa", "20050", "/sadsd/20043");

        when(categoryDTODomainAssembler.toDTO(any(Category.class))).thenReturn(outputCategoryDTO);

        OutputCategoryDTO expected = new OutputCategoryDTO("Casa", "20050", "/sadsd/20043");

        OutputCategoryDTO result = createStandardCategoryService.createStandardCategory(inputStandardCategoryDTO);

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }

    @Test
    void createStandardCategoryFailureBlankName() {

        InputStandardCategoryDTO inputStandardCategoryDTO = new InputStandardCategoryDTO("", "/sadsd/20043");
        when(categoryDTODomainAssembler.createCategoryName("")).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> createStandardCategoryService.createStandardCategory(inputStandardCategoryDTO));
    }
}