package switchtwentytwenty.project.usecaseservices.applicationservices.iappservices;

import switchtwentytwenty.project.dto.category.OutputCategoryTreeDTO;

public interface IGetCustomCategoriesService {

    OutputCategoryTreeDTO getCustomCategories(String familyID);

}
