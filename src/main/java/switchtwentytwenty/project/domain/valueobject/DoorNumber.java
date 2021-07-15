package switchtwentytwenty.project.domain.valueobject;

import switchtwentytwenty.project.exceptions.InvalidAddressNumberException;

import java.util.Objects;

public class DoorNumber implements ValueObject {

    private final String number;


    public DoorNumber(String number) {
        this.number = number;
        validateData();
    }

    @Override
    public String toString() {
        return this.number;
    }

    private void validateData() {
        checkNumber();
    }

    private void checkNumber() {
        String invalidAddressNumber = "Invalid Address Number";
        if (this.number == null || this.number.isEmpty() || this.number.trim().length()==0) {
            throw new InvalidAddressNumberException(invalidAddressNumber);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoorNumber that = (DoorNumber) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }


}
