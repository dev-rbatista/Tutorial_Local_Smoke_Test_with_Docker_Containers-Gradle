package switchtwentytwenty.project.datamodel.domainjpa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovementJPATest {

    Long id = 3L;
    Long amount = 33L;
    String currency = "EUR";

    Long accountIDJPA = 123456L;
    OwnerIDJPA ownerIDJPA = new OwnerIDJPA("tonyze@latinlover.com");
    String designation = "Conta do Ze";
    String accountType = "Current";
    AccountJPA account = new AccountJPA(accountIDJPA, ownerIDJPA, designation, accountType);

    Long otherAccountIDJPA = 99999L;
    AccountJPA otherAccount = new AccountJPA(otherAccountIDJPA, ownerIDJPA, designation, accountType);
    Long otherID = new Long(6L);


    @Test
    void getMovementIdTest() {
        MovementJPA movement = new MovementJPA(amount, currency, account);
        movement.setId(id);

        Long expected = 3L;

        Long result = movement.getId();

        assertEquals(expected, result);
    }

    @Test
    void getMovementAmountTest() {
        MovementJPA movement = new MovementJPA(amount, currency, account);

        Long expected = 33L;

        Long result = movement.getAmount();

        assertEquals(expected, result);
    }

    @Test
    void getMovementCurrencyTest() {
        MovementJPA movement = new MovementJPA(amount, currency, account);

        String expected = "EUR";

        String result = movement.getCurrency();

        assertEquals(expected, result);
    }

    @Test
    void getMovementAccountTest() {
        MovementJPA movement = new MovementJPA(amount, currency, account);

        AccountJPA expected = new AccountJPA(accountIDJPA, ownerIDJPA, designation, accountType);

        AccountJPA result = movement.getAccount();

        assertEquals(expected, result);
    }

    @Test
    void equalsMovementEqualMovementTest() {
        MovementJPA movementOne = new MovementJPA(amount, currency, account);
        MovementJPA movementTwo = new MovementJPA(amount, currency, account);

        assertEquals(movementOne, movementTwo);
        assertNotSame(movementOne, movementTwo);
    }

    @Test
    void equalsMovementEqualNoArgsMovementTest() {
        MovementJPA movementOne = new MovementJPA();
        MovementJPA movementTwo = movementOne;

        assertEquals(movementOne, movementTwo);
    }

    @Test
    void equalsMovementIsSameMovementTest() {
        MovementJPA movementOne = new MovementJPA(amount, currency, account);
        MovementJPA movementTwo = movementOne;

        assertEquals(movementOne, movementTwo);
        assertSame(movementOne, movementTwo);
    }

    @Test
    void equalsMovementDifferentAccountTest() {
        MovementJPA movementOne = new MovementJPA(amount, currency, account);
        MovementJPA movementTwo = new MovementJPA(amount, currency, otherAccount);

        assertNotEquals(movementOne, movementTwo);
    }
    @Test
    void equalsMovementDifferentIDTest() {
        MovementJPA movementOne = new MovementJPA(amount, currency, account);
        movementOne.setId(id);
        MovementJPA movementTwo = new MovementJPA(amount, currency, account);
        movementTwo.setId(otherID);
        assertNotEquals(movementOne, movementTwo);
    }

    @Test
    void equalsMovementDifferentClassMovementTest() {
        MovementJPA movementOne = new MovementJPA(amount, currency, account);

        assertNotEquals(movementOne, id);
    }
    @Test
    void equalsMovementSameClassMovementTest() {
        MovementJPA movementOne = new MovementJPA(amount, currency, account);
        MovementJPA movementTwo = new MovementJPA(amount, currency, otherAccount);

        assertEquals(movementOne.getClass(), movementTwo.getClass());
    }

    @Test
    void equalsMovementDifferentFromNullTest() {
        MovementJPA movementOne = new MovementJPA(amount, currency, account);
        String nullString = null;

        assertNotEquals(movementOne, nullString);
    }

    @Test
    void hashCodeMovementJPAIsEquals() {
        MovementJPA movementOne = new MovementJPA(amount, currency, account);
        MovementJPA movementTwo = new MovementJPA(amount, currency, account);

        assertEquals(movementOne.hashCode(), movementTwo.hashCode());
        assertNotSame(movementOne, movementTwo);
    }

    @Test
    void hashCodeMovementJPAAccountIsDifferent() {
        MovementJPA movementOne = new MovementJPA(amount, currency, account);
        MovementJPA movementTwo = new MovementJPA(amount, currency, otherAccount);

        assertNotEquals(movementOne.hashCode(), movementTwo.hashCode());
    }
    @Test
    void hashCodeMovementJPAIDIsDifferent() {
        MovementJPA movementOne = new MovementJPA(amount, currency, account);
        movementOne.setId(id);
        MovementJPA movementTwo = new MovementJPA(amount, currency, account);
        movementTwo.setId(otherID);

        assertNotEquals(movementOne.hashCode(), movementTwo.hashCode());
    }
}