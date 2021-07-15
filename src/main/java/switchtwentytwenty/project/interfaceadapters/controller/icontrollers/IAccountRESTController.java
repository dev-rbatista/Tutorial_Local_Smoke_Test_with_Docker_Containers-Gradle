package switchtwentytwenty.project.interfaceadapters.controller.icontrollers;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.accounts.CreateAccountDTO;
import switchtwentytwenty.project.dto.accounts.OutputAccountDTO;

public interface IAccountRESTController {

    ResponseEntity<OutputAccountDTO> createAccount(CreateAccountDTO createAccountDTO);
}
