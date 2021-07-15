/*package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.AccountDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.AccountJPA;
import switchtwentytwenty.project.datamodel.domainjpa.OwnerIDJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.IAccountRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.account.AccountFactory;
import switchtwentytwenty.project.domain.valueobject.AccountID;
import switchtwentytwenty.project.dto.assemblers.implassemblers.AccountDTODomainAssembler;
import switchtwentytwenty.project.usecaseservices.irepositories.IAccountRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateAccountServiceIT {


    CreateAccountService createAccountService;

    @Mock
    IAccountRepository iAccountRepository;

    @Mock
    IAccountRepositoryJPA iAccountRepositoryJPA;

    @Autowired
    AccountDTODomainAssembler accountDTODomainAssembler;

    @Autowired
    AccountFactory accountFactory;

    @Autowired
    AccountDataDomainAssembler accountDataDomainAssembler;

    AccountID accountID = new AccountID(123L);

    @Disabled
    @Test
    public void createAccountSuccessCase() {



Mockito.when(iAccountRepositoryJPA.findByOwnerID(any(OwnerIDJPA.class))).thenReturn(Optional.empty());





    }

}*/