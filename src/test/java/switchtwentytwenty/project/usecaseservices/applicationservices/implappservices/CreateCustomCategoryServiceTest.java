package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.datamodel.repositoryjpa.ICategoryRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.aggregates.category.CategoryFactory;
import switchtwentytwenty.project.domain.aggregates.category.CustomCategory;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.assemblers.iassemblers.ICategoryDTODomainAssembler;
import switchtwentytwenty.project.dto.category.InputCustomCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;
import switchtwentytwenty.project.usecaseservices.irepositories.ICategoryRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCustomCategoryServiceTest {

    @Mock
    ICategoryDTODomainAssembler categoryAssembler;

    @Mock
    CategoryFactory categoryFactory;

    @Mock
    ICategoryRepository categoryRepository;

    @InjectMocks
    CreateCustomCategoryService createCustomCategoryService;

    @Mock
    ICategoryRepositoryJPA categoryRepositoryJPA;

    @Test
    void createCustomCategorySuccess() {

        InputCustomCategoryDTO inputCustomCategoryDTO = new InputCustomCategoryDTO("ramboia", "/balelas/123", "@tonyze@latinlover.com");

        CategoryName categoryName = new CategoryName("ramboia");

        when(categoryAssembler.createCategoryName(anyString())).thenReturn(categoryName);
        when(categoryAssembler.createParentCategoryPath(anyString())).thenReturn(new ParentCategoryPath("/balelas/123"));
        when(categoryFactory.createCategory(any(CategoryName.class), any(ParentCategoryPath.class), any(FamilyID.class))).thenReturn(new CustomCategory());
        when(categoryRepository.add(any(Category.class))).thenReturn(new CustomCategory());

        OutputCategoryDTO outputCategoryDTO = new OutputCategoryDTO("ramboia", "123", "/balelas/123", "@tonyze@latinlover.com");

        when(categoryAssembler.toDTO(any(Category.class))).thenReturn(outputCategoryDTO);

        OutputCategoryDTO expected = new OutputCategoryDTO("ramboia", "123", "/balelas/123", "@tonyze@latinlover.com");

        OutputCategoryDTO result = createCustomCategoryService.createCustomCategory(inputCustomCategoryDTO);

        assertEquals(expected, result);
        assertNotSame(expected, result);

    }

    @Test
    void createCustomCategoryFailurePathCategoryBlank() {
        CategoryName categoryName = new CategoryName("ramboia");
        InputCustomCategoryDTO inputCustomCategoryDTO = new InputCustomCategoryDTO("balelas", "", "@tonyze@latinlover.com");
        when(categoryAssembler.createCategoryName(anyString())).thenReturn(categoryName);
        when(categoryAssembler.createParentCategoryPath(anyString())).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class,  () -> createCustomCategoryService.createCustomCategory(inputCustomCategoryDTO));
    }
}