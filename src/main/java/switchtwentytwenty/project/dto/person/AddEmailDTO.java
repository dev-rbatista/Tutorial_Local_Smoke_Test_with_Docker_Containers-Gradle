package switchtwentytwenty.project.dto.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
public class AddEmailDTO {

    private String email;

    public AddEmailDTO(String email) {
        this.email = email;
    }

    public String unpackEmail() {
        return this.email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddEmailDTO that = (AddEmailDTO) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
