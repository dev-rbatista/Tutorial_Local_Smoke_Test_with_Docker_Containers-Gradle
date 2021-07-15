package switchtwentytwenty.project.domain.valueobject;

import lombok.Getter;

import java.util.Objects;

public class CategoryID implements ID {
    @Getter
    private final String id;

    public CategoryID(long categoryID) {
        this.id = String.valueOf(categoryID);
    }

    public CategoryID(String categoryID) {
        this.id = categoryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryID)) return false;
        CategoryID that = (CategoryID) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString(){
        return String.valueOf(id);
    }

}
