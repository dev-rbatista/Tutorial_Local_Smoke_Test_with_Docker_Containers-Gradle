package switchtwentytwenty.project.domain.valueobject;

import switchtwentytwenty.project.exceptions.InvalidVATException;

import java.util.Objects;
import java.util.regex.Pattern;

public class VATNumber implements ValueObject {

    private final int vat;

    public VATNumber(int vat) {
        this.vat = vat;
        validateData();
    }

    @Override
    public String toString() {
        return String.valueOf(this.vat);

    }

    private void validateData() {
        checkVatNumber();
    }

    private void checkVatNumber() {
        String invalidVat = "Invalid VAT";
        if (!isValidVAT())
            throw new InvalidVATException(invalidVat);
    }

    private boolean isValidVAT() {
        String regex = "\\d{9}";
        String vatNum = String.valueOf(vat);
        return Pattern.matches(regex, vatNum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VATNumber)) return false;
        VATNumber vatNumber1 = (VATNumber) o;
        return vat == vatNumber1.vat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vat);
    }

    public int toInt() {
        return this.vat;
    }
}
