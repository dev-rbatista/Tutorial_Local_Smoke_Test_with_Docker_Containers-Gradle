package switchtwentytwenty.project.domain.valueobject;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class MonetaryValue {

    private Currency currency;
    private BigDecimal amount;

    public MonetaryValue(String currency, BigDecimal amount) {
        this.currency = validateCurrency(currency);
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonetaryValue)) return false;
        MonetaryValue monetaryValue = (MonetaryValue) o;
        return Objects.equals(getCurrency(), monetaryValue.getCurrency()) && Objects.equals(getAmount(), monetaryValue.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrency(), getAmount());
    }

    private Currency validateCurrency(String currencyToCheck) {
        Currency currencyCheck;
        if (checkNull(currencyToCheck) || checkBlank(currencyToCheck)) {
            currencyCheck = Currency.getInstance("EUR");
        } else {
            currencyCheck = Currency.getInstance(currencyToCheck);
        }
        return currencyCheck;
    }

    private boolean checkBlank (String currency){
        boolean isBlank = false;
        if (currency.trim().length() == 0){
            isBlank = true;
        }
        return isBlank;
    }

    private boolean checkNull (String currency){
        boolean isNull = false;
        if (currency == null){
            isNull = true;
        }
        return isNull;
    }
}
