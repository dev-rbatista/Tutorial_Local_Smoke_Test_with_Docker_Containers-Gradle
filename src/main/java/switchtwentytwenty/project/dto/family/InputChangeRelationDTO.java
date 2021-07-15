package switchtwentytwenty.project.dto.family;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputChangeRelationDTO {

    private String relationID;
    private String newDesignation;
    private String familyID;

    public InputChangeRelationDTO(ChangeRelationDTO changeRelationDTO, String familyID, String relationID) {
        this.relationID = relationID;
        this.newDesignation = changeRelationDTO.getNewRelationDesignation();
        this.familyID = familyID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputChangeRelationDTO that = (InputChangeRelationDTO) o;
        return Objects.equals(relationID, that.relationID) && Objects.equals(newDesignation, that.newDesignation) && Objects.equals(familyID, that.familyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relationID, newDesignation, familyID);
    }

  }