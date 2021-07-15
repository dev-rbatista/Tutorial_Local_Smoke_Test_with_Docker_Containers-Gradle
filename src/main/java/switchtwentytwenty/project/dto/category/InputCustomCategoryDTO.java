package switchtwentytwenty.project.dto.category;

import java.util.Objects;

public class InputCustomCategoryDTO {
    private final String categoryName;
    private final String parentCategory;
    private final String familyID;

    public InputCustomCategoryDTO(String categoryName, String parentID, String familyID) {
        this.categoryName = categoryName;
        this.parentCategory = parentID;
        this.familyID = familyID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getParentID() {
        return parentCategory;
    }

    public String getFamilyID() { return familyID; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputCustomCategoryDTO that = (InputCustomCategoryDTO) o;
        return categoryName.equals(that.categoryName) && parentCategory.equals(that.parentCategory) && familyID.equals(that.familyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName, parentCategory, familyID);
    }
}
