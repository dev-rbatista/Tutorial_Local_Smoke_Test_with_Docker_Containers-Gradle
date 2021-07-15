package switchtwentytwenty.project.domain.valueobject;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
public class PersonID implements IOwnerID, Serializable {

    private EmailAddress id;

    public PersonID(String id) {
        this.id = new EmailAddress(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonID personID = (PersonID) o;
        return Objects.equals(id, personID.id);
    }

    @Override
    public String toString() {
        return this.id.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isThisEmail(EmailAddress email) {
        return this.id.equals(email);
    }
}
