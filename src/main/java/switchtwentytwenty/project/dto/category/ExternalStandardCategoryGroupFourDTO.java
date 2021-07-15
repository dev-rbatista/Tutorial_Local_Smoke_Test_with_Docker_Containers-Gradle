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
public class ExternalStandardCategoryGroupFourDTO {

    private String name;
    private String id;
    private String parentID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExternalStandardCategoryGroupFourDTO)) return false;
        ExternalStandardCategoryGroupFourDTO that = (ExternalStandardCategoryGroupFourDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(id, that.id) && Objects.equals(parentID, that.parentID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, parentID);
    }
}
