package switchtwentytwenty.project.interfaceadapters.implrepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.aggregates.category.CategoryFactory;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.assemblers.implassemblers.ExternalCategoryDTODomainAssemblerGroupTwo;
import switchtwentytwenty.project.dto.category.ExternalStandardCategoryGroupTwoDTO;
import switchtwentytwenty.project.usecaseservices.irepositories.IExternalCategoryRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExternalCategoryRepositoryGroupTwo implements IExternalCategoryRepository {

    private final ExternalCategoryDTODomainAssemblerGroupTwo externalCategoryDTODomainAssemblerGroupTwo;

    private final CategoryFactory categoryFactory;

    private final RestTemplate restTemplate;

    private String resource;

    @Autowired
    public ExternalCategoryRepositoryGroupTwo(ExternalCategoryDTODomainAssemblerGroupTwo externalCategoryDTODomainAssemblerGroupTwo, CategoryFactory categoryFactory, RestTemplate restTemplate) {
        this.externalCategoryDTODomainAssemblerGroupTwo = externalCategoryDTODomainAssemblerGroupTwo;
        this.categoryFactory = categoryFactory;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<>();
        List<ExternalStandardCategoryGroupTwoDTO> externalStandardCategoryGroupTwoDTOSList = findAll();

        for (ExternalStandardCategoryGroupTwoDTO externalStandardCategoryGroupTwoDTOS : externalStandardCategoryGroupTwoDTOSList) {
            Category category = createCategory(externalStandardCategoryGroupTwoDTOS);
            categoryList.add(category);
        }

        return categoryList;
    }

    private List<ExternalStandardCategoryGroupTwoDTO> findAll() {
        return Arrays.stream(restTemplate.getForObject(resource, ExternalStandardCategoryGroupTwoDTO[].class)).collect(Collectors.toList());
    }

    private Category createCategory(ExternalStandardCategoryGroupTwoDTO externalStandardCategoryGroupTwoDTO) {
        CategoryName name = externalCategoryDTODomainAssemblerGroupTwo.createCategoryName(externalStandardCategoryGroupTwoDTO);
        CategoryID id = externalCategoryDTODomainAssemblerGroupTwo.createCategoryID(externalStandardCategoryGroupTwoDTO);
        ParentCategoryPath parentID = externalCategoryDTODomainAssemblerGroupTwo.createParentID(externalStandardCategoryGroupTwoDTO);

        return categoryFactory.createCategory(name, id, parentID);
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
