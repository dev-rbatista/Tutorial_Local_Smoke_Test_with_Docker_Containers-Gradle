package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.aggregates.category.CategoryFactory;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.assemblers.iassemblers.ICategoryDTODomainAssembler;
import switchtwentytwenty.project.dto.category.InputStandardCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.ICreateStandardCategoryService;
import switchtwentytwenty.project.usecaseservices.irepositories.ICategoryRepository;

@Service
public class CreateStandardCategoryService implements ICreateStandardCategoryService {

    private final ICategoryDTODomainAssembler categoryDTODomainAssembler;
    private ICategoryRepository categoryRepository;
    private CategoryFactory categoryFactory;

    @Autowired
    public CreateStandardCategoryService(ICategoryRepository categoryRepository, CategoryFactory categoryFactory, ICategoryDTODomainAssembler categoryDTODomainAssembler){
        this.categoryRepository = categoryRepository;
        this.categoryDTODomainAssembler = categoryDTODomainAssembler;
        this.categoryFactory = categoryFactory;
    }

    @Override
    public OutputCategoryDTO createStandardCategory(InputStandardCategoryDTO inputStandardCategoryDTO) {
        CategoryName categoryName = categoryDTODomainAssembler.createCategoryName(inputStandardCategoryDTO.getCategoryName());
        ParentCategoryPath parentCategory = categoryDTODomainAssembler.createParentCategoryPath(inputStandardCategoryDTO.getParentID());

        Category category = categoryFactory.createCategory(categoryName, parentCategory);

        Category savedStandardCategory = categoryRepository.add(category);

        return categoryDTODomainAssembler.toDTO(savedStandardCategory);
    }
}