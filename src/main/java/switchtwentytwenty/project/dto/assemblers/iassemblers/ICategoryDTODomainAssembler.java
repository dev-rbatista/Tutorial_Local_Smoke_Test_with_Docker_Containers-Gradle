package switchtwentytwenty.project.dto.assemblers.iassemblers;

import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;

public interface ICategoryDTODomainAssembler {

    OutputCategoryDTO toDTO(Category category);

    CategoryName createCategoryName(String categoryName);

    ParentCategoryPath createParentCategoryPath(String parentCategory);

}