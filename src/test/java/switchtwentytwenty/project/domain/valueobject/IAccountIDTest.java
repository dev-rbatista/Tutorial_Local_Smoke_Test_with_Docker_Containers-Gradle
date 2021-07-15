package switchtwentytwenty.project.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class IAccountIDTest {

    Long idOne = 100L;
    Long idTwo = 200L;

    @Test
    void constructorTestValidObject(){
        AccountID accountID = new AccountID(idOne);

        assertNotNull(accountID);
    }

    @Test
    void invalidIDShouldThrowException(){
        assertThrows(IllegalArgumentException.class,()-> {
            AccountID accountID = new AccountID(null);
        });
    }

    @Test
    void equalsTestEqualObjects(){
        AccountID accountIDOne = new AccountID(idOne);
        AccountID accountIDTwo = new AccountID(idOne);

        assertEquals(accountIDOne,accountIDTwo);
        assertNotSame(accountIDOne,accountIDTwo);
    }

    @Test
    void equalsTestSameObject(){
        AccountID accountIDOne = new AccountID(idOne);
        AccountID accountIDTwo = accountIDOne;

        assertEquals(accountIDOne,accountIDTwo);
    }

    @Test
    void equalsTestDifferentAccountIDs(){
        AccountID accountIDOne = new AccountID(idOne);
        AccountID accountIDTwo = new AccountID(idTwo);

        assertNotEquals(accountIDOne,accountIDTwo);
    }

    @Test
    void equalsTestDifferentObjects(){
        AccountID accountID = new AccountID(idOne);
        LocalDate notAccountID = LocalDate.now();

        assertNotEquals(accountID,notAccountID);
    }

    @Test
    void hashCodeSameHashCode(){
        AccountID accountIDOne = new AccountID(idOne);
        AccountID accountIDTwo = new AccountID(idOne);

        assertEquals(accountIDOne.hashCode(),accountIDTwo.hashCode());
        assertNotSame(accountIDOne,accountIDTwo);
    }

    @Test
    void hashCodeDifferentHashCode(){
        AccountID accountIDOne = new AccountID(idOne);
        AccountID accountIDTwo = new AccountID(idTwo);

        assertNotEquals(accountIDOne.hashCode(),accountIDTwo.hashCode());
    }

    @Test
    void getAccountSameID() {
        AccountID accountIDOne = new AccountID(idOne);
        AccountID accountIDTwo = new AccountID(idOne);
        Long result = accountIDOne.getId();
        Long expected = accountIDTwo.getId();

        assertEquals(result, expected);
        assertSame(result, expected);
    }

    @Test
    void getAccountDifferentID(){
        AccountID accountIDOne = new AccountID(idOne);
        AccountID accountIDTwo = new AccountID(idTwo);
        Long result = accountIDOne.getId();
        Long expected = accountIDTwo.getId();

        assertNotEquals(result, expected);
    }

    @Test
    void setAccountSameID(){
        AccountID accountIDOne = new AccountID(idOne);
        AccountID accountIDTwo = new AccountID(idTwo);
        accountIDTwo.setId(idOne);

        assertEquals(accountIDOne, accountIDTwo);
        assertNotSame(accountIDOne, accountIDTwo);
    }

    @Test
    void setAccountDifferentID(){
        AccountID accountIDOne = new AccountID(idOne);
        AccountID accountIDTwo = new AccountID(idOne);
        accountIDTwo.setId(idTwo);

        assertNotEquals(accountIDOne, accountIDTwo);
    }
}