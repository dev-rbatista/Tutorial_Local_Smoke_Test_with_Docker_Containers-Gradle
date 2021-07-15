package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.springframework.stereotype.Component;
import switchtwentytwenty.project.dto.category.CreateCategoryDTO;
import switchtwentytwenty.project.dto.category.InputCustomCategoryDTO;
import switchtwentytwenty.project.dto.category.InputStandardCategoryDTO;

@Component
public class CategoryInputDTOAssembler {

    public InputStandardCategoryDTO toInputStandardCategoryDTO(CreateCategoryDTO createCategoryDTO) {
        String description = createCategoryDTO.getCategoryDescription();
        String parentID = createCategoryDTO.getParentCategory();

        return new InputStandardCategoryDTO(description, parentID);
    }

    public InputCustomCategoryDTO toInputCustomCategoryDTO(CreateCategoryDTO createCategoryDTO, String familyID) {
        String description = createCategoryDTO.getCategoryDescription();
        String parentID = createCategoryDTO.getParentCategory();

        return new InputCustomCategoryDTO(description, parentID, familyID);
    }

}
