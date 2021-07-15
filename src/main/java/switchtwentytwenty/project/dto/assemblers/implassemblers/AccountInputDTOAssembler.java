package switchtwentytwenty.project.dto.assemblers.implassemblers;

import org.springframework.stereotype.Component;
import switchtwentytwenty.project.dto.accounts.CreateAccountDTO;
import switchtwentytwenty.project.dto.accounts.InputAccountDTO;
import switchtwentytwenty.project.dto.assemblers.iassemblers.IAccountInputDTOAssembler;

import java.math.BigDecimal;

@Component
public class AccountInputDTOAssembler implements IAccountInputDTOAssembler {

     @Override
    public InputAccountDTO toInputDTO(CreateAccountDTO createAccountDTO) {
        String designation = createAccountDTO.getDesignation();
        BigDecimal initialAmount = createAccountDTO.getInitialAmount();
        String currency = createAccountDTO.getCurrency();
        String ownerID = createAccountDTO.getOwnerID();
        String accountType = createAccountDTO.getAccountType();
         return new InputAccountDTO(designation, initialAmount, currency, ownerID, accountType);
    }
}

