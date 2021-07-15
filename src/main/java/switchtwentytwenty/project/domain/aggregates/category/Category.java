package switchtwentytwenty.project.domain.aggregates.category;

import switchtwentytwenty.project.domain.aggregates.AggregateRoot;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;

import java.util.Optional;

public interface Category extends AggregateRoot<CategoryID> {

    CategoryName getCategoryName();

    ParentCategoryPath getParentCategoryPath();

    Optional<FamilyID> getFamilyID();
}
