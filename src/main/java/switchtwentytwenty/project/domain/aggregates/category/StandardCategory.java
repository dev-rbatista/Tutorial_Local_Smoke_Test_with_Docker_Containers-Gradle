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
public class StandardCategory implements Category {

    @Getter
    private CategoryName categoryName;
    private CategoryID categoryID;
    private ParentCategoryPath parentID;

    public StandardCategory(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    public StandardCategory(CategoryName categoryName, ParentCategoryPath parentID) {

        this.categoryName = categoryName;
        this.parentID = parentID;
    }

    public StandardCategory(CategoryName categoryName, CategoryID categoryID, ParentCategoryPath parentID) {

        this.categoryName = categoryName;
        this.categoryID = categoryID;
        this.parentID = parentID;
    }

    public ParentCategoryPath getParentCategoryPath(){
        return this.parentID;
    }

    public Optional<FamilyID> getFamilyID(){
        return Optional.empty();
    }

    @Override
    public CategoryID id() {
        return this.categoryID;
    }

    @Override
    public boolean hasID(CategoryID id) {
       return this.categoryID.equals(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandardCategory that = (StandardCategory) o;
        return Objects.equals(categoryID, that.categoryID) && this.categoryName.equals(that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName, categoryID, parentID);
    }
}
