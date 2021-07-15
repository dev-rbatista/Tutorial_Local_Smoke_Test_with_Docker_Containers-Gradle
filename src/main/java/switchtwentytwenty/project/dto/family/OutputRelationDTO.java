package switchtwentytwenty.project.dto.family;

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
public class OutputRelationDTO extends RepresentationModel<OutputRelationDTO> {

    private String memberOneID;
    private String memberTwoID;
    private String relationDesignation;
    private String relationID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OutputRelationDTO that = (OutputRelationDTO) o;
        return memberOneID.equals(that.memberOneID) && memberTwoID.equals(that.memberTwoID) && relationDesignation.equals(that.relationDesignation) && relationID.equals(that.relationID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), memberOneID, memberTwoID, relationDesignation, relationID);
    }
}