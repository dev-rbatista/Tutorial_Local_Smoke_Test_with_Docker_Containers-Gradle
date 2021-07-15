package switchtwentytwenty.project.dto.category;

import java.util.Objects;

public class InputStandardCategoryDTO {
    private final String categoryName;
    private final String parentCategory;

    public InputStandardCategoryDTO(String categoryName, String parentID) {
        this.categoryName = categoryName;
        this.parentCategory = parentID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getParentID() {
        return parentCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputStandardCategoryDTO that = (InputStandardCategoryDTO) o;
        return categoryName.equals(that.categoryName) && parentCategory.equals(that.parentCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName, parentCategory);
    }
}
