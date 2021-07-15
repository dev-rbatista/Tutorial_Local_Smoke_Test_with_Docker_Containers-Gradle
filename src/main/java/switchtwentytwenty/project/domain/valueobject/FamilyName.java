package switchtwentytwenty.project.domain.valueobject;

import java.util.Objects;

public class FamilyName implements ValueObject {

    private final String name;

    public FamilyName(String name) {
        validateName(name);
        this.name = name.trim();
    }

    /**
     * Method to verify if an input name is valid, verifies that it is not null, empty or blank
     */
    private void validateName(String familyName) {
        checkNull(familyName);
        checkBlank(familyName);
    }

    /**
     * Method to verify if the family name is null, throws and exception if the name is null
     *
     * @param familyName
     */
    private void checkNull(String familyName) {
        if (familyName == null) {
            throw new IllegalArgumentException("Name is Null");
        }
    }

    /**
     * Method to verify if the family name is blank, throws and exception if the name is blank
     *
     * @param familyName
     */
    private void checkBlank(String familyName) {
        if (familyName.trim().length() == 0) {
            throw new IllegalArgumentException("Name is Blank or Empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FamilyName)) return false;
        FamilyName that = (FamilyName) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}