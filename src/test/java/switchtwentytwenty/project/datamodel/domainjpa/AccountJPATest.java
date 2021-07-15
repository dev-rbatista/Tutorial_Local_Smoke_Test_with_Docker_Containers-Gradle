package switchtwentytwenty.project.datamodel.domainjpa;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountJPATest {

    Long accountIDJPA = 123456L;
    OwnerIDJPA ownerIDJPA = new OwnerIDJPA("tonyze@latinlover.com");
    String designation = "Conta do Ze";
    String accountType = "Current";
    List<MovementJPA> movements = new ArrayList<MovementJPA>();

    Long accountIDJPATwo = 999999L;

    Long amount = 33L;
    String currency = "EUR";
    Long otherAmount = 66L;


    @Test
    void getAccountIDTest() {
        AccountJPA accountJPA = new AccountJPA(accountIDJPA, ownerIDJPA, designation, accountType);

        Long expectedAccountIDJPA = 123456L;
        Long result = accountJPA.getId();

        assertEquals(expectedAccountIDJPA, result);
    }

    @Test
    void getOwnerIDTest() {
        AccountJPA accountJPA = new AccountJPA(accountIDJPA, ownerIDJPA, designation, accountType);

        String expectedOwnerID = "tonyze@latinlover.com";

        String result = accountJPA.getOwnerID().toString();

        assertEquals(expectedOwnerID, result);
    }
    @Test
    void getDesignationTest() {
        AccountJPA accountJPA = new AccountJPA(accountIDJPA, ownerIDJPA, designation, accountType);

        String expectedDesignation = "Conta do Ze";

        String result = accountJPA.getDesignation();

        assertEquals(expectedDesignation, result);
    }
    @Test
    void getTestAccountTypeTest() {
        AccountJPA accountJPA = new AccountJPA(accountIDJPA, ownerIDJPA, designation, accountType);

        String expectedAccountType = "Current";

        String result = accountJPA.getAccountType();

        assertEquals(expectedAccountType, result);
    }
    @Test
    void getListOfMovementsTest() {
        AccountJPA accountJPA = new AccountJPA(accountIDJPA, ownerIDJPA, designation, accountType);
        MovementJPA movementJPAOne = new MovementJPA(amount, currency, accountJPA);
        MovementJPA movementJPATwo = new MovementJPA(otherAmount, currency, accountJPA);

        movements.add(movementJPAOne);
        movements.add(movementJPATwo);
        accountJPA.setMovements(movements);

        List<MovementJPA> expectedList = new ArrayList<>();
        expectedList.add(movementJPAOne);
        expectedList.add(movementJPATwo);

        List<MovementJPA> result = accountJPA.getMovements();

        assertEquals(expectedList, result);
    }
    @Test
    void equalsAccountJPAEqualAccountJPATest() {
        AccountJPA accountJPAOne = new AccountJPA(accountIDJPA, ownerIDJPA, designation, accountType);
        AccountJPA accountJPATwo = new AccountJPA(accountIDJPA, ownerIDJPA, designation, accountType);

        assertEquals(accountJPAOne, accountJPATwo);
        assertNotSame(accountJPAOne, accountJPATwo);
    }
    @Test
    void equalsAccountJPAEqualAccountJPANoArgsTest() {
        AccountJPA accountJPAOne = new AccountJPA();
        AccountJPA accountJPAtwo = accountJPAOne;

        assertEquals(accountJPAOne, accountJPAtwo);

    }
    @Test
    void equalsAccountJPAIsSameAccountJPA(){
        AccountJPA accountJPAOne = new AccountJPA(accountIDJPA, ownerIDJPA, designation, accountType);
        AccountJPA accountJPATwo = accountJPAOne;

        assertEquals(accountJPAOne, accountJPATwo);
        assertSame(accountJPAOne, accountJPATwo);
    }
    @Test
    void equalsAccountJPADifferentAccountJPATest() {
        AccountJPA accountJPAOne = new AccountJPA(accountIDJPA, ownerIDJPA, designation, accountType);
        AccountJPA accountJPATwo = new AccountJPA(accountIDJPATwo, ownerIDJPA, designation, accountType);

        assertNotEquals(accountJPAOne, accountJPATwo);
    }
    @Test
    void equalsAccountJPADifferentClassTest() {
        AccountJPA accountJPAOne = new AccountJPA(accountIDJPA, ownerIDJPA, designation, accountType);

        assertNotEquals(accountJPAOne, accountIDJPA);
    }
    @Test
    void equalsAccountJPADifferentFromNullTest() {
        AccountJPA accountJPAOne = new AccountJPA(accountIDJPA, ownerIDJPA, designation, accountType);
        String nullString = null;

        assertNotEquals(accountJPAOne, nullString);
    }
    @Test
    void hashCodeAccountJPAIsEquals(){
        AccountJPA accountJPAOne = new AccountJPA(accountIDJPA, ownerIDJPA, designation, accountType);
        AccountJPA accountJPATwo = new AccountJPA(accountIDJPA, ownerIDJPA, designation, accountType);

        assertEquals(accountJPAOne.hashCode(), accountJPATwo.hashCode());
        assertNotSame(accountJPAOne, accountJPATwo);
    }
    @Test
    void hashCodeAccountJPAIsDifferent(){
        AccountJPA accountJPAOne = new AccountJPA(accountIDJPA, ownerIDJPA, designation, accountType);
        AccountJPA accountJPATwo = new AccountJPA(accountIDJPATwo, ownerIDJPA, designation, accountType);

        assertNotEquals(accountJPAOne.hashCode(), accountJPATwo.hashCode());
    }
}
