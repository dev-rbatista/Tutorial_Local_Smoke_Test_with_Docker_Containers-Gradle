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
public class BankAccount extends AbNonCashAccount {


    public BankAccount(IOwnerID ownerID, Designation designation) {
        super(ownerID, designation);
    }

    public BankAccount() {
        super();
    }

    public BankAccount(AccountID id, IOwnerID ownerID, Designation designation, List<Movement> movements) {
        super(id, ownerID, designation, movements);
    }


    @Override
    public String getAccountType() {
        return "bank";
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount)) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(super.id(), that.id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.id(), super.getOwnerId());
    }
}