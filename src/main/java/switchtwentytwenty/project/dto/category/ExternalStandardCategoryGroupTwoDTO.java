package switchtwentytwenty.project.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExternalStandardCategoryGroupTwoDTO {

    private String designation;
    private String id;
    private String parentID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExternalStandardCategoryGroupTwoDTO)) return false;
        ExternalStandardCategoryGroupTwoDTO that = (ExternalStandardCategoryGroupTwoDTO) o;
        return Objects.equals(designation, that.designation) && Objects.equals(id, that.id) && Objects.equals(parentID, that.parentID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(designation, id, parentID);
    }

}
