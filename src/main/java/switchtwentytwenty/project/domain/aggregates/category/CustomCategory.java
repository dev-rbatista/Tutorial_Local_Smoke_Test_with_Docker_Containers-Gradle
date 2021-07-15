package switchtwentytwenty.project.domain.aggregates.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;

import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor
@Setter
public class CustomCategory implements Category {

    private CategoryID categoryID;
    private ParentCategoryPath parentID;
    @Getter
    private CategoryName categoryName;

    private FamilyID familyID;

    public CustomCategory(ParentCategoryPath parentID, CategoryName categoryName, FamilyID familyID) {
        this.parentID = parentID;
        this.categoryName = categoryName;
        this.familyID = familyID;
    }

    public CustomCategory(CategoryID categoryID, ParentCategoryPath parentID, CategoryName categoryName, FamilyID familyID) {
        this.categoryID = categoryID;
        this.parentID = parentID;
        this.categoryName = categoryName;
        this.familyID = familyID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomCategory that = (CustomCategory) o;
        return Objects.equals(categoryID, that.categoryID) && Objects.equals(parentID, that.parentID) && Objects.equals(categoryName, that.categoryName) && Objects.equals(familyID, that.familyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryID, parentID, categoryName, familyID);
    }

    @Override
    public CategoryID id() {
        return this.categoryID;
    }

    @Override
    public boolean hasID(CategoryID id) {
        return this.categoryID.equals(id);
    }


    public ParentCategoryPath getParentCategoryPath(){
        return this.parentID;
    }

    public Optional<FamilyID> getFamilyID(){
        return Optional.of(this.familyID);
    }
}
