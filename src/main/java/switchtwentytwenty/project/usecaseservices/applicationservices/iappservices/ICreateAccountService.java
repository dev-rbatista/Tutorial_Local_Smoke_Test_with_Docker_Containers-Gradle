package switchtwentytwenty.project.usecaseservices.applicationservices.iappservices;

import org.springframework.stereotype.Component;
import switchtwentytwenty.project.dto.accounts.OutputAccountDTO;
import switchtwentytwenty.project.dto.accounts.InputAccountDTO;

@Component
public interface ICreateAccountService {

    OutputAccountDTO createAccount(InputAccountDTO inputAccountDTO);
}

