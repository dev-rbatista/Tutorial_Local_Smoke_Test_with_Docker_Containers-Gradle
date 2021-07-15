package switchtwentytwenty.project.usecaseservices.irepositories;

import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.FamilyID;

import java.util.List;

public interface ICategoryRepository extends Repository<Category, CategoryID> {

    Category add(Category category);

    List<Category> getStandardCategoryList();

    List<Category> getCustomCategoryList(FamilyID familyID);
}
