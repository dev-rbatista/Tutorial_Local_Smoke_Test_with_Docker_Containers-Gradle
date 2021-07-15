package switchtwentytwenty.project.dto.person;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OutputEmailDTO extends RepresentationModel<OutputEmailDTO> {

    private String email;

    /**
     * This DTO receives information from the updated Person in the Persistence and retrieves the added email.
     * Sends the info back to the Controller to be converted to an External DTO
     * to be placed in the Response Entity.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OutputEmailDTO that = (OutputEmailDTO) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email);
    }
}
