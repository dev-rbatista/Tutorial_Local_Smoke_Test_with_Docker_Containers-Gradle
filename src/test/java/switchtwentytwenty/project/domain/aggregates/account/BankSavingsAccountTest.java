package switchtwentytwenty.project.domain.aggregates.account;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.valueobject.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankSavingsAccountTest {
    Long id = 66L;
    AccountID accountID = new AccountID(id);
    PersonID personID = new PersonID("tonyze@latinlover.com");
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
        IAccount bankSavingsAccount = new BankSavingsAccount(personID, designation);
        bankSavingsAccount.setAccountID(accountID);

        AccountID expected = accountID;

        AccountID result = bankSavingsAccount.id();

        assertEquals(expected, result);
    }
    @Test
    void getOwnerIDTest() {
        IAccount bankSavingsAccount = new BankSavingsAccount(personID, designation);

        IOwnerID expected = new PersonID("tonyze@latinlover.com");

        IOwnerID result = bankSavingsAccount.getOwnerId();

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }
    @Test
    void getDesignationTest() {
        IAccount bankSavingsAccount = new BankSavingsAccount();
        bankSavingsAccount.setDesignation(designation);

        Designation expected = designation;

        Designation result = bankSavingsAccount.getDesignation();

        assertEquals(expected, result);
    }
    @Test
    void getListOfMovementsTest() {
        IAccount bankSavingsAccount = new BankSavingsAccount(personID, designation);

        movementList.add(movementOne);
        movementList.add(movementTwo);
        bankSavingsAccount.setMovements(movementList);

        List<Movement> expected = movementList;

        List<Movement> result = bankSavingsAccount.getListOfMovements();

        assertEquals(expected, result);
    }

    @Test
    void getListOfMovementsTestEmptyList() {
        IAccount bankSavingsAccount = new BankSavingsAccount(personID, designation);

        List<Movement> expected = new ArrayList<>();

        List<Movement> result = bankSavingsAccount.getListOfMovements();

        assertEquals(expected, result);
    }

    @Test
    void getAccountTypeTest() {
        IAccount bankSavingsAccount = new BankSavingsAccount();

        String expected = "savings";

        String result = bankSavingsAccount.getAccountType();

        assertEquals(expected, result);
    }
    @Test
    void setOwnerTest() {
        IAccount bankSavingsAccountOne = new BankSavingsAccount();
        bankSavingsAccountOne.setOwner(personID);

        IOwnerID expected = new PersonID("tonyze@latinlover.com");

        IOwnerID result = bankSavingsAccountOne.getOwnerId();

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }
    @Test
    void setDesignation() {
        IAccount bankSavingsAccountOne = new BankSavingsAccount();
        bankSavingsAccountOne.setDesignation(designation);

        Designation expected = new Designation("noitadas");

        Designation result = bankSavingsAccountOne.getDesignation();

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }
    @Test
    void addMovementTest(){
        IAccount bankSavingsAccount = new BankSavingsAccount(personID, designation);

        movementList.add(movementOne);
        movementList.add(movementTwo);
        List<Movement> expected = movementList;

        bankSavingsAccount.addMovement(movementOne);
        bankSavingsAccount.addMovement(movementTwo);
        List<Movement> result = bankSavingsAccount.getListOfMovements();

        assertEquals(expected, result);
    }
    @Test
    void hasIDTrueTest() {
        IAccount bankSavingsAccountOne = new BankSavingsAccount(personID, designation);
        bankSavingsAccountOne.setAccountID(accountID);

        assertTrue(bankSavingsAccountOne.hasID(accountID));
    }
    @Test
    void hasIDFalseTest() {
        IAccount bankSavingsAccountOne = new BankSavingsAccount(personID, designation);
        bankSavingsAccountOne.setAccountID(accountID);

        assertFalse(bankSavingsAccountOne.hasID(otherAccountID));
    }
    @Test
    void equalsBankSavingsAccountTest(){
        IAccount bankSavingsAccountOne = new BankSavingsAccount(personID, designation);
        IAccount bankSavingsAccountTwo = new BankSavingsAccount(personID, designation);

        assertEquals(bankSavingsAccountOne, bankSavingsAccountTwo);
        assertNotSame(bankSavingsAccountOne, bankSavingsAccountTwo);
    }
    @Test
    void equalsBankSavingsAccountAllArgsTest(){
        IAccount bankSavingsAccountOne = new BankSavingsAccount(accountID, personID, designation, movementList);
        IAccount bankSavingsAccountTwo = new BankSavingsAccount(accountID, personID, designation, movementList);

        assertEquals(bankSavingsAccountOne, bankSavingsAccountTwo);
        assertNotSame(bankSavingsAccountOne, bankSavingsAccountTwo);
    }
    @Test
    void equalsBankSavingsAccountNoArgsTest(){
        IAccount bankSavingsAccountOne = new BankSavingsAccount();
        IAccount bankSavingsAccountTwo = new BankSavingsAccount();

        assertEquals(bankSavingsAccountOne, bankSavingsAccountTwo);
        assertNotSame(bankSavingsAccountOne, bankSavingsAccountTwo);
    }
    @Test
    void equalsBankSavingsAccountIsSameTest(){
        IAccount bankSavingsAccountOne = new BankSavingsAccount(personID, designation);
        IAccount bankSavingsAccountTwo = bankSavingsAccountOne;

        assertEquals(bankSavingsAccountOne, bankSavingsAccountTwo);
        assertSame(bankSavingsAccountOne, bankSavingsAccountTwo);
    }
    @Test
    void equalsBankSavingsAccountAllIsDifferentTest(){
        IAccount bankSavingsAccountOne = new BankSavingsAccount(personID, designation);
        bankSavingsAccountOne.setAccountID(accountID);
        IAccount bankSavingsAccountTwo = new BankSavingsAccount(personID, designation);
        bankSavingsAccountTwo.setAccountID(otherAccountID);

        assertNotEquals(bankSavingsAccountOne, bankSavingsAccountTwo);
    }
    @Test
    void equalsBankSavingsAccountIsDifferentClassTest(){
        IAccount bankSavingsAccountOne = new BankSavingsAccount(personID, designation);

        assertNotEquals(bankSavingsAccountOne, accountID);
        assertNotSame(bankSavingsAccountOne, accountID);
    }
    @Test
    void equalsBankSavingsAccountIsDifferentFromNullTest(){
        IAccount bankSavingsAccountOne = new BankSavingsAccount(personID, designation);
        String nullString = null;

        assertNotEquals(bankSavingsAccountOne, nullString);
        assertNotSame(bankSavingsAccountOne, nullString);
    }
    @Test
    void hashCodeBankSavingsAccountIsEqualsTest() {
        IAccount bankSavingsAccountOne = new BankSavingsAccount(personID, designation);
        IAccount bankSavingsAccountTwo = new BankSavingsAccount(personID, designation);

        assertEquals(bankSavingsAccountOne.hashCode(), bankSavingsAccountTwo.hashCode());
        assertNotSame(bankSavingsAccountOne, bankSavingsAccountTwo);
    }
    @Test
    void hashCodeBankSavingsAccountIsDifferentTest() {
        IAccount bankSavingsAccountOne = new BankSavingsAccount(personID, designation);
        bankSavingsAccountOne.setAccountID(accountID);
        IAccount bankSavingsAccountTwo = new BankSavingsAccount(personID, designation);
        bankSavingsAccountTwo.setAccountID(otherAccountID);

        assertNotEquals(bankSavingsAccountOne.hashCode(), bankSavingsAccountTwo.hashCode());
    }




}