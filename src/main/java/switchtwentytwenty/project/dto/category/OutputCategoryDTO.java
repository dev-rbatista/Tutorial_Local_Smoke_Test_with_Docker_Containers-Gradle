package switchtwentytwenty.project.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OutputCategoryDTO extends RepresentationModel<OutputCategoryDTO> {

    private String categoryName;
    private String categoryID;
    private String parentID;
    private String familyID;

    public OutputCategoryDTO(String categoryName, String categoryID, String parentID) {
        this.categoryName = categoryName;
        this.categoryID = categoryID;
        this.parentID = parentID;
    }

    public OutputCategoryDTO(String categoryName, String categoryID) {
        this.categoryName = categoryName;
        this.categoryID = categoryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutputCategoryDTO)) return false;
        if (!super.equals(o)) return false;
        OutputCategoryDTO that = (OutputCategoryDTO) o;
        return Objects.equals(categoryName, that.categoryName) && Objects.equals(categoryID, that.categoryID) && Objects.equals(parentID, that.parentID) && Objects.equals(familyID, that.familyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), categoryName, categoryID, parentID, familyID);
    }
}
