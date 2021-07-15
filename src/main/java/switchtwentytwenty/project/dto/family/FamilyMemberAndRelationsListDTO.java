package switchtwentytwenty.project.dto.family;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import switchtwentytwenty.project.dto.person.FamilyMemberAndRelationsDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class FamilyMemberAndRelationsListDTO extends RepresentationModel<FamilyMemberAndRelationsListDTO> {
    @Getter
    List<FamilyMemberAndRelationsDTO> familyMemberAndRelationsDTO = new ArrayList<>();


    public void addDTO(FamilyMemberAndRelationsDTO familyMemberAndRelationsDTO) {
        this.familyMemberAndRelationsDTO.add(familyMemberAndRelationsDTO);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FamilyMemberAndRelationsListDTO that = (FamilyMemberAndRelationsListDTO) o;
        return Objects.equals(familyMemberAndRelationsDTO, that.familyMemberAndRelationsDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), familyMemberAndRelationsDTO);
    }


}
