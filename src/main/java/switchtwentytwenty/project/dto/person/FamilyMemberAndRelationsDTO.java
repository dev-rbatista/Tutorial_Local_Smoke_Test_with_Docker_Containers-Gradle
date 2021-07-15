package switchtwentytwenty.project.dto.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import switchtwentytwenty.project.dto.family.OutputPersonRelationDTO;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FamilyMemberAndRelationsDTO extends RepresentationModel {

    private String name;
    private String personID;
    private List<OutputPersonRelationDTO> relations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FamilyMemberAndRelationsDTO)) return false;
        if (!super.equals(o)) return false;
        FamilyMemberAndRelationsDTO that = (FamilyMemberAndRelationsDTO) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getPersonID(), that.getPersonID()) && Objects.equals(getRelations(), that.getRelations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getPersonID(), getRelations());
    }
}
