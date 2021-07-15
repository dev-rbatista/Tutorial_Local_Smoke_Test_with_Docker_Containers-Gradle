package switchtwentytwenty.project.dto.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class InputRemoveEmailDTO {
    private String email;
    private String personID;

    public InputRemoveEmailDTO(String email, String personID) {
        this.email = email;
        this.personID = personID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputRemoveEmailDTO that = (InputRemoveEmailDTO) o;
        return Objects.equals(email, that.email) && Objects.equals(personID, that.personID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, personID);
    }
}
