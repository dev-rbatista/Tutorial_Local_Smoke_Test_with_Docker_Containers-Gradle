package switchtwentytwenty.project.dto.family;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class OutputFamilyDTO extends RepresentationModel<OutputFamilyDTO> {
    private String familyName;
    private String familyID;
    private String adminID;
    private String registrationDate;

    public OutputFamilyDTO(String familyName, String familyID, String adminID, String registrationDate) {
        this.familyName = familyName;
        this.familyID = familyID;
        this.adminID = adminID;
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OutputFamilyDTO that = (OutputFamilyDTO) o;
        return familyName.equals(that.familyName) && familyID.equals(that.familyID) && adminID.equals(that.adminID) && registrationDate.equals(that.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), familyName, familyID, adminID, registrationDate);
    }
}