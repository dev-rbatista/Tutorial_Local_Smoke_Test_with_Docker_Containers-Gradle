package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregates.account.*;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.accounts.InputAccountDTO;
import switchtwentytwenty.project.dto.accounts.OutputAccountDTO;
import switchtwentytwenty.project.dto.assemblers.iassemblers.IAccountDTODomainAssembler;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class AccountDTODomainAssemblerTest {

    AccountDTODomainAssembler accountDTODomainAssembler = new AccountDTODomainAssembler();

    InputAccountDTO inputAccountDTOa = new InputAccountDTO("balelas", BigDecimal.valueOf(10), "EUR", "tonyze@latinlover.com", "CashAccount");
    InputAccountDTO inputAccountDTOb = new InputAccountDTO("balelas", BigDecimal.valueOf(10), "EUR", "tonyze@latinlover.com", "CreditCardAccount");
    InputAccountDTO inputAccountDTOc = new InputAccountDTO("balelas", BigDecimal.valueOf(10), "EUR", "tonyze@latinlover.com", "BankAccount");
    InputAccountDTO inputAccountDTOd = new InputAccountDTO("balelas", BigDecimal.valueOf(10), "EUR", "tonyze@latinlover.com", "BankSavingsAccount");
    InputAccountDTO inputAccountDTOe = new InputAccountDTO("balelas", BigDecimal.valueOf(10), "EUR", "@tonyze@latinlover.com", "CashAccount");

    @Test
    void designationToDomainSuccess() {
        Designation expected = new Designation("balelas");
        Designation result = accountDTODomainAssembler.designationToDomain(inputAccountDTOa);

        assertEquals(expected, result);
    }

    @Test
    void initialAmountToDomainSuccess() {
        MonetaryValue expected = new MonetaryValue("EUR", BigDecimal.valueOf(10));
        MonetaryValue result = accountDTODomainAssembler.initialAmountToDomain(inputAccountDTOa);

        assertEquals(expected, result);

    }

    @Test
    void ownerIDIsPersonToDomainSuccess() {
        PersonID expected = new PersonID("tonyze@latinlover.com");
        IOwnerID result = accountDTODomainAssembler.ownerIDToDomain(inputAccountDTOa);

        assertEquals(expected, result);
    }

    @Test
    void ownerIDIsFamilyToDomainSuccess() {
        IOwnerID expected = new FamilyID("@tonyze@latinlover.com");
        IOwnerID result = accountDTODomainAssembler.ownerIDToDomain(inputAccountDTOe);

        assertEquals(expected, result);
    }

    @Test
    void accountTypeCashAccountToDomainSuccess() {
        String expected = "CashAccount";
        String result = accountDTODomainAssembler.accountTypeToDomain(inputAccountDTOa);

        assertEquals(expected, result);
    }

    @Test
    void accountTypeCreditCardAccountToDomainSuccess() {
        String expected = "CreditCardAccount";
        String result = accountDTODomainAssembler.accountTypeToDomain(inputAccountDTOb);

        assertEquals(expected, result);
    }

    @Test
    void accountTypeBankAccountToDomainSuccess() {
        String expected = "BankAccount";
        String result = accountDTODomainAssembler.accountTypeToDomain(inputAccountDTOc);

        assertEquals(expected, result);
    }

    @Test
    void accountTypeBankSavingsAccountToDomainSuccess() {
        String expected = "BankSavingsAccount";
        String result = accountDTODomainAssembler.accountTypeToDomain(inputAccountDTOd);

        assertEquals(expected, result);
    }

    @Test
    void fromCashAccountSavedtoDTO() {

        IAccountDTODomainAssembler accountDTODomainAssembler = new AccountDTODomainAssembler();
        //account A
        AccountID accountIDA = new AccountID(123L);
        IOwnerID ownerIDA = new PersonID("tonyze@latinlover.com");
        Designation designationA = new Designation("balelas");
        //account B
        AccountID accountIDB = new AccountID(456L);
        IOwnerID ownerIDB = new PersonID("katia@latinas.com");
        Designation designationB = new Designation("chungaria");

        IAccount savedAccountA = new CashAccount(accountIDA, ownerIDA, designationA, Collections.emptyList());
        IAccount savedAccountB = new CashAccount(accountIDB, ownerIDB, designationB, Collections.emptyList());

        OutputAccountDTO expected = new OutputAccountDTO(accountIDA.toString(), ownerIDA.toString(), designationA.toString());
        OutputAccountDTO resultA = accountDTODomainAssembler.toDTO(savedAccountA);
        OutputAccountDTO resultB = accountDTODomainAssembler.toDTO(savedAccountB);
        expected.removeLinks();
        resultA.removeLinks();
        assertEquals(expected, resultA);
        assertNotSame(expected, resultB);
    }

    @Test
    void fromCreditCardAccountSavedtoDTO() {
        //account A
        AccountID accountIDA = new AccountID(123L);
        IOwnerID ownerIDA = new PersonID("tonyze@latinlover.com");
        Designation designationA = new Designation("balelas");
        //account B
        AccountID accountIDB = new AccountID(456L);
        IOwnerID ownerIDB = new PersonID("katia@latinas.com");
        Designation designationB = new Designation("chungaria");

        IAccount savedAccountA = new CreditCardAccount(accountIDA, ownerIDA, designationA, Collections.emptyList());
        IAccount savedAccountB = new CreditCardAccount(accountIDB, ownerIDB, designationB, Collections.emptyList());

        OutputAccountDTO expected = new OutputAccountDTO(accountIDA.toString(), ownerIDA.toString(), designationA.toString());
        OutputAccountDTO resultA = accountDTODomainAssembler.toDTO(savedAccountA);
        OutputAccountDTO resultB = accountDTODomainAssembler.toDTO(savedAccountB);

        assertEquals(expected, resultA);
        assertNotSame(expected, resultB);
    }

    @Test
    void fromBankAccountSavedtoDTO() {
        //account A
        AccountID accountIDA = new AccountID(123L);
        IOwnerID ownerIDA = new PersonID("tonyze@latinlover.com");
        Designation designationA = new Designation("balelas");
        //account B
        AccountID accountIDB = new AccountID(456L);
        IOwnerID ownerIDB = new PersonID("katia@latinas.com");
        Designation designationB = new Designation("chungaria");

        IAccount savedAccountA = new BankAccount(accountIDA, ownerIDA, designationA, Collections.emptyList());
        IAccount savedAccountB = new BankAccount(accountIDB, ownerIDB, designationB, Collections.emptyList());

        OutputAccountDTO expected = new OutputAccountDTO(accountIDA.toString(), ownerIDA.toString(), designationA.toString());
        OutputAccountDTO resultA = accountDTODomainAssembler.toDTO(savedAccountA);
        OutputAccountDTO resultB = accountDTODomainAssembler.toDTO(savedAccountB);

        assertEquals(expected, resultA);
        assertNotSame(expected, resultB);
    }

    @Test
    void fromBankSavingsAccountSavedtoDTO() {
        //account A
        AccountID accountIDA = new AccountID(123L);
        IOwnerID ownerIDA = new PersonID("tonyze@latinlover.com");
        Designation designationA = new Designation("balelas");
        //account B
        AccountID accountIDB = new AccountID(456L);
        PersonID ownerIDB = new PersonID("katia@latinas.com");
        Designation designationB = new Designation("chungaria");

        IAccount savedAccountA = new BankSavingsAccount(accountIDA, ownerIDA, designationA, Collections.emptyList());
        IAccount savedAccountB = new BankSavingsAccount(accountIDB, ownerIDB, designationB, Collections.emptyList());

        OutputAccountDTO expected = new OutputAccountDTO(accountIDA.toString(), ownerIDA.toString(), designationA.toString());
        OutputAccountDTO resultA = accountDTODomainAssembler.toDTO(savedAccountA);
        OutputAccountDTO resultB = accountDTODomainAssembler.toDTO(savedAccountB);

        assertEquals(expected, resultA);
        assertNotSame(expected, resultB);
    }

}