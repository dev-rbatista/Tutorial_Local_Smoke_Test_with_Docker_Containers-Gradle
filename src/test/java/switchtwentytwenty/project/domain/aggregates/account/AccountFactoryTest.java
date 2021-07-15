package switchtwentytwenty.project.domain.aggregates.account;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentytwenty.project.domain.valueobject.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class AccountFactoryTest {

    @Autowired
    AccountFactory accountFactory;

    Designation designation = new Designation("Compras");
    MonetaryValue monetaryValue = new MonetaryValue("EUR", BigDecimal.valueOf(20.00));
    IOwnerID ownerID = new PersonID("toni@emial.com");
    String invalidAccountType = "CryptoAccount";
    String bankAccountType = "bank";
    String cashAccountType = "cash";
    AccountID accountID = new AccountID(3L);
    List<Movement> movements = new ArrayList<>();
    Movement movement = new Movement(monetaryValue);


    @DisplayName("Account is successfully created with Monetary")
    @Test
    public void createAccountWithMonetarySuccess() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        IAccount expected = new BankAccount();
        expected.setDesignation(new Designation("Compras"));
        expected.addMovement(new Movement(new MonetaryValue("EUR", BigDecimal.valueOf(20.00))));
        expected.setOwner(new PersonID("toni@emial.com"));

        IAccount result = accountFactory.createAccount(designation, monetaryValue, ownerID, bankAccountType);

        assertEquals(expected, result);
    }

    @DisplayName("Account fails with Monetary - Account type dont match")
    @Test
    public void createAccountWithMonetaryFailsInvalidAccountType() {
        assertThrows(IllegalArgumentException.class,()->accountFactory.createAccount(designation, monetaryValue, ownerID, invalidAccountType));
    }

    @DisplayName("Account is successfully created with List of Movements")
    @Test
    public void createAccountWithListOfMovementsSuccess() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        IAccount expected = new CashAccount();
        expected.setAccountID(accountID);
        expected.setDesignation(designation);
        expected.setMovements(movements);
        expected.setOwner(ownerID);

        IAccount result = accountFactory.createAccount(accountID, movements, ownerID, designation, cashAccountType);

        assertEquals(expected, result);
        assertEquals(expected.getDesignation(), result.getDesignation());
        assertEquals(expected.getListOfMovements(), result.getListOfMovements());
        assertEquals(expected.getOwnerId(),result.getOwnerId());
    }

    @Test
    public void createAccountTestExistingAccount() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Designation designation = new Designation("Compras");
        MonetaryValue monetaryValue = new MonetaryValue("EUR", BigDecimal.valueOf(20.00));
        Movement movement = new Movement(monetaryValue);
        List<Movement> movementList = new ArrayList<>();
        movementList.add(movement);
        IOwnerID ownerID = new PersonID("toni@emial.com");
        String accountType = "bank";

        IAccount expected = new BankAccount();
        expected.setAccountID(new AccountID(0L));
        expected.setDesignation(new Designation("Compras"));
        expected.addMovement(new Movement(new MonetaryValue("EUR", BigDecimal.valueOf(20.00))));
        expected.setOwner(new PersonID("toni@emial.com"));

        IAccount result = accountFactory.createAccount(new AccountID(0L), movementList, ownerID, designation, accountType);

        assertEquals(expected, result);
        assertEquals(expected.getDesignation(), result.getDesignation());
        assertEquals(expected.getListOfMovements(), result.getListOfMovements());
        assertEquals(expected.getOwnerId(),result.getOwnerId());
    }

    @Test
    public void createAccountTestNewAccount() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Designation designation = new Designation("Compras");
        MonetaryValue monetaryValue = new MonetaryValue("EUR", BigDecimal.valueOf(20.00));
        IOwnerID ownerID = new PersonID("toni@emial.com");
        String accountType = "bank";

        IAccount expected = new BankAccount();
        expected.setDesignation(new Designation("Compras"));
        expected.addMovement(new Movement(new MonetaryValue("EUR", BigDecimal.valueOf(20.00))));
        expected.setOwner(new PersonID("toni@emial.com"));

        IAccount result = accountFactory.createAccount(designation, monetaryValue, ownerID , accountType);

        assertEquals(expected, result);
        assertEquals(expected.getDesignation(), result.getDesignation());
        assertEquals(expected.getListOfMovements(), result.getListOfMovements());
        assertEquals(expected.getOwnerId(),result.getOwnerId());
    }

    @DisplayName("Account with Movements fails with Monetary - Account type dont match")
    @Test
    public void createAccountWithListOfMovementsFailInvalidAccountType(){
        movements.add(movement);
        assertThrows(IllegalArgumentException.class,()->accountFactory.createAccount(accountID, movements, ownerID, designation, invalidAccountType));
    }


    @Test
    void createAccountClassNotFoundExceptionNoID() {
        Designation designation = new Designation("Compras");
        MonetaryValue monetaryValue = new MonetaryValue("EUR", BigDecimal.valueOf(20.00));
        IOwnerID ownerID = new PersonID("toni@emial.com");
        String invalidAccountType = "santander";
        assertThrows(IllegalArgumentException.class,()-> accountFactory.createAccount(designation,monetaryValue,ownerID,invalidAccountType));
    }

    @Test
    void testCreateAccountClassNotFoundExceptionWithID() {
        Designation designation = new Designation("Compras");
        MonetaryValue monetaryValue = new MonetaryValue("EUR", BigDecimal.valueOf(20.00));
        IOwnerID ownerID = new PersonID("toni@emial.com");
        String invalidAccountType = "santander";
        AccountID accountID = new AccountID(2L);
        Movement movement = new Movement(monetaryValue);
        List<Movement> listOfMovements = new ArrayList<>();
        listOfMovements.add(movement);
        assertThrows(IllegalArgumentException.class,()-> accountFactory.createAccount(accountID,listOfMovements,ownerID,designation,invalidAccountType));
    }
}