package switchtwentytwenty.project.domain.valueobject;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class AccountID implements ID {

    @Getter
    @Setter
    private Long id;

    public AccountID(Long id) {
        this.id = id;
        validateID();
    }

    /**
     * Method that validates a familyID, throws an exception if the ID isn't valid
     */
    private void validateID() {
        if (!isIDValid()) {
            throw new IllegalArgumentException("Invalid ID");
        }
    }

    /**
     * Method to determine if an ID is valid, i.e. not null
     *
     * @return boolean, true if ID is valid, false otherwise
     */
    private boolean isIDValid() {
        return this.id != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountID)) return false;
        AccountID accountID1 = (AccountID) o;
        return id.equals(accountID1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString(){
        return this.id.toString();
    }
}