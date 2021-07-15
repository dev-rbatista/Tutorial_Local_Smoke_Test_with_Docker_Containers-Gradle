package switchtwentytwenty.project.usecaseservices.applicationservices.iappservices;

import switchtwentytwenty.project.dto.category.InputStandardCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;

public interface ICreateStandardCategoryService {

    OutputCategoryDTO createStandardCategory(InputStandardCategoryDTO inputStandardCategoryDTO);
}
