package switchtwentytwenty.project.interfaceadapters.implrepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.web.client.RestTemplate;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.aggregates.category.CategoryFactory;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.dto.assemblers.implassemblers.ExternalCategoryDTODomainAssemblerGroupFour;
import switchtwentytwenty.project.dto.category.ExternalStandardCategoryGroupFourDTO;
import switchtwentytwenty.project.usecaseservices.irepositories.IExternalCategoryRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ExternalCategoryRepositoryGroupFour implements IExternalCategoryRepository {

    private final ExternalCategoryDTODomainAssemblerGroupFour externalCategoryDTODomainAssemblerGroupFour;

    private final CategoryFactory categoryFactory;

    private final RestTemplate restTemplate;



    private String resource;

    @Autowired
    public ExternalCategoryRepositoryGroupFour(ExternalCategoryDTODomainAssemblerGroupFour externalCategoryDTODomainAssemblerGroupFour, CategoryFactory categoryFactory, RestTemplate restTemplate) {
        this.externalCategoryDTODomainAssemblerGroupFour = externalCategoryDTODomainAssemblerGroupFour;
        this.categoryFactory = categoryFactory;
        this.restTemplate = restTemplate;
    }

    private List<ExternalStandardCategoryGroupFourDTO> findAll() {
        return Arrays.stream(restTemplate.getForObject(resource, ExternalStandardCategoryGroupFourDTO[].class)).collect(Collectors.toList());
    }

    private Category createCategory(ExternalStandardCategoryGroupFourDTO externalStandardCategoryGroupFourDTO) {

        CategoryName name = externalCategoryDTODomainAssemblerGroupFour.createCategoryName(externalStandardCategoryGroupFourDTO);
        CategoryID id = externalCategoryDTODomainAssemblerGroupFour.createCategoryID(externalStandardCategoryGroupFourDTO);
        ParentCategoryPath parentID = externalCategoryDTODomainAssemblerGroupFour.createParentID(externalStandardCategoryGroupFourDTO);

        return categoryFactory.createCategory(name, id, parentID);
    }

    @Override
    public List<Category> getCategoryList() {
        List<ExternalStandardCategoryGroupFourDTO> externalStandardCategoryGroupFourDTOSList = findAll();

        List<Category> categoryList = new ArrayList<>();

        for (ExternalStandardCategoryGroupFourDTO externalStandardCategoryGroupFourDTOS : externalStandardCategoryGroupFourDTOSList) {
            Category category = createCategory(externalStandardCategoryGroupFourDTOS);
            categoryList.add(category);
        }

        return categoryList;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
