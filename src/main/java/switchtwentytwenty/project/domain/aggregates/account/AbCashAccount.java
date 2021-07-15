package switchtwentytwenty.project.domain.aggregates.account;

import switchtwentytwenty.project.domain.valueobject.AccountID;
import switchtwentytwenty.project.domain.valueobject.Designation;
import switchtwentytwenty.project.domain.valueobject.IOwnerID;
import switchtwentytwenty.project.domain.valueobject.Movement;

import java.util.List;


public abstract class AbCashAccount extends AbAccount {

    private IOwnerID ownerID;

    public AbCashAccount(IOwnerID ownerID, Designation designation) {
        super(designation);
        this.ownerID = ownerID;
    }

    public AbCashAccount() {
        super();
    }

    public AbCashAccount(AccountID id, IOwnerID ownerID, Designation designation, List<Movement> movements) {
        super(id, designation, movements);
        this.ownerID = ownerID;

    }

    @Override
    public IOwnerID getOwnerId() {
        return this.ownerID;
    }

    @Override
    public void setOwner(IOwnerID ownerID) {
        this.ownerID = ownerID;
    }

}