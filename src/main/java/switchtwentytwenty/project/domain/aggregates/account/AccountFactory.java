package switchtwentytwenty.project.domain.aggregates.account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import switchtwentytwenty.project.domain.valueobject.*;

import java.util.List;

@Component
public class AccountFactory {

    @Autowired
    private Environment environment;

    public IAccount createAccount(Designation designation, MonetaryValue monetaryValue, IOwnerID ownerID, String accountType) {
        IAccount newIAccount;
        Movement movement = new Movement(monetaryValue);

        String classpath = environment.getProperty(accountType.toLowerCase());


        try {
            newIAccount = (IAccount) Class.forName(classpath).newInstance();


            // BATISTA !!!
            // Como já não ha build e não sabemos qual instancia de Account a "newAccount" se vai tornar,
            // usamos construtores vazios para instanciar qualquer tipo de Account e set's para adicionar os atributos
            // que são comuns a todas elas.

            newIAccount.setDesignation(designation);
            newIAccount.addMovement(movement);
            newIAccount.setOwner(ownerID);

        } catch (NullPointerException | ClassNotFoundException | InstantiationException | IllegalAccessException exception) {
            throw new IllegalArgumentException("Unsupported Account type");
        }
        return newIAccount;
    }

    public IAccount createAccount(AccountID accountID, List<Movement> movements, IOwnerID ownerID, Designation designation, String accountType) {
        IAccount account;

        String classpath = environment.getProperty(accountType.toLowerCase());


        try {
            account = (IAccount) Class.forName(classpath).newInstance();
            account.setAccountID(accountID);
            account.setDesignation(designation);
            account.setMovements(movements);
            account.setOwner(ownerID);
        } catch (NullPointerException | ClassNotFoundException | InstantiationException | IllegalAccessException exception) {
            throw new IllegalArgumentException("Unsupported Account type");
        }
        return account;
    }
}