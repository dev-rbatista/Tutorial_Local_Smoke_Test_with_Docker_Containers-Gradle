package switchtwentytwenty.project.dto.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CreateCategoryDTO {

    private String categoryDescription;
    private String parentCategory;

    public CreateCategoryDTO(String categoryDescription, String parentCategory) {
        this.categoryDescription = categoryDescription;
        this.parentCategory = parentCategory;
    }
}
