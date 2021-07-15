package switchtwentytwenty.project.domain.valueobject;

import switchtwentytwenty.project.exceptions.InvalidStreetException;

import java.util.Objects;

public class Street implements ValueObject {

    private final String streetName;


    public Street(String streetName) {
        this.streetName = streetName;
        validateData();
    }

    private void validateData() {
        checkStreet();
    }


    private void checkStreet() {
        String invalidStreet = "Invalid Street Name";
        if (!validateStreet()) {
            throw new InvalidStreetException(invalidStreet);
        }
    }

    // Verificar com lógica de negócio
    private boolean validateStreet() {
        return streetName != null && streetName.trim().length() != 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Street street1 = (Street) o;
        return Objects.equals(streetName, street1.streetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName);
    }

    @Override
    public String toString() {
        return this.streetName;
    }
}
