package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.springframework.stereotype.Component;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.category.ExternalStandardCategoryGroupFourDTO;

@Component
public class ExternalCategoryDTODomainAssemblerGroupFour {

    public CategoryID createCategoryID(ExternalStandardCategoryGroupFourDTO externalStandardCategoryGroupFourDTO) {
        return new CategoryID(externalStandardCategoryGroupFourDTO.getId());
    }

    public CategoryName createCategoryName(ExternalStandardCategoryGroupFourDTO externalStandardCategoryGroupFourDTO) {
        return new CategoryName(externalStandardCategoryGroupFourDTO.getName());
    }

    public ParentCategoryPath createParentID(ExternalStandardCategoryGroupFourDTO externalStandardCategoryGroupFourDTO) {
        return new ParentCategoryPath(externalStandardCategoryGroupFourDTO.getParentID());
    }
}
