package switchtwentytwenty.project.interfaceadapters.controller.implcontrollers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.accounts.CreateAccountDTO;
import switchtwentytwenty.project.dto.accounts.InputAccountDTO;
import switchtwentytwenty.project.dto.accounts.OutputAccountDTO;
import switchtwentytwenty.project.dto.assemblers.iassemblers.IAccountInputDTOAssembler;
import switchtwentytwenty.project.exceptions.AccountAlreadyRegisteredException;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.ICreateAccountService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class AccountRESTControllerTest {

    @Mock
    IAccountInputDTOAssembler mockAccountInputDTOAssembler;

    @Mock
    ICreateAccountService mockCreateAccountService;

    @InjectMocks
    AccountRESTController accountRESTController;

    //Setup
    String designation = "Conta do tonyZe";
    BigDecimal amount = BigDecimal.valueOf(3);
    String currency = "EUR";
    String ownerID = "tonyze@latinlover.com";
    String accountType = "cash";
    String accountID = "3L";
    CreateAccountDTO createCashAccountDTO = new CreateAccountDTO(designation, amount, currency, ownerID, accountType);

    InputAccountDTO inputAccountDTO = new InputAccountDTO(designation, amount, currency, ownerID, accountType);

    OutputAccountDTO outputAccountDTO = new OutputAccountDTO(designation, ownerID, accountID);


    @Test
    @DisplayName("Success case in creating an Account")
    void createCashAccountWithSuccess() {
        OutputAccountDTO expectedOutputAccountDTO = new OutputAccountDTO(designation, ownerID, accountID);
        Link link = linkTo(methodOn(AccountRESTController.class).getAccount(outputAccountDTO.getAccountID())).withSelfRel();
        expectedOutputAccountDTO.add(link);

        Mockito.when(mockAccountInputDTOAssembler.toInputDTO(createCashAccountDTO)).thenReturn(inputAccountDTO);
        Mockito.when(mockCreateAccountService.createAccount(inputAccountDTO)).thenReturn(outputAccountDTO);

        ResponseEntity expected = new ResponseEntity(expectedOutputAccountDTO, HttpStatus.CREATED);

        ResponseEntity result = accountRESTController.createAccount(createCashAccountDTO);

        assertEquals(expected.getBody(), result.getBody());
        assertEquals(result.getStatusCode(), result.getStatusCode());
    }

    @Test
    @DisplayName("Fail case in creating an Account because Family already has account")
    void failToCreateCashAccount() {
        Mockito.when(mockAccountInputDTOAssembler.toInputDTO(createCashAccountDTO)).thenReturn(inputAccountDTO);
        Mockito.when(mockCreateAccountService.createAccount(inputAccountDTO)).thenThrow(AccountAlreadyRegisteredException.class);


        ResponseEntity expected = new ResponseEntity( HttpStatus.UNPROCESSABLE_ENTITY);

        ResponseEntity result = accountRESTController.createAccount(createCashAccountDTO);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Obtain exception when trying to get account")
    void throwUnsupportedOperationExceptionWhenTryingToGetAccount() {
        assertThrows(UnsupportedOperationException.class, () -> accountRESTController.getAccount(accountID));

    }


}