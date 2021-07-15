package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.springframework.stereotype.Component;
import switchtwentytwenty.project.domain.aggregates.account.IAccount;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.accounts.InputAccountDTO;
import switchtwentytwenty.project.dto.accounts.OutputAccountDTO;
import switchtwentytwenty.project.dto.assemblers.iassemblers.IAccountDTODomainAssembler;

import java.math.BigDecimal;


@Component
public class AccountDTODomainAssembler implements IAccountDTODomainAssembler {

    public Designation designationToDomain(InputAccountDTO inputAccountDTO) {
        return new Designation(inputAccountDTO.getDesignation());
    }

    public MonetaryValue initialAmountToDomain(InputAccountDTO inputAccountDTO) {
        BigDecimal amount = inputAccountDTO.getInitialAmount();
        return new MonetaryValue(inputAccountDTO.getCurrency(), amount);
    }

    public IOwnerID ownerIDToDomain(InputAccountDTO inputAccountDTO) {

        /**
         * Alterar o @ no próximo sprint. Check FamilyID.
         */
        char validation = inputAccountDTO.getOwnerID().charAt(0);
        String validationID = Character.toString(validation);
        IOwnerID ownerID;

        if (validationID.equals("@")) {
            ownerID = new FamilyID(inputAccountDTO.getOwnerID());
        } else {
            ownerID = new PersonID(inputAccountDTO.getOwnerID());
        }
        return ownerID;

    }

    public String accountTypeToDomain(InputAccountDTO inputAccountDTO) {
        return inputAccountDTO.getAccountType();
    }

    @Override
    public OutputAccountDTO toDTO(IAccount account) {
        String accountID = account.id().toString();
        String ownerID = account.getOwnerId().toString();
        String designation = account.getDesignation().toString();

        OutputAccountDTO outputAccountDTO = new OutputAccountDTO();
        outputAccountDTO.setAccountID(accountID);
        outputAccountDTO.setDesignation(designation);
        outputAccountDTO.setOwnerID(ownerID);

        return outputAccountDTO;
    }
}