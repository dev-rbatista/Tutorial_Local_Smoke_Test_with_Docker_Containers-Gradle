package switchtwentytwenty.project.interfaceadapters.implrepositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchtwentytwenty.project.datamodel.assemblerjpa.iassemblersjpa.ICategoryDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.CategoryIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.CategoryJPA;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.ICategoryRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.aggregates.category.CategoryFactory;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;
import switchtwentytwenty.project.usecaseservices.irepositories.ICategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository implements ICategoryRepository {

    private final ICategoryRepositoryJPA categoryRepositoryJPA;
    private final ICategoryDataDomainAssembler categoryAssembler;
    private final CategoryFactory categoryFactory;

    @Autowired
    public CategoryRepository(ICategoryRepositoryJPA categoryRepositoryJPA, ICategoryDataDomainAssembler categoryAssembler, CategoryFactory categoryFactory) {
        this.categoryRepositoryJPA = categoryRepositoryJPA;
        this.categoryAssembler = categoryAssembler;
        this.categoryFactory = categoryFactory;
    }


    @Override
    public Category getByID(CategoryID id) {

        CategoryIDJPA categoryIDJPA = categoryAssembler.toData(id);
        Optional<CategoryJPA> categoryJPA = categoryRepositoryJPA.findById(categoryIDJPA);
        if (categoryJPA.isPresent()) {
            return createCategory(categoryJPA.get());
        } else {
            throw new IllegalArgumentException("Category does not exist");
        }
    }

    @Override
    public Category add(Category category) {
        CategoryJPA categoryJPA = categoryAssembler.toData(category);
        CategoryJPA registeredCategoryJPA = categoryRepositoryJPA.save(categoryJPA);

        return createCategory(registeredCategoryJPA);
    }


    @Override
    public List<Category> getCustomCategoryList(FamilyID familyID) {
        FamilyIDJPA familyIDJPA = categoryAssembler.toData(familyID);
        List<CategoryJPA> customCategoryJPAList = categoryRepositoryJPA.findAllByFamilyIDJPA(familyIDJPA);
        return convertCategoryJPAListToCategoryList(customCategoryJPAList);

    }


    public List<Category> getStandardCategoryList() {
        List<CategoryJPA> categoryListJPA;
        categoryListJPA = categoryRepositoryJPA.findAllByFamilyIDJPAIsNull();
        return convertCategoryJPAListToCategoryList(categoryListJPA);
    }

    private List<Category> convertCategoryJPAListToCategoryList(List<CategoryJPA> categoryJPAList) {
        List<Category> categoryList = new ArrayList<>();
        for (CategoryJPA jpa : categoryJPAList) {
            categoryList.add(createCategory(jpa));
        }
        return categoryList;
    }

    private Category createCategory(CategoryJPA categoryJPA) {

        CategoryID id = categoryAssembler.createCategoryID(categoryJPA);
        CategoryName name = categoryAssembler.createCategoryName(categoryJPA);
        ParentCategoryPath parentID = categoryAssembler.createParentID(categoryJPA);
        Optional<FamilyID> familyID = categoryAssembler.createFamilyID(categoryJPA);

        return categoryFactory.createCategory(id, name, parentID, familyID);
    }

}