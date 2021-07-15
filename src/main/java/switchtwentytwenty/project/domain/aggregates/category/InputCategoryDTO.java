package switchtwentytwenty.project.domain.aggregates.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InputCategoryDTO {

    private Long categoryID;
    private String description;
    private Long parentID;
    private String familyID;

    public InputCategoryDTO(Long categoryID, String description, Long parentID, String familyID) {
        this.categoryID = categoryID;
        this.description = description;
        this.parentID = parentID;
        this.familyID = familyID;
    }
}