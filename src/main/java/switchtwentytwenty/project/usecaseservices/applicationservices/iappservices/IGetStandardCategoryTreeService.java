package switchtwentytwenty.project.usecaseservices.applicationservices.iappservices;

import switchtwentytwenty.project.dto.category.OutputCategoryTreeDTO;


public interface IGetStandardCategoryTreeService {

    OutputCategoryTreeDTO getStandardCategoryTreeOwn();

    OutputCategoryTreeDTO getStandardCategoryTreeAll();

}
