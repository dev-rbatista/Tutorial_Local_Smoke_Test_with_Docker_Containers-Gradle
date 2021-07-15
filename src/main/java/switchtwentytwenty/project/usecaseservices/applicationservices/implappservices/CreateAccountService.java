package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.domain.aggregates.account.AccountFactory;
import switchtwentytwenty.project.domain.aggregates.account.IAccount;
import switchtwentytwenty.project.domain.valueobject.Designation;
import switchtwentytwenty.project.domain.valueobject.MonetaryValue;
import switchtwentytwenty.project.domain.valueobject.IOwnerID;
import switchtwentytwenty.project.dto.accounts.InputAccountDTO;
import switchtwentytwenty.project.dto.accounts.OutputAccountDTO;
import switchtwentytwenty.project.dto.assemblers.implassemblers.AccountDTODomainAssembler;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.ICreateAccountService;
import switchtwentytwenty.project.usecaseservices.irepositories.IAccountRepository;

@Service
@NoArgsConstructor
public class CreateAccountService implements ICreateAccountService {

    private AccountFactory accountFactory;
    private IAccountRepository accountRepository;
    private AccountDTODomainAssembler accountDTODomainAssembler;

    @Autowired
    public CreateAccountService(IAccountRepository accountRepository, AccountDTODomainAssembler accountDTODomainAssembler,AccountFactory accountFactory)  {
        this.accountRepository = accountRepository;
        this.accountDTODomainAssembler = accountDTODomainAssembler;
        this.accountFactory = accountFactory;
    }

    @Override
    public OutputAccountDTO createAccount(InputAccountDTO inputAccountDTO){

        Designation designation = accountDTODomainAssembler.designationToDomain(inputAccountDTO);
        MonetaryValue monetaryValue = accountDTODomainAssembler.initialAmountToDomain(inputAccountDTO);
        IOwnerID ownerID = accountDTODomainAssembler.ownerIDToDomain(inputAccountDTO);
        String accountType = accountDTODomainAssembler.accountTypeToDomain(inputAccountDTO);

        IAccount account = accountFactory.createAccount(designation, monetaryValue, ownerID, accountType);
        IAccount savedAccount = accountRepository.add(account);
        return accountDTODomainAssembler.toDTO(savedAccount);
    }

}