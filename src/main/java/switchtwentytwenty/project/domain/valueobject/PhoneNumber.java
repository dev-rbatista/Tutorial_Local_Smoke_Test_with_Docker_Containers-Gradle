package switchtwentytwenty.project.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import switchtwentytwenty.project.exceptions.InvalidPhoneNumberException;

import java.util.Objects;
import java.util.regex.Pattern;
@AllArgsConstructor
public class PhoneNumber implements ValueObject {
    @Getter
    private long id;

    @Getter
    private Integer number;

    public PhoneNumber(Integer number) {
        this.number = number;
        validateData();
    }

    @Override
    public String toString() {
        return this.number.toString();
    }

    private void validateData() {
        if (number != null) {
            checkNumber();
        }
    }

    private void checkNumber() {
        String invalidPhone = "Phone number is not valid";
        if (!isPhoneNumberValid())
            throw new InvalidPhoneNumberException(invalidPhone);
    }

    // Definir lógica de negócio
    private boolean isPhoneNumberValid() {
        String regex = "\\d{9}";
        String phoneNumb = String.valueOf(number);
        return Pattern.matches(regex, phoneNumb);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
