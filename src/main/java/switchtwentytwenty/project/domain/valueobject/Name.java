package switchtwentytwenty.project.domain.valueobject;

import switchtwentytwenty.project.exceptions.InvalidNameException;

import java.util.Objects;

public class Name implements ValueObject {


    private final String thisName;

    public Name(String thisName) {
        validateData(thisName);
        this.thisName = thisName.trim();
    }

    private void validateData(String name) {
        checkName(name);
    }

    private void checkName(String name) {
        String invalidName = "Name is not valid";
        if(!isValidName(name))
            throw new InvalidNameException(invalidName);
    }

    // Falta Verificação com regras de negócio
    private boolean isValidName(String name) {
        return name != null && name.trim().length() != 0;
    }

    @Override
    public String toString() {
        return this.thisName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return thisName.equals(name1.thisName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thisName);
    }
}