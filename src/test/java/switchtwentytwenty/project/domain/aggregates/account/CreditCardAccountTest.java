package switchtwentytwenty.project.domain.aggregates.account;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.valueobject.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardAccountTest {
    Long id = 66L;
    AccountID accountID = new AccountID(id);
    IOwnerID personID = new PersonID("tonyze@latinlover.com");
    Designation designation = new Designation("noitadas");

    Long otherID = 99L;
    AccountID otherAccountID = new AccountID(otherID);

    String currency = new String("EUR");
    BigDecimal amount = new BigDecimal("66.6");
    BigDecimal otherAmount = new BigDecimal("99.9");
    MonetaryValue monetaryValueOne = new MonetaryValue(currency, amount);
    Movement movementOne = new Movement(monetaryValueOne);
    MonetaryValue monetaryValueTwo = new MonetaryValue(currency, otherAmount);
    Movement movementTwo = new Movement(monetaryValueTwo);
    List<Movement> movementList = new ArrayList<>();
    List<Movement> otherMovementList = new ArrayList<>();

    @Test
    void getAccountIDTest() {
        IAccount creditCardAccount = new CreditCardAccount(personID, designation);
        creditCardAccount.setAccountID(accountID);

        AccountID expected = accountID;

        AccountID result = creditCardAccount.id();

        assertEquals(expected, result);
    }
    @Test
    void getOwnerIDTest() {
        IAccount creditCardAccount = new CreditCardAccount(personID, designation);

        IOwnerID expected = new PersonID("tonyze@latinlover.com");

        IOwnerID result = creditCardAccount.getOwnerId();

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }
    @Test
    void getDesignationTest() {
        IAccount creditCardAccount = new CreditCardAccount();
        creditCardAccount.setDesignation(designation);

        Designation expected = designation;

        Designation result = creditCardAccount.getDesignation();

        assertEquals(expected, result);
    }
    @Test
    void getListOfMovementsTest() {
        IAccount creditCardAccount = new CreditCardAccount(personID, designation);

        movementList.add(movementOne);
        movementList.add(movementTwo);
        creditCardAccount.setMovements(movementList);

        List<Movement> expected = movementList;

        List<Movement> result = creditCardAccount.getListOfMovements();

        assertEquals(expected, result);
    }
    @Test
    void getListOfMovementsTestEmptyList() {
        IAccount creditCardAccount = new CreditCardAccount(personID, designation);

        List<Movement> expected = new ArrayList<>();

        List<Movement> result = creditCardAccount.getListOfMovements();

        assertEquals(expected, result);
    }

    @Test
    void getAccountTypeTest() {
        IAccount creditCardAccount = new CreditCardAccount();

        String expected = "credit";

        String result = creditCardAccount.getAccountType();

        assertEquals(expected, result);
    }
    @Test
    void setOwnerTest() {
        IAccount creditCardAccountOne = new CreditCardAccount();
        creditCardAccountOne.setOwner(personID);

        IOwnerID expected = new PersonID("tonyze@latinlover.com");

        IOwnerID result = creditCardAccountOne.getOwnerId();

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }
    @Test
    void setDesignation() {
        IAccount creditCardAccountOne = new CreditCardAccount();
        creditCardAccountOne.setDesignation(designation);

        Designation expected = new Designation("noitadas");

        Designation result = creditCardAccountOne.getDesignation();

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }
    @Test
    void addMovementTest(){
        IAccount creditCardAccount = new CreditCardAccount(personID, designation);

        movementList.add(movementOne);
        movementList.add(movementTwo);
        List<Movement> expected = movementList;

        creditCardAccount.addMovement(movementOne);
        creditCardAccount.addMovement(movementTwo);
        List<Movement> result = creditCardAccount.getListOfMovements();

        assertEquals(expected, result);
    }
    @Test
    void hasIDTrueTest() {
        IAccount creditCardAccountOne = new CreditCardAccount(personID, designation);
        creditCardAccountOne.setAccountID(accountID);

        assertTrue(creditCardAccountOne.hasID(accountID));
    }
    @Test
    void hasIDFalseTest() {
        IAccount creditCardAccountOne = new CreditCardAccount(personID, designation);
        creditCardAccountOne.setAccountID(accountID);

        assertFalse(creditCardAccountOne.hasID(otherAccountID));
    }
    @Test
    void equalsCreditCardAccountTest(){
        IAccount creditCardAccountOne = new CreditCardAccount(personID, designation);
        IAccount creditCardAccountTwo = new CreditCardAccount(personID, designation);

        assertEquals(creditCardAccountOne, creditCardAccountTwo);
        assertNotSame(creditCardAccountOne, creditCardAccountTwo);
    }
    @Test
    void equalsCreditCardAccountAllArgsTest(){
        IAccount creditCardAccountOne = new CreditCardAccount(accountID, personID, designation, movementList);
        IAccount creditCardAccountTwo = new CreditCardAccount(accountID, personID, designation, movementList);

        assertEquals(creditCardAccountOne, creditCardAccountTwo);
        assertNotSame(creditCardAccountOne, creditCardAccountTwo);
    }
    @Test
    void equalsCreditCardAccountNoArgsTest(){
        IAccount creditCardAccountOne = new CreditCardAccount();
        IAccount creditCardAccountTwo = new CreditCardAccount();

        assertEquals(creditCardAccountOne, creditCardAccountTwo);
        assertNotSame(creditCardAccountOne, creditCardAccountTwo);
    }
    @Test
    void equalsCreditCardAccountIsSameTest(){
        IAccount creditCardAccountOne = new CreditCardAccount(personID, designation);
        IAccount creditCardAccountTwo = creditCardAccountOne;

        assertEquals(creditCardAccountOne, creditCardAccountTwo);
        assertSame(creditCardAccountOne, creditCardAccountTwo);
    }
    @Test
    void equalsCreditCardAccountAllIsDifferentTest(){
        IAccount creditCardAccountOne = new CreditCardAccount(personID, designation);
        creditCardAccountOne.setAccountID(accountID);
        IAccount creditCardAccountTwo = new CreditCardAccount(personID, designation);
        creditCardAccountTwo.setAccountID(otherAccountID);

        assertNotEquals(creditCardAccountOne, creditCardAccountTwo);
    }
    @Test
    void equalsCreditCardAccountIsDifferentClassTest(){
        IAccount creditCardAccountOne = new CreditCardAccount(personID, designation);

        assertNotEquals(creditCardAccountOne, accountID);
        assertNotSame(creditCardAccountOne, accountID);
    }
    @Test
    void equalsCreditCardAccountIsDifferentFromNullTest(){
        IAccount creditCardAccountOne = new CreditCardAccount(personID, designation);
        String nullString = null;

        assertNotEquals(creditCardAccountOne, nullString);
        assertNotSame(creditCardAccountOne, nullString);
    }
    @Test
    void hashCodeCreditCardAccountIsEqualsTest() {
        IAccount creditCardAccountOne = new CreditCardAccount(personID, designation);
        IAccount creditCardAccountTwo = new CreditCardAccount(personID, designation);

        assertEquals(creditCardAccountOne.hashCode(), creditCardAccountTwo.hashCode());
        assertNotSame(creditCardAccountOne, creditCardAccountTwo);
    }
    @Test
    void hashCodeCreditCardAccountIsDifferentTest() {
        IAccount creditCardAccountOne = new CreditCardAccount(personID, designation);
        creditCardAccountOne.setAccountID(accountID);
        IAccount creditCardAccountTwo = new CreditCardAccount(personID, designation);
        creditCardAccountTwo.setAccountID(otherAccountID);

        assertNotEquals(creditCardAccountOne.hashCode(), creditCardAccountTwo.hashCode());
    }

}

