package switchtwentytwenty.project.interfaceadapters.implrepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchtwentytwenty.project.datamodel.assemblerjpa.iassemblersjpa.IAccountDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.AccountJPA;
import switchtwentytwenty.project.datamodel.domainjpa.OwnerIDJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.IAccountRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.account.AccountFactory;
import switchtwentytwenty.project.domain.aggregates.account.IAccount;
import switchtwentytwenty.project.domain.valueobject.AccountID;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.IOwnerID;
import switchtwentytwenty.project.exceptions.AccountAlreadyRegisteredException;
import switchtwentytwenty.project.exceptions.AccountNotRegisteredException;
import switchtwentytwenty.project.usecaseservices.irepositories.IAccountRepository;

import switchtwentytwenty.project.domain.valueobject.Designation;
import switchtwentytwenty.project.domain.valueobject.Movement;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository implements IAccountRepository {

    private final IAccountRepositoryJPA accountRepositoryJPA;
    private final AccountFactory accountFactory;
    private final IAccountDataDomainAssembler accountDataDomainAssembler;

    @Autowired
    public AccountRepository(IAccountRepositoryJPA accountRepositoryJPA, AccountFactory accountFactory, IAccountDataDomainAssembler accountDataDomainAssembler) {
        this.accountRepositoryJPA = accountRepositoryJPA;
        this.accountDataDomainAssembler = accountDataDomainAssembler;
        this.accountFactory = accountFactory;
    }

    @Override
    public IAccount getByID(AccountID id) {
        return retrieveAccountById(id);
    }

    private IAccount retrieveAccountById(AccountID accountID) {
        Optional<AccountJPA> accountJPA = accountRepositoryJPA.findById(accountID.getId());
        if (accountJPA.isPresent()){
            return createAccount(accountJPA.get());
        } else {
            throw new AccountNotRegisteredException();
        }
    }

    public IAccount add(IAccount account) {
        if (isAccountAlreadyRegistered(account)) {
            throw new AccountAlreadyRegisteredException();
        } else {
            AccountJPA accountJPA = accountDataDomainAssembler.toData(account);
            AccountJPA savedAccountJPA = accountRepositoryJPA.save(accountJPA);
            return createAccount(savedAccountJPA);
        }
    }

    private boolean isFamilyID(IAccount account){
        boolean isFamily;
        IOwnerID ownerID = account.getOwnerId();
        if (ownerID instanceof FamilyID) {
            isFamily = true;
        } else {
            isFamily = false;
        }
        return isFamily;
    }

    public IAccount createAccount(AccountJPA accountJPA){
        AccountID accountID = accountDataDomainAssembler.createAccountID(accountJPA);
        IOwnerID ownerID = accountDataDomainAssembler.createOwnerID(accountJPA);
        Designation designation = accountDataDomainAssembler.createDesignation(accountJPA);
        List<Movement> movements = accountDataDomainAssembler.createMovements(accountJPA);
        String accountType = accountJPA.getAccountType();
        return accountFactory.createAccount(accountID, movements, ownerID, designation, accountType);
    }

    private boolean isAccountAlreadyRegistered(IAccount account) {
        boolean isAlreadyRegistered = false;
        if (isFamilyID(account)) {
            OwnerIDJPA ownerIDJPA = new OwnerIDJPA(account.getOwnerId().toString());
            Optional<AccountJPA> optional = accountRepositoryJPA.findByOwnerID(ownerIDJPA);
            if (optional.isPresent()) {
                isAlreadyRegistered = true;
            }
        }
        return isAlreadyRegistered;
    }
}