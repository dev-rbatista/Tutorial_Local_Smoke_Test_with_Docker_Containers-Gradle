package switchtwentytwenty.project.usecaseservices.applicationservices.iappservices;

import switchtwentytwenty.project.dto.category.InputCustomCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;

public interface ICreateCustomCategoryService {

    OutputCategoryDTO createCustomCategory(InputCustomCategoryDTO inputCustomCategoryDTO);
}
