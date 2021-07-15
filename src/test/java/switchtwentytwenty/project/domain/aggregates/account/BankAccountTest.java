package switchtwentytwenty.project.domain.aggregates.account;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.valueobject.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

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
        IAccount bankAccount = new BankAccount(personID, designation);
        bankAccount.setAccountID(accountID);

        AccountID expected = accountID;

        AccountID result = bankAccount.id();

        assertEquals(expected, result);
    }
    @Test
    void getOwnerIDTest() {
        IAccount bankAccount = new BankAccount(personID, designation);

        IOwnerID expected = new PersonID("tonyze@latinlover.com");

        IOwnerID result = bankAccount.getOwnerId();

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }
    @Test
    void getDesignationTest() {
        IAccount bankAccount = new BankAccount();
        bankAccount.setDesignation(designation);

        Designation expected = designation;

        Designation result = bankAccount.getDesignation();

        assertEquals(expected, result);
    }
    @Test
    void getListOfMovementsTest() {
        IAccount bankAccount = new BankAccount(personID, designation);

        movementList.add(movementOne);
        movementList.add(movementTwo);
        bankAccount.setMovements(movementList);

        List<Movement> expected = movementList;

        List<Movement> result = bankAccount.getListOfMovements();

        assertEquals(expected, result);
    }
    @Test
    void getListOfMovementsEmptyListTest() {
        IAccount bankAccount = new BankAccount(personID, designation);

        List<Movement> expected = new ArrayList<>();

        List<Movement> result = bankAccount.getListOfMovements();

        assertEquals(expected, result);
    }

    @Test
    void getAccountTypeTest() {
        IAccount bankAccount = new BankAccount();

        String expected = "bank";

        String result = bankAccount.getAccountType();

        assertEquals(expected, result);
    }
    @Test
    void setOwnerTest() {
        IAccount bankAccountOne = new BankAccount();
        bankAccountOne.setOwner(personID);

        IOwnerID expected = new PersonID("tonyze@latinlover.com");

        IOwnerID result = bankAccountOne.getOwnerId();

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }
    @Test
    void setDesignation() {
        IAccount bankAccountOne = new BankAccount();
        bankAccountOne.setDesignation(designation);

        Designation expected = new Designation("noitadas");

        Designation result = bankAccountOne.getDesignation();

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }
    @Test
    void addMovementTest(){
        IAccount bankAccount = new BankAccount(personID, designation);

        movementList.add(movementOne);
        movementList.add(movementTwo);
        List<Movement> expected = movementList;

        bankAccount.addMovement(movementOne);
        bankAccount.addMovement(movementTwo);
        List<Movement> result = bankAccount.getListOfMovements();

        assertEquals(expected, result);
    }
    @Test
    void hasIDTrueTest() {
        IAccount bankAccountOne = new BankAccount(personID, designation);
        bankAccountOne.setAccountID(accountID);

        assertTrue(bankAccountOne.hasID(accountID));
    }
    @Test
    void hasIDFalseTest() {
        IAccount bankAccountOne = new BankAccount(personID, designation);
        bankAccountOne.setAccountID(accountID);

        assertFalse(bankAccountOne.hasID(otherAccountID));
    }
    @Test
    void equalsBankAccountTest(){
        IAccount bankAccountOne = new BankAccount(personID, designation);
        IAccount bankAccountTwo = new BankAccount(personID, designation);

        assertEquals(bankAccountOne, bankAccountTwo);
        assertNotSame(bankAccountOne, bankAccountTwo);
    }
    @Test
    void equalsBankAccountAllArgsTest(){
        IAccount bankAccountOne = new BankAccount(accountID, personID, designation, movementList);
        IAccount bankAccountTwo = new BankAccount(accountID, personID, designation, movementList);

        assertEquals(bankAccountOne, bankAccountTwo);
        assertNotSame(bankAccountOne, bankAccountTwo);
    }
    @Test
    void equalsBankAccountNoArgsTest(){
        IAccount bankAccountOne = new BankAccount();
        IAccount bankAccountTwo = new BankAccount();

        assertEquals(bankAccountOne, bankAccountTwo);
        assertNotSame(bankAccountOne, bankAccountTwo);
    }
    @Test
    void equalsBankAccountIsSameTest(){
        IAccount bankAccountOne = new BankAccount(personID, designation);
        IAccount bankAccountTwo = bankAccountOne;

        assertEquals(bankAccountOne, bankAccountTwo);
        assertSame(bankAccountOne, bankAccountTwo);
    }
    @Test
    void equalsBankAccountAllIsDifferentTest(){
        IAccount bankAccountOne = new BankAccount(personID, designation);
        bankAccountOne.setAccountID(accountID);
        IAccount bankAccountTwo = new BankAccount(personID, designation);
        bankAccountTwo.setAccountID(otherAccountID);

        assertNotEquals(bankAccountOne, bankAccountTwo);
    }
    @Test
    void equalsBankAccountIsDifferentClassTest(){
        IAccount bankAccountOne = new BankAccount(personID, designation);

        assertNotEquals(bankAccountOne, accountID);
        assertNotSame(bankAccountOne, accountID);
    }
    @Test
    void equalsBankAccountIsDifferentFromNullTest(){
        IAccount bankAccountOne = new BankAccount(personID, designation);
        String nullString = null;

        assertNotEquals(bankAccountOne, nullString);
        assertNotSame(bankAccountOne, nullString);
    }
    @Test
    void hashCodeBankAccountIsEqualsTest() {
        IAccount bankAccountOne = new BankAccount(personID, designation);
        IAccount bankAccountTwo = new BankAccount(personID, designation);

        assertEquals(bankAccountOne.hashCode(), bankAccountTwo.hashCode());
        assertNotSame(bankAccountOne, bankAccountTwo);
    }
    @Test
    void hashCodeBankAccountIsDifferentTest() {
        IAccount bankAccountOne = new BankAccount(personID, designation);
        bankAccountOne.setAccountID(accountID);
        IAccount bankAccountTwo = new BankAccount(personID, designation);
        bankAccountTwo.setAccountID(otherAccountID);

        assertNotEquals(bankAccountOne.hashCode(), bankAccountTwo.hashCode());
    }



}