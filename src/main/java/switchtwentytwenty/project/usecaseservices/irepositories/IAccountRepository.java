package switchtwentytwenty.project.usecaseservices.irepositories;

import switchtwentytwenty.project.domain.aggregates.account.IAccount;
import switchtwentytwenty.project.domain.valueobject.AccountID;

public interface IAccountRepository extends Repository<IAccount, AccountID> {

    IAccount add(IAccount entity);

}