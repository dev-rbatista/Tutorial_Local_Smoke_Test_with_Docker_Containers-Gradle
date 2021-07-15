package switchtwentytwenty.project.domain.aggregates.account;

import switchtwentytwenty.project.domain.valueobject.AccountID;
import switchtwentytwenty.project.domain.valueobject.Designation;
import switchtwentytwenty.project.domain.valueobject.Movement;

import java.util.ArrayList;
import java.util.List;


public abstract class AbAccount implements IAccount {

    private AccountID accountID;

    private Designation designation;

    private List<Movement> movements = new ArrayList<>();

    public AbAccount(Designation designation) {
        this.designation = designation;
    }

    public AbAccount() {
    }

    public AbAccount(AccountID id, Designation designation, List<Movement> movements) {
        this.accountID = id;
        this.designation = designation;
        this.movements = listOfMovementsClone(movements);
    }

    @Override
    public AccountID id() {
        return this.accountID;
    }

    @Override
    public List<Movement> getListOfMovements() {
        return listOfMovementsClone(this.movements);
    }

    private List<Movement> listOfMovementsClone(List<Movement> movements) {
        return new ArrayList<>(movements);
    }

    @Override
    public void setAccountID(AccountID accountID) {
        this.accountID = accountID;
    }

    @Override
    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    @Override
    public Designation getDesignation() {
        return this.designation;
    }

    @Override
    public boolean hasID(AccountID id) {
        return this.accountID == id;
    }

    @Override
    public void setMovements(List<Movement> movements) {
        this.movements = listOfMovementsClone(movements);
    }

    @Override
    public void addMovement(Movement movement) {
        this.movements.add(movement);
    }
}