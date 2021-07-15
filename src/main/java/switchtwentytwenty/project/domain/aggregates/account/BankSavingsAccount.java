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
public class BankSavingsAccount extends AbNonCashAccount {


    protected BankSavingsAccount(IOwnerID ownerID, Designation designation) {
        super(ownerID, designation);
    }

    public BankSavingsAccount() {
        super();
    }

    public BankSavingsAccount(AccountID id, IOwnerID ownerID, Designation designation, List<Movement> movements) {
        super(id, ownerID, designation, movements);
    }

    @Override
    public String getAccountType() {
        return "savings";
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
        if (!(o instanceof BankSavingsAccount)) return false;
        BankSavingsAccount that = (BankSavingsAccount) o;
        return Objects.equals(super.id(), that.id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.id());
    }
}