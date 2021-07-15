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
public class InputRelationDTO {

    private String personIDOne;
    private String personIDTwo;
    private String designation;
    private String familyID;

    public InputRelationDTO(CreateRelationDTO createRelationDTO, String familyID) {
        this.personIDOne = createRelationDTO.getMemberOneID();
        this.personIDTwo = createRelationDTO.getMemberTwoID();
        this.designation = createRelationDTO.getRelationDesignation();
        this.familyID = familyID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputRelationDTO that = (InputRelationDTO) o;
        return personIDOne.equals(that.personIDOne) && personIDTwo.equals(that.personIDTwo) && designation.equals(that.designation) && familyID.equals(that.familyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personIDOne, personIDTwo, designation, familyID);
    }
}