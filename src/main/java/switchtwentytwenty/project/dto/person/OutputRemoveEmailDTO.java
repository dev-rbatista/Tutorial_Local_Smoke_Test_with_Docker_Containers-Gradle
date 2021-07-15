package switchtwentytwenty.project.dto.person;

import lombok.Getter;
import lombok.Setter;;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class OutputRemoveEmailDTO {

    private List<String> emailAddresses;

    public OutputRemoveEmailDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutputRemoveEmailDTO that = (OutputRemoveEmailDTO) o;
        return Objects.equals(emailAddresses, that.emailAddresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddresses);
    }
}

