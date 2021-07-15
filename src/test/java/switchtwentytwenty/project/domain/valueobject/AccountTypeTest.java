package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class AccountTypeTest {

    @Test
    void testEqualsTrue() {
        AccountType accountType1 = new AccountType("cash");
        AccountType accountType2 = new AccountType("cash");

        assertEquals(accountType1, accountType2);
        assertNotSame(accountType1, accountType2);
    }

    @Test
    void testEqualsTrueSameAccountType() {
        AccountType accountTypeOne = new AccountType("cash");
        AccountType accountTypeTwo = accountTypeOne;

        assertEquals(accountTypeOne, accountTypeTwo);
        assertSame(accountTypeOne, accountTypeTwo);
    }


    @Test
    void testEqualsFalse() {
        AccountType accountType1 = new AccountType("cash");
        AccountType accountType2 = new AccountType("cashhh");
        AccountType accountTypenull = null;
        String notAccountType = "a string";

        assertNotEquals(accountType1, accountType2);
        assertNotEquals(accountType1, accountTypenull);
        assertNotEquals(accountType1, notAccountType);
        assertNotSame(accountType1, accountType2);

    }

    @Test
    void testHashCodeEquals() {
        AccountType accountType1 = new AccountType("cash");
        AccountType accountType2 = new AccountType("cash");

        assertEquals(accountType1.hashCode(), accountType2.hashCode());

    }

    @Test
    void testHashCodeNotEquals() {
        AccountType accountType1 = new AccountType("cash");
        AccountType accountType2 = new AccountType("cashhhhh");

        assertNotEquals(accountType1.hashCode(), accountType2.hashCode());

    }

    @Test
    void testAccountTypeSetter() {
        AccountType accountType = new AccountType();
        accountType.setType("cash");
        String expected = "cash";

        String result = accountType.getType();

        assertEquals(expected,result);
    }
}