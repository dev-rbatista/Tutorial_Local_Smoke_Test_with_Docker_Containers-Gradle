package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.dto.assemblers.iassemblers.ICategoryDTODomainAssembler;
import switchtwentytwenty.project.dto.category.OutputCategoryDTO;
import switchtwentytwenty.project.dto.category.OutputCategoryTreeDTO;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IGetStandardCategoryTreeService;
import switchtwentytwenty.project.usecaseservices.irepositories.ICategoryRepository;
import switchtwentytwenty.project.usecaseservices.irepositories.IExternalCategoryRepository;

import java.util.List;

@Service
public class GetStandardCategoryTreeService implements IGetStandardCategoryTreeService {

    private final ICategoryRepository categoryRepository;
    private final ICategoryDTODomainAssembler categoryDTODomainAssembler;
    private final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:external-config.xml");
    private IExternalCategoryRepository externalCategoryRepository = (IExternalCategoryRepository) applicationContext.getBean("externalCategories");


    @Autowired
    public GetStandardCategoryTreeService(ICategoryRepository categoryRepository, ICategoryDTODomainAssembler categoryDTODomainAssembler) {
        this.categoryRepository = categoryRepository;
        this.categoryDTODomainAssembler = categoryDTODomainAssembler;
    }

    public void setIExternalCategoryRepository(IExternalCategoryRepository externalCategoryRepository) {
        this.externalCategoryRepository = externalCategoryRepository;
    }

    public IExternalCategoryRepository getIExternalCategoryRepository(){
        return this.externalCategoryRepository;
    }

    public OutputCategoryTreeDTO getStandardCategoryTreeOwn(){
        List<Category> categoryList = categoryRepository.getStandardCategoryList();

        return createStandardCategoryTreeDTO(categoryList);
    }

    public OutputCategoryTreeDTO getStandardCategoryTreeAll() {
        List<Category> categoryList = categoryRepository.getStandardCategoryList();
        categoryList.addAll(externalCategoryRepository.getCategoryList());

        return createStandardCategoryTreeDTO(categoryList);
    }

    private OutputCategoryTreeDTO createStandardCategoryTreeDTO(List<Category> categoryList){
        OutputCategoryTreeDTO outputCategoryTreeDTO = new OutputCategoryTreeDTO();

        for (Category category: categoryList) {
            OutputCategoryDTO outputCategoryDTO = categoryDTODomainAssembler.toDTO(category);
            outputCategoryTreeDTO.addOutputCategoryDTO(outputCategoryDTO);
        }

        return outputCategoryTreeDTO;
    }

}
