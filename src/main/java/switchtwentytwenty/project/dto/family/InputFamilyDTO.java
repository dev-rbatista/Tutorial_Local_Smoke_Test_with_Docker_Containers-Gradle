package switchtwentytwenty.project.dto.family;

import java.util.Objects;

public class InputFamilyDTO {


    private final String familyName;
    private final String registrationDate;

    public InputFamilyDTO(String familyName, String registrationDate) {
        this.familyName = familyName;
        this.registrationDate = registrationDate;

    }


    public String unpackFamilyName() {
        return this.familyName;
    }

    public String unpackRegistrationDate() {
        return this.registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputFamilyDTO that = (InputFamilyDTO) o;
        return familyName.equals(that.familyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyName);
    }
}
