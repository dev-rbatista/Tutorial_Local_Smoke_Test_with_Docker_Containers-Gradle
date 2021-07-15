package switchtwentytwenty.project.dto.assemblers.iassemblers;

import switchtwentytwenty.project.dto.accounts.CreateAccountDTO;
import switchtwentytwenty.project.dto.accounts.InputAccountDTO;

public interface IAccountInputDTOAssembler {

    InputAccountDTO toInputDTO (CreateAccountDTO createAccountDTO);
}
