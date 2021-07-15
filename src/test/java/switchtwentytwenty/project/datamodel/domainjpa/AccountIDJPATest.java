package switchtwentytwenty.project.datamodel.domainjpa;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
/*
class AccountIDJPATest {

    Long idOne = 127L;
    Long idTwo = 721L;

    @Test
    void accountIDJPAValidObject(){
        AccountIDJPA accountIDOne = new AccountIDJPA(idOne);
        assertNotNull(accountIDOne);
    }

    @Test
    void getAccountIDJPASameID(){
        AccountIDJPA accountIDOne = new AccountIDJPA(idOne);
        AccountIDJPA accountIDTwo = new AccountIDJPA(idOne);
        Long expected = accountIDOne.getId();
        Long result = accountIDTwo.getId();

        assertEquals(expected, result);
        assertSame(expected, result);
    }

    @Test
    void getAccountIDJPADifferentFromNull(){
        AccountIDJPA accountIDOne = new AccountIDJPA(idOne);
        String nullString = null;

        assertNotEquals(accountIDOne, nullString);
    }

    @Test
    void getAccountIDJPADifferentID(){
        AccountIDJPA accountIDOne = new AccountIDJPA(idOne);
        AccountIDJPA accountIDTwo = new AccountIDJPA(idTwo);
        Long expected = accountIDOne.getId();
        Long result = accountIDTwo.getId();

        assertNotEquals(expected, result);
    }


    @Test
    void setAccountIDJPADifferentObjectSameID(){
        AccountIDJPA accountIDOne = new AccountIDJPA();
        AccountIDJPA accountIDTwo = new AccountIDJPA(idTwo);
        accountIDOne.setId(idTwo);

        assertEquals(accountIDOne, accountIDTwo);
        assertNotSame(accountIDOne, accountIDTwo);
    }

    @Test
    void setAccountIDJPADifferentObjectDifferentID(){
        AccountIDJPA accountIDOne = new AccountIDJPA();
        AccountIDJPA accountIDTwo = new AccountIDJPA();
        accountIDOne.setId(idTwo);
        accountIDTwo.setId(idOne);

        assertNotEquals(accountIDOne, accountIDTwo);
    }

    @Test
    void equalsTestSameObject(){
        AccountIDJPA accountIDOne = new AccountIDJPA(idOne);
        AccountIDJPA accountIDTwo = accountIDOne;

        assertSame(accountIDOne, accountIDTwo);
    }

    @Test
    void equalsTestDifferentClass(){
        AccountIDJPA accountIDOne = new AccountIDJPA(idOne);

        assertNotEquals(accountIDOne, idOne);
    }

    @Test
    void hashCodeSameHashCode(){
        AccountIDJPA accountIDOne = new AccountIDJPA(idOne);
        AccountIDJPA accountIDTwo = new AccountIDJPA(idOne);

        assertEquals(accountIDOne.hashCode(), accountIDTwo.hashCode());
        assertNotSame(accountIDOne, accountIDTwo);
    }

    @Test
    void hashCodeDifferentHashCode(){
        AccountIDJPA accountIDOne = new AccountIDJPA(idOne);
        AccountIDJPA accountIDTwo = new AccountIDJPA(idTwo);

        assertNotEquals(accountIDOne.hashCode(), accountIDTwo.hashCode());
    }

    @Test
    void toLongSameID(){
        AccountIDJPA accountIDOne = new AccountIDJPA(idOne);
        AccountIDJPA accountIDTwo = new AccountIDJPA(idOne);
        Long expected = accountIDOne.toLong();
        Long result = accountIDTwo.toLong();

        assertEquals(expected, result);
        assertSame(expected, result);
    }

    @Test
    void toLongDifferentID(){
        AccountIDJPA accountIDOne = new AccountIDJPA(idOne);
        AccountIDJPA accountIDTwo = new AccountIDJPA();
        Long expected = accountIDOne.toLong();
        Long result = accountIDTwo.toLong();

        assertNotEquals(expected, result);
        assertNotSame(expected, result);
    }


}

 */

