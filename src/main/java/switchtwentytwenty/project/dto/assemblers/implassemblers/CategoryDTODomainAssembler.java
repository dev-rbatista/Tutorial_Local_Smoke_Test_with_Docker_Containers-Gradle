package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.springframework.stereotype.Component;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.assemblers.iassemblers.ICategoryDTODomainAssembler;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;

import java.util.Optional;

@Component
public class CategoryDTODomainAssembler implements ICategoryDTODomainAssembler {

    @Override
    public OutputCategoryDTO toDTO(Category category) {
        OutputCategoryDTO outputCategoryDTO = new OutputCategoryDTO();
        outputCategoryDTO.setCategoryID(category.id().toString());
        outputCategoryDTO.setCategoryName(category.getCategoryName().toString());
        outputCategoryDTO.setParentID(category.getParentCategoryPath().toString());
        Optional<FamilyID> familyID = category.getFamilyID();
        if(familyID.isPresent()){
        outputCategoryDTO.setFamilyID(familyID.get().toString());
        }
        return outputCategoryDTO;
    }

    public CategoryName createCategoryName(String categoryName) {
        return new CategoryName(categoryName);
    }

    public ParentCategoryPath createParentCategoryPath(String parentCategory) {
        return new ParentCategoryPath(parentCategory);
    }

}