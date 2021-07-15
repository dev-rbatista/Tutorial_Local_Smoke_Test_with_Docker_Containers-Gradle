package switchtwentytwenty.project.dto.person;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@NoArgsConstructor
public class InputEmailDTO {

    private String id;
    private String email;

    public InputEmailDTO(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String unpackEmail() {
        return this.email;
    }

    public String unpackUserID() { return this.id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputEmailDTO that = (InputEmailDTO) o;
        return Objects.equals(email, that.email) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
