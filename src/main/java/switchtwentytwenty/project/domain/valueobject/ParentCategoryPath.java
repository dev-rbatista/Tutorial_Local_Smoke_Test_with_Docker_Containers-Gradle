package switchtwentytwenty.project.domain.valueobject;

import java.util.Objects;

public class ParentCategoryPath {

    private final String parentCategory;

    public ParentCategoryPath(String parentCategoryPath) {
        this.parentCategory = parentCategoryPath;
        validateParentCategoryPath();
    }

    private void validateParentCategoryPath() {
        if (!isNull() && isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return parentCategory;
    }

    private boolean isNull() {
        return parentCategory == null;
    }

    private boolean isBlank() {
        return parentCategory.trim().length() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParentCategoryPath)) return false;
        ParentCategoryPath that = (ParentCategoryPath) o;
        return Objects.equals(parentCategory, that.parentCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentCategory);
    }

}