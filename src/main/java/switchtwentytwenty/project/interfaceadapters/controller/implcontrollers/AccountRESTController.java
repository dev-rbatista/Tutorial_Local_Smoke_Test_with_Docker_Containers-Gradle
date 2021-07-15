package switchtwentytwenty.project.interfaceadapters.controller.implcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentytwenty.project.dto.accounts.CreateAccountDTO;
import switchtwentytwenty.project.dto.accounts.InputAccountDTO;
import switchtwentytwenty.project.dto.accounts.OutputAccountDTO;
import switchtwentytwenty.project.dto.assemblers.iassemblers.IAccountInputDTOAssembler;
import switchtwentytwenty.project.exceptions.AccountNotRegisteredException;
import switchtwentytwenty.project.interfaceadapters.controller.icontrollers.IAccountRESTController;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.ICreateAccountService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/accounts")
@CrossOrigin(value = "*")
public class AccountRESTController implements IAccountRESTController {


    private final ICreateAccountService createAccountService;
    private final IAccountInputDTOAssembler accountInputDTOAssembler;

    @Autowired
    public AccountRESTController (ICreateAccountService createAccountService, IAccountInputDTOAssembler accountInputDTOAssembler) {
        this.createAccountService = createAccountService;
        this.accountInputDTOAssembler = accountInputDTOAssembler;
    }


    @PostMapping
    public ResponseEntity<OutputAccountDTO> createAccount(@RequestBody CreateAccountDTO createAccountDTO) {
        InputAccountDTO inputAccountDTO = accountInputDTOAssembler.toInputDTO(createAccountDTO);
        OutputAccountDTO outputAccountDTO;

        try {
            outputAccountDTO = createAccountService.createAccount(inputAccountDTO);
            Link selfLink = linkTo(methodOn(AccountRESTController.class).getAccount(outputAccountDTO.getAccountID())).withSelfRel();
            outputAccountDTO.add(selfLink);
            return new ResponseEntity<>(outputAccountDTO, HttpStatus.CREATED);
        }
        catch(IllegalArgumentException | AccountNotRegisteredException exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }



    @GetMapping(value = "/{accountID}")
    public ResponseEntity<Object> getAccount(@PathVariable String accountID){
        throw new UnsupportedOperationException();
    }


}