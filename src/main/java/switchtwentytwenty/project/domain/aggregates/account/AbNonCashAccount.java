package switchtwentytwenty.project.domain.aggregates.account;

import switchtwentytwenty.project.domain.valueobject.*;

import java.util.List;

public abstract class AbNonCashAccount extends AbAccount {

    private PersonID personID;

    public AbNonCashAccount(IOwnerID personID, Designation designation) {
        super(designation);
        this.personID = (PersonID) personID;
    }

    public AbNonCashAccount() {
        super();
    }

    public AbNonCashAccount(AccountID id, IOwnerID ownerID, Designation designation, List<Movement> movements) {
        super(id, designation, movements);
        this.personID = (PersonID) ownerID;
    }

    @Override
    public IOwnerID getOwnerId() {
        return this.personID;
    }

    @Override
    public void setOwner(IOwnerID ownerID) {
        this.personID = (PersonID) ownerID;
    }
}