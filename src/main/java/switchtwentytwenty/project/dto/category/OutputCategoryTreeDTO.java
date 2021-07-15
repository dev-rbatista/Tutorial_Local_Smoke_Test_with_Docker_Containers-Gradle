package switchtwentytwenty.project.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OutputCategoryTreeDTO extends RepresentationModel<OutputCategoryTreeDTO> {

    private List<OutputCategoryDTO> outputCategoryDTOList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutputCategoryTreeDTO)) return false;
        if (!super.equals(o)) return false;
        OutputCategoryTreeDTO that = (OutputCategoryTreeDTO) o;
        return Objects.equals(getOutputCategoryDTOList(), that.getOutputCategoryDTOList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOutputCategoryDTOList());
    }

    public void addOutputCategoryDTO(OutputCategoryDTO outputCategoryDTO) {
        this.outputCategoryDTOList.add(outputCategoryDTO);
    }

}
