package switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.datamodel.domainjpa.AccountJPA;
import switchtwentytwenty.project.datamodel.domainjpa.MovementJPA;
import switchtwentytwenty.project.datamodel.domainjpa.OwnerIDJPA;
import switchtwentytwenty.project.domain.aggregates.account.CashAccount;
import switchtwentytwenty.project.domain.aggregates.account.IAccount;
import switchtwentytwenty.project.domain.valueobject.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountDataDomainAssemblerTest {
    AccountDataDomainAssembler accountDataDomainAssembler = new AccountDataDomainAssembler();

    @Test
    void createAccountID() {
        Long accountIDJPA = 1L;
        OwnerIDJPA ownerIDJPA = new OwnerIDJPA("email@email.com");
        AccountJPA accountJPA = new AccountJPA(accountIDJPA, ownerIDJPA, "account", "cash");

        AccountID result = accountDataDomainAssembler.createAccountID(accountJPA);
        AccountID expected = new AccountID(1L);

        assertNotNull(result);
        Assertions.assertNotSame(expected, result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void createPersonID() {
        Long accountIDJPA = 1L;
        OwnerIDJPA ownerIDJPA = new OwnerIDJPA("email@email.com");
        AccountJPA accountJPA = new AccountJPA(accountIDJPA, ownerIDJPA, "account", "cash");

        IOwnerID result = accountDataDomainAssembler.createOwnerID(accountJPA);
        PersonID expected = new PersonID("email@email.com");

        assertNotNull(result);
        Assertions.assertNotSame(expected, result);
        Assertions.assertEquals(expected, result);

    }

    @Test
    void createDesignation() {
        Long accountIDJPA = 1L;
        OwnerIDJPA ownerIDJPA = new OwnerIDJPA("email@email.com");
        AccountJPA accountJPA = new AccountJPA(accountIDJPA, ownerIDJPA, "account", "cash");

        Designation result = accountDataDomainAssembler.createDesignation(accountJPA);
        Designation expected = new Designation("account");

        assertNotNull(result);
        Assertions.assertNotSame(expected, result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void createAccountType() {
        Long accountIDJPA = 1L;
        OwnerIDJPA ownerIDJPA = new OwnerIDJPA("email@email.com");
        AccountJPA accountJPA = new AccountJPA(accountIDJPA, ownerIDJPA, "account", "cash");

        AccountType result = accountDataDomainAssembler.createAccountType(accountJPA);
        AccountType expected = new AccountType("cash");

        assertNotNull(result);
        Assertions.assertNotSame(expected, result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void createOwnerIDReturnFamilyID() {
        Long accountIDJPA = 1L;
        OwnerIDJPA ownerIDJPA = new OwnerIDJPA("@email@email.com");
        AccountJPA accountJPA = new AccountJPA(accountIDJPA, ownerIDJPA, "account", "cash");

        IOwnerID result = accountDataDomainAssembler.createOwnerID(accountJPA);
        FamilyID expected = new FamilyID("@email@email.com");

        assertNotNull(result);
        Assertions.assertNotSame(expected, result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void createOwnerIDReturnPersonID() {
        Long accountIDJPA = 1L;
        OwnerIDJPA ownerIDJPA = new OwnerIDJPA("email@email.com");
        AccountJPA accountJPA = new AccountJPA(accountIDJPA, ownerIDJPA, "account", "cash");

        IOwnerID result = accountDataDomainAssembler.createOwnerID(accountJPA);
        PersonID expected = new PersonID("email@email.com");

        assertNotNull(result);
        Assertions.assertNotSame(expected, result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void toDataTest() {
        AccountID accountID = new AccountID(12L);
        IOwnerID ownerID = new PersonID("administrator@email.com");
        Designation designation = new Designation("Cash Account");
        List<Movement> movements = new ArrayList<>();
        IAccount account = new CashAccount(accountID, ownerID, designation, movements);
        AccountJPA expected = new AccountJPA(new Long(12L), new OwnerIDJPA(), "Cash Account", "Cash Account");

        AccountJPA result = accountDataDomainAssembler.toData(account);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void toDataVariousMovementsTest(){
        AccountID accountID = new AccountID(12L);
        IOwnerID ownerID = new PersonID("administrator@email.com");
        Designation designation = new Designation("Cash Account");
        List<Movement> movements = new ArrayList<>();
        movements.add(new Movement(new MonetaryValue("EUR",BigDecimal.valueOf(100))));
        IAccount account = new CashAccount(accountID,ownerID,designation,movements);
        AccountJPA expected = new AccountJPA(new Long(12L),new OwnerIDJPA(),"Cash Account","Cash Account");

        AccountJPA result = accountDataDomainAssembler.toData(account);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void createMovementsTest() {
        AccountJPA accountJPA = new AccountJPA(new Long(12L), new OwnerIDJPA(), "Cash Account", "Cash Account");
        List<Movement> expected = new ArrayList<>();
        Movement movement = new Movement(new MonetaryValue("EUR", BigDecimal.valueOf(100)));
        expected.add(movement);
        List<MovementJPA> movementsJPA = new ArrayList<>();
        MovementJPA movementJPA = new MovementJPA(100L, "EUR", accountJPA);
        movementsJPA.add(movementJPA);

        accountJPA.setMovements(movementsJPA);
        List<Movement> result = accountDataDomainAssembler.createMovements(accountJPA);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void toDataNoIDJPA() {
        CashAccount account = new CashAccount();
        IOwnerID ownerID = new PersonID("tonyze@latinlover.com");
        account.setOwner(ownerID);
        account.setDesignation(new Designation("des"));
        List<Movement> movements = new ArrayList<>();
        movements.add(new Movement(new MonetaryValue("EUR", BigDecimal.valueOf(15))));
        movements.add(new Movement(new MonetaryValue("EUR", BigDecimal.valueOf(10))));
        account.setMovements(movements);
        AccountJPA result = accountDataDomainAssembler.toData(account);

        assertNull(result.getId());
    }

    @Test
    void toDataMovementaNotNullINJPA() {
        CashAccount account = new CashAccount();
        IOwnerID ownerID = new PersonID("tonyze@latinlover.com");
        account.setOwner(ownerID);
        account.setDesignation(new Designation("des"));
        List<Movement> movements = new ArrayList<>();
        movements.add(new Movement(new MonetaryValue("EUR", BigDecimal.valueOf(15))));
        movements.add(new Movement(new MonetaryValue("EUR", BigDecimal.valueOf(10))));
        account.setMovements(movements);
        AccountJPA result = accountDataDomainAssembler.toData(account);
        List<MovementJPA> movementsJPA = new ArrayList<>();
        movementsJPA.add(new MovementJPA(15L,"EUR",result));
        movementsJPA.add(new MovementJPA(10L,"EUR",result));

        assertNotNull(result.getMovements());
        assertEquals(result.getMovements(),movementsJPA);
    }
}