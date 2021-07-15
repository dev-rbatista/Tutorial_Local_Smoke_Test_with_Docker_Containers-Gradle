package switchtwentytwenty.project.domain.aggregates.category;

import org.springframework.stereotype.Component;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;

import java.util.Optional;

@Component
public class CategoryFactory {

    public Category createCategory(CategoryID id, CategoryName name, ParentCategoryPath parentID, Optional<FamilyID> familyID) {

        Category category;
        if (familyID.isPresent()) {
            category = new CustomCategory(id, parentID, name, familyID.get());
        } else {
            category = new StandardCategory(name, id, parentID);
        }

        return category;
    }

    public Category createCategory(CategoryName name, ParentCategoryPath parentID) {

        Category category;

        category = new StandardCategory(name, parentID);

        return category;
    }

    public Category createCategory(CategoryName name, CategoryID id, ParentCategoryPath parentID) {
        Category category;

        category = new StandardCategory(name, id, parentID);

        return category;
    }

    public Category createCategory(CategoryName name, ParentCategoryPath parentID, FamilyID familyID) {

        Category category;

        category = new CustomCategory(parentID, name, familyID);

        return category;
    }
}
