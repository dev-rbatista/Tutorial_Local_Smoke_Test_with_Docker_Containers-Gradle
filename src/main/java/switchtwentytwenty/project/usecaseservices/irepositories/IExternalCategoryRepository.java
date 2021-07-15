package switchtwentytwenty.project.usecaseservices.irepositories;

import switchtwentytwenty.project.domain.aggregates.category.Category;

import java.util.List;

public interface IExternalCategoryRepository {

    List<Category> getCategoryList();

}
