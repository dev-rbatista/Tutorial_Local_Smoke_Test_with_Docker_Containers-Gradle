package switchtwentytwenty.project.domain.valueobject;

import java.util.Objects;

public class Movement implements ValueObject {

    private MonetaryValue monetaryValue;

    public Movement(MonetaryValue monetaryValue) {
        this.monetaryValue = monetaryValue;
    }

    public MonetaryValue getMonetaryValue() {
        return monetaryValue;
    }

    public void setMonetary(MonetaryValue monetaryValue) {
        this.monetaryValue = monetaryValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movement)) return false;
        Movement movement = (Movement) o;
        return Objects.equals(getMonetaryValue(), movement.getMonetaryValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMonetaryValue());
    }
}
