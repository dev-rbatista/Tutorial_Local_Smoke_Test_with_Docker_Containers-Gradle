package switchtwentytwenty.project.interfaceadapters.implrepositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.datamodel.assemblerjpa.iassemblersjpa.IAccountDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.AccountJPA;
import switchtwentytwenty.project.datamodel.domainjpa.OwnerIDJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.IAccountRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.account.AccountFactory;
import switchtwentytwenty.project.domain.aggregates.account.BankAccount;
import switchtwentytwenty.project.domain.aggregates.account.CashAccount;
import switchtwentytwenty.project.domain.aggregates.account.IAccount;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.exceptions.AccountAlreadyRegisteredException;
import switchtwentytwenty.project.exceptions.AccountNotRegisteredException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class AccountRepositoryTest {

    @Mock
    IAccountRepositoryJPA accountRepositoryJPA;

    @Mock
    AccountFactory accountFactory;

    @Mock
    IAccountDataDomainAssembler accountDataDomainAssembler;

    @Mock
    IAccount account;

    @Mock
    AccountJPA accountJPA;

    @Mock
    AccountJPA savedAccountJPA;

    @Mock
    IAccount savedAccount;

    @Mock
    IOwnerID mockFamilyID;

    @InjectMocks
    AccountRepository accountRepository;


    Long id = 3L;
    String currency = "EUR";
    BigDecimal amount = new BigDecimal("3");
    MonetaryValue monetaryValue = new MonetaryValue(currency, amount);
    String email = "tonyZe@gamil.com";
    String desig = "x";
    String accType = "CashAccount";

    AccountID accountID = new AccountID(id);
    //Movement movement = new Movement(monetary);
    List<Movement> movements = new ArrayList<>();
    PersonID ownerID = new PersonID(email);
    Designation designation = new Designation(desig);
    AccountType accountType = new AccountType(accType);

    AccountJPA realAccountJPA = new AccountJPA();

    @DisplayName("AccountRepository adds the Account successfully")
    @Test
    void addAccountSucess() {
        when(accountDataDomainAssembler.toData(account)).thenReturn(accountJPA);
        when(accountRepositoryJPA.save(accountJPA)).thenReturn(savedAccountJPA);
        when(accountDataDomainAssembler.createAccountID(savedAccountJPA)).thenReturn(accountID);
        when(accountDataDomainAssembler.createMovements(savedAccountJPA)).thenReturn(movements);
        when(accountDataDomainAssembler.createOwnerID(savedAccountJPA)).thenReturn(ownerID);
        when(accountDataDomainAssembler.createDesignation(savedAccountJPA)).thenReturn(designation);
        when(accountDataDomainAssembler.createAccountType(savedAccountJPA)).thenReturn(accountType);
        when(accountFactory.createAccount(accountID, movements, ownerID, designation, accType)).thenReturn(savedAccount);

        assertDoesNotThrow(() -> accountRepository.add(account));

    }

    @Test
    void addAccountFailAccountAlreadyRegistered() {
        IOwnerID famOwnerID = new FamilyID("@lil@lil.com");
        IAccount familyAccount = new CashAccount(famOwnerID, designation);
        AccountJPA accountJPA = new AccountJPA();
        Optional<AccountJPA> optional = Optional.of(accountJPA);
        when(accountRepositoryJPA.findByOwnerID(any(OwnerIDJPA.class))).thenReturn(optional);

        assertThrows(AccountAlreadyRegisteredException.class, () -> accountRepository.add(familyAccount));

    }

    @DisplayName("AccountRepository fails to add the Account")
    @Test
    void addAccountFailure() {
        when(account.getOwnerId()).thenReturn(new FamilyID());
        when(accountRepositoryJPA.findByOwnerID(new OwnerIDJPA(account.getOwnerId().toString()))).thenReturn(Optional.of(realAccountJPA));

        assertThrows(AccountAlreadyRegisteredException.class, () -> accountRepository.add(account));
    }

    @DisplayName("Check if Account Exists - Success, Account exists")
    @Test
    void getByIdSuccess() {
        when(accountRepositoryJPA.findById(any(Long.class))).thenReturn(Optional.of(new AccountJPA()));
        when(accountDataDomainAssembler.createAccountID(any(AccountJPA.class))).thenReturn(accountID);
        when(accountDataDomainAssembler.createMovements(any(AccountJPA.class))).thenReturn(movements);
        when(accountDataDomainAssembler.createOwnerID(any(AccountJPA.class))).thenReturn(ownerID);
        when(accountDataDomainAssembler.createDesignation(any(AccountJPA.class))).thenReturn(designation);
        when(accountJPA.getAccountType()).thenReturn(accType);
        when(accountFactory.createAccount(any(AccountID.class), any(), any(), any(Designation.class), any())).thenReturn(new BankAccount());

        IAccount returnedAccount = accountRepository.getByID(accountID);

        assertNotNull(returnedAccount);
    }

    @DisplayName("Check if Account Exists - Throws error, Account does not exists")
    @Test
    void getByIdFailure() {
        when(accountRepositoryJPA.findById(any(Long.class))).thenThrow(AccountNotRegisteredException.class);
        assertThrows(AccountNotRegisteredException.class, () -> accountRepository.getByID(accountID));

    }

    @DisplayName("Check if Account Exists - Throws error, Account does not exists")
    @Test
    void getByIdFailureAccountNotRegisteredException() {
        Optional<AccountJPA> optional = Optional.empty();
        when(accountRepositoryJPA.findById(any(Long.class))).thenReturn(optional);
        assertThrows(AccountNotRegisteredException.class, () -> accountRepository.getByID(accountID));

    }


}