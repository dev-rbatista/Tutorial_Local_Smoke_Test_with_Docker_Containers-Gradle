package switchtwentytwenty.project.domain.valueobject;

import switchtwentytwenty.project.exceptions.InvalidZipCodeException;

import java.util.Objects;
import java.util.regex.Pattern;

public class ZipCode implements ValueObject {

    private final String code;


    public ZipCode(String code) {
        this.code = code;
        validateData();
    }

    @Override
    public String toString() {
        return this.code;
    }

    private void validateData() {
        checkZipCode();
    }

    private void checkZipCode() {
        String invalidZipCode = "Invalid Zip Code";
        if (!validateZipCode()) {
            throw new InvalidZipCodeException(invalidZipCode);
        }
    }

    private boolean validateZipCode() {
        String regex = "\\d{4}(-\\d{3})?";
        boolean result;
        if (code == null || code.trim().length() == 0) {
            result = false;
        } else {
            result = Pattern.matches(regex, code);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZipCode zipCode1 = (ZipCode) o;
        return code.equals(zipCode1.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
