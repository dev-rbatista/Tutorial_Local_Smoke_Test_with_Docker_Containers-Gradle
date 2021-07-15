package switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa;

import org.springframework.stereotype.Component;
import switchtwentytwenty.project.datamodel.assemblerjpa.iassemblersjpa.IAccountDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.AccountJPA;
import switchtwentytwenty.project.datamodel.domainjpa.MovementJPA;
import switchtwentytwenty.project.datamodel.domainjpa.OwnerIDJPA;
import switchtwentytwenty.project.domain.aggregates.account.IAccount;
import switchtwentytwenty.project.domain.valueobject.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDataDomainAssembler implements IAccountDataDomainAssembler {

    @Override
    public AccountJPA toData(IAccount account) {


        OwnerIDJPA ownerId = new OwnerIDJPA(account.getOwnerId().toString());

        String designation = account.getDesignation().toString();
        String accountType = account.getAccountType();
        List<Movement> movements = account.getListOfMovements();

        AccountJPA accountJPA;

        if (account.id() == null) {
            accountJPA = new AccountJPA(ownerId, designation, accountType);
        } else {
            accountJPA = new AccountJPA(account.id().getId(), ownerId, designation, accountType);
        }
        List<MovementJPA> movementJPAList = new ArrayList<>();

        for (Movement movement : movements) {
            String currency = movement.getMonetaryValue().getCurrency().toString();
            Long amount = movement.getMonetaryValue().getAmount().longValue();
            MovementJPA movementJPA = new MovementJPA(amount, currency, accountJPA);
            movementJPAList.add(movementJPA);
        }
        accountJPA.setMovements(movementJPAList);
        return accountJPA;
    }

    public AccountID createAccountID(AccountJPA accountJPA) {
        return new AccountID(accountJPA.getId());
    }

    public IOwnerID createOwnerID(AccountJPA accountJPA){
        char validation = accountJPA.getOwnerID().toString().charAt(0);
        String validationID = Character.toString(validation);
        IOwnerID ownerID;
        if(validationID.equals("@")){
            ownerID = new FamilyID(accountJPA.getOwnerID().toString());
        } else {
            ownerID = new PersonID(accountJPA.getOwnerID().toString());
        }
        return ownerID;
    }

    public Designation createDesignation(AccountJPA accountJPA) {
        return new Designation(accountJPA.getDesignation());
    }

    public AccountType createAccountType(AccountJPA accountJPA) {
        return new AccountType(accountJPA.getAccountType());
    }

    public List<Movement> createMovements(AccountJPA accountJPA) {
        List<Movement> movements = new ArrayList<>();
        List<MovementJPA> movementJPAList = accountJPA.getMovements();

        for (MovementJPA movementJPA : movementJPAList) {
            String currency = movementJPA.getCurrency();
            BigDecimal amount = new BigDecimal(movementJPA.getAmount());
            MonetaryValue monetaryValue = new MonetaryValue(currency, amount);
            Movement movement = new Movement(monetaryValue);
            movements.add(movement);
        }

        return movements;
    }


}