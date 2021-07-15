package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.springframework.stereotype.Component;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.category.ExternalStandardCategoryGroupTwoDTO;

@Component
public class ExternalCategoryDTODomainAssemblerGroupTwo {

    public CategoryID createCategoryID(ExternalStandardCategoryGroupTwoDTO externalStandardCategoryGroupTwoDTO) {
        return new CategoryID(externalStandardCategoryGroupTwoDTO.getId());
    }

    public CategoryName createCategoryName(ExternalStandardCategoryGroupTwoDTO externalStandardCategoryGroupTwoDTO) {
        return new CategoryName(externalStandardCategoryGroupTwoDTO.getDesignation());
    }

    public ParentCategoryPath createParentID(ExternalStandardCategoryGroupTwoDTO externalStandardCategoryGroupTwoDTO) {
        return new ParentCategoryPath(externalStandardCategoryGroupTwoDTO.getParentID());
    }
}
