package switchtwentytwenty.project.domain.aggregates.account;

import org.springframework.stereotype.Component;
import switchtwentytwenty.project.domain.valueobject.AccountID;
import switchtwentytwenty.project.domain.valueobject.Designation;
import switchtwentytwenty.project.domain.valueobject.IOwnerID;
import switchtwentytwenty.project.domain.valueobject.Movement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CashAccount extends AbCashAccount {


    public CashAccount(IOwnerID ownerID, Designation designation) {
        super(ownerID, designation);

    }

    public CashAccount() {
        super();
    }

    public CashAccount(AccountID id, IOwnerID ownerID, Designation designation, List<Movement> movements) {
        super(id, ownerID, designation, movements);
    }

    @Override
    public List<Movement> getListOfMovements() {
        List<Movement> copyMovements = new ArrayList<>();
        if (!super.getListOfMovements().isEmpty()) {
            copyMovements.addAll(super.getListOfMovements());
        }
        return copyMovements;
    }

    @Override
    public String getAccountType() {
        return "cash";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CashAccount)) return false;
        CashAccount that = (CashAccount) o;
        return Objects.equals(super.id(), that.id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.id(), super.getOwnerId(), getDesignation(), super.getListOfMovements());
    }
}