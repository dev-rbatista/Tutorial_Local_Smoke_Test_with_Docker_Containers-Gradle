package switchtwentytwenty.project.dto.family;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangeRelationDTO {


    private String newRelationDesignation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangeRelationDTO that = (ChangeRelationDTO) o;
        return Objects.equals(newRelationDesignation, that.newRelationDesignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newRelationDesignation);
    }
}