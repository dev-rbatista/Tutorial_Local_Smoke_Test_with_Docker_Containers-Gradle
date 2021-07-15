package switchtwentytwenty.project.dto.accounts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class OutputAccountDTO extends RepresentationModel<OutputAccountDTO> {

    private String accountID;
    private String ownerID;
    private String designation;

    public OutputAccountDTO(String accountID, String ownerID, String designation) {
        this.accountID = accountID;
        this.ownerID = ownerID;
        this.designation = designation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutputAccountDTO)) return false;
        if (!super.equals(o)) return false;
        OutputAccountDTO that = (OutputAccountDTO) o;
        return accountID.equals(that.accountID) && ownerID.equals(that.ownerID) && designation.equals(that.designation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accountID);
    }
}