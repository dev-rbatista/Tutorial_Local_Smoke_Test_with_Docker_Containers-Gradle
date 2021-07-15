package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class MonetaryValueTest {

    @Test
    void monetaryConstructorNullCurrency() {
        BigDecimal amount = BigDecimal.valueOf(100);
        MonetaryValue result = new MonetaryValue(null,amount);

        MonetaryValue expected = new MonetaryValue("EUR",amount);

        assertEquals(expected,result);
    }

    @Test
    void monetaryConstructorBlankCurrency() {
        BigDecimal amount = BigDecimal.valueOf(100);
        MonetaryValue result = new MonetaryValue("",amount);

        MonetaryValue expected = new MonetaryValue("EUR",amount);

        assertEquals(expected,result);
    }

    @Test
    void monetaryConstructor() {
        BigDecimal amount = BigDecimal.valueOf(100);

        assertThrows(IllegalArgumentException.class,()->new MonetaryValue("Patacas",amount));
    }

    @Test
    void getCurrencyTest() {
        BigDecimal amount = BigDecimal.valueOf(100);
        MonetaryValue monetaryValue = new MonetaryValue("EUR",amount);
        Currency expected = Currency.getInstance("EUR");

        Currency result = monetaryValue.getCurrency();

        assertEquals(expected,result);
    }


    @Test
    void setCurrencyTest() {
        BigDecimal amount = BigDecimal.valueOf(100);
        MonetaryValue monetaryValue = new MonetaryValue("EUR",amount);
        Currency expected = Currency.getInstance("USD");

        monetaryValue.setCurrency(Currency.getInstance("USD"));
        Currency result = monetaryValue.getCurrency();

        assertEquals(expected,result);
    }

    @Test
    void getAmountTest() {
        BigDecimal amount = BigDecimal.valueOf(100);
        MonetaryValue monetaryValue = new MonetaryValue("EUR",amount);
        BigDecimal expected = BigDecimal.valueOf(100);

        BigDecimal result = monetaryValue.getAmount();

        assertEquals(expected,result);
    }

    @Test
    void setAmountTest() {
        BigDecimal amount = BigDecimal.valueOf(100);
        MonetaryValue monetaryValue = new MonetaryValue("EUR",amount);
        BigDecimal expected = BigDecimal.valueOf(200);

        monetaryValue.setAmount(BigDecimal.valueOf(200));
        BigDecimal result = monetaryValue.getAmount();

        assertEquals(expected,result);
    }

    @Test
    void testEqualsForEqualMonetary() {
        MonetaryValue monetaryValueOne = new MonetaryValue("EUR",BigDecimal.valueOf(100));
        MonetaryValue monetaryValueTwo = new MonetaryValue("EUR",BigDecimal.valueOf(100));

        assertEquals(monetaryValueOne, monetaryValueTwo);
        assertNotSame(monetaryValueOne, monetaryValueTwo);
    }

    @Test
    void testEqualsForSameMonetary() {
        MonetaryValue monetaryValueOne = new MonetaryValue("EUR",BigDecimal.valueOf(100));
        MonetaryValue monetaryValueTwo = monetaryValueOne;

        assertEquals(monetaryValueOne, monetaryValueTwo);
        assertSame(monetaryValueOne, monetaryValueTwo);
    }

    @Test
    void testEqualsForDifferentCurrencyMonetary() {
        MonetaryValue monetaryValueOne = new MonetaryValue("EUR",BigDecimal.valueOf(100));
        MonetaryValue monetaryValueTwo = new MonetaryValue("USD",BigDecimal.valueOf(100));

        assertNotEquals(monetaryValueOne, monetaryValueTwo);
    }

    @Test
    void testEqualsForDifferentAmountMonetary() {
        MonetaryValue monetaryValueOne = new MonetaryValue("EUR",BigDecimal.valueOf(100));
        MonetaryValue monetaryValueTwo = new MonetaryValue("EUR",BigDecimal.valueOf(200));

        assertNotEquals(monetaryValueOne, monetaryValueTwo);
    }

    @Test
    void testEqualsForDifferentObjects() {
        MonetaryValue monetaryValue = new MonetaryValue("EUR",BigDecimal.valueOf(100));
        String notMonetary = "Not a Monetary Value";

        assertNotEquals(monetaryValue,notMonetary);
    }

    @Test
    void testEqualsDifferentFromNull() {
        MonetaryValue monetaryValue = new MonetaryValue("EUR",BigDecimal.valueOf(100));
        String nullString = null;

        assertNotEquals(monetaryValue,nullString);
    }


    @Test
    void testHashCodeSameHashCode() {
        MonetaryValue monetaryValueOne = new MonetaryValue("EUR",BigDecimal.valueOf(100));
        MonetaryValue monetaryValueTwo = new MonetaryValue("EUR",BigDecimal.valueOf(100));

        assertEquals(monetaryValueOne.hashCode(), monetaryValueTwo.hashCode());
        assertNotSame(monetaryValueOne, monetaryValueTwo);
    }

    @Test
    void testHashCodeDifferentHashCode() {
        MonetaryValue monetaryValueOne = new MonetaryValue("EUR",BigDecimal.valueOf(100));
        MonetaryValue monetaryValueTwo = new MonetaryValue("USD",BigDecimal.valueOf(100));

        assertNotEquals(monetaryValueOne.hashCode(), monetaryValueTwo.hashCode());
    }

}