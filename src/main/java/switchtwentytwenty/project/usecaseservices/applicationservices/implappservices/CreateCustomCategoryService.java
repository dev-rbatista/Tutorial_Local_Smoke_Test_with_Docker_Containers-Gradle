package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.aggregates.category.CategoryFactory;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.assemblers.iassemblers.ICategoryDTODomainAssembler;
import switchtwentytwenty.project.dto.category.InputCustomCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.ICreateCustomCategoryService;
import switchtwentytwenty.project.usecaseservices.irepositories.ICategoryRepository;

@Service
public class CreateCustomCategoryService implements ICreateCustomCategoryService {

    private final ICategoryRepository categoryRepository;

    private final ICategoryDTODomainAssembler categoryAssembler;

    private final CategoryFactory categoryFactory;

    @Autowired
    public CreateCustomCategoryService(ICategoryRepository categoryRepository, ICategoryDTODomainAssembler categoryAssembler, CategoryFactory categoryFactory) {
        this.categoryRepository = categoryRepository;
        this.categoryAssembler = categoryAssembler;
        this.categoryFactory = categoryFactory;
    }

    public OutputCategoryDTO createCustomCategory(InputCustomCategoryDTO inputCustomCategoryDTO) {
        CategoryName categoryName = categoryAssembler.createCategoryName(inputCustomCategoryDTO.getCategoryName());
        ParentCategoryPath parentCategoryPath = categoryAssembler.createParentCategoryPath(inputCustomCategoryDTO.getParentID());
        FamilyID familyID = new FamilyID(inputCustomCategoryDTO.getFamilyID());

        Category customCategory = categoryFactory.createCategory(categoryName, parentCategoryPath, familyID);

        Category savedCustomCategory = categoryRepository.add(customCategory);

        return categoryAssembler.toDTO(savedCustomCategory);
    }
}
