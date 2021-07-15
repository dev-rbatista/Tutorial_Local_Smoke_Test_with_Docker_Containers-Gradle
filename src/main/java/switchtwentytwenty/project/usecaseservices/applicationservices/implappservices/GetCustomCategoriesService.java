package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.dto.assemblers.iassemblers.ICategoryDTODomainAssembler;
import switchtwentytwenty.project.dto.category.OutputCategoryTreeDTO;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IGetCustomCategoriesService;
import switchtwentytwenty.project.usecaseservices.irepositories.ICategoryRepository;

import java.util.List;

@Service
public class GetCustomCategoriesService implements IGetCustomCategoriesService {

    ICategoryRepository categoryRepository;
    ICategoryDTODomainAssembler categoryDTODomainAssembler;

    @Autowired
    public GetCustomCategoriesService(ICategoryRepository categoryRepository, ICategoryDTODomainAssembler categoryDTODomainAssembler) {
        this.categoryRepository = categoryRepository;
        this.categoryDTODomainAssembler = categoryDTODomainAssembler;
    }

    @Override
    public OutputCategoryTreeDTO getCustomCategories(String stringFamilyID) {
        FamilyID familyID = new FamilyID(stringFamilyID);

        List<Category> standardCategories = categoryRepository.getStandardCategoryList();
        List<Category> customCategories = categoryRepository.getCustomCategoryList(familyID);

        OutputCategoryTreeDTO outputCategoryTreeDTO = new OutputCategoryTreeDTO();

        for (Category category : standardCategories) {
            outputCategoryTreeDTO.addOutputCategoryDTO(this.categoryDTODomainAssembler.toDTO(category));
        }

        for (Category category : customCategories) {
            outputCategoryTreeDTO.addOutputCategoryDTO(this.categoryDTODomainAssembler.toDTO(category));
        }

        return outputCategoryTreeDTO;
    }
}
